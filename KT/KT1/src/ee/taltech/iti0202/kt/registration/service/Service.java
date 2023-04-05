package ee.taltech.iti0202.kt.registration.service;

import ee.taltech.iti0202.kt.registration.cosmetic.Cosmetic;

import java.time.LocalDateTime;

public class Service {
    String name;
    ServiceType type;
    LocalDateTime startTime;

    Integer length;

    Cosmetic cosmetic;

    Integer price;

    ServiceStatus serviceStatus;

    Boolean isBooked;

    public enum ServiceType {
        HAIR_CUT,
        HAIR_COLOR,
        MAKEUP,

        FACIAL_CARE



    }

    public enum ServiceStatus {
        REGULAR,
        VIP
    }

    /**
     *
     * @param name
     * @param type
     * @param startTime
     * @param length
     * @param cosmetic
     * @param price
     * @param serviceStatus
     */
    public Service(String name, ServiceType type, LocalDateTime startTime, Integer length, Cosmetic cosmetic,
                   Integer price, ServiceStatus serviceStatus) {
        if (cosmetic.getServiceTypes().contains(type)) {
            this.name = name;
            this.type = type;
            this.startTime = startTime;
            this.length = length;
            this.cosmetic = cosmetic;
            this.price = price;
            this.serviceStatus = serviceStatus;
            isBooked = false;
        } else {
            throw new IllegalArgumentException("The assigned cosmetic cannot provide the specified service");

        }

    }

    /**
     *
     * @return String
     */
    public String getName() {
        return name;
    }
    /**
     *
     * @return ServiceType
     */
    public ServiceType getType() {
        return type;
    }

    /**
     *
     * @return Integer
     */
    public Integer getLength() {
        return length;
    }


    /**
     *
     * @return boolean
     */
    public boolean  isBooked() {
        return isBooked;
    }

    /**
     *
     * @param b
     */
    public void setIsBooked(boolean b) {
        isBooked = b;
    }

    /**
     *
     * @return Cosmetic
     */
    public Cosmetic getCosmetic() {
        return cosmetic;
    }


    /**
     *
     * @return Integer
     */
    public Integer getPrice() {
        return price;
    }


    /**
     *
     * @return ServiceStatus
     */
    public ServiceStatus getServiceStatus() {
        return serviceStatus;
    }

}
