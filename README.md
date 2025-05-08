# HabitTracker (Java Swing GUI Application)

Description: For this project, this is a Habit Tracker application made with Java, and it has a graphical user interface (GUI) built using Swing. The system enables users to set, keep up with/manage, and track progress on habits they create. 


Features
- Create Habits: Input habit details like name, comment, frequency, start date, end date, and category.
- Edit Habits: Ability to edit habit details like name, comment, frequency, start date, end date, and category.
- View Habit Info: Display all stored habit details.
- Track Progress: Check off habits you completed and visualize progress.
- Delete Habits: Remove unnecessary habits.
- Reset Progress: Ability to remove all habits and progress

Installation & Running Instructions - To Run the Project
1. Download the zip or clone the repo.
2. Open the zip.
3. Open all files in Visual Studio Code.
4. Locate “HabitTrackerGUI.java”.
5. Run “HabitTrackerGUI.java”.
6. The Habit Tracker application will launch after running.

To Operate the Project
Main Functions
- Create a Habit
  - Habit name 
  - Habit comment 
  - Habit frequency
  - Habit start date 
  - Habit end date 
  - Habit category 
  - Use “Save” to create a habit.

- Edit Habit
  - Select an existing habit.
  - Ability to rename habit name
  - Ability to modify comment
  - Adjust frequency
  - Modify start and end dates.
  - Ability to Rename Category 
  - Use “Save” to update habit.
 
Other Functions
- View Habit Info: Click “Info” to display all details.
- Check Habit: Click “Check” to mark the habit as completed.  (affect progress)
- Uncheck Habit**: Click “Uncheck” to remove completion for the day (affect progress)
- Show Progress: Click “Show Progress” to view weekly progress
 - Resets based on cycle:
    - Daily: Resets in 1 day.
    - Weekly: Resets in 7 days.
    - Monthly: Resets in 30 days.
    - If progress reaches zero, the habit is automatically deleted.
- Delete Habit: Removes the selected habit from the tracker.
- Reset All Progress: Deletes all habits and their tracking data.

Technologies Used
- Java (Main language)
- Swing (Graphical User Interface)
- ActionListeners (User interactions)


















