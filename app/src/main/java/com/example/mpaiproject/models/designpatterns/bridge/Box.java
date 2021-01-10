package com.example.mpaiproject.models.designpatterns.bridge;

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
