package ug.ac.vu.g01.core;

import java.util.Scanner;

/**
 * Centralized scanner utility to prevent terminal crashes across all modules.
 * Handles mismatch exceptions, empty strings, out-of-bounds inputs, and user cancellation.
 * 
 * @author Kirabo Naume (Integration Lead)
 */
public class InputHelper {
    private static final Scanner scanner = new Scanner(System.in);

    private static void printError(String msg) {
        System.out.println("   [Input Error] " + msg + " Please try again.");
    }

    public static String readString(String prompt) throws OperationCancelledException {
        while (true) {
            System.out.print(prompt + " (or type 'CANCEL' to abort): ");
            String line = scanner.nextLine().trim();
            if (line.equalsIgnoreCase("CANCEL")) {
                throw new OperationCancelledException("Operation aborted by user.");
            }
            if (!line.isEmpty()) {
                return line;
            }
            printError("Text input cannot be empty.");
        }
    }

    public static int readInt(String prompt, int min, int max) throws OperationCancelledException {
        while (true) {
            System.out.print(prompt + " (" + min + "-" + max + ", or -1 to cancel): ");
            String line = scanner.nextLine().trim();
            if (line.equals("-1") || line.equalsIgnoreCase("CANCEL")) {
                throw new OperationCancelledException("Operation aborted by user.");
            }
            try {
                int val = Integer.parseInt(line);
                if (val >= min && val <= max) {
                    return val;
                }
                printError("Value out of valid range [" + min + " to " + max + "].");
            } catch (NumberFormatException e) {
                printError("Invalid integer entered. Please enter numbers only.");
            }
        }
    }

    public static double readDouble(String prompt, double min, double max) throws OperationCancelledException {
        while (true) {
            System.out.print(prompt + " (" + min + "-" + max + ", or -1 to cancel): ");
            String line = scanner.nextLine().trim();
            if (line.equals("-1") || line.equalsIgnoreCase("CANCEL")) {
                throw new OperationCancelledException("Operation aborted by user.");
            }
            try {
                double val = Double.parseDouble(line);
                if (val >= min && val <= max) {
                    return val;
                }
                printError("Numeric value out of valid range [" + min + " to " + max + "].");
            } catch (NumberFormatException e) {
                printError("Invalid decimal number entered.");
            }
        }
    }
}