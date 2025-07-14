package com.keyin;

public abstract class Animal {
    private final String name;
    private int order;

    public Animal(String name) {
        this.name = name;
    }

    public void setOrder(int order) { this.order = order; }
    public int getOrder() { return order; }

    public boolean isOlderThan(Animal other) {
        return this.order < other.getOrder();
    }

    public String getName() { return name; }
}