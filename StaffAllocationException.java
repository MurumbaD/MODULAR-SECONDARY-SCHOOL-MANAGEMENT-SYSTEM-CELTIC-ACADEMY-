package ug.ac.vu.g01.staff;

/**
 * Exception for staff registration and allocation errors.
 * @author Tibarimbasa Isaac (Group 01)
 */
public class StaffAllocationException extends Exception {
    public StaffAllocationException(String msg) {
        super(msg);
    }
}