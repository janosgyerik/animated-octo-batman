package com.janosgyerik.codereview.VinceEmigh.orig;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class School {
    private Course[] courses;
    private Student[] students;

    public School(Course[] courses) {
        this.courses = courses;

        //ive been told that constructors shouldnt contain logic. is there any other way to handle this?
        int numOfStudents = 0;
        for (Course course : courses)
            numOfStudents += course.getStudents().size();

        students = new Student[numOfStudents];
    }

    public void register(Student... students) { //this method is pretty messy, and loops quite a few times. any suggestions?
        if (isFull())
            throw new IllegalStateException("Cannot register anymore students at this time");

        for (Student student : students) {
            if (Arrays.asList(this.students).contains(student)) //wrapping the array every loop. is there any better way to do this, without creating my own private contains method for students?
                throw new IllegalArgumentException("You cannot add the same student to a school twice"); //should I be throwing a runtime exception here? or should i just continue with the rest of the students

            for (Course course : courses) {
                if (student.prefersCourse(course) && !course.isFull())
                    student.assignCourse(course);
            }

            verifyStudent(student); //make sure the student is ready for school
            student.setSchool(this);
            for (int i = 0; i < this.students.length; i++) {
                if (this.students[i] == null) {
                    this.students[i] = student;
                    break;
                }
            }
        }
    }

    private void verifyStudent(Student student) {
        verifyCourses(student);
        //more will be added here later
    }

    private void verifyCourses(Student student) {
        boolean verified = false;

        //assigns a random course. is there a cleaner way to handle this?
        while (!verified) {
            for (Course course : student.getCourses()) {
                if (course == null) {
                    int index = (int) (Math.random() * courses.length);
                    student.assignCourse(courses[index]);
                }
            }

            verified = !Arrays.asList(student.getCourses()).contains(null);
        }
    }

    public Student[] getStudents() {
        return Arrays.copyOf(students, students.length);
    }

    public Course[] getCourses() {
        return Arrays.copyOf(courses, courses.length);
    }

    public boolean isFull() {
        boolean full = true;
        for (Student student : students)
            if (student == null)
                return full = false;

        return full;
    }
}

abstract class Course {
    private final int maxStudents;
    private final Teacher teacher;
    private final Set<Student> students;

    protected Course(int maxStudents, Teacher teacher) {
        this.maxStudents = maxStudents;
        this.teacher = teacher;
        this.students = new HashSet<>();
    }

    void addStudent(Student student) {
        students.add(student);
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public boolean isFull() {
        return students.size() == maxStudents;
    }

    public abstract String getName();
}

class Student extends Entity {
    private School school;
    private Course[] courses;
    private Set<Class<? extends Course>> preferredCourses;

    public Student(String name) {
        super(name);
        courses = new Course[2];
        preferredCourses = new HashSet<>();
    }

    public void setPreferredCourses(Class<? extends Course>... courses) {
        for (Class<? extends Course> course : courses) {
            preferredCourses.add(course);
        }
    }

    void assignCourse(Course course) {
        for (int i = 0; i < courses.length; i++) {
            if (course == courses[i])
                continue;

            if (courses[i] == null) {
                course.addStudent(this);
                courses[i] = course;
                return;
            }
        }
    }

    void setSchool(School school) {
        this.school = school;
    }

    public School getSchool() {
        return school;
    }

    public Course[] getCourses() {
        return Arrays.copyOf(courses, courses.length);
    }

    public boolean prefersCourse(Course course) {
        return preferredCourses.contains(course.getClass());
    }

    public boolean isTakingCourse(Course course) {
        boolean contains = false;
        for (Course c : courses)
            return contains = (c == course);

        return contains;
    }
}

class Teacher extends Entity {
    public Teacher(String name) {
        super(name);
    }
}

abstract class Entity {
    private final String name;

    protected Entity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class APIDesignCourse extends Course {

    public APIDesignCourse(int numOfStudents, Teacher teacher) {
        super(numOfStudents, teacher);
    }

    public String getName() {
        return getTeacher().getName() + "'s API Design Course";
    }
}

class NetworkCourse extends Course {
    public NetworkCourse(int numOfStudents, Teacher teacher) {
        super(numOfStudents, teacher);
    }

    public String getName() {
        return getTeacher().getName() + "'s Network Course";
    }
}

class PerformanceCourse extends Course {
    public PerformanceCourse(int numOfStudents, Teacher teacher) {
        super(numOfStudents, teacher);
    }

    public String getName() {
        return getTeacher().getName() + "'s Performance Course";
    }
}

class SwingCourse extends Course {

    public SwingCourse(int numOfStudents, Teacher teacher) {
        super(numOfStudents, teacher);
    }

    public String getName() {
        return getTeacher().getName() + "'s Swing Course";
    }
}

public class SchoolTest {
    @Test
    public void example() {
        Teacher phil = new Teacher("Phil");
        Teacher bill = new Teacher("Bill");
        Teacher lil = new Teacher("Lil");
        Teacher joe = new Teacher("Joe");

        Course[] courses = {new NetworkCourse(15, phil), new SwingCourse(30, bill), new APIDesignCourse(50, lil), new PerformanceCourse(5, joe)};
        School school = new School(courses);

        Student ludwig = new Student("Ludwig");
        Student cam = new Student("Cam");
        Student daniel = new Student("Daniel");
        ludwig.setPreferredCourses(NetworkCourse.class, SwingCourse.class); //give students preferred classes if they have them
        cam.setPreferredCourses(APIDesignCourse.class, PerformanceCourse.class, NetworkCourse.class);

        school.register(ludwig, cam, daniel);

        test(school);
    }

    static void test(School school) {
            /*
             * Prints all the students in the school, all the courses in the school, and which course each student has
    		 */
        System.out.println("Students and their courses:");
        for (Student student : school.getStudents()) {
            if (student != null) {
                String message = student.getName() + " is taking"; //message will reset for each new student, since we do = and not += here

                for (Course course : student.getCourses())
                    message += " - " + course.getName();

                System.out.println(message);
            }
        }

        System.out.println("\nCourses and their students:");
        for (Course course : school.getCourses()) {
            String message = course.getName() + " is taken by";

            for (Student student : course.getStudents()) {
                if (student != null)
                    message += " - " + student.getName();
            }
            System.out.println(message);
        }
    }
}

