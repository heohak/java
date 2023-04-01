package ee.taltech.iti0202.coffee.exceptions;

public class TrashIsFullException extends Exception {

    String result;

    public TrashIsFullException(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }
}
