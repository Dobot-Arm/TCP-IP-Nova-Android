package cc.dobot.crtcpdemo.message.product.cr;

import android.os.Build;

import com.xuhao.didi.core.pojo.OriginalData;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import cc.dobot.crtcpdemo.message.base.BaseMessage;

public class NovaMessageGetInRegs extends BaseMessage {

    private int index=1;
    private int addr=3095;
    private int count=1;
    private String type=null;
    @Override
    public void constructSendData() {
        this.messageStrContent=("GetInRegs("+index+","+addr+","+count);
        if (type!=null)
        {
            this.messageStrContent=this.messageStrContent+",\""+type+"\")";
        }else
        {
            this.messageStrContent=this.messageStrContent+")";
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

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index) {
        this.index = index;
        constructSendData();
    }

    public int getAddr() {
        return addr;
    }

    public void setAddr(int addr) {
        this.addr = addr;
        constructSendData();
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
        constructSendData();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
        constructSendData();
    }
}
