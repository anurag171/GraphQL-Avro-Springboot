package com.anurag.spring.constant;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum MutationOperator {
    ADD("+"),SUBTRACT("-");

    private final String operator;

    private static final Map<String,MutationOperator> ENUM_MAP;

    static {
        ENUM_MAP = new HashMap<>();
        for(MutationOperator mutationOperator:MutationOperator.values()){
            ENUM_MAP.put(mutationOperator.getOperator(),mutationOperator);
        }
    }

    MutationOperator(String operator) {
        this.operator = operator;
    }

    public static MutationOperator getMutationOperator(String key){
        return ENUM_MAP.get(key);
    }

}
