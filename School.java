import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

/**
 * This class represents a school.<br>
 * A school is associated with the following classes:
 * {@link Subject}, {@link Course}, {@link Student}, {@link Instructor}
 * and keeps information about them.<br>
 * In addition, this class implements a method that simulates events of a day at school.
 *
 * @author albertoTamajo
 */
public class School implements Serializable {

    private String name;
    private int daysRunning;
    private HashSet<Student> students;
    private HashSet<Instructor> instructors;
    private HashSet<Subject> subjects;
    private HashSet<Course> courses;


    /**
     * Creates a School object with a name
     *
     * @param name school's name
     */
    public School(String name) {

        this.name = name;
        this.students = new HashSet<>();
        this.instructors = new HashSet<>();
        this.subjects = new HashSet<>();
        this.courses = new HashSet<>();
        this.daysRunning = 1;
    }

    /**
     * Creates a School object with 'Default' as name
     */
    public School() {
        this("Default");
    }

    /**
     * Gets the number of school days
     *
     * @return number of school days
     */
    public int getDaysRunning() {
        return daysRunning;
    }

    /**
     * Gets the name of the school
     *
     * @return name of the school
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the school
     *
     * @param name name of the school
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Adds a new student to the school
     *
     * @param student student to add
     * @return true if the student is added successfully, false otherwise
     */
    public boolean add(Student student) {

        //If the student already attends the school
        if (!(students.add(student))) {

            System.out.println("**The student " + student.getName() + " already attends this school**\n");
            return false;
        }

        //The student is added successfully
        System.out.println("**The student " + student.getName() + " has been added successfully**\n");
        return true;

    }

    /**
     * Removes a student from the school
     *
     * @param student student to remove
     * @return true if the student is removed successfully, false otherwise
     */
    public boolean remove(Student student) {

        //If the student does not exist
        if (!(students.remove(student))) {

            System.out.println("**The student " + student.getName() + " does not exist**\n");
            return false;
        }

        //The student is removed successfully
        System.out.println("**The student " + student.getName() + " has been removed successfully**\n");
        return true;

    }

    /**
     * Adds a new subject to the school
     *
     * @param subject new subject to add
     * @return true if the subject is added successfully, false otherwise
     */
    public boolean add(Subject subject) {

        //If the subject already exists
        if (!(subjects.add(subject))) {

            System.out.println("**The subject " + subject.getDescription() + " already exists**\n");
            return false;
        }

        //The subject is added successfully
        System.out.println("**The subject " + subject.getDescription() + " has been added successfully**\n");
        return true;

    }

    /**
     * Removes a subject from the school
     *
     * @param subject subject to remove
     * @return true if the subject is removed successfully, false otherwise
     */
    public boolean remove(Subject subject) {

        //If the subject does not exist
        if (!(subjects.remove(subject))) {

            System.out.println("**The subject " + subject.getDescription() + " does not exist**\n");
            return false;
        }

        //The subject is removed successfully
        System.out.println("**The subject " + subject.getDescription() + " has been removed successfully**\n");
        return true;

    }

    /**
     * Adds a new instructor to the school
     *
     * @param instructor instructor to add
     * @return true if the instructor is added successfully, false otherwise
     */
    public boolean add(Instructor instructor) {

        //If the instructor already teaches in the school
        if (!(instructors.add(instructor))) {

            System.out.println("**The instructor " + instructor.getName() + " already teaches in this school**\n");
            return false;
        }

        //The instructor is added successfully
        System.out.println("**The instructor " + instructor.getName() + " has been added successfully**\n");
        return true;
    }

    /**
     * Removes an instructor from the school
     *
     * @param instructor instructor to remove
     * @return true if the instructor is removed successfully, false otherwise
     */
    public boolean remove(Instructor instructor) {

        //If the instructor  does not exist
        if (!(instructors.remove(instructor))) {

            System.out.println("**The instructor " + instructor.getName() + " does not exist**\n");
            return false;
        }

        //The instructor is removed successfully
        System.out.println("**The instructor " + instructor.getName() + " has been removed successfully**\n");
        return true;
    }

