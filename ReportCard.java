package ug.ac.vu.g01.reports;

import ug.ac.vu.g01.core.Entity;

/**
 * Aggregates student terminal report card details.
 * @author Mwesigwa Nekamia (Group 01)
 */
public class ReportCard extends Entity {
    private String studentId;
    private double averageScore;
    private String overallDivision;

    public ReportCard(String id, String studentId, double averageScore, String overallDivision) throws ReportGenerationException {
        super(id);
        setStudentId(studentId);
        setAverageScore(averageScore);
        setOverallDivision(overallDivision);
    }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) throws ReportGenerationException {
        if (studentId == null || !studentId.startsWith("G01-STU-")) {
            throw new ReportGenerationException("Invalid Student ID for report card generation.");
        }
        this.studentId = studentId;
    }

    public double getAverageScore() { return averageScore; }
    public void setAverageScore(double averageScore) throws ReportGenerationException {
        if (averageScore < 0.0 || averageScore > 100.0) {
            throw new ReportGenerationException("Average score must be between 0 and 100.");
        }
        this.averageScore = averageScore;
    }

    public String getOverallDivision() { return overallDivision; }
    public void setOverallDivision(String overallDivision) throws ReportGenerationException {
        if (overallDivision == null || overallDivision.isBlank()) {
            throw new ReportGenerationException("Division classification required.");
        }
        this.overallDivision = overallDivision;
    }

    @Override
    public String getDetails() {
        return String.format("Report Card ID: %-10s | Student ID: %-12s | Average: %5.1f%% | Result: %s",
                id, studentId, averageScore, overallDivision);
    }
}