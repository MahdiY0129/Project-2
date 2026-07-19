import java.util.Scanner; 
import java.util.ArrayList;
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

                switch (choice) {
                    case 1:
                        System.out.print("Enter student name and id in this format (name, id): ");
                        String name = sc.next(); 
                        int id = sc.nextInt(); 
                        manager.addStudent(new GradebookStudent(id, name));
                        break;

                    case 2:
                        System.out.print("Enter student id, grade title, and the score (id, title, score): ");
                        int id2 = sc.nextInt(); 
                        String title = sc.next(); 
                        double score = Double.parseDouble(sc.next()); 
                        manager.addGradeToStudent(id2, title, score);
                        break;
                        
                    case 3:
                        System.out.print("Enter the students id of the student's detail you want to view: ");
                        int id3 = sc.nextInt(); 
                        manager.viewOneStudent(id3);
                        break;

                    case 4:
                        
                        break;
                    
                    case 5:
                        System.out.print("Enter the student id: ");
                        int id4 = sc.nextInt(); 
                        GradebookStudent student = manager.findByID(id4); 
                        System.out.println("Students name is: " + student.getName());
                        break;

                    case 6:

                        break;

                    case 7:
                        
                        break;

                    case 8:
                        exit = true; 
                        break;
                
                    default:
                        throw new IllegalArgumentException("Invalid input, number is not in menu!"); 
                }
            } catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
            }catch(Exception e){
                System.out.println("Invalid input!");
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

    public static void saveData(ArrayList<GradebookStudent> roster, ArrayList<GradeItem> grades, String path) throws IOException {
        PrintWriter out = new PrintWriter(path);

        
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
        
    }


}
