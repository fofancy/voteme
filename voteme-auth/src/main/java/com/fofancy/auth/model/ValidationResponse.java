package com.fofancy.auth.model;

import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 19.07.2017.
 */
public class ValidationResponse implements Serializable{
    private HttpStatus status;
    private List messages;

    public ValidationResponse() {
        messages = new ArrayList<>();
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public void addMessage(ErrorMessage message) {
        messages.add(message);
    }

    public void removeMessage(ErrorMessage message) {
        messages.remove(message);
    }

    public List getMessages() {
        return messages;
    }

    public void setMessages(List messages) {
        this.messages = messages;
    }
}
