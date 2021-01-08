package com.example.mdasproject.models;

import com.example.mdasproject.models.interfaces.PackageType;

public abstract class PackageRecipient {
    protected PackageType packageType;

    public PackageRecipient(PackageType packageType) {
        this.packageType = packageType;
    }

    public abstract String set();
}
