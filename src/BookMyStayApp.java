import java.util.*;

class Room {
    private String type;
    private double price;

    public Room(String type, double price) {
        this.type = type;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }
}

class RoomInventory {

    private Map<String, Integer> roomAvailability;

    public RoomInventory() {
        roomAvailability = new HashMap<>();
        roomAvailability.put("Single", 5);
        roomAvailability.put("Double", 3);
        roomAvailability.put("Suite", 2);
    }

    public Map<String, Integer> getRoomAvailability() {
        return roomAvailability;
    }
}

class RoomSearchService {

    public void searchAvailableRooms(
            RoomInventory inventory,
            Room singleRoom,
            Room doubleRoom,
            Room suiteRoom) {

        Map<String, Integer> availability = inventory.getRoomAvailability();

        if (availability.get("Single") > 0) {
            System.out.println(singleRoom.getType() + " Room Available | Price: " + singleRoom.getPrice());
        }

        if (availability.get("Double") > 0) {
            System.out.println(doubleRoom.getType() + " Room Available | Price: " + doubleRoom.getPrice());
        }

        if (availability.get("Suite") > 0) {
            System.out.println(suiteRoom.getType() + " Room Available | Price: " + suiteRoom.getPrice());
        }
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        Room singleRoom = new Room("Single", 2000);
        Room doubleRoom = new Room("Double", 3500);
        Room suiteRoom = new Room("Suite", 5000);

        RoomSearchService service = new RoomSearchService();

        service.searchAvailableRooms(inventory, singleRoom, doubleRoom, suiteRoom);
    }
}