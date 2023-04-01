package ee.taltech.iti0202.coffee.exceptions;

public class NoBeansException extends Exception {
    String result;

    public NoBeansException(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }
}
