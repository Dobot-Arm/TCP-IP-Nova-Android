package cc.dobot.crtcpdemo.message.product.cr;

import android.os.Build;

import com.xuhao.didi.core.pojo.OriginalData;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import cc.dobot.crtcpdemo.message.base.BaseMessage;

public class NovaMessageMoveJog extends BaseMessage {

    private String axisID = "";
    private boolean isStop = false;
    int coordtype=-1;
    int user;
    int tool;

    @Override
    public void constructSendData() {
        if (isStop) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                this.messageContent="MoveJog()".getBytes(StandardCharsets.US_ASCII);
            }else
                this.messageContent="MoveJog()".getBytes( Charset.forName("US-ASCII"));
            this.messageStrContent="MoveJog()";
        } else {
            this.messageStrContent = ("MoveJog(" + axisID+"");
            if (user>=0)
                this.messageStrContent=this.messageStrContent+",user="+user;
            if (tool>=0)
                this.messageStrContent=this.messageStrContent+",tool="+tool;
            if (coordtype>=0)
                this.messageStrContent=this.messageStrContent+",coordtype="+coordtype;
            this.messageStrContent=this.messageStrContent+")";
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                this.messageContent = messageStrContent.getBytes(StandardCharsets.US_ASCII);
            } else
                this.messageContent = messageStrContent.getBytes(Charset.forName("US-ASCII"));
        }
    }

    @Override
    public void transformReceiveData2Object(OriginalData data) {

        this.messageContent = data.getBodyBytes();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            this.messageStrContent = new String(data.getBodyBytes(), StandardCharsets.US_ASCII);
        } else
            this.messageStrContent = new String(data.getBodyBytes(), Charset.forName("US-ASCII"));
    }

    public String getAxisID() {
        return axisID;
    }

    public void setAxisID(String axisID) {
        this.axisID = axisID;
        constructSendData();
    }

    public boolean isStop() {
        return isStop;
    }

    public void setStop(boolean stop) {
        isStop = stop;
        constructSendData();
    }

    public int getCoordtype() {
        return coordtype;
    }

    public void setCoordtype(int coordtype) {
        this.coordtype = coordtype;
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
