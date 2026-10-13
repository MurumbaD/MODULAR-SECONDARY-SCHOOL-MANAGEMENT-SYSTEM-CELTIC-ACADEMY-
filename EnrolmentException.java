package ug.ac.vu.g01.enrolment;

/**
 * Custom exception for enrolment validation and processing errors.
 * @author Ahmed Abdijabar Farah (Group 01)
 */
public class EnrolmentException extends Exception {
    public EnrolmentException(String message) {
        super(message);
    }
}