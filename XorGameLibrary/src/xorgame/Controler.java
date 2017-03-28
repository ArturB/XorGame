/* This software is licensed under XorGame 1.0 license. License does not expire.
 * Can be used for creating unlimited applications.
 * Can be distributed in binary or object form only.
 * Non-commercial use only.
 * Cannot modify source-code for any purpose (cannot create derivative works).
 */

package xorgame;

import java.sql.*;
import java.util.*;
import javax.persistence.*;

/**
 * Controler class provides methods and functions that accomplish the game mechanics. 
 * It also allows to download game GUI from database and download all information about user account necessary to play. 
 * To see general description of the XorGame rules, see <a href="Manual.pdf">Manual.pdf</a>
 * @author Artur M. Brodzki
 */
public class Controler {
    private final Connection DB_CONNECTION_;
    
    /**
     * Controler class only contructor. Connects to database containing game data. 
     * When work with Controler is over, connection with database must be closed by calling {@link #close() } method. 
     * @param url URL address of XorGame database to connect. 
     * @param port Port number. 
     * @param databaseName Name of database. 
     * @param username Username. 
     * @param password Password. 
     * @throws SQLException Thrown if connection to database fails, for example because of incorrent username or password. 
     */
    public Controler(String url, int port, String databaseName, String username, String password) throws SQLException {
        String urlParam = "jdbc:mysql://" + url + ":" + port + "/" + databaseName + "?" + "useSSL=false";
        DB_CONNECTION_ = DriverManager.getConnection(urlParam, username, password);
    }
    
    /**
     * Abandons planet with specified coordinates (sets its User to null). 
     * @param coordinates Coordinates of the planet to abandon
     * @throws SQLException Thrown if specified planet does not exists. 
     */
    public void abandonPlanet(PlanetPK coordinates) throws SQLException {
        CallableStatement callProc = DB_CONNECTION_.prepareCall("{ call abandonPlanet(?, ?, ?) }");
        
        callProc.setInt(1, coordinates.getX());
        callProc.setInt(2, coordinates.getY());
        callProc.setInt(3, coordinates.getPosition());
        
        callProc.execute();
    }
    
    /**
     * Add empty moon existing planet. 
     * @param coordinates Coordinates of the planet where moon is to add
     * @param name Name of created moon
     * @param gui Index of image of the moon. Possible images can be downloaded from the database by {@link #downloadGUI() } method.
     * @throws SQLException Thrown if given planet does not exist. 
     */
    public void addEmptyMoon(PlanetPK coordinates, String name, int gui) throws SQLException {
        CallableStatement callProc = DB_CONNECTION_.prepareCall("{ call addEmptyMoon(?, ?, ?, ?, ?) }");
        
        callProc.setInt(1, coordinates.getX());
        callProc.setInt(2, coordinates.getY());
        callProc.setInt(3, coordinates.getPosition());
        
        callProc.setString(4, name);
        callProc.setInt(5, gui);
        
        callProc.execute();
    }
    
    /**
     * Adds empty planet to existing solar system (to existing star). Returns position of new planet in solar system. 
     * @param coordinates Coordinates of the solar system when planet is to add
     * @param name Name of created planet
     * @param gui Index of image of the planet. Avaiable images can be downloaded from the database by {@link #downloadGUI() } method. 
     * @return Position of the new planet in solar system
     * @throws SQLException Thrown if given star does not exist. 
     */
    public short addEmptyPlanet(StarPK coordinates, String name, int gui) throws SQLException {
        PreparedStatement callFunc = 
                DB_CONNECTION_.prepareStatement("select addEmptyPlanet(?, ?, ?, ?)",
                                               ResultSet.TYPE_FORWARD_ONLY,
                                               ResultSet.CONCUR_READ_ONLY);
        
        callFunc.setInt(1, coordinates.getX());
        callFunc.setInt(2, coordinates.getY());
        callFunc.setString(3, name);
        callFunc.setInt(4, gui);
        
        ResultSet result = callFunc.executeQuery();
        result.first();
        return result.getShort(1);
    }
    
    /**
     * Changes planet name
     * @param coordinates Planet coordinates
     * @param name New name of the planet
     */
    public void changePlanetName(PlanetPK coordinates, String name) throws SQLException {
        CallableStatement callProc = DB_CONNECTION_.prepareCall("update Planet set Name = ? where X = ? and Y = ? and Position = ?");
        
        callProc.setString(1, name);
        callProc.setString(2, Integer.toString(coordinates.getX()));
        callProc.setString(3, Integer.toString(coordinates.getY()));
        callProc.setString(4, Integer.toString(coordinates.getPosition()));
        
        callProc.execute();
    }
    
    /**
     * Adds new user to the game. Assign him random empty planet. 
     * @param nick Nick of created user. 
     * @param password Password
     * @throws SQLException  Thrown if user with given nick already exists. 
     */
    public void addNewUser(String nick, String password) throws SQLException {
        CallableStatement callProc = DB_CONNECTION_.prepareCall("{ call addNewUser(?, ?) }");
        
        callProc.setString(1, nick);
        callProc.setString(2, password);
        
        callProc.execute();
    }
    
    /**
     * Adds new star to the galaxy. 
     * @param coordinates Coordinates of the star 
     * @param radius Radius of the star. The bigger the radius, the more power will solar satellites in the system provide. 
     * @param gui Index if image of the star. Available images can be downloaded from the  database by {@link #downloadGUI() } method. 
     * @throws SQLException Thrown if star with specified coordinates already exists. 
     */
    public void addStar(StarPK coordinates, int radius, int gui) throws SQLException {
        CallableStatement callProc = DB_CONNECTION_.prepareCall("{ call addStar(?, ?, ?, ?) }");
        
        callProc.setInt(1, coordinates.getX());
        callProc.setInt(2, coordinates.getY());
        callProc.setInt(3, radius);
        callProc.setInt(4, gui);
        
        callProc.execute();
    }
    
    /**
     * Starts developing of specified building on given location to the next level.
     * @param coordinates Coordinates of the location when building is
     * @param location Building may be build either on planet or on the moon.
     * @param building Building to build. 
     * @throws SQLException Thrown if specified location does not exist.
     * @see Location
     * @see Building
     */
    public void buildUpBuilding(PlanetPK coordinates, Location location, Building building) throws SQLException {
        CallableStatement callProc = DB_CONNECTION_.prepareCall("{ call buildUpBuilding(?, ?, ?, ?, ?) }");
        
        callProc.setInt(1, coordinates.getX());
        callProc.setInt(2, coordinates.getY());
        callProc.setInt(3, coordinates.getPosition());
        
        callProc.setString(4, location.name());
        callProc.setString(5, building.name());
        
        callProc.execute();
    }
    
    /**
     * Assigns specified planet to given user. 
     * @param user User to which planet must be assigned
     * @param coordinates Coordinates of the planet to colonize
     * @throws SQLException Thrown if specified planet or specified user does not exist. 
     */
    public void colonizePlanet(String user, PlanetPK coordinates) throws SQLException {
        CallableStatement callProc = DB_CONNECTION_.prepareCall("{ call colonizePlanet(?, ?, ?, ?) }");
        
        callProc.setString(1, user);
        callProc.setInt(2, coordinates.getX());
        callProc.setInt(3, coordinates.getY());
        callProc.setInt(4, coordinates.getPosition());
        
        callProc.execute();
    }
    
