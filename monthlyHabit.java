//this is the SUBCLASS of Habit
//this is what monthly habit has
public class monthlyHabit extends Habit {
    public monthlyHabit(String name, String comment, String startDate, String endDate, String category) {
        super(name, comment, 3, startDate, endDate, category); //every 30 days is habit but case is 3
    }
}
