package cc.dobot.crtcpdemo.message.product.cr;

import android.os.Build;

import com.xuhao.didi.core.pojo.OriginalData;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import cc.dobot.crtcpdemo.message.base.BaseMessage;

public class NovaMessageGetStartPose extends BaseMessage {
    private static final String MESSAGE_CONTENT="GetPathStartPose()";
    private String traceName="";
    @Override
    public void constructSendData() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            this.messageContent=("GetPathStartPose(\""+traceName+"\")").getBytes(StandardCharsets.US_ASCII);
        }else
            this.messageContent=("GetPathStartPose(\""+traceName+"\")").getBytes( Charset.forName("US-ASCII"));
        this.messageStrContent=("GetPathStartPose(\""+traceName+"\")");
    }

    @Override
    public void transformReceiveData2Object(OriginalData data) {
        this.messageContent=data.getBodyBytes();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            this.messageStrContent = new String(data.getBodyBytes(), StandardCharsets.US_ASCII);
        }else
            this.messageStrContent = new String(data.getBodyBytes(),Charset.forName("US-ASCII"));

    }

    public String getTraceName() {
        return traceName;
    }

    public void setTraceName(String traceName) {
        this.traceName = traceName;
        constructSendData();
    }
}
