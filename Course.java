import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class represents a Course taught by a school.<br>
 * A course is associated with a unique subject and so its length is determined by the subject.<br>
 * A course is characterised by the number of days until it starts.<br>
 * A course is taught by an instructor and a maximum of 3 students can enroll in it.
 *
 * @author albertoTamajo
 */
public class Course implements Serializable, Comparable<Course> {


    private Subject subject;
    private int daysUntilStarts;
    private int daysToRun;
    private ArrayList<Student> enrolledStudentsArrayList;
    private int enrolledStudents;
    private Instructor instructor;
    private boolean isCancelled;


    /**
     * Creates a Course with a subject and the number of days until it starts
     *
     * @param subject         a subject
     * @param daysUntilStarts the number of days until the course starts
     */
    public Course(Subject subject, int daysUntilStarts) {

        this.subject = subject;

        //The subject has a course now
        this.subject.toggleHasCourse();

        this.daysUntilStarts = daysUntilStarts;
        this.daysToRun = subject.getDuration();
        this.enrolledStudentsArrayList = new ArrayList<>();
        this.isCancelled = false;
    }

    /**
     * Gets the subject taught by the course
     *
     * @return the subject taught by the course
     */
    public Subject getSubject() {
        return subject;
    }

    /**
     * Gets the status of the course.<br>
     * Returns a positive number if the course has already started, a negative number
     * if the course still must start, 0 if the course has finished.
     *
     * @return a negative number indicating the number of days until the course starts
     * or a positive number indicating the number of days still to run
     * or 0 if the course has finished
     */
    public int getStatus() {

        //If the course still must start
        if (daysUntilStarts > 0) {

            return -(daysUntilStarts);

        } else if (daysToRun > 0) {

            //If the course has already started
            return daysToRun;

        } else {

            //The course has finished
            return 0;
        }
    }

    /**
     * Cancels the course
     */
    private void cancelCourse() {

        isCancelled = true;
        this.daysToRun = 0;

        //The subject has no course now
        subject.toggleHasCourse();

        System.out.println("**The course " + this.subject.getDescription() + " has been cancelled !**\n");

        //If the course has un instructor
        if (hasInstructor()) {
            unassignInstructor();
        }

        //If the course has at least 1 student enrolled
        if (enrolledStudents > 0) {
            unenrollStudents();
        }

    }

    /**
     * Provides a certificate of completion to the students
     */
    private void graduateStudents() {

        //Loops until all students are graduated
        for (Student student : enrolledStudentsArrayList) {
            student.graduate(subject);
        }

    }

    /**
     * Unassign the current instructor from the course
     */
    private void unassignInstructor() {

        //If the course has an instructor
        if (instructor != null) {

            //Unassigns the instructor
            instructor.unassignCourse();
            instructor = null;

        } else {
            //If the course does not have an instructor
            System.out.println("This course does not have an instructor yet");
        }

    }

    /**
     * Unenrolls students from the course
     */
    private void unenrollStudents() {

        String courseDescription = subject.getDescription();

        //Loops until all students are unenrolled
        for (Student student : enrolledStudentsArrayList) {

            System.out.println("**The student " + student.getName() + " does not attend the " + courseDescription + " Course anymore**\n");
            student.toggleEnrollment();
        }

        enrolledStudents = 0;

        //Removes all students from the course
        enrolledStudentsArrayList = new ArrayList<>();
    }

    /**
     * Terminates a course by graduating students
     */
    private void courseCompletion() {

        String courseDescription = subject.getDescription();
        System.out.println("**The course " + courseDescription + " Course" + " has finished**\n");

        //Graduates al students enrolled
        graduateStudents();

        //Unassign the instructor
        unassignInstructor();

        //Unenroll all students
        unenrollStudents();

        subject.toggleHasCourse();

    }

    /**
     * Advances the course by one day
     */
    public void aDayPasses() {

        //If the course is not cancelled
        if (!(isCancelled)) {

            //If the course still must start
            if (daysUntilStarts > 0) {

                daysUntilStarts--;

                //If the course is running now
                if (daysUntilStarts == 0) {

                    //If no instructor is assigned or no students are enrolled
                    if (!(hasInstructor()) || (enrolledStudents == 0)) {

                        //Cancels course
                        cancelCourse();
                    }
                }
            } else if (daysToRun > 0) {
                //If the course has already started

                daysToRun--;

                //If the course has finished
                if (daysToRun == 0) {

                    //Terminates the course
                    courseCompletion();
                }
            }
        }
    }

    /**
     * Enrolls a student to the course
     *
     * @param student the student to enroll
     * @return true if the student has been enrolled successfully, false otherwise
     */
    public boolean enrolStudent(Student student) {

        String courseDescription = this.getSubject().getDescription();

        //If the student has already attended the course
        if (student.hasCertificate(this.subject)) {

            System.out.println("**The student " + student.getName() + " has already attended " + courseDescription + " course**\n");
            return false;

        } else if ((!(prerequisitesChecker(student)))) {

            //If the student does not have the prerequisites
            System.out.println("**The student " + student.getName() + " does not have the prerequisites to join the " + courseDescription + " Course**\n");
            return false;

        } else if (daysUntilStarts == 0) {

            //If the course has already started
            System.out.println("**The student " + student.getName() + " cannot join the " + courseDescription + " Course because it has already started**\n");
            return false;

        } else if (enrolledStudents == 3) {

            //If the course is full
            System.out.println("**The student " + student.getName() + " cannot join the " + courseDescription + " Course because it is full**\n");
            return false;

        } else {

            System.out.println("**The student " + student.getName() + " has enrolled in " + courseDescription + " Course**\n");

            //Adds the new student to the arrayList
            enrolledStudentsArrayList.add(student);

            //Increases the number of students enrolled
            enrolledStudents++;

            //Toggles the student enrollment status
            student.toggleEnrollment();

            student.setCourseEnrolled(this);

            return true;
        }

    }

