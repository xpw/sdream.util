package com.tx.sdream.util.collect;

import com.tx.sdream.util.ObjectBuilder;
import com.tx.sdream.util.domain.Teacher;
import com.tx.sdream.util.string.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;

@Slf4j
public class StringUtilsTest extends BaseTest {

    @Test
    public void list2String() {
        List<Teacher> teacherList = ObjectBuilder.initTearchers();

        String sexMap = StringUtils.list2String(teacherList, o -> o.getSex(), ",");

        log.info(sexMap);
    }

    @Test
    public void list2StringAndDistinct() {
        List<Teacher> teacherList = ObjectBuilder.initTearchers();

        String sexMap = StringUtils.list2StringAndDistinct(teacherList, o -> o.getSex(), ",");

        log.info(sexMap);
    }

    @Test
    public void testIsEmpty() {
        boolean empty = StringUtils.isEmpty(" ");

        boolean blank = StringUtils.isBlank(" ");

        print(empty);
        print(blank);
    }
}