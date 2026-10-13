package ug.ac.vu.g01.students;

/**
 * Subclass representing international secondary students.
 * @author Okello Solomon (Group 01)
 */
public class InternationalStudent extends Student {
    private String passportNumber;
    private String homeCountry;

    public InternationalStudent(String id, String name, int age, String classLevel, String stream, double initialBalance,
                                String passportNumber, String homeCountry) throws InvalidStudentDataException {
        super(id, name, age, classLevel, stream, initialBalance);
        setPassportNumber(passportNumber);
        setHomeCountry(homeCountry);
    }

    public String getPassportNumber() { 
        return passportNumber; 
    }

    public void setPassportNumber(String passportNumber) throws InvalidStudentDataException {
        if (passportNumber == null || passportNumber.isBlank()) {
            throw new InvalidStudentDataException("Passport number is required.");
        }
        this.passportNumber = passportNumber;
    }

    public String getHomeCountry() { 
        return homeCountry; 
    }

    public void setHomeCountry(String homeCountry) throws InvalidStudentDataException {
        if (homeCountry == null || homeCountry.isBlank()) {
            throw new InvalidStudentDataException("Home country cannot be blank.");
        }
        this.homeCountry = homeCountry;
    }

    @Override
    public String getDetails() {
        return super.getDetails() + String.format(" | [Intl: %s (%s)]", homeCountry, passportNumber);
    }
}