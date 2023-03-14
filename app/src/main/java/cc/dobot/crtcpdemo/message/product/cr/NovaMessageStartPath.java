package cc.dobot.crtcpdemo.message.product.cr;

import android.os.Build;

import com.xuhao.didi.core.pojo.OriginalData;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import cc.dobot.crtcpdemo.message.base.BaseMessage;

public class NovaMessageStartPath extends BaseMessage {

    private String traceName="";
    private int isConst;
    private float multi;
    int user;
    int tool;

    @Override
    public void constructSendData() {
        this.messageStrContent = ("StartPath(\"" + traceName+"\"");
        if (isConst>0)
            this.messageStrContent=this.messageStrContent+",isConst="+isConst;
        if (multi>0)
            this.messageStrContent=this.messageStrContent+",multi="+multi;
        if (user>0)
            this.messageStrContent=this.messageStrContent+",user="+user;
        if (tool>0)
            this.messageStrContent=this.messageStrContent+",tool="+tool;

        this.messageStrContent=this.messageStrContent+")";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            this.messageContent = messageStrContent.getBytes(StandardCharsets.US_ASCII);
        } else
            this.messageContent = messageStrContent.getBytes(Charset.forName("US-ASCII"));
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

    public boolean isConst() {
        return isConst==1;
    }

    public void setIsConst(boolean isConst) {
        this.isConst = isConst?1:0;
        constructSendData();
    }

    public float getMulti() {
        return multi;
    }

    public void setMulti(float multi) {
        this.multi = multi;
        constructSendData();
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
        constructSendData();
    }

    public int getTool() {
        return tool;
    }

    public void setTool(int tool) {
        this.tool = tool;
        constructSendData();
    }
}
