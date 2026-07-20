import java.util.ArrayList;

public class GradebookStudent {
    private int id;
    private String name;
    private ArrayList<GradeItem> grades;

    public GradebookStudent(int i, String n) {
        if(i <= 0) throw new IllegalArgumentException("Invalid id, must be greater than 0!");
        if (n == null || n.trim().isEmpty()) throw new IllegalArgumentException("Write a proper name.");        
        id = i;
        name = n;
        grades = new ArrayList<>();
    }

    public void addGrade(GradeItem g) {
        grades.add(g);
    }

    public void describe() {
        System.out.println("Student details:");
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Grades:");
        if (grades.isEmpty()) System.out.println(name + " has no grades yet.");
        for(GradeItem g : grades) System.out.println(g.getTitle() + ": " + g.getScore());
        System.out.println(calculateAverage());
    }

    public double calculateAverage() {
        if(grades.size()==0) return 0.0;
        else {
        double sum = 0;
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
        return new ArrayList<>(grades);
    }
}

