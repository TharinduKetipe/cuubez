package com.cuubez.example;


public class Account {

    private String accountId;
    private String name;
    private String description;

    public Account(String accountId, String name, String description) {
        this.accountId = accountId;
        this.description = description;
        this.name = name;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
