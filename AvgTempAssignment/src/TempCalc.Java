public class TempCalc {
    public static double calculateAverage(double[] temps) {
        double sum = 0;
        for (double temp : temps) {
            sum += temp;
        }
        return sum / temps.length;
    }

    public static int countAboveAverage(double[] temps, double average) {
        int count = 0;
        for (double temp : temps) {
            if (temp > average) {
                count++;
            }
        }
        return count;
    }
}