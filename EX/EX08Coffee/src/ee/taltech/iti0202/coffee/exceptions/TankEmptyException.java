package ee.taltech.iti0202.coffee.exceptions;

public class TankEmptyException extends Exception {

    String result;

    public TankEmptyException(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }
}
