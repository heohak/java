package ee.taltech.iti0202.kt.registration.salon;

import ee.taltech.iti0202.kt.registration.service.Service;

import java.util.ArrayList;
import java.util.List;

public class Salon {

    List<Service> allServices = new ArrayList<>();

    List<Service> unBookedServices = new ArrayList<>();

    List<Service> bookedServices = new ArrayList<>();


    /**
     *
     */
    public Salon() {
    }

    /**
     *
     * @param service
     */
    public void addServiceToSalon(Service service) {
        allServices.add(service);
        unBookedServices.add(service);
    }

    /**
     *
     * @param service
     */
    public void addServiceToBookedServices(Service service) {
        bookedServices.add(service);
    }

    /**
     *
     * @param service
     */
    public void addServiceToUnBookedServices(Service service) {
        unBookedServices.add(service);
    }

    /**
     *
     * @return List
     */
    public List<Service> getAllServices() {
        return allServices;
    }

    /**
     *
     * @return List
     */
    public List<Service> getUnBookedServices() {
        return unBookedServices;
    }

    /**
     *
     * @return List
     */
    public List<Service> getBookedServices() {
        return bookedServices;
    }

    /**
     *
     * @param type
     * @return Service
     */
    public Service findServiceByType(Service.ServiceType type) {
        return allServices.stream()
                .filter(service -> service.getType() == type && !service.isBooked())
                .min((service1, service2) -> Double.compare(service1.getPrice(), service2.getPrice()))
                .orElse(null);
    }
}
