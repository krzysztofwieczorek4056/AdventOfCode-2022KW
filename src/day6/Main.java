package day6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) {
        File file = new File("src/inputs/input6.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            int procesed = 0;
            //Setup
            while (line != null) {
                //for (int i = 0; i < line.length() - 3; i++) { //part1
                for (int i = 0; i < line.length() - 13; i++) { //part2
                    //String fourChars = line.substring(i, i + 4); //part 1
                    String fourChars = line.substring(i, i + 14); //part 2
                    System.out.println(uniqueCharacters(fourChars) + ": " + fourChars);
                    //if (uniqueCharacters(fourChars) == 4) { //part1
                    if (uniqueCharacters(fourChars) == 14) {//part 2
                        procesed = i;
                        break;
                    }

                }
                line = reader.readLine();
            }
            //System.out.println(procesed + 4); //part1
            System.out.println(procesed + 14);//part 2
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int uniqueCharacters(String test) {
        return (int) test.chars().distinct().count();
    }
}

