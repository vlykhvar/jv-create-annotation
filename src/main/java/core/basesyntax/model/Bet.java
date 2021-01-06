package core.basesyntax.model;

public class Bet {
    private int value;
    private double risk;

    public Bet(int value, double risk) {
        this.value = check(value);
        this.risk = check(risk);
    }

    public int getValue() {
        return value;
    }

    public double getRisk() {
        return risk;
    }

    @Override
    public String toString() {
        return "Bet{"
                + "value=" + value
                + ", risk=" + risk
                + '}';
    }

    private int check(int value) {
        if (value < 0) {
            throw new NullPointerException();
        }
        return value;
    }

    private double check(double risk) {
        if (risk < 0) {
            throw new NullPointerException();
        }
        return risk;
    }
}
