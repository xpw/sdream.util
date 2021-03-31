package com.tx.sdream.util.string;

import com.tx.sdream.util.collect.ListUtil;

import java.util.List;
import java.util.Set;
import java.util.function.Function;

public class StringUtils {

    /**
     * 将集合按分隔符转成一个字符串。刑如 ["b","a","t"] => "b,a,t"
     *
     * @param list
     * @param keyMapper
     * @param delimiter 分隔符
     * @param <T>
     * @param <K>
     * @return
     */
    public static <T, K> String list2String(List<T> list, Function<? super T, ? extends K> keyMapper,
                                            String delimiter) {
        if (list == null) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        for (T t : list) {
            if (t == null) {
                continue;
            }
            String key = (String) keyMapper.apply(t);
            sb.append(key).append(delimiter);
        }
        if (sb.length() > 0) {
            return sb.substring(0, sb.length() - 1);
        }
        return sb.toString();
    }

    /**
     * 将集合按分隔符转成一个字符串,且去重。刑如 ["b","b","a","t"] => "b,a,t"
     *
     * @param list
     * @param keyMapper
     * @param delimiter 分隔符
     * @param <T>
     * @param <K>
     * @return
     */
    public static <T, K> String list2StringAndDistinct(List<T> list, Function<? super T, ? extends K> keyMapper,
                                                       String delimiter) {
        if (list == null) {
            return null;
        }
        Set<? extends K> set = ListUtil.toSet(list, keyMapper);
        StringBuffer sb = new StringBuffer();
        for (K t : set) {
            if (t == null) {
                continue;
            }
            String key = (String) t;
            sb.append(key).append(delimiter);
        }
        if (sb.length() > 0) {
            return sb.substring(0, sb.length() - 1);
        }
        return sb.toString();
    }

    /**
     * <p>Checks if a String is empty ("") or null.</p>
     *
     * <pre>
     * StringUtils.isEmpty(null)      = true
     * StringUtils.isEmpty("")        = true
     * StringUtils.isEmpty(" ")       = false
     * StringUtils.isEmpty("bob")     = false
     * StringUtils.isEmpty("  bob  ") = false
     * </pre>
     *
     * <p>NOTE: This method changed in Lang version 2.0.
     * It no longer trims the String.
     * That functionality is available in isBlank().</p>
     *
     * @param str the String to check, may be null
     * @return <code>true</code> if the String is empty or null
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    /**
     * <p>Checks if a String is not empty ("") and not null.</p>
     *
     * <pre>
     * StringUtils.isNotEmpty(null)      = false
     * StringUtils.isNotEmpty("")        = false
     * StringUtils.isNotEmpty(" ")       = true
     * StringUtils.isNotEmpty("bob")     = true
     * StringUtils.isNotEmpty("  bob  ") = true
     * </pre>
     *
     * @param str the String to check, may be null
     * @return <code>true</code> if the String is not empty and not null
     */
    public static boolean isNotEmpty(String str) {
        return !StringUtils.isEmpty(str);
    }

    /**
     * <p>Checks if a String is whitespace, empty ("") or null.</p>
     *
     * <pre>
     * StringUtils.isBlank(null)      = true
     * StringUtils.isBlank("")        = true
     * StringUtils.isBlank(" ")       = true
     * StringUtils.isBlank("bob")     = false
     * StringUtils.isBlank("  bob  ") = false
     * </pre>
     *
     * @param str the String to check, may be null
     * @return <code>true</code> if the String is null, empty or whitespace
     * @since 2.0
     */
    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>Checks if a String is not empty (""), not null and not whitespace only.</p>
     *
     * <pre>
     * StringUtils.isNotBlank(null)      = false
     * StringUtils.isNotBlank("")        = false
     * StringUtils.isNotBlank(" ")       = false
     * StringUtils.isNotBlank("bob")     = true
     * StringUtils.isNotBlank("  bob  ") = true
     * </pre>
     *
     * @param str the String to check, may be null
     * @return <code>true</code> if the String is
     * not empty and not null and not whitespace
     * @since 2.0
     */
    public static boolean isNotBlank(String str) {
        return !StringUtils.isBlank(str);
    }
}
