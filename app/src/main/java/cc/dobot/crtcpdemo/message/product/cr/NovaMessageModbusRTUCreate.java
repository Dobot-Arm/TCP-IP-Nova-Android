package cc.dobot.crtcpdemo.message.product.cr;

import android.os.Build;
import android.text.TextUtils;

import com.xuhao.didi.core.pojo.OriginalData;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import cc.dobot.crtcpdemo.message.base.BaseMessage;

public class NovaMessageModbusRTUCreate extends BaseMessage {

    private int slaveId=1;
    private int baud=3095;
    private String parity;
    private int dataBit=7;
    private int stopBit=1;
    @Override
    public void constructSendData() {
        if (TextUtils.isEmpty(parity))
        this.messageStrContent="SetHoldRegs("+slaveId+","+baud+")";
       else
        {
            this.messageStrContent="SetHoldRegs("+slaveId+","+baud+",\""+parity+"\""+dataBit+","+stopBit+")";
        }
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

    public int getBaud() {
        return baud;
    }

    public void setBaud(int baud) {
        this.baud = baud;
        constructSendData();
    }

    public String getParity() {
        return parity;
    }

    public void setParity(String parity) {
        this.parity = parity;
        constructSendData();
    }

    public int getDataBit() {
        return dataBit;
    }

    public void setDataBit(int dataBit) {
        this.dataBit = dataBit;
        constructSendData();
    }

    public int getStopBit() {
        return stopBit;

    }

    public void setStopBit(int stopBit) {
        this.stopBit = stopBit;
        constructSendData();
    }
}
