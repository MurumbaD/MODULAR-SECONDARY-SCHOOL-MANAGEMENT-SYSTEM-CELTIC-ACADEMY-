package ug.ac.vu.g01.students;

/**
 * Custom domain exception thrown when student parameters breach business constraints.
 * 
 * @author Kirabo Naume
 */
public class InvalidStudentDataException extends Exception {
    public InvalidStudentDataException(String message) {
        super(message);
    }
}