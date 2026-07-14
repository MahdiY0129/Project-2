import java.util.ArrayList;

public class GradebookStudent {
    private int id;
    private String name;
    private ArrayList<GradeItem> grades;

    public GradebookStudent() {
        grades = new ArrayList<>();
    }

    public GradebookStudent(int i, String n) {
        if(id < 0) throw new IllegalArgumentException("Enter a valid ID.");
        
        if(name.trim().equals(null) || name.trim().equals("")) throw new IllegalArgumentException("Write a proper name.");
        
        i = id;
        n = name;
    }

    public String describe() {
        return name + "has a " + calculateAverage() + " average.";
    }


    public double calculateAverage() {
        if(grades.size()==0) return 0.0;
        else {int sum = 0;
        for (GradeItem g : grades) {
            sum += g.getScore();
        }
        return sum/grades.size();
        }
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<GradeItem> getGrades(){
        return grades;
    }
}

