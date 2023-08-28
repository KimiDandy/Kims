package Admin;

import Classes.Config;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import notification.MessageDialog;
import notification.Notification;

/**
 *
 * @author Kimi DandY
 */
public class Admin_karyawan extends javax.swing.JFrame {
    
    private int cmb = 0;
    private int edit = 0;
    private int a;
    public static boolean dialog = false;
    boolean formedit = true; 
    public static String akses;
    

    class NoSpecialCharacterDocumentFilterNumber extends DocumentFilter {
    @Override
    public void insertString(DocumentFilter.FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (!Character.isDigit(ch)) {
                return;
            }
        }
        super.insertString(fb, offset, text, attr);
    }

    @Override
    public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (!Character.isDigit(ch)) {
                return;
            }
        }
        super.replace(fb, offset, length, text, attrs);
    }
    }
    
    class NoSpecialCharacterDocumentFilter extends DocumentFilter {
    @Override
    public void insertString(DocumentFilter.FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (!Character.isLetterOrDigit(ch)) {
                return;
            }
        }
        super.insertString(fb, offset, text, attr);
    }

    @Override
    public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (!Character.isLetterOrDigit(ch)) {
                return;
            }
        }
        super.replace(fb, offset, length, text, attrs);
    }
    }
    
    class NoSpecialCharacterDocumentFilterSpace extends DocumentFilter {
    @Override
    public void insertString(DocumentFilter.FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (!Character.isLetterOrDigit(ch) &&  ch != ' ' && ch != '.' && ch != ',') {
                return;
            }
        }
        super.insertString(fb, offset, text, attr);
    }

    @Override
    public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (!Character.isLetterOrDigit(ch) &&  ch != ' ' && ch != '.' && ch != ',') {
                return;
            }
        }
        super.replace(fb, offset, length, text, attrs);
    }
    }
    
    class NoSpecialCharacterDocumentFilterName extends DocumentFilter {
    @Override
    public void insertString(DocumentFilter.FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (!Character.isLetter(ch) &&  ch != ' ' && ch != '.') {
                return;
            }
        }
        super.insertString(fb, offset, text, attr);
    }

    @Override
    public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (!Character.isLetter(ch) &&  ch != ' ' && ch != '.') {
                return;
            }
        }
        super.replace(fb, offset, length, text, attrs);
    }
    }
    
    
    public void validasichar(){
            
            ((AbstractDocument) usernametf.getDocument()).setDocumentFilter(new NoSpecialCharacterDocumentFilter());
            ((AbstractDocument) passwordtf.getDocument()).setDocumentFilter(new NoSpecialCharacterDocumentFilter());
            ((AbstractDocument) namatf.getDocument()).setDocumentFilter(new NoSpecialCharacterDocumentFilterName());
            ((AbstractDocument) alamattf.getDocument()).setDocumentFilter(new NoSpecialCharacterDocumentFilterSpace());
            ((AbstractDocument) nohptf.getDocument()).setDocumentFilter(new NoSpecialCharacterDocumentFilterNumber());
            
            usernametf.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (usernametf.getText().length() >= 20) {
                    e.consume();
                    Notification panel = new Notification(Admin_karyawan.this, Notification.Type.INFO,
                    Notification.Location.
                    TOP_CENTER, "Username terlalu panjang");
                    panel.showNotification();
                }
            }
        });
            
            passwordtf.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (passwordtf.getText().length() >= 20) {
                    e.consume();
                    Notification panel = new Notification(Admin_karyawan.this, Notification.Type.INFO,
                    Notification.Location.
                    TOP_CENTER, "Password terlalu panjang");
                    panel.showNotification();
                }
            }
        });
            
            namatf.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (namatf.getText().length() >= 30) {
                    e.consume();
                    Notification panel = new Notification(Admin_karyawan.this, Notification.Type.INFO,
                    Notification.Location.
                    TOP_CENTER, "Nama terlalu panjang");
                    panel.showNotification();
                }
            }
        });
            
            alamattf.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (alamattf.getText().length() >= 50) {
                    e.consume();
                    Notification panel = new Notification(Admin_karyawan.this, Notification.Type.INFO,
                    Notification.Location.
                    TOP_CENTER, "Alamat terlalu panjang");
                    panel.showNotification();
                }
            }
        });
            
            nohptf.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                String text = nohptf.getText();
                
                if (nohptf.getText().length() > 13) {
                    e.consume();
                    Notification panel = new Notification(Admin_karyawan.this, Notification.Type.INFO,
                    Notification.Location.
                    TOP_CENTER, "No HP terlalu panjang");
                    panel.showNotification();
                }
            }
        });
            
            
    }
    
    public void cmbKaryawan(){
        try{
        String sql = "select nama_user from user order by nama_user asc";
        java.sql.Connection conn=(Connection)Config.GetConnection();
        java.sql.PreparedStatement pst=conn.prepareStatement(sql);
        ResultSet res = pst.executeQuery(sql);
        while(res.next()){
            
            cmbkaryawan.addItem(res.getString("nama_user"));
        }
        res.last(); 
        int jumlahdata = res.getRow();
        res.first();
        }catch (Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    public void dataUserCmb() throws SQLException{       
        String sql = "select kd_user, nama_user, username, password, level, alamat, nohp from user where nama_user = '" 
                + cmbkaryawan.getSelectedItem() + "';";
        java.sql.Connection conn=(Connection)Config.GetConnection();
        java.sql.PreparedStatement pst=conn.prepareStatement(sql);
        ResultSet res = pst.executeQuery(sql);
        while(res.next()){
            kdtf.setText(res.getString("kd_user"));
            namatf.setText(res.getString("nama_user"));
            usernametf.setText(res.getString("username"));
            passwordtf.setText(res.getString("password"));
            leveltf.setText(res.getString("level"));
            alamattf.setText(res.getString("alamat"));
            nohptf.setText(res.getString("nohp"));         
        }
    }
    
    public void dataUserStand() throws SQLException{       
        String sql = "select kd_user, nama_user, username, password, level, alamat, "
                + "nohp from user where kd_user = '" + Login.id.getText() + "';";
        java.sql.Connection conn=(Connection)Config.GetConnection();
        java.sql.PreparedStatement pst=conn.prepareStatement(sql);
        ResultSet res = pst.executeQuery(sql);
        while(res.next()){
            kdtf.setText(res.getString("kd_user"));
            namatf.setText(res.getString("nama_user"));
            usernametf.setText(res.getString("username"));
            passwordtf.setText(res.getString("password"));
            leveltf.setText(res.getString("level"));
            alamattf.setText(res.getString("alamat"));
            nohptf.setText(res.getString("nohp"));         
        }
    }
    
    public void editUser(){
        try{
            String sql = "update user set nama_user = '" + namatf.getText() + "', username = '" + usernametf.getText() 
                    + "', password = '" + passwordtf.getText() + "', nohp = '" + nohptf.getText() + "', alamat = '" + alamattf.getText() + "' where kd_user = '" + kdtf.getText() + "';";
            java.sql.Connection conn = (Connection)Config.GetConnection();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();                   
        } catch (Exception e){
            System.out.println("method edit karyawan gagal");
        }       
    }
    
    public void hapusUser() throws SQLException{
        String sql = "delete from user where kd_user = '" + kdtf.getText() + "';";
        String sqll = "delete from user_login where kd_user = '" + kdtf.getText() + "';";
        java.sql.Connection conn = (Connection)Config.GetConnection();
        java.sql.PreparedStatement pst = conn.prepareStatement(sql);
        java.sql.PreparedStatement pstt = conn.prepareStatement(sqll);
        pst.execute();
        pstt.execute();    
    }
    
    public void panel(){
        if (Login.status.getText().equals("Admin")){
            paneldatakaryawan.setVisible(false);
        } else if (Login.status.getText().equals("Pemilik Stand")){
            tambahbtn.setEnabled(false);
        }
    }
    
    public void exit(){
        MessageDialog obj = new MessageDialog(this);
                obj.action = "login";
                obj.showMessage("Keluar Dari Aplikasi?","Pilih OK untuk keluar dari aplikasi");
    }

    public Admin_karyawan() {
        initComponents();
        if (Login.status.getText().equals("Pemilik Stand")){
            cmbkaryawan.setVisible(false);
        }
        panel();
        validasichar();
        cmbKaryawan();
        namatf.setBackground(new Color(158,194,198,0));
        usernametf.setBackground(new Color(158,194,198,0));
        passwordtf.setBackground(new Color(158,194,198,0));
        nohptf.setBackground(new Color(158,194,198,0));
        alamattf.setBackground(new Color(158,194,198,0));
        leveltf.setBackground(new Color(158,194,198,0));
        
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exit();
            }
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

        kdtf = new javax.swing.JTextField();
        namatf = new javax.swing.JTextField();
        usernametf = new javax.swing.JTextField();
        passwordtf = new javax.swing.JTextField();
        leveltf = new javax.swing.JTextField();
        nohptf = new javax.swing.JTextField();
        alamattf = new javax.swing.JTextArea();
        paneldatakaryawan = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cmbkaryawan = new notification.Combobox1();
        background = new javax.swing.JLabel();
        editbtn = new javax.swing.JButton();
        tambahbtn = new javax.swing.JButton();
        standmenubtn = new javax.swing.JButton();
        laporanbtn = new javax.swing.JButton();
        riwayatlogin = new javax.swing.JButton();
        kembalibtn = new javax.swing.JButton();
        btnhapus = new javax.swing.JButton();

        kdtf.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        namatf.setEditable(false);
        namatf.setBackground(new java.awt.Color(158, 194, 198));
        namatf.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        namatf.setForeground(new java.awt.Color(34, 87, 126));
        namatf.setBorder(null);
        namatf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namatfActionPerformed(evt);
            }
        });
        getContentPane().add(namatf, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 440, 360, 40));

        usernametf.setEditable(false);
        usernametf.setBackground(new java.awt.Color(158, 194, 198));
        usernametf.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        usernametf.setForeground(new java.awt.Color(34, 87, 126));
        usernametf.setBorder(null);
        getContentPane().add(usernametf, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 540, 350, 40));

        passwordtf.setEditable(false);
        passwordtf.setBackground(new java.awt.Color(158, 194, 198));
        passwordtf.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        passwordtf.setForeground(new java.awt.Color(34, 87, 126));
        passwordtf.setBorder(null);
        getContentPane().add(passwordtf, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 640, 350, 40));

        leveltf.setEditable(false);
        leveltf.setBackground(new java.awt.Color(158, 194, 198));
        leveltf.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        leveltf.setForeground(new java.awt.Color(34, 87, 126));
        leveltf.setBorder(null);
        leveltf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leveltfActionPerformed(evt);
            }
        });
        getContentPane().add(leveltf, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 640, 350, 40));

        nohptf.setEditable(false);
        nohptf.setBackground(new java.awt.Color(158, 194, 198));
        nohptf.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        nohptf.setForeground(new java.awt.Color(34, 87, 126));
        nohptf.setBorder(null);
        nohptf.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                nohptfFocusLost(evt);
            }
        });
        getContentPane().add(nohptf, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 730, 350, 40));

        alamattf.setColumns(20);
        alamattf.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        alamattf.setRows(5);
        alamattf.setBorder(null);
        alamattf.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                alamattfFocusLost(evt);
            }
        });
        getContentPane().add(alamattf, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 430, 350, 130));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(34, 87, 126));
        jLabel1.setText("Data Karyawan");
        paneldatakaryawan.add(jLabel1);

        getContentPane().add(paneldatakaryawan, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 260, 420, 80));

        cmbkaryawan.setBackground(new java.awt.Color(255, 244, 189));
        cmbkaryawan.setForeground(new java.awt.Color(34, 87, 126));
        cmbkaryawan.setFont(new java.awt.Font("Dialog", 3, 19)); // NOI18N
        cmbkaryawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbkaryawanActionPerformed(evt);
            }
        });
        getContentPane().add(cmbkaryawan, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 270, 330, 60));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image_fix/DETAIL DATA KARYAWAN BARU (1).png"))); // NOI18N
        getContentPane().add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 880));

        editbtn.setText("Edit");
        editbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editbtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                editbtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                editbtnMouseExited(evt);
            }
        });
        editbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editbtnActionPerformed(evt);
            }
        });
        getContentPane().add(editbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 270, 130, 60));

        tambahbtn.setText("jButton1");
        tambahbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahbtnActionPerformed(evt);
            }
        });
        getContentPane().add(tambahbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 270, 50, 50));

        standmenubtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                standmenubtnActionPerformed(evt);
            }
        });
        getContentPane().add(standmenubtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 350, 150, 240));

        laporanbtn.setText("jButton1");
        laporanbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                laporanbtnActionPerformed(evt);
            }
        });
        getContentPane().add(laporanbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 590, 150, 280));

        riwayatlogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                riwayatloginActionPerformed(evt);
            }
        });
        getContentPane().add(riwayatlogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 270, 240, 60));

        kembalibtn.setText("jButton1");
        kembalibtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kembalibtnActionPerformed(evt);
            }
        });
        getContentPane().add(kembalibtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 120, 50));

        btnhapus.setText("jButton1");
        btnhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapusActionPerformed(evt);
            }
        });
        getContentPane().add(btnhapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 270, 130, 60));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void editbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editbtnActionPerformed
      
        if ( edit == 0 || edit == 1){
            a = -1;
        } else {
            a = edit % 2;
        }   
        
        if (cmb != 0){
            if (edit == 0 || a == 0){                
                namatf.setEditable(true);
                usernametf.setEditable(true);
                passwordtf.setEditable(true);
                alamattf.setEditable(true);
                nohptf.setEditable(true);
            
                edit++;
                editbtn.setBackground(Color.BLUE);        
            } else if (edit != 0 && a != 0){
                if(namatf.getText().isEmpty()){
                    Notification panel = new Notification(this, Notification.Type.INFO,
                Notification.Location.
                TOP_CENTER, "Data tidak boleh kosong");
                panel.showNotification();                    
                } else if (usernametf.getText().isEmpty()){
                    Notification panel = new Notification(this, Notification.Type.INFO,
                Notification.Location.
                TOP_CENTER, "Data tidak boleh kosong");
                panel.showNotification(); 
                    
                } else if (passwordtf.getText().isEmpty()){
                    Notification panel = new Notification(this, Notification.Type.INFO,
                Notification.Location.
                TOP_CENTER, "Data tidak boleh kosong");
                panel.showNotification();
                    
                } else if (alamattf.getText().isEmpty()){
                    Notification panel = new Notification(this, Notification.Type.INFO,
                Notification.Location.
                TOP_CENTER, "Data tidak boleh kosong");
                panel.showNotification();
                    
                } else if (nohptf.getText().isEmpty()){
                    Notification panel = new Notification(this, Notification.Type.INFO,
                Notification.Location.
                TOP_CENTER, "Data tidak boleh kosong");
                panel.showNotification();
                    
                } else {
                    if(formedit == true){
                        MessageDialog obj = new MessageDialog(this);
            obj.action = "edit karyawan";            
            obj.showMessage("Yakin Mengubah Data User?","Pilih OK mengubah data user");
            
            if (dialog == true){
                editUser();
                Notification panel = new Notification(this, Notification.Type.SUCCESS,
                Notification.Location.
                TOP_CENTER, "Berhasil Edit Data User");
                panel.showNotification();
            }
            
                namatf.setEditable(false);
                usernametf.setEditable(false);
                passwordtf.setEditable(false);
                alamattf.setEditable(false);
                nohptf.setEditable(false);
                
                edit ++;            
                cmbkaryawan.removeAllItems();
                cmbKaryawan();
                        
                    } else if (formedit == false){
                        Notification panel = new Notification(this, Notification.Type.INFO,
                Notification.Location.
                TOP_CENTER, "Data user tidak valid");
                panel.showNotification();
                        
                    }
                    
                    
                }
                
                
            }           
        }       
    }//GEN-LAST:event_editbtnActionPerformed

    private void kembalibtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kembalibtnActionPerformed
    dispose();
    new Login().setVisible(true);
    Notification panel = new Notification(this, Notification.Type.SUCCESS,
                            Notification.Location.
                            TOP_CENTER, "Berhasil Log Out");
                    panel.showNotification();
    
    
    }//GEN-LAST:event_kembalibtnActionPerformed

    private void tambahbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahbtnActionPerformed
        dispose();
        new Karyawan_Register().setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_tambahbtnActionPerformed

    private void editbtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editbtnMouseEntered

    }//GEN-LAST:event_editbtnMouseEntered

    private void editbtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editbtnMouseExited

    }//GEN-LAST:event_editbtnMouseExited

    private void editbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editbtnMouseClicked

    }//GEN-LAST:event_editbtnMouseClicked

    private void riwayatloginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_riwayatloginActionPerformed
        dispose();
        new Karyawan_Riwayat().setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_riwayatloginActionPerformed

    private void standmenubtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_standmenubtnActionPerformed
        if (Login.status.getText().equals("Admin")){
            akses = "Admin";
            dispose();
            new Admin_stand().setVisible(true);            
        } else if (Login.status.getText().equals("Pemilik Stand")){
            akses = "Stand";
            dispose();
            new Stand_Menu().setVisible(true);  
        }
        

        // TODO add your handling code here:
    }//GEN-LAST:event_standmenubtnActionPerformed

    private void laporanbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_laporanbtnActionPerformed
        dispose();
        try {
            new Admin_laporan().setVisible(true);
            
            // TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(Admin_karyawan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_laporanbtnActionPerformed

    private void namatfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namatfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namatfActionPerformed

    private void btnhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapusActionPerformed
        try{    
            MessageDialog obj = new MessageDialog(this);
            obj.action = "hapus karyawan";            
            obj.showMessage("Yakin Menghapus Data User?","Pilih OK untuk menghapus data user");
            
            if (dialog == true){
                    String sql1 = "delete a from user_login as a join user as b on a.kd_user = "
                            + "b.kd_user where nama_user = '" + namatf.getText() +"';";
                    String sql2 = "delete from user where nama_user = '" + namatf.getText() +"';";
                    java.sql.Connection conn = (Connection)Config.GetConnection();
                    java.sql.PreparedStatement pst1 = conn.prepareStatement(sql1);
                    java.sql.PreparedStatement pst2 = conn.prepareStatement(sql2);
                    pst1.execute();
                    pst2.execute();
                    cmbkaryawan.removeAllItems();
                    cmbKaryawan();
                    
                    Notification panel = new Notification(this, Notification.Type.SUCCESS,
                    Notification.Location.
                    TOP_CENTER, "Berhasil Hapus Data User");
                    panel.showNotification();
            }
            
                    
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        

        // TODO add your handling code here:
    }//GEN-LAST:event_btnhapusActionPerformed

    private void cmbkaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbkaryawanActionPerformed
        if (cmb == 0){
            cmb++;
        }
        if (Login.status.getText().equals("Admin")){
            try {
            dataUserCmb();
        } catch (SQLException ex) {
            Logger.getLogger(Admin_karyawan.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        } else if (Login.status.getText().equals("Pemilik Stand")){
            try {
            dataUserStand();
        } catch (SQLException ex) {
            Logger.getLogger(Admin_karyawan.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        

        // TODO add your handling code here:
    }//GEN-LAST:event_cmbkaryawanActionPerformed

    private void leveltfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leveltfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_leveltfActionPerformed

    private void nohptfFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nohptfFocusLost
        String text = nohptf.getText();
        
        if (nohptf.getText().length() < 12){
           
            Notification panel = new Notification(Admin_karyawan.this, Notification.Type.INFO,
                    Notification.Location.
                    TOP_CENTER, "Mohon isi No HP dengan benar");
                    panel.showNotification();
                    nohptf.requestFocus();
                    formedit = false;
            
        } else if (text.charAt(0) != '0' || text.charAt(1) != '8'){
                    Notification panel = new Notification(Admin_karyawan.this, Notification.Type.INFO,
                    Notification.Location.
                    TOP_CENTER, "Mohon isi No HP dengan benar");
                    panel.showNotification();
                    nohptf.requestFocus();
                    formedit = false;
        } else if (nohptf.getText().length() >= 12 && text.charAt(0) == '0' && text.charAt(1) == '8'){
            formedit = true;
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_nohptfFocusLost

    private void alamattfFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_alamattfFocusLost
        if (alamattf.getText().length() < 10){
            Notification panel = new Notification(Admin_karyawan.this, Notification.Type.INFO,
                    Notification.Location.
                    TOP_CENTER, "Mohon isi alamat dengan benar");
                    panel.showNotification();
                    alamattf.requestFocus();
                    formedit = false;
        } else if (alamattf.getText().length() >= 10){
            formedit = true;
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_alamattfFocusLost

    /**
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
            java.util.logging.Logger.getLogger(Admin_karyawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin_karyawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin_karyawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin_karyawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin_karyawan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea alamattf;
    private javax.swing.JLabel background;
    private javax.swing.JButton btnhapus;
    private notification.Combobox1 cmbkaryawan;
    private javax.swing.JButton editbtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField kdtf;
    private javax.swing.JButton kembalibtn;
    private javax.swing.JButton laporanbtn;
    private javax.swing.JTextField leveltf;
    private javax.swing.JTextField namatf;
    private javax.swing.JTextField nohptf;
    private javax.swing.JPanel paneldatakaryawan;
    private javax.swing.JTextField passwordtf;
    private javax.swing.JButton riwayatlogin;
    private javax.swing.JButton standmenubtn;
    private javax.swing.JButton tambahbtn;
    private javax.swing.JTextField usernametf;
    // End of variables declaration//GEN-END:variables
}
