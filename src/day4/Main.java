package day4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        File file = new File("src/inputs/input4.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            int contain = 0;
            int contain2 = 0;
            while (line != null) {
                String[] pairs = line.split(",");
                List<String> sections = new ArrayList<>();
                for (String assignment : pairs) {
                    List<String> s = Arrays.asList(assignment.split("-"));
                    sections.addAll(s);
                }
                List<Integer> matrix = IntStream.rangeClosed(Integer.parseInt(sections.get(0)), Integer.parseInt(sections.get(1))).boxed().toList();
                List<Integer> matrix2 = IntStream.rangeClosed(Integer.parseInt(sections.get(2)), Integer.parseInt(sections.get(3))).boxed().toList();

                if (matrix.stream().allMatch(matrix2::contains) || matrix2.stream().allMatch(matrix::contains)) {
                    contain++;
                }
                if (matrix.stream().anyMatch(matrix2::contains) || matrix2.stream().anyMatch(matrix::contains)) {
                    contain2++;
                }
                line = reader.readLine();
            }

            System.out.println(contain);
            System.out.println(contain2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
