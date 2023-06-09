package cc.dobot.crtcpdemo.message.product.cr;

import android.os.Build;

import com.xuhao.didi.core.pojo.OriginalData;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import cc.dobot.crtcpdemo.message.base.BaseMessage;

public class NovaMessageEnableSafeSkin extends BaseMessage {

    private int status=0;
    @Override
    public void constructSendData() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            this.messageContent=("EnableSafeSkin("+status+")").getBytes(StandardCharsets.US_ASCII);
        }else
            this.messageContent=("EnableSafeSkin("+status+")").getBytes( Charset.forName("US-ASCII"));
        this.messageStrContent=("EnableSafeSkin("+status+")");
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
}
