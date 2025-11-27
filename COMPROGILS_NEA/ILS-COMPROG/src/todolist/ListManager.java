package todolist;
import java.io.*;
import java.util.Scanner;
import java.util.LinkedList;

public class ListManager {
    private final LinkedList<List> listManager = new LinkedList<>();
    private final Scanner sc = new Scanner(System.in);
    private final String FILE_PATH = new File("list.txt").getAbsolutePath();

    public void toFileFormat() {
        loadFromFile();
    }

    public void addList(String toDoList) {
        List list = new List(toDoList);
        listManager.add(list);
        System.out.println("Task Added!: " + list.getId() + " " + toDoList);
        saveToFile();
    }

    public void viewList() {
        if (listManager.isEmpty()) {
            System.out.println("You are free today!");
            return;
        }
        for (List l : listManager) {
            System.out.println(l);
        }
    }

    public void accessToDoListById(int id) {
        for (List list : listManager) {
            if (list.getId() == id) {
                System.out.println("FOUND: " + list.getList());
                return;
            }
        }
        System.out.println("Task ID not found.");
    }

    public void deleteListById(int id) {
        if (listManager.isEmpty()) {
            System.out.println("There's nothing to delete");
            return;
        }
        for (List list : listManager) {
            if (list.getId() == id) {
                listManager.remove(list);
                System.out.println("Removed Successfully: " + list.getList());
                saveToFile();
                return;
            }
        }
        System.out.println("Task number not found");
    }

    public void markTask() {
        viewList();
        if (listManager.isEmpty()) return;
        System.out.print("Enter a task ID to MARK DONE: ");
        int id = sc.nextInt();
        for (List task : listManager) {
            if (task.getId() == id) {
                task.setToDoList(task.getList() + " - ✔ DONE");
                System.out.println("Marked as Done: " + task.getList());
                saveToFile();
                return;
            }
        }
        System.out.println("Invalid Task ID");
    }

    public void saveToFile() {
        String filePath = "Notepad\\" + FILE_PATH;
        File parent = new File(filePath);
        File parentDir = parent.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            if (!parent.mkdir()) {
                System.err.println("Error creating Directory: " + parent.getAbsolutePath());
                return;
            }
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(parent))) {
            for (List list : listManager) {
                writer.write(list.toFileFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving: " + e.getMessage());
        }
    }

    public void loadFromFile() {
        try (BufferedReader dataReader = new BufferedReader(new FileReader("Notepad\\" + FILE_PATH))) {
            String line;
            while ((line = dataReader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                try {
                    List list = List.fromFile(line);
                    listManager.add(new List(list.getList()));
                } catch (Exception e) {
                    System.err.println("Skipping invalid item line during load: " + line + " Error: " + e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error loading items: File not found. Starting with empty inventory.");
        } catch (IOException e) {
            System.out.println("Error reading file during load: " + e.getMessage());

        }
    }
}