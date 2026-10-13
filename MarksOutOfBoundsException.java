package ug.ac.vu.g01.marks;

/**
 * Exception thrown when exam scores fall outside the standard secondary school grading range (0 - 100).
 * @author Babirye Shanilah (Group 01)
 */
public class MarksOutOfBoundsException extends Exception {
    public MarksOutOfBoundsException(String message) {
        super(message);
    }
}