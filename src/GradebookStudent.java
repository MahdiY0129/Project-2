import java.util.ArrayList;

public class GradebookStudent {
    private int id;
    private String name;
    private ArrayList<GradeItem> grades;

    public GradebookStudent(int i, String n) {//constructor and validates the id and name are correct
        if(i <= 0) throw new IllegalArgumentException("Invalid id, must be greater than 0!");
        if (n == null || n.trim().isEmpty()) throw new IllegalArgumentException("Write a proper name.");        
        id = i;
        name = n;
        grades = new ArrayList<>();
    }

    public void addGrade(GradeItem g) {//addes a grade to the the student object 
        grades.add(g);
    }

    public void describe() {//Describe used to print out the students detals 
        System.out.println("Student Details:");
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("\nGrades:");
        if (grades.isEmpty()) System.out.println(name + " has no grades yet.");
        for(GradeItem g : grades) System.out.println(g.getTitle() + ": " + g.getScore());
        System.out.println();
        System.out.printf("Average: %.2f%n", calculateAverage());
    }

    public double calculateAverage() {//calculates the average grade of the students 
        if(grades.size()==0) return 0.0;
        else {
        double sum = 0;
        for (GradeItem g : grades) {
            sum += g.getScore();
        }
        return sum/grades.size();
        }
    }
    
    //getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<GradeItem> getGrades(){//returns a copy
        return new ArrayList<>(grades);
    }
}