    /**
     * Deletes specified user from database, Does not delete planets of the user.
     * Planet of the user will by possible to colonize by another user. 
     * @param user User to delete
     * @throws SQLException Thrown if specified user does not exist. 
     */
    public void deleteUser(String user) throws SQLException {
        CallableStatement callProc = DB_CONNECTION_.prepareCall("{ call deleteUser(?) }");
        
        callProc.setString(1, user);
        
        callProc.execute();
    }
    
    /**
     * Creates new order of building ships.
     * @param coordinates Coordinates of the location when order is to add
     * @param location Location - items may be build either on planet or on the moon
     * @param item Name of item to build
     * @param number Number of items to builds
     * @throws SQLException  Thrown if specified location or item does not exist. 
     */
    public void newShipOrder(PlanetPK coordinates, Location location, ShipyardItem item, int number) throws SQLException {
        CallableStatement callProc = DB_CONNECTION_.prepareCall("{ call newShipOrder(?, ?, ?, ?, ?, ?) }");
        
        callProc.setInt(1, coordinates.getX());
        callProc.setInt(2, coordinates.getY());
        callProc.setInt(3, coordinates.getPosition()); 
        callProc.setString(4, location.name());
        callProc.setString(5, item.name());
        callProc.setInt(6, number);
        
        callProc.execute();
    }
    
    /**
     * Creates new order of building defense systems.
     * @param coordinates Coordinates of the location when order is to add
     * @param location Location - items may be build either on planet or on the moon
     * @param item Name of item to build
     * @param number Number of items to builds
     * @throws SQLException  Thrown if specified location or item does not exist. 
     */
    public void newDefenseOrder(PlanetPK coordinates, Location location, ShipyardItem item, int number) throws SQLException {
        CallableStatement callProc = DB_CONNECTION_.prepareCall("{ call newDefenseOrder(?, ?, ?, ?, ?, ?) }");
        
        callProc.setInt(1, coordinates.getX());
        callProc.setInt(2, coordinates.getY());
        callProc.setInt(3, coordinates.getPosition()); 
        callProc.setString(4, location.name());
        callProc.setString(5, item.name());
        callProc.setInt(6, number);
        
        callProc.execute();
    }
    
    /**
     * Creates order to research specified technology to next level.
     * If on specified planet is built Research Network, research will be done on all planets that are in the range on the research network. This will shorten time to end the research. 
     * @param coordinates Coordinates of the location when technology is to research
     * @param research Name of technology to research
     * @throws SQLException  Thrown if specified planet or technology does not exist. 
     */
    public void researchUp(PlanetPK coordinates, Research research) throws SQLException {
        CallableStatement callProc = DB_CONNECTION_.prepareCall("{ call researchUp(?, ?, ?, ?) }");
        
        callProc.setInt(1, coordinates.getX());
        callProc.setInt(2, coordinates.getY());
        callProc.setInt(3, coordinates.getPosition()); 
        callProc.setString(4, research.name());
        
        callProc.execute();
    }
    
    /**
     * Sedns fleet with specified mission. 
     * @param startCoordinates Coordinates of the starting location
     * @param location1 Starting location mey be either planet or moon
     * @param mission Mission of the fleet. 
     * @param endCoordinates Coordinates of the destiny location
     * @param location2 Ending location may be either planet or moon or debris (when mission is recycle or transport)
     * @param fleet Fleet to send
     * @param speed Speed rate of the fleet (in range 0.0 to 1.0)
     * @param metal Amount of transported metal
     * @param cristal Amount of transported cristal
     * @param deuter Amount of transported deuter
     * @param antimatter Amount of transported antimatter
     * @throws SQLException Thrown if starting or ending location does not exist. 
     * @throws SpeedRateOutOfRange Thrown if speed parameter is less than 0 or greater than 1
     */
    public void sendFlight(PlanetPK startCoordinates, Location location1, Mission mission, PlanetPK endCoordinates, Location location2, Fleet fleet, double speed, int metal, int cristal, int deuter, int antimatter) throws SQLException, SpeedRateOutOfRange {
        CallableStatement callProc = DB_CONNECTION_.prepareCall("{ call sendFlight(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) }");
        if(speed < 0 || speed > 1)
            throw new SpeedRateOutOfRange();
        
        callProc.setInt(1, startCoordinates.getX());
        callProc.setInt(2, startCoordinates.getY());
        callProc.setInt(3, startCoordinates.getPosition());
        callProc.setString(4, location1.name());
        callProc.setString(5, mission.name());
        callProc.setInt(6, endCoordinates.getX());
        callProc.setInt(7, endCoordinates.getY());
        callProc.setInt(8, endCoordinates.getPosition());
        callProc.setString(9, location2.name());
        
        callProc.setInt(10, fleet.getTransporter());
        callProc.setInt(11, fleet.getFreighter());
        callProc.setInt(12, fleet.getRecycler());
        callProc.setInt(13, fleet.getColonizer());
        callProc.setInt(14, fleet.getSpySond());
        callProc.setInt(15, fleet.getFighter());
        callProc.setInt(16, fleet.getIonFighter());
        callProc.setInt(17, fleet.getFrigate());
        callProc.setInt(18, fleet.getBomber());
        callProc.setInt(19, fleet.getCruiser());
        callProc.setInt(20, fleet.getDrednot());
        callProc.setInt(21, fleet.getDestroyer());
        callProc.setInt(22, fleet.getDeathStar());
        
        callProc.setDouble(23, speed);
        callProc.setInt(24, metal);
        callProc.setInt(25, cristal);
        callProc.setInt(26, deuter);
        callProc.setInt(27, antimatter);
        
        callProc.execute();
    }
    
    /**
     * Sens message to user.
     * @param from Nick of the sender
     * @param to Nick of the receiver
     * @param date Date when the message was send
     * @param text Text of the message
     * @throws SQLException Thrown if either sender or receiver does not exist. 
     */
    public void sendMessage(String from, String to, java.util.Date date, String text) throws SQLException {
        CallableStatement callProc = DB_CONNECTION_.prepareCall("{ call sendMessage(?, ?, ?, ?) }");
        
        callProc.setString(1, from);
        callProc.setString(2, to);
        callProc.setString(3, new java.sql.Timestamp(date.getTime()).toString()); /* TODO - format date */ 
        callProc.setString(4, text);
        
        callProc.execute();
    }
    
    /**
     * Deletes message from game server. 
     * @param msg Message to delete.
     */
    public void deleteMessage(Message msg) throws SQLException {
        CallableStatement callProc = DB_CONNECTION_.prepareCall("{ call deleteMessage(?) }");
        callProc.setInt(1, msg.getId());
        
        callProc.execute();
    }
    
    /**
     * Changes nick if the user.
     * @param previousNick Nick of the user to change
     * @param nick New nick
     * @throws SQLException Thrown if specified user does nit exist or new nick is already in use.  
     */
    public void setUserNick(String previousNick, String nick) throws SQLException {
        CallableStatement callProc = DB_CONNECTION_.prepareCall("{ call setUserNick(?, ?) }");
        
        callProc.setString(1, previousNick);
        callProc.setString(2, nick);
        
        callProc.execute();
    }
    
    /**
     * Changes user password.
     * @param nick Uset to change the password
     * @param password New password
     * @throws SQLException Thrown if specified user does not exist. 
     */
    public void setUserPassword(String nick, String password) throws SQLException {
        CallableStatement callProc = DB_CONNECTION_.prepareCall("{ call setUserPassword(?, ?) }");
        
        callProc.setString(1, nick);
        callProc.setString(2, password);
        
        callProc.execute();
    }
    
