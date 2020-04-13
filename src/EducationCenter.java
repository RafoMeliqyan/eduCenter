import exception.DuplicateLessonException;
import model.Lesson;
import model.Student;
import storage.LessonStorage;
import storage.StudentStorage;

import java.util.Scanner;

public class EducationCenter implements Commands {

    public static LessonStorage lessonStorage = new LessonStorage();
    public static StudentStorage studentStorage = new StudentStorage();

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean isRun = true;
        while (isRun) {
            printCommands();
            int command;
            try {
                command = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please input number");
                command = -1;
            }
            switch (command) {
                case EXIT:
                    System.out.println("Bye!!!");
                    isRun = false;
                    break;
                case ADD_STUDENT:
                    addStudent();
                    break;
                case ADD_LESSON:
                    addLesson();
                    break;
                case PRINT_STUDENTS:
                    studentStorage.print();
                    break;
                case PRINT_LESSONS:
                    lessonStorage.print();
                    break;
                case CHANGE_STUDENT_LESSON:
                    changeStudentLesson();
                    break;
                case PRINT_STUDENTS_BY_LESSON_NAME:
                    printStudentsByLessonName();
                    break;
                default:
                    System.out.println("Wrong command");
            }
        }
    }


    private static void addLesson() {
        System.out.println("Import lessons data (name, duration,price,lectureName)");
        try {
            String lessonDataStr = scanner.nextLine();
            String[] lessonData = lessonDataStr.split(",");
            Lesson byName = lessonStorage.getByName(lessonData[0]);
//            if (byName != null) {
//                System.out.println("Duplicate Lesson");
//                addLesson();
//            } else {
                Lesson lesson = new Lesson();
                lesson.setName(lessonData[0]);
                lesson.setDuration(Integer.parseInt(lessonData[1]));
                lesson.setPrice(Integer.parseInt(lessonData[2]));
                lesson.setLecturerName(lessonData[3]);
                lessonStorage.add(lesson);
                System.out.println("Lesson added!!!");
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e){
            System.out.println("Incorrect value! Please try again");
            addLesson();
        } catch (DuplicateLessonException e){
            System.out.println(e.getMessage());
        }

    }

    private static void addStudent() {
        if (lessonStorage.isEmpty()) {
            System.out.println("Please add lesson first");
            return;
        }
        try {
            Lesson[] lessons = chooseLessons();
            if (lessons.length > 0) {
                System.out.println("Import student data (name, surname,phone,email)");
                String stud = scanner.nextLine();
                Student student = new Student();
                String[] addStud = stud.split(",");
                Student byEmail = studentStorage.getByEmail(addStud[3]);
                if (byEmail != null) {
                    System.out.println("Duplicate Student!");
                    addStudent();
                } else {
                    student.setLessons(lessons);
                    student.setName(addStud[0]);
                    student.setSurname(addStud[1]);
                    student.setPhone(Integer.parseInt(addStud[2]));
                    student.setEmail(addStud[3]);
                    studentStorage.add(student);
                    System.out.println("Student added!!!");
                }
            }
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Invalid Data! please try again");
            addStudent();
        }

    }

    private static void changeStudentLesson() {
        System.out.println("Import name of the email");
        String changeStudent = scanner.nextLine();
        Student student = studentStorage.getByEmail(changeStudent);
        if (student == null) {
            System.out.println("Wrong name!");
            changeStudentLesson();
        } else {
            Lesson[] lessons = chooseLessons();
            if (lessons.length > 0) {
                student.setLessons(lessons);
                System.out.println("Student lessons are changed!");
            }
        }
    }

    private static Lesson[] chooseLessons() {
        System.out.println("Please choose Lessons from list");
        lessonStorage.print();
        String lessonsStr = scanner.nextLine();
        String[] lessonNames = lessonsStr.split(",");
        Lesson[] lessons = new Lesson[lessonNames.length];
        int i = 0;
        for (String lessonName : lessonNames) {
            Lesson byName = lessonStorage.getByName(lessonName);
            if (byName != null) {
                lessons[i++] = byName;
            }
        }
        return lessons;
    }

    private static void printStudentsByLessonName() {
        String lessonName = scanner.nextLine();
        Lesson byName = lessonStorage.getByName(lessonName);
        if (byName == null) {
            System.out.println("Wrong lesson name!");
            printStudentsByLessonName();
        } else {
            studentStorage.printByLesson(byName);
        }
    }

    private static void printCommands() {
        System.out.println("Import " + EXIT + " for EXIT");
        System.out.println("Import " + ADD_STUDENT + " for ADD STUDENT");
        System.out.println("Import " + ADD_LESSON + " for ADD LESSON");
        System.out.println("Import " + PRINT_STUDENTS + " for PRINT STUDENTS");
        System.out.println("Import " + PRINT_LESSONS + " for PRINT LESSONS");
        System.out.println("Import " + CHANGE_STUDENT_LESSON + " for CHANGE STUDENT LESSONS");
        System.out.println("Import " + PRINT_STUDENTS_BY_LESSON_NAME + " for PRINT STUDENTS BY LESSON NAME");
    }

}
