package com.tx.sdream.util.collect;

import com.tx.sdream.util.ObjectBuilder;
import com.tx.sdream.util.domain.Teacher;
import com.tx.sdream.util.json.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class MapUtilTest {

    @Test
    public void testToKeyValMap() {
        List<Teacher> teacherList = ObjectBuilder.initTearchers();
        Map<Long, String> keyValMap = MapUtil.toKeyValMap(teacherList, o -> o.getId(), o -> o.getName());
        log.info(JSON.toJson(keyValMap));
    }

    @Test
    public void testSumMap() {
        List<Teacher> teacherList = ObjectBuilder.initTearchers();
        Map<String, BigDecimal> stringBigDecimalMap = MapUtil.sumMap(teacherList, o -> o.getSex(),
                o -> o.getAge());
        log.info(JSON.toJson(stringBigDecimalMap));
    }

    @Test
    public void testInitTearchers() {
        List<Teacher> teacherList = ObjectBuilder.initTearchers();
        Map<String, Long> resMap = new HashMap<>();
        for (Teacher t : teacherList) {
            if (resMap.get(t.getSex()) == null) {
                resMap.put(t.getSex(), t.getAge());
            } else {
                resMap.put(t.getSex(), resMap.get(t.getSex()) + t.getAge());
            }
        }
        log.info(JSON.toJson(resMap));
    }

    @Test
    public void testGroupByToMap() {
        List<Teacher> teacherList = ObjectBuilder.initTearchers();
        Map<String, List<Long>> stringListMap = MapUtil.groupByToMap(teacherList, o -> o.getSex(),
                o -> o.getAge());
        log.info(JSON.toJson(stringListMap));

        Map<String, List<Teacher>> teacherMap = MapUtil.groupByToMap(teacherList, o -> o.getSex(),
                o -> o);
        log.info(JSON.toJson(teacherMap));
    }
}
