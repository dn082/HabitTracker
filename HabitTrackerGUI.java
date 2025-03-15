import javax.swing.*; //Imports JFrame, JPanel
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class HabitTrackerGUI {
    private JFrame frame;
    private JPanel panel;
    private JTextField habitInput;
    private DefaultListModel<String> habitListModel;
    private JList<String> habitList;
    private ArrayList<String> habits;


//MAIN METHOD
    public static void main(String[] args) {
        SwingUtilities.invokeLater(HabitTracker::new);
    }


    public HabitTracker() {
        frame = new JFrame("Habit Tracker"); //This create new 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        habits = new ArrayList<>();

        // Input panel for adding habits
        panel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        habitInput = new JTextField(20);
        JButton addButton = new JButton("Add Habit");
        addButton.addActionListener(e -> addHabit());
        inputPanel.add(habitInput);
        inputPanel.add(addButton);

        frame.add(inputPanel, BorderLayout.NORTH);

        // List to display habits
        habitListModel = new DefaultListModel<>();
        habitList = new JList<>(habitListModel);
        frame.add(new JScrollPane(habitList), BorderLayout.CENTER);

        // Button to mark habits as completed
        JButton completeButton = new JButton("Mark as Completed");
        completeButton.addActionListener(e -> completeHabit());
        frame.add(completeButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void addHabit() {
        String habit = habitInput.getText().trim();
        if (!habit.isEmpty()) {
            habits.add(habit);
            habitListModel.addElement(habit);
            habitInput.setText("");
        } else {
            JOptionPane.showMessageDialog(frame, "Please enter a habit!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void completeHabit() {
        int selectedIndex = habitList.getSelectedIndex();
        if (selectedIndex != -1) {
            String completedHabit = habitListModel.getElementAt(selectedIndex) + " (Completed)";
            habitListModel.set(selectedIndex, completedHabit);
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a habit to mark as completed!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

   
}
