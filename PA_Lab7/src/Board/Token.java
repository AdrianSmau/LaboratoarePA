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