    /**
     * Changes user urlop status. 
     * @param nick Nick of the user to change urlop status
     * @param urlopOn New urlop status (true/false)
     * @throws SQLException  Thrown if specified user does not exist. 
     */
    public void setUserUrlop(String nick, boolean urlopOn) throws SQLException {
        CallableStatement callProc = DB_CONNECTION_.prepareCall("{ call setUserUrlop(?, ?) }");
        
        callProc.setString(1, nick);
        if(urlopOn)
            callProc.setString(2, "1");
        else
            callProc.setString(2, "0");
        
        callProc.execute();
    }
    
    /**
     * Turns specifed flight back. 
     * @param id ID of the flight to turn back
     * @throws SQLException Thrown if specified flight does not exist. 
     */
    public void turnFlightBack(int id) throws SQLException {
        CallableStatement callProc = DB_CONNECTION_.prepareCall("{ call turnFlightBack(?) }");
        
        callProc.setInt(1, id);
        
        callProc.execute();
    }
    
    /**
     * Refreshes planet materials amount, checks for ended building, research or shipyard orders or flights. 
     * Actualizes all planet buildings, technologies and fleets. 
     * @param coordinates Coordinates of the location to update
     * @throws SQLException Thrown of specified planet does not exist. 
     */
    public void updatePlanet(PlanetPK coordinates) throws SQLException {
        CallableStatement callProc = DB_CONNECTION_.prepareCall("{ call updatePlanet(?, ?, ?) }");
        
        callProc.setInt(1, coordinates.getX());
        callProc.setInt(2, coordinates.getY());
        callProc.setInt(3, coordinates.getPosition()); 
        
        callProc.execute();
    }
    
    /**
     * Calculates energy amount available on the location. 
     * @param coordinates Coordinates of the location when energy is to calculate
     * @param location Energy may be calculated either on planet or on the moon
     * @return Amount of energy available on specified location. 
     * @throws SQLException Thrown if specified location does not exists. 
     */
    public int availableEnergy(PlanetPK coordinates, Location location) throws SQLException {
        PreparedStatement callFunc = 
                DB_CONNECTION_.prepareStatement("select availableEnergy(?, ?, ?, ?)",
                                               ResultSet.TYPE_FORWARD_ONLY,
                                               ResultSet.CONCUR_READ_ONLY);
        callFunc.setInt(1, coordinates.getX());
        callFunc.setInt(2, coordinates.getY());
        callFunc.setInt(3, coordinates.getPosition()); 
        callFunc.setString(4, location.name());
        
        ResultSet result = callFunc.executeQuery();
        result.first();
        return result.getInt(1);
    }
    
    /**
     * Calculates cost of developing specified building to the next level. 
     * @param coordinates Coordinates of the location when the building is
     * @param location Building may exist either on planet or on moon
     * @param material Kind of material to calculate (Metal, Cristal, Deuter or antimatter)
     * @param building Name of building to calculate
     * @return Cost of developing specified building on specified location to the next level. 
     * @throws SQLException Thrown of specified location or building does not exist. 
     */
    public int buildingCost(PlanetPK coordinates, Location location, Material material, Building building) throws SQLException {
        PreparedStatement callFunc = 
                DB_CONNECTION_.prepareStatement("select buildingCost(?, ?, ?, ?, ?, ?)",
                                               ResultSet.TYPE_FORWARD_ONLY,
                                               ResultSet.CONCUR_READ_ONLY);
        callFunc.setInt(1, coordinates.getX());
        callFunc.setInt(2, coordinates.getY());
        callFunc.setInt(3, coordinates.getPosition()); 
        callFunc.setString(4, location.name());
        callFunc.setString(5, material.name());
        callFunc.setString(6, building.name());
        
        ResultSet result = callFunc.executeQuery();
        result.first();
        return result.getInt(1);
    }
    
    /**
     * Calculates cost of developing specified building from previous to the current lvl.  
     * @param coordinates Coordinates of the location when the building is
     * @param location Building may exist either on planet or on moon
     * @param material Kind of material to calculate (Metal, Cristal, Deuter or antimatter)
     * @param building Name of building to calculate
     * @return Cost of developing specified building from previous to the current lvl. 
     * @throws SQLException Thrown of specified location or building does not exist. 
     */
    public int buildingCurrentLvlCost(PlanetPK coordinates, Location location, Material material, Building building) throws SQLException {
        PreparedStatement callFunc = 
                DB_CONNECTION_.prepareStatement("select buildingCostCurrentLvl(?, ?, ?, ?, ?, ?)",
                                               ResultSet.TYPE_FORWARD_ONLY,
                                               ResultSet.CONCUR_READ_ONLY);
        callFunc.setInt(1, coordinates.getX());
        callFunc.setInt(2, coordinates.getY());
        callFunc.setInt(3, coordinates.getPosition()); 
        callFunc.setString(4, location.name());
        callFunc.setString(5, material.name());
        callFunc.setString(6, building.name());
        
        ResultSet result = callFunc.executeQuery();
        result.first();
        return result.getInt(1);
    }
    
    /**
     * Checks if technological requirements to develop building are met. 
     * @param coordinates Coordinates of the location when the building is
     * @param location Building may exist either on planet or on moon
     * @param building Building to check
     * @return True, if all requirements to develop given building are met on the given planet. False, otherwise. 
     * @throws SQLException Thrown if specified location does not exist. 
     */
    public boolean buildingEnabled(PlanetPK coordinates, Location location, Building building) throws SQLException {
        PreparedStatement callFunc = 
                DB_CONNECTION_.prepareStatement("select buildingEnabled(?, ?, ?, ?, ?)",
                                               ResultSet.TYPE_FORWARD_ONLY,
                                               ResultSet.CONCUR_READ_ONLY);
        callFunc.setInt(1, coordinates.getX());
        callFunc.setInt(2, coordinates.getY());
        callFunc.setInt(3, coordinates.getPosition()); 
        callFunc.setString(4, location.name());
        callFunc.setString(5, building.name());
        
        ResultSet result = callFunc.executeQuery();
        result.first();
        
        return result.getInt(1) == 1;
    }
    
    /**
     * Check if specified location has enough materials to develop specified building to the next level. 
     * @param coordinates Coordinates of the location when the building is
     * @param location Building may exist either on planet or on the moon
     * @param building Name of building to check
     * @return True, if given location has enough materials to develop given building. False, otherwise. 
     * @throws SQLException Thrown if specified location does not exist. 
     */
    public boolean buildingEnoughMaterials(PlanetPK coordinates, Location location, Building building) throws SQLException {
        PreparedStatement callFunc = 
                DB_CONNECTION_.prepareStatement("select buildingEnoughMaterials(?, ?, ?, ?, ?)",
                                               ResultSet.TYPE_FORWARD_ONLY,
                                               ResultSet.CONCUR_READ_ONLY);
        callFunc.setInt(1, coordinates.getX());
        callFunc.setInt(2, coordinates.getY());
        callFunc.setInt(3, coordinates.getPosition()); 
        callFunc.setString(4, location.name());
        callFunc.setString(5, building.name());
        
        ResultSet result = callFunc.executeQuery();
        result.first();
        return result.getInt(1) == 1;
    }
    
    /**
     * Returns level of specified building on specified location
     * @param coordinates Coordinates of the location when the building is
     * @param location Building may exist either on planet or on the moon
     * @param building Name of building to check
     * @return Level of given building on specified location
     * @throws SQLException  Thrown if specified location does not exist. 
     */
    public int buildingLvl(PlanetPK coordinates, Location location, Building building) throws SQLException {
        PreparedStatement callFunc = 
                DB_CONNECTION_.prepareStatement("select buildingLvl(?, ?, ?, ?, ?)",
                                               ResultSet.TYPE_FORWARD_ONLY,
                                               ResultSet.CONCUR_READ_ONLY);
        callFunc.setInt(1, coordinates.getX());
        callFunc.setInt(2, coordinates.getY());
        callFunc.setInt(3, coordinates.getPosition()); 
        callFunc.setString(4, location.name());
        callFunc.setString(5, building.name());
        
        ResultSet result = callFunc.executeQuery();
        result.first();
        return result.getInt(1);
    }
    
