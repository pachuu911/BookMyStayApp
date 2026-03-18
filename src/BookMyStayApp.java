import java.util.*;

class Service {
    private String serviceName;
    private double cost;

    public Service(String serviceName, double cost) {
        this.serviceName = serviceName;
        this.cost = cost;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getCost() {
        return cost;
    }
}

class AddOnServiceManager {
    private Map<String, List<Service>> servicesByReservation;

    public AddOnServiceManager() {
        servicesByReservation = new HashMap<>();
    }

    public void addService(String reservationId, Service service) {
        servicesByReservation.putIfAbsent(reservationId, new ArrayList<>());
        servicesByReservation.get(reservationId).add(service);
    }

    public double calculateTotalServiceCost(String reservationId) {
        double total = 0;
        List<Service> services = servicesByReservation.get(reservationId);
        if (services != null) {
            for (Service s : services) {
                total += s.getCost();
            }
        }
        return total;
    }
}

public class BookMyStayApp {
    public static void main(String[] args) {

        AddOnServiceManager manager = new AddOnServiceManager();

        String reservationId = "R101";

        Service breakfast = new Service("Breakfast", 500);
        Service spa = new Service("Spa", 1500);
        Service pickup = new Service("Airport Pickup", 800);

        manager.addService(reservationId, breakfast);
        manager.addService(reservationId, spa);
        manager.addService(reservationId, pickup);

        double total = manager.calculateTotalServiceCost(reservationId);

        System.out.println("Total Add-On Cost for " + reservationId + " : " + total);
    }
}