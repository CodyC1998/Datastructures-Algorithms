package com.keyin;

import java.util.LinkedList;
import java.util.Queue;

public class AnimalShelter {
    private final Queue<Dog> dogs = new LinkedList<>();
    private final Queue<Cat> cats = new LinkedList<>();
    private int order = 0;

    public void enqueue(Animal animal) {
        animal.setOrder(order++);
        if (animal instanceof Dog) {
            dogs.add((Dog) animal);
        } else if (animal instanceof Cat) {
            cats.add((Cat) animal);
        }
    }

    public Animal dequeueAny() {
        if (dogs.isEmpty()) return dequeueCat();
        if (cats.isEmpty()) return dequeueDog();

        Dog dog = dogs.peek();
        Cat cat = cats.peek();

        if (dog.isOlderThan(cat)) {
            return dequeueDog();
        } else {
            return dequeueCat();
        }
    }

    public Dog dequeueDog() {
        return dogs.poll();
    }

    public Cat dequeueCat() {
        return cats.poll();
    }
}
