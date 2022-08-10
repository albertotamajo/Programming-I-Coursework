import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * This class represents a student.<br>
 * A student can enroll to a course at a time and will receive
 * a certification after having completed it successfully.
 *
 * @author albertoTamajo
 */
public class Student extends Person implements Serializable {


    private ArrayList<Integer> certificates;
    private boolean isEnrolled;
    private Course courseEnrolled;


    /**
     * Creates a Student object with name, gender and age
     *
     * @param name   student's name
     * @param gender student's gender
     * @param age    student's age
     */
    public Student(String name, char gender, int age) {

        super(name, gender, age);
        this.certificates = new ArrayList<>();
        this.isEnrolled = false;
        this.courseEnrolled = null;
    }

    /**
     * Gets the course the student is enrolled in
     *
     * @return the course the student is enrolled in
     */
    public Course getCourseEnrolled() {
        return courseEnrolled;
    }

    /**
     * Sets the course the student is enrolled in
     *
     * @param courseEnrolled a course the student is enrolled in
     */
    public void setCourseEnrolled(Course courseEnrolled) {
        this.courseEnrolled = courseEnrolled;
    }

    /**
     * Adds a certificate of completion after the student finishes the course
     *
     * @param subject a subject that the student has learnt successfully
     */
    public void graduate(Subject subject) {

        String subjectDescription = subject.getDescription();
        int certificateId = subject.getID();

        //Adds the course's certificate to the student
        certificates.add(certificateId);

        //If the student has got more than 1 certificate in total
        if (certificates.size() > 1) {

            //Sorts the certificate into ascending order
            Collections.sort(certificates);
        }

        System.out.println("**The student " + this.getName() + " has successfully completed the " + subjectDescription + " Course**\n");

    }

    /**
     * Gets the certificates of the student
     *
     * @return an ArrayList of certificates obtained by the student
     */
    public ArrayList<Integer> getCertificates() {
        return certificates;
    }

    /**
     * Checks whether or not the student has a certain certificate
     *
     * @param subject a subject in order to check the student's possession of the certificate
     * @return true if the student has the certificate, false otherwise
     */
    public boolean hasCertificate(Subject subject) {

        //Loops until a student certificate is matched to the subject id
        for (int certificate : certificates) {

            //The student possesses the certificate
            if (certificate == subject.getID()) {
                return true;
            }
        }

        //The student does not possess the certificate
        return false;

    }

    /**
     * Gets the iterator necessary to iterate over the student's certificates
     *
     * @return an iterator that iterates over the student's certificates
     */
    private Iterator<Integer> getCertificatesIterator() {
        return certificates.iterator();
    }

    /**
     * Returns the enrollment state of the student
     *
     * @return true if the student is enrolled, false otherwise
     */
    public boolean isEnrolled() {
        return isEnrolled;
    }

    /**
     * Toggles the enrollment state of the student
     */
    public void toggleEnrollment() {
        isEnrolled = !(isEnrolled);
    }

    /**
     * Returns all the certificates of the student in one string
     *
     * @return a string containing the certificates of the student
     */
    private String certificatesString() {

        String certificates = "";

        //Loops until all certificates are added to the string
        for (Integer a : this.certificates) {

            certificates += (a + "\n");
        }

        return certificates;
    }

    /**
     * Returns all the information of the student
     *
     * @return a string containing the information of the student
     */
    @Override
    public String toString() {

        String enrollment = "Enrolled in a course: ";
        String certificates = "Certificates : ";
        String yes = "YES" + "\n";
        String no = "NO" + "\n";
        String fancyCharacters = "+------------------------------------+";

        //If the student doesn't have a certificate
        if (this.certificates.isEmpty()) {

            //If the student is enrolled in a course
            if (this.isEnrolled) {

                return super.toString() + certificates + no + enrollment + yes + courseEnrolled.toStringForStudentsText() + fancyCharacters + "\n";

            } else {

                //If the student is not enrolled in a course
                return super.toString() + certificates + no + enrollment + no + fancyCharacters + "\n";
            }

        } else {        //If the student has at least 1 certificate

            //If the student is enrolled in a course
            if (this.isEnrolled) {

                return super.toString() + certificates + yes + certificatesString() + enrollment + yes + courseEnrolled.toStringForStudentsText() + fancyCharacters + "\n";

            } else {

                //If the student is not enrolled in a course
                return super.toString() + certificates + yes + certificatesString() + enrollment + no + fancyCharacters + "\n";
            }
        }
    }
}
