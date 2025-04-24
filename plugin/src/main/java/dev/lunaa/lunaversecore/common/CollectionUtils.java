package dev.lunaa.lunaversecore.common;

import java.util.Collection;

public class CollectionUtils {

    public static <E> boolean nullOrEmpty(Collection<E> collection) {
        return collection == null || collection.isEmpty();
    }

}
