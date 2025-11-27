package todolist;
import java.io.*;
import java.util.InputMismatchException;
import java.util.ArrayList;
import java.util.Scanner;

public class ListManager {
    private final ArrayList<List> listManager = new ArrayList<>();
    private final Scanner sc = new Scanner(System.in);
    private final String FILE_PATH = "Notepad/list.txt";

    public void addList(String toDoList) {
        List list = new List(toDoList);
        listManager.add(list);
        saveToFile();
        System.out.println("Task Added!: " + list.getId() + " " + toDoList);
    }

    public void viewList() {
        if (listManager.isEmpty()) {
            System.out.println("You are free today!");
            return;
        }
        for (List l : listManager) System.out.println(l);
    }

    public void accessToDoListById(int id) {
        for (List list : listManager)
            if (list.getId() == id) {
                System.out.println("FOUND: " + list.getList());
                return;
            }
        System.out.println("Task ID not found.");
    }

    public void deleteListById(int id) {
        if (listManager.isEmpty()) {
            System.out.println("There's nothing to delete");
            return;
        }
        for (List list : listManager)
            if (list.getId() == id) {
                listManager.remove(list);
                saveToFile();
                System.out.println("Removed Successfully: " + list.getList());
                return;
            }
        System.out.println("Task number not found");
    }

    public void markTask() {
    	try {
        viewList();
        if (listManager.isEmpty()) return;
        System.out.print("Enter a task ID to MARK DONE: ");
        int id = sc.nextInt();
        for (List task : listManager)
            if (task.getId() == id) {
                task.setToDoList(task.getList() + " - ✔ DONE");
                saveToFile();
                System.out.println("Marked as Done: " + task.getList());
                return;
            }
        System.out.println("Invalid Task ID");
    	} catch (InputMismatchException e) {
    		System.out.println("Invalid input");
    		sc.nextLine();
    	}
    }

    public void saveToFile() {
        File file = new File(FILE_PATH);
        File parent = file.getParentFile();
        if (!parent.exists()) parent.mkdirs();

        try (BufferedWriter w = new BufferedWriter(new FileWriter(file))) {
            for (List l : listManager) {
                w.write(l.toFileFormat());
                w.newLine();
            }
        } catch (IOException e) {
            System.out.println("SAVE ERROR: " + e.getMessage());
        }
    }

    public void loadFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return;

        try (BufferedReader r = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = r.readLine()) != null)
                if (!line.trim().isEmpty())
                    listManager.add(List.fromFile(line));
        } catch (IOException e) {
            System.out.println("LOAD ERROR: " + e.getMessage());
        }
    }
}
