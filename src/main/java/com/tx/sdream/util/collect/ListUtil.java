package com.tx.sdream.util.collect;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * list常用方法
 */
public class ListUtil {

    /**
     * 转换list为map
     *
     * @param list
     * @param keyMapper
     * @param valueMapper
     * @return
     */
    public static <T, K, V> Map<K, List<V>> toMapList(List<T> list,
                                                      Function<? super T, ? extends K> keyMapper,
                                                      Function<? super T, ? extends V> valueMapper) {
        if (list == null) {
            return new HashMap<>();
        }
        Map<K, List<V>> map = new HashMap<>();
        for (T t : list) {
            if (t == null) {
                continue;
            }
            K key = keyMapper.apply(t);
            List<V> values = map.get(key);
            if (values == null) {
                values = new ArrayList<>();
                map.put(key, values);
            }
            V value = valueMapper.apply(t);
            values.add(value);
        }
        return map;
    }

    /**
     * 转换list为set
     *
     * @param list
     * @param mapper
     * @return
     */
    @Deprecated
    public static <T, K> Set<K> toSet2(List<T> list, Function<? super T, ? extends K> mapper) {
        if (list == null) {
            return new HashSet<>();
        }
        Set<K> set = new HashSet<>();
        for (T t : list) {
            if (t == null) {
                continue;
            }
            K key = mapper.apply(t);
            set.add(key);
        }
        return set;
    }

    /**
     * 转换list为set
     *
     * @param list
     * @param mapper 支持lambda写法
     * @return
     */
    public static <T, R> Set<R> toSet(List<T> list, Function<? super T, ? extends R> mapper) {
        if (list == null || list.isEmpty()) {
            return new HashSet<>();
        }
        return list.stream().map(mapper).collect(Collectors.toSet());
    }

    /**
     * list -> set
     *
     * @param list
     * @param <T>
     * @return
     */
    public static <T> Set<T> toSet(List<T> list) {
        if (list == null || list.isEmpty()) {
            return new HashSet<>();
        }
        return new HashSet<>(list);
    }

    /**
     * array -> set
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> Set<T> toSet(T[] t) {
        List<T> list = toList(t);
        return toSet(list);
    }

    /**
     * array -> set
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> Set<T> ByVariableParams(T... t) {
        return toSet(t);
    }

    /**
     * 数组 -> list, 返回的list是Arrays的内部类ArrayList,而不是java.util.ArrayList,不可操作
     *
     * @param array
     * @param <T>
     * @return
     */
    public static <T> List<T> toListAndNotAllowEdit(T[] array) {
        if (array == null || array.length == 0) {
            return new ArrayList<>();
        }
        return Arrays.asList(array);
    }

    /**
     * 数组 -> list, 返回的list可操作。不同于Arrays.asList()生成的list不可操作
     *
     * @param array
     * @param <T>
     * @return
     */
    public static <T> List<T> toList(T[] array) {
        if (array == null || array.length == 0) {
            return new ArrayList<>();
        }
        List<T> list = new ArrayList<>(array.length);
        for (T t : array) {
            list.add(t);
        }
        return list;
    }

    /**
     * 可变参数。 数组 -> list, 返回的list可操作。
     *
     * @param array
     * @param <T>
     * @return
     */
    public static <T> List<T> toListByVariableParams(T... array) {
        return toList(array);
    }

    /**
     * set -> list
     *
     * @param set
     * @param <T>
     * @return
     */
    public static <T> List<T> toList(Set<T> set) {
        if (set == null || set.isEmpty()) {
            return new ArrayList<>();
        }
        List<T> list = new ArrayList<>(set);
        return list;
    }

    /**
     * 转换list为新list
     *
     * @param list
     * @param mapper 支持lambda写法
     * @return
     */
    public static <T, R> List<? extends R> toList(List<T> list, Function<? super T, ? extends R> mapper) {
        if (list == null) {
            return new ArrayList<>();
        }
        return list.stream().map(mapper).collect(Collectors.toList());
    }

    /**
     * 转换list为新list
     *
     * @param list
     * @param mapper 支持lambda写法
     * @return
     */
    public static <T, R> List<? extends R> toListAndDistinct(List<T> list, Function<? super T, ? extends R> mapper) {
        if (list == null) {
            return new ArrayList<>();
        }
        Set<? extends R> set = toSet(list, mapper);
        return new ArrayList<>(set);
    }

    /**
     * 字符串转list
     *
     * @param str       目标串
     * @param delimiter 分隔符
     * @return
     */
    public static List<String> string2List(String str, String delimiter) {
        if (str == null) {
            return new ArrayList<>();
        }
        String[] split = str.split(delimiter);
        return toList(split);
    }

