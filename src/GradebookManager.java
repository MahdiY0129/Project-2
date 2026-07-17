import java.util.ArrayList;

public class GradebookManager {
    private ArrayList<GradebookStudent> students; 

    public GradebookManager(){
        students = new ArrayList<>(); 
    }

    public void addStudent(GradebookStudent e){

        if(isValidID(e) == true){
            students.add(e); 
        }
        else throw new IllegalArgumentException("Invalid input, ID already exists"); 

    }

    public GradebookStudent findByID(int id){
        for (GradebookStudent s : students) {
            if (s.getId() == id) return s; 
        }

        return null; 
    }

    public boolean isValidID(GradebookStudent e){
        for (GradebookStudent s : students) {
            if (e.getId() == s.getId()) return false; 
        }

        return true; 
    }

    public void viewStudents(){
        for (GradebookStudent s : students) {
            System.out.println("Student name: " + s.getName());
        }
    }

    public void addGradeToStudent(int id, String title, double score){
        for (GradebookStudent s : students) {
            if (s.getId() == id){
                s.addGrade(new GradeItem(title, score));
            }
        }
    }

    public void viewOneStudent(int id){
        for (GradebookStudent s : students) {
            if(s.getId() == id){
                System.out.println(s.describe());
            }
        }
    }
    
}
