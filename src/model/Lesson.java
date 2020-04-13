package model;

public class Lesson {
    private String name;
    private int duration;
    private int price;
    private String lecturerName;

    public Lesson(String name, int duration, int price, String lecturerName) {
        this.name = name;
        this.duration = duration;
        this.price = price;
        this.lecturerName = lecturerName;
    }

    public Lesson() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lesson lesson = (Lesson) o;

        if (duration != lesson.duration) return false;
        if (price != lesson.price) return false;
        if (name != null ? !name.equals(lesson.name) : lesson.name != null) return false;
        return lecturerName != null ? lecturerName.equals(lesson.lecturerName) : lesson.lecturerName == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + duration;
        result = 31 * result + price;
        result = 31 * result + (lecturerName != null ? lecturerName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "name='" + name + '\'' +
                ", duration=" + duration +
                ", price=" + price +
                ", lecturerName='" + lecturerName + '\'' +
                '}';
    }

}
