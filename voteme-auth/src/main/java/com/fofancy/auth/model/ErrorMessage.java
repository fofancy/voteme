package com.fofancy.auth.model;

import java.io.Serializable;

/**
 * Created by User on 19.07.2017.
 */
public class ErrorMessage implements Serializable {
    private String fieldName;
    private String message;

    public ErrorMessage() {
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
