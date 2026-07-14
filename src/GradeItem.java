public class GradeItem {
    private String title; 
    private double score; 

    public GradeItem(String t, double s){

        if(s<0.0 && s>100.0) 
            throw new IllegalArgumentException("Invalid input, grade must be above 0!"); 
        if(t.trim() == null || t.trim().length() == 0) 
            throw new IllegalArgumentException("Invalid input, title can't be empty or null!");


        try{
            score = s; 
            title = t; 
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }    

    public String getTitle(){return title;}

    public double getScore(){return score;}

    public void setScore(double s){
        if(s<0.0 && s>100.0) 
            throw new IllegalArgumentException("Invalid input, grade must be above 0!");

        try{
            score = s; 
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }


}
