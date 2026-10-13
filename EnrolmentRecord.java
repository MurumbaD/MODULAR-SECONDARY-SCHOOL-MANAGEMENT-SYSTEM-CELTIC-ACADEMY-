package ug.ac.vu.g01.enrolment;

import ug.ac.vu.g01.core.Entity;

/**
 * Tracks student enrolment status and stream allocation at Celtec Academy Secondary School.
 * @author Ahmed Abdijabar Farah (Group 01)
 */
public class EnrolmentRecord extends Entity {
    private String studentId;
    private String academicYear;
    private String term;
    private String status; // Active, Deferred, Graduated

    public EnrolmentRecord(String id, String studentId, String academicYear, String term, String status) throws EnrolmentException {
        super(id);
        setStudentId(studentId);
        setAcademicYear(academicYear);
        setTerm(term);
        setStatus(status);
    }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) throws EnrolmentException {
        if (studentId == null || !studentId.startsWith("G01-STU-")) {
            throw new EnrolmentException("Enrolment requires a valid student ID starting with 'G01-STU-'.");
        }
        this.studentId = studentId;
    }

    public String getAcademicYear() { return academicYear; }
    public void setAcademicYear(String academicYear) throws EnrolmentException {
        if (academicYear == null || !academicYear.matches("20\\d{2}")) {
            throw new EnrolmentException("Academic year must be in format YYYY (e.g. 2026).");
        }
        this.academicYear = academicYear;
    }

    public String getTerm() { return term; }
    public void setTerm(String term) throws EnrolmentException {
        if (term == null || !term.matches("Term [1-3]")) {
            throw new EnrolmentException("Term must be 'Term 1', 'Term 2', or 'Term 3'.");
        }
        this.term = term;
    }

    public String getStatus() { return status; }
    public void setStatus(String status) throws EnrolmentException {
        if (status == null || (!status.equalsIgnoreCase("Active") && !status.equalsIgnoreCase("Deferred") && !status.equalsIgnoreCase("Graduated"))) {
            throw new EnrolmentException("Status must be 'Active', 'Deferred', or 'Graduated'.");
        }
        this.status = status;
    }

    @Override
    public String getDetails() {
        return String.format("Enrolment ID: %-12s | Student ID: %-12s | Year: %s | %s | Status: %s",
                id, studentId, academicYear, term, status);
    }
}