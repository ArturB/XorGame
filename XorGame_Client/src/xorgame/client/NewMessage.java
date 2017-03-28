/* This software is licensed under XorGame 1.0 license. License does not expire.
 * Can be used for creating unlimited applications.
 * Can be distributed in binary or object form only.
 * Non-commercial use only.
 * Cannot modify source-code for any purpose (cannot create derivative works).
 */
package xorgame.client;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import xorgame.*;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Artur
 */
public class NewMessage extends javax.swing.JPanel {
    Main main;

    /**
     * Creates new form NewMessage
     */
    public NewMessage() {
        initComponents();
    }
    
    public void setMainParent(Main main_) {
        main = main_;
    }
    
    public void setFrom(String user) {
        fromTextField.setText(user); 
    }
    
    public void setTo(String user) {
        toTextField.setText(user); 
    }
    
    public void setContent(String text) {
        content.setText(text); 
    }
    
    public void sendMessage() {
        try {
            main.getMainControler().sendMessage(fromTextField.getText(), toTextField.getText(), new Date(), content.getText());
            main.getMessages().updateMessages();
            CardLayout layout = (CardLayout)main.getWorkPanel().getLayout();
            layout.show(main.getWorkPanel(), "MessagesCard");
            JOptionPane.showMessageDialog(main, "Message successfully sent!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(main, "Invalid receiver nick!", "Error", JOptionPane.ERROR_MESSAGE); 
            Logger.getLogger(NewMessage.class.getName()).log(Level.SEVERE, null, ex);
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

        headerPanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        fromTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        toTextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        content = new javax.swing.JTextArea();
        sendPanel = new javax.swing.JPanel();
        sendButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 0, 25));
        setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153))));
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                formKeyTyped(evt);
            }
        });
        setLayout(new java.awt.BorderLayout());

        headerPanel.setBackground(new java.awt.Color(0, 0, 25));
        headerPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        headerPanel.setLayout(new java.awt.GridLayout(1, 4, 20, 20));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("From:");
        headerPanel.add(jLabel4);

        fromTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fromTextFieldActionPerformed(evt);
            }
        });
        headerPanel.add(fromTextField);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("To:");
        headerPanel.add(jLabel5);
        headerPanel.add(toTextField);

        add(headerPanel, java.awt.BorderLayout.NORTH);

        content.setBackground(new java.awt.Color(0, 0, 44));
        content.setColumns(20);
        content.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        content.setForeground(new java.awt.Color(255, 255, 255));
        content.setRows(5);
        content.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jScrollPane1.setViewportView(content);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);

        sendPanel.setBackground(new java.awt.Color(0, 0, 25));
        sendPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        sendPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        sendButton.setBackground(new java.awt.Color(0, 153, 51));
        sendButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        sendButton.setForeground(new java.awt.Color(255, 255, 255));
        sendButton.setText("SEND");
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });
        sendPanel.add(sendButton);

        add(sendPanel, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed
        sendMessage();
    }//GEN-LAST:event_sendButtonActionPerformed

    private void fromTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fromTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fromTextFieldActionPerformed

    private void formKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyTyped
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            sendMessage();
        }
    }//GEN-LAST:event_formKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea content;
    private javax.swing.JTextField fromTextField;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton sendButton;
    private javax.swing.JPanel sendPanel;
    private javax.swing.JTextField toTextField;
    // End of variables declaration//GEN-END:variables
}
