import java.util.ArrayList;
import java.util.PriorityQueue;

public class core {
    public static ArrayList<Student> init(){
        ArrayList<Student> std = new ArrayList<>();
        std.add(new Student("Аадислав",22,2));
        std.add(new Student("Блад",23,4));
        std.add(new Student("Влег",24,4));
        std.add(new Student("Глава",19,1));
        std.add(new Student("Демён",17,3));
        std.add(new Student("Еихаил",25,3));
        return std;
    }

    public static void main(String[] args) {
        ArrayList<Student> students = init();
        PriorityQueue<Student> queueAge = new PriorityQueue<>(Student.comparatorAge);
        PriorityQueue<Student> queueCourse = new PriorityQueue<>(Student.comparatorCourse);
        PriorityQueue<Student> queueName = new PriorityQueue<>(Student.comparatorName);
        for (Student student : students) {
            queueAge.offer(student);
            queueCourse.offer(student);
            queueName.offer(student);
        }

        System.out.println("Сортировка по возрасту:");
        while(true){
            Student stud = queueAge.poll();
            if(stud == null) break;
            System.out.println("    Студент: " + stud);
        }
        System.out.println();
        System.out.println("Сортировка по имени:");
        while(true){
            Student stud = queueName.poll();
            if(stud == null) break;
            System.out.println("    Студент: " + stud);
        }
        System.out.println();
        System.out.println("Сортировка по курсу:");
        while(true){
            Student stud = queueCourse.poll();
            if(stud == null) break;
            System.out.println("    Студент: " + stud);
        }


    }
}
