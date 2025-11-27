package todolist;

public class List {
    private static int counter = 1;
    private int toDoListId;
    private String toDoList;

    List (String toDoList) {
        this.toDoListId = counter++;
        this.toDoList = toDoList;
    }


    public int getId() { return toDoListId; }
    public String getList() { return toDoList; }

   
    public void setToDoList(String toDoList) { this.toDoList = toDoList; }
    
    public String toFileFormat() {
        return toDoListId + "|" + toDoList;
    }

    public static List fromFile(String line) {
        String[] parts = line.split("\\|", 2);
        List l = new List(parts[1]);
        l.toDoListId = Integer.parseInt(parts[0]);
        return l;
    }

    @Override
    public String toString() {
        return "[ " + getId() + " ]." + getList();
    }
}
