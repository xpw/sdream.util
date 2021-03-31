package com.tx.sdream.util.collect;

import com.tx.sdream.util.ObjectBuilder;
import com.tx.sdream.util.domain.Teacher;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class CollectUtilTest extends BaseTest {

    @Test
    public void testIsEmpty() {

        Map<String,String> map = null;
        boolean empty = CollectUtil.isEmpty(map);
        print(empty);

        map = new HashMap<>();
        empty = CollectUtil.isEmpty(map);
        print(empty);

        List<Teacher> teacherList = ObjectBuilder.initTearchers();
        Map<String, List<Teacher>> sexMap = ListUtil.toMapList(teacherList, o -> o.getSex(), o -> o);
        empty = CollectUtil.isEmpty(sexMap);
        print(empty);

        empty = CollectUtil.isNotEmpty(sexMap);
        print(empty);

        empty = CollectUtil.isNotEmpty(teacherList);
        print(empty);
    }
}
