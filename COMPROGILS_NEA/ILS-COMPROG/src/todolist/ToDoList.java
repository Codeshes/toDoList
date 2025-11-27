package todolist;

import java.util.Scanner;
import java.util.ArrayList;

public class ToDoList {
    private static ArrayList<String> tasks = new ArrayList<>();

    public static void showTask() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks for today");
        } else {
            System.out.println("To do list");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    public static void addTask(Scanner sc) {
        System.out.println("How many task you like to add?");
        int addTaskNum = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < addTaskNum; i++) {
            System.out.println("Enter task " + (i + 1) + ": ");
            String task = sc.nextLine();
            tasks.add(task);
            System.out.println("Task added!");
        }
    }

    public static void editTask(Scanner sc) {
        showTask();
        if (!tasks.isEmpty()) {
            System.out.println("Enter task number to edit");
            int indexTask = sc.nextInt() - 1;
            sc.nextLine();

            if (indexTask >= 0 && indexTask < tasks.size()) {
                System.out.println("Enter new task Description");
                String newTask = sc.nextLine();
                tasks.set(indexTask, newTask);
                System.out.println("Task Uploaded Successfuly");
            }
        }
    }

    public static void deleteTask(Scanner sc) {
        showTask();
        if (!tasks.isEmpty()) {
            System.out.println("Enter an task to delete");
            int indexDelete = sc.nextInt() - 1;
            sc.nextLine();

            if (indexDelete < 0 || indexDelete >= tasks.size()) {
                System.out.println("Invalid task Number");
            } else {
                String deletedTask = tasks.remove(indexDelete);
                System.out.println("Task: '" + deletedTask + "' deleted Successfully!");
            }

        }
    }

    public static void markTask(Scanner sc) {
        showTask();
        if (!tasks.isEmpty()) {
            System.out.println("Enter an task number to mark");
            int indexTask = sc.nextInt() - 1;
            sc.nextLine();
            if (indexTask >= 0 && indexTask < tasks.size()) {
                String completedTasks = tasks.get(indexTask) + " ✅";
                tasks.set(indexTask, completedTasks);
            } else {
                System.out.println("Invalid Task number");
            }
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== Menu's === ");
            System.out.println("1. View Tasks");
            System.out.println("2. Add Task");
            System.out.println("3. Edit task");
            System.out.println("4. Delete task");
            System.out.println("5. Mark task");
            System.out.println("0. Exit");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    showTask();
                    break;
                case 2:
                    addTask(sc);
                    break;
                case 3:
                    editTask(sc);
                    break;
                case 4:
                    deleteTask(sc);
                    break;
                case 5:
                    markTask(sc);
                    break;
                case 0:
                    System.out.println("Exiting....");
                    for (int i = 0; i < 10; i++) {
                        System.out.println("*");
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }

        } while (choice != 0);

    }
}
