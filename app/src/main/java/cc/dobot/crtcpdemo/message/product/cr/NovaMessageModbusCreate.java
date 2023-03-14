package cc.dobot.crtcpdemo.message.product.cr;

import android.os.Build;

import com.xuhao.didi.core.pojo.OriginalData;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import cc.dobot.crtcpdemo.message.base.BaseMessage;

public class NovaMessageModbusCreate extends BaseMessage {

    private int slaveId=1;
    private int port=3095;
    private String ip;
    private int isRTU=-1;
    @Override
    public void constructSendData() {
        this.messageStrContent="SetHoldRegs(\""+ip+"\","+port+","+slaveId+(isRTU==0||isRTU==1?","+isRTU:"")+")";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            this.messageContent= this.messageStrContent.getBytes(StandardCharsets.US_ASCII);
        }else
            this.messageContent= this.messageStrContent.getBytes( Charset.forName("US-ASCII"));

    }

    @Override
    public void transformReceiveData2Object(OriginalData data) {

        this.messageContent=data.getBodyBytes();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            this.messageStrContent = new String(data.getBodyBytes(), StandardCharsets.US_ASCII);
        }else
            this.messageStrContent = new String(data.getBodyBytes(),Charset.forName("US-ASCII"));
    }

    public int getSlaveId() {
        return slaveId;
    }

    public void setSlaveId(int slaveId) {
        this.slaveId = slaveId;
        constructSendData();
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
        constructSendData();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
        constructSendData();
    }

    public int getIsRTU() {
        return isRTU;
    }

    public void setIsRTU(int isRTU) {
        this.isRTU = isRTU;
        constructSendData();
    }
}
