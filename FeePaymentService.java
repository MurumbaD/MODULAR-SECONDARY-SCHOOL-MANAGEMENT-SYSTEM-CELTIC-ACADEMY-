package ug.ac.vu.g01.fees;

import ug.ac.vu.g01.students.Student;
import ug.ac.vu.g01.students.StudentService;

import java.io.*;
import java.util.*;

/**
 * Service orchestrating payments and directly modifying linked Student object balances.
 * Demonstrates Module Interoperability (Fees <-> Students).
 * 
 * @author Ahmed Abdijabar Farah
 */
public class FeePaymentService {
    private final List<FeePayment> payments = new ArrayList<>();
    private final StudentService studentService;
    private final String storageFile = "G01_FeesData.txt";

    public FeePaymentService(StudentService studentService) {
        this.studentService = studentService;
        loadFromFile();
    }

    public void processPayment(String paymentId, String studentId, double amount, String method) 
            throws Exception {
        Student student = studentService.findById(studentId);
        if (student == null) {
            throw new InvalidFeePaymentException("Transaction failed: Secondary student ID " + studentId + " not found.");
        }

        FeePayment payment = new FeePayment(paymentId, studentId, amount, method);
        student.recordPayment(amount); // Inter-module invocation updating student balance
        payments.add(payment);
        saveToFile();
    }

    public List<FeePayment> getPaymentsSortedByAmount() {
        List<FeePayment> sorted = new ArrayList<>(payments);
        sorted.sort((p1, p2) -> Double.compare(p2.getAmountPaid(), p1.getAmountPaid()));
        return sorted;
    }

    private void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(storageFile))) {
            for (FeePayment p : payments) {
                writer.println(p.getId() + ";" + p.getStudentId() + ";" + p.getAmountPaid() + ";" + p.getPaymentMethod());
            }
        } catch (IOException e) {
            System.err.println("Warning: Fees file save failed: " + e.getMessage());
        }
    }

    private void loadFromFile() {
        File file = new File(storageFile);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length >= 4) {
                    payments.add(new FeePayment(parts[0], parts[1], Double.parseDouble(parts[2]), parts[3]));
                }
            }
        } catch (Exception e) {
            System.out.println("Note: Initialized fee payment ledger cleanly.");
        }
    }
}