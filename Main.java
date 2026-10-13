package ug.ac.vu.g01.app;

import java.util.List;
import ug.ac.vu.g01.core.*;
import ug.ac.vu.g01.students.*;
import ug.ac.vu.g01.fees.*;
import ug.ac.vu.g01.enrolment.*;
import ug.ac.vu.g01.marks.*;
import ug.ac.vu.g01.reports.*;
import ug.ac.vu.g01.library.*;
import ug.ac.vu.g01.staff.*;

/**
 * Main application CLI entry point connecting all seven secondary school system modules.
 * @author Murumba David & Okello Solomon (Group 01)
 */
public class Main {
    private static final StudentService studentService = new StudentService();
    private static final FeePaymentService feeService = new FeePaymentService(studentService);
    private static final EnrolmentService enrolmentService = new EnrolmentService(studentService);
    private static final MarksService marksService = new MarksService(studentService);
    private static final ReportCardService reportService = new ReportCardService(studentService, marksService);
    private static final LibraryService libraryService = new LibraryService();
    private static final StaffService staffService = new StaffService();

    private static int studentCounter = 101;
    private static int feeCounter = 1;
    private static int enrolmentCounter = 1;
    private static int markCounter = 1;
    private static int reportCounter = 1;
    private static int bookCounter = 1;
    private static int staffCounter = 1;

