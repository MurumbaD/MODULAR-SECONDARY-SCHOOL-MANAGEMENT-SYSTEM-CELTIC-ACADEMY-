package ug.ac.vu.g01.staff;

import ug.ac.vu.g01.core.Entity;

/**
 * Model class for teaching and administrative staff.
 * @author Tibarimbasa Isaac (Group 01)
 */
public class StaffMember extends Entity {
    private String name;
    private String department;
    private double salary;

    public StaffMember(String id, String name, String department, double salary) throws StaffAllocationException {
        super(id);
        setName(name);
        setDepartment(department);
        setSalary(salary);
    }

    public String getName() { return name; }
    public void setName(String name) throws StaffAllocationException {
        if (name == null || name.trim().length() < 2) throw new StaffAllocationException("Staff name must contain at least 2 characters.");
        this.name = name.trim();
    }

    public String getDepartment() { return department; }
    public void setDepartment(String department) throws StaffAllocationException {
        if (department == null || department.isBlank()) throw new StaffAllocationException("Department cannot be blank.");
        this.department = department.trim();
    }

    public double getSalary() { return salary; }
    public void setSalary(double salary) throws StaffAllocationException {
        if (salary < 0) throw new StaffAllocationException("Salary cannot be negative.");
        this.salary = salary;
    }

    @Override
    public String getDetails() {
        return String.format("Staff ID: %-10s | Name: %-20s | Dept: %-12s | Salary: UGX %,10.2f",
                id, name, department, salary);
    }
}