package cc.dobot.crtcpdemo.message.product.cr;

import android.os.Build;

import com.xuhao.didi.core.pojo.OriginalData;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import cc.dobot.crtcpdemo.message.base.BaseMessage;

public class NovaMessageSetSafeSkin extends BaseMessage {

    private int part;
    private int status=1;
    @Override
    public void constructSendData() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            this.messageContent=("SetSafeSkin("+part+","+status+")").getBytes(StandardCharsets.US_ASCII);
        }else
            this.messageContent=("SetSafeSkin("+part+","+status+")").getBytes( Charset.forName("US-ASCII"));
        this.messageStrContent=("SetSafeSkin("+part+","+status+")");
    }

    @Override
    public void transformReceiveData2Object(OriginalData data) {

        this.messageContent=data.getBodyBytes();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            this.messageStrContent = new String(data.getBodyBytes(), StandardCharsets.US_ASCII);
        }else
            this.messageStrContent = new String(data.getBodyBytes(),Charset.forName("US-ASCII"));
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
        constructSendData();
    }

    public NovaMessageSetSafeSkin(int part) {
        this.part = part;
        constructSendData();
    }

    public int getPart() {
        return part;
    }
}
