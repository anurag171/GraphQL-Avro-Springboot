package com.anurag.spring.constant;

import lombok.Getter;

@Getter
public enum MutationOperator {
    ADD("+"),SUBTRACT("-");

    private final String operator;

    MutationOperator(String operator) {
        this.operator = operator;
    }
}
