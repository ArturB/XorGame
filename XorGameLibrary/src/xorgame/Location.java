/* This software is licensed under XorGame 1.0 license. License does not expire.
 * Can be used for creating unlimited applications.
 * Can be distributed in binary or object form only.
 * Non-commercial use only.
 * Cannot modify source-code for any purpose (cannot create derivative works).
 */

package xorgame;

/**
 * Enumeration contains possible locations at specified coordinates (planet, moon or debris).
 * @author Artur M. Brodzki
 */
public enum Location {
    P, M, D;
    
    @Override
    public String toString() {
        switch (this) {
            case P:
                return "Planet";
            case M:
                return "Moon";
            default:
                return "Debris";
        }
    }
}
