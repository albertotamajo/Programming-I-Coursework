/**
 * This class represents a teacher.<br>
 * A teacher inherits properties and methods from the class {@link Instructor}.<br>
 * A teacher can teach subjects with the following specialisms: 1, 2
 *
 * @author albertoTamajo
 */

public class Teacher extends Instructor {

    /**
     * Creates a Teacher object with name, gender and age
     *
     * @param name   teacher's name
     * @param gender teacher's gender
     * @param age    teacher's age
     */
    public Teacher(String name, char gender, int age) {
        super(name, gender, age);
    }

    /**
     * Checks whether or not the Teacher can teach a certain subject
     *
     * @param subject a subject
     * @return true if the Teacher can teach the subject, false otherwise
     */
    @Override
    public boolean canTeach(Subject subject) {

        int subjectSpecialism = subject.getSpecialism();

        return (subjectSpecialism == 1) || (subjectSpecialism == 2);
    }

    /**
     * Returns a string containing the information of the teacher
     *
     * @return information of the teacher
     */
    @Override
    public String toString() {
        String teacherIntro = "Instructor type: TEACHER\n";
        return teacherIntro + super.toString();
    }
}
