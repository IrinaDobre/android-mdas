package com.example.mdasproject.decorator;

import com.example.mdasproject.models.interfaces.PackageType;

public class GreetingCard extends PackageDecorator{

    public GreetingCard(PackageType pack) {
        super(pack);
    }

    @Override
    public String apply() {
        return super.apply() + " greeting card";
    }


    @Override
    public double getPrice() {
        return super.getPrice() + 6;
    }
}
