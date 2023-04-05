package ee.taltech.iti0202.kt.registration.client;

import ee.taltech.iti0202.kt.registration.salon.Salon;
import ee.taltech.iti0202.kt.registration.service.Service;

import java.util.ArrayList;
import java.util.List;

public class Client {
    Integer money;
    List<Service> bookedServices = new ArrayList<>();

    /**
     *
     * @param money
     */
    public Client(Integer money) {
        this.money = money;
    }

    /**
     *
     * @return
     */
    public Integer getMoney() {
        return money;
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
     * @param service
     * @param salon
     */
    public void bookService(Service service, Salon salon) {
        if (salon.getUnBookedServices().contains(service) && getMoney() >= service.getPrice()
                && !service.isBooked() && !bookedServices.contains(service)) {
            service.setIsBooked(true);
            salon.addServiceToBookedServices(service);
            this.money = this.money - service.getPrice();
            bookedServices.add(service);
        }
    }

    /**
     *
     * @param service
     * @param salon
     */
    public void cancelService(Service service, Salon salon) {
        if (salon.getBookedServices().contains(service) && service.getServiceStatus() == Service.ServiceStatus.REGULAR
                && bookedServices.contains(service)) {
            service.setIsBooked(false);
            salon.addServiceToUnBookedServices(service);
            bookedServices.remove(service);
        }
    }
}
