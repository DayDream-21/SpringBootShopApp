package com.slavamashkov.springboot_test.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Cat {
    private Long id;
    private String name;
    private String color;
}