    /**
     * Calculates energy demand of given building on next level than actually built. 
     * Function may be used to check if a location has enough energy to build the building. 
     * @param coordinates Coordinates of the location when the building is
     * @param location Building may exist either on planet or on moon
     * @param building Name of building to calculate
     * @return Energy demand of the building on the next level than actual
     * @throws SQLException  Thrown if specified location does not exist
     */
    public int buildingNextLvlEnergy(PlanetPK coordinates, Location location, Building building) throws SQLException {
        PreparedStatement callFunc = 
                DB_CONNECTION_.prepareStatement("select buildingNextLvlEnergy(?, ?, ?, ?, ?)",
                                               ResultSet.TYPE_FORWARD_ONLY,
                                               ResultSet.CONCUR_READ_ONLY);
        callFunc.setInt(1, coordinates.getX());
        callFunc.setInt(2, coordinates.getY());
        callFunc.setInt(3, coordinates.getPosition()); 
        callFunc.setString(4, location.name());
        callFunc.setString(5, building.name());
        
        ResultSet result = callFunc.executeQuery();
        result.first();
        return result.getInt(1);
    }
    
    /**
     * Calculates building productivity on given location
     * @param coordinates Coordinates of the location when the building is
     * @param location Building may exist either on planet or on moon
     * @param building Name of building to calculate
     * @return Productivity of specified building 
     * @throws SQLException  Thrown if specified location does not exist
     */
    public int buildingProductivity(PlanetPK coordinates, Location location, Building building) throws SQLException {
        PreparedStatement callFunc = 
                DB_CONNECTION_.prepareStatement("select buildingProductivity(?, ?, ?, ?, ?)",
                                               ResultSet.TYPE_FORWARD_ONLY,
                                               ResultSet.CONCUR_READ_ONLY);
        callFunc.setInt(1, coordinates.getX());
        callFunc.setInt(2, coordinates.getY());
        callFunc.setInt(3, coordinates.getPosition()); 
        callFunc.setString(4, location.name());
        callFunc.setString(5, building.name());
        
        ResultSet result = callFunc.executeQuery();
        result.first();
        return result.getInt(1);
    }
    
    /**
     * Calculates building productivity on the next level on given location
     * @param coordinates Coordinates of the location when the building is
     * @param location Building may exist either on planet or on moon
     * @param building Name of building to calculate
     * @return Productivity of specified building 
     * @throws SQLException  Thrown if specified location does not exist
     */
    public int buildingNextLvlProductivity(PlanetPK coordinates, Location location, Building building) throws SQLException {
        PreparedStatement callFunc = 
                DB_CONNECTION_.prepareStatement("select buildingNextLvlProductivity(?, ?, ?, ?, ?)",
                                               ResultSet.TYPE_FORWARD_ONLY,
                                               ResultSet.CONCUR_READ_ONLY);
        callFunc.setInt(1, coordinates.getX());
        callFunc.setInt(2, coordinates.getY());
        callFunc.setInt(3, coordinates.getPosition()); 
        callFunc.setString(4, location.name());
        callFunc.setString(5, building.name());
        
        ResultSet result = callFunc.executeQuery();
        result.first();
        return result.getInt(1);
    }
    
    /**
     * Calculates time required to develop building to next level. 
     * @param coordinates Coordinates of the location when the building is
     * @param location Building may exist either on planet or on the moon
     * @param building Name of building to calculate
     * @return Time necessary to develop building to next level
     * @throws SQLException  Thrown if specified location does not exist 
     */
    public String buildingTime(PlanetPK coordinates, Location location, Building building) throws SQLException {
        PreparedStatement callFunc = 
                DB_CONNECTION_.prepareStatement("select buildingTime(?, ?, ?, ?, ?)",
                                               ResultSet.TYPE_FORWARD_ONLY,
                                               ResultSet.CONCUR_READ_ONLY);
        callFunc.setInt(1, coordinates.getX());
        callFunc.setInt(2, coordinates.getY());
        callFunc.setInt(3, coordinates.getPosition()); 
        callFunc.setString(4, location.name());
        callFunc.setString(5, building.name());
        
        ResultSet result = callFunc.executeQuery();
        result.first();
        return result.getString(1);
    }
    
    /**
     * Calculates total capacity of the fleet
     * @param fleet Fleet which capacity is to calculate
     * @return Capacity of the fleet
     * @throws SQLException Thrown only if internet connection to database fail. 
     */
    public int flightCapacity(Fleet fleet) throws SQLException {
        PreparedStatement callFunc = 
                DB_CONNECTION_.prepareStatement("select flightCapacity(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                                               ResultSet.TYPE_FORWARD_ONLY,
                                               ResultSet.CONCUR_READ_ONLY);
        callFunc.setInt(1, fleet.getTransporter());
        callFunc.setInt(2, fleet.getFreighter());
        callFunc.setInt(3, fleet.getRecycler());
        callFunc.setInt(4, fleet.getColonizer());
        callFunc.setInt(5, fleet.getSpySond());
        callFunc.setInt(6, fleet.getFighter());
        callFunc.setInt(7, fleet.getIonFighter());
        callFunc.setInt(8, fleet.getFrigate());
        callFunc.setInt(9, fleet.getBomber());
        callFunc.setInt(10, fleet.getCruiser());
        callFunc.setInt(11, fleet.getDrednot());
        callFunc.setInt(12, fleet.getDestroyer());
        callFunc.setInt(13, fleet.getDeathStar());
        
        ResultSet result = callFunc.executeQuery();
        result.first();
        return result.getInt(1);
    }
    
    /**
     * Checks if specified location has enough ships to send a fleet
     * @param coordinates Coordinates of the location to check
     * @param location Fleet may be sent either from planet or from moon
     * @param fleet Fleet which is checked
     * @return True, if specified location has enough ships to send given fleet. False, otherwise. 
     * @throws SQLException Thrown if specified location does not exist. 
     */
    public boolean flightEnoughShips(PlanetPK coordinates, Location location, Fleet fleet) throws SQLException {
        PreparedStatement callFunc = 
                DB_CONNECTION_.prepareStatement("select flightEnoughShips(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                                               ResultSet.TYPE_FORWARD_ONLY,
                                               ResultSet.CONCUR_READ_ONLY);
        callFunc.setInt(1, coordinates.getX());
        callFunc.setInt(2, coordinates.getY());
        callFunc.setInt(3, coordinates.getPosition()); 
        callFunc.setString(4, location.name());
        
        callFunc.setInt(5, fleet.getTransporter());
        callFunc.setInt(6, fleet.getFreighter());
        callFunc.setInt(7, fleet.getRecycler());
        callFunc.setInt(8, fleet.getColonizer());
        callFunc.setInt(9, fleet.getSpySond());
        callFunc.setInt(10, fleet.getFighter());
        callFunc.setInt(11, fleet.getIonFighter());
        callFunc.setInt(12, fleet.getFrigate());
        callFunc.setInt(13, fleet.getBomber());
        callFunc.setInt(14, fleet.getCruiser());
        callFunc.setInt(15, fleet.getDrednot());
        callFunc.setInt(16, fleet.getDestroyer());
        callFunc.setInt(17, fleet.getDeathStar());
        
        ResultSet result = callFunc.executeQuery();
        result.first();
        return result.getInt(1) == 1;
    }
    
