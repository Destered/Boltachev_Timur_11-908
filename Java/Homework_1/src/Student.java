import java.util.Comparator;

public class Student {
    String name;
    int age;
    int course;

    public Student(String name, int age, int course) {
        this.name = name;
        this.age = age;
        this.course = course;
    }

    @Override
    public String toString() {
        return name + " " + age + " лет " + course + " курс";
    }

    static Comparator<Student> comparatorName = Comparator.comparing(o -> o.name);
    static Comparator<Student> comparatorAge = Comparator.comparingInt(o -> o.age);
    static Comparator<Student> comparatorCourse = Comparator.comparingInt(o -> o.course);
}
