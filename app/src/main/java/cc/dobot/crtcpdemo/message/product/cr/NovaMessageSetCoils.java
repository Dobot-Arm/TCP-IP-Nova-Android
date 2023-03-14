package cc.dobot.crtcpdemo.message.product.cr;

import android.os.Build;

import com.xuhao.didi.core.pojo.OriginalData;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import cc.dobot.crtcpdemo.message.base.BaseMessage;

public class NovaMessageSetCoils extends BaseMessage {

    private int index=1;
    private int addr=3095;
    private int count=1;
    private String varTab="";
    @Override
    public void constructSendData() {
        this.messageStrContent=("SetCoils("+index+","+addr+","+count);
        if (varTab!=null)
        {
            this.messageStrContent=this.messageStrContent+","+varTab+")";
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
        return index;
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

    public String getVarTab() {
        return this.varTab;
    }

    public void setVarTab(String varTab) {
        this.varTab = varTab;
        constructSendData();
    }
}
