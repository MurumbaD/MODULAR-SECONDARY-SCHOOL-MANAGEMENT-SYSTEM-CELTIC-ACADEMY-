package ug.ac.vu.g01.core;

/**
 * Interface establishing a uniform structural contract for all domain records across Celtec Academy.
 * Ensures every record exposes a unique identifier starting with 'G01-' and a display string.
 * 
 * @author Murumba David (Group 01 Leader - VU-BIT-2607-3239-EVE)
 */
public interface Identifiable {
    /** @return Unique record identifier string starting with G01- */
    String getId();
    
    /** @return Human-readable display string for CLI menus */
    String getDisplayName();
}