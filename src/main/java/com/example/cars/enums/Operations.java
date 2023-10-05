package com.example.cars.enums;

import java.util.Optional;

public enum Operations {
    moreThan,
    lessThan,
    equals;

    public static Optional<Operations> from(String operation) {
        for (Operations operations : values()) {
            if (operations.name().equalsIgnoreCase(operation)) {
                return Optional.of(operations);
            }
        }
        return Optional.empty();
    }
}
