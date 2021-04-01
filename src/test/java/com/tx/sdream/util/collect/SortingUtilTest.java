package com.tx.sdream.util.collect;

import com.tx.sdream.util.ObjectBuilder;
import com.tx.sdream.util.domain.Teacher;
import com.tx.sdream.util.json.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;

@Slf4j
public class SortingUtilTest {

    @Test
    public void testAscSort() {

        List<Teacher> teacherList = ObjectBuilder.initTearchers();
        SortingUtil.sort(teacherList, new SortingField<Teacher, Long>() {
            @Override
            public Long apply(Teacher input) {
                return input.getAge();
            }
        });

        log.info(JSON.toJson(teacherList));

        ListUtil.sortDescNullFirst(teacherList, o -> o.getAge());
        log.info(JSON.toJson(teacherList));
    }
}
