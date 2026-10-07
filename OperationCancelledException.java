package ug.ac.vu.g01.core;

/**
 * Custom exception thrown when a user explicitly chooses to cancel an ongoing CLI input prompt.
 * 
 * @author Group 01 Dev Team
 */
public class OperationCancelledException extends Exception {
    public OperationCancelledException() {
        super("Operation cancelled by user.");
    }

    public OperationCancelledException(String message) {
        super(message);
    }
}