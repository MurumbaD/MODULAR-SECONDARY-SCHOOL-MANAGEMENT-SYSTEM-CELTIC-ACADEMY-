package ug.ac.vu.g01.reports;

/**
 * Custom exception for report card compiling errors.
 * @author Mwesigwa Nekamia (Group 01)
 */
public class ReportGenerationException extends Exception {
    public ReportGenerationException(String message) {
        super(message);
    }
}