package cc.dobot.crtcpdemo.message.product.cr;

import android.os.Build;

import com.xuhao.didi.core.pojo.OriginalData;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import cc.dobot.crtcpdemo.message.base.BaseMessage;

public class NovaMessageEmergencyStop extends BaseMessage {
    private int value=0;
    private static final String MESSAGE_CONTENT="EmergencyStop()";
    @Override
    public void constructSendData() {
        this.messageStrContent="EmergencyStop("+value+")";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            this.messageContent=messageStrContent.getBytes(StandardCharsets.US_ASCII);
        }else
            this.messageContent=messageStrContent.getBytes( Charset.forName("US-ASCII"));

    }

    @Override
    public void transformReceiveData2Object(OriginalData data) {

        this.messageContent=data.getBodyBytes();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            this.messageStrContent = new String(data.getBodyBytes(), StandardCharsets.US_ASCII);
        }else
            this.messageStrContent = new String(data.getBodyBytes(),Charset.forName("US-ASCII"));

    }

    public void setValue(boolean emergencyStop) {
        this.value = emergencyStop?1:0;

    }

    public boolean getValue() {
        return value==1;
    }
}
