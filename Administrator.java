import java.io.*;
import java.util.*;

/**
 * This class represents an administrator and is connected to a school.<br>
 * The administrator is responsible for organising the typical day of school and running the school simulation.
 *
 * @author albertoTamajo
 */
public class Administrator {


    private School school;


    /**
     * Creates an administrator with a default school
     */
    public Administrator() {
        this.school = new School();
    }

    /**
     * Creates an administrator with a school to administrate
     *
     * @param school school to administrate
     */
    public Administrator(School school) {
        this.school = school;
    }

    /**
     * Sets the school to administrate
     *
     * @param school school to administrate
     */
    private void setSchool(School school) {
        this.school = school;
    }

    /**
     * This method is responsible of running the school simulation
     *
     * @param args
     */
    public static void main(String[] args) {

        Administrator administrator = new Administrator();

        Scanner scanner = new Scanner(System.in);

        System.out.println("\n\nWELCOME TO THIS JAVA SCHOOL SIMULATION!");
        System.out.println("-----------------------------------------\n");
        System.out.println("This simulation allows you to either enter a simulation file or to continue a simulation already started");

        //Loops until the user enters a correct input
        while (true) {

            System.out.println("Enter 'SF' if you want to enter a simulation file or 'C' if you want to continue a simulation");
            String userInput = scanner.nextLine();

            //Tries that the user enters a valid input
            try {

                //If the user wants to enter a simulation file
                if (userInput.equals("SF")) {

                    administrator.fileSimulationTool();
                    int days = administrator.daysSelection();

                    //Runs the school simulation
                    administrator.run(days);

                    administrator.save();

                } else if (userInput.equals("C")) {        //If the user wants to continue a simulation already started

                    //Loads a saved simulation file
                    School school = administrator.load();

                    administrator.setSchool(school);
                    int days = administrator.daysSelection();

                    //Runs the school simulation
                    administrator.run(days);

                    administrator.save();

                } else {

                    //If the user input is not valid
                    throw new InputMismatchException();
                }

            } catch (InputMismatchException e) {
                System.err.println("Sorry, your input is not correct, retry");
            }
        }
    }

    /**
     * Reads a simulation file (with a specific formatting) in order to assign a
     * {@link School} to the administrator and assign {@link Student}, {@link Subject},
     * {@link Instructor} and {@link Course} objects to the school.<br>
     * A simulation file with no subjects listed won't be accepted.
     */
    private void fileSimulationTool() {

        Scanner scanner = new Scanner(System.in);
        String line;
        BufferedReader bufferedReader;

        //Loops until a file is read successfully and a simulation can be executed
        while (true) {

            //Tries to read a simulation file successfully
            try {

                System.out.println("Write the file path of the simulation file\n");
                String filePath = scanner.nextLine();
                bufferedReader = new BufferedReader(new FileReader(filePath));

                //Loops until there is a text line in the file
                while ((line = bufferedReader.readLine()) != null) {

                    String[] strings = line.split(":|,");

                    //Generates an object of the same class specified by the line
                    fromTextLineToObjectGenerator(strings);
                }

                bufferedReader.close();

                //If there are no subjects provided an exception is thrown
                //because it is not possible to run a simulation with no subject taught in a school
                if (school.getSubjects().isEmpty()) {
                    throw new SubjectsNotFoundException();
                }

                //Lets the user write the name of the school
                //If not provided in the simulation file
                if (school.getName().equals("Default")) {

                    System.err.println("You have not provided a school in your file\n");
                    System.out.println("Choose the name of your school\n");
                    String schoolName = scanner.nextLine();
                    school.setName(schoolName);
                }

                break;


            } catch (FileNotFoundException e) {

                //The simulation file is not found
                System.err.println("Your source has not been found, retry");

            } catch (SubjectsNotFoundException e) {

                //Subjects are not provided by the simulation file
                System.err.println(e.getMessage());

            } catch (Exception e) {

                //General exception
                System.err.println("Error");
            }
        }
    }


