package com.example.cars.model;

public class Result {

    private Integer status;
    private String descriptions;
    private Object value;

    public Result(Integer status, String descriptions, Object value) {
        this.status = status;
        this.descriptions = descriptions;
        this.value = value;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}

