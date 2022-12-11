package day11;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Monkey {
    public int monkeyNumber;
    public int test;
    List<BigInteger> startingItems;
    String operation;
    int monkeyOption1;
    int monkeyOption2;
    BigInteger value;

    public Monkey(int monkeyNumber, List<String> startingItems, String operation, int test, int monkeyOption1, int monkeyOption2) {
        this.monkeyNumber = monkeyNumber;
        List<BigInteger> items = new ArrayList<>();
        for (String s : startingItems) {
            items.add(new BigInteger(s));
        }
        this.startingItems = items;
        this.operation = operation;
        this.test = test;
        this.monkeyOption1 = monkeyOption1;
        this.monkeyOption2 = monkeyOption2;
        if (!this.operation.contains("old * old")) {
            this.value = BigInteger.valueOf(Integer.parseInt(this.operation.substring(6)));
        }
    }

    static long gcd2(long a, long b) {
        while (b > 0) {
            long temp = b;
            b = a % b; // % is remainder
            a = temp;
        }
        return a;
    }

    static long gcd(List<BigInteger> input) {
        long result = input.get(0).longValue();
        for (int i = 1; i < input.size(); i++) result = gcd2(result, input.get(1).longValue());
        return result;
    }

    public int getMonkeyNumber() {
        return monkeyNumber;
    }

    public void setMonkeyNumber(int monkeyNumber) {
        this.monkeyNumber = monkeyNumber;
    }

    public List<BigInteger> getStartingItems() {
        return startingItems;
    }

    public void setStartingItems(List<BigInteger> startingItems) {
        this.startingItems = startingItems;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public int getTest() {
        return test;
    }

    public void setTest(int test) {
        this.test = test;
    }

    public int getMonkeyOption1() {
        return monkeyOption1;
    }

    public void setMonkeyOption1(int monkeyOption1) {
        this.monkeyOption1 = monkeyOption1;
    }

    public int getMonkeyOption2() {
        return monkeyOption2;
    }

    public void setMonkeyOption2(int monkeyOption2) {
        this.monkeyOption2 = monkeyOption2;
    }

    public BigInteger getValue() {
        return value;
    }

    public void setValue(BigInteger value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Monkey{" + "monkeyNumber=" + monkeyNumber + ", startingItems=" + startingItems + ", operation='" + operation + '\'' + ", test=" + test + ", monkeyOption1=" + monkeyOption1 + ", monkeyOption2=" + monkeyOption2 + '}';
    }

    public BigInteger getFirstItem() {
        BigInteger item = this.startingItems.get(0);
        this.startingItems.remove(0);
        return item;
    }

    public int getItemsSize() {
        return this.startingItems.size();
    }

    public BigInteger getWorryLevel(BigInteger item) {
        if (this.operation.contains("old * old")) {
            return item.pow(2);
        } else if (this.operation.contains("old *")) {
            return item.multiply(this.value);
        } else {
            return item.add(this.value);
        }
    }

    public int testThrow(BigInteger worryLevel) {
        if (worryLevel.mod(BigInteger.valueOf(this.test)).equals(BigInteger.ZERO)) {
            return monkeyOption1;
        } else {
            return monkeyOption2;
        }
    }

    public void throwItem(BigInteger item) {
        this.startingItems.add(item);
    }

    public int getNumber() {
        return this.monkeyNumber;
    }

    public void lowerNumbers(BigInteger gcd) {
        int n = this.startingItems.size();
        if (n > 0 && gcd.intValue() > 0) {
            for (int i = 0; i < this.startingItems.size(); i++) {
                this.startingItems.set(i, this.startingItems.get(i).mod(gcd));
            }
        }

    }
}
