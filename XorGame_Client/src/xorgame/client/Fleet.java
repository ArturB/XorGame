/* This software is licensed under XorGame 1.0 license. License does not expire.
 * Can be used for creating unlimited applications.
 * Can be distributed in binary or object form only.
 * Non-commercial use only.
 * Cannot modify source-code for any purpose (cannot create derivative works).
 */
package xorgame.client;

import java.awt.event.ItemEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.sql.SQLException;
import xorgame.*;
import java.awt.event.*;
import java.awt.*;
import static xorgame.client.Shared.*;

/**
 * Panel that allows user to send a fleet. 
 * @author Artur
 */
public class Fleet extends javax.swing.JPanel {

    Main main;
    
    /**
     * Creates new form Fleet
     */
    public Fleet() {
        initComponents();
    }
    
    /**
     * Sets reference to main window of the client. Used to get session data and controler.
     * @param main_ Referense to main window of the client. 
     */
    public void setMainParent(Main main_) {
        main = main_;
    }
    
    public void setContent() {
        ships1TextField.setText("0");
        ships2TextField.setText("0");
        ships3TextField.setText("0");
        ships4TextField.setText("0");
        ships5TextField.setText("0");
        ships6TextField.setText("0");
        ships7TextField.setText("0");
        ships8TextField.setText("0");
        ships9TextField.setText("0");
        ships10TextField.setText("0");
        ships11TextField.setText("0");
        ships12TextField.setText("0");
        ships13TextField.setText("0");
        
        metalTextField.setText("0");
        cristalTextField.setText("0"); 
        deuterTextField.setText("0");
        antimatterTextField.setText("0"); 
    }
    
    /**
     * Sets start coordinates text fields to coordinates of currently selected user planet. 
     */
    public void setStartCoordinates() {
        startXTextField.setText(Short.toString(main.getSelectedPlanet().getPlanetPK().getX()));
        startYTextField.setText(Short.toString(main.getSelectedPlanet().getPlanetPK().getY()));
        startPosTextField.setText(Short.toString(main.getSelectedPlanet().getPlanetPK().getPosition()));
    }
    
    /**
     * Sets destination coordinates text fields to specified values. 
     * @param coordinates Coordinates to set. 
     */
    public void setDestCoordinates(PlanetPK coordinates) {
        destXTextField.setText(Short.toString(coordinates.getX()));
        destYTextField.setText(Short.toString(coordinates.getY()));
        destPosTextField.setText(Short.toString(coordinates.getPosition()));
    }
    
