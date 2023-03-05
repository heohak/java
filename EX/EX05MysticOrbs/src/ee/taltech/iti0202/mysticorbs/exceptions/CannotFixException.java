package ee.taltech.iti0202.mysticorbs.exceptions;

import ee.taltech.iti0202.mysticorbs.oven.Oven;

public class CannotFixException extends Exception {

    private final Oven oven;
    private final Reason reason;
    public enum Reason {
        IS_NOT_BROKEN,
        FIXED_MAXIMUM_TIMES,
        NOT_ENOUGH_RESOURCES,

    }

    /**
     *
     * @param oven
     * @param reason
     */
    public CannotFixException(Oven oven, Reason reason) {
        this.oven = oven;
        this.reason = reason;
    }

    /**
     *
     * @return Oven
     */
    public Oven getOven() {
        return oven;
    }

    /**
     *
     * @return Reason
     */
    public Reason getReason() {
        return reason;
    }


}
