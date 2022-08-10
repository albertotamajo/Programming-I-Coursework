/**
 * This class represents a GUI Trainer.<br>
 * A GUI Trainer inherits properties and methods from the class {@link Teacher}.<br>
 * A GUI Trainer can teach subjects with the following specialisms: 1, 2, 4
 *
 * @author albertoTamajo
 */
public class GUITrainer extends Teacher {

    /**
     * Creates a GUI Trainer object with name, gender and age
     *
     * @param name   GUI Trainer's name
     * @param gender GUI Trainer's gender
     * @param age    GUI Trainer's age
     * @see Teacher#Teacher(String name, char gender, int age)
     */
    public GUITrainer(String name, char gender, int age) {
        super(name, gender, age);
    }

    /**
     * Checks whether or not the GUI Trainer can teach a certain subject
     *
     * @param subject a subject
     * @return true if the GUI Trainer can teach the subject, false otherwise
     */
    @Override
    public boolean canTeach(Subject subject) {

        int subjectSpecialism = subject.getSpecialism();
        return super.canTeach(subject) || (subjectSpecialism == 4);
    }

    /**
     * Returns a string containing the information of the GUI Trainer
     *
     * @return information of the GUI Trainer
     */
    @Override
    public String toString() {
        String guiTrainerIntro = "Sub-Instructor type: GUI TRAINER\n";
        return guiTrainerIntro + super.toString();
    }
}
