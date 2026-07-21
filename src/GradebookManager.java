import java.util.ArrayList;

public class GradebookManager {
    private ArrayList<GradebookStudent> students; 

    public GradebookManager(){
        students = new ArrayList<>();
    }

    public ArrayList<GradebookStudent> getStudents() {
        return new ArrayList<> (students);
    }

    public void sort() {
        GradebookStudent temp;
        int value = 0;
        for(int i = 0; i < students.size(); i++) {
            GradebookStudent smaller = students.get(i);
            for(int j = i + 1; j < students.size(); j++) {
                value = smaller.getName().compareTo(students.get(j).getName());
                if(value>0) {
                    temp = students.get(i);
                    students.set(i,students.get(j));
                    students.set(j,temp);
                }
            }
        }
    }

    public void addStudent(GradebookStudent e){

        if(isValidID(e) == true){
            students.add(e);
            System.out.println("Student added successfully!");
        }
        else throw new IllegalArgumentException("Invalid input, ID already exists"); 

    }

    public GradebookStudent findByID(int id){
        if(students.isEmpty()){
            throw new IllegalArgumentException("No students in the gradebook yet. Add or load data first."); 
        }
        
        for (GradebookStudent s : students) {
            if (s.getId() == id) return s; 
        }

        return null; 
    }

    public ArrayList<GradebookStudent> findByName(String name) {
        ArrayList<GradebookStudent> list = new ArrayList<>();
        if(students.isEmpty()){
            throw new IllegalArgumentException("No students in the gradebook yet. Add or load data first."); 
        }
        String newName = name.toLowerCase();
        for (GradebookStudent s : students) {
            if(s.getName().contains(newName)) list.add(s);
        }
        return list;
    }

    public boolean isValidID(GradebookStudent e){
        for (GradebookStudent s : students) {
            if (e.getId() == s.getId()) return false; 
        }

        return true; 
    }

    public void viewStudents(){
        
        if(students.isEmpty()) throw new IllegalArgumentException("No students added yet!");
        
        else {
            System.out.println("All Students (sorted alphabetically A-Z):");
            for (GradebookStudent s : students) {
                System.out.println(s.getId() + " - " + s.getName() + " - Average: " + s.calculateAverage());
            }
        }

    }

    public void addGradeToStudent(int id, String title, double score){
        for (GradebookStudent s : students) {
            if (s.getId() == id){
                s.addGrade(new GradeItem(title, score));
                System.out.println("Grade added successfully!");
                return; 
            }
        }
        
        if(students.isEmpty()) throw new IllegalArgumentException("No students added yet!"); 
        else throw new IllegalArgumentException("No student found with id 999. Grade was not added."); 
    }

    public void viewOneStudent(int id){
        for (GradebookStudent s : students) {
            if(s.getId() == id){
                s.describe();
                return; 
            }
        }
        
        if(students.isEmpty()) throw new IllegalArgumentException("No students added yet!"); 
        else throw new IllegalArgumentException("Student not found!"); 
    }
    
}
