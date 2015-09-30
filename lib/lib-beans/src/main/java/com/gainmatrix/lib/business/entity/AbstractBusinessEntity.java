package com.gainmatrix.lib.business.entity;

import com.gainmatrix.lib.serialization.SerialVersionUID;

import java.io.Serializable;

/**
 * Абстрактный базовый класс для сущностей.
 * <br/>
 * Делегирует стандартные функции hashCode и equals бизнес-ключам этих сущностей.
 * <br/>
 * Содержит версию мета-модели для хранения в БД. Данная версия должна автоматически устанавливаться в текущее
 * значение текущей версии мета-модели при операциях вставки и обновления.
 *
 * @param <I> Тип идентификатора
 */
public abstract class AbstractBusinessEntity<I> implements BusinessEntity<I>, Serializable {

    public static final int BUSINESS_MODEL_VERSION_UNDEFINED = -1;

    private static final long serialVersionUID = SerialVersionUID.UNCONTROLLED;

    /**
     * Бизнес-идентификатор сущности
     */
    private BusinessId businessId;

    /**
     * Версия бизнес-модели которая создала эту сущность
     */
    private int businessModelVersion;

    /**
     * Версия сущности
     */
    private int version;

    protected AbstractBusinessEntity() {
        this.businessId = BusinessId.generateUnique();
        this.version = 0;
        this.businessModelVersion = getCurrentBusinessModelVersion();
    }

    @Override
    public BusinessId getBusinessId() {
        return businessId;
    }

    protected void setBusinessId(BusinessId businessId) {
        this.businessId = businessId;
    }

    @Override
    public int getBusinessModelVersion() {
        return businessModelVersion;
    }

    protected void setBusinessModelVersion(int businessModelVersion) {
        this.businessModelVersion = businessModelVersion;
    }

    @Override
    public int getVersion() {
        return version;
    }

    protected void setVersion(int version) {
        this.version = version;
    }

    protected void updateBusinessModelVersion() {
        this.businessModelVersion = getCurrentBusinessModelVersion();
    }

    @Override
    public int hashCode() {
        return this.businessId.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }

        if ((!this.getClass().isAssignableFrom(obj.getClass())) &&
            (!obj.getClass().isAssignableFrom(this.getClass())))
        {
            return false;
        }

        AbstractBusinessEntity that = (AbstractBusinessEntity) obj;

        return this.getBusinessId().equals(that.getBusinessId());
    }

    /**
     * Should return current global version of business model in the application
     * @return Constant version of business model
     */
    public abstract int getCurrentBusinessModelVersion();

}