    /**
     * Calculates fuel necessary to send a fleet
     * @param startCoordinates Coordinates of the starting location
     * @param endCoordinates Coordinates of the destiny location
     * @param fleet Fleet to send
     * @param speed Speed rate of the fleet - in range from 0.0 to 1.0 
     * @return Fuel necessary to send the fleet
     * @throws SQLException Thrown if specified starting or destiny location does not exist
     * @throws SpeedRateOutOfRange Thrown if speed parameter is less than 0 or grater than 1
     */
    public int flightFuel(PlanetPK startCoordinates, PlanetPK endCoordinates, Fleet fleet, double speed) throws SQLException, SpeedRateOutOfRange {
        if(speed < 0 || speed > 1)
            throw new SpeedRateOutOfRange();
        
        PreparedStatement callFunc = 
                DB_CONNECTION_.prepareStatement("select flightFuel(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                                               ResultSet.TYPE_FORWARD_ONLY,
                                               ResultSet.CONCUR_READ_ONLY);
        callFunc.setInt(1, startCoordinates.getX());
        callFunc.setInt(2, startCoordinates.getY());
        callFunc.setInt(3, startCoordinates.getPosition());
        callFunc.setInt(4, endCoordinates.getX());
        callFunc.setInt(5, endCoordinates.getY());
        callFunc.setInt(6, endCoordinates.getPosition());
        
        callFunc.setInt(7, fleet.getTransporter());
        callFunc.setInt(8, fleet.getFreighter());
        callFunc.setInt(9, fleet.getRecycler());
        callFunc.setInt(10, fleet.getColonizer());
        callFunc.setInt(11, fleet.getSpySond());
        callFunc.setInt(12, fleet.getFighter());
        callFunc.setInt(13, fleet.getIonFighter());
        callFunc.setInt(14, fleet.getFrigate());
        callFunc.setInt(15, fleet.getBomber());
        callFunc.setInt(16, fleet.getCruiser());
        callFunc.setInt(17, fleet.getDrednot());
        callFunc.setInt(18, fleet.getDestroyer());
        callFunc.setInt(19, fleet.getDeathStar());
        
        callFunc.setDouble(20, speed);
        
        ResultSet result = callFunc.executeQuery();
        result.first();
        return result.getInt(1);
    }
    
    /**
     * Calculates fleet velocity
     * @param startCoordinates Coordinates of the start location
     * @param fleet Fleet to send
     * @param speed Speed rate of the fleet (in range from 0.0 to 1.0)
     * @return Fleet velovity
     * @throws SQLException Thrown if given location does not exist
     * @throws SpeedRateOutOfRange  Thrown if given speed parameter is less than 0 or greater than 1
     */
    public int flightSpeed(PlanetPK startCoordinates, Fleet fleet, double speed) throws SQLException, SpeedRateOutOfRange {
        if(speed < 0 || speed > 1)
            throw new SpeedRateOutOfRange();
        
        PreparedStatement callFunc = 
                DB_CONNECTION_.prepareStatement("select flightSpeed(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                                               ResultSet.TYPE_FORWARD_ONLY,
                                               ResultSet.CONCUR_READ_ONLY);
        callFunc.setInt(1, startCoordinates.getX());
        callFunc.setInt(2, startCoordinates.getY());
        callFunc.setInt(3, startCoordinates.getPosition());
        
        callFunc.setInt(4, fleet.getTransporter());
        callFunc.setInt(5, fleet.getFreighter());
        callFunc.setInt(6, fleet.getRecycler());
        callFunc.setInt(7, fleet.getColonizer());
        callFunc.setInt(8, fleet.getSpySond());
        callFunc.setInt(9, fleet.getFighter());
        callFunc.setInt(10, fleet.getIonFighter());
        callFunc.setInt(11, fleet.getFrigate());
        callFunc.setInt(12, fleet.getBomber());
        callFunc.setInt(13, fleet.getCruiser());
        callFunc.setInt(14, fleet.getDrednot());
        callFunc.setInt(15, fleet.getDestroyer());
        callFunc.setInt(16, fleet.getDeathStar());
        
        callFunc.setDouble(17, speed);
        
        ResultSet result = callFunc.executeQuery();
        result.first();
        return result.getInt(1);
    }
    
    /**
     * Calculates time the fleet will fly. 
     * @param startCoordinates Coordinates of the start location
     * @param endCoordinates Coordinates of the destiny location
     * @param fleet Fleet to send
     * @param speed Speed rate of the fleet (in range from 0.0 to 1.0)
     * @return Time the fleet will fly. 
     * @throws SQLException Thrown if specified starting or destiny location does not exist
     * @throws SpeedRateOutOfRange Thrown if speed parameter is less than 0 or greater than 1
     */
    public String flightTime(PlanetPK startCoordinates, PlanetPK endCoordinates, Fleet fleet, double speed) throws SQLException, SpeedRateOutOfRange {
        if(speed < 0 || speed > 1)
            throw new SpeedRateOutOfRange();
        
        PreparedStatement callFunc = 
                DB_CONNECTION_.prepareStatement("select flightTime(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                                               ResultSet.TYPE_FORWARD_ONLY,
                                               ResultSet.CONCUR_READ_ONLY);
        callFunc.setInt(1, startCoordinates.getX());
        callFunc.setInt(2, startCoordinates.getY());
        callFunc.setInt(3, startCoordinates.getPosition());
        callFunc.setInt(4, endCoordinates.getX());
        callFunc.setInt(5, endCoordinates.getY());
        callFunc.setInt(6, endCoordinates.getPosition());
        
        callFunc.setInt(7, fleet.getTransporter());
        callFunc.setInt(8, fleet.getFreighter());
        callFunc.setInt(9, fleet.getRecycler());
        callFunc.setInt(10, fleet.getColonizer());
        callFunc.setInt(11, fleet.getSpySond());
        callFunc.setInt(12, fleet.getFighter());
        callFunc.setInt(13, fleet.getIonFighter());
        callFunc.setInt(14, fleet.getFrigate());
        callFunc.setInt(15, fleet.getBomber());
        callFunc.setInt(16, fleet.getCruiser());
        callFunc.setInt(17, fleet.getDrednot());
        callFunc.setInt(18, fleet.getDestroyer());
        callFunc.setInt(19, fleet.getDeathStar());
        
        callFunc.setDouble(20, speed);
        
        ResultSet result = callFunc.executeQuery();
        result.first();
        return result.getString(1);
    }
    
    /**
     * Calculater maximal number of possible to build ships, according to the location's available resources
     * @param coordinates Coordinates of the location
     * @param location Ships may be built either on planet or on the moon
     * @param item Name of item to build
     * @return Maximal number of ships possible to build
     * @throws SQLException Thrown if specified location does not exist. 
     */
    public int maxShipItems(PlanetPK coordinates, Location location, ShipyardItem item) throws SQLException {
        PreparedStatement callFunc = 
                DB_CONNECTION_.prepareStatement("select maxShipItems(?, ?, ?, ?, ?)",
                                               ResultSet.TYPE_FORWARD_ONLY,
                                               ResultSet.CONCUR_READ_ONLY);
        callFunc.setInt(1, coordinates.getX());
        callFunc.setInt(2, coordinates.getY());
        callFunc.setInt(3, coordinates.getPosition()); 
        callFunc.setString(4, location.name());
        callFunc.setString(5, item.name());
        
        ResultSet result = callFunc.executeQuery();
        result.first();
        return result.getInt(1);
    }
    
