/* This software is licensed under XorGame 1.0 license. License does not expire.
 * Can be used for creating unlimited applications.
 * Can be distributed in binary or object form only.
 * Non-commercial use only.
 * Cannot modify source-code for any purpose (cannot create derivative works).
 */

package xorgame;

/**
 * This enum contains names of all items (ships or defense systems) that can be build in the shipyard. 
 * Available items:
 * a) defense systems:
      - RocketLauncher 
      - LaserGun 
      - TurboLaserGun 
      - IonGun 
      - TractorGun 
      - PlasmaGun 
      - HeavyPlasmaGun 
   b) ships:
      - SolarSat 
      - Transporter 
      - Freighter 
      - Recycler 
      - Colonizer 
      - SpySond 
      - Fighter 
      - IonFighter 
      - Frigate 
      - Bomber
      - Cruiser 
      - Drednot 
      - Destroyer
      - DeathStar 
 * @author Artur M. Brodzki
 */
public enum ShipyardItem {
    RocketLauncher ,
    LaserGun ,
    TurboLaserGun ,
    IonGun ,
    TractorGun ,
    PlasmaGun ,
    HeavyPlasmaGun ,
    SolarSat ,
    Transporter ,
    Freighter ,
    Recycler ,
    Colonizer ,
    SpySond ,
    Fighter ,
    IonFighter ,
    Frigate ,
    Bomber,
    Cruiser ,
    Drednot ,
    Destroyer,
    DeathStar;
    
    @Override
    public String toString() {
        if(this.equals(ShipyardItem.RocketLauncher))
            return "Rocket launcher";
        else if(this.equals(ShipyardItem.LaserGun))
            return "Laser cannon";
        else if(this.equals(ShipyardItem.TurboLaserGun))
            return "Turbolaser";
        else if(this.equals(ShipyardItem.IonGun))
            return "Ion cannon";
        else if(this.equals(ShipyardItem.TractorGun))
            return "Tractor beam";
        else if(this.equals(ShipyardItem.PlasmaGun))
            return "Plasma cannon";
        else if(this.equals(ShipyardItem.HeavyPlasmaGun))
            return "Heavy plasma cannon";
        else if(this.equals(ShipyardItem.SolarSat))
            return "Solar satelite";
        else if(this.equals(ShipyardItem.Transporter))
            return "Transporter";
        else if(this.equals(ShipyardItem.Freighter))
            return "Freighter";
        else if(this.equals(ShipyardItem.Recycler))
            return "Recycler";
        else if(this.equals(ShipyardItem.Colonizer))
            return "Colonizer";
        else if(this.equals(ShipyardItem.SpySond))
            return "Spy sond";
        else if(this.equals(ShipyardItem.Fighter))
            return "Fighter";
        else if(this.equals(ShipyardItem.IonFighter))
            return "Ion fighter";
        else if(this.equals(ShipyardItem.Frigate))
            return "Frigate";
        else if(this.equals(ShipyardItem.Bomber))
            return "Bomber";
        else if(this.equals(ShipyardItem.Cruiser))
            return "Cruiser";
        else if(this.equals(ShipyardItem.Drednot))
            return "Dreadnought";
        else if(this.equals(ShipyardItem.Destroyer))
            return "Destroyer";
        else
            return "Death Star";
    }
}
