package com.cuubez.example;


import com.cuubez.core.security.annotation.EncryptedField;

public class Account {

    private String accountId;
    @EncryptedField(name = "name")
    private String name;
    @EncryptedField(name = "description")
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
