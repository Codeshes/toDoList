package todolist;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ListManager manager = new ListManager();
        int choice;
        int id = 0;

        do {

            try {
                System.out.println("""
	                		      ---------------------------------------
	                		                  WELCOME USER!            
	                		      ---------------------------------------
	                        [1]. Add TO DO LIST.                 
	                        [2]. VIEW LIST.                      
	                        [3]. SEARCH Via ID/Number of the list.
	                        [4]. delete list.                    
	                        [5]. Mark as done                       
	                        [6]. EXIT
	                        ---------------------------------------
	                                  Enter your Choice.          
	                        ---------------------------------------
	                        """);
                choice = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Choice must be a Number");
                return;
            }

            switch (choice) {
                case 1 -> {
                    System.out.println("Enter task");
                    String toDoList = sc.nextLine();
                    manager.addList(toDoList);
                }
                case 2 -> {
                    System.out.println("\n=== TO DO LIST ===");
                    System.out.println("---------------------------------------");
                    manager.viewList();
                }
                case 3 -> {
                    try {
                        System.out.print("Enter List number to Search: ");
                        id = sc.nextInt() - 1;
                    } catch (InputMismatchException e) {
                        System.out.println("Input must be a number");
                        return;
                    }
                    manager.accessToDoListById(id);
                }
                case 4 -> {
                    try {
                        System.out.print("Enter id to Delete: ");
                        id = sc.nextInt() - 1;
                    } catch (InputMismatchException e) {
                        System.out.println("Input must be a number");
                        return;
                    }
                    manager.deleteListById(id);
                }
                case 5 -> {
                	manager.markTask();
                }
                case 6 -> {
                	
                	System.out.println("Exiting");
                    for (int i = 0; i < 4; i++) {
                        try {
                            Thread.sleep(400);
                            System.out.println(".");
                            System.exit(0);
                            sc.close();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            System.exit(0);
                            sc.close();
                        }
                    }
                }
            }
        } while (choice != 5);

    }

}
