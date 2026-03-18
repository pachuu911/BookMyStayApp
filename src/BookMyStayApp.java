import java.util.*;
import java.io.*;

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

class FilePersistenceService {

    public void saveInventory(RoomInventory inventory, String filePath) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

            for (Map.Entry<String, Integer> entry : inventory.getRoomAvailability().entrySet()) {
                writer.write(entry.getKey() + "=" + entry.getValue());
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error saving inventory.");
        }
    }

    public void loadInventory(RoomInventory inventory, String filePath) {

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split("=");

                String roomType = parts[0];
                int count = Integer.parseInt(parts[1]);

                inventory.updateAvailability(roomType, count);
            }

        } catch (IOException e) {
            System.out.println("Error loading inventory.");
        }
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();
        FilePersistenceService service = new FilePersistenceService();

        String filePath = "inventory.txt";

        service.saveInventory(inventory, filePath);

        inventory.updateAvailability("Single", 0);

        service.loadInventory(inventory, filePath);

        for (Map.Entry<String, Integer> entry : inventory.getRoomAvailability().entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}