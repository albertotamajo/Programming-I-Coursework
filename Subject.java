import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * This class represents a subject taught by a school.<br>
 * A subject is characterised by a unique id, a specialism and a duration.<br>
 * Additionally, it is possible to set prerequisites of lower level for the subject.
 *
 * @author albertoTamajo
 */
public class Subject implements Serializable, Comparable<Subject> {

    private int id;
    private int specialism;
    private int duration;
    private String description;
    private boolean hasCourse;
    private ArrayList<Integer> prerequisites;


    /**
     * Creates a Subject object with id, specialism and duration.
     *
     * @param id         subject's id
     * @param specialism subject's specialism
     * @param duration   subject's duration
     */
    public Subject(int id, int specialism, int duration) {
        this(id, specialism, duration, new ArrayList<>());
    }

    /**
     * Creates a Subject object with id, specialism, duration and prerequisites.
     *
     * @param id            subject's id
     * @param specialism    subject's specialism
     * @param duration      subject's duration
     * @param prerequisites subject's prerequisites to attend the course
     */
    public Subject(int id, int specialism, int duration, ArrayList<Integer> prerequisites) {
        this.id = id;
        this.specialism = specialism;
        this.duration = duration;
        this.prerequisites = prerequisites;
    }

    /**
     * Gets the prerequisites needed to attend the course.
     *
     * @return an ArrayList of prerequisites
     */
    public ArrayList<Integer> getPrerequisites() {
        return prerequisites;
    }


    /**
     * Checks every prerequisite provided by the argument <code>prerequisites</code>
     * in order to remove the invalid entries.<br>
     * Only  non negative and lower level prerequisites can be added to the subject.
     *
     * @param prerequisites prerequisites to add to the subject
     * @return ArrayList of valid prerequisites
     */
    private ArrayList<Integer> prerequisitesChecker(ArrayList<Integer> prerequisites) {

        Iterator<Integer> prerequisitesIterator = prerequisites.iterator();

        //Loops until all prerequisites are checked to be valid
        //Removes the invalid prerequisites
        while (prerequisitesIterator.hasNext()) {

            int nextPrerequisite = prerequisitesIterator.next();

            //If the prerequisite is a course of higher level
            if ((nextPrerequisite >= id) || (nextPrerequisite < 0)) {

                System.err.println("**Sorry, only non negative and lower level prerequisites can be added**\n");

                //Removes the prerequisite
                prerequisitesIterator.remove();
            }
        }

        return prerequisites;
    }

    /**
     * Sets the prerequisites of the subject.<br>
     * Only  non negative and lower level prerequisites can be added to the subject.<br>
     * The prerequisites that do not respect the above condition will be removed.<br>
     * If the subject already has prerequisites, these will be overwritten.
     *
     * @param prerequisites prerequisites to set for the subject
     */
    public void setPrerequisites(ArrayList<Integer> prerequisites) {

        //Removes the invalid entries and sets the prerequisites of the course
        this.prerequisites = prerequisitesChecker(prerequisites);

        //Sorts the prerequisites into ascending order
        Collections.sort(this.prerequisites);
    }

    /**
     * Adds prerequisites to the subject<br>
     * If the subject already has prerequisites, these will be preserved.
     *
     * @param prerequisites prerequisites to add to the subject
     */
    public void addPrerequisites(ArrayList<Integer> prerequisites) {

        //Adds the valid prerequisites to the course
        this.prerequisites.addAll(prerequisitesChecker(prerequisites));

        //Sorts the prerequisites into ascending order
        Collections.sort(this.prerequisites);
    }


    /**
     * Gets the subject's ID
     *
     * @return an int representing the subject's ID
     */
    public int getID() {
        return id;
    }

    /**
     * Gets the subject's specialism
     *
     * @return an int representing the subject's specialism
     */
    public int getSpecialism() {
        return specialism;
    }

    /**
     * Gets the subject's duration
     *
     * @return an int representing the number of days
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Sets the description of the subject
     *
     * @param description subect's description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the description of the subject
     *
     * @return a string containing the subject's description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Toggle the course status of the subject
     */
    public void toggleHasCourse() {
        hasCourse = !(hasCourse);
    }

    /**
     * Returns whether or not the subject is taught by a course
     *
     * @return true if the subject is taught by a course, false otherwise
     */
    public boolean hasCourse() {
        return this.hasCourse;
    }

    /**
     * Compares the descriptions of two Subject objects ({@link String} objects) lexicographically.<br>
     *
     * @param o a subject whose description is compared
     * @return see <code>String.compareTo()</code>
     * @see String#compareTo(String)
     */
    @Override
    public int compareTo(Subject o) {
        return this.description.compareTo(o.description);
    }

    /**
     * Returns the information of the the subject
     *
     * @return a string containing the subject's information
     */
    @Override
    public String toString() {

        String id = "Subject ID: " + this.id + "\n";
        String specialism = "Subject Specialism: " + this.specialism + "\n";
        String duration = "Subject Duration: " + this.duration + " days" + "\n";
        String description = "Subject Description: " + this.description + "\n";
        String prerequisites = "Subject Prerequisites: " + this.prerequisites + "\n";
        String fancyCharacters = "+------------------------------------+";

        //If the course has no prerequisites
        if (this.prerequisites.isEmpty()) {
            return "\n" + description + id + specialism + duration + fancyCharacters;
        } else {
            //The course has prerequisites
            return "\n" + description + id + specialism + duration + prerequisites + fancyCharacters;
        }

    }

    /**
     * Returns the information of the subject.<br>
     * It is used in the {@link Course} class in order to have a cleaner appearance.
     *
     * @return a string containing the subject's information
     */
    public String toStringForCourseText() {

        //It returns the same string of toString() except the fancy character below
        return toString().replace("\n+------------------------------------+", "");

    }

}
