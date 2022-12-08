package day7;

import java.util.ArrayList;
import java.util.List;

public class Folder {
    public String patch;
    public int fileSizes;

    public Folder parentFolder;

    public List<Folder> folders;

    public Folder(String patch) {
        this.patch = patch;
        this.folders = new ArrayList<>();
        this.fileSizes = 0;

    }

    public Folder(String patch, Folder parent) {
        this.patch = patch;
        this.folders = new ArrayList<>();
        this.fileSizes = 0;
        this.parentFolder = parent;
    }


    public void addFileSize(int size) {
        this.fileSizes += size;
        if (this.parentFolder != null) {
            this.parentFolder.addFileSize(size);
        }
    }


    public Folder addFolder(String patch, Folder parent) {
        Folder created = new Folder(patch, parent);
        folders.add(created);
        return created;
    }

    public int getDirAndSubDirSize() {
        int size = 0;
        if (fileSizes < 100000) {
            size = fileSizes;
        }
        for (Folder a : folders) {

            size += a.getDirAndSubDirSize();
        }
        return size;
    }

    public ArrayList<Integer> getDirs() {
        List<Integer> size = new ArrayList<Integer>();
        size.add(fileSizes);
        for (Folder a : folders) {
            size.addAll(a.getDirs());
        }

        return (ArrayList<Integer>) size;
    }

    public int getSmalest(ArrayList<Integer> size) {
        int smalest = 70000000;
        int needed = 30000000 - (70000000 - this.fileSizes);
        for (Integer s : size) {
            if (s > needed) {
                if (s < smalest) {
                    smalest = s;
                }
            }

        }
        return smalest;
    }

    @Override
    public String toString() {
        return this.patch;
    }

    public String printTree() {
        return printRecurse(this, 0);
    }
    private static String printRecurse(Folder node, int level) {
        StringBuilder sb = new StringBuilder();
        sb.append(new String(new char[level * 2]).replace("\0", " "))
                .append(node.toString()).append("\n");
        for (Folder child : node.folders) {
            sb.append(printRecurse(child, level + 1));
        }
        return sb.toString();
    }
}