    /**
     * Adds a new course to the school
     *
     * @param course course to add
     * @return true if the course is added successfully, false otherwise
     */
    public boolean add(Course course) {

        //If the course already exists
        if (!(courses.add(course))) {

            System.out.println("**The course " + course.getSubject().getDescription() + " already exists**\n");
            return false;
        }

        //The course is added successfully
        System.out.println("**The course " + course.getSubject().getDescription() + " has been added successfully**\n");
        return true;

    }

    /**
     * Removes a course from the school
     *
     * @param course course to remove
     * @return true if the course is removed successfully, false otherwise
     */
    public boolean remove(Course course) {

        //If the course does not exist
        if (!(courses.remove(course))) {

            System.out.println("**The course " + course.getSubject().getDescription() + " does not exist**\n");
            return false;
        }

        //The course is removed successfully
        System.out.println("**The course " + course.getSubject().getDescription() + " has been removed successfully**\n");
        return true;
    }

    /**
     * Gets the set of students of the school
     *
     * @return the set of students enrolled in the school
     */
    public HashSet<Student> getStudents() {
        return students;
    }

    /**
     * Gets the set of instructors of the school
     *
     * @return the set of instructors working at the school
     */
    public HashSet<Instructor> getInstructors() {
        return instructors;
    }

    /**
     * Gets the set of subjects of the school
     *
     * @return the set of subjects taught in the school
     */
    public HashSet<Subject> getSubjects() {
        return subjects;
    }

    /**
     * Gets the set of courses of the school
     *
     * @return the set of courses taught in the school
     */
    public HashSet<Course> getCourses() {
        return courses;
    }

    /**
     * Generates and returns information in ascending order
     * about all elements present in a HashSet
     *
     * @param set a set from which information of its elements is needed
     * @return a string containing the info of every element
     */
    private String infoGenerator(HashSet set) {

        String infoString = "";
        ArrayList items = new ArrayList();

        //If the set is not empty
        if (!(set.isEmpty())) {

            //Adds every element of the set into the arrayList items
            for (Object o : set) {
                items.add(o);
            }

            //Sorts the elements of the arrayList items
            Collections.sort(items);

            //Adds the information of every element to the string infoString
            for (Object o : items) {
                infoString += o.toString();
            }

            return infoString;

        } else {

            //If the set is empty
            return "**NO ENTRIES**\n";
        }
    }

    /**
     * Returns a set of subjects that are not currently assigned to a course
     *
     * @return the set of subjects not currently having a course
     */
    private HashSet<Subject> subjectsNotTaught() {

        HashSet<Subject> subjectsWithNoCourses = new HashSet<>();

        //Loops for all subjects taught in the school
        //If a subject is not taught, it will be added to subjectsWithNoCourses
        for (Subject subject : subjects) {

            //If the subject does not have a course
            if (!(subject.hasCourse())) {

                //Adds the course
                subjectsWithNoCourses.add(subject);
            }

        }

        return subjectsWithNoCourses;
    }

    /**
     * Returns a set of instructors that are not currently teaching in a course
     *
     * @return the set of instructors not currently teaching
     */
    private HashSet<Instructor> instructorsWithNoCourses() {

        HashSet<Instructor> instructorsNotTeaching = new HashSet<>();

        //Loops for all instructors
        //If an instructor is not teaching, he/she will be added to instructorWithNoCourses
        for (Instructor instructor : instructors) {

            //If the instructor is not teaching
            if (!(instructor.isTeaching())) {

                //Adds the instructor
                instructorsNotTeaching.add(instructor);
            }

        }

        return instructorsNotTeaching;
    }

    /**
     * Returns a set of students that are not currently enrolled in a course
     *
     * @return the set of students not enrolled in a course
     */
    private HashSet<Student> studentsWithNoCourses() {

        HashSet<Student> studentsNotEnrolled = new HashSet<>();

        //Loops for all students
        //If a student is not enrolled in a course, he/she will be added to studentsNotEnrolled
        for (Student student : students) {

            //If the student is not enrolled
            if (!(student.isEnrolled())) {

                //Adds the student
                studentsNotEnrolled.add(student);
            }

        }

        return studentsNotEnrolled;

    }

