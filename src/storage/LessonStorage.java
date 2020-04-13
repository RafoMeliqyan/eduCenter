package storage;


import exception.DuplicateLessonException;
import model.Lesson;

public class LessonStorage {
    private Lesson[] lessons;
    private int size = 0;


    public LessonStorage(int capacity) {
        lessons = new Lesson[capacity];
    }

    public LessonStorage() {
        lessons = new Lesson[16];
    }

    public void add(Lesson value) throws DuplicateLessonException {
        if (getByName(value.getName()) != null) {
            throw new DuplicateLessonException("Lesson with name "+ value.getName() + " already exists");
        }
        if (size == lessons.length) {
            extend();
        }
        lessons[size++] = value;
    }

    private void extend() {
        Lesson[] arr = new Lesson[lessons.length + 10];
        System.arraycopy(lessons, 0, arr, 0, lessons.length);
        lessons = arr;
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.println(lessons[i]);
        }
    }


    public Lesson getByName(String name) {
        for (int i = 0; i < size; i++) {
            if (name.equals(lessons[i].getName())) {
                return lessons[i];
            }
        }
        return null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

}
