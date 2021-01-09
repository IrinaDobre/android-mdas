package com.example.mdasproject.decorator;

import com.example.mdasproject.models.interfaces.PackageType;

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
