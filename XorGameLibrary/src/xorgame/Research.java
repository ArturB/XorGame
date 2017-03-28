/* This software is licensed under XorGame 1.0 license. License does not expire.
 * Can be used for creating unlimited applications.
 * Can be distributed in binary or object form only.
 * Non-commercial use only.
 * Cannot modify source-code for any purpose (cannot create derivative works).
 */

package xorgame;

/**
 * This enum contains names of all technologies available in XorGame. 
 * Available technologies:
   - Energetic 
   - Nanotechnology 
   - Astrophysics 
   - Hyperspace 
   - Astronavigation 
   - Laser 
   - Ion 
   - Plasma 
   - Gravitons 
   - Combat 
   - Shield 
   - Material 
   - IonEngine 
   - HyperEngine 
 * @author Artur M. Brodzki
 */
public enum Research {
   Energetic ,
   Nanotechnology ,
   Astrophysics ,
   Hyperspace ,
   Astronavigation ,
   Laser ,
   Ion ,
   Plasma ,
   Gravitons ,
   Combat ,
   Shield ,
   Material ,
   IonEngine ,
   HyperEngine;
   
   @Override
   public String toString() {
       switch (this) {
           case Energetic:
               return "Energetic tech.";
           case Nanotechnology:
               return "Nanotechnology";
           case Astrophysics:
               return "Astrophysics.";
           case Hyperspace:
               return "Hyperspace tech.";
           case Astronavigation:
               return "Astronavigation.";
           case Laser:
               return "Laser tech.";
           case Ion:
               return "Ion tech.";
           case Plasma:
               return "Plasma tech.";
           case Gravitons:
               return "Gravitons tech.";
           case Combat:
               return "Combat tech.";
           case Shield:
               return "Shield tech.";
           case Material:
               return "Material tech.";
           case IonEngine:
               return "Ion engine.";
           default:
               return "Hyper-engine.";
       }
   }
}
