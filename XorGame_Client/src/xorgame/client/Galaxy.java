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
import xorgame.*;
import java.util.*;
import java.awt.*;
import javax.swing.JTextField;
import static xorgame.client.Shared.*;

/**
 * Panel that allows user to see nearby planets. Visible planets must be in the range of observatory of currently selected planet. 
 * @author Artur
 */
public class Galaxy extends javax.swing.JPanel {
    
    Main main;
    /**
     * Creates new form Galaxy
     */
    public Galaxy() {
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
     * Returns text field with X coordinate used to filter visible planets. 
     * @return 
     */
    public JTextField getxFilterLabel() {
        return xFilterLabel;
    }
    
    /**
     * Returns text field with Y coordinate used to filter visible planets. 
     * @return 
     */
    public JTextField getyFilterLabel() {
        return yFilterLabel;
    }
    
    /**
     * Filters visible planets according to coordinates specified in text fields in the header. Only planets from solar systems matching to specified coordinates are shown. 
     */
    public void filter() {
        galaxyPanel.setVisible(false); 
        Component[] records = galaxyPanel.getComponents();
        galaxyPanel.removeAll();
        ((GridLayout)galaxyPanel.getLayout()).setRows(20); 
        //non filter
        if(xFilterLabel.getText().isEmpty() && yFilterLabel.getText().isEmpty())
            updatePlanets();
        //only X coordinate
        else if(!xFilterLabel.getText().isEmpty() && yFilterLabel.getText().isEmpty()) {
            for(Component rec: records) {
                GalaxyRecord gRec = (GalaxyRecord)rec;
                if( gRec.getPlanetPK().getX() == Short.valueOf(xFilterLabel.getText()) ) {
                    galaxyPanel.add(gRec);
                    GridLayout layout = ((GridLayout)galaxyPanel.getLayout());
                    layout.setRows(layout.getRows() + 1); 
                }
            }
        }
        //only Y coordinate
        else if(xFilterLabel.getText().isEmpty() && !yFilterLabel.getText().isEmpty()) {
            for(Component rec: records) {
                GalaxyRecord gRec = (GalaxyRecord)rec;
                if( gRec.getPlanetPK().getY() == Short.valueOf(yFilterLabel.getText()) ) {
                    galaxyPanel.add(gRec);
                    GridLayout layout = ((GridLayout)galaxyPanel.getLayout());
                    layout.setRows(layout.getRows() + 1); 
                }
            }
        }
        //both coordinates
        else {
            for(Component rec: records) {
                GalaxyRecord gRec = (GalaxyRecord)rec;
                if( 
                        gRec.getPlanetPK().getX() == Short.valueOf(xFilterLabel.getText()) && 
                        gRec.getPlanetPK().getY() == Short.valueOf(yFilterLabel.getText()) 
                  ) {
                    galaxyPanel.add(gRec);
                    GridLayout layout = ((GridLayout)galaxyPanel.getLayout());
                    layout.setRows(layout.getRows() + 1); 
                }
            }
        }
        galaxyPanel.validate();
        galaxyPanel.setVisible(true); 
    }
    
    /**
     * Download visible planets from game database. 
     */
    public void updatePlanets() {
        try {
            galaxyPanel.removeAll();
            
            Set<Planet> planets = main.getMainControler().visiblePlanets(main.getSelectedPlanet().getPlanetPK());
            java.util.List<Planet> planetList = new ArrayList();
            for(Planet pl : planets)
                planetList.add(pl);
            
            planetList.sort((Planet pl1, Planet pl2) -> {
                double dist1 = 0;
                double dist2 = 0;
                try {
                    Planet selected = main.getSelectedPlanet();
                    dist1 = main.getMainControler().planetDistance(pl1.getPlanetPK(), selected.getPlanetPK());
                    dist2 = main.getMainControler().planetDistance(pl2.getPlanetPK(), selected.getPlanetPK());
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(main, "Error while connecting to game server!", "Error", JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(Galaxy.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(dist1 > dist2)
                    return 1;
                else if(dist1 < dist2)
                    return -1;
                else
                    return 0;
            });
                
            ((GridLayout)galaxyPanel.getLayout()).setRows(planets.size());
            
            for(Planet pl : planetList) {
                GalaxyRecord record = new GalaxyRecord();
                record.setMainParent(main); 
                record.setContent(pl); 
                galaxyPanel.add(record);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error while connecting to game server!", "Error", JOptionPane.ERROR_MESSAGE); 
            Logger.getLogger(Galaxy.class.getName()).log(Level.SEVERE, null, ex);
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

        galaxyHeaderPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        filterLabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        xFilterLabel = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        yFilterLabel = new javax.swing.JTextField();
        filterButton = new javax.swing.JButton();
        galaxyScrollPanel = new javax.swing.JScrollPane();
        galaxyScrollContentPanel = new javax.swing.JPanel();
        recordsHeaderPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        galaxyPanel = new javax.swing.JPanel();

        setBackground(new java.awt.Color(0, 0, 25));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setLayout(new java.awt.BorderLayout());

        galaxyHeaderPanel.setBackground(new java.awt.Color(0, 0, 25));
        galaxyHeaderPanel.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204))));
        galaxyHeaderPanel.setLayout(new java.awt.GridLayout(2, 5, 20, 5));

        jLabel1.setText("jLabel1");
        galaxyHeaderPanel.add(jLabel1);

        jLabel2.setText("jLabel1");
        galaxyHeaderPanel.add(jLabel2);

        filterLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        filterLabel.setForeground(new java.awt.Color(255, 255, 255));
        filterLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        filterLabel.setText("FILTER PLANETS");
        galaxyHeaderPanel.add(filterLabel);

        jLabel4.setText("jLabel1");
        galaxyHeaderPanel.add(jLabel4);

        jLabel5.setText("jLabel1");
        galaxyHeaderPanel.add(jLabel5);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("X = ");
        galaxyHeaderPanel.add(jLabel6);
        galaxyHeaderPanel.add(xFilterLabel);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Y = ");
        galaxyHeaderPanel.add(jLabel7);
        galaxyHeaderPanel.add(yFilterLabel);

        filterButton.setBackground(new java.awt.Color(0, 102, 0));
        filterButton.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        filterButton.setForeground(new java.awt.Color(255, 255, 255));
        filterButton.setText("Filter!");
        filterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterButtonActionPerformed(evt);
            }
        });
        galaxyHeaderPanel.add(filterButton);

