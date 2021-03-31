package com.tx.sdream.util.collect;

import com.tx.sdream.util.ObjectBuilder;
import com.tx.sdream.util.domain.Teacher;
import com.tx.sdream.util.json.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
public class ListUtilTest {

    @Test
    public void testToMapList() {
        List<Teacher> teacherList = ObjectBuilder.initTearchers();

        Map<String, List<Teacher>> sexMap = ListUtil.toMapList(teacherList, o -> o.getSex(), o -> o);

        log.info(JSON.toJson(sexMap));
    }

    @Test
    public void testToSet() {
        List<Teacher> teacherList = ObjectBuilder.initTearchers();

        Set<Object> sexMap = ListUtil.toSet(teacherList, o -> o.getSex());

        log.info(JSON.toJson(sexMap));
    }

    @Test
    public void testString2List() {

        List<String> strings = ListUtil.string2List("1,2,3,3", ",");

        log.info(JSON.toJson(strings));
    }

    @Test
    public void testString2ListAndDistict() {

        Set<String> strings = ListUtil.string2ListAndDistict("1,2,3,3", ",");

        log.info(JSON.toJson(strings));
    }

    @Test
    public void testToList() {

        List<Teacher> teacherList = ObjectBuilder.initTearchers();

        List<? extends String> strings = ListUtil.toList(teacherList, Teacher::getSex);

        log.info(JSON.toJson(strings));
    }

    @Test
    public void testToList2() {

        String[] arrays = new String[]{"1", "2"};

        List<String> list = ListUtil.toList(arrays);

        log.info(JSON.toJson(list));

        // java.lang.UnsupportedOperationException
        list.add("3");
        log.info(JSON.toJson(list));
    }

    @Test
    public void testToListAndDistinct() {

        List<Teacher> teacherList = ObjectBuilder.initTearchers();

        List<? extends String> strings = ListUtil.toListAndDistinct(teacherList, Teacher::getSex);

        log.info(JSON.toJson(strings));
    }

    @Test
    public void testFilter() {

        List<Teacher> teacherList = ObjectBuilder.initTearchers();

        List<Teacher> filter = ListUtil.filter(teacherList, o -> o.getSex().equals("女"));

        log.info(JSON.toJson(filter));
    }

    @Test
    public void testAddAll() {

        ArrayList<Object> objects = new ArrayList<>();
        log.info(objects.isEmpty() + "");

        List<Teacher> teacherList = ObjectBuilder.initTearchers();
        List<Teacher> teacherList2 = ObjectBuilder.initTearchers();
        List<String> list = ListUtil.addAll(teacherList, o -> o.getSex(), teacherList2, o -> o.getSex());
        log.info(JSON.toJson(list));

        List<Teacher> teacherList3 = new ArrayList<>();
        list = ListUtil.addAll(teacherList, o -> o.getSex(), teacherList3, o -> o.getSex());
        log.info(JSON.toJson(list));

        list = ListUtil.addAll(teacherList3, o -> o.getSex(), teacherList, o -> o.getSex());
        log.info(JSON.toJson(list));
    }

    @Test
    public void testAddAllAndDistinct() {
        // copy后的新list依旧指向老list
//        List<Teacher> sources = ObjectBuilder.initTearchers();
//        List<Teacher> dest = sources.stream().collect(Collectors.toList());
//        dest.forEach(o->o.setName("haha"));
//        log.info(JSON.toJson(dest));
        List<Teacher> teacherList = ObjectBuilder.initTearchers();
        List<Teacher> newList = ListUtil.copy(teacherList);

        List<String> list = ListUtil.addAllAndDistinct(teacherList, o -> o.getSex(), newList, o -> o.getSex());
        log.info(JSON.toJson(list));
    }

    @Test
    public void testSub() {
        Teacher t1 = new Teacher(1L, "小红", "女", 10L);
        Teacher t2 = new Teacher(2L, "小黄", "女", 11L);
        Teacher t3 = new Teacher(3L, "小黄", "男", 12L);
        Teacher t4 = new Teacher(2L, "小明", "男", 13L);
        List<Teacher> list1 = new ArrayList<>();
        list1.add(t1);
        list1.add(t2);
        List<Teacher> list2 = new ArrayList<>();
        list2.add(t3);
        list2.add(t4);

        Set<String> set = ListUtil.sub(list1, o -> o.getName(), list2, o -> o.getName());
        log.info(JSON.toJson(set));

        List<String> stringList1 = Arrays.asList("1", "2");
        List<String> stringList2 = Arrays.asList("2", "3");
        Set<String> setR = ListUtil.sub(stringList1, stringList2);
        log.info(JSON.toJson(setR));

        Set<String> set1 = new HashSet<>(stringList1);
        Set<String> set2 = new HashSet<>(stringList2);
        Set<String> setR2 = ListUtil.sub(set2, set1);
        log.info(JSON.toJson(setR2));
    }

    @Test
    public void testIntersect() {
        Teacher t1 = new Teacher(1L, "小红", "女", 10L);
        Teacher t2 = new Teacher(2L, "小黄", "女", 11L);
        Teacher t3 = new Teacher(3L, "小黄", "男", 12L);
        Teacher t4 = new Teacher(2L, "小明", "男", 13L);
        List<Teacher> list1 = new ArrayList<>();
        list1.add(t1);
        list1.add(t2);
        List<Teacher> list2 = new ArrayList<>();
        list2.add(t3);
        list2.add(t4);

        Set<? extends String> set = ListUtil.intersect(list1, o -> o.getName(), list2, o -> o.getName());
        log.info(JSON.toJson(set));

        List<String> stringList1 = Arrays.asList("1", "2");
        List<String> stringList2 = Arrays.asList("2", "3");
        Set<String> setR = ListUtil.intersect(stringList1, stringList2);
        log.info(JSON.toJson(setR));

        Set<String> set1 = new HashSet<>(stringList1);
        Set<String> set2 = new HashSet<>(stringList2);
        Set<String> setR2 = ListUtil.intersect(set2, set1);
        log.info(JSON.toJson(setR2));
    }

    @Test
    public void testAggregate() {
        Teacher t1 = new Teacher(1L, "小红", "女", 10L);
        Teacher t2 = new Teacher(2L, "小黄", "女", 11L);
        Teacher t3 = new Teacher(3L, "小黄", "男", 12L);
        Teacher t4 = new Teacher(2L, "小明", "男", 13L);
        List<Teacher> list1 = new ArrayList<>();
        list1.add(t1);
        list1.add(t2);
        List<Teacher> list2 = new ArrayList<>();
        list2.add(t3);
        list2.add(t4);

        Set<? extends String> set = ListUtil.aggregate(list1, o -> o.getName(), list2, o -> o.getName());
        log.info(JSON.toJson(set));

        List<String> stringList1 = Arrays.asList("1", "2");
        List<String> stringList2 = Arrays.asList("2", "3");
        Set<String> setR = ListUtil.aggregate(stringList1, stringList2);
        log.info(JSON.toJson(setR));

        Set<String> set1 = new HashSet<>(stringList1);
        Set<String> set2 = new HashSet<>(stringList2);
        Set<String> setR2 = ListUtil.aggregate(set2, set1);
        log.info(JSON.toJson(setR2));
    }
}
