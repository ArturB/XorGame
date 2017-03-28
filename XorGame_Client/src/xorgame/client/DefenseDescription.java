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
 * Panel with description of particular defense system. 
 * @author Artur
 */
public class DefenseDescription extends javax.swing.JPanel {
    Main main;
    ShipyardItem item;
    
    /**
     * Creates new form DefenseDecription
     */
    public DefenseDescription() {
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
     * Sets icons and names of defense system. 
     * @param item_ Defense system to show. 
     * @param iconPath Path to icon of this defense system. 
     */
    public void setContent(ShipyardItem item_, String iconPath) {
        item = item_;
        iconLabel.setIcon(loadIcon(iconPath, ITEM_ICON_SIZE)); 
        nameLabel.setText(item.toString()); 
        amountTextField.setText(""); 
    }
    
    /**
     * Updates description of defense system - cost and building time. 
     */
    public void updateDescription() {
        try {
            amountLabel.setText(NumberFormat.getNumberInstance(Locale.US).format(main.getMainControler().shipyardItemAmount(main.getSelectedPlanet().getPlanetPK(), Location.P, item)));
            MCostLabel.setText(NumberFormat.getNumberInstance(Locale.US).format(main.getMainControler().defenseCost(item, Material.M)));
            KCostLabel.setText(NumberFormat.getNumberInstance(Locale.US).format(main.getMainControler().defenseCost(item, Material.K)));
            DCostLabel.setText(NumberFormat.getNumberInstance(Locale.US).format(main.getMainControler().defenseCost(item, Material.D)));
            ACostLabel.setText(NumberFormat.getNumberInstance(Locale.US).format(main.getMainControler().defenseCost(item, Material.A)));
            timeLabel.setText(main.getMainControler().defenseTime(main.getSelectedPlanet().getPlanetPK(), Location.P, item, 1));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(main, "Error while connecting to game server!", "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(ResearchDescription.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Starts building of new defense system. Checks requirements and input data errors. 
     * @param amount Number of defense systems. 
     */
    public void newDefenseOrder(int amount) {
        try {
            main.getMaterials().updateMaterials();
            if(amount <= 0)
                JOptionPane.showMessageDialog(main, "Amount must be grater than 0!", "Cannot start", JOptionPane.ERROR_MESSAGE);
            else if(main.getSelectedPlanet().getShipyard() == 0)
                JOptionPane.showMessageDialog(main, "You must first build a shipyard!", "Cannot start", JOptionPane.ERROR_MESSAGE);
            else if(!main.getMainControler().shipyardEnabled(main.getSelectedPlanet().getPlanetPK(), Location.P, item))
                JOptionPane.showMessageDialog(main, "Technical requirements are not met!", "Cannot start!", JOptionPane.ERROR_MESSAGE);
            else if(main.getMainControler().maxDefenseItems(main.getSelectedPlanet().getPlanetPK(), Location.P, item) == 0)
                JOptionPane.showMessageDialog(main, "Too few resources!", "Cannot start", JOptionPane.ERROR_MESSAGE);
            else if(main.getMainControler().maxDefenseItems(main.getSelectedPlanet().getPlanetPK(), Location.P, item) < amount) {
                if(JOptionPane.showConfirmDialog(main, 
                        "Max possible number of " + item.toString() + " is " + 
                                main.getMainControler().maxDefenseItems(main.getSelectedPlanet().getPlanetPK(), Location.P, item) + 
                                ", build?") == JOptionPane.YES_OPTION) {
                    newDefenseOrderUnsafe();
                }
            }
            else
                newDefenseOrderUnsafe();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(main, "Error while connecting to game server!", "Error", JOptionPane.ERROR_MESSAGE); 
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Starts building of new defense system. Does not check requirements and input data errors.
     */
    void newDefenseOrderUnsafe() {
        try {
            main.getMainControler().newDefenseOrder(main.getSelectedPlanet().getPlanetPK(), Location.P, item, parseIntSafe(amountTextField.getText()));
            main.getMaterials().updateMaterials();
            JOptionPane.showMessageDialog(main, "Building sucessfully started!"); 
            amountTextField.setText("");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(main, "Cannot connect to the game server!", "Error", JOptionPane.ERROR_MESSAGE); 
            Logger.getLogger(ShipyardDescription.class.getName()).log(Level.SEVERE, null, ex);
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

        defensePanel = new javax.swing.JPanel();
        iconLabel = new javax.swing.JLabel();
        descriptionPanel = new javax.swing.JPanel();
        nameLabel = new javax.swing.JLabel();
        amountLabel = new javax.swing.JLabel();
        jLabel221 = new javax.swing.JLabel();
        MCostLabel = new javax.swing.JLabel();
        jLabel222 = new javax.swing.JLabel();
        KCostLabel = new javax.swing.JLabel();
        jLabel223 = new javax.swing.JLabel();
        DCostLabel = new javax.swing.JLabel();
        jLabel224 = new javax.swing.JLabel();
        ACostLabel = new javax.swing.JLabel();
        vfel32 = new javax.swing.JLabel();
        timeLabel = new javax.swing.JLabel();
        startPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        amountTextField = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        startButton = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        defensePanel.setBackground(new java.awt.Color(0, 0, 25));
        defensePanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        defensePanel.setLayout(new java.awt.GridLayout(1, 3));

        iconLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconLabel.setText("jLabel29");
        defensePanel.add(iconLabel);

        descriptionPanel.setBackground(new java.awt.Color(0, 0, 25));
        descriptionPanel.setLayout(new java.awt.GridLayout(6, 2));

        nameLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        nameLabel.setForeground(new java.awt.Color(255, 255, 255));
        descriptionPanel.add(nameLabel);

        amountLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        amountLabel.setForeground(new java.awt.Color(255, 255, 255));
        descriptionPanel.add(amountLabel);

        jLabel221.setForeground(new java.awt.Color(255, 255, 255));
        jLabel221.setText("Metal cost:");
        descriptionPanel.add(jLabel221);

        MCostLabel.setForeground(new java.awt.Color(255, 255, 255));
        descriptionPanel.add(MCostLabel);

        jLabel222.setForeground(new java.awt.Color(255, 255, 255));
        jLabel222.setText("Cristal cost:");
        descriptionPanel.add(jLabel222);

        KCostLabel.setForeground(new java.awt.Color(255, 255, 255));
        descriptionPanel.add(KCostLabel);

        jLabel223.setForeground(new java.awt.Color(255, 255, 255));
        jLabel223.setText("Deuter cost:");
        descriptionPanel.add(jLabel223);

        DCostLabel.setForeground(new java.awt.Color(255, 255, 255));
        descriptionPanel.add(DCostLabel);

        jLabel224.setForeground(new java.awt.Color(255, 255, 255));
        jLabel224.setText("Antimatter cost:");
        descriptionPanel.add(jLabel224);

        ACostLabel.setForeground(new java.awt.Color(255, 255, 255));
        descriptionPanel.add(ACostLabel);

        vfel32.setForeground(new java.awt.Color(255, 255, 255));
        vfel32.setText("Time to build:");
        descriptionPanel.add(vfel32);

        timeLabel.setForeground(new java.awt.Color(255, 255, 255));
        descriptionPanel.add(timeLabel);

        defensePanel.add(descriptionPanel);

        startPanel.setBackground(new java.awt.Color(0, 0, 25));
        startPanel.setLayout(new java.awt.GridLayout(2, 1, 20, 40));

        jPanel1.setBackground(new java.awt.Color(0, 0, 25));
        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jPanel1.setLayout(new java.awt.BorderLayout());

        amountTextField.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        amountTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        amountTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jPanel1.add(amountTextField, java.awt.BorderLayout.CENTER);

        startPanel.add(jPanel1);

        jPanel2.setBackground(new java.awt.Color(0, 0, 25));
        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jPanel2.setLayout(new java.awt.BorderLayout());

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
        jPanel2.add(startButton, java.awt.BorderLayout.CENTER);

        startPanel.add(jPanel2);

        defensePanel.add(startPanel);

        add(defensePanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        String value = amountTextField.getText();
        if(value.isEmpty())
            JOptionPane.showMessageDialog(this, "Type amount!", "Amount error!", JOptionPane.ERROR_MESSAGE);
        else if( !value.matches("^[0-9]+$") )
            JOptionPane.showMessageDialog(this, "Ship amount may contain only digits!", "Amount error!", JOptionPane.ERROR_MESSAGE);
        else
            newDefenseOrder(Integer.valueOf(value));
    }//GEN-LAST:event_startButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ACostLabel;
    private javax.swing.JLabel DCostLabel;
    private javax.swing.JLabel KCostLabel;
    private javax.swing.JLabel MCostLabel;
    private javax.swing.JLabel amountLabel;
    private javax.swing.JTextField amountTextField;
    private javax.swing.JPanel defensePanel;
    private javax.swing.JPanel descriptionPanel;
    private javax.swing.JLabel iconLabel;
    private javax.swing.JLabel jLabel221;
    private javax.swing.JLabel jLabel222;
    private javax.swing.JLabel jLabel223;
    private javax.swing.JLabel jLabel224;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JButton startButton;
    private javax.swing.JPanel startPanel;
    private javax.swing.JLabel timeLabel;
    private javax.swing.JLabel vfel32;
    // End of variables declaration//GEN-END:variables
}
