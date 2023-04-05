package ee.taltech.iti0202.kt.registration.tests;

import ee.taltech.iti0202.kt.registration.client.Client;
import ee.taltech.iti0202.kt.registration.cosmetic.Cosmetic;
import ee.taltech.iti0202.kt.registration.salon.Salon;
import ee.taltech.iti0202.kt.registration.service.Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class BeautySalonTest {
    Salon salon1;

    Cosmetic cosmetic1;

    Client client1;

    Service service1;

    Service service2;

    Service service3;
    Service service4;

    @BeforeEach
    void setup() {
        LocalDateTime time1 = LocalDateTime.of(2023, Month.JANUARY, 10, 10, 0);
        salon1 = new Salon();
        cosmetic1 = new Cosmetic(List.of(Service.ServiceType.MAKEUP, Service.ServiceType.HAIR_CUT,
                Service.ServiceType.HAIR_COLOR, Service.ServiceType.FACIAL_CARE));
        client1 = new Client(100);
        service1 = new Service("cheapFaceCleaning", Service.ServiceType.FACIAL_CARE, time1, 2, cosmetic1,
                50, Service.ServiceStatus.REGULAR);
        service2 = new Service("SpecialMakeUp", Service.ServiceType.MAKEUP, time1, 1, cosmetic1, 140,
                Service.ServiceStatus.VIP);
        service3 = new Service("EvenMoreCheaperFaceCleaning", Service.ServiceType.FACIAL_CARE, time1,
                2, cosmetic1, 20, Service.ServiceStatus.REGULAR);
        service4 = new Service("HairColor", Service.ServiceType.HAIR_COLOR, time1, 1, cosmetic1, 60,
                Service.ServiceStatus.VIP);
    }

    @Test
    void testClientBooksService() {
        salon1.addServiceToSalon(service1);
        client1.bookService(service1, salon1);
        assertEquals(50, client1.getMoney());
        assertEquals(1, client1.getBookedServices().size());
        assertEquals(1, salon1.getBookedServices().size());

    }

    @Test
    void testClientCantBookServiceNoMoney() {
        salon1.addServiceToSalon(service2);
        client1.bookService(service2, salon1);
        assertFalse(service1.isBooked());
        assertEquals(0, client1.getBookedServices().size());
    }

    @Test
    void testClientCancelRegularService() {
        salon1.addServiceToSalon(service1);
        salon1.addServiceToSalon(service2);
        client1.bookService(service1, salon1);
        assertEquals(1, client1.getBookedServices().size());
        client1.cancelService(service1, salon1);
        assertEquals(0, client1.getBookedServices().size());
    }

    @Test
    void testClientCantCancelVipService() {
        salon1.addServiceToSalon(service4);
        client1.bookService(service4, salon1);
        assertEquals(1, client1.getBookedServices().size());
        client1.cancelService(service4, salon1);
        assertEquals(1, client1.getBookedServices().size());
    }

    @Test
    void testGetAllServices() {
        salon1.addServiceToSalon(service1);
        salon1.addServiceToSalon(service2);
        assertEquals(2, salon1.getAllServices().size());
    }

    @Test
    void testFindServiceByType() {
        salon1.addServiceToSalon(service1);
        salon1.addServiceToSalon(service3);

        assertEquals(service3, salon1.findServiceByType(Service.ServiceType.FACIAL_CARE));
    }
}
