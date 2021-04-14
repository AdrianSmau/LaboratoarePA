package Board;

public class Token {
    final private int number1;
    final private int number2;
    final private int value;
    private boolean available = true;

    public Token(int number1, int number2, int value) {
        this.number1 = number1;
        this.number2 = number2;
        this.value = value;
    }

    public int getNumber1() {
        return number1;
    }

    public int getNumber2() {
        return number2;
    }

    public int getValue() {
        return value;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "TOKEN - (" + this.number1 + "," + this.number2 + ") - value: " + this.value;
    }
}
