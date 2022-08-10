/**
 * This class represents a demonstrator.<br>
 * A demonstrator inherits properties and methods from the class {@link Instructor}.<br>
 * A demonstrator can teach subjects with the following specialisms: 2
 *
 * @author albertoTamajo
 */

public class Demonstrator extends Instructor {

    /**
     * Creates a Demonstrator object with name, gender and age
     *
     * @param name   demonstrator's age
     * @param gender demonstrator's gender
     * @param age    demonstrators's age
     * @see Instructor#Instructor(String name, char gender, int age)
     */
    public Demonstrator(String name, char gender, int age) {
        super(name, gender, age);
    }

    /**
     * Checks whether or not the Demonstrator can teach a certain subject
     *
     * @param subject a subject
     * @return true if the demonstrator can teach the subject, false otherwise
     */
    @Override
    public boolean canTeach(Subject subject) {
        int subjectSpecialism = subject.getSpecialism();
        return (subjectSpecialism == 2);
    }

    /**
     * Returns a string containing the information of the demonstrator
     *
     * @return information of the demonstrator
     */
    @Override
    public String toString() {
        String demonstratorIntro = "Instructor type: DEMONSTRATOR\n";
        return demonstratorIntro + super.toString();
    }
}
