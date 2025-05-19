package MovieTheatre.src;

public class Theatre {
    private char[][] seats;

    public Theatre(int rows, int cols) {
        seats = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                seats[i][j] = 'O'; // O = Open, X = Reserved
            }
        }
    }

    public boolean reserveSeat(String seatLabel) {
        int[] pos = parseSeatLabel(seatLabel);
        if (pos == null) return false;

        int row = pos[0];
        int col = pos[1];

        if (seats[row][col] == 'X') return false;

        seats[row][col] = 'X';
        return true;
    }

    public boolean cancelSeat(String seatLabel) {
        int[] pos = parseSeatLabel(seatLabel);
        if (pos == null) return false;

        int row = pos[0];
        int col = pos[1];

        if (seats[row][col] == 'O') return false;

        seats[row][col] = 'O';
        return true;
    }

    public void displaySeats() {
        System.out.print("   ");
        for (int col = 1; col <= seats[0].length; col++) {
            System.out.print(col + " ");
        }
        System.out.println();

        for (int i = 0; i < seats.length; i++) {
            char rowLabel = (char) ('A' + i);
            System.out.print(rowLabel + ": ");
            for (int j = 0; j < seats[i].length; j++) {
                System.out.print(seats[i][j] + " ");
            }
            System.out.println();
        }
    }

    public String suggestSeat() {
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j] == 'O') {
                    char row = (char) ('A' + i);
                    return row + String.valueOf(j + 1);
                }
            }
        }
        return null;
    }

    private int[] parseSeatLabel(String label) {
        if (label.length() < 2) return null;

        char rowChar = Character.toUpperCase(label.charAt(0));
        int row = rowChar - 'A';

        int col;
        try {
            col = Integer.parseInt(label.substring(1)) - 1;
        } catch (NumberFormatException e) {
            return null;
        }

        if (row < 0 || row >= seats.length || col < 0 || col >= seats[0].length) {
            return null;
        }

        return new int[]{row, col};
    }
}
