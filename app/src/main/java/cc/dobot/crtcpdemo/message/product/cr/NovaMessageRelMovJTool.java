package cc.dobot.crtcpdemo.message.product.cr;

import android.os.Build;

import com.xuhao.didi.core.pojo.OriginalData;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import cc.dobot.crtcpdemo.message.base.BaseMessage;

public class NovaMessageRelMovJTool extends BaseMessage {
    double x, y, z, rx, ry, rz;
    int user;
    int tool;
    int a;
    int v;
    int cp;


    @Override
    public void constructSendData() {
            this.messageStrContent = ("RelMovJTool(" + x + "," + y + "," + z + "," + rx + "," + ry + "," + rz );
        if (user>0)
            this.messageStrContent=this.messageStrContent+",user="+user;
        if (tool>0)
            this.messageStrContent=this.messageStrContent+",tool="+tool;
        if(a>0)
            this.messageStrContent=this.messageStrContent+",a="+a;
        if(v>0)
            this.messageStrContent=this.messageStrContent+",v="+v;
        if(cp>0)
            this.messageStrContent=this.messageStrContent+",cp="+cp;
        this.messageStrContent=this.messageStrContent+")";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            this.messageContent = this.messageStrContent.getBytes(StandardCharsets.US_ASCII);
        } else
            this.messageContent = this.messageStrContent.getBytes(Charset.forName("US-ASCII"));

    }

    @Override
    public void transformReceiveData2Object(OriginalData data) {

        this.messageContent = data.getBodyBytes();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            this.messageStrContent = new String(data.getBodyBytes(), StandardCharsets.US_ASCII);
        } else
            this.messageStrContent = new String(data.getBodyBytes(), Charset.forName("US-ASCII"));
    }


    public void setPose(double[] pose) {
        x = pose[0];
        y = pose[1];
        z = pose[2];
        rx = pose[3];
        ry = pose[4];
        rz = pose[5];
        constructSendData();
    }

    public double[] getPose() {
        return new double[]{x, y, z, rx, ry, rz};
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

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
        constructSendData();
    }

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
        constructSendData();
    }

    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
        constructSendData();
    }
}
