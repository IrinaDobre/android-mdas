package com.example.mdasproject.models;

import com.example.mdasproject.models.interfaces.PackageType;

public class OtherRecipient extends PackageRecipient {

    public OtherRecipient(PackageType packageType) {
        super(packageType);
    }

    @Override
    public String set() {
        return "Applied " + packageType.apply() + " to cart items, package is a gift for somebody else";
    }
}
