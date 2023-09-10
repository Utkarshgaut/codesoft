import java.util.*;

class Course {
    private String code;
    private String title;
    private String description;
    private int capacity;
    private List<String> schedule;
    private List<String> registeredStudents;

    public Course(String code, String title, String description, int capacity, List<String> schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.registeredStudents = new ArrayList<>();
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<String> getSchedule() {
        return schedule;
    }

    public List<String> getRegisteredStudents() {
        return registeredStudents;
    }

    public boolean registerStudent(String studentID) {
        if (registeredStudents.size() < capacity) {
            registeredStudents.add(studentID);
            return true;
        } else {
            System.out.println("Course is full. Cannot register.");
            return false;
        }
    }

    public boolean removeStudent(String studentID) {
        if (registeredStudents.contains(studentID)) {
            registeredStudents.remove(studentID);
            return true;
        } else {
            System.out.println("Student not found in the course.");
            return false;
        }
    }

    public void displayCourseDetails() {
        System.out.println("Course Code: " + code);
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Capacity: " + capacity);
        System.out.println("Schedule: " + schedule);
        System.out.println("Registered Students: " + registeredStudents);
    }
}

class Student {
    private String studentID;
    private String name;
    private List<String> registeredCourses;

    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public List<String> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerCourse(String courseCode, List<Course> courses) {
        for (Course course : courses) {
            if (course.getCode().equals(courseCode)) {
                if (course.registerStudent(studentID)) {
                    registeredCourses.add(courseCode);
                    System.out.println(name + " registered for course: " + course.getTitle());
                }
                return;
            }
        }
        System.out.println("Course with code " + courseCode + " not found.");
    }

    public void removeCourse(String courseCode, List<Course> courses) {
        for (Course course : courses) {
            if (course.getCode().equals(courseCode)) {
                if (course.removeStudent(studentID)) {
                    registeredCourses.remove(courseCode);
                    System.out.println(name + " dropped course: " + course.getTitle());
                }
                return;
            }
        }
        System.out.println("Course with code " + courseCode + " not found.");
    }

    public void displayRegisteredCourses(List<Course> courses) {
        System.out.println(name + "'s Registered Courses:");
        for (String courseCode : registeredCourses) {
            for (Course course : courses) {
                if (course.getCode().equals(courseCode)) {
                    System.out.println(course.getTitle());
                }
            }
        }
    }
}

public class CourseRegistrationSystem {
    public static void main(String[] args) {
        List<Course> courses = new ArrayList<>();
        List<Student> students = new ArrayList<>();

        // Sample Courses
        courses.add(new Course("CSE101", "Introduction to Programming", "Fundamentals of programming", 30, Arrays.asList("Mon 10:00 AM", "Wed 10:00 AM")));
        courses.add(new Course("MATH201", "Calculus I", "Fundamental calculus concepts", 25, Arrays.asList("Tue 9:00 AM", "Thu 9:00 AM")));

        // Sample Students
        students.add(new Student("S001", "Alice"));
        students.add(new Student("S002", "Bob"));

        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Display Course Listing");
            System.out.println("2. Register Student for a Course");
            System.out.println("3. Remove Student from a Course");
            System.out.println("4. Display Student's Registered Courses");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\nCourse Listing:");
                    for (Course course : courses) {
                        course.displayCourseDetails();
                    }
                    break;
                case 2:
                    System.out.print("\nEnter student ID: ");
                    String studentID = scanner.next();
                    for (Student student : students) {
                        if (student.getStudentID().equals(studentID)) {
                            System.out.print("Enter course code to register: ");
                            String courseCode = scanner.next();
                            student.registerCourse(courseCode, courses);
                            break;
                        }
                    }
                    break;
                case 3:
                    System.out.print("\nEnter student ID: ");
                    studentID = scanner.next();
                    for (Student student : students) {
                        if (student.getStudentID().equals(studentID)) {
                            System.out.print("Enter course code to remove: ");
                            String courseCode = scanner.next();
                            student.removeCourse(courseCode, courses);
                            break;
                        }
                    }
                    break;
                case 4:
                    System.out.print("\nEnter student ID: ");
                    studentID = scanner.next();
                    for (Student student : students) {
                        if (student.getStudentID().equals(studentID)) {
                            student.displayRegisteredCourses(courses);
                            break;
                        }
                    }
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
