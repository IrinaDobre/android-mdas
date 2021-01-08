package com.example.mdasproject.models;

import com.example.mdasproject.models.interfaces.PackageType;

public class LoggedUser extends PackageRecipient {

    public LoggedUser(PackageType packageType){
        super(packageType);
    }

    @Override
    public String set() {
        return "Applied " + packageType.apply() + " to cart items, package is for myself";
    }
}
