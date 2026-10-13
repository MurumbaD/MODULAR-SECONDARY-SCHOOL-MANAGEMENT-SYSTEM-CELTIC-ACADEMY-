package ug.ac.vu.g01.marks;

import ug.ac.vu.g01.core.Entity;

/**
 * Represents exam score entries for secondary subjects (e.g. Mathematics, Physics, English).
 * @author Babirye Shanilah (Group 01)
 */
public class SubjectMark extends Entity {
    private String studentId;
    private String subjectName;
    private double score;

    public SubjectMark(String id, String studentId, String subjectName, double score) throws MarksOutOfBoundsException {
        super(id);
        setStudentId(studentId);
        setSubjectName(subjectName);
        setScore(score);
    }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) throws MarksOutOfBoundsException {
        if (studentId == null || !studentId.startsWith("G01-STU-")) {
            throw new MarksOutOfBoundsException("Invalid Student ID for marks entry.");
        }
        this.studentId = studentId;
    }

    public String getSubjectName() { return subjectName; }
    public void setSubjectName(String subjectName) throws MarksOutOfBoundsException {
        if (subjectName == null || subjectName.isBlank()) {
            throw new MarksOutOfBoundsException("Subject name cannot be empty.");
        }
        this.subjectName = subjectName.trim();
    }

    public double getScore() { return score; }
    public void setScore(double score) throws MarksOutOfBoundsException {
        if (score < 0.0 || score > 100.0) {
            throw new MarksOutOfBoundsException("Exam score must be between 0.0 and 100.0. Given: " + score);
        }
        this.score = score;
    }

    public String getGrade() {
        if (score >= 80) return "D1";
        if (score >= 75) return "D2";
        if (score >= 65) return "C3";
        if (score >= 60) return "C4";
        if (score >= 55) return "C5";
        if (score >= 50) return "C6";
        if (score >= 45) return "P7";
        if (score >= 40) return "P8";
        return "F9";
    }

    @Override
    public String getDetails() {
        return String.format("Mark ID: %-10s | Student ID: %-12s | Subject: %-15s | Score: %5.1f%% | Grade: %s",
                id, studentId, subjectName, score, getGrade());
    }
}