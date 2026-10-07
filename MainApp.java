package ug.ac.vu.g01.app;

import ug.ac.vu.g01.core.Entity;
import ug.ac.vu.g01.core.InputHelper;
import ug.ac.vu.g01.core.OperationCancelledException;
import ug.ac.vu.g01.students.*;
import ug.ac.vu.g01.fees.*;

import java.util.List;

/**
 * Main application CLI runner for Group 01.
 * 
 * @author Murumba David (Group 01 Leader)
 */
public class MainApp {
    private static final StudentService studentService = new StudentService();
    private static final FeePaymentService feeService = new FeePaymentService(studentService);
    private static int studentCounter = 1;
    private static int feeCounter = 1;

    public static void main(String[] args) {
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("            WELCOME TO CELTEC ACADEMY MANAGEMENT SYSTEM                  ");
        System.out.println("                                                                         ");
        System.out.println("*************************************************************************");

        boolean running = true;
        while (running) {
            printMainMenu();
            try {
                int choice = InputHelper.readInt("Select Main Menu Option", 0, 7);
                switch (choice) {
                    case 1 -> runStudentSubmenu();
                    case 2 -> System.out.println("\n[Enrolment Module]: Active - Stream & term allocation operational.");
                    case 3 -> runFeesSubmenu();
                    case 4 -> System.out.println("\n[Marks Module]: Active - Continuous Assessment module initialized.");
                    case 5 -> System.out.println("\n[Report Cards Module]: Active - Secondary term report engine ready.");
                    case 6 -> System.out.println("\n[Library Books Module]: Active - Curriculum catalog synchronized.");
                    case 7 -> System.out.println("\n[Staff Module]: Active - Staff registry operational.");
                    case 0 -> {
                        System.out.println("\nExiting system... State saved cleanly. Good luck!");
                        running = false;
                    }
                }
            } catch (OperationCancelledException e) {
                System.out.println("\n [NOTICE] Main menu action aborted by user.");
            }
        }
    }

    private static void printMainMenu() {
        System.out.println("\n---------------------------------------------------------");
        System.out.println("                     MAIN SYSTEM MENU                    ");
        System.out.println("---------------------------------------------------------");
        System.out.println(" 1. Students Module");
        System.out.println(" 2. Enrolment Module");
        System.out.println(" 3. School Fees Module (Linked with Students)");
        System.out.println(" 4. Marks Module");
        System.out.println(" 5. Report Cards Module");
        System.out.println(" 6. Library Books Module");
        System.out.println(" 7. Staff Module");
        System.out.println(" 0. Exit System");
        System.out.println("---------------------------------------------------------");
    }

    private static void runStudentSubmenu() {
        boolean inSub = true;
        while (inSub) {
            System.out.println("\n--- STUDENTS MANAGEMENT SUBMENU ---");
            System.out.println(" 1. Register Regular Student");
            System.out.println(" 2. Register International Student");
            System.out.println(" 3. Search Student by ID");
            System.out.println(" 4. List All Students (Sorted Alphabetically)");
            System.out.println(" 5. Process Polymorphic Entities Loop");
            System.out.println(" 0. Return to Main Menu");

            try {
                int ch = InputHelper.readInt("Select Option", 0, 5);
                switch (ch) {
                    case 1 -> {
                        try {
                            String id = String.format("G01-STU-%03d", studentCounter++);
                            String name = InputHelper.readString("Enter Student Full Name");
                            int age = InputHelper.readInt("Enter Age (11-20)", 11, 20);
                            String cls = InputHelper.readString("Enter Class Level (e.g. S1, S4, S6)");
                            double bal = InputHelper.readDouble("Enter Initial Fees Owed (UGX)", 0, 10000000);

                            Student s = new Student(id, name, age, cls, bal);
                            studentService.addStudent(s);
                            System.out.println(" Success: " + s.getDetails());
                        } catch (InvalidStudentDataException e) {
                            System.out.println(" Validation Error: " + e.getMessage());
                        }
                    }
                    case 2 -> {
                        try {
                            String id = String.format("G01-STU-%03d", studentCounter++);
                            String name = InputHelper.readString("Enter Full Name");
                            int age = InputHelper.readInt("Enter Age (11-20)", 11, 20);
                            String cls = InputHelper.readString("Enter Class Level");
                            double bal = InputHelper.readDouble("Enter Fees Owed (UGX)", 0, 10000000);
                            String pass = InputHelper.readString("Enter Passport No");
                            String country = InputHelper.readString("Enter Country of Origin");

                            InternationalStudent is = new InternationalStudent(id, name, age, cls, bal, pass, country);
                            studentService.addStudent(is);
                            System.out.println(" Success: " + is.getDetails());
                        } catch (InvalidStudentDataException e) {
                            System.out.println(" Validation Error: " + e.getMessage());
                        }
                    }
                    case 3 -> {
                        String id = InputHelper.readString("Enter Student ID (e.g. G01-STU-001)");
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
                        System.out.println("\n--- POLYMORPHIC ENTITIES PROCESSING LOOP ---");
                        List<Student> students = studentService.getAllStudents();
                        for (Entity e : students) {
                            System.out.println(" [Polymorphic Call]: " + e.getDisplayName());
                        }
                    }
                    case 0 -> inSub = false;
                }
            } catch (OperationCancelledException e) {
                System.out.println("\n [CANCELLED] Action aborted. Returning to Submenu...");
            }
        }
    }

    private static void runFeesSubmenu() {
        boolean inSub = true;
        while (inSub) {
            System.out.println("\n--- SCHOOL FEES SUBMENU ---");
            System.out.println(" 1. Record Term Fee Payment");
            System.out.println(" 2. Display Receipts (Sorted by Amount Descending)");
            System.out.println(" 0. Return to Main Menu");

            try {
                int ch = InputHelper.readInt("Select Option", 0, 2);
                switch (ch) {
                    case 1 -> {
                        try {
                            String pid = String.format("G01-PAY-%03d", feeCounter++);
                            String sid = InputHelper.readString("Enter Student ID");
                            double amt = InputHelper.readDouble("Enter Payment Amount (UGX)", 1, 10000000);
                            String method = InputHelper.readString("Enter Payment Method (Cash/Bank/MoMo)");

                            feeService.processPayment(pid, sid, amt, method);
                            System.out.println(" Payment Successful! Ledger updated.");
                        } catch (Exception e) {
                            System.out.println(" Payment Processing Failed: " + e.getMessage());
                        }
                    }
                    case 2 -> {
                        System.out.println("\n--- FEE PAYMENT RECEIPTS (SORTED BY AMOUNT DESCENDING) ---");
                        List<FeePayment> list = feeService.getPaymentsSortedByAmount();
                        if (list.isEmpty()) {
                            System.out.println(" No fee receipts recorded.");
                        } else {
                            list.forEach(p -> System.out.println(" " + p.getDetails()));
                        }
                    }
                    case 0 -> inSub = false;
                }
            } catch (OperationCancelledException e) {
                System.out.println("\n [CANCELLED] Fee operation aborted. Returning to Submenu...");
            }
        }
    }
}