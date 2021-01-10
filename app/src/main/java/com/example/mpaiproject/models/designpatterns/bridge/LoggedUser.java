package com.example.mpaiproject.models.designpatterns.bridge;

public class LoggedUser extends PackageRecipient {

    public LoggedUser(PackageType packageType){
        super(packageType);
    }

    @Override
    public String set() {
        return "Applied " + packageType.apply() + " to cart items, package is for myself";
    }
}