    /**
     * Returns a set of courses requiring an instructor
     *
     * @return the set of courses with no instructor assigned
     */
    private HashSet<Course> coursesRequiringInstructor() {

        HashSet<Course> coursesWithNoInstructor = new HashSet<>();

        //Loops for all courses
        //If a course requires an instructor, it will be added to coursesWithNoInstructor
        for (Course course : courses) {

            //If the course requires an instructor
            if (!(course.hasInstructor())) {

                //Adds the course
                coursesWithNoInstructor.add(course);
            }

        }

        return coursesWithNoInstructor;
    }

    /**
     * Creates a course for every subject provided by the parameter {@code subjectsSet}<br>
     * The number of days until the courses start depends on the parameter {@code daysUntilStarts}.
     *
     * @param subjectsSet     a set of subjects that will be assigned to a course
     * @param daysUntilStarts number of days until the courses start
     */
    private void courseCreator(HashSet<Subject> subjectsSet, int daysUntilStarts) {

        //If the subjectsSet contains elements
        if (!(subjectsSet.isEmpty())) {

            System.out.println("**New courses have been created**\n");

            //Loops for all subjects present in the set
            //and creates courses for them
            for (Subject subject : subjectsSet) {

                Course course = new Course(subject, daysUntilStarts);
                courses.add(course);

                String courseInfo = course.toString();
                System.out.println(courseInfo);

            }

        }
    }

    /**
     * Assigns available instructors to courses with no instructor assigned
     */
    private void assignInstructorsToCourses() {

        HashSet<Course> coursesWithNoInstructor = coursesRequiringInstructor();
        HashSet<Instructor> instructorsNotTeaching = instructorsWithNoCourses();

        //Loops for all courses with no instructor
        for (Course course : coursesWithNoInstructor) {

            //Loops for all instructors not teaching
            for (Instructor instructor : instructorsNotTeaching) {

                //If the instructor has been assigned to the course successfully
                if (course.setInstructor(instructor)) {

                    //Removes the instructor from the set of instructors with no courses
                    //So that the next course will not re-iterate over it resulting in a redundant iteration
                    instructorsNotTeaching.remove(instructor);
                    break;

                }
            }
        }
    }

    /**
     * Returns a set of courses not full (less than 3 students enrolled)
     *
     * @return the set of courses with less than 3 students enrolled
     */
    private HashSet<Course> coursesNotFull() {

        HashSet<Course> coursesWithAvailability = new HashSet<>();

        //Loops for all courses
        //Adds courses not full to coursesWithAvailability
        for (Course course : courses) {

            //If less than 3 students are enrolled
            if (course.getSize() < 3) {

                coursesWithAvailability.add(course);
            }

        }

        return coursesWithAvailability;
    }

    /**
     * Returns a set of courses not started yet
     *
     * @return the set of courses not started
     */
    private HashSet<Course> coursesNotStarted() {

        HashSet<Course> coursesNotRunning = new HashSet<>();

        //Loops for all courses
        //Adds courses not started yet to coursesNotRunning
        for (Course course : courses) {

            //If the course has not started yet
            if (course.getStatus() < 0) {
                coursesNotRunning.add(course);
            }

        }

        return coursesNotRunning;
    }

