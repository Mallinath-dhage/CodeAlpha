import java.util.*;

class Student {
    private final int id;
    private final String name;
    private final Map<String, Double> grades;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
        this.grades = new HashMap<>();
    }

    public void addGrade(String subject, double grade) {
        grades.put(subject, grade);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Map<String, Double> getGrades() {
        return new HashMap<>(grades); 
    
    }

    public double getAverageGrade() {
        if (grades.isEmpty()) return 0.0;
        double sum = 0;
        for (double grade : grades.values()) {
            sum += grade;
        }
        return sum / grades.size();
    }
}

class GradeTracker {
    private final Map<Integer, Student> students; 
    public GradeTracker() {
        this.students = new HashMap<>();
    }

    public void addStudent(int id, String name) {
        if (!students.containsKey(id)) {
            students.put(id, new Student(id, name));
        }
    }

    
    public void addGrade(int studentId, String subject, double grade) {
        Student student = students.get(studentId);
        if (student != null) {
            student.addGrade(subject, grade);
        }
    }

    
    public double getStudentAverage(int studentId) {
        Student student = students.get(studentId);
        return (student != null) ? student.getAverageGrade() : -1;
    }

    
    public void displayStudents() {
        for (Student student : students.values()) {
            System.out.println("ID: " + student.getId() + ", Name: " + student.getName());
            System.out.println("Grades: " + student.getGrades());
            System.out.println("Average: " + student.getAverageGrade());
            System.out.println("-----------");
        }
    }
}

public class StudentGradeTracker {
    public static void main(String[] args) {
        GradeTracker tracker = new GradeTracker();

        tracker.addStudent(1, "Shivaraju");
        tracker.addStudent(2, "Ramesh");
        tracker.addStudent(3, "Suresh");

        tracker.addGrade(1, "Math", 85);
        tracker.addGrade(1, "Science", 90);
        tracker.addGrade(2, "Math", 78);
        tracker.addGrade(2, "History", 88);
        tracker.addGrade(3, "BigData", 85);
        tracker.addGrade(3, "Machine Learning", 98);

        tracker.displayStudents();
    }
}
