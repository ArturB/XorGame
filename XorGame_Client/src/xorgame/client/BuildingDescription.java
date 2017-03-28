/* This software is licensed under XorGame 1.0 license. License does not expire.
 * Can be used for creating unlimited applications.
 * Can be distributed in binary or object form only.
 * Non-commercial use only.
 * Cannot modify source-code for any purpose (cannot create derivative works).
 */
package xorgame.client;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static xorgame.client.Shared.*;
import xorgame.*;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Panel with description of particular building. 
 * @author Artur
 */
public class BuildingDescription extends javax.swing.JPanel {
    Main main;
    Building building;
    /**
     * Creates new form BuildingDescription
     */
    public BuildingDescription() {
        initComponents();
    }
    
    /**
     * Sets reference to main window of the client. Used to get session data and controler. 
     * @param main_ Reference to main window of the client. 
     */
    public void setMainParent(Main main_) {
        main = main_;
    }
    
    /**
     * Sets buiding name and icon. 
     * @param building_ Building to show. 
     * @param iconPath Path to icon of the building. 
     */
    public void setContent(Building building_, String iconPath) {
        building = building_;
        iconLabel.setIcon(loadIcon(iconPath, ITEM_ICON_SIZE)); 
        nameLabel.setText(building.toString()); 
    }
    
