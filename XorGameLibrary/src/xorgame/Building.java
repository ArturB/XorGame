/* This software is licensed under XorGame 1.0 license. License does not expire.
 * Can be used for creating unlimited applications.
 * Can be distributed in binary or object form only.
 * Non-commercial use only.
 * Cannot modify source-code for any purpose (cannot create derivative works).
 */

package xorgame;

/**
 * This enum contains names of buildings available in XorGame. 
 * Available buildings:
 *  - MetalMine (only on the planet)
    - CristalMine (only on the planet)
    - DeuterExt (only on the planet)
    - Fusion (only on the planet)
    - Accelerator (only on the planet)
    - MetalMag 
    - CristalMag 
    - DeuterMag 
    - AntimatterMag
    - Robot 
    - Nanit 
    - Shipyard 
    - Observatory (only on the planet)
    - Laboratory (only on the planet)
    - ResearchNet (only on the planet)
    - RayShield 
    - MoonObservatory (only on the moon)
    - OrbitalShipyard  (only on the moon)
    - Teleporter  (only on the moon)
 * @author Artur M. Brodzki
 */
public enum Building {
    MetalMine, 
    CristalMine,
    DeuterExt, 
    Fusion,
    Accelerator, 
    MetalMag, 
    CristalMag,
    DeuterMag,
    AntimatterMag,
    Robot, 
    Nanit, 
    Shipyard, 
    Laboratory, 
    Observatory, 
    ResearchNet,
    RayShield,
    MoonObservatory,
    OrbitalShipyard,
    Teleporter;
    
    @Override
    public String toString() {
        switch (this) {
            case MetalMine:
                return "Metal mine";
            case CristalMine:
                return "Cristal mine";
            case DeuterExt:
                return "Deuter extractor";
            case Fusion:
                return "Fusion reactor";
            case Accelerator:
                return "Accelerator";
            case MetalMag:
                return "Metal magazine";
            case CristalMag:
                return "Cristal magazine";
            case DeuterMag:
                return "Deuter magazine";
            case AntimatterMag:
                return "Antimatter magazine";
            case Robot:
                return "Robot factory";
            case Nanit:
                return "Nanit factory";
            case Shipyard:
                return "Shipyard";
            case Laboratory:
                return "Laboratory";
            case Observatory:
                return "Observatory";
            case ResearchNet:
                return "Research network";
            case RayShield:
                return "Ray shield";
            case MoonObservatory:
                return "Moon observatory";
            case OrbitalShipyard:
                return "Orbital shipyard";
            default:
                return "Teleporter";
        }
    }
}