    public static void main(String[] args) {
        System.out.println("=========================================================================");
        System.out.println("   CELTEC ACADEMY SECONDARY SCHOOL - MANAGEMENT SYSTEM (GROUP 01)        ");
        System.out.println("=========================================================================");

        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = InputHelper.readInt("Select Main Menu Option", 0, 7);
            switch (choice) {
                case 1 -> runStudentSubmenu();
                case 2 -> runEnrolmentSubmenu();
                case 3 -> runFeesSubmenu();
                case 4 -> runMarksSubmenu();
                case 5 -> runReportsSubmenu();
                case 6 -> runLibrarySubmenu();
                case 7 -> runStaffSubmenu();
                case 0 -> {
                    System.out.println("\nExiting system... All Celtec Academy data saved cleanly across 7 files.");
                    running = false;
                }
            }
        }
    }

    private static void printMainMenu() {
        System.out.println("\n---------------------------------------------------------");
        System.out.println("               CELTEC ACADEMY MAIN SYSTEM MENU            ");
        System.out.println("---------------------------------------------------------");
        System.out.println(" 1. Students Module");
        System.out.println(" 2. Enrolment Module");
        System.out.println(" 3. School Fees Module (Linked with Students)");
        System.out.println(" 4. Academic Marks Module");
        System.out.println(" 5. Report Cards Module");
        System.out.println(" 6. Library Books Module");
        System.out.println(" 7. Staff Records Module");
        System.out.println(" 0. Exit System");
        System.out.println("---------------------------------------------------------");
    }

    private static void runStudentSubmenu() {
        boolean inSub = true;
        while (inSub) {
            System.out.println("\n--- STUDENTS MANAGEMENT SUBMENU ---");
            System.out.println(" 1. Register Regular Day Student");
            System.out.println(" 2. Register Boarding Student");
            System.out.println(" 3. Search Student by ID");
            System.out.println(" 4. List All Students (Sorted Alphabetically)");
            System.out.println(" 5. Process Polymorphic Entities Loop");
            System.out.println(" 0. Return to Main Menu");
            int ch = InputHelper.readInt("Select Option", 0, 5);
            switch (ch) {
                case 1 -> {
                    try {
                        String id = String.format("G01-STU-%03d", studentCounter++);
                        String name = InputHelper.readString("Enter Full Name");
                        int age = InputHelper.readInt("Enter Age", 12, 20);
                        String cls = InputHelper.readString("Enter Class Level (e.g. S1, S4)");
                        String stream = InputHelper.readString("Enter Stream (e.g. North, South)");
                        double bal = InputHelper.readDouble("Enter Fees Owed (UGX)", 0, 10000000);
                        Student s = new Student(id, name, age, cls, stream, bal);
                        studentService.addStudent(s);
                        System.out.println("\n >>> SUCCESS! ASSIGNED STUDENT ID: " + id);
                        System.out.println(" Details: " + s.getDetails());
                    } catch (InvalidStudentDataException e) {
                        System.out.println(" Error: " + e.getMessage());
                    }
                }
                case 2 -> {
                    try {
                        String id = String.format("G01-STU-%03d", studentCounter++);
                        String name = InputHelper.readString("Enter Full Name");
                        int age = InputHelper.readInt("Enter Age", 12, 20);
                        String cls = InputHelper.readString("Enter Class Level");
                        String stream = InputHelper.readString("Enter Stream");
                        double bal = InputHelper.readDouble("Enter Fees Owed (UGX)", 0, 10000000);
                        String dorm = InputHelper.readString("Enter Dormitory Name");
                        int bed = InputHelper.readInt("Enter Bed Space Number", 1, 500);
                        BoardingStudent bs = new BoardingStudent(id, name, age, cls, stream, bal, dorm, bed);
                        studentService.addStudent(bs);
                        System.out.println("\n >>> SUCCESS! ASSIGNED BOARDER STUDENT ID: " + id);
                        System.out.println(" Details: " + bs.getDetails());
                    } catch (InvalidStudentDataException e) {
                        System.out.println(" Error: " + e.getMessage());
                    }
                }
                case 3 -> {
                    String id = InputHelper.readString("Enter Student ID (e.g. G01-STU-101)");
                    Student s = studentService.findById(id);
                    if (s != null) {
                        System.out.println(" Record Found: " + s.getDetails());
                    } else {
                        System.out.println(" Record not found.");
                    }
                }
                case 4 -> {
                    System.out.println("\n--- STUDENT LIST (SORTED BY NAME) ---");
                    List<Student> list = studentService.getStudentsSortedByName();
                    if (list.isEmpty()) {
                        System.out.println(" No student records found.");
                    } else {
                        list.forEach(s -> System.out.println(" " + s.getDetails()));
                    }
                }
                case 5 -> {
                    System.out.println("\n--- POLYMORPHIC ENTITIES LOOP ---");
                    List<Student> students = studentService.getAllStudents();
                    for (Entity e : students) {
                        System.out.println(" [Polymorphic Call]: " + e.getDisplayName());
                    }
                }
                case 0 -> inSub = false;
            }
        }
    }

    private static void runEnrolmentSubmenu() {
        boolean inSub = true;
        while (inSub) {
            System.out.println("\n--- ENROLMENT SUBMENU ---");
            System.out.println(" 1. Enrol Student for Term");
            System.out.println(" 2. Display Enrolments (Sorted by Year)");
            System.out.println(" 0. Return to Main Menu");
            int ch = InputHelper.readInt("Select Option", 0, 2);
            switch (ch) {
                case 1 -> {
                    try {
                        String id = String.format("G01-ENR-%03d", enrolmentCounter++);
                        String sid = InputHelper.readString("Enter Target Student ID (e.g. G01-STU-101)");
                        String year = InputHelper.readString("Enter Academic Year (e.g. 2026)");
                        String term = InputHelper.readString("Enter Term (e.g. Term 1)");
                        String status = InputHelper.readString("Enter Status (Active/Deferred/Graduated)");
                        enrolmentService.addEnrolment(id, sid, year, term, status);
                        System.out.println("\n >>> ENROLMENT SUCCESSFUL! RECORD ID: " + id);
                    } catch (Exception e) {
                        System.out.println(" Error: " + e.getMessage());
                    }
                }
                case 2 -> {
                    System.out.println("\n--- ENROLMENT RECORDS ---");
                    List<EnrolmentRecord> list = enrolmentService.getAllEnrolmentsSortedByYear();
                    if (list.isEmpty()) {
                        System.out.println(" No enrolment records found.");
                    } else {
                        list.forEach(e -> System.out.println(" " + e.getDetails()));
                    }
                }
                case 0 -> inSub = false;
            }
        }
    }

    private static void runFeesSubmenu() {
        boolean inSub = true;
        while (inSub) {
            System.out.println("\n--- SCHOOL FEES SUBMENU ---");
            System.out.println(" 1. Record Fee Payment");
            System.out.println(" 2. Display Receipts (Sorted by Amount)");
            System.out.println(" 0. Return to Main Menu");
            int ch = InputHelper.readInt("Select Option", 0, 2);
            switch (ch) {
                case 1 -> {
                    try {
                        String pid = String.format("G01-PAY-%03d", feeCounter++);
                        String sid = InputHelper.readString("Enter Student ID (e.g. G01-STU-101)");
                        double amt = InputHelper.readDouble("Enter Amount (UGX)", 1, 10000000);
                        String method = InputHelper.readString("Enter Method (Cash/Bank/MoMo)");
                        feeService.processPayment(pid, sid, amt, method);
                        System.out.println("\n >>> PAYMENT SUCCESSFUL! RECEIPT ID: " + pid);
                    } catch (Exception e) {
                        System.out.println(" Error: " + e.getMessage());
                    }
                }
                case 2 -> {
                    System.out.println("\n--- FEE RECEIPTS ---");
                    List<FeePayment> list = feeService.getPaymentsSortedByAmount();
                    if (list.isEmpty()) {
                        System.out.println(" No fee receipts found.");
                    } else {
                        list.forEach(p -> System.out.println(" " + p.getDetails()));
                    }
                }
                case 0 -> inSub = false;
            }
        }
    }

    private static void runMarksSubmenu() {
        boolean inSub = true;
        while (inSub) {
            System.out.println("\n--- ACADEMIC MARKS SUBMENU ---");
            System.out.println(" 1. Record Subject Mark");
            System.out.println(" 2. Display Marks (Sorted by Highest Score)");
            System.out.println(" 0. Return to Main Menu");
            int ch = InputHelper.readInt("Select Option", 0, 2);
            switch (ch) {
                case 1 -> {
                    try {
                        String id = String.format("G01-MRK-%03d", markCounter++);
                        String sid = InputHelper.readString("Enter Student ID (e.g. G01-STU-101)");
                        String sub = InputHelper.readString("Enter Subject Name (e.g. Mathematics)");
                        double score = InputHelper.readDouble("Enter Score (0-100)", 0, 100);
                        marksService.recordMark(id, sid, sub, score);
                        System.out.println("\n >>> MARK RECORDED! MARK ENTRY ID: " + id);
                    } catch (Exception e) {
                        System.out.println(" Error: " + e.getMessage());
                    }
                }
                case 2 -> {
                    System.out.println("\n--- MARKS LIST ---");
                    List<SubjectMark> list = marksService.getAllMarksSortedByScore();
                    if (list.isEmpty()) {
                        System.out.println(" No marks recorded.");
                    } else {
                        list.forEach(m -> System.out.println(" " + m.getDetails()));
                    }
                }
                case 0 -> inSub = false;
            }
        }
    }

    private static void runReportsSubmenu() {
        boolean inSub = true;
        while (inSub) {
            System.out.println("\n--- REPORT CARDS SUBMENU ---");
            System.out.println(" 1. Generate Report Card for Student");
            System.out.println(" 2. Display Report Cards (Sorted by Average)");
            System.out.println(" 0. Return to Main Menu");
            int ch = InputHelper.readInt("Select Option", 0, 2);
            switch (ch) {
                case 1 -> {
                    try {
                        String id = String.format("G01-REP-%03d", reportCounter++);
                        String sid = InputHelper.readString("Enter Student ID (e.g. G01-STU-101)");
                        ReportCard card = reportService.generateReportCard(id, sid);
                        System.out.println("\n >>> REPORT CARD GENERATED! REPORT ID: " + id);
                        System.out.println(" Summary: " + card.getDetails());
                    } catch (Exception e) {
                        System.out.println(" Error: " + e.getMessage());
                    }
                }
                case 2 -> {
                    System.out.println("\n--- REPORT CARDS ---");
                    List<ReportCard> list = reportService.getReportsSortedByAverage();
                    if (list.isEmpty()) {
                        System.out.println(" No report cards generated.");
                    } else {
                        list.forEach(r -> System.out.println(" " + r.getDetails()));
                    }
                }
                case 0 -> inSub = false;
            }
        }
    }

    private static void runLibrarySubmenu() {
        boolean inSub = true;
        while (inSub) {
            System.out.println("\n--- LIBRARY BOOKS SUBMENU ---");
            System.out.println(" 1. Add New Book");
            System.out.println(" 2. List All Books (Sorted by Title)");
            System.out.println(" 0. Return to Main Menu");
            int ch = InputHelper.readInt("Select Option", 0, 2);
            switch (ch) {
                case 1 -> {
                    try {
                        String id = String.format("G01-BOK-%03d", bookCounter++);
                        String title = InputHelper.readString("Enter Book Title");
                        String author = InputHelper.readString("Enter Author");
                        int copies = InputHelper.readInt("Enter Available Copies", 1, 1000);
                        LibraryBook b = new LibraryBook(id, title, author, copies);
                        libraryService.addBook(b);
                        System.out.println("\n >>> BOOK ADDED TO CATALOG! BOOK ID: " + id);
                    } catch (Exception e) {
                        System.out.println(" Error: " + e.getMessage());
                    }
                }
                case 2 -> {
                    System.out.println("\n--- LIBRARY CATALOG ---");
                    List<LibraryBook> list = libraryService.getAllBooksSortedByTitle();
                    if (list.isEmpty()) {
                        System.out.println(" No books in catalog.");
                    } else {
                        list.forEach(b -> System.out.println(" " + b.getDetails()));
                    }
                }
                case 0 -> inSub = false;
            }
        }
    }

    private static void runStaffSubmenu() {
        boolean inSub = true;
        while (inSub) {
            System.out.println("\n--- STAFF RECORDS SUBMENU ---");
            System.out.println(" 1. Register Staff Member");
            System.out.println(" 2. List All Staff (Sorted by Name)");
            System.out.println(" 0. Return to Main Menu");
            int ch = InputHelper.readInt("Select Option", 0, 2);
            switch (ch) {
                case 1 -> {
                    try {
                        String id = String.format("G01-STF-%03d", staffCounter++);
                        String name = InputHelper.readString("Enter Staff Name");
                        String dept = InputHelper.readString("Enter Department");
                        double sal = InputHelper.readDouble("Enter Salary (UGX)", 100000, 50000000);
                        StaffMember s = new StaffMember(id, name, dept, sal);
                        staffService.addStaff(s);
                        System.out.println("\n >>> STAFF MEMBER REGISTERED! STAFF ID: " + id);
                    } catch (Exception e) {
                        System.out.println(" Error: " + e.getMessage());
                    }
                }
                case 2 -> {
                    System.out.println("\n--- STAFF DIRECTORY ---");
                    List<StaffMember> list = staffService.getStaffSortedByName();
                    if (list.isEmpty()) {
                        System.out.println(" No staff registered.");
                    } else {
                        list.forEach(s -> System.out.println(" " + s.getDetails()));
                    }
                }
                case 0 -> inSub = false;
            }
        }
    }
}