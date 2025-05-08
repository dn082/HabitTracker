//I have User Class but it has not been incorporated because its a local project
import java.util.ArrayList;

public class User {
    private String username; 
    private ArrayList<Habit> userHabits; // list of habits associated with the user

    public User(String username) { 
        this.username = username;
        this.userHabits = new ArrayList<>();
    }

    // Getter for username
    public String getUsername() {
        return username;
    }

    // Setter for username 
    public void setUsername(String username) {
        this.username = username;
    }
}