    /**
     * Returns the number of days of the simulation.<br>
     * The number of days is chosen by the user through a command line input.
     *
     * @return the number of days of the simulation
     */
    private int daysSelection() {

        Scanner scanner = new Scanner(System.in);

        //Loops until the user chooses an input greater than 0
        while (true) {

            //Tries that the user enters an input greater than 0
            try {

                System.out.println("Enter the number of days of the simulation\n");
                int days = scanner.nextInt();

                //If the input is greater than 0
                if (days > 0) {
                    return days;
                } else {
                    //The number of days cannot be negative
                    throw new InputMismatchException();
                }

            } catch (InputMismatchException e) {
                System.err.println("Sorry, the number of days must be greater than 0, retry");
            }
        }
    }

    /**
     * Generates an object from a text line provided by a simulation file (with a specific formatting).<br>
     * The objects that this method can create are:<br>
     * a. {@link School}<br>
     * b. {@link Subject}<br>
     * c. all subclasses of {@link Person}<br>
     *
     * @param string text line containing object information
     */
    private void fromTextLineToObjectGenerator(String[] string) {


        switch (string[0]) {

            //If the line refers to a school
            case "school":
                school.setName(string[1]);
                break;

            //If the line refers to a subject
            case "subject": {

                //Tries to create a subject and add it to the school
                try {

                    String description = string[1];
                    int id = Integer.parseInt(string[2]);
                    int specialism = Integer.parseInt(string[3]);
                    int duration = Integer.parseInt(string[4]);

                    //If the prerequisites have been provided by the line
                    if (string.length > 5) {

                        ArrayList<Integer> prerequisites = new ArrayList<>();

                        //Loops until all prerequisites are added to the arrayList prerequisites
                        for (int i = 5; i < string.length; i++) {

                            //Tries that every argument provided as prerequisite is a value int
                            try {

                                prerequisites.add(Integer.parseInt(string[i]));

                            } catch (NumberFormatException e) {

                                //The argument provided was not a value int
                                System.err.println("Sorry, a prerequisite was expected to be a value int\n");
                            }
                        }

                        Subject subject = new Subject(id, specialism, duration);
                        subject.setDescription(description);
                        subject.setPrerequisites(prerequisites);

                        //The subject is added to the school
                        this.school.add(subject);


                    } else {        //If no prerequisites are provided

                        Subject subject = new Subject(id, specialism, duration);
                        subject.setDescription(description);

                        //The subject is added to the school
                        this.school.add(subject);
                    }


                } catch (IndexOutOfBoundsException e) {

                    //More arguments were expected
                    System.err.println("Sorry, this subject cannot be created because more arguments were expected\n");

                } catch (NumberFormatException e) {

                    //An argument of value int was expected
                    System.err.println("Sorry, this subject cannot be created because arguments of value int were expected\n");

                } catch (Exception e) {

                    //A general error
                    System.err.println("Sorry, an error has occurred\n");
                }

                break;
            }

            //If the line refers to a student
            case "student": {

                //Tries to create a student and add it to the school
                try {

                    String name = string[1];
                    char gender = string[2].charAt(0);
                    int age = Integer.parseInt(string[3]);
                    this.school.add(new Student(name, gender, age));

                } catch (IndexOutOfBoundsException e) {

                    //More arguments were expected
                    System.err.println("Sorry, this student cannot be created because more arguments were expected\n");

                } catch (NumberFormatException e) {

                    //An argument of value int was expected
                    System.err.println("Sorry, this student cannot be created because a value of type int was expected\n");

                } catch (Exception e) {

                    //A general error
                    System.err.println("Sorry, an error has occurred\n");
                }

                break;
            }

            //If the line refers to a teacher
            case "Teacher": {

                //Tries to create a teacher and add it to the school
                try {

                    String name = string[1];
                    char gender = string[2].charAt(0);
                    int age = Integer.parseInt(string[3]);
                    this.school.add(new Teacher(name, gender, age));

                } catch (IndexOutOfBoundsException e) {

                    //More arguments were expected
                    System.err.println("Sorry, this teacher cannot be created because more arguments were expected\n");

                } catch (NumberFormatException e) {

                    //An argument of value int was expected
                    System.err.println("Sorry, this teacher cannot be created because a value of type int was expected\n");
                } catch (Exception e) {

                    //A general error
                    System.err.println("Sorry, an error has occurred\n");
                }

                break;
            }

            //If the line refers to a demonstrator
            case "Demonstrator": {

                //Tries to create demonstrator and add it to the school
                try {

                    String name = string[1];
                    char gender = string[2].charAt(0);
                    int age = Integer.parseInt(string[3]);
                    this.school.add(new Demonstrator(name, gender, age));

                } catch (IndexOutOfBoundsException e) {

                    //More arguments were expected
                    System.err.println("Sorry, this demonstrator cannot be created because more arguments were expected\n");

                } catch (NumberFormatException e) {

                    //An argument of value int was expected
                    System.err.println("Sorry, this demonstrator cannot be created because a value of type int was expected\n");
                } catch (Exception e) {

                    //A general error
                    System.err.println("Sorry, an error has occurred\n");
                }
                break;
            }

            //If the line refers to an OO Trainer
            case "OOTrainer": {

                //Tries to create an OO trainer and add it to the school
                try {
                    String name = string[1];
                    char gender = string[2].charAt(0);
                    int age = Integer.parseInt(string[3]);
                    this.school.add(new OOTrainer(name, gender, age));

                } catch (IndexOutOfBoundsException e) {

                    //More arguments were expected
                    System.err.println("Sorry, this OOTrainer cannot be created because more arguments were expected\n");

                } catch (NumberFormatException e) {

                    //An argument of value int was expected
                    System.err.println("Sorry, this OOTrainer cannot be created because a value of type int was expected\n");

                } catch (Exception e) {

                    //A general error
                    System.err.println("Sorry, an error has occurred\n");
                }

                break;
            }

            //If the line refers to a GUI trainer
            case "GUITrainer": {

                //Tries to create a GUI trainer and add it to the school
                try {

                    String name = string[1];
                    char gender = string[2].charAt(0);
                    int age = Integer.parseInt(string[3]);
                    this.school.add(new GUITrainer(name, gender, age));

                } catch (IndexOutOfBoundsException e) {

                    //More arguments were expected
                    System.err.println("Sorry, this GUITrainer cannot be created because more arguments were expected\n");

                } catch (NumberFormatException e) {

                    //An argument of value int was expected
                    System.err.println("Sorry, this GUITrainer cannot be created because a value of type int was expected\n");

                } catch (Exception e) {

                    //A general error
                    System.err.println("Sorry, an error has occurred\n");
                }
                break;
            }

            default:
                System.err.println("Invalid Input\n");
        }


    }


