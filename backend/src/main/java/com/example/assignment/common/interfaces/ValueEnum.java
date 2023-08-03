package com.example.assignment.common.interfaces;

import com.fasterxml.jackson.annotation.JsonValue;

public interface ValueEnum<T> {

    @JsonValue
    T getEnumValue();
}