    /**
     * 字符串转list,且去重
     *
     * @param str       目标串
     * @param delimiter 分隔符
     * @return
     */
    public static Set<String> string2ListAndDistict(String str, String delimiter) {
        if (str == null) {
            return new HashSet<>();
        }
        String[] split = str.split(delimiter);
        return new HashSet<>(Arrays.asList(split));
    }

    /**
     * list -> array
     *
     * @param list
     * @param <T>
     * @return
     */
    public static <T> T[] toArray(List<T> list) {
        return (T[]) list.toArray();
    }

    /**
     * list -> array
     *
     * @param list
     * @param mapper
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R> R[] toArray(List<T> list, Function<? super T, ? extends R> mapper) {
        if (list == null || list.isEmpty()) {
            return (R[]) new Object[]{};
        }
        List<? extends R> collect = list.stream().map(mapper).collect(Collectors.toList());
        return (R[]) collect.toArray();
    }

    /**
     * list -> array
     *
     * @param set
     * @param <T>
     * @return
     */
    public static <T> T[] toArray(Set<T> set) {
        return (T[]) set.toArray();
    }

    /**
     * 数值求和
     *
     * @param list
     * @param mapper
     * @return
     */
    public static <T, R> BigDecimal sum(List<T> list, Function<? super T, ? extends R> mapper) {
        BigDecimal sum = BigDecimal.ZERO;
        if (list == null) {
            return sum;
        }
        for (T t : list) {
            if (t == null) continue;
            Object val = mapper.apply(t);
            if (val == null) continue;
            BigDecimal a = null;
            if (!(val instanceof BigDecimal)) {
                try {
                    a = new BigDecimal(val.toString());
                } catch (Exception e) {// ignore
                }
            } else {
                a = (BigDecimal) val;
            }
            if (a == null) continue;
            sum = sum.add(a);
        }
        return sum;
    }

    /**
     * 将list按照指定的条件predicate过滤
     *
     * @param list
     * @param predicate
     * @param <T>
     * @return
     */
    public static <T> List<T> filter(List<T> list, Predicate<? super T> predicate) {
        if (list == null) {
            return new ArrayList<>();
        }
        return list.stream().filter(predicate).collect(Collectors.toList());
    }

    /**
     * 拷贝新的list
     *
     * @param list
     * @param <T>
     * @return
     */
    public static <T> List<T> copy(List<T> list) {
        if (list == null || list.isEmpty()) {
            return new ArrayList<>();
        }
        List<T> newList = new ArrayList<>();
        newList.addAll(list);
        return newList;
    }

    /**
     * 两个集合合并,不去重
     *
     * @param list1
     * @param mapper1
     * @param list2
     * @param mapper2
     * @param <T>
     * @param <T2>
     * @param <R>
     * @return
     */
    public static <T, T2, R> List<R> addAll(List<T> list1, Function<? super T, ? extends R> mapper1, List<T2> list2,
                                            Function<? super T2, ? extends R> mapper2) {
        if (list1 == null && list2 == null && list1.isEmpty() && list2.isEmpty()) {
            return new ArrayList<>();
        }
        List<R> rList1 = new ArrayList<>();
        for (T t : list1) {
            R r = mapper1.apply(t);
            rList1.add(r);
        }
        List<R> rList2 = new ArrayList<>();
        for (T2 t : list2) {
            R r = mapper2.apply(t);
            rList2.add(r);
        }
        if (list1 == null || list1.isEmpty()) {
            return rList2;
        } else if (list2 == null || list2.isEmpty()) {
            return rList1;
        }
        rList1.addAll(rList2);
        return rList1;
    }

    /**
     * 两个集合合并,去重
     *
     * @param list1
     * @param mapper1
     * @param list2
     * @param mapper2
     * @param <T>
     * @param <T2>
     * @param <R>
     * @return
     */
    public static <T, T2, R> List<R> addAllAndDistinct(List<T> list1, Function<? super T, ? extends R> mapper1,
                                                       List<T2> list2,
                                                       Function<? super T2, ? extends R> mapper2) {
        List<R> rList1 = addAll(list1, mapper1, list2, mapper2);
        return rList1.stream().distinct().collect(Collectors.toList());
    }

    /**
     * set集合求差集
     *
     * @param list1
     * @param mapper1
     * @param list2
     * @param mapper2
     * @param <T>
     * @param <T2>
     * @param <R>
     * @return
     */
    public static <T, T2, R> Set<R> sub(List<T> list1, Function<? super T, ? extends R> mapper1,
                                        List<T2> list2, Function<? super T2, ? extends R> mapper2) {
        if (list1 == null && list2 == null && list1.isEmpty() && list2.isEmpty()) {
            return new HashSet<>();
        }
        Set<R> set1 = toSet(list1, mapper1);
        Set<R> set2 = toSet(list2, mapper2);
        set1.removeAll(set2);
        return set1;
    }

