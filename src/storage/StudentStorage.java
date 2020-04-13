package storage;


import model.Lesson;
import model.Student;

public class StudentStorage {
    private Student[] students;
    private int size;

    public StudentStorage(int capacity) {
        students = new Student[capacity];
    }

    public StudentStorage() {
        students = new Student[16];
    }

    public void add(Student value) {
        if (size == students.length) {
            extend();
        }
        students[size++] = value;
    }

    private void extend() {
        Student[] arr = new Student[students.length + 10];
        System.arraycopy(students, 0, arr, 0, students.length);
        students = arr;
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.println(students[i]);
        }
    }

    public Student getByEmail(String email) {
        for (int i = 0; i < size; i++) {
            if (email.equals(students[i].getEmail())) {
                return students[i];
            }
        }
        return null;
    }
     public void printByLesson(Lesson byName){
         for (int i = 0; i < size; i++) {
             for (Lesson lesson: students[i].getLessons()) {
                 if (lesson.equals(byName)) {
                     System.out.println(students[i]);
                     break;
                 }
             }
         }
     }

}
