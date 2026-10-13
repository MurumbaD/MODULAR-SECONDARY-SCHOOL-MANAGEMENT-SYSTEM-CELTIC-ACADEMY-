package ug.ac.vu.g01.reports;

import java.io.*;
import java.util.*;
import ug.ac.vu.g01.students.*;
import ug.ac.vu.g01.marks.*;

/**
 * Service orchestrating report card compilation from marks data.
 * @author Mwesigwa Nekamia (Group 01)
 */
public class ReportCardService {
    private final List<ReportCard> reports = new ArrayList<>();
    private final StudentService studentService;
    private final MarksService marksService;
    private final String storageFile = "G01_Celtec_ReportCardsData.txt";

    public ReportCardService(StudentService studentService, MarksService marksService) {
        this.studentService = studentService;
        this.marksService = marksService;
        loadFromFile();
    }

    public ReportCard generateReportCard(String reportId, String studentId) throws Exception {
        Student s = studentService.findById(studentId);
        if (s == null) {
            throw new ReportGenerationException("Student record not found for ID: " + studentId);
        }

        List<SubjectMark> studentMarks = marksService.getMarksForStudent(studentId);
        if (studentMarks.isEmpty()) {
            throw new ReportGenerationException("No subject marks found for student ID: " + studentId);
        }

        double total = 0;
        for (SubjectMark m : studentMarks) {
            total += m.getScore();
        }
        double avg = total / studentMarks.size();

        String division;
        if (avg >= 75) division = "Division 1";
        else if (avg >= 60) division = "Division 2";
        else if (avg >= 45) division = "Division 3";
        else division = "Division 4";

        ReportCard card = new ReportCard(reportId, studentId, avg, division);
        reports.add(card);
        saveToFile();
        return card;
    }

    public List<ReportCard> getReportsSortedByAverage() {
        List<ReportCard> sorted = new ArrayList<>(reports);
        sorted.sort((r1, r2) -> Double.compare(r2.getAverageScore(), r1.getAverageScore()));
        return sorted;
    }

    private void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(storageFile))) {
            for (ReportCard r : reports) {
                writer.println(r.getId() + ";" + r.getStudentId() + ";" + r.getAverageScore() + ";" + r.getOverallDivision());
            }
        } catch (IOException e) {
            System.err.println("Warning: Could not save report card data: " + e.getMessage());
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
                    reports.add(new ReportCard(parts[0], parts[1], Double.parseDouble(parts[2]), parts[3]));
                }
            }
        } catch (Exception e) {
            System.out.println("Note: Initialized report card ledger.");
        }
    }
}