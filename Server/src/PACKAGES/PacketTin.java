package PACKAGES;


public class PacketTin {

    private String id;
    private String cmd;
    private String message;

    public PacketTin() {
    }

    public boolean isId(String id) {
        return this.id.equals(id);
    }
    
    public boolean isCmd(String id) {
        return this.cmd.equals(id);
    }

    public void khoiTao(String cmd, String msg) {
        setCmd(cmd);
        setMessage(msg);
    }
// Được sử dụng để chuyển đổi một chuỗi đầu vào một định dạng cụ thể thành từng thành phần riêng lẻ.
    public void phanTichMessage(String strReceived) {
        // Gói tin dạng: id:chatclient###cmd:start###msg:Hello World!
        // Lấy 3 phần đầu của gói cách nhau bởi ###
        String[] msgSplit = strReceived.split("###", 3);
        if (msgSplit.length != 3) {
            id = message = cmd = "";
            return;
        }
        // Lấy ID từ phần thứ nhất của gói
//        trim loại bỏ khoảng trắng
        String idPkg = msgSplit[0].trim();
        if (idPkg.startsWith("id:")) {
//            loại bỏ id của phần tử đầu là id
            id = idPkg.replaceFirst("id:", "");
        } else {
            id = "inf";
        }
        // Lấy command từ phần tứ 2 của gói
        String cmdPkg = msgSplit[1].trim();
        if (cmdPkg.startsWith("cmd:")) {
            cmd = cmdPkg.replaceFirst("cmd:", "");
        } else {
            cmd = "";
        }
        // Lấy message từ phần tứ 3 của gói
        String msgPkg = msgSplit[2].trim();
        if (msgPkg.startsWith("msg:")) {
            message = msgPkg.replaceFirst("msg:", "");
        } else {
            message = "";
        }
    }

    @Override
    public String toString() {
        return String.format("id:%s###cmd:%s###msg:%s", getId(), getCmd(), getMessage());
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the cmd
     */
    public String getCmd() {
        return cmd;
    }

    /**
     * @param cmd the cmd to set
     */
    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