    /**
     * Assigns students not enrolled in a course to an available course randomly
     */
    private void assignStudentsToCourses() {

        HashSet<Student> studentsNotEnrolled = studentsWithNoCourses();

        //If all students are enrolled in a course
        if (studentsNotEnrolled.isEmpty()) {

            System.out.println("**All students are enrolled in a course\n");

        } else {

            ArrayList<Course> coursesShuffled = new ArrayList<>();

            //Adds the courses not full and not already started
            //into the arrayList coursesShuffled
            for (Course course : courses) {

                //If the course is full or has already started
                if ((course.getSize() == 3) || (course.getStatus() >= 0)) {

                    //does nothing
                    continue;
                }

                //Otherwise, adds the course
                coursesShuffled.add(course);

            }

            //If no courses are available
            if (coursesShuffled.isEmpty()) {

                System.out.println("**No courses are available at the moment**\n");
                System.out.println("**The following students remain without a course:\n");

                //Prints out the names of all students without a course
                for (Student student : studentsNotEnrolled) {
                    System.out.println(student.getName() + "\n");
                }

            } else {       //If there are courses available

                Iterator<Student> studentsNotEnrolledIterator = studentsNotEnrolled.iterator();
                Iterator<Course> coursesShuffledIterator;

                //Loops for all students not enrolled in a course
                //Assigns these students to an available course
                loop1:
                while (studentsNotEnrolledIterator.hasNext()) {

                    Student nextStudent = studentsNotEnrolledIterator.next();

                    //Shuffles the arrayList so that students enrol to random courses
                    Collections.shuffle(coursesShuffled);
                    coursesShuffledIterator = coursesShuffled.iterator();

                    //Loops until the student can enroll to an available course
                    while (coursesShuffledIterator.hasNext()) {

                        Course nextCourse = coursesShuffledIterator.next();

                        //If the student can enroll to this course
                        if (nextCourse.enrolStudent(nextStudent)) {

                            //Deletes the student
                            studentsNotEnrolledIterator.remove();

                            //If the course is full now, deletes it
                            //So that to avoid redundant iterations
                            if (nextCourse.getSize() == 3) {

                                coursesShuffledIterator.remove();
                            }

                            //If there are no courses available now
                            if (coursesShuffled.isEmpty()) {

                                System.out.println("**No more courses are available at the moment**\n");

                                studentsNotEnrolledIterator = studentsNotEnrolled.iterator();

                                //Loops for all students that still are not enrolled in a course
                                while (studentsNotEnrolledIterator.hasNext()) {

                                    Student nextStudentWithNoCourse = studentsNotEnrolledIterator.next();

                                    System.out.println("**The student " + nextStudentWithNoCourse.getName() + " remains without a course**\n");

                                }

                                //Gets out of the loop1 and so the method terminates
                                break loop1;
                            }

                            //Gets out of this loop, loop1 will restart
                            break;
                        }
                    }
                }
            }
        }
    }

    /**
     * Advances all the courses taught by one day.<br>
     * The courses that are either finished or
     * cancelled will be deleted from the school.
     */
    public void advanceOneDay() {

        Iterator<Course> coursesIterator = courses.iterator();

        //Loops for all courses and advances them by 1 day
        //Removes the finished or cancelled courses
        while (coursesIterator.hasNext()) {

            Course nextCourse = coursesIterator.next();
            nextCourse.aDayPasses();

            //If the course has finished or has been cancelled
            if ((nextCourse.getStatus() == 0) || (nextCourse.isCancelled())) {

                //Removes the course from the set of courses
                coursesIterator.remove();
            }

        }

        //Increments the number of school days
        daysRunning++;
    }


    /**
     * Simulates events of a day at school.<br>
     * 1. Subjects that are not being taught will be assigned to a new course.<br>
     * 2. Courses requiring an instructor will be assigned to an available instructor.<br>
     * 3. Students not enrolled in a course will be enrolled in an available course.<br>
     * 4. All the courses will be advanced by one day.
     */
    public void aDayAtSchool() {

        //Creates new courses with subjects that are not being taught
        //The courses will start in 2 days
        courseCreator(subjectsNotTaught(), 2);

        //Assigns instructors to courses
        assignInstructorsToCourses();

        //Assigns students to available courses
        assignStudentsToCourses();

        //Advances all courses one day
        advanceOneDay();
    }


    /**
     * Returns a pretty-print string of the school containing
     * information about subjects, courses, instructors, students
     * and the relationship between them.
     *
     * @return a string containing all the necessary information about the school
     */
    @Override
    public String toString() {

        String schoolName = "THE NAME OF THIS SCHOOL IS: " + name + "\n";
        String subjectsTaught = "\nTHE SUBJECTS TAUGHT ARE: " + "\n";
        String instructorsSchool = "\nTHE INSTRUCTORS OF THIS SCHOOL ARE LISTED BELOW: " + "\n\n";
        String coursesArranged = "\n\nTHE COURSES ARRANGED ARE: " + "\n\n";
        String studentsSchool = "\nTHE STUDENTS OF THIS SCHOOL ARE: " + "\n\n";
        String fancyCharacters = "+------------------------------------+" + "\n";
        return schoolName + subjectsTaught + infoGenerator(this.subjects) + coursesArranged
                + infoGenerator(this.courses) + instructorsSchool + infoGenerator(this.instructors)
                + studentsSchool + infoGenerator(this.students) + fancyCharacters + "\n";

    }
}
