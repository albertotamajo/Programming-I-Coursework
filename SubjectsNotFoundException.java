public class SubjectsNotFoundException extends Exception {

    @Override
    public String getMessage() {
        return "Sorry, the file you have provided does not contain a subject\nIt is not possible to run the simulation" +
                " with no subject";
    }
}
