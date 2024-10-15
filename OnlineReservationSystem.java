import java.util.Scanner;
import java.util.HashMap;

public class OnlineReservationSystem {



        private static Scanner scanner = new Scanner(System.in);
        private static HashMap<String, String> users = new HashMap<>();
        private static HashMap<String, Reservation> reservations = new HashMap<>();

        public static void main(String[] args) {
            users.put("user1", "password1"); // Sample user for login

            if (login()) {
                int choice;
                do {
                    System.out.println("\nWelcome to the Online Reservation System");
                    System.out.println("1. Make a Reservation");
                    System.out.println("2. Cancel a Reservation");
                    System.out.println("3. Exit");
                    System.out.print("Enter your choice: ");
                    choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    switch (choice) {
                        case 1:
                            makeReservation();
                            break;
                        case 2:
                            cancelReservation();
                            break;
                        case 3:
                            System.out.println("Thank you for using the system.");
                            break;
                        default:
                            System.out.println("Invalid choice. Try again.");
                    }
                } while (choice != 3);
            }
        }

        private static boolean login() {
            System.out.println("Please Login to Continue:");
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            if (users.containsKey(username) && users.get(username).equals(password)) {
                System.out.println("Login successful.");
                return true;
            } else {
                System.out.println("Invalid username or password.");
                return false;
            }
        }

        private static void makeReservation() {
            System.out.println("\nReservation Form:");

            System.out.print("Enter Train Number: ");
            String trainNumber = scanner.nextLine();

            System.out.print("Enter Train Name: ");
            String trainName = scanner.nextLine();

            System.out.print("Enter Class Type: ");
            String classType = scanner.nextLine();

            System.out.print("Enter From Location: ");
            String fromLocation = scanner.nextLine();

            System.out.print("Enter To Location: ");
            String toLocation = scanner.nextLine();

            System.out.print("Enter Date of Journey: ");
            String dateOfJourney = scanner.nextLine();

            String pnrNumber = generatePNR();

            Reservation reservation = new Reservation(trainNumber, trainName, classType, fromLocation, toLocation, dateOfJourney, pnrNumber);
            reservations.put(pnrNumber, reservation);

            System.out.println("Reservation made successfully. Your PNR number is: " + pnrNumber);
        }

        private static void cancelReservation() {
            System.out.println("\nCancellation Form:");
            System.out.print("Enter PNR Number: ");
            String pnrNumber = scanner.nextLine();

            if (reservations.containsKey(pnrNumber)) {
                Reservation reservation = reservations.get(pnrNumber);
                System.out.println("Reservation Details for PNR " + pnrNumber);
                System.out.println(reservation);

                System.out.print("Do you want to cancel this reservation? (yes/no): ");
                String choice = scanner.nextLine();

                if (choice.equalsIgnoreCase("yes")) {
                    reservations.remove(pnrNumber);
                    System.out.println("Reservation cancelled successfully.");
                } else {
                    System.out.println("Cancellation aborted.");
                }
            } else {
                System.out.println("No reservation found with the given PNR.");
            }
        }

        private static String generatePNR() {
            return "PNR" + (int) (Math.random() * 10000); // Simple random PNR generation
        }
    }

    class Reservation {
        private String trainNumber;
        private String trainName;
        private String classType;
        private String fromLocation;
        private String toLocation;
        private String dateOfJourney;
        private String pnrNumber;

        public Reservation(String trainNumber, String trainName, String classType, String fromLocation, String toLocation, String dateOfJourney, String pnrNumber) {
            this.trainNumber = trainNumber;
            this.trainName = trainName;
            this.classType = classType;
            this.fromLocation = fromLocation;
            this.toLocation = toLocation;
            this.dateOfJourney = dateOfJourney;
            this.pnrNumber = pnrNumber;
        }

        @Override
        public String toString() {
            return "Train Number: " + trainNumber + "\nTrain Name: " + trainName + "\nClass Type: " + classType +
                    "\nFrom: " + fromLocation + "\nTo: " + toLocation + "\nDate of Journey: " + dateOfJourney +
                    "\nPNR Number: " + pnrNumber;
        }
    }


