/* This software is licensed under XorGame 1.0 license. License does not expire.
 * Can be used for creating unlimited applications.
 * Can be distributed in binary or object form only.
 * Non-commercial use only.
 * Cannot modify source-code for any purpose (cannot create derivative works).
 */

package xorgame;

/**
 * This enum represents possible kinds of materials in XorGame - metal, cristal, deuter, antimatter, or energy. 
 * @author Artur M. Brodzki
 */
public enum Material {
    M, K, D, A, E;
    
    @Override
    public String toString() {
        switch (this) {
            case M:
                return "Metal";
            case K:
                return "Cristal";
            case D:
                return "Deuter";
            case A:
                return "Antimatter";
            default:
                return "Energy";
        }   
    }
}
