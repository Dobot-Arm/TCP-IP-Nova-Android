package cc.dobot.crtcpdemo.message.product.cr;

import android.os.Build;

import com.xuhao.didi.core.pojo.OriginalData;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import cc.dobot.crtcpdemo.message.base.BaseMessage;

public class NovaMessageToolAI extends BaseMessage {

    private int index=1;
    @Override
    public void constructSendData() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            this.messageContent=("ToolAI("+index+")").getBytes(StandardCharsets.US_ASCII);
        }else
            this.messageContent=("ToolAI("+index+")").getBytes( Charset.forName("US-ASCII"));
        this.messageStrContent=("ToolAI("+index+")");
    }

    @Override
    public void transformReceiveData2Object(OriginalData data) {

        this.messageContent=data.getBodyBytes();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            this.messageStrContent = new String(data.getBodyBytes(), StandardCharsets.US_ASCII);
        }else
            this.messageStrContent = new String(data.getBodyBytes(),Charset.forName("US-ASCII"));
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index) {
        this.index = index;
        constructSendData();
    }
}

