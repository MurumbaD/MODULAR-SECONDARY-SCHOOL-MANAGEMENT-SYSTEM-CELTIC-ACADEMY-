package ug.ac.vu.g01.core;

import java.time.LocalDate;

/**
 * Abstract superclass serving as the foundation for all school records.
 * Demonstrates encapsulation, inheritance, and abstraction.
 * 
 * @author Murumba David (Group 01 Leader)
 */
public abstract class Entity implements Identifiable {
    // Protected attributes accessible by direct subclass hierarchies
    protected String id;
    protected String createdDate;

    /**
     * Protected constructor ensuring subclasses initialize shared identity attributes.
     *  @throws IllegalArgumentException if ID format violates the required group prefix
     * @param id Unique record ID formatted as G01-(XXX-###)
     */
    protected Entity(String id) {
        if (id == null || !id.startsWith("G01-")) {
            throw new IllegalArgumentException("CRITICAL: Record ID must start with group tag 'G01-'. Given: " + id);
        }
        this.id = id;
        this.createdDate = LocalDate.now().toString();
    }

    @Override
    public String getId() {
        return this.id;
    }

    public String getCreatedDate() {
        return this.createdDate;
    }

    /** Abstract method forcing polymorphic formatting implementation in subclasses */
    public abstract String getDetails();

    @Override
    public String getDisplayName() {
        return "[" + id + "] " + getDetails();
    }
}