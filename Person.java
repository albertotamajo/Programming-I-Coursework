import java.io.Serializable;

/**
 * This abstract class represents a Person.<br>
 * It defines the basic properties of a person such as  name, gender and age.<br>
 * The classes {@link Student} and {@link Instructor} are based on this class.
 *
 * @author albertoTamajo
 */
public abstract class Person implements Serializable, Comparable<Person> {

    private String name;
    private char gender;
    private int age;


    /**
     * Creates a Person object with name, gender and age.<br>
     * If the gender of the person provided is not neither 'M' nor 'F',
     * it will be set to '*'.<br>
     * If the age of the person is not valid, the age will be set to '-1'.
     *
     * @param name   person's name
     * @param gender person's gender. 'M' for males and 'F' for females
     * @param age    person's age
     */
    public Person(String name, char gender, int age) {

        this.name = name;

        //If the gender of the person is either M or F
        if (gender == 'M' || gender == 'F') {

            this.gender = gender;

        } else {

            //If the gender of the person is not valid
            System.err.println("**The gender of a person can only be either 'M' or 'F'**");

            this.gender = '*';
        }

        //If the age of the person is negative or greater than 130
        if ((age < 0) || (age > 130)) {

            System.err.println("**The age of the person is not valid**");

            //Indicates that the person's age was not set properly
            this.age = -1;

        } else {
            //If the age of the person is valid
            this.age = age;
        }
    }

    /**
     * Creates a Person object with name and gender.<br>
     * If the gender of the person provided is not neither 'M' nor 'F',
     * it will be set to '*'.<br>
     *
     * @param name   person's name
     * @param gender person's gender
     */
    public Person(String name, char gender) {

        this.name = name;

        //If the gender of the person is either M or F
        if (gender == 'M' || gender == 'F') {

            this.gender = gender;

        } else {

            //If the gender of the person is not valid
            System.err.println("**The gender of a person can only be either 'M' or 'F'**");

            this.gender = '*';
        }
    }

    /**
     * Gets the name of the person
     *
     * @return a String containing the name of the person
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the gender of the person
     *
     * @return a char: 'M' if the person is a male, 'F' if the person is a female,
     * '*' if the gender is unknown
     */
    public char getGender() {
        return gender;
    }

    /**
     * Gets the age of the person
     *
     * @return an int containing the age of the person.<br>
     * A value equal to '-1' indicates that the age is unknown
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age of the person.<br>
     * If the age of the person is not valid, the age will be set to '-1'.
     *
     * @param age person's age
     */
    public void setAge(int age) {

        //If the age of the person is negative or greater than 130
        if ((age < 0) || (age > 130)) {

            System.err.println("**The age of the person is not valid**");

            //Indicates that the person's age was not set properly
            this.age = -1;

        } else {
            //If the age of the person is valid
            this.age = age;
        }
    }

    /**
     * Returns a string containing the information about the person
     *
     * @return a string containing the name, gender and age of the person
     */
    @Override
    public String toString() {

        String name = "Name: " + this.name + "\n";
        String gender = "Gender: " + this.gender + "\n";
        String age = "Age: " + +this.age + "\n";
        return name + gender + age;
    }

    /**
     * Compares the names ({@link String} objects) of two Person objects lexicographically
     *
     * @param o a person whose name is compared
     * @return see <code>String.compareTo()</code>
     * @see String#compareTo(String)
     */
    @Override
    public int compareTo(Person o) {
        return this.name.compareTo(o.name);
    }

}
