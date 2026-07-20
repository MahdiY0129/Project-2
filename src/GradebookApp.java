import java.util.Scanner; 
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class GradebookApp {
    public static void main(String[] args) {
        boolean exit = false;
        System.out.println("==== Gradebook Manager ===="); 
        System.out.println("");
        Scanner sc = new Scanner(System.in); 
        GradebookManager manager = new GradebookManager(); 
        
        while(exit == false){
            try{
                printMenu(); 
                int choice = sc.nextInt();
                sc.nextLine(); 

                switch (choice) {
                    case 1:
                        System.out.print("Enter student name and id in this format (name, id): ");
                        String name = sc.next(); 
                        int id = sc.nextInt(); 
                        manager.addStudent(new GradebookStudent(id, name));
                        break;

                    case 2:
                        System.out.print("Enter student grade title, score, and tid (title, scrore, id): ");
                        String line = sc.nextLine().trim(); 
                        String[] parts = line.split(","); 
                        
                        String title = parts[0].trim(); 
                        double score = Double.parseDouble(parts[1].trim()); 
                        int id2 = Integer.parseInt(parts[2].trim()); 
                        manager.addGradeToStudent(id2, title, score);
                        break;
                        
                    case 3:
                        manager.viewStudents();
                        break;

                    case 4:
                        System.out.print("Enter the students id of the student's detail you want to view: ");
                        int id4 = sc.nextInt(); 
                        manager.viewOneStudent(id4);
                        break;
                    
                    case 5:
                        System.out.print("Enter the student id: ");
                        int id5 = sc.nextInt(); 
                        GradebookStudent student = manager.findByID(id5); 
                        
                        if(student == null) System.out.println("Student with id " + id5 + " was not found!");
                        else student.describe();
                        break;

                    case 6:
                        loadData("data/sample_data.txt", manager);
                        break;

                    case 7:
                        try {
                            saveData(manager, "data/sample_data.txt");
                            System.out.println("Saved successfully!");
                        }catch(FileNotFoundException e){
                            System.out.println("File not found!");
                        }
                        break;

                    case 8:
                        exit = true; 
                        break;
                
                    default:
                        throw new IllegalArgumentException("Invalid input, number is not in menu!"); 
                }
            }catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
            }catch(InputMismatchException e){
                System.out.println("Invalid input, enter a number!");
                sc.next(); 
            }catch(Exception e){
                System.out.println("Invalid input!");
                sc.next();
            }
        }
    }

    public static void printMenu(){
        System.out.println("1. Add Student");
        System.out.println("2. Add Grade to Student");
        System.out.println("3. View All Students");
        System.out.println("4. View Student Details");
        System.out.println("5. Search Student by ID");
        System.out.println("6. Load Data from File");
        System.out.println("7. Save Data to File");
        System.out.println("8. Exit");
        System.out.print("Enter choice: ");
    }

    public static void saveData(GradebookManager manager, String path) throws IOException {
        ArrayList<GradebookStudent> roster = manager.getStudents();
        PrintWriter out = new PrintWriter(path);

        if(roster.isEmpty()) {
            out.close();
            throw new IllegalArgumentException("No gradebook data to save yet. Add a student or load data from a file before saving."); 
        }


        for(GradebookStudent student : roster) {
            out.println("STUDENT," + student.getId() + "," + student.getName());
            for(GradeItem grade : student.getGrades()) {
                out.println("GRADE," + student.getId() + "," + grade.getTitle() + "," + grade.getScore());
            }
        }

        out.close();
    }

    public static void loadData(String path, GradebookManager manager){
        File file = new File(path); 
        Scanner input; 
        try{
            input = new Scanner(file); 
        }catch(FileNotFoundException e){
            System.out.println("File not found!");
            return; 
        }

        while(input.hasNextLine()){
            String line = input.nextLine(); 

            if(line.isEmpty()){
                continue;
            }

            String[] parts = line.split(","); 
            
            if(parts[0].equalsIgnoreCase("student")){
                int id = Integer.parseInt(parts[1]); 
                String name = parts[2]; 
                manager.addStudent(new GradebookStudent(id, name));
            }
            else if(parts[0].equalsIgnoreCase("Grade")){
                int id = Integer.parseInt(parts[1]);
                String title = parts[2]; 
                double score = Double.parseDouble(parts[3]); 
                manager.addGradeToStudent(id, title, score);
            }
            

        }
        System.out.println("Load successful!");
        input.close();
        
    }


}
