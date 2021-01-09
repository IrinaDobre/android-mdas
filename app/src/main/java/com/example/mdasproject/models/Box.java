package com.example.mdasproject.models;

import com.example.mdasproject.models.interfaces.PackageType;

public class Box implements PackageType {
    @Override
    public String apply() {
        return "box";
    }

    @Override
    public double getPrice() {
        return 15;
    }


}
