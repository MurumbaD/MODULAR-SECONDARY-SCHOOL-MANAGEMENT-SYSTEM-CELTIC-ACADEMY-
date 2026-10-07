package ug.ac.vu.g01.fees;

import ug.ac.vu.g01.core.Entity;

/**
 * Fee payment entity linked to a secondary school student.
 * 
 * @author Ahmed Abdijabar Farah
 */
public class FeePayment extends Entity {
    private String studentId;
    private double amountPaid;
    private String paymentMethod;

    public FeePayment(String id, String studentId, double amountPaid, String paymentMethod) 
            throws InvalidFeePaymentException {
        super(id);
        setStudentId(studentId);
        setAmountPaid(amountPaid);
        setPaymentMethod(paymentMethod);
    }

    public String getStudentId() { return studentId; }

    public void setStudentId(String studentId) throws InvalidFeePaymentException {
        if (studentId == null || !studentId.startsWith("G01-")) {
            throw new InvalidFeePaymentException("Invalid Student ID cross-reference format.");
        }
        this.studentId = studentId;
    }

    public double getAmountPaid() { return amountPaid; }

    public void setAmountPaid(double amountPaid) throws InvalidFeePaymentException {
        if (amountPaid <= 0) {
            throw new InvalidFeePaymentException("Payment amount must be greater than UGX 0.");
        }
        this.amountPaid = amountPaid;
    }

    public String getPaymentMethod() { return paymentMethod; }

    public void setPaymentMethod(String paymentMethod) throws InvalidFeePaymentException {
        if (paymentMethod == null || paymentMethod.isBlank()) {
            throw new InvalidFeePaymentException("Payment method required.");
        }
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String getDetails() {
        return String.format("Receipt: %-12s | Student ID: %-10s | Paid: UGX %,10.2f | Method: %s",
                id, studentId, amountPaid, paymentMethod);
    }
}