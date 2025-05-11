import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("How many temperatures would you like to enter? ");
        int numDays = scanner.nextInt();
        double[] temperatures = new double[numDays];

        for (int i = 0; i < numDays; i++) {
            System.out.print("Day " + (i + 1) + "'s temperature: ");
            temperatures[i] = scanner.nextDouble();
        }

        double average = TempCalc.calculateAverage(temperatures);
        int countAboveAverage = TempCalc.countAboveAverage(temperatures, average);

        System.out.printf("Average temperature: %.2f\n", average);
        System.out.println(countAboveAverage + " day(s) above average");

        scanner.close();
    }
}
