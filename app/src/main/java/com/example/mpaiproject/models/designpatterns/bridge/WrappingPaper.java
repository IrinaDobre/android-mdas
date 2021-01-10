package com.example.mpaiproject.models.designpatterns.bridge;

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
