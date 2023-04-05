package ee.taltech.iti0202.kt.registration.cosmetic;

import ee.taltech.iti0202.kt.registration.service.Service;

import java.util.List;

public class Cosmetic {
    List<Service.ServiceType> serviceTypes;


    /**
     *
     * @param serviceTypes
     */
    public Cosmetic(List<Service.ServiceType> serviceTypes) {

        this.serviceTypes = serviceTypes;
    }

    /**
     *
     * @return List
     */
    public List<Service.ServiceType> getServiceTypes() {
        return serviceTypes;
    }
}
