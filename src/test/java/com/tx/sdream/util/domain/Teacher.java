package com.tx.sdream.util.domain;

import lombok.Data;

@Data
public class Teacher {

    private Long id;

    private String name;

    private String sex;

    private Long age;

    public Teacher() {

    }

    public Teacher(Long id, String name, String sex, Long age) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
    }
}