    public void calculate() {
        if(startXTextField.getText().isEmpty())
            JOptionPane.showMessageDialog(this, "Start X coordinate not filled!", "Error!", JOptionPane.ERROR_MESSAGE); 
        else if(startYTextField.getText().isEmpty())
            JOptionPane.showMessageDialog(this, "Start Y coordinate not filled!", "Error!", JOptionPane.ERROR_MESSAGE); 
        else if(startPosTextField.getText().isEmpty())
            JOptionPane.showMessageDialog(this, "Start positon not filled!", "Error!", JOptionPane.ERROR_MESSAGE); 
        else if(destXTextField.getText().isEmpty())
            JOptionPane.showMessageDialog(this, "Destination X coordinate not filled!", "Error!", JOptionPane.ERROR_MESSAGE); 
        else if(destYTextField.getText().isEmpty())
            JOptionPane.showMessageDialog(this, "Destination Y coordinate not filled!", "Error!", JOptionPane.ERROR_MESSAGE); 
        else if(destPosTextField.getText().isEmpty())
            JOptionPane.showMessageDialog(this, "Destination position not filled!", "Error!", JOptionPane.ERROR_MESSAGE); 
        else {
            if(parseIntSafe(ships1TextField.getText()) > main.getSelectedPlanet().getTransporter())
                ships1TextField.setText(Integer.toString(main.getSelectedPlanet().getTransporter())); 
            
            if(parseIntSafe(ships2TextField.getText()) > main.getSelectedPlanet().getFreighter())
                ships2TextField.setText(Integer.toString(main.getSelectedPlanet().getFreighter())); 
            
            if(parseIntSafe(ships3TextField.getText()) > main.getSelectedPlanet().getRecycler())
                ships3TextField.setText(Integer.toString(main.getSelectedPlanet().getRecycler()));
            
            if(parseIntSafe(ships4TextField.getText()) > main.getSelectedPlanet().getColonizer())
                ships4TextField.setText(Integer.toString(main.getSelectedPlanet().getColonizer())); 
            
            if(parseIntSafe(ships5TextField.getText()) > main.getSelectedPlanet().getSpySond())
                ships5TextField.setText(Integer.toString(main.getSelectedPlanet().getSpySond())); 
            
            if(parseIntSafe(ships6TextField.getText()) > main.getSelectedPlanet().getFighter())
                ships6TextField.setText(Integer.toString(main.getSelectedPlanet().getFighter())); 
            
            if(parseIntSafe(ships7TextField.getText()) > main.getSelectedPlanet().getIonFighter())
                ships7TextField.setText(Integer.toString(main.getSelectedPlanet().getIonFighter())); 
            
            if(parseIntSafe(ships8TextField.getText()) > main.getSelectedPlanet().getFrigate())
                ships8TextField.setText(Integer.toString(main.getSelectedPlanet().getFrigate())); 
            
            if(parseIntSafe(ships9TextField.getText()) > main.getSelectedPlanet().getBomber())
                ships9TextField.setText(Integer.toString(main.getSelectedPlanet().getBomber())); 
            
            if(parseIntSafe(ships10TextField.getText()) > main.getSelectedPlanet().getCruiser())
                ships10TextField.setText(Integer.toString(main.getSelectedPlanet().getCruiser())); 
            
            if(parseIntSafe(ships11TextField.getText()) > main.getSelectedPlanet().getCruiser())
                ships11TextField.setText(Integer.toString(main.getSelectedPlanet().getCruiser())); 
            
            if(parseIntSafe(ships12TextField.getText()) > main.getSelectedPlanet().getDestroyer())
                ships12TextField.setText(Integer.toString(main.getSelectedPlanet().getDestroyer())); 
            
            if(parseIntSafe(ships13TextField.getText()) > main.getSelectedPlanet().getDeathStar())
                ships13TextField.setText(Integer.toString(main.getSelectedPlanet().getDeathStar())); 
            
            
            int Transporter = parseIntSafe(ships1TextField.getText()); 
            int Freighter   = parseIntSafe(ships2TextField.getText()); 
            int Recycler    = parseIntSafe(ships3TextField.getText()); 
            int Colonizer   = parseIntSafe(ships4TextField.getText()); 
            int SpySond     = parseIntSafe(ships5TextField.getText()); 
            int Fighter     = parseIntSafe(ships6TextField.getText()); 
            int IonFighter  = parseIntSafe(ships7TextField.getText()); 
            int Frigate     = parseIntSafe(ships8TextField.getText()); 
            int Bomber      = parseIntSafe(ships9TextField.getText()); 
            int Cruiser     = parseIntSafe(ships10TextField.getText()); 
            int Drednot     = parseIntSafe(ships11TextField.getText()); 
            int Destroyer   = parseIntSafe(ships12TextField.getText()); 
            int DeathStar   = parseIntSafe(ships13TextField.getText()); 
            
            xorgame.Fleet fleet = new xorgame.Fleet(
                    Transporter, 
                    Freighter, 
                    Recycler, 
                    Colonizer, 
                    SpySond, 
                    Fighter, 
                    IonFighter, 
                    Frigate, 
                    Bomber, 
                    Cruiser, 
                    Drednot, 
                    Destroyer, 
                    DeathStar );
            PlanetPK startPK = new PlanetPK(
                                            Short.valueOf(startXTextField.getText()), 
                                            Short.valueOf(startYTextField.getText()), 
                                            Short.valueOf(startPosTextField.getText()));
            PlanetPK destPK = new PlanetPK(
                                            Short.valueOf(destXTextField.getText()), 
                                            Short.valueOf(destYTextField.getText()), 
                                            Short.valueOf(destPosTextField.getText()));
            try {
                if(main.getMainControler().flightCapacity(fleet) == 0)
                    JOptionPane.showMessageDialog(this, "You have not chosen any ship!", "Ships not chosen!", JOptionPane.ERROR_MESSAGE);
                else {
                    distanceLabel.setText(Double.toString(Math.round(main.getMainControler().planetDistance(startPK, destPK))));
                    velocityLabel.setText(Integer.toString(main.getMainControler().flightSpeed(startPK, fleet, 1.0)));
                    timeLabel.setText(main.getMainControler().flightTime(startPK, destPK, fleet, 1.0));
                    fuelLabel.setText(Integer.toString(main.getMainControler().flightFuel(startPK, destPK, fleet, 1.0))); 
                
                    int metal      = parseIntSafe(metalTextField.getText());
                    int cristal    = parseIntSafe(cristalTextField.getText());
                    int deuter     = parseIntSafe(deuterTextField.getText());
                    int antimatter = parseIntSafe(antimatterTextField.getText());
                
                    capacityLabel.setText(Integer.toString(main.getMainControler().flightCapacity(fleet) - metal - cristal - deuter - antimatter));
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error while conecting to game server!", "Error", JOptionPane.ERROR_MESSAGE); 
                Logger.getLogger(Fleet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SpeedRateOutOfRange ex) {
                JOptionPane.showMessageDialog(this, "Speed rate out of range!", "Error", JOptionPane.ERROR_MESSAGE); 
                Logger.getLogger(Fleet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup1 = new javax.swing.ButtonGroup();
        CoordinatesPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        startXTextField = new javax.swing.JTextField();
        startYTextField = new javax.swing.JTextField();
        startPosTextField = new javax.swing.JTextField();
        startComboBox = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        destXTextField = new javax.swing.JTextField();
        destYTextField = new javax.swing.JTextField();
        destPosTextField = new javax.swing.JTextField();
        destComboBox = new javax.swing.JComboBox<>();
        InfoPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        distanceLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        velocityLabel = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        timeLabel = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        fuelLabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        calculateButton = new javax.swing.JButton();
        ShipsPanel = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        ships1TextField = new javax.swing.JTextField();
        maxTransporterButton = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        ships2TextField = new javax.swing.JTextField();
        maxFreighterButton = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        ships3TextField = new javax.swing.JTextField();
        maxRecyclerButton = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        ships4TextField = new javax.swing.JTextField();
        maxColonizerButton = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        ships5TextField = new javax.swing.JTextField();
        maxSpySondButton = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        ships6TextField = new javax.swing.JTextField();
        maxFighterButton = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        ships7TextField = new javax.swing.JTextField();
        maxIonFighterButton = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        ships8TextField = new javax.swing.JTextField();
        maxFrigateButton = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        ships9TextField = new javax.swing.JTextField();
        maxBomberButton = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        ships10TextField = new javax.swing.JTextField();
        maxCruiserButton = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        ships11TextField = new javax.swing.JTextField();
        maxDrednotButton = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        ships12TextField = new javax.swing.JTextField();
        maxDestroyerButton = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        ships13TextField = new javax.swing.JTextField();
        maxDeathStarButton = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        maxAllShipsButton = new javax.swing.JButton();
        MaterialsPanel = new javax.swing.JPanel();
        missionPanel = new javax.swing.JPanel();
        missionComboBox = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        materialsPanel = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        metalTextField = new javax.swing.JTextField();
        maxMetalButton = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        cristalTextField = new javax.swing.JTextField();
        maxCristalButton = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        deuterTextField = new javax.swing.JTextField();
        maxDeuterButton = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        antimatterTextField = new javax.swing.JTextField();
        maxAntimatterButton = new javax.swing.JButton();
        h = new javax.swing.JLabel();
        capacityLabel = new javax.swing.JLabel();
        maxAllMaterialsButton = new javax.swing.JButton();
        sendPanel = new javax.swing.JPanel();
        sendButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 0, 25));
        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        layout.columnWidths = new int[] {0, 10, 0};
        layout.rowHeights = new int[] {0, 10, 0};
        setLayout(layout);

        CoordinatesPanel.setBackground(new java.awt.Color(0, 0, 25));
        CoordinatesPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        CoordinatesPanel.setLayout(new java.awt.GridLayout(2, 5, 10, 15));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Start from:");
        CoordinatesPanel.add(jLabel1);
        CoordinatesPanel.add(startXTextField);
        CoordinatesPanel.add(startYTextField);
        CoordinatesPanel.add(startPosTextField);

        startComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Planet", "Moon" }));
        CoordinatesPanel.add(startComboBox);

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Destination:");
        CoordinatesPanel.add(jLabel2);
        CoordinatesPanel.add(destXTextField);
        CoordinatesPanel.add(destYTextField);
        CoordinatesPanel.add(destPosTextField);

        destComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Planet", "Moon", "Debris" }));
        CoordinatesPanel.add(destComboBox);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.4;
        add(CoordinatesPanel, gridBagConstraints);

        InfoPanel.setBackground(new java.awt.Color(0, 0, 25));
        InfoPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        InfoPanel.setLayout(new java.awt.GridLayout(5, 2, 20, 5));

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Distance:");
        InfoPanel.add(jLabel3);

        distanceLabel.setForeground(new java.awt.Color(255, 255, 255));
        distanceLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        distanceLabel.setText("...");
        InfoPanel.add(distanceLabel);

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Velocity:");
        InfoPanel.add(jLabel5);

        velocityLabel.setForeground(new java.awt.Color(255, 255, 255));
        velocityLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        velocityLabel.setText("...");
        InfoPanel.add(velocityLabel);

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Flight time:");
        InfoPanel.add(jLabel7);

        timeLabel.setForeground(new java.awt.Color(255, 255, 255));
        timeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        timeLabel.setText("...");
        InfoPanel.add(timeLabel);

        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Fuel:");
        InfoPanel.add(jLabel22);

        fuelLabel.setForeground(new java.awt.Color(255, 255, 255));
        fuelLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fuelLabel.setText("...");
        InfoPanel.add(fuelLabel);
        InfoPanel.add(jLabel4);

        calculateButton.setBackground(new java.awt.Color(0, 153, 0));
        calculateButton.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        calculateButton.setForeground(new java.awt.Color(255, 255, 255));
        calculateButton.setText("Calculate!");
        calculateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calculateButtonActionPerformed(evt);
            }
        });
        InfoPanel.add(calculateButton);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.4;
        add(InfoPanel, gridBagConstraints);

        ShipsPanel.setBackground(new java.awt.Color(0, 0, 25));
        ShipsPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        ShipsPanel.setLayout(new java.awt.GridLayout(14, 3, 10, 5));

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Transporter:");
        ShipsPanel.add(jLabel9);

        ships1TextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        ships1TextField.setText("0");
        ShipsPanel.add(ships1TextField);

        maxTransporterButton.setBackground(new java.awt.Color(204, 204, 204));
        maxTransporterButton.setText("Max");
        maxTransporterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maxTransporterButtonActionPerformed(evt);
            }
        });
        ShipsPanel.add(maxTransporterButton);

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Freighter:");
        ShipsPanel.add(jLabel10);

        ships2TextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        ships2TextField.setText("0");
        ShipsPanel.add(ships2TextField);

        maxFreighterButton.setText("Max");
        maxFreighterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maxFreighterButtonActionPerformed(evt);
            }
        });
        ShipsPanel.add(maxFreighterButton);

        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Recycler:");
        ShipsPanel.add(jLabel11);

        ships3TextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        ships3TextField.setText("0");
        ShipsPanel.add(ships3TextField);

        maxRecyclerButton.setText("Max");
        maxRecyclerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maxRecyclerButtonActionPerformed(evt);
            }
        });
        ShipsPanel.add(maxRecyclerButton);

        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Colonizer:");
        ShipsPanel.add(jLabel12);

        ships4TextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        ships4TextField.setText("0");
        ShipsPanel.add(ships4TextField);

        maxColonizerButton.setText("Max");
        maxColonizerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maxColonizerButtonActionPerformed(evt);
            }
        });
        ShipsPanel.add(maxColonizerButton);

        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Spy sond:");
        ShipsPanel.add(jLabel13);

        ships5TextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        ships5TextField.setText("0");
        ShipsPanel.add(ships5TextField);

        maxSpySondButton.setText("Max");
        maxSpySondButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maxSpySondButtonActionPerformed(evt);
            }
        });
        ShipsPanel.add(maxSpySondButton);

        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Fighter:");
        ShipsPanel.add(jLabel14);

        ships6TextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        ships6TextField.setText("0");
        ShipsPanel.add(ships6TextField);

        maxFighterButton.setText("Max");
        maxFighterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maxFighterButtonActionPerformed(evt);
            }
        });
        ShipsPanel.add(maxFighterButton);

        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Ion fighter:");
        ShipsPanel.add(jLabel15);

        ships7TextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        ships7TextField.setText("0");
        ShipsPanel.add(ships7TextField);

        maxIonFighterButton.setText("Max");
        maxIonFighterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maxIonFighterButtonActionPerformed(evt);
            }
        });
        ShipsPanel.add(maxIonFighterButton);

        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Frigate:");
        ShipsPanel.add(jLabel17);

        ships8TextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        ships8TextField.setText("0");
        ShipsPanel.add(ships8TextField);

        maxFrigateButton.setText("Max");
        maxFrigateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maxFrigateButtonActionPerformed(evt);
            }
        });
        ShipsPanel.add(maxFrigateButton);

        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Bomber:");
        ShipsPanel.add(jLabel19);

        ships9TextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        ships9TextField.setText("0");
        ShipsPanel.add(ships9TextField);

        maxBomberButton.setText("Max");
        maxBomberButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maxBomberButtonActionPerformed(evt);
            }
        });
        ShipsPanel.add(maxBomberButton);

        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Cruiser:");
        ShipsPanel.add(jLabel18);

        ships10TextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        ships10TextField.setText("0");
        ShipsPanel.add(ships10TextField);

        maxCruiserButton.setText("Max");
        maxCruiserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maxCruiserButtonActionPerformed(evt);
            }
        });
        ShipsPanel.add(maxCruiserButton);

        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Dreadnought:");
        ShipsPanel.add(jLabel16);

        ships11TextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        ships11TextField.setText("0");
        ShipsPanel.add(ships11TextField);

        maxDrednotButton.setText("Max");
        maxDrednotButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maxDrednotButtonActionPerformed(evt);
            }
        });
        ShipsPanel.add(maxDrednotButton);

        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Destroyer:");
        ShipsPanel.add(jLabel21);

        ships12TextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        ships12TextField.setText("0");
        ShipsPanel.add(ships12TextField);

        maxDestroyerButton.setText("Max");
        maxDestroyerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maxDestroyerButtonActionPerformed(evt);
            }
        });
        ShipsPanel.add(maxDestroyerButton);

        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Death Star:");
        ShipsPanel.add(jLabel20);

        ships13TextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        ships13TextField.setText("0");
        ShipsPanel.add(ships13TextField);

        maxDeathStarButton.setText("Max");
        maxDeathStarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maxDeathStarButtonActionPerformed(evt);
            }
        });
        ShipsPanel.add(maxDeathStarButton);
        ShipsPanel.add(jLabel8);
        ShipsPanel.add(jLabel27);

        maxAllShipsButton.setText("Max all!");
        maxAllShipsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maxAllShipsButtonActionPerformed(evt);
            }
        });
        ShipsPanel.add(maxAllShipsButton);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.6;
        add(ShipsPanel, gridBagConstraints);

        MaterialsPanel.setBackground(new java.awt.Color(0, 0, 25));
        MaterialsPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        MaterialsPanel.setLayout(new java.awt.GridLayout(3, 0, 5, 5));

        missionPanel.setBackground(new java.awt.Color(0, 0, 25));
        java.awt.GridBagLayout missionPanelLayout = new java.awt.GridBagLayout();
        missionPanelLayout.columnWidths = new int[] {0};
        missionPanelLayout.rowHeights = new int[] {0, 20, 0};
        missionPanel.setLayout(missionPanelLayout);

        missionComboBox.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        missionComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Transport", "Station", "Recycle", "Colonize", "Spy", "Attack", "Destroy" }));
        missionComboBox.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        missionComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                missionComboBoxItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        missionPanel.add(missionComboBox, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Mission:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        missionPanel.add(jLabel6, gridBagConstraints);

        MaterialsPanel.add(missionPanel);

        materialsPanel.setBackground(new java.awt.Color(0, 0, 25));
        materialsPanel.setLayout(new java.awt.GridLayout(5, 3, 10, 10));

        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Metal:");
        materialsPanel.add(jLabel23);

        metalTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        metalTextField.setText("0");
        metalTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                metalTextFieldKeyTyped(evt);
            }
        });
        materialsPanel.add(metalTextField);

        maxMetalButton.setBackground(new java.awt.Color(204, 204, 204));
        maxMetalButton.setText("Max");
        maxMetalButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maxMetalButtonActionPerformed(evt);
            }
        });
        materialsPanel.add(maxMetalButton);

        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Cristal:");
        materialsPanel.add(jLabel24);

        cristalTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        cristalTextField.setText("0");
        cristalTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cristalTextFieldKeyTyped(evt);
            }
        });
        materialsPanel.add(cristalTextField);

        maxCristalButton.setText("Max");
        maxCristalButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maxCristalButtonActionPerformed(evt);
            }
        });
        materialsPanel.add(maxCristalButton);

        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Deuter:");
        materialsPanel.add(jLabel25);

        deuterTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        deuterTextField.setText("0");
        deuterTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                deuterTextFieldKeyTyped(evt);
            }
        });
        materialsPanel.add(deuterTextField);

        maxDeuterButton.setText("Max");
        maxDeuterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maxDeuterButtonActionPerformed(evt);
            }
        });
        materialsPanel.add(maxDeuterButton);

        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Antimatter:");
        materialsPanel.add(jLabel26);

        antimatterTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        antimatterTextField.setText("0");
        antimatterTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                antimatterTextFieldKeyTyped(evt);
            }
        });
        materialsPanel.add(antimatterTextField);

        maxAntimatterButton.setText("Max");
        maxAntimatterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maxAntimatterButtonActionPerformed(evt);
            }
        });
        materialsPanel.add(maxAntimatterButton);

        h.setForeground(new java.awt.Color(255, 255, 255));
        h.setText("Free capacity:");
        materialsPanel.add(h);

        capacityLabel.setForeground(new java.awt.Color(255, 255, 255));
        capacityLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        capacityLabel.setText("...");
        materialsPanel.add(capacityLabel);

        maxAllMaterialsButton.setText("Max all");
        maxAllMaterialsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maxAllMaterialsButtonActionPerformed(evt);
            }
        });
        materialsPanel.add(maxAllMaterialsButton);

        MaterialsPanel.add(materialsPanel);

        sendPanel.setBackground(new java.awt.Color(0, 0, 25));
        sendPanel.setLayout(new java.awt.GridBagLayout());

        sendButton.setBackground(new java.awt.Color(0, 153, 0));
        sendButton.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        sendButton.setForeground(new java.awt.Color(255, 255, 255));
        sendButton.setText("SEND FLEET");
        sendButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });
        sendPanel.add(sendButton, new java.awt.GridBagConstraints());

        MaterialsPanel.add(sendPanel);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.6;
        add(MaterialsPanel, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void maxTransporterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maxTransporterButtonActionPerformed
        ships1TextField.setText(Integer.toString(main.getSelectedPlanet().getTransporter())); 
    }//GEN-LAST:event_maxTransporterButtonActionPerformed

    private void maxMetalButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maxMetalButtonActionPerformed
        calculateButtonActionPerformed(evt);
        int metal = Math.round(main.getSelectedPlanet().getMetal());
        int freeCapacity = parseIntSafe(capacityLabel.getText());
        metalTextField.setText(Integer.toString(Math.max(metal,freeCapacity))); 
        calculateButtonActionPerformed(evt);
    }//GEN-LAST:event_maxMetalButtonActionPerformed

    private void maxFreighterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maxFreighterButtonActionPerformed
        ships2TextField.setText(Integer.toString(main.getSelectedPlanet().getFreighter())); 
    }//GEN-LAST:event_maxFreighterButtonActionPerformed

    private void maxRecyclerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maxRecyclerButtonActionPerformed
        ships3TextField.setText(Integer.toString(main.getSelectedPlanet().getRecycler())); 
    }//GEN-LAST:event_maxRecyclerButtonActionPerformed

    private void maxColonizerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maxColonizerButtonActionPerformed
        ships4TextField.setText(Integer.toString(main.getSelectedPlanet().getColonizer())); 
    }//GEN-LAST:event_maxColonizerButtonActionPerformed

    private void maxSpySondButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maxSpySondButtonActionPerformed
        ships5TextField.setText(Integer.toString(main.getSelectedPlanet().getSpySond())); 
    }//GEN-LAST:event_maxSpySondButtonActionPerformed

    private void maxFighterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maxFighterButtonActionPerformed
        ships6TextField.setText(Integer.toString(main.getSelectedPlanet().getFighter())); 
    }//GEN-LAST:event_maxFighterButtonActionPerformed

    private void maxIonFighterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maxIonFighterButtonActionPerformed
        ships7TextField.setText(Integer.toString(main.getSelectedPlanet().getIonFighter())); 
    }//GEN-LAST:event_maxIonFighterButtonActionPerformed

    private void maxFrigateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maxFrigateButtonActionPerformed
        ships8TextField.setText(Integer.toString(main.getSelectedPlanet().getFrigate())); 
    }//GEN-LAST:event_maxFrigateButtonActionPerformed

    private void maxBomberButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maxBomberButtonActionPerformed
        ships9TextField.setText(Integer.toString(main.getSelectedPlanet().getBomber())); 
    }//GEN-LAST:event_maxBomberButtonActionPerformed

    private void maxCruiserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maxCruiserButtonActionPerformed
        ships10TextField.setText(Integer.toString(main.getSelectedPlanet().getCruiser())); 
    }//GEN-LAST:event_maxCruiserButtonActionPerformed

    private void maxDrednotButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maxDrednotButtonActionPerformed
        ships11TextField.setText(Integer.toString(main.getSelectedPlanet().getDrednot())); 
    }//GEN-LAST:event_maxDrednotButtonActionPerformed

    private void maxDestroyerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maxDestroyerButtonActionPerformed
        ships12TextField.setText(Integer.toString(main.getSelectedPlanet().getDestroyer())); 
    }//GEN-LAST:event_maxDestroyerButtonActionPerformed

    private void maxDeathStarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maxDeathStarButtonActionPerformed
        ships13TextField.setText(Integer.toString(main.getSelectedPlanet().getDeathStar())); 
    }//GEN-LAST:event_maxDeathStarButtonActionPerformed

    private void maxAllShipsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maxAllShipsButtonActionPerformed
        ships1TextField.setText(Integer.toString(main.getSelectedPlanet().getTransporter())); 
        ships2TextField.setText(Integer.toString(main.getSelectedPlanet().getFreighter())); 
        ships3TextField.setText(Integer.toString(main.getSelectedPlanet().getRecycler())); 
        ships4TextField.setText(Integer.toString(main.getSelectedPlanet().getColonizer())); 
        ships5TextField.setText(Integer.toString(main.getSelectedPlanet().getSpySond())); 
        ships6TextField.setText(Integer.toString(main.getSelectedPlanet().getFighter())); 
        ships7TextField.setText(Integer.toString(main.getSelectedPlanet().getIonFighter())); 
        ships8TextField.setText(Integer.toString(main.getSelectedPlanet().getFrigate())); 
        ships9TextField.setText(Integer.toString(main.getSelectedPlanet().getBomber())); 
        ships10TextField.setText(Integer.toString(main.getSelectedPlanet().getCruiser())); 
        ships11TextField.setText(Integer.toString(main.getSelectedPlanet().getDrednot())); 
        ships12TextField.setText(Integer.toString(main.getSelectedPlanet().getDestroyer())); 
        ships13TextField.setText(Integer.toString(main.getSelectedPlanet().getDeathStar())); 
    }//GEN-LAST:event_maxAllShipsButtonActionPerformed

    private void calculateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calculateButtonActionPerformed
        calculate();
    }//GEN-LAST:event_calculateButtonActionPerformed

    private void missionComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_missionComboBoxItemStateChanged
        if(evt.getStateChange() == ItemEvent.SELECTED)  {
            calculate();
            int mission = ((JComboBox)evt.getSource()).getSelectedIndex();
            //recycle
            if(mission == 2) {
                if(destComboBox.getSelectedIndex() != 2 || parseIntSafe(ships3TextField.getText()) == 0) {
                    JOptionPane.showMessageDialog(this, "Mission recycle requires at least one recycler and must be send to debris!", "Error", JOptionPane.ERROR_MESSAGE);
                    ((JComboBox)evt.getSource()).setSelectedIndex(0); 
                }
            }
            //colonize
            if(mission == 3) {
                int destX   = parseIntSafe(destXTextField.getText());
                int destY   = parseIntSafe(destXTextField.getText());
                int destPos = parseIntSafe(destXTextField.getText());
                
                if(destComboBox.getSelectedIndex() != 0 || parseIntSafe(ships4TextField.getText()) == 0) {
                    JOptionPane.showMessageDialog(this, "Mission colonize requires at least one colonizer and must be send to planet!", "Error", JOptionPane.ERROR_MESSAGE);
                    ((JComboBox)evt.getSource()).setSelectedIndex(0); 
                }
            }
            //spy
            if(mission == 4) {
                if(parseIntSafe(ships5TextField.getText()) == 0) {
                    JOptionPane.showMessageDialog(this, "Mission spy requires at least one spy sond!", "Error", JOptionPane.ERROR_MESSAGE);
                    ((JComboBox)evt.getSource()).setSelectedIndex(0); 
                }
            }
            //destroy
            if(mission == 6) {
                if(destComboBox.getSelectedIndex() == 2 || parseIntSafe(ships13TextField.getText()) == 0) {
                    JOptionPane.showMessageDialog(this, "Mission destroy requires at least one Death Star and cannot be sent to debris!", "Error", JOptionPane.ERROR_MESSAGE);
                    ((JComboBox)evt.getSource()).setSelectedIndex(0); 
                }
            }
        }
    }//GEN-LAST:event_missionComboBoxItemStateChanged

    private void maxCristalButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maxCristalButtonActionPerformed
        calculateButtonActionPerformed(evt);
        int cristal = Math.round(main.getSelectedPlanet().getCristal());
        int freeCapacity = parseIntSafe(capacityLabel.getText());
        cristalTextField.setText(Integer.toString(Math.max(cristal,freeCapacity))); 
        calculateButtonActionPerformed(evt);
    }//GEN-LAST:event_maxCristalButtonActionPerformed

    private void maxDeuterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maxDeuterButtonActionPerformed
        calculateButtonActionPerformed(evt);
        int deuter = Math.round(main.getSelectedPlanet().getDeuter());
        int freeCapacity = parseIntSafe(capacityLabel.getText());
        deuterTextField.setText(Integer.toString(Math.max(deuter,freeCapacity))); 
        calculateButtonActionPerformed(evt);
    }//GEN-LAST:event_maxDeuterButtonActionPerformed

    private void maxAntimatterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maxAntimatterButtonActionPerformed
        calculateButtonActionPerformed(evt);
        int antimatter = Math.round(main.getSelectedPlanet().getAntimatter());
        int freeCapacity = parseIntSafe(capacityLabel.getText());
        antimatterTextField.setText(Integer.toString(Math.max(antimatter,freeCapacity))); 
        calculateButtonActionPerformed(evt);
    }//GEN-LAST:event_maxAntimatterButtonActionPerformed

    private void maxAllMaterialsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maxAllMaterialsButtonActionPerformed
        calculateButtonActionPerformed(evt);
        
        Float metal      = main.getSelectedPlanet().getMetal();
        Float cristal    = main.getSelectedPlanet().getMetal();
        Float deuter     = main.getSelectedPlanet().getMetal();
        Float antimatter = main.getSelectedPlanet().getMetal();
        
        int Transporter = parseIntSafe(ships1TextField.getText()); 
        int Freighter   = parseIntSafe(ships2TextField.getText()); 
        int Recycler    = parseIntSafe(ships3TextField.getText()); 
        int Colonizer   = parseIntSafe(ships4TextField.getText()); 
        int SpySond     = parseIntSafe(ships5TextField.getText()); 
        int Fighter     = parseIntSafe(ships6TextField.getText()); 
        int IonFighter  = parseIntSafe(ships7TextField.getText()); 
        int Frigate     = parseIntSafe(ships8TextField.getText()); 
        int Bomber      = parseIntSafe(ships9TextField.getText()); 
        int Cruiser     = parseIntSafe(ships10TextField.getText()); 
        int Drednot     = parseIntSafe(ships11TextField.getText()); 
        int Destroyer   = parseIntSafe(ships12TextField.getText()); 
        int DeathStar   = parseIntSafe(ships13TextField.getText()); 
            
            xorgame.Fleet fleet = new xorgame.Fleet(
                    Transporter, 
                    Freighter, 
                    Recycler, 
                    Colonizer, 
                    SpySond, 
                    Fighter, 
                    IonFighter, 
                    Frigate, 
                    Bomber, 
                    Cruiser, 
                    Drednot, 
                    Destroyer, 
                    DeathStar );
        
        try {
            if(metal + cristal + deuter + antimatter <= main.getMainControler().flightCapacity(fleet)) {
                metalTextField.setText(Integer.toString(Math.round(metal))); 
                cristalTextField.setText(Integer.toString(Math.round(cristal))); 
                deuterTextField.setText(Integer.toString(Math.round(deuter))); 
                antimatterTextField.setText(Integer.toString(Math.round(antimatter))); 
            }
            else {
                float ratio = main.getMainControler().flightCapacity(fleet) / (metal + cristal + deuter + antimatter);
                metalTextField.setText(Integer.toString(Math.round(metal * ratio))); 
                cristalTextField.setText(Integer.toString(Math.round(cristal * ratio))); 
                deuterTextField.setText(Integer.toString(Math.round(deuter * ratio))); 
                antimatterTextField.setText(Integer.toString(Math.round(antimatter * ratio))); 
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error while connecting to the database!", "Error", JOptionPane.ERROR_MESSAGE); 
            Logger.getLogger(Fleet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_maxAllMaterialsButtonActionPerformed

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed
        if(startXTextField.getText().isEmpty())
            JOptionPane.showMessageDialog(this, "Start X coordinate not filled!", "Error!", JOptionPane.ERROR_MESSAGE); 
        else if(startYTextField.getText().isEmpty())
            JOptionPane.showMessageDialog(this, "Start Y coordinate not filled!", "Error!", JOptionPane.ERROR_MESSAGE); 
        else if(startPosTextField.getText().isEmpty())
            JOptionPane.showMessageDialog(this, "Start positon not filled!", "Error!", JOptionPane.ERROR_MESSAGE); 
        else if(destXTextField.getText().isEmpty())
            JOptionPane.showMessageDialog(this, "Destination X coordinate not filled!", "Error!", JOptionPane.ERROR_MESSAGE); 
        else if(destYTextField.getText().isEmpty())
            JOptionPane.showMessageDialog(this, "Destination Y coordinate not filled!", "Error!", JOptionPane.ERROR_MESSAGE); 
        else if(destPosTextField.getText().isEmpty())
            JOptionPane.showMessageDialog(this, "Destination position not filled!", "Error!", JOptionPane.ERROR_MESSAGE); 
        else {
            calculateButtonActionPerformed(evt);
            
            int metal      = parseIntSafe(metalTextField.getText()); 
            int cristal    = parseIntSafe(cristalTextField.getText()); 
            int deuter     = parseIntSafe(deuterTextField.getText()); 
            int antimatter = parseIntSafe(antimatterTextField.getText()); 
        
            int Transporter = parseIntSafe(ships1TextField.getText()); 
            int Freighter   = parseIntSafe(ships2TextField.getText()); 
            int Recycler    = parseIntSafe(ships3TextField.getText()); 
            int Colonizer   = parseIntSafe(ships4TextField.getText()); 
            int SpySond     = parseIntSafe(ships5TextField.getText()); 
            int Fighter     = parseIntSafe(ships6TextField.getText()); 
            int IonFighter  = parseIntSafe(ships7TextField.getText()); 
            int Frigate     = parseIntSafe(ships8TextField.getText()); 
            int Bomber      = parseIntSafe(ships9TextField.getText()); 
            int Cruiser     = parseIntSafe(ships10TextField.getText()); 
            int Drednot     = parseIntSafe(ships11TextField.getText()); 
            int Destroyer   = parseIntSafe(ships12TextField.getText()); 
            int DeathStar   = parseIntSafe(ships13TextField.getText()); 
            
            xorgame.Fleet fleet = new xorgame.Fleet(
                    Transporter, 
                    Freighter, 
                    Recycler, 
                    Colonizer, 
                    SpySond, 
                    Fighter, 
                    IonFighter, 
                    Frigate, 
                    Bomber, 
                    Cruiser, 
                    Drednot, 
                    Destroyer, 
                    DeathStar );
        
            PlanetPK startPK = new PlanetPK(
                                            Short.valueOf(startXTextField.getText()), 
                                            Short.valueOf(startYTextField.getText()), 
                                            Short.valueOf(startPosTextField.getText()));
            Location startLoc;
            if(startComboBox.getSelectedIndex() == 0)
                startLoc = Location.P;
            else
                startLoc = Location.M;
            PlanetPK destPK = new PlanetPK(
                                            Short.valueOf(destXTextField.getText()), 
                                            Short.valueOf(destYTextField.getText()), 
                                            Short.valueOf(destPosTextField.getText()));
            Location destLoc;
            switch (destComboBox.getSelectedIndex()) {
                case 0:
                    destLoc = Location.P;
                    break;
                case 1:
                    destLoc = Location.M;
                    break;
                default:
                    destLoc = Location.D;
                    break;
            }
            Mission mission;
            switch (missionComboBox.getSelectedIndex()) {
                case 0:
                    mission = Mission.T;
                    break;
                case 1:
                    mission = Mission.S;
                    break;
                case 2:
                    mission = Mission.R;
                    break;
                case 3:
                    mission = Mission.K;
                    break;
                case 4:
                    mission = Mission.SZ;
                    break;
                case 5:
                    mission = Mission.A;
                    break;
                default:
                    mission = Mission.N;
                    break;
            }
        
            try {
                System.out.println(main.getMainControler().sendFlightPossible(startPK, startLoc, mission, destPK, destLoc, fleet, 1.0D, metal, cristal, deuter, antimatter));
                if(main.getMainControler().flightCapacity(fleet) == 0)
                    JOptionPane.showMessageDialog(this, "You have not chosen ships!", "Cannot send!", JOptionPane.ERROR_MESSAGE); 
                else if(main.getMainControler().
                        sendFlightPossible(startPK, startLoc, mission, destPK, destLoc, fleet, 1.0D, metal, cristal, deuter, antimatter)) {
                    
                    main.getMainControler().sendFlight(startPK, startLoc, mission, destPK, destLoc, fleet, 1.0D, metal, cristal, deuter, antimatter);
                    JOptionPane.showMessageDialog(this, "Fleet was successfully sent!"); 
                    
                    main.getPreview().updatePreview();
                    CardLayout layout = (CardLayout)main.getWorkPanel().getLayout();
                    layout.show(main.getWorkPanel(), "PreviewCard"); 
                }
                else
                    JOptionPane.showMessageDialog(this, "You have not enough fuel to send the fleet!", "Cannot send", JOptionPane.ERROR_MESSAGE); 
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Destination planet does not exist!", "Error", JOptionPane.ERROR_MESSAGE); 
                Logger.getLogger(Fleet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SpeedRateOutOfRange ex) {
                JOptionPane.showMessageDialog(this, "Speed rate value out of range!", "Error", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(Fleet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_sendButtonActionPerformed

    private void metalTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_metalTextFieldKeyTyped
        calculateButtonActionPerformed(new ActionEvent(this, 0, ""));
    }//GEN-LAST:event_metalTextFieldKeyTyped

    private void cristalTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cristalTextFieldKeyTyped
        calculateButtonActionPerformed(new ActionEvent(this, 0, ""));
    }//GEN-LAST:event_cristalTextFieldKeyTyped

    private void deuterTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_deuterTextFieldKeyTyped
        calculateButtonActionPerformed(new ActionEvent(this, 0, ""));
    }//GEN-LAST:event_deuterTextFieldKeyTyped

    private void antimatterTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_antimatterTextFieldKeyTyped
        calculateButtonActionPerformed(new ActionEvent(this, 0, ""));
    }//GEN-LAST:event_antimatterTextFieldKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CoordinatesPanel;
    private javax.swing.JPanel InfoPanel;
    private javax.swing.JPanel MaterialsPanel;
    private javax.swing.JPanel ShipsPanel;
    private javax.swing.JTextField antimatterTextField;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton calculateButton;
    private javax.swing.JLabel capacityLabel;
    private javax.swing.JTextField cristalTextField;
    private javax.swing.JComboBox<String> destComboBox;
    private javax.swing.JTextField destPosTextField;
    private javax.swing.JTextField destXTextField;
    private javax.swing.JTextField destYTextField;
    private javax.swing.JTextField deuterTextField;
    private javax.swing.JLabel distanceLabel;
    private javax.swing.JLabel fuelLabel;
    private javax.swing.JLabel h;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel materialsPanel;
    private javax.swing.JButton maxAllMaterialsButton;
    private javax.swing.JButton maxAllShipsButton;
    private javax.swing.JButton maxAntimatterButton;
    private javax.swing.JButton maxBomberButton;
    private javax.swing.JButton maxColonizerButton;
    private javax.swing.JButton maxCristalButton;
    private javax.swing.JButton maxCruiserButton;
    private javax.swing.JButton maxDeathStarButton;
    private javax.swing.JButton maxDestroyerButton;
    private javax.swing.JButton maxDeuterButton;
    private javax.swing.JButton maxDrednotButton;
    private javax.swing.JButton maxFighterButton;
    private javax.swing.JButton maxFreighterButton;
    private javax.swing.JButton maxFrigateButton;
    private javax.swing.JButton maxIonFighterButton;
    private javax.swing.JButton maxMetalButton;
    private javax.swing.JButton maxRecyclerButton;
    private javax.swing.JButton maxSpySondButton;
    private javax.swing.JButton maxTransporterButton;
    private javax.swing.JTextField metalTextField;
    private javax.swing.JComboBox<String> missionComboBox;
    private javax.swing.JPanel missionPanel;
    private javax.swing.JButton sendButton;
    private javax.swing.JPanel sendPanel;
    private javax.swing.JTextField ships10TextField;
    private javax.swing.JTextField ships11TextField;
    private javax.swing.JTextField ships12TextField;
    private javax.swing.JTextField ships13TextField;
    private javax.swing.JTextField ships1TextField;
    private javax.swing.JTextField ships2TextField;
    private javax.swing.JTextField ships3TextField;
    private javax.swing.JTextField ships4TextField;
    private javax.swing.JTextField ships5TextField;
    private javax.swing.JTextField ships6TextField;
    private javax.swing.JTextField ships7TextField;
    private javax.swing.JTextField ships8TextField;
    private javax.swing.JTextField ships9TextField;
    private javax.swing.JComboBox<String> startComboBox;
    private javax.swing.JTextField startPosTextField;
    private javax.swing.JTextField startXTextField;
    private javax.swing.JTextField startYTextField;
    private javax.swing.JLabel timeLabel;
    private javax.swing.JLabel velocityLabel;
    // End of variables declaration//GEN-END:variables
}
