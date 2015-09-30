package com.gainmatrix.lib.paging;

import com.gainmatrix.lib.serialization.SerialVersionUID;
import com.google.common.base.Preconditions;

import java.io.Serializable;

/**
 * Класс описывающий выборку из источника данных
 */
public class Extraction implements Serializable {

    public static final Extraction ALL = null;

    public static final Extraction FIRST = new Extraction(0, 1);

    public static final Extraction NONE = new Extraction(0, 0);

    private static final long serialVersionUID = SerialVersionUID.UNCONTROLLED;

    /**
     * Индекс смещения выборки (начинается с нуля)
     */
    private final int offset;

    /**
     * Максимальное число элементов в запрашиваемой выборке
     */
    private final int count;

    public Extraction(int offset, int count) {
        Preconditions.checkArgument(offset >= 0, "Offset must be positive or zero");
        Preconditions.checkArgument(count >= 0, "Count must be positive or zero");

        this.offset = offset;
        this.count = count;
    }

    public Extraction(int count) {
        this(0, count);
    }

    public Extraction() {
        this(0, 0);
    }

    public int getOffset() {
        return offset;
    }

    public int getCount() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public static boolean isRequired(Extraction extraction) {
        return extraction != null;
    }

}
