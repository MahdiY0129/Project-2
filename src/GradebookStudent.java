import java.util.ArrayList;

public class GradebookStudent {
    private int id;
    private String name;
    private ArrayList<GradeItem> grades;

    public GradebookStudent() {
        grades = new ArrayList<>();
    }

    public GradebookStudent(int i, String n, ArrayList<GradeItem> g) {
        if(id > 0) i = id;
        
        if(!name.trim().equals(null) || !name.trim().equals("")) name = n;

        grades = g;
        
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


}

