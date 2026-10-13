package ug.ac.vu.g01.students;

import java.io.*;
import java.util.*;

/**
 * Service managing student collection persistence and queries.
 * @author Okello Solomon (Group 01)
 */
public class StudentService {
    private final List<Student> studentList = new ArrayList<>();
    private final String storageFile = "G01_Celtec_StudentsData.txt";

    public StudentService() {
        loadFromFile();
    }

    public void addStudent(Student student) {
        studentList.add(student);
        saveToFile();
    }

    public Student findById(String id) {
        for (Student s : studentList) {
            if (s.getId().equalsIgnoreCase(id)) {
                return s;
            }
        }
        return null;
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(studentList);
    }

    public List<Student> getStudentsSortedByName() {
        List<Student> sorted = new ArrayList<>(studentList);
        sorted.sort(Comparator.comparing(Student::getName));
        return sorted;
    }

    private void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(storageFile))) {
            for (Student s : studentList) {
                writer.println(s.getId() + ";" + s.getName() + ";" + s.getAge() + ";" + s.getClassLevel() + ";" + s.getStream() + ";" + s.getFeesBalance());
            }
        } catch (IOException e) {
            System.err.println("Warning: Could not save student records: " + e.getMessage());
        }
    }

    private void loadFromFile() {
        File file = new File(storageFile);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length >= 6) {
                    Student s = new Student(parts[0], parts[1], Integer.parseInt(parts[2]), parts[3], parts[4], Double.parseDouble(parts[5]));
                    studentList.add(s);
                }
            }
        } catch (Exception e) {
            System.out.println("Note: Initialized Celtec Academy student registry.");
        }
    }
}