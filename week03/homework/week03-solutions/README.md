# Expeditors - Java Academy

## Week 03 Homework

<u>Task 01</u>

* Create a Java application that allows the user to enter a list of students and their test scores.  Once entered, users can interact with a menu that allows them to make specific queries of the data.  An example trace of your program's console might be:

```
How many students do you want to process scores for? 6
Enter a student name and score separated by a comma.
Enter student #1 name and score: Ian,85
Enter student #2 name and score: Ezra,91
Enter student #3 name and score: Elisha,78
Enter student #4 name and score: Siddalee,68
Enter student #5 name and score: Pursalane,88
Enter student #6 name and score: Zephaniah,100
Menu:
1 - Display the average test score
2 - Display the student with the highest score
3 - Display the student with the lowest score
4 - Quit
Enter command: 2
Zephaniah had a score of 100
Enter command: 3
Siddalee had a score of 68
Enter command: 1
The average of all 6 scores is 85
Enter command: 4
```

<u>Task 02</u>

* Enhance your solution for task 01 to include the following:
  * Handle cases where input contains extraneous spaces - `   Joe   , 74 ` should be processed as `Joe,74`
  * Add a fifth option to the menu that displays the student data in order of grade value (highest to lowest)
  * Integrate the letter grade logic to display letter grade that goes along with the numeric score for a student
  * Add JUnit tests to validate that each of the menu options (average, highest, lowest, sorted) is working correctly
