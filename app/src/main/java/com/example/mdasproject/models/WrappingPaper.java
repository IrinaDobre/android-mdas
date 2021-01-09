package com.example.mdasproject.models;

import com.example.mdasproject.models.interfaces.PackageType;

public class WrappingPaper implements PackageType {
    @Override
    public String apply() {
        return "wrapping paper";
    }

    @Override
    public double getPrice() {
        return 10;
    }

}
