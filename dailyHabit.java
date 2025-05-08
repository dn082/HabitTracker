//this is the SUBCLASS of Habit
//is this what a daily habit has
public class dailyHabit extends Habit {
    public dailyHabit(String name, String comment, String startDate, String endDate, String category) {
        super(name, comment, 1, startDate, endDate, category); // every 1 day for daily habits but case is 1
    }
}