    /**
     * Builds building to the next level. 
     */
    void buildUpBuilding() {
        try {
            main.getMaterials().updateMaterials();
            if(!main.getSelectedPlanet().getBuildingOrderSet().isEmpty())
                JOptionPane.showMessageDialog(main, "Another building is in developing!", "Cannot build!", JOptionPane.ERROR_MESSAGE);
            else if(!main.getMainControler().buildingEnabled(main.getSelectedPlanet().getPlanetPK(), Location.P, building))
                JOptionPane.showMessageDialog(main, "Technical requirements are not met!", "Cannot build!", JOptionPane.ERROR_MESSAGE);
            else if(!main.getMainControler().buildingEnoughMaterials(main.getSelectedPlanet().getPlanetPK(), Location.P, building))
                JOptionPane.showMessageDialog(main, "You have not enough materials or energy!", "Cannot build!", JOptionPane.ERROR_MESSAGE);
            else {
                main.getMainControler().buildUpBuilding(main.getSelectedPlanet().getPlanetPK(), Location.P, building);
                main.getMaterials().updateMaterials();
                JOptionPane.showMessageDialog(main, "Building sucessfully started!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(main, "Error while connecting to game server!", "Error", JOptionPane.ERROR_MESSAGE); 
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Updates building description - cost, building time, productivity and required energy. 
     */
    public void updateDescription() {
        try {
            LvlLabel.setText(NumberFormat.getNumberInstance(Locale.US).format(main.getMainControler().buildingLvl(main.getSelectedPlanet().getPlanetPK(), Location.P, building)) + " lvl");
            MCostLabel.setText(NumberFormat.getNumberInstance(Locale.US).format(main.getMainControler().buildingCost(main.getSelectedPlanet().getPlanetPK(), Location.P, Material.M, building)));
            KCostLabel.setText(NumberFormat.getNumberInstance(Locale.US).format(main.getMainControler().buildingCost(main.getSelectedPlanet().getPlanetPK(), Location.P, Material.K, building)));
            DCostLabel.setText(NumberFormat.getNumberInstance(Locale.US).format(main.getMainControler().buildingCost(main.getSelectedPlanet().getPlanetPK(), Location.P, Material.D, building)));
            ACostLabel.setText(NumberFormat.getNumberInstance(Locale.US).format(main.getMainControler().buildingCost(main.getSelectedPlanet().getPlanetPK(), Location.P, Material.A, building)));
            timeLabel.setText((main.getMainControler().buildingTime(main.getSelectedPlanet().getPlanetPK(), Location.P, building)));
            
            currentProductionLabel.setText(NumberFormat.getNumberInstance(Locale.US).format(main.getMainControler().buildingProductivity(main.getSelectedPlanet().getPlanetPK(), Location.P, building))); 
            nextLvlProductionLabel.setText(NumberFormat.getNumberInstance(Locale.US).format(main.getMainControler().buildingNextLvlProductivity(main.getSelectedPlanet().getPlanetPK(), Location.P, building))); 
            currentEnergyUseLabel.setText(NumberFormat.getNumberInstance(Locale.US).format(main.getMainControler().buildingCurrentLvlCost(main.getSelectedPlanet().getPlanetPK(), Location.P, Material.E, building))); 
            nextLvlEnergyUseLabel.setText(NumberFormat.getNumberInstance(Locale.US).format(main.getMainControler().buildingCost(main.getSelectedPlanet().getPlanetPK(), Location.P, Material.E, building))); 
            int requiredEnergy = 
                    main.getMainControler().buildingCost(main.getSelectedPlanet().getPlanetPK(), Location.P, Material.E, building) - 
                    main.getMainControler().buildingCurrentLvlCost(main.getSelectedPlanet().getPlanetPK(), Location.P, Material.E, building);
            requiredEnergyLabel.setText(NumberFormat.getNumberInstance(Locale.US).format(requiredEnergy)); 
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(main, "Error while connecting to game server!", "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
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

        metalMinePanel0 = new javax.swing.JPanel();
        iconLabel = new javax.swing.JLabel();
        descriptionPanel1 = new javax.swing.JPanel();
        nameLabel = new javax.swing.JLabel();
        LvlLabel = new javax.swing.JLabel();
        jLabel131 = new javax.swing.JLabel();
        MCostLabel = new javax.swing.JLabel();
        jLabel132 = new javax.swing.JLabel();
        KCostLabel = new javax.swing.JLabel();
        jLabel133 = new javax.swing.JLabel();
        DCostLabel = new javax.swing.JLabel();
        jLabel134 = new javax.swing.JLabel();
        ACostLabel = new javax.swing.JLabel();
        vfel17 = new javax.swing.JLabel();
        timeLabel = new javax.swing.JLabel();
        descriptionPanel2 = new javax.swing.JPanel();
        jLabel136 = new javax.swing.JLabel();
        currentProductionLabel = new javax.swing.JLabel();
        jLabel137 = new javax.swing.JLabel();
        nextLvlProductionLabel = new javax.swing.JLabel();
        jLabel = new javax.swing.JLabel();
        currentEnergyUseLabel = new javax.swing.JLabel();
        nextLvlEnergyUse = new javax.swing.JLabel();
        nextLvlEnergyUseLabel = new javax.swing.JLabel();
        vfel18 = new javax.swing.JLabel();
        requiredEnergyLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        startPanel = new javax.swing.JPanel();
        jLabel135 = new javax.swing.JLabel();
        startButton = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        metalMinePanel0.setBackground(new java.awt.Color(0, 0, 25));
        metalMinePanel0.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        metalMinePanel0.setLayout(new java.awt.GridLayout());

        iconLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconLabel.setText("jLabel29");
        metalMinePanel0.add(iconLabel);

        descriptionPanel1.setBackground(new java.awt.Color(0, 0, 25));
        descriptionPanel1.setLayout(new java.awt.GridBagLayout());

        nameLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        nameLabel.setForeground(new java.awt.Color(255, 255, 255));
        nameLabel.setText("...");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.2;
        descriptionPanel1.add(nameLabel, gridBagConstraints);

        LvlLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        LvlLabel.setForeground(new java.awt.Color(255, 255, 255));
        LvlLabel.setText("...");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.weighty = 0.2;
        descriptionPanel1.add(LvlLabel, gridBagConstraints);

        jLabel131.setForeground(new java.awt.Color(255, 255, 255));
        jLabel131.setText("Metal cost:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.weighty = 0.1;
        descriptionPanel1.add(jLabel131, gridBagConstraints);

        MCostLabel.setForeground(new java.awt.Color(255, 255, 255));
        MCostLabel.setText("...");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.1;
        descriptionPanel1.add(MCostLabel, gridBagConstraints);

        jLabel132.setForeground(new java.awt.Color(255, 255, 255));
        jLabel132.setText("Cristal cost:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.weighty = 0.1;
        descriptionPanel1.add(jLabel132, gridBagConstraints);

        KCostLabel.setForeground(new java.awt.Color(255, 255, 255));
        KCostLabel.setText("...");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.1;
        descriptionPanel1.add(KCostLabel, gridBagConstraints);

        jLabel133.setForeground(new java.awt.Color(255, 255, 255));
        jLabel133.setText("Deuter cost:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.weighty = 0.1;
        descriptionPanel1.add(jLabel133, gridBagConstraints);

        DCostLabel.setForeground(new java.awt.Color(255, 255, 255));
        DCostLabel.setText("...");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.1;
        descriptionPanel1.add(DCostLabel, gridBagConstraints);

        jLabel134.setForeground(new java.awt.Color(255, 255, 255));
        jLabel134.setText("Antimatter cost:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.weighty = 0.1;
        descriptionPanel1.add(jLabel134, gridBagConstraints);

        ACostLabel.setForeground(new java.awt.Color(255, 255, 255));
        ACostLabel.setText("...");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.1;
        descriptionPanel1.add(ACostLabel, gridBagConstraints);

        vfel17.setForeground(new java.awt.Color(255, 255, 255));
        vfel17.setText("Time to build:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.weighty = 0.1;
        descriptionPanel1.add(vfel17, gridBagConstraints);

        timeLabel.setForeground(new java.awt.Color(255, 255, 255));
        timeLabel.setText("...");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.1;
        descriptionPanel1.add(timeLabel, gridBagConstraints);

        metalMinePanel0.add(descriptionPanel1);

        descriptionPanel2.setBackground(new java.awt.Color(0, 0, 25));
        descriptionPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel136.setForeground(new java.awt.Color(255, 255, 255));
        jLabel136.setText("Current production:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.weighty = 0.1;
        descriptionPanel2.add(jLabel136, gridBagConstraints);

        currentProductionLabel.setForeground(new java.awt.Color(255, 255, 255));
        currentProductionLabel.setText("...");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.1;
        descriptionPanel2.add(currentProductionLabel, gridBagConstraints);

        jLabel137.setForeground(new java.awt.Color(255, 255, 255));
        jLabel137.setText("Next lvl production:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.weighty = 0.1;
        descriptionPanel2.add(jLabel137, gridBagConstraints);

        nextLvlProductionLabel.setForeground(new java.awt.Color(255, 255, 255));
        nextLvlProductionLabel.setText("...");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.1;
        descriptionPanel2.add(nextLvlProductionLabel, gridBagConstraints);

        jLabel.setForeground(new java.awt.Color(255, 255, 255));
        jLabel.setText("Current energy use:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.weighty = 0.1;
        descriptionPanel2.add(jLabel, gridBagConstraints);

        currentEnergyUseLabel.setForeground(new java.awt.Color(255, 255, 255));
        currentEnergyUseLabel.setText("...");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.1;
        descriptionPanel2.add(currentEnergyUseLabel, gridBagConstraints);

        nextLvlEnergyUse.setForeground(new java.awt.Color(255, 255, 255));
        nextLvlEnergyUse.setText("Next lvl energy use:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.weighty = 0.1;
        descriptionPanel2.add(nextLvlEnergyUse, gridBagConstraints);

        nextLvlEnergyUseLabel.setForeground(new java.awt.Color(255, 255, 255));
        nextLvlEnergyUseLabel.setText("...");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.1;
        descriptionPanel2.add(nextLvlEnergyUseLabel, gridBagConstraints);

        vfel18.setForeground(new java.awt.Color(255, 255, 255));
        vfel18.setText("Required energy:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.weighty = 0.1;
        descriptionPanel2.add(vfel18, gridBagConstraints);

        requiredEnergyLabel.setForeground(new java.awt.Color(255, 255, 255));
        requiredEnergyLabel.setText("...");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.1;
        descriptionPanel2.add(requiredEnergyLabel, gridBagConstraints);

        jLabel1.setText(".");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.weighty = 0.2;
        descriptionPanel2.add(jLabel1, gridBagConstraints);

        jLabel2.setText(".");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.2;
        descriptionPanel2.add(jLabel2, gridBagConstraints);

        metalMinePanel0.add(descriptionPanel2);

        startPanel.setBackground(new java.awt.Color(0, 0, 25));
        startPanel.setLayout(new java.awt.GridLayout(3, 1));
        startPanel.add(jLabel135);

        startButton.setBackground(new java.awt.Color(0, 102, 0));
        startButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        startButton.setForeground(new java.awt.Color(255, 255, 255));
        startButton.setText("START");
        startButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 153)));
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });
        startPanel.add(startButton);

        metalMinePanel0.add(startPanel);

        add(metalMinePanel0, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        buildUpBuilding();
    }//GEN-LAST:event_startButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ACostLabel;
    private javax.swing.JLabel DCostLabel;
    private javax.swing.JLabel KCostLabel;
    private javax.swing.JLabel LvlLabel;
    private javax.swing.JLabel MCostLabel;
    private javax.swing.JLabel currentEnergyUseLabel;
    private javax.swing.JLabel currentProductionLabel;
    private javax.swing.JPanel descriptionPanel1;
    private javax.swing.JPanel descriptionPanel2;
    private javax.swing.JLabel iconLabel;
    private javax.swing.JLabel jLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel131;
    private javax.swing.JLabel jLabel132;
    private javax.swing.JLabel jLabel133;
    private javax.swing.JLabel jLabel134;
    private javax.swing.JLabel jLabel135;
    private javax.swing.JLabel jLabel136;
    private javax.swing.JLabel jLabel137;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel metalMinePanel0;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel nextLvlEnergyUse;
    private javax.swing.JLabel nextLvlEnergyUseLabel;
    private javax.swing.JLabel nextLvlProductionLabel;
    private javax.swing.JLabel requiredEnergyLabel;
    private javax.swing.JButton startButton;
    private javax.swing.JPanel startPanel;
    private javax.swing.JLabel timeLabel;
    private javax.swing.JLabel vfel17;
    private javax.swing.JLabel vfel18;
    // End of variables declaration//GEN-END:variables
}
