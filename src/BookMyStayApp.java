import java.util.*;

class RoomInventory {

    private Map<String, Integer> roomAvailability;

    public RoomInventory() {
        roomAvailability = new HashMap<>();
        initializeInventory();
    }

    private void initializeInventory() {
        roomAvailability.put("Single", 10);
        roomAvailability.put("Double", 8);
        roomAvailability.put("Suite", 5);
        roomAvailability.put("Deluxe", 4);
    }

    public Map<String, Integer> getRoomAvailability() {
        return roomAvailability;
    }

    public void updateAvailability(String roomType, int count) {
        roomAvailability.put(roomType, count);
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        Map<String, Integer> availability = inventory.getRoomAvailability();

        for (String room : availability.keySet()) {
            System.out.println(room + " : " + availability.get(room));
        }

        inventory.updateAvailability("Single", 7);

        System.out.println();

        for (String room : availability.keySet()) {
            System.out.println(room + " : " + availability.get(room));
        }
    }
}