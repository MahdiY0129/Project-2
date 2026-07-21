import java.util.Scanner; 
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class GradebookApp {
    public static void main(String[] args) {
        boolean exit = false; // Boolean for the while loop
        System.out.println("==== Gradebook Manager ===="); 
        System.out.println("");
        Scanner sc = new Scanner(System.in); 
        GradebookManager manager = new GradebookManager(); 
        
        while(exit == false){
            try{ // Sets up a try and catch to catch any valdiation errors, IO exceptions, or scanner exceptions
                printMenu(); 
                int choice = sc.nextInt();
                sc.nextLine(); 

                switch (choice) {//Switch for the main menu
                    case 1:
                        System.out.print("Enter student name and id in this format (id, name): ");
                        String input = sc.nextLine().trim();

                        String[] parts = input.split(","); 
                        int id = Integer.parseInt(parts[0].trim()); 
                        String name = parts[1].trim(); 
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
                        else System.out.printf("Found:%n%d - %s%nAverage: %.2f%n", student.getId(), student.getName(), student.calculateAverage());
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
                    
                    case 9:
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
                    
                    default:
                        throw new IllegalArgumentException("Invalid input, number is not in menu!"); 
                }
            }catch(IllegalArgumentException e){//Catches the possible and common exceptions
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

    public static void printMenu(){//helper method to to print out the menu instead of having it in the main method
        System.out.println("1. Add Student");
        System.out.println("2. Add Grade to Student");
        System.out.println("3. View All Students");
        System.out.println("4. View Student Details");
        System.out.println("5. Search Student by ID");
        System.out.println("6. Load Data from File");
        System.out.println("7. Save Data to File");
        System.out.println("8. Exit");
        System.out.println("9. Search Student by Name");
        System.out.print("Enter choice: ");
    }

    public static void saveData(GradebookManager manager, String path) throws IOException { //Saves data or prints the student array into a file
        ArrayList<GradebookStudent> roster = manager.getStudents();
        PrintWriter out = new PrintWriter(path);

        if(roster.isEmpty()) {//Validator to make sure that there is actually any data in the roster
            out.close();
            throw new IllegalArgumentException("No gradebook data to save yet. Add a student or load data from a file before saving."); 
        }


        for(GradebookStudent student : roster) {//Loops through and prints the information into a file
            out.println("STUDENT," + student.getId() + "," + student.getName());
            for(GradeItem grade : student.getGrades()) {
                out.println("GRADE," + student.getId() + "," + grade.getTitle() + "," + grade.getScore());
            }
        }

        out.close();
    }

    public static void loadData(String path, GradebookManager manager){//loads the data from the file onto an aray
        File file = new File(path); 
        Scanner input; 
        try{//sets up scanner and handles FNF exception
            input = new Scanner(file); 
        }catch(FileNotFoundException e){
            System.out.println("Could not find file:" + path);
            System.out.println("Gradebook was not changed.");
            return; 
        }

        int studentsLoaded = 0;
        int gradesLoaded = 0;

        while(input.hasNextLine()){
            String line = input.nextLine(); 

            if(line.trim().isEmpty()){
                continue;
            }

            String[] parts = line.split(","); 
            
            try{//parses the data and adds it to the manager object 
                if(parts[0].equalsIgnoreCase("student")){
                    int id = Integer.parseInt(parts[1].trim()); 
                    String name = parts[2].trim(); 
                    manager.addStudent(new GradebookStudent(id, name));
                    studentsLoaded++; 
                }
                else if(parts[0].equalsIgnoreCase("Grade")){
                    int id = Integer.parseInt(parts[1].trim());
                    String title = parts[2].trim(); 
                    double score = Double.parseDouble(parts[3].trim()); 
                    manager.addGradeToStudent(id, title, score);
                    gradesLoaded++; 
                }
                else {
                    System.out.println("Skipping unrecognized line:" + line);
                }
            }catch(Exception e){//if any exceptions occur then there was a bad line
                System.out.println("Skipping malformed line: " + line);
            }
            

        }
        System.out.println("Data loaded successfully.");//prints out only if the load was succesfull and prints out the students and grades loaded
        System.out.println("Students loaded: " + studentsLoaded);
        System.out.println("Grades loaded: " + gradesLoaded);
        input.close();
        
    }


}
