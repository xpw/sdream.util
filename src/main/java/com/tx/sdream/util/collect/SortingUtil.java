package com.tx.sdream.util.collect;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 排序工具，元素null safe<br>
 * <p>
 * 使用方式详见TestSortingUtils
 */
public class SortingUtil {

    /**
     * 排序list。只需要返回一个可排序的值，例如Integer。
     *
     * @param list
     * @param sortingField
     */
    public static <T, R extends Comparable<R>> void sort(List<T> list, final SortingField<T, R> sortingField) {
        if (list == null || sortingField == null) {
            return;
        }

        Collections.sort(list, new Comparator<T>() {
            @Override
            public int compare(T left, T right) {
                boolean isLeftNull = left == null;
                boolean isRightNull = right == null;
                if (isLeftNull && isRightNull) {
                    return 0;
                } else if (isLeftNull) {
                    return sortingField.isNullFirst() ? -1 : 1;
                } else if (isRightNull) {
                    return sortingField.isNullFirst() ? 1 : -1;
                }

                R comparableLeft = sortingField.apply(left);
                R comparableRight = sortingField.apply(right);
                isLeftNull = comparableLeft == null;
                isRightNull = comparableRight == null;
                if (isLeftNull && isRightNull) {
                    return 0;
                } else if (isLeftNull) {
                    return sortingField.isNullFirst() ? -1 : 1;
                } else if (isRightNull) {
                    return sortingField.isNullFirst() ? 1 : -1;
                }

                return sortingField.isAsc() ? comparableLeft.compareTo(comparableRight)
                        : comparableRight.compareTo(comparableLeft);
            }
        });
    }

    @SafeVarargs
    public static <T> void sort(List<T> list, final SortingField<T, ? extends Comparable<?>>... sortingFields) {
        if (list == null || sortingFields == null || sortingFields.length == 0) {
            return;
        }

        Collections.sort(list, new Comparator<T>() {
            @SuppressWarnings("unchecked")
            @Override
            public int compare(T left, T right) {
                for (SortingField<T, ? extends Comparable<?>> sortingField : sortingFields) {
                    boolean isLeftNull = left == null;
                    boolean isRightNull = right == null;
                    if (isLeftNull && isRightNull) {
                        continue;
                    } else if (isLeftNull) {
                        return sortingField.isNullFirst() ? -1 : 1;
                    } else if (isRightNull) {
                        return sortingField.isNullFirst() ? 1 : -1;
                    }

                    Comparable<Object> comparableLeft = (Comparable<Object>) sortingField.apply(left);
                    Comparable<Object> comparableRight = (Comparable<Object>) sortingField.apply(right);
                    isLeftNull = comparableLeft == null;
                    isRightNull = comparableRight == null;
                    if (isLeftNull && isRightNull) {
                        continue;
                    } else if (isLeftNull) {
                        return sortingField.isNullFirst() ? -1 : 1;
                    } else if (isRightNull) {
                        return sortingField.isNullFirst() ? 1 : -1;
                    }

                    int compareResult = comparableLeft.compareTo(comparableRight);
                    if (compareResult == 0) {
                        continue;
                    }
                    return (sortingField.isAsc() ? 1 : -1) * compareResult;
                }
                return 0;
            }
        });
    }
}