package ug.ac.vu.g01.library;

/**
 * Custom exception thrown when a student exceeds textbook checkout limits.
 * @author Tibarimbasa Isaac (Group 01)
 */
public class LoanLimitExceededException extends Exception {
    public LoanLimitExceededException(String msg) {
        super(msg);
    }
}