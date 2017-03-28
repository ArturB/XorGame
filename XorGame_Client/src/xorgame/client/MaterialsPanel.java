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
import static xorgame.client.Shared.*;
import java.awt.*;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.*;

/**
 * Panel with information about materials and energy on the planet. 
 * @author Artur
 */
public class MaterialsPanel extends javax.swing.JPanel {
    Main main;
    Thread autoUpdater;
    /**
     * Creates new form MaterialsPanel
     */
    public MaterialsPanel() {
        this.autoUpdater = new Thread( () -> {
            try {
                while(true) {
                    autoUpdater.sleep(AUTO_UPDATE_PERIOD);
                    updateMaterials();
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(MaterialsPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        initComponents();
    }
    
    /**
     * Sets reference to main window of the client. Used to get session data and controler.
     * @param main_ Referense to main window of the client. 
     */
    public void setMainParent(Main main_) {
        main = main_;
    }
    
    /**
     * Start thread that automatically updates amount of materials on the planet. 
     */
    public void startAutoUpdating() {
        autoUpdater.start();
    }
    
    /**
     * Downloads from the database current materials and energy amount. 
     */
    public void updateMaterials() {
        if(main.getSelectedPlanet() != null) {
        try {
            main.getMainControler().updatePlanet(main.getSelectedPlanet().getPlanetPK());
            main.updateUserSessionData(main.getUser().getNick());

            metalAmount.setText(
                    NumberFormat.getNumberInstance(Locale.US).format(Math.round(main.getSelectedPlanet().getMetal())) + " / " + 
                    NumberFormat.getNumberInstance(Locale.US).format(main.getMainControler().buildingProductivity(main.getSelectedPlanet().getPlanetPK(), Location.P, Building.MetalMag)));
            cristalAmount.setText(
                    NumberFormat.getNumberInstance(Locale.US).format(Math.round(main.getSelectedPlanet().getCristal())) + " / " + 
                    NumberFormat.getNumberInstance(Locale.US).format(main.getMainControler().buildingProductivity(main.getSelectedPlanet().getPlanetPK(), Location.P, Building.CristalMag)));
            deuterAmount.setText(
                    NumberFormat.getNumberInstance(Locale.US).format(Math.round(main.getSelectedPlanet().getDeuter())) + " / " + 
                    NumberFormat.getNumberInstance(Locale.US).format(main.getMainControler().buildingProductivity(main.getSelectedPlanet().getPlanetPK(), Location.P, Building.DeuterMag))); 
            antimatterAmount.setText(
                    NumberFormat.getNumberInstance(Locale.US).format(Math.round(main.getSelectedPlanet().getAntimatter())) + " / " + 
                    NumberFormat.getNumberInstance(Locale.US).format(main.getMainControler().buildingProductivity(main.getSelectedPlanet().getPlanetPK(), Location.P, Building.AntimatterMag)));
            
            energyAmount.setText(NumberFormat.getNumberInstance(Locale.US).format(main.getMainControler().availableEnergy(main.getSelectedPlanet().getPlanetPK(), Location.P))); 
            
            metalAmount.setToolTipText(
                    "Production: " + NumberFormat.getNumberInstance(Locale.US).format(
                            main.getMainControler().buildingProductivity(main.getSelectedPlanet().getPlanetPK(), Location.P, Building.MetalMine)));
            cristalAmount.setToolTipText(
                    "Production: " + NumberFormat.getNumberInstance(Locale.US).format(
                            main.getMainControler().buildingProductivity(main.getSelectedPlanet().getPlanetPK(), Location.P, Building.CristalMine)));
            deuterAmount.setToolTipText(
                    "Production: " + NumberFormat.getNumberInstance(Locale.US).format(
                            main.getMainControler().buildingProductivity(main.getSelectedPlanet().getPlanetPK(), Location.P, Building.DeuterExt)));
            antimatterAmount.setToolTipText(
                    "Production: " + NumberFormat.getNumberInstance(Locale.US).format(
                            main.getMainControler().buildingProductivity(main.getSelectedPlanet().getPlanetPK(), Location.P, Building.Accelerator)));
            
            metalIcon.setToolTipText(
                    "Production: " + NumberFormat.getNumberInstance(Locale.US).format(
                            main.getMainControler().buildingProductivity(main.getSelectedPlanet().getPlanetPK(), Location.P, Building.MetalMine)));
            cristalIcon.setToolTipText(
                    "Production: " + NumberFormat.getNumberInstance(Locale.US).format(
                            main.getMainControler().buildingProductivity(main.getSelectedPlanet().getPlanetPK(), Location.P, Building.CristalMine)));
            deuterIcon.setToolTipText(
                    "Production: " + NumberFormat.getNumberInstance(Locale.US).format(
                            main.getMainControler().buildingProductivity(main.getSelectedPlanet().getPlanetPK(), Location.P, Building.DeuterExt)));
            antimatterIcon.setToolTipText(
                    "Production: " + NumberFormat.getNumberInstance(Locale.US).format(
                            main.getMainControler().buildingProductivity(main.getSelectedPlanet().getPlanetPK(), Location.P, Building.Accelerator)));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Cannot connect to the game server!");
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }
    
    /**
     * Updates from the game database list of planets actually owned by the user and puts them all into proper combo box. 
     */
    public void updateComboBox() {
        Planet selectedPlanet = main.getSelectedPlanet();
        Set<Planet> userPlanetSet = main.getUser().getPlanetSet();
        
        planetsComboBox.removeAllItems();
        
        java.util.List<Planet> planets = new ArrayList();
        for(Planet pl : userPlanetSet) {
            planets.add(pl);
        }
       
        planets.sort((Planet pl1, Planet pl2) -> {
            if(pl1.getName().compareTo(pl2.getName()) > 0)
                return 1;
            else if(pl1.getName().compareTo(pl2.getName()) < 0)
                return -1;
            else
                return 0;
        });
        
        if(main.getSelectedPlanet() == null) {
            //System.out.println("selected planet is = " + main.getSelectedPlanet().toString());
            main.setSelectedPlanet(planets.get(0)); 
            selectedPlanet = main.getSelectedPlanet();
        } 
        
        for(Planet pl : planets) {
            planetsComboBox.addItem(pl.toString());
            if(pl.toString().equals(selectedPlanet.toString())) {
                planetsComboBox.setSelectedIndex(planets.indexOf(pl)); 
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

        MaterialsPanel = new javax.swing.JPanel();
        metalIcon = new javax.swing.JLabel();
        cristalIcon = new javax.swing.JLabel();
        deuterIcon = new javax.swing.JLabel();
        antimatterIcon = new javax.swing.JLabel();
        metalAmount = new javax.swing.JLabel();
        cristalAmount = new javax.swing.JLabel();
        deuterAmount = new javax.swing.JLabel();
        antimatterAmount = new javax.swing.JLabel();
        metalLabel = new javax.swing.JLabel();
        cristalLabel = new javax.swing.JLabel();
        deuterLabel = new javax.swing.JLabel();
        antimatterLabel = new javax.swing.JLabel();
        planetsComboBox = new javax.swing.JComboBox<>();
        logoLabel = new javax.swing.JLabel();
        logoutButton = new javax.swing.JButton();
        energyIcon = new javax.swing.JLabel();
        energyAmount = new javax.swing.JLabel();
        energyLabel = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        MaterialsPanel.setBackground(new java.awt.Color(0, 0, 25));
        MaterialsPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        MaterialsPanel.setLayout(new java.awt.GridBagLayout());

        metalIcon.setForeground(new java.awt.Color(255, 255, 255));
        metalIcon.setIcon(loadIcon("/xorgame/client/General_GUI/Metal.jpg", MATERIAL_SIZE));
        metalIcon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 0), 2));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.5;
        MaterialsPanel.add(metalIcon, gridBagConstraints);

        cristalIcon.setForeground(new java.awt.Color(255, 255, 255));
        cristalIcon.setIcon(loadIcon("/xorgame/client/General_GUI/Cristal.jpg", MATERIAL_SIZE));
        cristalIcon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 0), 2));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.5;
        MaterialsPanel.add(cristalIcon, gridBagConstraints);

        deuterIcon.setForeground(new java.awt.Color(255, 255, 255));
        deuterIcon.setIcon(loadIcon("/xorgame/client/General_GUI/Deuter.jpg", MATERIAL_SIZE));
        deuterIcon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 0), 2));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.5;
        MaterialsPanel.add(deuterIcon, gridBagConstraints);

        antimatterIcon.setForeground(new java.awt.Color(255, 255, 255));
        antimatterIcon.setIcon(loadIcon("/xorgame/client/General_GUI/Antimatter.jpg", MATERIAL_SIZE));
        antimatterIcon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 0), 2));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.5;
        MaterialsPanel.add(antimatterIcon, gridBagConstraints);

        metalAmount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        metalAmount.setForeground(new java.awt.Color(255, 255, 255));
        metalAmount.setText("jLabel25");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.25;
        MaterialsPanel.add(metalAmount, gridBagConstraints);

        cristalAmount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cristalAmount.setForeground(new java.awt.Color(255, 255, 255));
        cristalAmount.setText("jLabel26");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.25;
        MaterialsPanel.add(cristalAmount, gridBagConstraints);

        deuterAmount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        deuterAmount.setForeground(new java.awt.Color(255, 255, 255));
        deuterAmount.setText("jLabel27");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.25;
        MaterialsPanel.add(deuterAmount, gridBagConstraints);

        antimatterAmount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        antimatterAmount.setForeground(new java.awt.Color(255, 255, 255));
        antimatterAmount.setText("jLabel28");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.25;
        MaterialsPanel.add(antimatterAmount, gridBagConstraints);

        metalLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        metalLabel.setForeground(new java.awt.Color(255, 255, 255));
        metalLabel.setText("Metal");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.weighty = 0.25;
        MaterialsPanel.add(metalLabel, gridBagConstraints);

        cristalLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cristalLabel.setForeground(new java.awt.Color(255, 255, 255));
        cristalLabel.setText("Cristal");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.weighty = 0.25;
        MaterialsPanel.add(cristalLabel, gridBagConstraints);

        deuterLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        deuterLabel.setForeground(new java.awt.Color(255, 255, 255));
        deuterLabel.setText("Deuter");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.weighty = 0.25;
        MaterialsPanel.add(deuterLabel, gridBagConstraints);

        antimatterLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        antimatterLabel.setForeground(new java.awt.Color(255, 255, 255));
        antimatterLabel.setText("Antimatter");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.weighty = 0.25;
        MaterialsPanel.add(antimatterLabel, gridBagConstraints);

        planetsComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                planetsComboBoxItemStateChanged(evt);
            }
        });
        planetsComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                planetsComboBoxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.5;
        MaterialsPanel.add(planetsComboBox, gridBagConstraints);

        logoLabel.setIcon(loadIcon("/Resources/General_GUI/Logo.png", BUTTON_SIZE));
        logoLabel.setText("jLabel21");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weighty = 0.5;
        MaterialsPanel.add(logoLabel, gridBagConstraints);

        logoutButton.setBackground(new java.awt.Color(0, 0, 25));
        logoutButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        logoutButton.setForeground(new java.awt.Color(255, 255, 255));
        logoutButton.setText("  Logout  ");
        logoutButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        MaterialsPanel.add(logoutButton, gridBagConstraints);

        energyIcon.setIcon(loadIcon("/xorgame/client/General_GUI/Energy.jpg", MATERIAL_SIZE));
        energyIcon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 51), 2));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.5;
        MaterialsPanel.add(energyIcon, gridBagConstraints);

        energyAmount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        energyAmount.setForeground(new java.awt.Color(255, 255, 255));
        energyAmount.setText("jLabel22");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weighty = 0.25;
        MaterialsPanel.add(energyAmount, gridBagConstraints);

        energyLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        energyLabel.setForeground(new java.awt.Color(255, 255, 255));
        energyLabel.setText("Energy");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.weighty = 0.25;
        MaterialsPanel.add(energyLabel, gridBagConstraints);

        add(MaterialsPanel, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents

    private void planetsComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_planetsComboBoxItemStateChanged
        if(evt.getStateChange() == ItemEvent.SELECTED) {
            updateMaterials();

            String selectedItem = (String)((JComboBox)evt.getSource()).getSelectedItem();
            selectedItem = selectedItem.split(" ")[1];
            String[] coordinates = selectedItem.split(":");
            short x = Short.valueOf(coordinates[0]);
            short y = Short.valueOf(coordinates[1]);
            short position = Short.valueOf(coordinates[2]);
            PlanetPK newSelectedPlanet = new PlanetPK(x, y, position); 
            for(Planet pl : main.getUser().getPlanetSet()) {
                if(pl.getPlanetPK().equals(newSelectedPlanet)) {
                    main.setSelectedPlanet(pl); 
                    break;
                }
            }

            main.updateUserSessionData(main.getUser().getNick());
            updateMaterials();
            main.updateAll();
            //updateComboBox();
        }
    }//GEN-LAST:event_planetsComboBoxItemStateChanged

    private void planetsComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_planetsComboBoxActionPerformed

    }//GEN-LAST:event_planetsComboBoxActionPerformed

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        if(JOptionPane.showConfirmDialog(main, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            main.setSelectedPlanet(null); 
            CardLayout layout = (CardLayout)main.getContentPane().getLayout();
            layout.show(main.getContentPane(), "LoginRegister");
        }
    }//GEN-LAST:event_logoutButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel MaterialsPanel;
    private javax.swing.JLabel antimatterAmount;
    private javax.swing.JLabel antimatterIcon;
    private javax.swing.JLabel antimatterLabel;
    private javax.swing.JLabel cristalAmount;
    private javax.swing.JLabel cristalIcon;
    private javax.swing.JLabel cristalLabel;
    private javax.swing.JLabel deuterAmount;
    private javax.swing.JLabel deuterIcon;
    private javax.swing.JLabel deuterLabel;
    private javax.swing.JLabel energyAmount;
    private javax.swing.JLabel energyIcon;
    private javax.swing.JLabel energyLabel;
    private javax.swing.JLabel logoLabel;
    private javax.swing.JButton logoutButton;
    private javax.swing.JLabel metalAmount;
    private javax.swing.JLabel metalIcon;
    private javax.swing.JLabel metalLabel;
    private javax.swing.JComboBox<String> planetsComboBox;
    // End of variables declaration//GEN-END:variables
}