    /**
     * Calculater maximal number of possible to build defense systems, according to the location's available resources
     * @param coordinates Coordinates of the location
     * @param location Defense systems may be built either on planet or on the moon
     * @param item Name of item to build
     * @return Maximal number of defense systems possible to build.
     * @throws SQLException Thrown if specified location does not exist. 
     */
    public int maxDefenseItems(PlanetPK coordinates, Location location, ShipyardItem item) throws SQLException {
        PreparedStatement callFunc = 
                DB_CONNECTION_.prepareStatement("select maxDefenseItems(?, ?, ?, ?, ?)",
                                               ResultSet.TYPE_FORWARD_ONLY,
                                               ResultSet.CONCUR_READ_ONLY);
        callFunc.setInt(1, coordinates.getX());
        callFunc.setInt(2, coordinates.getY());
        callFunc.setInt(3, coordinates.getPosition()); 
        callFunc.setString(4, location.name());
        callFunc.setString(5, item.name());
        
        ResultSet result = callFunc.executeQuery();
        result.first();
        return result.getInt(1);
    }
    
    /**
     * Returns urlop status of the user.
     * @param user User to check
     * @return True, if given user is on the ulop, false otherwise
     * @throws SQLException  Thrown if given user does not exist
     */
    public boolean onUrlop(String user) throws SQLException {
        PreparedStatement callFunc = 
                DB_CONNECTION_.prepareStatement("select onUrlop(?)",
                                               ResultSet.TYPE_FORWARD_ONLY,
                                               ResultSet.CONCUR_READ_ONLY);
        callFunc.setString(1, user);
        
        ResultSet result = callFunc.executeQuery();
        result.first();
        return result.getInt(1) == 1;
    }
    
    /**
     * Calculates distance between two planets
     * @param coordinates1 Coordinates of the first location
     * @param coordinates2 Coordinates of the second location
     * @return Distance between planets
     * @throws SQLException Thrown only if the internet connection to database fails
     */
    public double planetDistance(PlanetPK coordinates1, PlanetPK coordinates2) throws SQLException {
        PreparedStatement callFunc = 
                DB_CONNECTION_.prepareStatement("select planetDistance(?, ?, ?, ?, ?, ?)",
                                               ResultSet.TYPE_FORWARD_ONLY,
                                               ResultSet.CONCUR_READ_ONLY);
        callFunc.setInt(1, coordinates1.getX());
        callFunc.setInt(2, coordinates1.getY());
        callFunc.setInt(3, coordinates1.getPosition());
        callFunc.setInt(4, coordinates2.getX());
        callFunc.setInt(5, coordinates2.getY());
        callFunc.setInt(6, coordinates2.getPosition());
        
        ResultSet result = callFunc.executeQuery();
        result.first();
        return result.getDouble(1);
    }  
    
    /**
     * Returns  cost of researching technology to the next level. 
     * @param coordinates Coordinates of the location when the technology is to research
     * @param material Kind of material to calulate
     * @param research Technology name to calculate
     * @return Cost of researching technology to the next level
     * @throws SQLException Thrown if specified planet does not exist. 
     */
    public int researchCost(PlanetPK coordinates, Material material, Research research) throws SQLException {
        PreparedStatement callFunc = 
                DB_CONNECTION_.prepareStatement("select researchCost(?, ?, ?, ?, ?)",
                                               ResultSet.TYPE_FORWARD_ONLY,
                                               ResultSet.CONCUR_READ_ONLY);
        callFunc.setInt(1, coordinates.getX());
        callFunc.setInt(2, coordinates.getY());
        callFunc.setInt(3, coordinates.getPosition()); 
        callFunc.setString(4, material.name());
        callFunc.setString(5, research.name());
        
        ResultSet result = callFunc.executeQuery();
        result.first();
        return result.getInt(1);
    }  
    
    /**
     * Checks if all technological requirements necessary to research a technology are met 
     * @param coordinates Coordinates of the location when the technology is to research
     * @param research Name fo the research to check
     * @return True, if all technological requirements necessary to research a technology are met, false otherwise. 
     * @throws SQLException Thrown if specified planet does not exist. 
     */
    public boolean researchEnabled(PlanetPK coordinates, Research research) throws SQLException {
        PreparedStatement callFunc = 
                DB_CONNECTION_.prepareStatement("select researchEnabled(?, ?, ?, ?)",
                                               ResultSet.TYPE_FORWARD_ONLY,
                                               ResultSet.CONCUR_READ_ONLY);
        callFunc.setInt(1, coordinates.getX());
        callFunc.setInt(2, coordinates.getY());
        callFunc.setInt(3, coordinates.getPosition()); 
        callFunc.setString(4, research.name());
        
        ResultSet result = callFunc.executeQuery();
        result.first();
        return result.getInt(1) == 1;
    }  
    
    /**
     * Check if specified planet has enough resources to research a technology to the next level. 
     * @param coordinates Coordinates of the location when the technology is to research
     * @param research Name of technology to check
     * @return True if specified planet has enough resources to research a technology to the next level, false otherwise. 
     * @throws SQLException Thrown if specified planet does not exist. 
     */
    public boolean researchEnoughMaterials(PlanetPK coordinates, Research research) throws SQLException {
        PreparedStatement callFunc = 
                DB_CONNECTION_.prepareStatement("select researchEnoughMaterials(?, ?, ?, ?)",
                                               ResultSet.TYPE_FORWARD_ONLY,
                                               ResultSet.CONCUR_READ_ONLY);
        callFunc.setInt(1, coordinates.getX());
        callFunc.setInt(2, coordinates.getY());
        callFunc.setInt(3, coordinates.getPosition()); 
        callFunc.setString(4, research.name());
        
        ResultSet result = callFunc.executeQuery();
        result.first();
        return result.getInt(1) == 1;
    }  
    
    /**
     * Returns research level on specified planet.
     * @param coordinates Coordinates of the location when the technology is to research
     * @param research Research name to check
     * @return Research level on the planet
     * @throws SQLException Thrown of specified planet does not exist. 
     */
    public int researchLvl(PlanetPK coordinates, Research research) throws SQLException {
        PreparedStatement callFunc = 
                DB_CONNECTION_.prepareStatement("select researchLvl(?, ?, ?, ?)",
                                               ResultSet.TYPE_FORWARD_ONLY,
                                               ResultSet.CONCUR_READ_ONLY);
        callFunc.setInt(1, coordinates.getX());
        callFunc.setInt(2, coordinates.getY());
        callFunc.setInt(3, coordinates.getPosition()); 
        callFunc.setString(4, research.name());
        
        ResultSet result = callFunc.executeQuery();
        result.first();
        return result.getInt(1);
    }  
    
    /**
     * Returns time necessary to research technology to the next level. 
     * @param coordinates Coordinates of the location when the technology is to research
     * @param research Name of technology 
     * @return Time necessary to research technology to the next level.
     * @throws SQLException Thrown if given location does not exist. 
     */
    public String researchTime(PlanetPK coordinates, Research research) throws SQLException {
        PreparedStatement callFunc = 
                DB_CONNECTION_.prepareStatement("select researchTime(?, ?, ?, ?)",
                                               ResultSet.TYPE_FORWARD_ONLY,
                                               ResultSet.CONCUR_READ_ONLY);
        callFunc.setInt(1, coordinates.getX());
        callFunc.setInt(2, coordinates.getY());
        callFunc.setInt(3, coordinates.getPosition()); 
        callFunc.setString(4, research.name());
        
        ResultSet result = callFunc.executeQuery();
        result.first();
        return result.getString(1);
    }  
    
