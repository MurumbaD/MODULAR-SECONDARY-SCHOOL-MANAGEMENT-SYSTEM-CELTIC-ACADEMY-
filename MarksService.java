package ug.ac.vu.g01.marks;

import java.io.*;
import java.util.*;
import ug.ac.vu.g01.students.*;

/**
 * Service managing academic subject marks and file storage.
 * @author Babirye Shanilah (Group 01)
 */
public class MarksService {
    private final List<SubjectMark> marksList = new ArrayList<>();
    private final StudentService studentService;
    private final String storageFile = "G01_Celtec_MarksData.txt";

    public MarksService(StudentService studentService) {
        this.studentService = studentService;
        loadFromFile();
    }

    public void recordMark(String id, String studentId, String subject, double score) throws Exception {
        Student s = studentService.findById(studentId);
        if (s == null) {
            throw new MarksOutOfBoundsException("Cannot record marks: Student ID " + studentId + " not found.");
        }
        SubjectMark mark = new SubjectMark(id, studentId, subject, score);
        marksList.add(mark);
        saveToFile();
    }

    public List<SubjectMark> getMarksForStudent(String studentId) {
        List<SubjectMark> res = new ArrayList<>();
        for (SubjectMark m : marksList) {
            if (m.getStudentId().equalsIgnoreCase(studentId)) {
                res.add(m);
            }
        }
        return res;
    }

    public List<SubjectMark> getAllMarksSortedByScore() {
        List<SubjectMark> sorted = new ArrayList<>(marksList);
        sorted.sort((m1, m2) -> Double.compare(m2.getScore(), m1.getScore()));
        return sorted;
    }

    private void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(storageFile))) {
            for (SubjectMark m : marksList) {
                writer.println(m.getId() + ";" + m.getStudentId() + ";" + m.getSubjectName() + ";" + m.getScore());
            }
        } catch (IOException e) {
            System.err.println("Warning: Could not save marks data: " + e.getMessage());
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
                    marksList.add(new SubjectMark(parts[0], parts[1], parts[2], Double.parseDouble(parts[3])));
                }
            }
        } catch (Exception e) {
            System.out.println("Note: Initialized marks registry.");
        }
    }
}