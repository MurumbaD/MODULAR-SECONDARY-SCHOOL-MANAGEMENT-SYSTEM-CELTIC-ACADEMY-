package ug.ac.vu.g01.students;

/**
 * Subclass representing resident boarding students at Celtec Academy Secondary School.
 * @author Okello Solomon (Group 01)
 */
public class BoardingStudent extends Student {
    private String dormitoryName;
    private int bedSpaceNumber;

    public BoardingStudent(String id, String name, int age, String classLevel, String stream, double initialBalance,
                           String dormitoryName, int bedSpaceNumber) throws InvalidStudentDataException {
        super(id, name, age, classLevel, stream, initialBalance);
        setDormitoryName(dormitoryName);
        setBedSpaceNumber(bedSpaceNumber);
    }

    public String getDormitoryName() { 
        return dormitoryName; 
    }

    public void setDormitoryName(String dormitoryName) throws InvalidStudentDataException {
        if (dormitoryName == null || dormitoryName.isBlank()) {
            throw new InvalidStudentDataException("Dormitory name is required for boarding students.");
        }
        this.dormitoryName = dormitoryName.trim();
    }

    public int getBedSpaceNumber() { 
        return bedSpaceNumber; 
    }

    public void setBedSpaceNumber(int bedSpaceNumber) throws InvalidStudentDataException {
        if (bedSpaceNumber <= 0) {
            throw new InvalidStudentDataException("Bed space number must be a positive integer.");
        }
        this.bedSpaceNumber = bedSpaceNumber;
    }

    @Override
    public String getDetails() {
        return super.getDetails() + String.format(" | [Boarder: %s Dorm, Bed #%d]", dormitoryName, bedSpaceNumber);
    }
}