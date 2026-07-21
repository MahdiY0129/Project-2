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
                        System.out.print("Enter student name and id in this format (id, name): ");
                        String input = sc.nextLine().trim();

                        String[] parts = input.split(","); 
                        int id = Integer.parseInt(parts[0].trim()); 
                        String name = parts[1]; 
                        manager.addStudent(new GradebookStudent(id, name));
                        break;

                    case 2:
                        System.out.print("Enter student grade title, score, and id (title, score, id): ");
                        String line = sc.nextLine().trim(); 
                        String[] parts1 = line.split(","); 
                        
                        String title = parts1[0].trim(); 
                        double score = Double.parseDouble(parts1[1].trim()); 
                        int id2 = Integer.parseInt(parts1[2].trim()); 
                        manager.addGradeToStudent(id2, title, score);
                        break;
                        
                    case 3:
                        manager.sort();
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
                    System.out.print("Enter the student name (can be partial): ");
                        String studentName = sc.next(); 
                        manager.sort();
                        ArrayList<GradebookStudent> list = manager.findByName(studentName);
                        
                        if(list.isEmpty()) System.out.println("There were no students with the name " + studentName);
                        else {
                            for(GradebookStudent s : list)
                            s.describe();
                            System.out.println();
                        }
                        break;
                    
                    case 7:
                        loadData("data/sample_data.txt", manager);
                        break;

                    case 8:
                        try {
                            saveData(manager, "data/sample_data.txt");
                            System.out.println("Saved successfully!");
                        }catch(FileNotFoundException e){
                            System.out.println("File not found!");
                        }
                        break;

                    case 9:
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
        System.out.println("6. Search Student by Name");
        System.out.println("7. Load Data from File");
        System.out.println("8. Save Data to File");
        System.out.println("9. Exit");
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
        int count = 0; 
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
            
            try{
                if(parts[0].equalsIgnoreCase("student")){
                    int id = Integer.parseInt(parts[1]); 
                    String name = parts[2]; 
                    manager.addStudent(new GradebookStudent(id, name));
                    count++; 
                }
                else if(parts[0].equalsIgnoreCase("Grade")){
                    int id = Integer.parseInt(parts[1]);
                    String title = parts[2]; 
                    double score = Double.parseDouble(parts[3]); 
                    manager.addGradeToStudent(id, title, score);
                    count++; 
                }
            }catch(Exception e){
                System.out.println("Something went wrong parsing a line!");
            }
            

        }
        System.out.println("Load successful!");
        System.out.println("Students loaded: " + count);
        input.close();
        
    }


}
