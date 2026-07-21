import java.util.ArrayList;

public class GradebookManager {
    private ArrayList<GradebookStudent> students; 

    public GradebookManager(){ //constructor that creates the ArrayList
        students = new ArrayList<>();
    }

    public ArrayList<GradebookStudent> getStudents() {// returns copy of students array
        return new ArrayList<> (students);
    }

    public void sort() { // Sorts through the array in alphabetical order using comparators 
        
        for(int i = 0; i < students.size(); i++) {
            int minIndex = i;
            for(int j = i + 1; j < students.size(); j++) {
                if (students.get(j).getName().compareTo(students.get(minIndex).getName()) < 0) minIndex = j;
                }
            if(minIndex!=i) {
                GradebookStudent temp = students.get(i);
                students.set(i,students.get(minIndex));
                students.set(minIndex, temp);
            }
            }
        }

    public void addStudent(GradebookStudent e){ // Adds a student object to manager

        if(isValidID(e) == true){ // validates the id is not already taken, and throws exception if it is
            students.add(e);
            System.out.println("Student added successfully!");
        }
        else throw new IllegalArgumentException("Invalid input, ID already exists"); 

    }

    public GradebookStudent findByID(int id){ // Loops through the array and checks if any students have the id entered
        if(students.isEmpty()){
            throw new IllegalArgumentException("No students in the gradebook yet. Add or load data first."); 
        }
        
        for (GradebookStudent s : students) {
            if (s.getId() == id) return s; 
        }

        return null; 
    }

    public ArrayList<GradebookStudent> findByName(String name) { //Loops through the array and checks if it contains the name that they entered
        ArrayList<GradebookStudent> list = new ArrayList<>();
        if(students.isEmpty()){
            throw new IllegalArgumentException("No students in the gradebook yet. Add or load data first."); 
        }
        String newName = name.toLowerCase();
        for (GradebookStudent s : students) {
            if(s.getName().toLowerCase().contains(newName)) list.add(s);
        }
        return list;
    }

    public boolean isValidID(GradebookStudent e){// Helper method to check if the id entered is already taken or not
        for (GradebookStudent s : students) {
            if (e.getId() == s.getId()) return false; 
        }

        return true; 
    }

    public void viewStudents(){ // Prints out all the students details in the expected format
        
        if(students.isEmpty()) throw new IllegalArgumentException("No students added yet!");
        
        else {
            System.out.println("All Students (sorted alphabetically A-Z):");
            for (GradebookStudent s : students) {
                if(s.calculateAverage()==0) System.out.printf("%d - %s - Average: 0.00 - (no grades yet)%n", s.getId(), s.getName());
                else System.out.printf("%d - %s - Average: %.2f%n", s.getId(), s.getName(), s.calculateAverage());
            }
        }

    }

    public void addGradeToStudent(int id, String title, double score){ // adds a grade to a student object using the addGrade method in student object
        for (GradebookStudent s : students) {
            if (s.getId() == id){
                s.addGrade(new GradeItem(title, score));
                System.out.println("Grade added successfully!");
                return; 
            }
        }
        
        if(students.isEmpty()) throw new IllegalArgumentException("No students added yet!"); 
        else throw new IllegalArgumentException("No student found with id " + id + ". Grade was not added."); 
    }

    public void viewOneStudent(int id){//Loops through and finds the student that the user wants and the prints only their details
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