        add(galaxyHeaderPanel, java.awt.BorderLayout.PAGE_START);

        galaxyScrollPanel.getVerticalScrollBar().setUnitIncrement(SCROLL_SPEED);

        galaxyScrollContentPanel.setLayout(new java.awt.BorderLayout());

        recordsHeaderPanel.setBackground(new java.awt.Color(0, 0, 25));
        recordsHeaderPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        recordsHeaderPanel.setLayout(new java.awt.GridLayout(1, 7, 30, 0));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Coordinates");
        recordsHeaderPanel.add(jLabel3);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Name");
        recordsHeaderPanel.add(jLabel8);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("User nick");
        recordsHeaderPanel.add(jLabel9);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Distance");
        recordsHeaderPanel.add(jLabel10);

        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        recordsHeaderPanel.add(jLabel11);

        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        recordsHeaderPanel.add(jLabel12);

        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        recordsHeaderPanel.add(jLabel13);

        galaxyScrollContentPanel.add(recordsHeaderPanel, java.awt.BorderLayout.PAGE_START);

        galaxyPanel.setBackground(new java.awt.Color(0, 0, 25));
        galaxyPanel.setLayout(new java.awt.GridLayout(1, 1, 2, 2));
        galaxyScrollContentPanel.add(galaxyPanel, java.awt.BorderLayout.CENTER);

        galaxyScrollPanel.setViewportView(galaxyScrollContentPanel);

        add(galaxyScrollPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void filterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterButtonActionPerformed
        filter();
    }//GEN-LAST:event_filterButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton filterButton;
    private javax.swing.JLabel filterLabel;
    private javax.swing.JPanel galaxyHeaderPanel;
    private javax.swing.JPanel galaxyPanel;
    private javax.swing.JPanel galaxyScrollContentPanel;
    private javax.swing.JScrollPane galaxyScrollPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel recordsHeaderPanel;
    private javax.swing.JTextField xFilterLabel;
    private javax.swing.JTextField yFilterLabel;
    // End of variables declaration//GEN-END:variables
}
