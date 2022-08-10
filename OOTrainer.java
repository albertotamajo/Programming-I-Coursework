/**
 * This class represents a OO Trainer.<br>
 * An OO Trainer inherits properties and methods from the class {@link Teacher}.<br>
 * An OO Trainer can teach teach subjects with the following specialisms: 1, 2, 3
 *
 * @author albertoTamajo
 */
public class OOTrainer extends Teacher {

    /**
     * Creates a OO Trainer with name, gender and age
     *
     * @param name   OO trainer's age
     * @param gender OO trainer's gender
     * @param age    OO trainer's age
     * @see Teacher#Teacher(String name, char gender, int agw)
     */
    public OOTrainer(String name, char gender, int age) {
        super(name, gender, age);
    }

    /**
     * Checks whether or not the OO Trainer can teach a certain subject
     *
     * @param subject a subject to be taught
     * @return true if the OO Trainer can teach the subject, false otherwise
     */
    @Override
    public boolean canTeach(Subject subject) {
        int subjectSpecialism = subject.getSpecialism();
        return super.canTeach(subject) || (subjectSpecialism == 3);
    }

    /**
     * Returns a string containing the information of the OO Trainer
     *
     * @return information of the OO Trainer
     */
    @Override
    public String toString() {

        String ooTrainerIntro = "Sub-Instructor type: OO TRAINER\n";
        return ooTrainerIntro + super.toString();
    }
}
