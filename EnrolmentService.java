package ug.ac.vu.g01.enrolment;

import java.io.*;
import java.util.*;
import ug.ac.vu.g01.students.*;

/**
 * Manages enrolment processing and file persistence.
 * @author Ahmed Abdijabar Farah (Group 01)
 */
public class EnrolmentService {
    private final List<EnrolmentRecord> enrolments = new ArrayList<>();
    private final StudentService studentService;
    private final String storageFile = "G01_Celtec_EnrolmentsData.txt";

    public EnrolmentService(StudentService studentService) {
        this.studentService = studentService;
        loadFromFile();
    }

    public void addEnrolment(String id, String studentId, String year, String term, String status) throws Exception {
        Student s = studentService.findById(studentId);
        if (s == null) {
            throw new EnrolmentException("Cannot enrol: Student ID " + studentId + " does not exist.");
        }
        EnrolmentRecord rec = new EnrolmentRecord(id, studentId, year, term, status);
        enrolments.add(rec);
        saveToFile();
    }

    public List<EnrolmentRecord> getAllEnrolmentsSortedByYear() {
        List<EnrolmentRecord> sorted = new ArrayList<>(enrolments);
        sorted.sort(Comparator.comparing(EnrolmentRecord::getAcademicYear).reversed());
        return sorted;
    }

    private void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(storageFile))) {
            for (EnrolmentRecord e : enrolments) {
                writer.println(e.getId() + ";" + e.getStudentId() + ";" + e.getAcademicYear() + ";" + e.getTerm() + ";" + e.getStatus());
            }
        } catch (IOException e) {
            System.err.println("Warning: Could not save enrolment data: " + e.getMessage());
        }
    }

    private void loadFromFile() {
        File file = new File(storageFile);
        if (!file.exists()) return;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length >= 5) {
                    enrolments.add(new EnrolmentRecord(parts[0], parts[1], parts[2], parts[3], parts[4]));
                }
            }
        } catch (Exception e) {
            System.out.println("Note: Initialized enrolment ledger.");
        }
    }
}