    /**
     * set集合求差集
     *
     * @param list1
     * @param list2
     * @param <T>
     * @return
     */
    public static <T> Set<T> sub(List<T> list1, List<T> list2) {
        if (list1 == null && list2 == null && list1.isEmpty() && list2.isEmpty()) {
            return new HashSet<>();
        }
        Set<T> set1 = new HashSet<>(list1);
        Set<T> set2 = new HashSet<>(list2);
        set1.removeAll(set2);
        return set1;
    }

    /**
     * set集合求差集
     *
     * @param set1
     * @param set2
     * @param <T>
     * @return
     */
    public static <T> Set<T> sub(Set<T> set1, Set<T> set2) {
        if (set1 == null && set2 == null && set1.isEmpty() && set2.isEmpty()) {
            return new HashSet<>();
        }
        set1.removeAll(set2);
        return set1;
    }

    /**
     * set集合求交集
     *
     * @param list1
     * @param mapper1
     * @param list2
     * @param mapper2
     * @param <T>
     * @param <T2>
     * @param <R>
     * @return
     */
    public static <T, T2, R> Set<R> intersect(List<T> list1, Function<? super T, ? extends R> mapper1,
                                              List<T2> list2, Function<? super T2, ? extends R> mapper2) {
        if (list1 == null && list2 == null && list1.isEmpty() && list2.isEmpty()) {
            return new HashSet<>();
        }
        Set<R> set1 = toSet(list1, mapper1);
        Set<R> set2 = toSet(list2, mapper2);
        Set<R> result = new HashSet<>();
        result.addAll(set1);
        result.retainAll(set2);
        return result;
    }

    /**
     * set集合求交集
     *
     * @param list1
     * @param list2
     * @param <T>
     * @return
     */
    public static <T> Set<T> intersect(List<T> list1, List<T> list2) {
        if (list1 == null && list2 == null && list1.isEmpty() && list2.isEmpty()) {
            return new HashSet<>();
        }
        Set<T> set1 = new HashSet<>(list1);
        Set<T> set2 = new HashSet<>(list2);
        Set<T> result = new HashSet<>();
        result.addAll(set1);
        result.retainAll(set2);
        return result;
    }

    /**
     * set集合求交集
     *
     * @param set1
     * @param set2
     * @param <T>
     * @return
     */
    public static <T> Set<T> intersect(Set<T> set1, Set<T> set2) {
        if (set1 == null && set2 == null && set1.isEmpty() && set2.isEmpty()) {
            return new HashSet<>();
        }
        Set<T> result = new HashSet<>();
        result.addAll(set1);
        result.retainAll(set2);
        return result;
    }

    /**
     * set集合求并集
     *
     * @param list1
     * @param mapper1
     * @param list2
     * @param mapper2
     * @param <T>
     * @param <T2>
     * @param <R>
     * @return
     */
    public static <T, T2, R> Set<R> aggregate(List<T> list1, Function<? super T, ? extends R> mapper1,
                                              List<T2> list2, Function<? super T2, ? extends R> mapper2) {
        if (list1 == null && list2 == null && list1.isEmpty() && list2.isEmpty()) {
            return new HashSet<>();
        }
        Set<R> set1 = toSet(list1, mapper1);
        Set<R> set2 = toSet(list2, mapper2);
        Set<R> result = new HashSet<>();
        result.addAll(set1);
        result.addAll(set2);
        return result;
    }

    /**
     * set集合求并集
     *
     * @param list1
     * @param list2
     * @param <T>
     * @return
     */
    public static <T> Set<T> aggregate(List<T> list1, List<T> list2) {
        if (list1 == null && list2 == null && list1.isEmpty() && list2.isEmpty()) {
            return new HashSet<>();
        }
        Set<T> set1 = new HashSet<>(list1);
        Set<T> set2 = new HashSet<>(list2);
        Set<T> result = new HashSet<>();
        result.addAll(set1);
        result.addAll(set2);
        return result;
    }

    /**
     * set集合求并集
     *
     * @param set1
     * @param set2
     * @param <T>
     * @return
     */
    public static <T> Set<T> aggregate(Set<T> set1, Set<T> set2) {
        if (set1 == null && set2 == null && set1.isEmpty() && set2.isEmpty()) {
            return new HashSet<>();
        }
        Set<T> result = new HashSet<>();
        result.addAll(set1);
        result.addAll(set2);
        return result;
    }
}
