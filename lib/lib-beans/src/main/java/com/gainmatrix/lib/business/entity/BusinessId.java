package com.gainmatrix.lib.business.entity;

import com.gainmatrix.lib.object.HashCodeUtils;
import com.gainmatrix.lib.serialization.SerialVersionUID;

import java.io.Serializable;
import java.util.UUID;

/**
 * Бизнес-идентификатор сущности.
 * <p/>
 * Призван решить фундаментальную проблему с реализацией методов equals() и hashCode() при работе с сущностями
 * в ORM. Вводит глобально-уникальный код на основе UUID который позволяет однозначно идентифицировать сущность как до
 * сохранения в БД, так и после сохранения в БД.
 * @see <a href="http://stackoverflow.com/questions/5031614/the-jpa-hashcode-equals-dilemma/5103360#5103360">
 *     Первоначальная идея бизнес-идентификатора</a>
 * @see <a href="http://community.jboss.org/wiki/EqualsAndHashCode">
 *     Equals and HashCode - Справочная информация из Hibernate FAQ</a>
 * @see <a href="http://onjava.com/pub/a/onjava/2006/09/13/dont-let-hibernate-steal-your-identity.html">
 *     Дискуссия по проблеме идентификации</a>
 * @see Object#hashCode()
 * @see Object#equals(Object)
 */
public final class BusinessId implements Serializable {

    private static final long serialVersionUID = SerialVersionUID.UNCONTROLLED;

    private long hi;

    private long lo;

    public BusinessId() {
        this(0, 0);
    }

    public BusinessId(long hi, long lo) {
        this.hi = hi;
        this.lo = lo;
    }

    public static BusinessId fromString(String uuidText) {
        UUID uuid = UUID.fromString(uuidText);
        return fromUuid(uuid);
    }

    public static BusinessId fromUuid(UUID uuid) {
        return new BusinessId(uuid.getMostSignificantBits(), uuid.getLeastSignificantBits());
    }

    public static BusinessId generateUnique() {
        UUID uuid = UUID.randomUUID();
        return BusinessId.fromUuid(uuid);
    }

    public long getLo() {
        return lo;
    }

    protected void setLo(long lo) {
        this.lo = lo;
    }

    public long getHi() {
        return hi;
    }

    protected void setHi(long hi) {
        this.hi = hi;
    }

    public UUID getUuid() {
        return new UUID(hi, lo);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != this.getClass()) { return false; }

        BusinessId that = (BusinessId) obj;

        if (this.hi != that.hi) { return false; }
        if (this.lo != that.lo) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        int result = HashCodeUtils.init();
        result = HashCodeUtils.hash(result, lo);
        result = HashCodeUtils.hash(result, hi);
        return result;
    }

    @Override
    public String toString() {
        return getUuid().toString();
    }

}
