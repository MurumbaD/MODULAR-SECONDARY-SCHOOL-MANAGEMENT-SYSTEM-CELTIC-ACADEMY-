package ug.ac.vu.g01.students;

import ug.ac.vu.g01.core.Entity;

/**
 * Encapsulated model class representing a secondary school student.
 * 
 * @author Kirabo Naume
 */
public class Student extends Entity {
    private String name;
    private int age;
    private String classLevel; // S1 to S6
    private double feesBalance;

    public Student(String id, String name, int age, String classLevel, double initialBalance) 
            throws InvalidStudentDataException {
        super(id);
        setName(name);
        setAge(age);
        setClassLevel(classLevel);
        setFeesBalance(initialBalance);
    }

    public String getName() { return name; }
    
    public void setName(String name) throws InvalidStudentDataException {
        if (name == null || name.trim().length() < 2) {
            throw new InvalidStudentDataException("Student name must contain at least 2 characters.");
        }
        this.name = name.trim();
    }

    public int getAge() { return age; }

    public void setAge(int age) throws InvalidStudentDataException {
        if (age < 11 || age > 20) { // Enforces secondary school age limits
            throw new InvalidStudentDataException("Invalid secondary student age: Must be between 11 and 20 years.");
        }
        this.age = age;
    }

    public String getClassLevel() { return classLevel; }

    public void setClassLevel(String classLevel) throws InvalidStudentDataException {
        if (classLevel == null || classLevel.isBlank()) {
            throw new InvalidStudentDataException("Secondary class level designation cannot be blank.");
        }
        this.classLevel = classLevel;
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
            throw new InvalidStudentDataException("Payment amount must be greater than zero.");
        }
        this.feesBalance -= amount;
    }

    @Override
    public String getDetails() {
        return String.format("Student: %-20s | Age: %2d | Class: %-5s | Term Fees Owed: UGX %,10.2f",
                name, age, classLevel, feesBalance);
    }
}