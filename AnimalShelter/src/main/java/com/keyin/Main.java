package com.keyin;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AnimalShelter shelter = new AnimalShelter();

        shelter.enqueue(new Dog("Rex"));
        shelter.enqueue(new Cat("Whiskers"));
        shelter.enqueue(new Dog("Buddy"));
        shelter.enqueue(new Cat("Mittens"));
        shelter.enqueue(new Dog("Doug"));
        shelter.enqueue(new Cat("Kitty"));

        while (true) {
            System.out.println("\nWelcome to the Animal Shelter!");
            System.out.println("What type of animal would you like to adopt?");
            System.out.println("1. Any");
            System.out.println("2. Dog");
            System.out.println("3. Cat");
            System.out.println("4. Exit");

            System.out.print("Enter your choice (1-4): ");
            String input = scanner.nextLine();

            Animal adoptedAnimal = null;

            switch (input) {
                case "1":
                    adoptedAnimal = shelter.dequeueAny();
                    break;
                case "2":
                    adoptedAnimal = shelter.dequeueDog();
                    break;
                case "3":
                    adoptedAnimal = shelter.dequeueCat();
                    break;
                case "4":
                    System.out.println("Thanks for visiting! Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
                    continue;
            }

            if (adoptedAnimal != null) {
                System.out.println("You adopted: " + adoptedAnimal.getClass().getSimpleName() + " - " + adoptedAnimal.getName());
            } else {
                System.out.println("Sorry, no animals of that type are currently available.");
            }
        }
    }
}
