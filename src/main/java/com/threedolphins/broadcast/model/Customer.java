package com.threedolphins.broadcast.model;

import java.io.Serializable;

public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String phoneNumber;
    private volatile MessageStatus status = MessageStatus.PENDING;
    private volatile String lastError;


    public Customer(Long id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public MessageStatus getStatus() {
        return status;
    }

    public void setStatus(MessageStatus status) {
        this.status = status;
    }

    public String getLastError() {
        return lastError;
    }

    public void setLastError(String lastError) {
        this.lastError = lastError;
    }
}