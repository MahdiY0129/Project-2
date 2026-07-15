import java.util.Scanner; 

public class GradebookApp {
    public static void main(String[] args) {
        boolean exit = false;
        System.out.println("==== Gradebook Manager ===="); 
        Scanner sc = new Scanner(System.in); 
        
        while(exit == false){
            try{
                printMenu(); 
                int choice = sc.nextInt(); 

                switch (choice) {
                    case 1:
                        
                        break;

                    case 2:

                        break;
                        
                    case 3:
                        
                        break;

                    case 4:

                        break;
                    
                    case 5:
                        
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
        System.out.println("Enter choice: ");
    }
}
