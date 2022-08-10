import java.io.Serializable;

/**
 * This abstract class represents an Instructor
 * and defines the basic properties and methods of all kinds of instructors.<br>
 * An instructor can be assigned and unassigned to/from a course.<br>
 * An instructor can be assigned to a course only if he/she possesses the right specialism.<br>
 * The specialism will be defined by the different kinds of instructors.<br>
 *
 * @author albertoTamajo
 */

public abstract class Instructor extends Person implements Serializable {


    private Course assignedCourse;
    private boolean isTeaching;

    /**
     * Creates an Instructor with name, gender and age
     *
     * @param name   instructor's name
     * @param gender instructor's gender
     * @param age    instructor's age
     * @see Person#Person(String name, char gender, int age)
     */
    public Instructor(String name, char gender, int age) {
        super(name, gender, age);
        this.isTeaching = false;
    }

    /**
     * Assigns a course to the Instructor
     *
     * @param course a course
     */
    public boolean assignCourse(Course course) {

        //If the Instructor is not teaching
        if (!(isTeaching)) {

            //If the Instructor can teach the course
            if (canTeach(course.getSubject())) {

                System.out.println("**The instructor " + this.getName() + " has been assigned to the " + course.getSubject().getDescription() + " Course**\n");
                assignedCourse = course;
                isTeaching = true;
                return true;

            } else {

                //If the instructor cannot teach the course
                System.out.println("**The instructor " + this.getName() + " cannot teach the " + course.getSubject().getDescription() + " Course**\n");
                return false;
            }

        } else {

            //If the instructor is teaching
            System.out.println("**The instructor " + this.getName() + " is already teaching " + assignedCourse.getSubject().getDescription() + " Course. Therefore, he/she cannot teach the " + course.getSubject().getDescription() + " Course**\n");
            return false;
        }
    }

    /**
     * Unassigns the course to the Instructor
     */
    public void unassignCourse() {

        //If the instructor has an assigned course
        if (assignedCourse != null) {

            System.out.println("**The instructor " + this.getName() + " has been unassigned to the Course " + assignedCourse.getSubject().getDescription() + "**\n");
            assignedCourse = null;
            isTeaching = false;
        }
    }

    /**
     * Returns the Instructor's teaching status
     *
     * @return true if the Instructor is teaching, false otherwise
     */
    public boolean isTeaching() {
        return this.isTeaching;
    }

    /**
     * Gets the Instructor's assigned course
     *
     * @return the assigned course
     */
    public Course getAssignedCourse() {

        //If the instructor is teaching
        if (isTeaching) {

            return assignedCourse;

        } else {

            //If the instructor is not teaching
            System.out.println("**This instructor is not teaching**");

            //assignedCourse is null
            return assignedCourse;
        }
    }

    /**
     * Checks whether or not the instructor can teach a subject
     *
     * @param subject subject to be taught
     * @return true if the instructor can teach the subject, false otherwise
     */
    abstract boolean canTeach(Subject subject);

    /**
     * Returns a string containing the information of the instructor
     *
     * @return information of the instructor
     */
    @Override
    public String toString() {

        String assignedCourse = "Assigned Course: ";
        String yes = "YES" + "\n";
        String no = "NO" + "\n";
        String fancyCharacters = "+------------------------------------+";

        //If the Instructor has an assigned course
        if (this.assignedCourse != null) {

            return super.toString() + assignedCourse + yes + this.assignedCourse.toString() + fancyCharacters + "\n";

        } else {

            //If the instructor does not have an assigned course
            return super.toString() + assignedCourse + no + fancyCharacters + "\n";
        }

    }
}
