/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xorgame.client;

import java.awt.*;
import javax.swing.*;
import xorgame.*;
import java.util.*;
import java.io.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.audio.*;
import static xorgame.client.Shared.*;
/**
 * Main frame of the XorGame client. 
 * @author Artur M. Brodzki
 */
public class Main extends javax.swing.JFrame {
    
    final private Controler mainControler;
    private GameGui gui;
    private User user = null;
    private Planet selectedPlanet = null;
    
    Thread musicThread;
    
    /**
     * Returns panel with main client content. 
     * @return Panel with main client content. 
     */
    public JPanel getWorkPanel() {
        return workPanel;
    }
    
    /**
     * Returns controller of the game, used to perform all operations required to play and communicate with game server. 
     * @return Controller of the game. 
     */
    public Controler getMainControler() {
        return mainControler;
    }
    
    /**
     * Returns thread playing background music. 
     * @return Thread playing background music. 
     */
    public Thread getMusicThread() {
        return musicThread;
    }
    
    /**
     * Returns currently logged user. 
     * @return Currently logged user. 
     */
    public User getUser() {
        return user;
    }
    
    /**
     * Returns actually selected user planet. 
     * @return Actually selected user planet.
     */
    public Planet getSelectedPlanet() {
        return selectedPlanet;
    }    
    
    /**
     * Gets images to show in the client downloaded from game server. 
     * @return Images to show in the client downloaded from game server. 
     */
    public GameGui getGui() {
        return gui;
    }
    
    /**
     * Sets images to show in the client downloaded from game server.
     * @param gui Images to show in the client downloaded from game server.
     */
    public void setGui(GameGui gui) {
        this.gui = gui;
    }
    
    /**
     * Set currently logged user. 
     * @param user Currently logged user. 
     */
    public void setUser(User user) {
        this.user = user;
    }
    
    /**
     * Sets actually selected user planet. 
     * @param selectedPlanet Actually selected user planet. 
     */
    public void setSelectedPlanet(Planet selectedPlanet) {
        this.selectedPlanet = selectedPlanet;
    }
    
    /**
     * Gets preview panel. 
     * @return Preview panel. 
     */
    public Preview getPreview() {
        return preview;
    }
    
    /**
     * Gets buildings panel. 
     * @return Buildings panel. 
     */
    public Buildings getBuildings() {
        return buildings;
    }
    
    /**
     * Gets defense panel. 
     * @return Defense panel. 
     */
    public Defense getDefense() {
        return defense;
    }

    /**
     * Gets fleet panel. 
     * @return Fleet panel. 
     */
    public xorgame.client.Fleet getFleet() {
        return fleet;
    }
    
    /**
     * Gets research panel. 
     * @return Research panel. 
     */
    public ResearchPanel getResearch() {
        return research;
    }
    
    /**
     * Gets shipyard panel. 
     * @return Shipyard panel. 
     */
    public Shipyard getShipyard() {
        return shipyard;
    }
    
    /**
     * Gets materials panel. 
     * @return Materials panel. 
     */
    public MaterialsPanel getMaterials() {
        return materials;
    }

    public Messages getMessages() {
        return messages;
    }

    public NewMessage getNewMessage() {
        return newMessage;
    }
    
    /**
     * Downloads data of user planets from game server. 
     * @param nick 
     */
    public void updateUserSessionData(String nick) {
        user = mainControler.getUserData(nick);  
        for(Planet pl : user.getPlanetSet()) {
            if(pl.getPlanetPK().equals(selectedPlanet.getPlanetPK())) {
                selectedPlanet = pl;
                break;
            }
        }
    }
    
    /**
     * Updates state of all panels in the game. 
     */
    public void updateAll() {
        getPreview().updatePreview();
        getBuildings().updateBuildings();
        getResearch().updateResearch();
        getShipyard().updateShipyard();
        getDefense().updateDefense();
        getGalaxy().updatePlanets();
    }
    
    /**
     * Gets galaxy panel. 
     * @return Galaxy panel. 
     */
    public Galaxy getGalaxy() {
        return galaxy;
    }