    /**
     * Runs a school simulation.<br>
     * A school simulation consists of the following events:<br>
     * 1. Up to 2 students will join the school<br>
     * 2. A new instructor may join the school<br>
     * 3. {@link School#aDayAtSchool()}<br>
     * 4. A free instructor might leave the school<br>
     * 5. A student with all certificates will leave the school<br>
     * 6. A student not enrolled in a course may leave the school
     */
    public void run() {

        //students enrolling
        int studentsEnrolling = numberOfStudentsEnrolling();
        studentGenerator(studentsEnrolling);


        //instructors joining
        instructorGenerator(0.2, 0.1, 0.05, 0.05);

        //Simulation of a day of school
        school.aDayAtSchool();


        //Instructors with no courses leaving
        instructorsLeaving(20);


        //Students with all certificates leaving
        removeStudentsWithAllCertificates();


        //Students not attending leaving
        studentsLeaving(5);


    }

    /**
     * Runs a school simulation.<br>
     * This method is an overloaded version of {@link #run()}
     * because in addition it lets the user decide the number of simulation days.
     *
     * @param days number of simulation days
     */
    public void run(int days) {

        //Loops until all days are simulated
        for (int i = 0; i < days; i++) {

            System.out.println("\n+---------------------------------+");
            System.out.println("**A new day of school !!!**\n**Day " + school.getDaysRunning() + "**");

            //Recap of what happened the day before

            System.out.println("**Recap of day " + (school.getDaysRunning() - 1) + "**\n");

            String info = school.toString();
            System.out.println(info);

            System.out.println("\n \n \n \n ---------------------------------------------------------");


            //Current events occurring at the school

            System.out.println("**Today's notifications**\n");


            //students enrolling
            try {

                Thread.sleep(2000);
                int studentsEnrolling = numberOfStudentsEnrolling();
                studentGenerator(studentsEnrolling);

            } catch (InterruptedException e) {
            }


            //instructors notification
            try {

                Thread.sleep(2000);
                instructorGenerator(0.2, 0.1, 0.05, 0.05);

            } catch (InterruptedException e) {
            }


            //Simulation of the school
            try {

                Thread.sleep(2000);
                school.aDayAtSchool();

            } catch (InterruptedException e) {
            }


            //Instructors with no courses leaving
            try {

                Thread.sleep(2000);
                instructorsLeaving(20);

            } catch (InterruptedException e) {
            }


            //Students with all certificates leaving
            try {

                Thread.sleep(2000);
                removeStudentsWithAllCertificates();

            } catch (InterruptedException e) {
            }


            //Students not attending leaving
            try {

                Thread.sleep(2000);
                studentsLeaving(5);

            } catch (InterruptedException e) {
            }

            //End of the day
            try {

                Thread.sleep(2000);
                System.out.println("**End of the day\n");

            } catch (InterruptedException e) {
            }

        }
    }


