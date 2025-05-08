//this is the SUBCLASS of Habit
//this is what weekly habit is
public class weeklyHabit extends Habit {
    public weeklyHabit(String name, String comment, String startDate, String endDate, String category) {
        super(name, comment, 2, startDate, endDate, category); // every 7 days for weekly habits but case is 2
    }
}

