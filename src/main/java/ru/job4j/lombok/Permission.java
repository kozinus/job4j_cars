package ru.job4j.lombok;

import lombok.*;

import java.util.List;

@Builder(builderMethodName = "of")
@ToString
@Data
public class Permission {
    private int id;
    private String name;
    @Singular("rule")
    private List<String> rules;
}