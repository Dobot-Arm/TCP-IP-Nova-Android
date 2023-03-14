package cc.dobot.crtcpdemo.message.product.cr;

import android.os.Build;

import com.xuhao.didi.core.pojo.OriginalData;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import cc.dobot.crtcpdemo.message.base.BaseMessage;

public class NovaMessageMovJIO extends BaseMessage {
    double x, y, z, rx, ry, rz;
    double j1, j2, j3, j4, j5, j6;
    int user;
    int tool;
    int a;
    int v;
    int cp;
    int mode;
    int distance;
    int index;
    int status;

    private boolean isPose = false;

    @Override
    public void constructSendData() {
        if (isPose)
            this.messageStrContent = ("MovJIO(pose={" + x + "," + y + "," + z + "," + rx + "," + ry + "," + rz + "}");
        else
            this.messageStrContent = ("MovJIO(joint={" + j1 + "," + j2 + "," + j3 + "," + j4 + "," + j5 + "," + j6 + "}");
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
        if (mode>0&&distance>=0&&index>0&&status>=0)
            this.messageStrContent=this.messageStrContent+",{"+mode+ "," + distance + "," + index +","+status+"}";
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

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
        constructSendData();
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
        constructSendData();
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
        constructSendData();
    }

    public double getRx() {
        return rx;
    }

    public void setRx(double rx) {
        this.rx = rx;
        constructSendData();
    }

    public double getRy() {
        return ry;
    }

    public void setRy(double ry) {
        this.ry = ry;
        constructSendData();
    }

    public double getRz() {
        return rz;
    }

    public void setRz(double rz) {
        this.rz = rz;
        constructSendData();
    }

    public void setPose(double[] pose) {
        isPose = true;
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

    public void setJoint(double[] joint) {
        isPose = false;
        j1 = joint[0];
        j2 = joint[1];
        j3 = joint[2];
        j4 = joint[3];
        j5 = joint[4];
        j6 = joint[5];
        constructSendData();
    }

    public double[] getJoint() {
        return new double[]{j1, j2, j3, j4, j5, j6};
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
    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
        constructSendData();
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
        constructSendData();
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
        constructSendData();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
        constructSendData();
    }
}
