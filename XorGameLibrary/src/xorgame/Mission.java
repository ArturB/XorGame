/* This software is licensed under XorGame 1.0 license. License does not expire.
 * Can be used for creating unlimited applications.
 * Can be distributed in binary or object form only.
 * Non-commercial use only.
 * Cannot modify source-code for any purpose (cannot create derivative works).
 */

package xorgame;

/**
 * Enumeration contains possible missions that a fleet can accept.
 * Possible missions are: transport, station, colonize, recycle, spy, attack, destroy, or a fleet can has no mission at all. 
 * @author Artur M. Brodzki
 */
public enum Mission {
    T, S, K, R, SZ, A, N, NO;
    
    @Override
    public String toString() {
        switch (this) {
            case T:
                return "Transport";
            case S:
                return "Station";
            case K:
                return "Colonize";
            case R:
                return "Recycle";
            case SZ:
                return "Spy";
            case A:
                return "Attack";
            case N:
                return "Destroy";
            default:
                return "No mission";
        }
    }
}
