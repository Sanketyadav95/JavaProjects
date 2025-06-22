import java.util.*;

class Booking { 
    String passengerName; 
    String source;   
    String destination;     
    int tickets;     
    int age;     
    String mobilenumber;  
    String gender; 
    String email; 

    public Booking(String passengerName, String source, String destination, int tickets, int age, String mobilenumber, String gender, String email) {        
        this.passengerName = passengerName; 
        this.source = source;     
        this.destination = destination;
        this.tickets = tickets;   
        this.age = age;     
        this.mobilenumber = mobilenumber;   
        this.gender = gender;       
        this.email = email; 
    } 
} 

class Train {     
    private int trainNumber;     
    private int totalSeats;     
    private int availableSeats;     
    private List<Booking> bookings;     

    public Train(int trainNumber, int totalSeats) {         
        this.trainNumber = trainNumber;         
        this.totalSeats = totalSeats;         
        this.availableSeats = totalSeats;         
        this.bookings = new ArrayList<>(); 
    } 

    public void bookTicket(String passengerName, String source, String destination, int tickets, int age, String mobilenumber, String gender, String email) { 
        if (tickets <= availableSeats) {             
            availableSeats -= tickets; 
            bookings.add(new Booking(passengerName, source, destination, tickets, age, mobilenumber, gender, email)); 
            System.out.println("Booking confirmed for " + passengerName); 
        } else { 
            System.out.println("Sorry, not enough seats available."); 
        } 
    } 

    public void cancelTicket(String passengerName) { 
        for (int i = 0; i < bookings.size(); i++) { 
            if (bookings.get(i).passengerName.equals(passengerName)) {                 
                availableSeats += bookings.get(i).tickets;                 
                bookings.remove(i); 
                System.out.println("Cancellation successful for " + passengerName); 
                return; 
            } 
        } 
        System.out.println("No booking found for " + passengerName); 
    } 

    public void displayAvailableSeats() { 
        System.out.println("Available seats: " + availableSeats); 
    } 

    public void displayBookings() { 
        System.out.println("Current bookings: "); 
        for (Booking booking : bookings) { 
            System.out.println("Passenger: " + booking.passengerName + ", Mobile Number: " + booking.mobilenumber + ", Age: " + booking.age + ", Gender: " + booking.gender + ", Email: " 
            + booking.email + ", Source: " + booking.source + ", Destination: " + booking.destination + ", Tickets: " + booking.tickets); 
        } 
    } 

    public void displayTrainDetails() { 
        System.out.println("Train Number: " + trainNumber); 
        System.out.println("Total Seats: " + totalSeats); 
        System.out.println("Available Seats: " + availableSeats); 
    } 
} 

public class RailwayReservationSystem {     
    public static void main(String[] args) { 
        Scanner scanner = new Scanner(System.in); 
        Train train1 = new Train(12345, 100);   

        while (true) { 
            System.out.println(" * * * * * * * * * *    Welcome to ticket reservation  * * * * * * * * * * "); 
            System.out.println("1. Reservation"); 
            System.out.println("2. Display Available Seats"); 
            System.out.println("3. Cancel Ticket"); 
            System.out.println("4. Display Bookings"); 
            System.out.println("5. Train Details"); 
            System.out.println("6. Exit"); 
            System.out.print("Enter your choice: ");     

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the leftover newline

            switch (choice) {
                case 1:
                    try {
                        System.out.print("Enter name: ");
                        String passengerName = scanner.nextLine();
                        if (!isValidName(passengerName)) {
                            throw new IllegalArgumentException("Invalid name format. Please enter a valid name.");
                        }

                        System.out.print("Enter Mobile number: ");
                        String mobilenumber = scanner.nextLine();

                        System.out.print("Enter Age: ");
                        int age = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        if (age <= 0 || age > 150) {
                            throw new IllegalArgumentException("Invalid age. Please enter a valid age.");
                        }

                        System.out.print("Enter Gender: ");
                        String gender = scanner.nextLine();

                        System.out.print("Enter Email: ");
                        String email = scanner.nextLine();
                        if (!isValidEmail(email)) {
                            throw new IllegalArgumentException("Invalid email format. Please enter a valid email.");
                        }

                        System.out.print("Enter source: ");
                        String source = scanner.nextLine();

                        System.out.print("Enter destination: ");
                        String destination = scanner.nextLine();

                        System.out.print("Enter number of tickets: ");
                        int tickets = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        train1.bookTicket(passengerName, source, destination, tickets, age, mobilenumber, gender, email);
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid value.");
                        scanner.nextLine(); // Clear the input buffer
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2: 
                    train1.displayAvailableSeats();                     
                    break;                 

                case 3:
                    System.out.print("Enter passenger name: ");
                    String name = scanner.nextLine();
                    train1.cancelTicket(name);
                    break;                 

                case 4: 
                    train1.displayBookings();                     
                    break;                 

                case 5:                     
                    train1.displayTrainDetails();                     
                    break; 

                case 6: 
                    System.out.println("  * * * * * * * * * *   Thank you for using our system! * * * * * * * * * * "); 
                    scanner.close();                     
                    return;                 

                default: 
                    System.out.println("Invalid choice. Please enter a valid option."); 
            } 
        } 
    } 

    // Validate Name Format     
    private static boolean isValidName(String name) {         
        return name.matches("[a-zA-Z]+"); 
    } 

    // Validate Email Format     
    private static boolean isValidEmail(String email) {         
        return email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}"); 
    } 
}