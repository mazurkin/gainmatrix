package com.gainmatrix.lib.beans;

import com.gainmatrix.lib.serialization.SerialVersionUID;
import com.google.common.base.Preconditions;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Comparator for identified objects
 * @param <T> Object type
 * @param <I> Identifier type
 */
public class IdentifiedComparator<T extends Identified<I>, I extends Comparable<I>>
    implements Comparator<T>, Serializable
{

    private static final long serialVersionUID = SerialVersionUID.UNCONTROLLED;

    @Override
    public int compare(T o1, T o2) {
        Preconditions.checkNotNull(o1, "Reference #1 is null");
        Preconditions.checkNotNull(o2, "Reference #2 is null");

        I id1 = o1.getId();
        Preconditions.checkNotNull(id1, "Identifier #1 is null");

        I id2 = o2.getId();
        Preconditions.checkNotNull(id2, "Identifier #2 is null");

        return id1.compareTo(id2);
    }

}
