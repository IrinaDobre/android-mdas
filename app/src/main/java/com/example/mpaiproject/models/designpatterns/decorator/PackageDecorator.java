package com.example.mpaiproject.models.designpatterns.decorator;

import com.example.mpaiproject.models.designpatterns.bridge.PackageType;

public abstract class PackageDecorator implements PackageType {

    protected final PackageType pack;

    public PackageDecorator(PackageType pack) {
        this.pack = pack;
    }

    @Override
    public String apply() {
        return pack.apply() + " with extra decorations: ";
    }

    @Override
    public double getPrice() {
        return pack.getPrice();
    }
}
