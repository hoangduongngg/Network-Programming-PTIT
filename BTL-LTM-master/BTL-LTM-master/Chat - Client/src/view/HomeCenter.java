/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import core.Client;
import entity.Response;
import entity.Room;
import entity.User;
import flag.ActionFlags;
import flag.ResultFlags;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author APC-LTN
 */
public class HomeCenter extends javax.swing.JFrame implements Observer {

    private Client client;
    private User user;
    private LoginForm loginForm;
    private List<Room> listRoom;
    private List<User> listUser;

    public HomeCenter(Client client, LoginForm loginForm, User user) {
        initComponents();
        this.client = client;
        this.client.addObserver(this);
        this.loginForm = loginForm;
        this.user = user;
    }

    @Override
    public void update(Observable o, Object arg) {
        Response response = (Response) arg;
        if (response.getResultType().equals(ResultFlags.ERROR)) {
            JOptionPane.showMessageDialog(null, response.getContent(), "Thất bại", JOptionPane.ERROR_MESSAGE);
        } else {
            System.out.println(response.getActionType());
            switch (response.getActionType()) {
                case ActionFlags.GET_ALL_USER: {
                    FillListUser((List<User>) response.getEntity());
                    break;
                }
                case ActionFlags.GET_LIST_ROOM: {
                    FillListRoom((List<Room>) response.getEntity());
                    break;
                }
                case ActionFlags.OPEN_ROOM_CHAT:{
                    Room room = (Room) response.getEntity();
                    boolean opened = false;
                    for (Integer roomId : client.userController.listRoomOpened) {
                        if (roomId == room.getId()) {
                            opened = true;
                        }
                    }
                    if (!opened) {
                        client.userController.listRoomOpened.add(room.getId());
                        System.out.println(client.userController.listRoomOpened.size());
                        RoomChat roomChat = new RoomChat(client, room, user);
                        roomChat.setVisible(true);
                    }
                }
            }
        }
    }

    public void FillListUser(List<User> listUser) {
        DefaultTableModel tm = (DefaultTableModel) tbUserOnline.getModel();
        tm.setRowCount(0);
        this.listUser = listUser;
        listUser.forEach(user -> {
            tm.addRow(user.toObjects());
        });
    }

    public void FillListRoom(List<Room> listRoom) {
        DefaultTableModel tm = (DefaultTableModel) tbRooms.getModel();
        tm.setRowCount(0);
        this.listRoom = listRoom;
        listRoom.forEach(room -> {
            tm.addRow(room.toObjects());
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbRooms = new javax.swing.JTable();
        btnLogout = new javax.swing.JButton();
        txtRoomName = new javax.swing.JTextField();
        btnCreateRoom = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbUserOnline = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("List Room");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        tbRooms.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Quantity"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbRooms.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbRoomsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbRooms);

        btnLogout.setText("Log out");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        txtRoomName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRoomNameKeyTyped(evt);
            }
        });

        btnCreateRoom.setText("Create Room");
        btnCreateRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateRoomActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Home ");

        jLabel2.setText("Name");

        tbUserOnline.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Active"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbUserOnline.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbUserOnlineMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbUserOnline);

        jLabel3.setText("Users online");

        jLabel4.setText("Rooms");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(26, 26, 26)
                        .addComponent(txtRoomName, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCreateRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(329, 329, 329)
                        .addComponent(jLabel1)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRoomName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCreateRoom)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCreateRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateRoomActionPerformed
        String roomName = txtRoomName.getText().trim();
        if (roomName.length() == 0) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập tên phòng", "Chưa nhập tên phòng", JOptionPane.WARNING_MESSAGE);
            txtRoomName.requestFocus();
            return;
        }
        txtRoomName.setText("");
        Room room = new Room();
        room.setType("public");
        room.setDescription(roomName);
        client.userController.createRoom(room);
    }//GEN-LAST:event_btnCreateRoomActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        this.setTitle("Xin chào " + user.getDisplayName());
    }//GEN-LAST:event_formWindowOpened

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
        client.userController.logout();
        client.dispose();
        loginForm.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_btnLogoutActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        client.userController.logout();
        client.dispose();
        System.exit(0);
    }//GEN-LAST:event_formWindowClosing

    private void txtRoomNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRoomNameKeyTyped
        // TODO add your handling code here:
//        String after = txtTenPhong.getText() + evt.getKeyChar();
//        after = after.toLowerCase();
//        if (after.contains("<row>") || after.contains("<cotbRooms       evt.consume();
    }//GEN-LAST:event_txtRoomNameKeyTyped

    private void tbRoomsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRoomsMouseClicked
        // TODO add your handling code here:
        int id = tbRooms.getSelectedRow();
        Room room = listRoom.get(id);
        boolean opened = false;
        for (Integer roomId : client.userController.listRoomOpened) {
            if (roomId == room.getId()) {
                opened = true;
            }
        }
        if (!opened) {
            client.userController.listRoomOpened.add(room.getId());
            RoomChat roomChat = new RoomChat(client, room, user);
            roomChat.setVisible(true);
        }
    }//GEN-LAST:event_tbRoomsMouseClicked

    private void tbUserOnlineMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbUserOnlineMouseClicked
        // TODO add your handling code here:
        int id = tbUserOnline.getSelectedRow();
        User user = listUser.get(id);
        client.userController.createOrJoinPrivateRoom(user);
    }//GEN-LAST:event_tbUserOnlineMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreateRoom;
    private javax.swing.JButton btnLogout;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tbRooms;
    private javax.swing.JTable tbUserOnline;
    private javax.swing.JTextField txtRoomName;
    // End of variables declaration//GEN-END:variables
}