    /**
     * Returns the prerequisites needed to enroll to the course
     *
     * @return an ArrayList of prerequisites
     */
    private ArrayList<Integer> prerequisitesArray() {
        return subject.getPrerequisites();
    }

    /**
     * Checks if a student has the prerequisites to enroll to the course
     *
     * @param student a student
     * @return true if the student can enroll to the course, false otherwise
     */
    private boolean prerequisitesChecker(Student student) {

        //If the course has prerequisites
        if (!(prerequisitesArray().isEmpty())) {

            //If the student has got certificates
            if (!(student.getCertificates().isEmpty())) {

                ArrayList<Integer> prerequisites = prerequisitesArray();
                ArrayList<Integer> studentCertificates = student.getCertificates();

                Iterator<Integer> prerequisitesIterator = prerequisites.iterator();
                Iterator<Integer> studentCertificatesIterator = studentCertificates.iterator();


                //Loops until all prerequisites of the course are checked
                while (prerequisitesIterator.hasNext()) {

                    int nextPrerequisite = prerequisitesIterator.next();

                    //Loops until a student certificates are checked
                    while (studentCertificatesIterator.hasNext()) {

                        int studentCertificate = studentCertificatesIterator.next();

                        //If the student has the prerequisite
                        if (nextPrerequisite == studentCertificate) {
                            break;
                        }

                        //If the student does not have any other certificate
                        if (!(studentCertificatesIterator.hasNext())) {

                            //The student does not have the prerequisites
                            return false;
                        }
                    }

                }

                //The student has all the prerequisites
                return true;

            } else {

                //The student has not got any certificate yet
                //Thus, he/she cannot attend the course
                return false;
            }

        } else {

            //If the course has no prerequisites
            return true;
        }

    }

    /**
     * Gets the number of students enrolled
     *
     * @return an int indicating the number of students enrolled
     */
    public int getSize() {
        return enrolledStudents;
    }

    /**
     * Gets the students enrolled
     *
     * @return an Array of the students enrolled
     */
    public Student[] getStudents() {

        return (Student[]) enrolledStudentsArrayList.toArray();
    }

    /**
     * Sets the instructor of the course
     *
     * @param instructor an instructor
     * @return true if the instructor has been set successfully, false otherwise
     */
    public boolean setInstructor(Instructor instructor) {

        //If no instructor is assigned
        if (!(hasInstructor())) {

            //If the instructor can teach the course
            if (instructor.assignCourse(this)) {

                this.instructor = instructor;
                return true;
            }
        }

        return false;
    }

    /**
     * Returns whether or not the course has an instructor
     *
     * @return true if the course has un instructor, false otherwise
     */
    public boolean hasInstructor() {

        //If the course has an instructor
        if (instructor != null) {
            return true;
        }

        //The course has no instructor
        return false;
    }

    /**
     * Return the status of the course
     *
     * @return true if the course is cancelled, false otherwise
     */
    public boolean isCancelled() {
        return isCancelled;
    }

    /**
     * Returns all the information about a course
     *
     * @return a String containing all the information about a course
     */
    @Override
    public String toString() {

        String courseIntro = "COURSE INFO:" + "\n";
        String fancyCharacters = "+------------------------------------+" + "\n";
        String daysUntilStarts = "\nThe course will start in " + this.daysUntilStarts + " days";
        String daysToRun = "\nThe course will end in " + this.daysToRun + "\n";
        String enrolledStudents = "\nThe number of students enrolled is " + this.enrolledStudents + "\n";
        String hasInstructorString = "Has an Instructor: ";
        String yes = "YES\n";
        String no = "NO\n";

        //If the course still must start
        if (this.daysUntilStarts > 0) {

            //If no instructor is assigned
            if (!(hasInstructor())) {

                return courseIntro + subject.toStringForCourseText() + daysUntilStarts + enrolledStudents + hasInstructorString + no + fancyCharacters + "\n";

            } else {

                //If an instructor is assigned
                return courseIntro + subject.toStringForCourseText() + daysUntilStarts + enrolledStudents + hasInstructorString + yes + fancyCharacters + "\n";
            }

        } else {          //If the course has started

            //If no instructor is assigned
            if (!(hasInstructor())) {

                return courseIntro + subject.toStringForCourseText() + daysToRun + enrolledStudents + hasInstructorString + no + fancyCharacters + "\n";

            } else {

                //If an instructor is assigned
                return courseIntro + subject.toStringForCourseText() + daysToRun + enrolledStudents + hasInstructorString + yes + fancyCharacters + "\n";
            }
        }

    }

    /**
     * Returns all the information about a course.<br>
     * It is used in the {@link Student} class in order to have a cleaner appearance.
     *
     * @return a string containing all the information about a course
     */
    public String toStringForStudentsText() {

        //It returns the same string of toString() except the fancy character below
        return toString().replace("+------------------------------------+", "");
    }

    /**
     * Compares the subject's description of two Courses objects lexicographically.<br>
     *
     * @param o a course whose subject's description is compared
     * @return see <code>Subject.compareTO()</code>
     * @see Subject#compareTo(Subject)
     */
    @Override
    public int compareTo(Course o) {
        return this.subject.compareTo(o.subject);
    }


}
