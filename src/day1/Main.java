package day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            File myObj = new File("src/inputs/input1.txt");
            Scanner myReader = new Scanner(myObj);
            int biggestElf = 0;
            int currentElf = 0;
            ArrayList<Integer> list = new ArrayList<Integer>();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if(!data.equals("")){
                    currentElf += Integer.parseInt(data);
                }else {
                    list.add(currentElf);
                    //System.out.println(currentElf);
                    if(currentElf > biggestElf){
                        biggestElf = currentElf;
                    }
                    currentElf = 0;
                }
            }
            Collections.sort(list);
            myReader.close();
            System.out.println(list.size());
            int sum = list.get(list.size()-1) +list.get(list.size()-2) + list.get(list.size()-3);
            System.out.println(sum);
            //System.out.println(biggestElf);
            //System.out.println(currentElf);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}