    /**
     * Creates new window
     */
    public Main() throws SQLException, IOException {
        Properties dbconf = new Properties();
        dbconf.load(getClass().getResourceAsStream("/META-INF/dbconf.properties"));
        String address = dbconf.getProperty("address");
        String port = dbconf.getProperty("port");
        String dbname = dbconf.getProperty("dbname");
        String username = dbconf.getProperty("username");
        String password = dbconf.getProperty("password");

        mainControler = new Controler(address, Integer.valueOf(port), dbname, username, password);
        gui = new GameGui();
        
        initComponents();
        
        musicThread = new Thread(() -> {
            try {
                InputStream in = getClass().getResourceAsStream("/Resources/StartMusic.wav");
                AudioStream music = new AudioStream(in);
                AudioPlayer.player.start(music);
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        musicThread.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        metalMinePanel = new javax.swing.JPanel();
        metalMineIconLabel = new javax.swing.JLabel();
        metalMineDescriptionPanel = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        metalMineLvlLabel = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        metalMineMCostLabel = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        metalMineKCostLabel = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        metalMineDCostLabel = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        metalMineACostLabel = new javax.swing.JLabel();
        vfel16 = new javax.swing.JLabel();
        metalMineTimeLabel = new javax.swing.JLabel();
        metalMineStartPanel = new javax.swing.JPanel();
        jLabel129 = new javax.swing.JLabel();
        metalMineStartButton = new javax.swing.JButton();
        loginRegister = new xorgame.client.LoginRegister();
        Main = new javax.swing.JPanel();
        materials = new xorgame.client.MaterialsPanel();
        controlPanel = new xorgame.client.ControlPanel();
        workPanel = new javax.swing.JPanel();
        preview = new xorgame.client.Preview();
        buildings = new xorgame.client.Buildings();
        research = new xorgame.client.ResearchPanel();
        shipyard = new xorgame.client.Shipyard();
        defense = new xorgame.client.Defense();
        fleet = new xorgame.client.Fleet();
        galaxy = new xorgame.client.Galaxy();
        settings = new xorgame.client.Settings();
        messages = new xorgame.client.Messages();
        newMessage = new xorgame.client.NewMessage();

        metalMinePanel.setBackground(new java.awt.Color(0, 0, 25));
        metalMinePanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        metalMinePanel.setLayout(new java.awt.GridLayout(1, 3));

        metalMineIconLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        metalMineIconLabel.setIcon(loadIcon("/xorgame/client/General_GUI/MetalMine.jpg", ITEM_ICON_SIZE));
        metalMineIconLabel.setText("jLabel29");
        metalMinePanel.add(metalMineIconLabel);

        metalMineDescriptionPanel.setBackground(new java.awt.Color(0, 0, 25));
        metalMineDescriptionPanel.setLayout(new java.awt.GridLayout(6, 2));

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("Metal mine");
        metalMineDescriptionPanel.add(jLabel31);

        metalMineLvlLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        metalMineLvlLabel.setForeground(new java.awt.Color(255, 255, 255));
        metalMineLvlLabel.setText("Metal Mine");
        metalMineDescriptionPanel.add(metalMineLvlLabel);

        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Metal cost:");
        metalMineDescriptionPanel.add(jLabel34);

        metalMineMCostLabel.setForeground(new java.awt.Color(255, 255, 255));
        metalMineMCostLabel.setText("Metal Mine");
        metalMineDescriptionPanel.add(metalMineMCostLabel);

        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setText("Cristal cost:");
        metalMineDescriptionPanel.add(jLabel39);

        metalMineKCostLabel.setForeground(new java.awt.Color(255, 255, 255));
        metalMineKCostLabel.setText("Metal Mine");
        metalMineDescriptionPanel.add(metalMineKCostLabel);

        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setText("Deuter cost:");
        metalMineDescriptionPanel.add(jLabel41);

        metalMineDCostLabel.setForeground(new java.awt.Color(255, 255, 255));
        metalMineDCostLabel.setText("Metal Mine");
        metalMineDescriptionPanel.add(metalMineDCostLabel);

        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setText("Antimatter cost:");
        metalMineDescriptionPanel.add(jLabel45);

        metalMineACostLabel.setForeground(new java.awt.Color(255, 255, 255));
        metalMineACostLabel.setText("Metal Mine");
        metalMineDescriptionPanel.add(metalMineACostLabel);

        vfel16.setForeground(new java.awt.Color(255, 255, 255));
        vfel16.setText("Time to build:");
        metalMineDescriptionPanel.add(vfel16);

        metalMineTimeLabel.setForeground(new java.awt.Color(255, 255, 255));
        metalMineTimeLabel.setText("Metal Mine");
        metalMineDescriptionPanel.add(metalMineTimeLabel);

        metalMinePanel.add(metalMineDescriptionPanel);

        metalMineStartPanel.setBackground(new java.awt.Color(0, 0, 25));
        metalMineStartPanel.setLayout(new java.awt.GridLayout(3, 1));
        metalMineStartPanel.add(jLabel129);

        metalMineStartButton.setBackground(new java.awt.Color(0, 102, 0));
        metalMineStartButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        metalMineStartButton.setForeground(new java.awt.Color(255, 255, 255));
        metalMineStartButton.setText("START");
        metalMineStartButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 153)));
        metalMineStartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                metalMineStartButtonActionPerformed(evt);
            }
        });
        metalMineStartPanel.add(metalMineStartButton);

        metalMinePanel.add(metalMineStartPanel);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("XorGame");
        setIconImage(loadImage("/Resources/General_GUI/Colonizer.jpg", 0.1F));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                formKeyTyped(evt);
            }
        });
        getContentPane().setLayout(new java.awt.CardLayout());

        loginRegister.setMainParent(this);
        getContentPane().add(loginRegister, "LoginRegister");

        Main.setLayout(new java.awt.BorderLayout());

        materials.setMainParent(this);
        Main.add(materials, java.awt.BorderLayout.NORTH);

        controlPanel.setMainParent(this);
        Main.add(controlPanel, java.awt.BorderLayout.LINE_START);

        workPanel.setBackground(new java.awt.Color(0, 0, 25));
        workPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        workPanel.setLayout(new java.awt.CardLayout());

        preview.setMainParent(this);
        workPanel.add(preview, "PreviewCard");

        buildings.setMainParent(this);
        buildings.setContent();
        workPanel.add(buildings, "BuildingsCard");

        research.setMainParent(this);
        research.setContent();
        workPanel.add(research, "ResearchCard");

        shipyard.setMainParent(this);
        shipyard.setContent();
        workPanel.add(shipyard, "ShipyardCard");

        defense.setMainParent(this);
        defense.setContent();
        workPanel.add(defense, "DefenseCard");

        fleet.setMainParent(this);
        workPanel.add(fleet, "FleetCard");

        galaxy.setMainParent(this);
        workPanel.add(galaxy, "GalaxyCard");

        settings.setMainParent(this);
        workPanel.add(settings, "SettingsCard");

        messages.setMainParent(this);
        workPanel.add(messages, "MessagesCard");

        newMessage.setMainParent(this);
        workPanel.add(newMessage, "NewMessageCard");

        Main.add(workPanel, java.awt.BorderLayout.CENTER);

        getContentPane().add(Main, "Main");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        
    }//GEN-LAST:event_formComponentShown

    private void formKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyTyped

    }//GEN-LAST:event_formKeyTyped

    private void metalMineStartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_metalMineStartButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_metalMineStartButtonActionPerformed

    /**
     * Main method. 
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        java.awt.EventQueue.invokeLater(() -> {
            try {
                Main window = new Main();
                window.setSize(new Dimension(window.getSize().width, Math.round(INIT_FRAME_SIZE * Toolkit.getDefaultToolkit().getScreenSize().height)));
                window.getMaterials().startAutoUpdating();
                window.setVisible(true);
            } catch (SQLException | IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        /* Create and display the form */
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Main;
    private xorgame.client.Buildings buildings;
    private xorgame.client.ControlPanel controlPanel;
    private xorgame.client.Defense defense;
    private xorgame.client.Fleet fleet;
    private xorgame.client.Galaxy galaxy;
    private javax.swing.JLabel jLabel129;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel45;
    private xorgame.client.LoginRegister loginRegister;
    private xorgame.client.MaterialsPanel materials;
    private xorgame.client.Messages messages;
    private javax.swing.JLabel metalMineACostLabel;
    private javax.swing.JLabel metalMineDCostLabel;
    private javax.swing.JPanel metalMineDescriptionPanel;
    private javax.swing.JLabel metalMineIconLabel;
    private javax.swing.JLabel metalMineKCostLabel;
    private javax.swing.JLabel metalMineLvlLabel;
    private javax.swing.JLabel metalMineMCostLabel;
    private javax.swing.JPanel metalMinePanel;
    private javax.swing.JButton metalMineStartButton;
    private javax.swing.JPanel metalMineStartPanel;
    private javax.swing.JLabel metalMineTimeLabel;
    private xorgame.client.NewMessage newMessage;
    private xorgame.client.Preview preview;
    private xorgame.client.ResearchPanel research;
    private xorgame.client.Settings settings;
    private xorgame.client.Shipyard shipyard;
    private javax.swing.JLabel vfel16;
    private javax.swing.JPanel workPanel;
    // End of variables declaration//GEN-END:variables

    
    
    



}
