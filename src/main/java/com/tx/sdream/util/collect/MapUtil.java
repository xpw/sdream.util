package com.tx.sdream.util.collect;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class MapUtil {

    /**
     * 对指定的集合list抽取每个元素对象的两个字段组成key-value结构.如 [{"id":1,"name":"name1"},{"id":2,"name":"name2"}] -> {"1":"name1",
     * "2":"name2"}
     *
     * @param list
     * @param keyMapper
     * @param valueMapper
     * @param <T>
     * @param <K>
     * @param <V>
     * @return
     */
    public static <T, K, V> Map<K, V> toKeyValMap(List<T> list, Function<? super T, ? extends K> keyMapper,
                                                  Function<? super T, ? extends V> valueMapper) {
        if (list == null) {
            return new HashMap<>();
        }
        Map<K, V> map = new HashMap<>();
        for (T t : list) {
            if (t == null) {
                continue;
            }
            K key = keyMapper.apply(t);
            V val = valueMapper.apply(t);
            map.put(key, val);
        }
        return map;
    }

    /**
     * 对指定的集合list及每个元素指定的对象进行求和。[{"id":1,"name":"name1","age":1},{"id":2,"name":"name2","age":2},{"id":3,
     * "name":"name2","age":3}] -> {"name1":1,"name2":5} (外部可以指定对name分组且对同一个name下的age进行求和)
     *
     * @param list
     * @param keyMapper
     * @param valueMapper
     * @param <T>
     * @param <R>
     * @param <V>
     * @return
     */
    public static <T, R, V> Map<R, BigDecimal> sumMap(List<T> list, Function<? super T, ? extends R> keyMapper,
                                                      Function<?
                                                              super T, ?
                                                              extends V> valueMapper) {
        BigDecimal sum = BigDecimal.ZERO;
        if (list == null) {
            return new HashMap<>();
        }
        Map<R, BigDecimal> map = new HashMap<>();
        for (T t : list) {
            if (t == null) continue;
            Object key = keyMapper.apply(t);
            if (key == null) continue;
            Object val = valueMapper.apply(t);
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
            if (map.get(key) == null) {
                map.put((R) key, a);
            } else {
                map.put((R) key, map.get(key).add(a));
            }
        }
        return map;
    }

    /**
     * 按指定的list分组成map
     * @param list
     * @param keyMapper
     * @param valueMapper
     * @param <T>
     * @param <K>
     * @param <V>
     * @return
     */
    public static <T, K, V> Map<K, List<V>> groupByToMap(List<T> list, Function<? super T, ? extends K> keyMapper,
                                                        Function<?
                                                                super T, ?
                                                                extends V> valueMapper) {
        if (list == null) {
            return new HashMap<>();
        }
        Map<K, List<V>> map = new HashMap<>();
        for (T t : list) {
            K key = keyMapper.apply(t);
            if (map.containsKey(key)) {
                map.get(key).add(valueMapper.apply(t));
            } else {
                List<V> valueList = new ArrayList<>();
                valueList.add(valueMapper.apply(t));
                map.put(key, valueList);
            }
        }
        return map;
    }
}
