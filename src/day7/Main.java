package day7;

import java.io.File;
import java.nio.file.Files;
import java.util.List;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        File file = new File("src/inputs/input7.txt");
        List<String> list = null;
        try {
            list = Files.readAllLines(file.toPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
        part(list);
    }

    public static void part(List<String> list) {
        Folder root = new Folder("patch");
        Folder currentFolder = root;
        for (String line : list) {
            if (line.startsWith("$ cd ..")) {
                currentFolder = currentFolder.parentFolder;
            } else if (line.startsWith("$ cd")) {
                currentFolder = currentFolder.addFolder(line.substring(5), currentFolder);
            } else if (isNumeric(line.substring(0, 1))) {
                int filesize = Integer.parseInt(line.split(" ")[0]);
                currentFolder.addFileSize(filesize);
            }
        }
        System.out.println("Part 1: " + root.getDirAndSubDirSize());
        System.out.println("Part 2: " + root.getSmalest(root.getDirs()));
        System.out.println(root.printTree());
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        return Pattern.compile("-?\\d+(\\.\\d+)?").matcher(strNum).matches();
    }
}