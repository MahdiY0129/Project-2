public class GradeItem {
    private String title; 
    private double score; 

    public GradeItem(String t, double s){

        if(s<0.0 && s>100.0) 
            throw new IllegalArgumentException("Invalid input, grade must be above 0!"); 
        if(t.trim() == null || t.trim().length() == 0) 
            throw new IllegalArgumentException("Invalid input, title can't be empty or null!");

            score = s; 
            title = t; 
        
    }    

    public String getTitle(){return title;}

    public double getScore(){return score;}

    public void setScore(double s){
        if(s<0.0 && s>100.0) 
            throw new IllegalArgumentException("Invalid input, grade must be above 0!");

        
        score = s; 
    
    }


}