    /**
     * Checks if it is possible to send a fleet from specified planet to the destiny. 
     * @param startCoordinates Coordinates of the start location
     * @param location1 Fleet may start either from planet or from moon
     * @param mission Mission of the fleet
     * @param endCoordinates Coordinates of the destiny location
     * @param location2 Destiny mey be either planet or moon or debris (if the misstion is transport or recycle)
     * @param fleet Fleet to send
     * @param speed Speed rate of the fleet (in range from 0.0 to 1.0)
     * @param metal Transported metal
     * @param cristal Transported cristal
     * @param deuter Transported deuter
     * @param antimatter Transported antimatter
     * @return True, if it is possible to send a fleet from specified planet to the destiny, false otherwise. 
     * @throws SQLException Thrown if specified starting or destiny localisation does not exist
     */
    public boolean sendFlightPossible(PlanetPK startCoordinates, Location location1, Mission mission, PlanetPK endCoordinates, Location location2, Fleet fleet, double speed, int metal, int cristal, int deuter, int antimatter) throws SQLException {
        PreparedStatement callFunc = 
                DB_CONNECTION_.prepareStatement("select sendFlightPossible(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                                               ResultSet.TYPE_FORWARD_ONLY,
                                               ResultSet.CONCUR_READ_ONLY);
        callFunc.setInt(1, startCoordinates.getX());
        callFunc.setInt(2, startCoordinates.getY());
        callFunc.setInt(3, startCoordinates.getPosition());
        callFunc.setString(4, location1.name());
        callFunc.setString(5, mission.name());
        callFunc.setInt(6, endCoordinates.getX());
        callFunc.setInt(7, endCoordinates.getY());
        callFunc.setInt(8, endCoordinates.getPosition());
        callFunc.setString(9, location2.name());
        
        callFunc.setInt(10, fleet.getTransporter());
        callFunc.setInt(11, fleet.getFreighter());
        callFunc.setInt(12, fleet.getRecycler());
        callFunc.setInt(13, fleet.getColonizer());
        callFunc.setInt(14, fleet.getSpySond());
        callFunc.setInt(15, fleet.getFighter());
        callFunc.setInt(16, fleet.getIonFighter());
        callFunc.setInt(17, fleet.getFrigate());
        callFunc.setInt(18, fleet.getBomber());
        callFunc.setInt(19, fleet.getCruiser());
        callFunc.setInt(20, fleet.getDrednot());
        callFunc.setInt(21, fleet.getDestroyer());
        callFunc.setInt(22, fleet.getDeathStar());
        
        callFunc.setDouble(23, speed);
        callFunc.setInt(24, metal);
        callFunc.setInt(25, cristal);
        callFunc.setInt(26, deuter);
        callFunc.setInt(27, antimatter);
        
        ResultSet result = callFunc.executeQuery();
        result.first();
        return result.getInt(1) == 1;
    }
    
    /**
     * Checks if all the technological requirement necessary to build specified defense system or ship are met. 
     * @param coordinates Coordinates of the location to check
     * @param location Ships may be built either on planet or on the moon
     * @param item Item to check
     * @return True, if all the technological requirement necessary to build specified defense system or ship are met, false otherwise. 
     * @throws SQLException Thrown if specified location does not exist. 
     */
    public boolean shipyardEnabled(PlanetPK coordinates, Location location, ShipyardItem item) throws SQLException {
        PreparedStatement callFunc = 
                DB_CONNECTION_.prepareStatement("select shipyardEnabled(?, ?, ?, ?, ?)",
                                               ResultSet.TYPE_FORWARD_ONLY,
                                               ResultSet.CONCUR_READ_ONLY);
        callFunc.setInt(1, coordinates.getX());
        callFunc.setInt(2, coordinates.getY());
        callFunc.setInt(3, coordinates.getPosition()); 
        callFunc.setString(4, location.name());
        callFunc.setString(5, item.name());
        
        ResultSet result = callFunc.executeQuery();
        result.first();
        return result.getInt(1) == 1;
    }  
    
    /**
     * Calculates the time neceesary to build a ship. 
     * @param coordinates Coordinates of the location
     * @param location Ships may be built either on planet or on the moon. 
     * @param item Kind of item to build
     * @param number Number of items to build
     * @return Time neceesary to build a ship
     * @throws SQLException Thrown if given location does not exist. 
     */
    public String shipTime(PlanetPK coordinates, Location location, ShipyardItem item, int number) throws SQLException {
        PreparedStatement callFunc = 
                DB_CONNECTION_.prepareStatement("select shipTime(?, ?, ?, ?, ?, ?)",
                                               ResultSet.TYPE_FORWARD_ONLY,
                                               ResultSet.CONCUR_READ_ONLY);
        callFunc.setInt(1, coordinates.getX());
        callFunc.setInt(2, coordinates.getY());
        callFunc.setInt(3, coordinates.getPosition()); 
        callFunc.setString(4, location.name());
        callFunc.setString(5, item.name());
        callFunc.setInt(6, number);
        
        ResultSet result = callFunc.executeQuery();
        result.first();
        return result.getString(1);
    }  
    
    /**
     * Calculates the time neceesary to build a defense system. 
     * @param coordinates Coordinates of the location
     * @param location Defense systems may be built either on planet or on the moon. 
     * @param item Kind of item to build
     * @param number Number of items to build
     * @return Time neceesary to build defense system.
     * @throws SQLException Thrown if given location does not exist. 
     */
    public String defenseTime(PlanetPK coordinates, Location location, ShipyardItem item, int number) throws SQLException {
        PreparedStatement callFunc = 
                DB_CONNECTION_.prepareStatement("select defenseTime(?, ?, ?, ?, ?, ?)",
                                               ResultSet.TYPE_FORWARD_ONLY,
                                               ResultSet.CONCUR_READ_ONLY);
        callFunc.setInt(1, coordinates.getX());
        callFunc.setInt(2, coordinates.getY());
        callFunc.setInt(3, coordinates.getPosition()); 
        callFunc.setString(4, location.name());
        callFunc.setString(5, item.name());
        callFunc.setInt(6, number);
        
        ResultSet result = callFunc.executeQuery();
        result.first();
        return result.getString(1);
    }  
    
    /**
     * Downloads data of all planets owned by specified user. 
     * @param user User
     * @return  List of all planets objects owned by specified user
     */
    public User getUserData(String user) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("XorGameLibraryPU");
        EntityManager manager = factory.createEntityManager();
        Query query = manager.createNamedQuery("User.findByNick");
        query.setParameter("nick", user);
        return (User)query.getSingleResult();
        
