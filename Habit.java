//these are all imports
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Habit { //start
    // these are the attributes for a habit
    private String name; // name 
    private int frequency; // frequency is int but case is 1 = daily, 2 = weekly, 3 = monthly
    private String comment;
    private String startDate; 
    private String endDate; 
    private String category; 
    private boolean isCompleted; 
    private List<Date> completionDates;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy"); // allows format change

    // this is a constructor
    public Habit(String name, String comment, int frequency, String startDate, String endDate, String category) {
        this.name = name;
        this.comment = comment;
        this.frequency = frequency;
        this.startDate = startDate;
        this.endDate = endDate;
        this.category = category;
        this.isCompleted = false; // if habit is not completed is deflaut
        this.completionDates = new ArrayList<>(); // Initialize the completionDates list
    }

    // these are setters of habit attributes
    public void setName(String name) {
        this.name = name;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public void setCompleted(boolean completed) {
        this.isCompleted = completed;
    }
    public void setCompletionDates(List<Date> completionDates) {
        this.completionDates = completionDates;
    }
    //end set

    // these are getters of habit attributes
    public String getName() {
        return name;
    }
    public String getComment() {
        return comment;
    }
    public int getFrequency() {
        return frequency;
    }
    public String getStartDate() {
        return startDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public String getCategory() {
        return category;
    }
    public boolean isCompleted() {
        return isCompleted;
    }
    public List<Date> getCompletionDates() { 
        return completionDates; 
    }
    // end get


    public void markCompleted() {
        this.isCompleted = true;
        this.completionDates.add(new Date()); 
    }

    public void markIncomplete() {
        this.isCompleted = false;
        if (!completionDates.isEmpty()) {
            this.completionDates.remove(this.completionDates.size() - 1); 
        }
    }

    public void resetHabit() {
        this.isCompleted = false;
    }

    public void clearCompletions() {
        this.completionDates.clear(); 
    }

    // method to display habit details
    public void displayDetails() {
        System.out.println("Habit: " + name);
        System.out.println("Comment: " + (comment != null ? comment : "None"));
        System.out.println("Frequency: " + (frequency == 1 ? "Daily" : frequency == 2 ? "Weekly" : "Monthly"));
        System.out.println("Category: " + category);
        System.out.println("Start Date: " + startDate);
        System.out.println("End Date: " + endDate);
        System.out.println("Completed: " + (isCompleted ? "Yes" : "No"));
        System.out.println();
    }
    
}//end