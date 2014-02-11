package com.cuubez.example;


import com.cuubez.core.annotation.HttpMethod;
import com.cuubez.core.annotation.RestService;
import com.cuubez.core.context.MediaType;
import com.cuubez.core.security.annotation.EncryptedRestService;

public class AccountService {


    @RestService(path = "account", mediaType = MediaType.XML, httpMethod = HttpMethod.GET, isSecure = false, roleIds = "", userIds = "")
    public Account getAccountDetail() {

        Account account = new Account("accountId-001", "test-accountName", "test-description");
        return account;

    }
}