        /*EntityManagerFactory factory = Persistence.createEntityManagerFactory("XorGameLibraryPU");
        EntityManager manager = factory.createEntityManager();
        return manager.find(User.class, user);*/
    }  
    
    /**
     * Downloads total ranking of users. 
     * @return List of users with total points oredered by total points descending. 
     */
    public List<TotalRanking> getTotalRanking() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("XorGameLibraryPU");
        EntityManager manager = factory.createEntityManager();
        Query query = manager.createNamedQuery("TotalRanking.findAll");
        return query.getResultList();
    }
    
    /**
     * Downloads economy ranking of users. 
     * @return List of users with economy points oredered by economy points descending. 
     */
    public List<EconomyRanking> getEconomyRanking() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("XorGameLibraryPU");
        EntityManager manager = factory.createEntityManager();
        Query query = manager.createNamedQuery("EconomyRanking.findAll");
        return query.getResultList();
    }
    
    /**
     * Downloads research ranking of users. 
     * @return List of users with research points oredered by research points descending. 
     */
    public List<ResearchRanking> getResearchRanking() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("XorGameLibraryPU");
        EntityManager manager = factory.createEntityManager();
        Query query = manager.createNamedQuery("ResearchRanking.findAll");
        return query.getResultList();
    }
    
    /**
     * Downloads military ranking of users. 
     * @return List of users with military points oredered by military points descending. 
     */
    public List<MilitaryRanking> getMilitaryRanking() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("XorGameLibraryPU");
        EntityManager manager = factory.createEntityManager();
        Query query = manager.createNamedQuery("MilitaryRanking.findAll");
        return query.getResultList();
    }
    
    /**
     * Returns total points of specified user. 
     * @param user User nick
     * @return Total points value of the user. 
     */
    public int getTotalPoints(String user) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("XorGameLibraryPU");
        EntityManager manager = factory.createEntityManager();
        Query query = manager.createNamedQuery("TotalRanking.findByNick");
        query.setParameter("nick", user);
        TotalRanking result = ((List<TotalRanking>)query.getResultList()).get(0);
        return (int)Math.round(result.getTotalPoints());
    }
    
    /**
     * Returns economy points of specified user. 
     * @param user User nick
     * @return Economy points value of the user
     */
    public int getEconomyPoints(String user) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("XorGameLibraryPU");
        EntityManager manager = factory.createEntityManager();
        Query query = manager.createNamedQuery("EconomyRanking.findByNick");
        query.setParameter("nick", user);
        EconomyRanking result = ((List<EconomyRanking>)query.getResultList()).get(0);
        return (int)Math.round(result.getEconomyPoints());
    }
    
    /**
     * Returns research points of specified user. 
     * @param user User nick
     * @return Research points value of the user. 
     */
    public int getResearchPoints(String user) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("XorGameLibraryPU");
        EntityManager manager = factory.createEntityManager();
        Query query = manager.createNamedQuery("ResearchRanking.findByNick");
        query.setParameter("nick", user);
        ResearchRanking result = ((List<ResearchRanking>)query.getResultList()).get(0);
        return (int)Math.round(result.getResearchPoints());
    }
    
    /**
     * Returns military points of specified user. 
     * @param user User nick
     * @return Military points value of the user 
     */
    public int getMilitaryPoints(String user) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("XorGameLibraryPU");
        EntityManager manager = factory.createEntityManager();
        Query query = manager.createNamedQuery("MilitaryRanking.findByNick");
        query.setParameter("nick", user);
        MilitaryRanking result = ((List<MilitaryRanking>)query.getResultList()).get(0);
        return (int)Math.round(result.getMilitaryPoints());
    }
    
    /**
     * Returns cost of specified ship. 
     * @param item Name of ship. 
     * @param material Kind of material
     * @return Cost of specified ship. 
     */
    public int shipCost(ShipyardItem item, Material material) throws SQLException {
        String materialName = null;
        if(null != material)
            switch (material) {
            case M:
                materialName = "Metal";
                break;
            case K:
                materialName = "Cristal";
                break;
            case D:
                materialName = "Deuter";
                break;
            default:
                break;
        }
        PreparedStatement query = 
                DB_CONNECTION_.prepareStatement("select " + materialName + " from Ship_Params where Item = ?",
                                               ResultSet.TYPE_FORWARD_ONLY,
                                               ResultSet.CONCUR_READ_ONLY);
        
        query.setString(1, item.name());
        
        ResultSet result = query.executeQuery();
        result.first();
        return result.getInt(1);
    }
    
    /**
     * Returns cost of specified defense system. 
     * @param item Name of defense system. 
     * @param material Kind of material
     * @return Cost of specified defense system. 
     */
    public int defenseCost(ShipyardItem item, Material material) throws SQLException {
        String materialName = null;
        if(null != material)
            switch (material) {
            case M:
                materialName = "Metal";
                break;
            case K:
                materialName = "Cristal";
                break;
            case D:
                materialName = "Deuter";
                break;
            default:
                break;
        }
        
        PreparedStatement query = 
                DB_CONNECTION_.prepareStatement("select " + materialName + " from Defense_Params where Item = ?",
                                               ResultSet.TYPE_FORWARD_ONLY,
                                               ResultSet.CONCUR_READ_ONLY);
        
        query.setString(1, item.name());
        
        ResultSet result = query.executeQuery();
        result.first();
        return result.getInt(1);
    }
    
    /**
     * Returns set of planets visible ijn observatory from specified location
     * @param planet Planet coordinates where observatory is
     * @return Set of planets visible ijn observatory from specified location
     * @throws SQLException 
     */
    public Set<Planet> visiblePlanets(PlanetPK planet) throws SQLException {
        PreparedStatement query = 
                DB_CONNECTION_.prepareStatement(" call visiblePlanets(?, ?, ?) ",
                                               ResultSet.TYPE_FORWARD_ONLY,
                                               ResultSet.CONCUR_READ_ONLY);
        query.setInt(1, planet.getX());
        query.setInt(2, planet.getY());
        query.setInt(3, planet.getPosition());
        
        ResultSet resultSet = query.executeQuery();
        Set<Planet> result = new HashSet();
        resultSet.first();
        do {
            Short X = resultSet.getShort("X");
            Short Y = resultSet.getShort("Y");
            Short Position = resultSet.getShort("Position"); 
            String name = resultSet.getString("Name");
            String userNick = resultSet.getString("User"); 
            
            Planet newPlanet = new Planet();
            newPlanet.setPlanetPK(new PlanetPK(X, Y, Position)); 
            newPlanet.setName(name); 
            User newUser = new User();
            newUser.setNick(userNick); 
            newPlanet.setUser(newUser);
            
            result.add(newPlanet); 
        } while(resultSet.next());
        
        return result;
    } 
    
    public int shipyardItemAmount(PlanetPK coordinates, Location location, ShipyardItem item) throws SQLException {
        PreparedStatement callFunc = 
                DB_CONNECTION_.prepareStatement("select shipyardItemAmount(?, ?, ?, ?, ?)",
                                               ResultSet.TYPE_FORWARD_ONLY,
                                               ResultSet.CONCUR_READ_ONLY);
        callFunc.setShort(1, coordinates.getX());
        callFunc.setShort(2, coordinates.getY());
        callFunc.setShort(3, coordinates.getPosition());
        callFunc.setString(4, location.name());
        callFunc.setString(5, item.name());
        
        ResultSet result = callFunc.executeQuery();
        result.first();
        
        return result.getInt(1);
    }
    
    /**
     * Downloads from the database all the images that are part of the game client GUI and texts displayed. 
     * @return {@link GameGui } object that contains all GUI data. 
     */
    public GameGui downloadGUI() {
        return new GameGui();
    }
    
    /**
     * Checks if given password is correct. 
     * @param user Username to check
     * @param password Password to check
     * @return True if given password is correct for specified username, false otherwise. 
     * @throws SQLException Thrown if specified user does not exist. 
     */
    public boolean passwordCorrect(String user, String password) throws SQLException {
        PreparedStatement callFunc = 
                DB_CONNECTION_.prepareStatement("select passwordCorrect(?, ?)",
                                               ResultSet.TYPE_FORWARD_ONLY,
                                               ResultSet.CONCUR_READ_ONLY);
        callFunc.setString(1, user);
        callFunc.setString(2, password);
        
        ResultSet result = callFunc.executeQuery();
        result.first();
        
        return result.getInt(1) == 1;
    }
    
    /**
     * Closes the connection with database when work is over. 
     * @throws SQLException Thrown only when internet connecion fails. 
     */ 
    public void close() throws SQLException {
        DB_CONNECTION_.close();
    } 
}
 