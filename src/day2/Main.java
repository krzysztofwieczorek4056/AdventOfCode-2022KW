package day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            File myObj = new File("src/inputs/input2.txt");
            Scanner myReader = new Scanner(myObj);
            int pktSum = 0;
            int pktSum2 = 0;
            while (myReader.hasNextLine()) {
                //part-one
                String data = myReader.nextLine();
                char elf = data.charAt(0);
                char me = data.charAt(2);
                if(me=='X'){
                    if(elf == 'A'){
                        pktSum += 3 + 1;
                    }else if(elf == 'B'){
                        pktSum += 0 + 1;
                    }else if(elf == 'C') {
                        pktSum += 6 + 1;
                    }
                } else if (me == 'Y') {
                    if(elf == 'A'){
                        pktSum += 6 + 2;
                    }else if(elf == 'B'){
                        pktSum += 3 + 2;
                    }else if(elf == 'C') {
                        pktSum += 0 + 2;
                    }
                }else if (me == 'Z') {
                    if(elf == 'A'){
                        pktSum += 0 + 3;
                    }else if(elf == 'B'){
                        pktSum += 6 + 3;
                    }else if(elf == 'C'){
                        pktSum += 3 + 3;
                    }
                }
                //part two
                // X - lose
                // Y - draw
                // Z - win
                // A - rock
                // B - paper
                // C - scisors
                if(me=='X'){
                    if(elf == 'A'){
                        pktSum2 += 0 + 3;
                    }else if(elf == 'B'){
                        pktSum2 += 0 + 1;
                    }else if(elf == 'C') {
                        pktSum2 += 0 + 2;
                    }
                } else if (me == 'Y') {
                    if(elf == 'A'){
                        pktSum2 += 3 + 1;
                    }else if(elf == 'B'){
                        pktSum2 += 3 + 2;
                    }else if(elf == 'C') {
                        pktSum2 += 3 + 3;
                    }
                }else if (me == 'Z') {
                    if(elf == 'A'){
                        pktSum2 += 6 + 2;
                    }else if(elf == 'B'){
                        pktSum2 += 6 + 3;
                    }else if(elf == 'C'){
                        pktSum2 += 6 + 1;
                    }
                }
            }
            System.out.println(pktSum);
            System.out.println(pktSum2);
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
}

