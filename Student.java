package ug.ac.vu.g01.students;

import ug.ac.vu.g01.core.Entity;

/**
 * Model class representing regular secondary school students at Celtec Academy.
 * @author Okello Solomon (Group 01)
 */
public class Student extends Entity {
    private String name;
    private int age;
    private String classLevel;
    private String stream;
    private double feesBalance;

    public Student(String id, String name, int age, String classLevel, String stream, double initialBalance) throws InvalidStudentDataException {
        super(id);
        setName(name);
        setAge(age);
        setClassLevel(classLevel);
        setStream(stream);
        setFeesBalance(initialBalance);
    }

    public String getName() { return name; }
    public void setName(String name) throws InvalidStudentDataException {
        if (name == null || name.trim().length() < 2) {
            throw new InvalidStudentDataException("Name must contain at least 2 characters.");
        }
        this.name = name.trim();
    }

    public int getAge() { return age; }
    public void setAge(int age) throws InvalidStudentDataException {
        if (age < 12 || age > 20) {
            throw new InvalidStudentDataException("Secondary student age must be between 12 and 20 years.");
        }
        this.age = age;
    }

    public String getClassLevel() { return classLevel; }
    public void setClassLevel(String classLevel) throws InvalidStudentDataException {
        if (classLevel == null || classLevel.isBlank()) {
            throw new InvalidStudentDataException("Class level cannot be blank (e.g. S1, S4).");
        }
        this.classLevel = classLevel.toUpperCase();
    }

    public String getStream() { return stream; }
    public void setStream(String stream) throws InvalidStudentDataException {
        if (stream == null || stream.isBlank()) {
            throw new InvalidStudentDataException("Stream cannot be blank (e.g. North, South).");
        }
        this.stream = stream.trim();
    }

    public double getFeesBalance() { return feesBalance; }
    public void setFeesBalance(double feesBalance) throws InvalidStudentDataException {
        if (feesBalance < 0) {
            throw new InvalidStudentDataException("Fees balance cannot be negative.");
        }
        this.feesBalance = feesBalance;
    }

    public void recordPayment(double amount) throws InvalidStudentDataException {
        if (amount <= 0) {
            throw new InvalidStudentDataException("Payment amount must be positive.");
        }
        this.feesBalance = Math.max(0, this.feesBalance - amount);
    }

    @Override
    public String getDetails() {
        return String.format("Student: %-18s | Class: %-2s (%-6s) | Age: %2d | Fees Owed: UGX %,10.2f",
                name, classLevel, stream, age, feesBalance);
    }
}