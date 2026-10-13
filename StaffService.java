package ug.ac.vu.g01.staff;

import java.io.*;
import java.util.*;

/**
 * Service managing staff member records and file storage.
 * @author Tibarimbasa Isaac (Group 01)
 */
public class StaffService {
    private final List<StaffMember> staffList = new ArrayList<>();
    private final String storageFile = "G01_Celtec_StaffData.txt";

    public StaffService() {
        loadFromFile();
    }

    public void addStaff(StaffMember staff) {
        staffList.add(staff);
        saveToFile();
    }

    public List<StaffMember> getStaffSortedByName() {
        List<StaffMember> sorted = new ArrayList<>(staffList);
        sorted.sort(Comparator.comparing(StaffMember::getName));
        return sorted;
    }

    private void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(storageFile))) {
            for (StaffMember s : staffList) {
                writer.println(s.getId() + ";" + s.getName() + ";" + s.getDepartment() + ";" + s.getSalary());
            }
        } catch (IOException e) {
            System.err.println("Warning: Could not save staff records: " + e.getMessage());
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
                    staffList.add(new StaffMember(parts[0], parts[1], parts[2], Double.parseDouble(parts[3])));
                }
            }
        } catch (Exception e) {
            System.out.println("Note: Initialized staff ledger.");
        }
    }
}