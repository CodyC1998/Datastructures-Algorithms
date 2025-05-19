package MovieTheatre.src;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Theatre theatre = new Theatre(5, 5); // A–E and 1–5

        while (true) {
            System.out.println("\n1. Reserve Seat");
            System.out.println("2. Cancel Seat");
            System.out.println("3. Show Seating Chart");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (choice == 1) {
                System.out.print("Enter seat to reserve (e.g. B3): ");
                String seat = scanner.nextLine();

                if (theatre.reserveSeat(seat)) {
                    System.out.println("Seat reserved.");
                } else {
                    System.out.println("Seat is taken or invalid.");
                    String suggestion = theatre.suggestSeat();
                    if (suggestion != null) {
                        System.out.println("Try seat: " + suggestion);
                    } else {
                        System.out.println("No seats available.");
                    }
                }

            } else if (choice == 2) {
                System.out.print("Enter seat to cancel (e.g. A1): ");
                String seat = scanner.nextLine();

                if (theatre.cancelSeat(seat)) {
                    System.out.println("Reservation cancelled.");
                } else {
                    System.out.println("That seat wasn't reserved or is invalid.");
                }

            } else if (choice == 3) {
                theatre.displaySeats();

            } else if (choice == 4) {
                System.out.println("Goodbye!");
                break;

            } else {
                System.out.println("Invalid option.");
            }
        }

        scanner.close();
    }
}

