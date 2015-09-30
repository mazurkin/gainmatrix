package com.gainmatrix.lib.money;

import com.gainmatrix.lib.object.HashCodeUtils;
import com.gainmatrix.lib.preconditions.Preconditions2;
import com.gainmatrix.lib.serialization.SerialVersionUID;
import com.google.common.base.Preconditions;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

public final class Money implements Serializable, Comparable<Money> {

    private static final long serialVersionUID = SerialVersionUID.UNCONTROLLED;

    private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

    private final BigDecimal amount;

    private final String currency;

    private final int scale;

    public Money(BigDecimal amount, String currency, int scale) {
        Preconditions.checkNotNull(amount, "Amount is null");
        Preconditions.checkNotNull(currency, "Currency is null");
        Preconditions.checkArgument(scale >= 0, "Scale should be >= 0");

        this.amount = amount.setScale(scale, ROUNDING_MODE);
        this.currency = currency;
        this.scale = scale;
    }

    public Money(BigDecimal amount, Currency currency) {
        this(
                amount,
                currency.getCurrencyCode(),
                currency.getDefaultFractionDigits() > 0 ? currency.getDefaultFractionDigits() : 0
        );
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public Money add(Money value) {
        Preconditions.checkNotNull(value);
        Preconditions2.checkEqual(this.currency, value.currency);

        BigDecimal amount = this.amount.add(value.amount);
        amount = amount.setScale(this.scale, ROUNDING_MODE);

        return new Money(amount, this.currency, scale);
    }

    public Money substract(Money value) {
        Preconditions.checkNotNull(value);
        Preconditions2.checkEqual(this.currency, value.currency);

        BigDecimal amount = this.amount.subtract(value.amount);
        amount = amount.setScale(this.scale, ROUNDING_MODE);

        return new Money(amount, this.currency, scale);
    }

    public Money multiple(BigDecimal multiplicand) {
        Preconditions.checkNotNull(multiplicand);

        BigDecimal amount = this.amount.multiply(multiplicand);
        amount = amount.setScale(this.scale, ROUNDING_MODE);

        return new Money(amount, this.currency, scale);
    }

    public Money divide(BigDecimal divisor) {
        Preconditions.checkNotNull(divisor);

        BigDecimal amount = this.amount.divide(divisor, this.scale, ROUNDING_MODE);

        return new Money(amount, this.currency, scale);
    }

    @Override
    public int compareTo(Money that) {
        Preconditions.checkNotNull(that);
        Preconditions2.checkEqual(this.currency, that.currency);

        return this.amount.compareTo(that.amount);
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) { return true; }
        if (that == null) { return false; }
        if (getClass() != that.getClass()) { return false; }

        Money money = (Money) that;

        return
                (this.amount.equals(money.amount)) &&
                (this.currency.equals(money.currency)) &&
                (this.scale == money.scale);
    }

    @Override
    public int hashCode() {
        int result = HashCodeUtils.init();
        result = HashCodeUtils.hash(result, amount);
        result = HashCodeUtils.hash(result, currency);
        result = HashCodeUtils.hash(result, scale);
        return result;
    }

    @Override
    public String toString() {
        return String.format("%s %s", amount.toString(), currency);
    }

}
