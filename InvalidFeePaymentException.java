package ug.ac.vu.g01.fees;

/**
 * Custom Exception for Fee transaction violations.
 * 
 * @author Ahmed Abdijabar Farah
 */
public class InvalidFeePaymentException extends Exception {
    public InvalidFeePaymentException(String msg) {
        super(msg);
    }
}