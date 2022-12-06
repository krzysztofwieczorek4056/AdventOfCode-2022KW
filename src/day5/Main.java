package day5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        File file = new File("src/inputs/input5.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            char[][] initTab = new char[8][9];
            int lineNumber = 1;
            List<Stack> listOfStack = new ArrayList<>();
            List<List> instructions = new ArrayList<>();
            //Setup
            while (line != null) {
                if (lineNumber < 9) {
                    int index = 1;
                    for (int j = 0; j < initTab[lineNumber - 1].length; j++) {
                        if (line.length() > index) {
                            initTab[lineNumber - 1][j] = line.charAt(index);
                            index += 4;
                        } else {
                            initTab[lineNumber - 1][j] = ' ';
                        }
                    }
                } else if (lineNumber > 10){
                    line = line.replace(" ", "");
                    List<String> s = new ArrayList<String>(Arrays.asList(line.split("[move,from,to]" )));
                    s.removeAll(Arrays.asList("", null));
                    instructions.add(s);
                }
                lineNumber++;
                line = reader.readLine();
            }
            for (int i = 0; i < initTab[0].length; i++) {
                Stack stack = new Stack();
                for (int j = initTab.length - 1; j >= 0; j--) {
                    if (initTab[j][i] != ' ') {
                        stack.push(initTab[j][i]);
                    }
                }
                listOfStack.add(stack);
            }

            //part 1
//            for(List inst: instructions){
//                for(int i = 0; i < Integer.parseInt((String) inst.get(0)); i++){
//                    char a = (char) listOfStack.get(Integer.parseInt((String) inst.get(1))-1).pop();
//                    listOfStack.get(Integer.parseInt((String) inst.get(2))-1).push(a);
//                }
//            }

            //part2
            for (List inst : instructions) {
                List<Character> crates = new ArrayList<>();
                for (int i = 0; i < Integer.parseInt((String) inst.get(0)); i++) {
                    char a = (char) listOfStack.get(Integer.parseInt((String) inst.get(1)) - 1).pop();
                    crates.add(a);
                }
                for(int i = crates.size()-1; i >= 0; i--){
                    listOfStack.get(Integer.parseInt((String) inst.get(2)) - 1).push(crates.get(i));
                }
            }
            for (Stack in : listOfStack){
                System.out.print(in.peek());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}