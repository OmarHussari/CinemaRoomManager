import java.util.Scanner;
public class CinemaRoomManager {
    static int[] stats = new int[1];

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the number of rows:");
            int numRows = scanner.nextInt();
            System.out.println("Enter the number of seats in each row:");
            int numSeats = scanner.nextInt();
            int totalSeats = numRows * numSeats;
            boolean end = false;
            System.out.println();
            String[][] seatRows = new String[numRows][numSeats];
            for (int i = 0; i < seatRows.length; i++) {
                for (int j = 0; j < seatRows[0].length; j++) {
                    seatRows[i][j] = "S";
                }
            }
            while (!end) {
                System.out.println("\n1. Show the seats");
                System.out.println("2. Buy a ticket");
                System.out.println("3. Statistics");
                System.out.println("0. Exit");
                int option = scanner.nextInt();

                switch (option) {
                    case 1:
                        showSeats(numSeats, seatRows);
                        break;
                    case 2:
                        buyTicket(seatRows, numSeats , numRows, totalSeats);
                        break;
                    case 3:
                        showStats(seatRows, numSeats, numRows, totalSeats);
                        break;
                    case 0:
                        end = true;
                }
            }
        }


        public static void showSeats(int numSeats, String[][] seatRows) {
            System.out.println("Cinema:");
            System.out.print("  ");
            for (int col = 1; col <= numSeats; col++) {
                System.out.print(col + " ");
            }
            System.out.println();
            for (int i = 0; i < seatRows.length; i++) {
                System.out.print(i + 1 + " ");
                for (int j = 0; j < seatRows[0].length; j++) {
                    System.out.print(seatRows[i][j] + " ");
                }
                System.out.println();
            }
        }


        public static void buyTicket(String[][] seatRows, int numOfSeats, int numOfRows, int totalSeats) {
            Scanner scanner = new Scanner(System.in);
            boolean validSeat = false;
            while (!validSeat) {
                System.out.println("Enter a row number:");
                int rowNumber = scanner.nextInt();
                System.out.println("Enter a seat number in that row:");
                int seatInRow = scanner.nextInt();
                if (rowNumber > numOfRows || seatInRow > numOfSeats) {
                    System.out.println("Wrong input!");
                    continue;
                } else if ("B".equals(seatRows[rowNumber - 1][seatInRow - 1])) {
                    System.out.println("\nThat ticket has already been purchased!");
                    continue;
                } else {
                    seatRows[rowNumber - 1][seatInRow - 1] = "B";
                    int ticketPrice;
                    int frontRows = numOfRows / 2;
                    if (totalSeats > 60) {
                        if (rowNumber <= frontRows) {
                            ticketPrice = 10;
                        } else {
                            ticketPrice = 8;
                        }
                    } else {
                        ticketPrice = 10;
                    }
                    stats[0] += ticketPrice;
                    System.out.println("Ticket price: " + "$" + ticketPrice);
                    System.out.println();
                    validSeat = true;
                    }
                }
            }


        public static void showStats(String[][] seatRows, int numSeats, int numRows, int totalSeats) {
            int reservedSeats = 0;
            for (int i = 0; i < seatRows.length; i++) {
                for (int j = 0; j < seatRows[0].length; j++) {
                    if ("B".equals(seatRows[i][j])) {
                        reservedSeats++;
                    }
                }
            }
            System.out.printf("\nNumber of purchased tickets: %d", reservedSeats);
            float percentage = (float) reservedSeats / totalSeats * 100;
            System.out.printf("\nPercentage: %.2f %s", percentage, "%");
            System.out.printf("\nCurrent income: $%d", stats[0]);
            int totalIncome = 0;
            int frontRows = numRows / 2;
            if (totalSeats < 60) {
                totalIncome = totalSeats * 10;
            } else {
                totalIncome = frontRows * numSeats * 10 + (numRows - frontRows) * numSeats * 8;
            }
            System.out.printf("\nTotal income: $%d", totalIncome);

        }
}

