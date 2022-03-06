package com.bruno.activemq.entities;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person {

    private int id;
    private String name;
    private int age;

}