    /**
     * Generates the instructors joining the school<br>.
     * Each type of instructor joins the school according to a probability<br>.
     * The probability of each instructor joining must range from 0.0 to 1.0 included
     *
     * @param probabilityTeacher      teacher's probability of joining the school
     * @param probabilityDemonstrator demonstrator's probability of joining the school
     * @param probabilityOOTrainer    OO trainer's probability of joining the school
     * @param probabilityGuiTrainer   GUI trainer's probability of joining the school
     */
    private void instructorGenerator(double probabilityTeacher, double probabilityDemonstrator, double probabilityOOTrainer, double probabilityGuiTrainer) {

        String intro = "**An instructor is joining**\n";
        HashSet<Instructor> instructors = school.getInstructors();

        //If the probability of a teacher joining is not more than 1
        //If the random number generated is less than or equal to the teacher's probability
        if ((probabilityTeacher <= 1) && (randomDoubleNumberGenerator() <= probabilityTeacher)) {

            System.out.println(intro);

            //Generates the gender
            char gender = genderGenerator();

            Instructor teacher = new Teacher(nameGenerator(gender), gender, ageGenerator());
            printsOutInfo(teacher);
            instructors.add(teacher);

        }

        //If the probability of a demonstrator joining is not more than 1
        //If the random number generated is less than or equal to the demonstrator's probability
        if ((probabilityDemonstrator <= 1) && (randomDoubleNumberGenerator() <= probabilityDemonstrator)) {

            System.out.println(intro);

            //Generates the gender
            char gender = genderGenerator();

            Instructor demonstrator = new Demonstrator(nameGenerator(gender), gender, ageGenerator());
            printsOutInfo(demonstrator);
            instructors.add(demonstrator);
        }

        //If the probability of an OO trainer joining is not more than 1
        //If the random number generated is less than or equal to the OO trainer's probability
        if ((probabilityOOTrainer <= 1) && (randomDoubleNumberGenerator() <= probabilityOOTrainer)) {

            System.out.println(intro);

            //Generates the gender
            char gender = genderGenerator();

            Instructor ooTrainer = new OOTrainer(nameGenerator(gender), gender, ageGenerator());
            printsOutInfo(ooTrainer);
            instructors.add(ooTrainer);
        }

        //If the probability of a GUI trainer joining is not more than 1
        //If the random number generated is less than or equal to the GUI trainer's probability
        if ((probabilityGuiTrainer <= 1) && (randomDoubleNumberGenerator() <= probabilityGuiTrainer)) {

            System.out.println(intro);

            //Generates the gender
            char gender = genderGenerator();

            Instructor guiTrainer = new GUITrainer(nameGenerator(gender), gender, ageGenerator());
            printsOutInfo(guiTrainer);
            instructors.add(guiTrainer);
        }

    }

