import java.util.*;

class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}

class RoomInventory {
    private Map<String, Integer> roomAvailability;

    public RoomInventory() {
        roomAvailability = new HashMap<>();
        roomAvailability.put("Single", 2);
        roomAvailability.put("Double", 2);
        roomAvailability.put("Suite", 1);
    }

    public Map<String, Integer> getRoomAvailability() {
        return roomAvailability;
    }

    public void updateAvailability(String roomType, int count) {
        roomAvailability.put(roomType, count);
    }
}

class RoomAllocationService {

    private Set<String> allocatedRoomIds;
    private Map<String, Set<String>> assignedRoomsByType;

    public RoomAllocationService() {
        allocatedRoomIds = new HashSet<>();
        assignedRoomsByType = new HashMap<>();
    }

    public void allocateRoom(Reservation reservation, RoomInventory inventory) {

        String type = reservation.getRoomType();
        Map<String, Integer> availability = inventory.getRoomAvailability();

        if (availability.get(type) == null || availability.get(type) <= 0) {
            System.out.println("No rooms available for " + reservation.getGuestName());
            return;
        }

        String roomId = generateRoomId(type);

        allocatedRoomIds.add(roomId);

        assignedRoomsByType.putIfAbsent(type, new HashSet<>());
        assignedRoomsByType.get(type).add(roomId);

        inventory.updateAvailability(type, availability.get(type) - 1);

        System.out.println("Reservation confirmed for " + reservation.getGuestName() + " | Room ID: " + roomId);
    }

    private String generateRoomId(String roomType) {
        int number = allocatedRoomIds.size() + 1;
        return roomType.substring(0, 1).toUpperCase() + number;
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        Queue<Reservation> queue = new LinkedList<>();

        queue.offer(new Reservation("Abhi", "Single"));
        queue.offer(new Reservation("Subha", "Double"));
        queue.offer(new Reservation("Vanmathi", "Suite"));

        RoomAllocationService service = new RoomAllocationService();

        while (!queue.isEmpty()) {
            Reservation r = queue.poll();
            service.allocateRoom(r, inventory);
        }
    }
}