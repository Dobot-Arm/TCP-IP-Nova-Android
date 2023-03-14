package cc.dobot.crtcpdemo.message.product.cr;

import android.os.Build;

import com.xuhao.didi.core.pojo.OriginalData;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import cc.dobot.crtcpdemo.message.base.BaseMessage;

public class NovaMessageBrakeControl extends BaseMessage {

    private int axisID=1;
    private int value=0;
    @Override
    public void constructSendData() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            this.messageContent=("BrakeControl("+axisID+","+value+")").getBytes(StandardCharsets.US_ASCII);
        }else
            this.messageContent=("BrakeControl("+axisID+","+value+")").getBytes( Charset.forName("US-ASCII"));
        this.messageStrContent=("BrakeControl("+axisID+","+value+")");
    }

    @Override
    public void transformReceiveData2Object(OriginalData data) {

        this.messageContent=data.getBodyBytes();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            this.messageStrContent = new String(data.getBodyBytes(), StandardCharsets.US_ASCII);
        }else
            this.messageStrContent = new String(data.getBodyBytes(),Charset.forName("US-ASCII"));
    }

    public int getAxisID() {
        return axisID;
    }

    public void setAxisID(int axisID) {
        this.axisID = axisID;
        constructSendData();
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        constructSendData();
    }
}
