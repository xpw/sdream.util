package com.tx.sdream.util;

import com.tx.sdream.util.domain.Teacher;
import com.tx.sdream.util.json.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
public class ObjectBuilder {

    public static List<Teacher> initTearchers() {
        List<Teacher> teacherList = new ArrayList<>();
        Random random = new Random();
        int randomVal = random.nextInt(10000);
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                teacherList.add(new Teacher(Long.valueOf(i), ("红子" + i) + "_" + randomVal, "女", i + 20L));
            } else {
                teacherList.add(new Teacher(Long.valueOf(i), ("亮子" + i) + "_" + randomVal, "男", i + 22L));
            }
        }
        log.info(JSON.toJson(teacherList));
        return teacherList;
    }
}
