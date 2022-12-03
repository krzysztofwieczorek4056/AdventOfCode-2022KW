package day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            File myObj = new File("src/inputs/input3.txt");
            Scanner myReader = new Scanner(myObj);
            List<Integer> out = new ArrayList<>();
            //first
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                List<Character> first = data.substring(0, data.length() / 2).chars().mapToObj((i) -> (char) i).toList();
                List<Character> second = data.substring(data.length() / 2).chars().mapToObj((i) -> (char) i).toList();
                List<Character> common = first.stream().filter(second::contains).toList();
                char x = common.get(0);
                if (x > 96) {
                    out.add((int) x - 96);
                } else {
                    out.add((int) x - 38);
                }
            }
            int sum = out.stream().mapToInt(a -> a).sum();
            System.out.println(sum);
            myReader.close();

            //second
            Scanner myReader2 = new Scanner(myObj);
            List<String> threeLines = new ArrayList<>();
            List<List> whole = new ArrayList<>();
            while (myReader2.hasNextLine()) {
                //part-one
                String data = myReader2.nextLine();
                if (threeLines.size() < 3) {
                    threeLines.add(data);
                    if (threeLines.size() == 3) {
                        whole.add(List.copyOf(threeLines));
                        threeLines.clear();
                    }
                }
            }
            List<Integer> out2 = new ArrayList<>();
            for (List list : whole) {

                ArrayList list1 = (ArrayList) ((String) list.get(0)).chars().mapToObj((i) -> (char) i).toList();
                ArrayList list2 = (ArrayList) ((String) list.get(1)).chars().mapToObj((i) -> (char) i).toList();
                ArrayList list3 = (ArrayList) ((String) list.get(2)).chars().mapToObj((i) -> (char) i).toList();
                List<Character> common2 = (List<Character>) list1.stream().filter(list2::contains).filter(list3::contains).toList();
                char x = common2.get(0);
                if (x > 96) {
                    out2.add((int) x - 96);
                } else {
                    out2.add((int) x - 38);
                }
            }
            int sum2 = out2.stream().mapToInt(a -> a).sum();
            System.out.println(sum2);
            myReader.close();

            myReader2.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
}

