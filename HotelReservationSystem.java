import java.util.*;

class Room {
    private int roomNumber;
    private boolean isBooked;
    private String guestName;

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
        this.isBooked = false;
        this.guestName = null;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public String getGuestName() {
        return guestName;
    }

    public void bookRoom(String guestName) {
        this.isBooked = true;
        this.guestName = guestName;
    }

    public void freeRoom() {
        this.isBooked = false;
        this.guestName = null;
    }

    @Override
    public String toString() {
        return "Room " + roomNumber + " - " + (isBooked ? "Booked by " + guestName : "Available");
    }
}

class Hotel {
    private final List<Room> rooms;
    private final Map<Integer, Room> bookedRooms;
    
    public Hotel(int numRooms) {
        rooms = new ArrayList<>();
        bookedRooms = new HashMap<>();
        for (int i = 1; i <= numRooms; i++) {
            rooms.add(new Room(i));
        }
    }

    public synchronized boolean bookRoom(int roomNumber, String guestName) {
        if (roomNumber < 1 || roomNumber > rooms.size()) {
            System.out.println("Invalid room number.");
            return false;
        }

        Room room = rooms.get(roomNumber - 1);
        if (!room.isBooked()) {
            room.bookRoom(guestName);
            bookedRooms.put(roomNumber, room);
            System.out.println("Room " + roomNumber + " booked successfully for " + guestName);
            return true;
        } else {
            System.out.println("Room " + roomNumber + " is already booked.");
            return false;
        }
    }

    public synchronized boolean cancelBooking(int roomNumber) {
        if (!bookedRooms.containsKey(roomNumber)) {
            System.out.println("No booking found for Room " + roomNumber);
            return false;
        }
        
        Room room = bookedRooms.get(roomNumber);
        room.freeRoom();
        bookedRooms.remove(roomNumber);
        System.out.println("Booking for Room " + roomNumber + " has been cancelled.");
        return true;
    }

    public void displayRooms() {
        for (Room room : rooms) {
            System.out.println(room);
        }
    }
}

public class HotelReservationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hotel hotel = new Hotel(10);  // Hotel with 10 rooms

        while (true) {
            System.out.println("\nHotel Reservation System");
            System.out.println("1. Book a Room");
            System.out.println("2. Cancel Booking");
            System.out.println("3. View Rooms");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter Room Number to Book: ");
                    int bookRoomNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Guest Name: ");
                    String guestName = scanner.nextLine();
                    hotel.bookRoom(bookRoomNumber, guestName);
                    break;
                case 2:
                    System.out.print("Enter Room Number to Cancel: ");
                    int cancelRoomNumber = scanner.nextInt();
                    hotel.cancelBooking(cancelRoomNumber);
                    break;
                case 3:
                    hotel.displayRooms();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