    /**
     * Returns a random number (ranging from 0 to 2 included) of new students enrolling
     *
     * @return the number of new students enrolling
     */
    private int numberOfStudentsEnrolling() {

        return randomIntegerNumberGenerator(3);
    }


    /**
     * Returns a random integer number ranging from 0 to the value of
     * the parameter <code>upperBound</code> excluded
     *
     * @param upperBound upperBound of the range of possible numbers
     * @return a random number
     */
    private int randomIntegerNumberGenerator(int upperBound) {

        Random random = new Random();
        return random.nextInt(upperBound);
    }

    /**
     * Returns a random double number between 0.0 and 1.0
     *
     * @return a random number between 0.0 and 1.0
     */
    private double randomDoubleNumberGenerator() {
        Random random = new Random();
        return random.nextDouble();
    }


    /**
     * Generates which instructors not teaching will leave
     * the school according to a probability.
     *
     * @param probability probability that an instructor not teaching leaves the school
     */
    private void instructorsLeaving(int probability) {

        HashSet<Instructor> instructors = school.getInstructors();
        Iterator<Instructor> instructorsIterator = instructors.iterator();

        //Loops for every instructor
        //If the instructor is not teaching, he/she may leave the school
        while (instructorsIterator.hasNext()) {

            Instructor nextInstructor = instructorsIterator.next();

            //If the instructor is not teaching
            if (!(nextInstructor.isTeaching())) {

                int randomNumber = randomIntegerNumberGenerator(100);

                //If the random number generated is less than or equal to the probability of leaving
                if (randomNumber <= probability) {

                    System.out.println("**This instructor is leaving the school**\n");
                    printsOutInfo(nextInstructor);
                    instructorsIterator.remove();
                }
            }
        }
    }


    /**
     * Removes the students with all certificates from the school
     */
    private void removeStudentsWithAllCertificates() {

        HashSet<Student> students = school.getStudents();
        Iterator<Student> studentsIterator = students.iterator();

        int numberSubjects = school.getSubjects().size();

        //Loops for all students
        //Students with all certificates will be removed
        while (studentsIterator.hasNext()) {

            Student nextStudent = studentsIterator.next();
            int studentCertificates = nextStudent.getCertificates().size();

            //If the students has got all the certificates
            if (studentCertificates == numberSubjects) {

                System.out.println("**A student has completed all the courses**");
                printsOutInfo(nextStudent);

                //Removes the student from the school
                studentsIterator.remove();

            }
        }
    }


    /**
     * Generates which students not enrolled in a course
     * will leave the school according to a probability
     *
     * @param probability probability that a student not enrolled in a course will leave the school
     */
    private void studentsLeaving(int probability) {

        HashSet<Student> students = school.getStudents();
        Iterator<Student> studentsIterator = students.iterator();

        //Loops for all students
        //Students that are not enrolled may leave the school
        while (studentsIterator.hasNext()) {

            Student nextStudent = studentsIterator.next();

            //If the student is not enrolled
            if (!(nextStudent.isEnrolled())) {

                int randomNumber = randomIntegerNumberGenerator(100);

                //If the random number generated is less than or equal to the probability of leaving
                if (randomNumber <= probability) {

                    System.out.println("**A student is leaving**");
                    printsOutInfo(nextStudent);

                    //Removes the student
                    studentsIterator.remove();

                }
            }
        }
    }

    /**
     * Generates randomly the gender of a person
     *
     * @return 'M' or 'F'
     */
    private char genderGenerator() {

        int randomNumber = randomIntegerNumberGenerator(2);

        if (randomNumber == 0) {
            return 'M';
        } else {
            return 'F';
        }
    }

    /**
     * Generates randomly the age of a person.<br>
     * The possible range is from 16 to 65 years old
     *
     * @return the age of a person
     */
    private int ageGenerator() {

        //the min age is 16 and the max age is 65
        return randomIntegerNumberGenerator(50) + 16;
    }


    /**
     * Generates a number of students according to the value of the <code>randomNumber</code> parameter
     *
     * @param randomNumber number of students to generate
     */
    private void studentGenerator(int randomNumber) {

        //Loops until all students are generated
        for (int i = 1; i <= randomNumber; i++) {

            System.out.println("**A new student has enrolled today**\n");

            //Generates the gender
            char gender = genderGenerator();
            Student newStudent = new Student(nameGenerator(gender), gender, ageGenerator());

            printsOutInfo(newStudent);

            //Adds the student to the school
            school.getStudents().add(newStudent);
        }
    }


