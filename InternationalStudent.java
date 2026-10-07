package ug.ac.vu.g01.students;

/**
 * Specialized subclass extending Student (Demonstrates Inheritance).
 * 
 * @author Kirabo Naume
 */
public class InternationalStudent extends Student {
    private String passportNumber;
    private String homeCountry;

    public InternationalStudent(String id, String name, int age, String classLevel, double initialBalance,
                                String passportNumber, String homeCountry) throws InvalidStudentDataException {
        super(id, name, age, classLevel, initialBalance);
        setPassportNumber(passportNumber);
        setHomeCountry(homeCountry);
    }

    public String getPassportNumber() { return passportNumber; }

    public void setPassportNumber(String passportNumber) throws InvalidStudentDataException {
        if (passportNumber == null || passportNumber.isBlank()) {
            throw new InvalidStudentDataException("Passport number is required for international students.");
        }
        this.passportNumber = passportNumber;
    }

    public String getHomeCountry() { return homeCountry; }

    public void setHomeCountry(String homeCountry) throws InvalidStudentDataException {
        if (homeCountry == null || homeCountry.isBlank()) {
            throw new InvalidStudentDataException("Country of origin cannot be blank.");
        }
        this.homeCountry = homeCountry;
    }

    @Override
    public String getDetails() {
        return super.getDetails() + String.format(" | [Intl: %s (%s)]", homeCountry, passportNumber);
    }
}