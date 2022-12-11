package day11;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Monkey {
    public int monkeyNumber;
    List<BigInteger> startingItems;
    String operation;
    public BigInteger test;
    int monkeyOption1;
    int monkeyOption2;

    BigInteger value;

    @Override
    public String toString() {
        return "Monkey{" +
                "monkeyNumber=" + monkeyNumber +
                ", startingItems=" + startingItems +
                ", operation='" + operation + '\'' +
                ", test=" + test +
                ", monkeyOption1=" + monkeyOption1 +
                ", monkeyOption2=" + monkeyOption2 +
                '}';
    }

    public Monkey(int monkeyNumber, List<String> startingItems, String operation, int test, int monkeyOption1, int monkeyOption2) {
        this.monkeyNumber = monkeyNumber;
        List<BigInteger > items = new ArrayList<>();
        for(String s: startingItems){
            items.add(new BigInteger(s));
        }
        this.startingItems = items;
        this.operation = operation;
        this.test = BigInteger.valueOf(test);
        this.monkeyOption1 = monkeyOption1;
        this.monkeyOption2 = monkeyOption2;
        if(!this.operation.contains("old * old")) {
            this.value = BigInteger.valueOf(Integer.parseInt(this.operation.substring(6)));
        }
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
        if(this.operation.contains("old * old")){
            return item.pow(2);
        } else if(this.operation.contains("old *")){
            return item.multiply(this.value);
        }else {
            return item.add(this.value);
        }
    }
    public int testThrow(BigInteger worryLevel) {
        if(worryLevel.mod(this.test).equals(BigInteger.ZERO)){
            return monkeyOption1;
        }else {
            return monkeyOption2;
        }
    }

    public void throwItem(BigInteger item) {
        this.startingItems.add(item);
    }

    public int getNumber() {
        return this.monkeyNumber;
    }
}
