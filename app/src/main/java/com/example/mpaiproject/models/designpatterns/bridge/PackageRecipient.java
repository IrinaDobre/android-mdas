package com.example.mpaiproject.models.designpatterns.bridge;

public abstract class PackageRecipient {
    protected PackageType packageType;

    public PackageRecipient(PackageType packageType) {
        this.packageType = packageType;
    }

    public abstract String set();
}
