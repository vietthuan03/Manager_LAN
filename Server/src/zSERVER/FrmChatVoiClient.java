package zSERVER;

import PACKAGES.PacketChat;
import PACKAGES.PacketTin;
import UTILS.DataUtils;
import java.net.Socket;

public class FrmChatVoiClient extends javax.swing.JDialog implements Runnable{

    boolean isContinued = true;
    Socket _mayClient;
    PacketChat _pkgChat;
    public FrmChatVoiClient(Socket mayClient) {
        this._mayClient = mayClient;
//        method được tạo tự động bỏi IDE và được sử dụng để khỏi tạo cấu hình của giao diện người dùng(FrmChatVoiClient)
        initComponents();
        setTitle(DataUtils.layTenMay(mayClient) +" ("+
                DataUtils.layIPMay(mayClient)+")");
        setVisible(true);
        _pkgChat = new PacketChat();
    }
     @Override
//    run method chay 1 thread riêng biệt và liên tực nhận tin nhắn từ client thông qua socket
//     Nhận và xử lí tn từ các client khi kết thúc 1 phiên
    public void run() {
        // Đảm bảo sau khi chat xong 1 client
        // còn chat các lần tiếp theo nữa
//        cho đến khi nhận giá trị false, khi kết thúc 1 client vẫn nhận và xử lí client khác
        while(isContinued){
            // Nếu không dùng thread
            // chương trình sẽ chờ nhận dữ liệu client ở đây
            // kết quả chương trình sẽ bị treo do đợi
//            Dùng method nhanDuLieu thông qua 1 đối tượng _mayClient
            String msg = DataUtils.nhanDuLieu(_mayClient);
//          kiểm tra xem 1 tin nhắn từ client nhận giá trị khác null và không rỗng hay không
            if(msg != null && !msg.isEmpty()){
                hienThiMessage(msg);
            }
        }
    }

    private void hienThiMessage(String msg){
//  object
        PacketTin pkgTin = new PacketTin();
//        gọi method phaTichMessage để phân tích và xử lí tin
        pkgTin.phanTichMessage(msg);
        if(pkgTin.isId(PacketChat.ID)){
            txtMessages.append(DataUtils.layTenMay(_mayClient)+": "
                    +pkgTin.getMessage()+"\n");
        }
    }
   
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtMessages = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtInput = new javax.swing.JTextArea();
        btnSend = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Chat với client");
        setType(java.awt.Window.Type.POPUP);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        txtMessages.setEditable(false);
        txtMessages.setColumns(20);
        txtMessages.setRows(5);
        jScrollPane1.setViewportView(txtMessages);

        txtInput.setColumns(20);
        txtInput.setRows(5);
        jScrollPane2.setViewportView(txtInput);

        btnSend.setForeground(new java.awt.Color(255, 0, 0));
        btnSend.setText("GỬI");
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(51, 51, 255));
        jLabel1.setText("SERVER");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSend)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(4, 4, 4)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnSend, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        // Initialize _pkgChat with an empty sender and the text from the txtInput field
    _pkgChat.khoiTao("", txtInput.getText());
    
    // Append the message to the txtMessages field, indicating it's from the server
    txtMessages.append("Server: " + txtInput.getText() + "\n");
    
    // Clear the input field (txtInput)
    txtInput.setText("");
    
    // Send the data from _pkgChat to the client using DataUtils.goiDuLieu
    DataUtils.goiDuLieu(_mayClient, _pkgChat.toString());
    }//GEN-LAST:event_btnSendActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
       isContinued = false;
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSend;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea txtInput;
    private javax.swing.JTextArea txtMessages;
    // End of variables declaration//GEN-END:variables

    
}
