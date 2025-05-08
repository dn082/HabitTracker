//RUN THIS FILE.

//all the imports i need
import javax.swing.*; //for JFrame, JButton, JLabel, JTextField, etc
import java.awt.*;
import java.util.ArrayList; 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

//My HabitTracker is a Graphical User Interface 
public class HabitTrackerGUI extends JFrame { // start
    private ArrayList<Habit> habits;
    //3 SEPERATE PANEL TO CATORGORISE HABITS
    private JPanel dailyPanel; 
    private JPanel weeklyPanel;
    private JPanel monthlyPanel;

    //main method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HabitTrackerGUI());
    }

    public HabitTrackerGUI() {
        //TOP PART OF APP
        setTitle("Habit Tracker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // for x out
        setSize(800, 800);
        setLayout(new BorderLayout());
    
        habits = new ArrayList<>(); // creates the arraylist of habits

        //TOP PART HAS TITLE LABEL
        JLabel titleLabel = new JLabel("WELCOME TO MY HABIT TRACKER APPLICATION", JLabel.CENTER);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setOpaque(true); // makes color background
        titleLabel.setBackground(new Color(0x004aad));
        add(titleLabel, BorderLayout.NORTH);

        // The Center Section GUI is the Habit Panels 
        // Shows the Daily, Weekly, and Monthy
        JTabbedPane habitTabbedPane = new JTabbedPane();

        //DAILYPANEL
        dailyPanel = new JPanel();
        dailyPanel.setLayout(new BoxLayout(dailyPanel, BoxLayout.Y_AXIS));
        dailyPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JScrollPane dailyScrollPane = new JScrollPane(dailyPanel);
        habitTabbedPane.addTab("Daily", dailyScrollPane);

        //WEEKLYPANEL
        weeklyPanel = new JPanel();
        weeklyPanel.setLayout(new BoxLayout(weeklyPanel, BoxLayout.Y_AXIS));
        weeklyPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JScrollPane weeklyScrollPane = new JScrollPane(weeklyPanel);
        habitTabbedPane.addTab("Weekly", weeklyScrollPane);

        //MONTHYPANEL
        monthlyPanel = new JPanel();
        monthlyPanel.setLayout(new BoxLayout(monthlyPanel, BoxLayout.Y_AXIS));
        monthlyPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JScrollPane monthlyScrollPane = new JScrollPane(monthlyPanel);
        habitTabbedPane.addTab("Monthly", monthlyScrollPane);
        add(habitTabbedPane, BorderLayout.CENTER);

        // The Bottom Part of GUI has Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(0x004aad));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //CREATE HABIT BUTTON
        JButton createHabitButton = new JButton("Create Habit");
        createHabitButton.addActionListener(e -> openCreateHabitDialog());
        buttonPanel.add(createHabitButton);

        //EDIT HABIT BUTTON
        JButton editHabitButton = new JButton("Edit Habit");
        editHabitButton.addActionListener(e -> openEditHabitDialog());
        buttonPanel.add(editHabitButton);

        //SHOW PROGRESS BUTTOM
        JButton showProgressButton = new JButton("Show Progress");
        showProgressButton.addActionListener(e -> showWeeklyProgress()); 
        buttonPanel.add(showProgressButton);

        //RESET PROGRESS BUTTOM
        JButton resetProgressButton = new JButton("Reset All Progress");
        resetProgressButton.addActionListener(e -> resetAllProgress()); 
        buttonPanel.add(resetProgressButton);

        // the panel will be on the bottom of the page
        add(buttonPanel, BorderLayout.SOUTH);

        updateHabitDisplay(); // display of habits
        setVisible(true);
    }

    //FOR CREATE HABIT BUTTON DIALOG - for when you click Create Habit
    private void openCreateHabitDialog() {
        JDialog dialog = new JDialog(this, "Create Habit", true);
        dialog.setSize(400, 400);
        dialog.setLayout(new GridLayout(7, 2, 5, 5));
        dialog.setLocationRelativeTo(this);

        JTextField habitNameField = new JTextField();
        JTextField commentField = new JTextField();
        JComboBox<String> frequencyBox = new JComboBox<>(new String[]{"Daily", "Weekly", "Monthly"});
        JTextField startDateField = new JTextField();
        JTextField endDateField = new JTextField();
        JTextField categoryField = new JTextField();

        //make spaces for so its not on the right edge of dialog
        dialog.add(new JLabel("   Habit Name:"));
        dialog.add(habitNameField);
        dialog.add(new JLabel("   Comment:"));
        dialog.add(commentField);
        dialog.add(new JLabel("   Frequency:"));
        dialog.add(frequencyBox);
        dialog.add(new JLabel("   Start Date (MM-DD-YYYY):"));
        dialog.add(startDateField);
        dialog.add(new JLabel("   End Date (MM-DD-YYYY):"));
        dialog.add(endDateField);
        dialog.add(new JLabel("   Category:"));
        dialog.add(categoryField);

        //Save Button
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            try {
                String name = habitNameField.getText().trim();
                String comment = commentField.getText().trim();
                String frequencyStr = (String) frequencyBox.getSelectedItem();
                int frequency = switch (frequencyStr) {
                    case "Daily" -> 1;
                    case "Weekly" -> 2;
                    case "Monthly" -> 3;
                    default -> throw new IllegalArgumentException("Invalid frequency");
                };
                String startDate = startDateField.getText().trim();
                String endDate = endDateField.getText().trim();
                String category = categoryField.getText().trim();

                if (!name.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty() && !category.isEmpty()) {
                    SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
                    sdf.setLenient(false); 
                    sdf.parse(startDate);
                    sdf.parse(endDate);

                    Habit habit = new Habit(name, comment, frequency, startDate, endDate, category);
                    habits.add(habit);
                    updateHabitDisplay();
                    JOptionPane.showMessageDialog(dialog, "Habit created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Please fill out all fields!", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(dialog, "Invalid date format (MM-DD-YYYY)!", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(dialog, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        dialog.add(saveButton);

        //Cancel Button
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dialog.dispose());
        dialog.add(cancelButton);

        dialog.setVisible(true);
    }

    //FOR EDIT BUTTON - for when you click Edit Habit
        private void openEditHabitDialog() {
            // if there are no habits
            if (habits.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No habits to edit.", "Info", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            //When you click Edit Habit it will lead you to this
            // Select a habit to edit Dialog
            JDialog selectDialog = new JDialog(this, "Select Habit to Edit", true);
            selectDialog.setSize(400, 150);
            selectDialog.setLayout(new FlowLayout());
            selectDialog.setLocationRelativeTo(this);
            
            //the list of habits
            JComboBox<String> habitList = new JComboBox<>();
            for (Habit habit : habits) {
                habitList.addItem(habit.getName());
            }
            selectDialog.add(new JLabel("Select Habit:"));
            selectDialog.add(habitList);
            
            //Select Button
            JButton selectButton = new JButton("Select");
            selectDialog.add(selectButton);
        
            selectButton.addActionListener(e -> {
                String selectedHabitName = (String) habitList.getSelectedItem();
                Habit habitToEdit = null;
                for (Habit habit : habits) {
                    if (habit.getName().equals(selectedHabitName)) {
                        habitToEdit = habit;
                        break;
                    }
                }
                selectDialog.dispose();
                if (habitToEdit != null) {
                    openEditHabitDetailsDialog(habitToEdit);
                }
            });

            //Cancel Button
            JButton cancelButton = new JButton("Cancel");
            cancelButton.addActionListener(e -> selectDialog.dispose());
            selectDialog.add(cancelButton);
        
            selectDialog.setVisible(true);
        }

        //When you click Edit Habit and then Select a Habit
        private void openEditHabitDetailsDialog(Habit habitToEdit) {
            JDialog dialog = new JDialog(this, "Edit Habit", true);
            dialog.setSize(400, 300);
            dialog.setLayout(new GridLayout(7, 2, 5, 5));
            dialog.setLocationRelativeTo(this);
        
            JTextField habitNameField = new JTextField(habitToEdit.getName());
            JTextField commentField = new JTextField(habitToEdit.getComment());
            JComboBox<String> frequencyBox = new JComboBox<>(new String[]{"Daily", "Weekly", "Monthly"});
            frequencyBox.setSelectedIndex(habitToEdit.getFrequency() - 1); // Set the current frequency
            JTextField startDateField = new JTextField(habitToEdit.getStartDate());
            JTextField endDateField = new JTextField(habitToEdit.getEndDate());
            JTextField categoryField = new JTextField(habitToEdit.getCategory());
        
            dialog.add(new JLabel("   Habit Name:"));
            dialog.add(habitNameField);
            dialog.add(new JLabel("   Comment:"));
            dialog.add(commentField);
            dialog.add(new JLabel("   Frequency:"));
            dialog.add(frequencyBox);
            dialog.add(new JLabel("   Start Date (MM-DD-YYYY):"));
            dialog.add(startDateField);
            dialog.add(new JLabel("   End Date (MM-DD-YYYY):"));
            dialog.add(endDateField);
            dialog.add(new JLabel("   Category:"));
            dialog.add(categoryField);
            
            //Save Button
            JButton saveButton = new JButton("Save");
            saveButton.addActionListener(e -> {
                try {
                    String name = habitNameField.getText().trim();
                    String comment = commentField.getText().trim();
                    String frequencyStr = (String) frequencyBox.getSelectedItem();
                    int frequency = switch (frequencyStr) {
                        case "Daily" -> 1;
                        case "Weekly" -> 2;
                        case "Monthly" -> 3;
                        default -> throw new IllegalArgumentException("Invalid frequency");
                    };
                    String startDate = startDateField.getText().trim();
                    String endDate = endDateField.getText().trim();
                    String category = categoryField.getText().trim();
        
                    if (!name.isEmpty() && !startDate.isEmpty() && !endDate.isEmpty() && !category.isEmpty()) {
                        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
                        sdf.setLenient(false);
                        sdf.parse(startDate);
                        sdf.parse(endDate);
        
                        habitToEdit.setName(name);
                        habitToEdit.setComment(comment);
                        habitToEdit.setFrequency(frequency);
                        habitToEdit.setStartDate(startDate);
                        habitToEdit.setEndDate(endDate);
                        habitToEdit.setCategory(category);
                        //when habit is filled
                        updateHabitDisplay();
                        JOptionPane.showMessageDialog(dialog, "Habit updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        dialog.dispose();
                    } else { //fill in all the info for habit
                        JOptionPane.showMessageDialog(dialog, "Please fill out all fields!", "Input Error", JOptionPane.ERROR_MESSAGE);
                    }   //invalid dates/error
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(dialog, "Invalid date format (MM-DD-YYYY)!", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(dialog, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
            dialog.add(saveButton);
            
            //Cancel Button
            JButton cancelButton = new JButton("Cancel");
            cancelButton.addActionListener(e -> dialog.dispose());
            dialog.add(cancelButton);
        
            dialog.setVisible(true);
        }


    // FOR SHOW PROGRESS BUTTON
    private void showWeeklyProgress() {
        // if there are no habits
        if (habits.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No habits to show progress for.", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        //the title is Weekly Habit Progress and this is the dialog for it
        JDialog progressDialog = new JDialog(this, "Weekly Habit Progress", true);
        progressDialog.setSize(300, 200); 
        progressDialog.setLayout(new BoxLayout(progressDialog.getContentPane(), BoxLayout.Y_AXIS));
        progressDialog.setLocationRelativeTo(this);

        int currentWeek = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        for (Habit habit : habits) {
            long completionsThisWeek = habit.getCompletionDates().stream()
                    .filter(date -> {
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(date);
                        return cal.get(Calendar.WEEK_OF_YEAR) == currentWeek && cal.get(Calendar.YEAR) == currentYear;
                    })
                    .count();

            progressDialog.add(new JLabel(habit.getName() + ": Completed " + completionsThisWeek + " times this week."));
        }

        progressDialog.add(Box.createVerticalStrut(10)); // make space between close and progressinfo
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> progressDialog.dispose());
        progressDialog.add(closeButton);

        progressDialog.setVisible(true);
    }

    //DELETES ALL HABITS FOR DAILY, WEEKLY, AND MONTHLY and gets rid of Show Progress Info
    private void resetAllProgress() {
        // Ask user if want to erase all their habits and their progress.
        int userQuestion = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete all habits and their progress?",
                "Confirm to Reset All",
                JOptionPane.YES_NO_OPTION
        );
    
        // if the user says yes then all the habits and progress will get removed
        if (userQuestion == JOptionPane.YES_OPTION) {
            habits.clear(); // removes all habits
            updateHabitDisplay(); // Update the display to show no habits.
            JOptionPane.showMessageDialog(
                    this,
                    "All habits and their progress have been deleted.",
                    "Reset Successful",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }


    // THIS MAKES THE HABIT ITEM PANEL FOR WHEN A HABIT IS MADE
    private JPanel createHabitItemPanel(Habit habit) {
        JPanel habitItemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        habitItemPanel.setBackground(Color.WHITE);
        habitItemPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        JLabel habitLabel = new JLabel(habit.getName() + " (Category: " + habit.getCategory() + ")");
        habitItemPanel.add(habitLabel);

        JButton checkButton = new JButton(habit.isCompleted() ? "Uncheck" : "Check");
        checkButton.addActionListener(e -> checkHabitCompletion(habit, checkButton));
        habitItemPanel.add(checkButton);

        JButton infoButton = new JButton("Info");
        infoButton.addActionListener(e -> {
        //Shown in Terminal When Info is clicked for a habit
        System.out.println("Info Button Clicked for: " + habit.getName() ); 
        habit.displayDetails(); 
        //Shown in GUI
        String info = "Habit: " + habit.getName() +
        "\nComment: " + (habit.getComment() != null ? habit.getComment() : "None") +
        "\nFrequency: " + (habit.getFrequency() == 1 ? "Daily" : habit.getFrequency() == 2 ? "Weekly" : "Monthly") +
        "\nCategory: " + habit.getCategory() +
        "\nStart Date: " + habit.getStartDate() +
        "\nEnd Date: " + habit.getEndDate() +
        "\nCompleted: " + (habit.isCompleted() ? "Yes" : "No");

        JOptionPane.showMessageDialog(null, info, "Habit Info", JOptionPane.INFORMATION_MESSAGE);
    });
    
    //This is the DELETE BUTTON to delete a habit
    JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete habit '" + habit.getName() + "' ?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) {
                habits.remove(habit);
                updateHabitDisplay();
            }
        });
        habitItemPanel.add(deleteButton);

        //this is the Info button that will show up right next to the Delete
        habitItemPanel.add(infoButton);
    
    // This show the amount of time between the dates you inputted (example: __ days)
    JLabel remainingTimeLabel = new JLabel(getRemainingTimeText(habit));
    habitItemPanel.add(remainingTimeLabel);

    return habitItemPanel;
    }


    //For Check and Uncheck
    private void checkHabitCompletion(Habit habit, JButton checkButton) {
        if (habit.isCompleted()) {
            habit.markIncomplete(); 
            checkButton.setText("Check");
        } else {
            habit.markCompleted(); 
            checkButton.setText("Uncheck");
        }
    }

    //Updates Panel
    private void updateHabitDisplay() {
        //clear up habits
        dailyPanel.removeAll();
        weeklyPanel.removeAll();
        monthlyPanel.removeAll();
    
        //loop through habits
        for (int i = 0; i < habits.size(); i++) {
            Habit habit = habits.get(i);
            //make panel
            JPanel habitItemPanel = createHabitItemPanel(habit); 
            //position it
            habitItemPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
            //put the habit in its right frequency
            switch (habit.getFrequency()) {
                case 1: // Daily
                    dailyPanel.add(habitItemPanel);
                    break;
                case 2: // Weekly
                    weeklyPanel.add(habitItemPanel);
                    break;
                case 3: // Monthly
                    monthlyPanel.add(habitItemPanel);
                    break;
            }
            //if this is not the only last habit add space between it
            if (i < habits.size() - 1) {
                //space between habits
                dailyPanel.add(Box.createVerticalStrut(10)); 
                weeklyPanel.add(Box.createVerticalStrut(10));
                monthlyPanel.add(Box.createVerticalStrut(10));
            }
        }
        // Refresh and update the user unterface
        dailyPanel.revalidate(); 
        dailyPanel.repaint(); 
        weeklyPanel.revalidate();
        weeklyPanel.repaint();
        monthlyPanel.revalidate();
        monthlyPanel.repaint();
    }

    //USED TO CALCULATE THE AMOUNT OF TIME LEFT UNTIL HABITS END DATE
    private String getRemainingTimeText(Habit habit) {
        try {
        // use format
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("MM-dd-yyyy");
        //turn the string into a Date for getEndDate() from Habit class
        Date endDate = sdf.parse(habit.getEndDate());
        //use todays date
        Date today = new Date();

        //Use Calendat to reset both endCal and todayCal to midnight
        java.util.Calendar endCal = java.util.Calendar.getInstance();
        endCal.setTime(endDate);
        endCal.set(java.util.Calendar.HOUR_OF_DAY, 0);
        endCal.set(java.util.Calendar.MINUTE, 0);
        endCal.set(java.util.Calendar.SECOND, 0);
        endCal.set(java.util.Calendar.MILLISECOND, 0);

        java.util.Calendar todayCal = java.util.Calendar.getInstance();
        todayCal.setTime(today);
        todayCal.set(java.util.Calendar.HOUR_OF_DAY, 0);
        todayCal.set(java.util.Calendar.MINUTE, 0);
        todayCal.set(java.util.Calendar.SECOND, 0);
        todayCal.set(java.util.Calendar.MILLISECOND, 0);

        //getTimeinMillis is a Calendar class date
        //get difference between the two dates in milliseconds
        long timediffinMillis = endCal.getTimeInMillis() - todayCal.getTimeInMillis();

        // turn millis to days
        // millis/milliseconds in a day to calculate days left
        long daysLeft = (timediffinMillis / (24 * 60 * 60 * 1000)) + 1;

        //get frequency of habit
        int frequency = habit.getFrequency();

        if (daysLeft < 0) {
            return "Habit Not Valid";
        }
        // use frequency to tell which way to format it
        switch (frequency) {
            case 1: // Daily
                return daysLeft + " days left";
            case 2: // Weekly
                long weeksLeft = daysLeft / 7;
                long remainingDays = daysLeft % 7;
                return weeksLeft + " weeks left and " + remainingDays + " days";
            case 3: // Monthly
                long monthsLeft = daysLeft / 30;
                long remainingDaysForMonth = daysLeft % 30;
                return monthsLeft + " months left and " + remainingDaysForMonth + " days";
            default:
                return "";
        }
    } catch (java.text.ParseException e) {
        return "error calculating remaining time";
    }
    }

    //for cycle refresh and removing
    private void cycleHabits() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy"); // format
        String todaydate = sdf.format(new Date()); // Date to String
    
        //var to removehabits
        Habit habitToRemove = null;
    
        for (Habit habit : habits) {
            if (!habit.isCompleted()) { // If unchecked
                try {
                    //note the setTime() and getTime() is part of the Date import
                    Date startDate = sdf.parse(habit.getStartDate()); //String to Date
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(startDate); //cal has startdate
    
                    // go to next cycle based on frequency of habit
                    switch (habit.getFrequency()) {
                        case 1: calendar.add(Calendar.DAY_OF_YEAR, 1); break;
                        case 2: calendar.add(Calendar.WEEK_OF_YEAR, 1); break;
                        case 3: calendar.add(Calendar.MONTH, 1); break;
                    }
    
                    String cycle = sdf.format(calendar.getTime()); // cycle to String
    
                    if (cycle.compareTo(todaydate) <= 0) {
                        habit.setCompleted(true);
    
                        // if habit has reached end date plan to remove
                        Date endDate = sdf.parse(habit.getEndDate());
                        if (endDate.before(new Date())) {
                            habitToRemove = habit;
                        }
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        // remove habit
        if (habitToRemove != null) {
            habits.remove(habitToRemove);
        }
        // refresh
        updateHabitDisplay();
    }
} // end