    /**
     * Generates a full name according to the gender of a person
     *
     * @param gender gender of the person, 'M' or 'F'
     * @return a string containing the full name of the person
     */
    private String nameGenerator(char gender) {

        String[] maleNames = {"Albert", "Logan ", "Ethan ", "Daniel", "Carlos", "Anthony", "Paul", "Charles"};
        String[] femaleNames = {"Amelia", "Olivia", "Isabella", "Mia", "Isabel", "Ana", "Alessia", "Fiona"};
        String[] familyNames = {"Miller", "Williams", "Clark", "Hall", "Bell", "Russell", "Tamajo", "Kuhn"};

        int sizeNames = maleNames.length;
        int sizeSurname = familyNames.length;

        int randomNumberNames = randomIntegerNumberGenerator(sizeNames);
        int randomNumberSurnames = randomIntegerNumberGenerator(sizeSurname);

        //If the person is a male
        if (gender == 'M') {

            String name = maleNames[randomNumberNames];
            String surname = familyNames[randomNumberSurnames];
            return name + " " + surname;

        } else {     //If the person is a female

            String name = femaleNames[randomNumberNames];
            String surname = familyNames[randomNumberSurnames];
            return name + " " + surname;
        }

    }


    /**
     * Prints out the info of any object
     *
     * @param o object whose information is needed
     */
    private void printsOutInfo(Object o) {
        System.out.println(o.toString());
    }

    /**
     * Lets the user save the state of the simulation.<br>
     * In the case the user decides to save the simulation,
     * he/her will have to write the path of where to save the file.<br>
     * The name of the file will be the following: javaSchoolSavedData.txt
     */
    public void save() {

        Scanner scanner = new Scanner(System.in);

        //Loops until the user enters a valid input
        while (true) {

            System.out.println("The simulation has finished, enter 'S' to save the simulation or 'T' to terminate without saving");

            //Tries that the user enters a valid input
            try {

                String userInput = scanner.nextLine();


                //If the user wants to save the simulation status
                if (userInput.equals("S")) {

                    //Loops until no error occurs
                    while (true) {

                        System.out.println("Write the name of the text file you want to save or a file path if you want to save the file in a specific destination");
                        String filePath = scanner.nextLine();

                        //Tries to save the simulation
                        try {

                            ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream(filePath));
                            objOut.writeObject(school);
                            objOut.close();
                            System.exit(0);

                        } catch (FileNotFoundException e) {
                            System.err.println("Sorry, your file path does not exist, retry");
                        } catch (IOException e) {
                            System.err.println("Sorry, an error has occurred, retry");
                        }
                    }

                } else if (userInput.equals("T")) {         //If the user does not want to save the simulation

                    System.out.println("GoodBye");
                    System.exit(0);

                } else {

                    //No valid input entered
                    throw new InputMismatchException();
                }

            } catch (InputMismatchException e) {
                System.err.println("Sorry, your input is not valid, retry");
            }
        }

    }

    /**
     * Lets the user load the state of a saved simulation.<br>
     * The method will return a {@link School} object which can be linked
     * to an {@link Administrator} object in order to start a new simulation.
     *
     * @return a {@link School} object
     */
    public School load() {

        Scanner scanner = new Scanner(System.in);

        //Loops until a correct school object is loaded
        while (true) {

            System.out.println("Write the file path of the file you want to load");
            String filePath = scanner.nextLine();

            //Tries to read a school object from a text file
            try {

                ObjectInputStream objIn = new ObjectInputStream(new FileInputStream(filePath));
                School school = (School) objIn.readObject();
                return school;

            } catch (FileNotFoundException e) {
                System.err.println("Sorry, your file path does not exist");
            } catch (IOException e) {
                System.err.println("Sorry, an error has occurred");
            } catch (ClassNotFoundException e) {
                System.err.println("Sorry, your object has not been found");
            }
        }

    }

}
