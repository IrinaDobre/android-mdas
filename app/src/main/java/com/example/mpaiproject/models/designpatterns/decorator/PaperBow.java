package com.example.mpaiproject.models.designpatterns.decorator;

import com.example.mpaiproject.models.designpatterns.bridge.PackageType;

public class PaperBow extends PackageDecorator{

    public PaperBow(PackageType pack) {
        super(pack);
    }

    @Override
    public String apply() {
        return super.apply() + " paper bow";
    }

    @Override
    public double getPrice() {
        return super.getPrice() + 5;
    }
}
