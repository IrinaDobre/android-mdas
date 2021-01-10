package com.example.mpaiproject.models.designpatterns.decorator;

import com.example.mpaiproject.models.designpatterns.bridge.PackageType;

public class GiftTag extends PackageDecorator{

    public GiftTag(PackageType pack) {
        super(pack);
    }

    @Override
    public String apply() {
        return super.apply() + " gift tag";
    }


    @Override
    public double getPrice() {
        return super.getPrice() + 3;
    }
}
