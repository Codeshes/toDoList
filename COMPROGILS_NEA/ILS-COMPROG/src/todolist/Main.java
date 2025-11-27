package todolist;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ListManager manager = new ListManager();
        manager.loadFromFile();   // ← REQUIRED

        int choice;

        do {
        	try {
            System.out.println("""
	                 ---------------------------------------
	                         WELCOME USER!            
	                 ---------------------------------------
	                 [1]. Add TO DO LIST.
	                 [2]. VIEW LIST.
	                 [3]. SEARCH Via ID.
	                 [4]. Delete list.
	                 [5]. Mark as done.
	                 [6]. EXIT
	                 ---------------------------------------
	                         Enter your Choice.
	                 ---------------------------------------
	                 """);
            
                choice = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Choice must be a number");
                sc.nextLine();
                continue;
            }

            switch (choice) {
                case 1 -> {
                    System.out.println("Enter task");
                    String toDoList = sc.nextLine();
                    manager.addList(toDoList);
                }
                case 2 -> manager.viewList();
                case 3 -> {
                	try {
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    manager.accessToDoListById(id);
                	} catch (InputMismatchException e) {
                		System.out.println("Invalid input");
                		sc.nextLine();
                	}
                }
                case 4 -> {
                	try {
                    System.out.print("Enter ID to delete: ");
                    int id = sc.nextInt();
                    manager.deleteListById(id);
                	} catch (InputMismatchException e) {
                		System.out.println("Invalid input");
                		sc.nextLine();
                	}
                }
                case 5 -> manager.markTask();
                case 6 -> {
                    System.out.println("Exiting...");
                    return;
                }
            }
        } while (true);
        
    }
    
}
