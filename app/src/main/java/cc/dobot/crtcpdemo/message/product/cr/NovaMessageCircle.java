package cc.dobot.crtcpdemo.message.product.cr;

import android.os.Build;

import com.xuhao.didi.core.pojo.OriginalData;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import cc.dobot.crtcpdemo.message.base.BaseMessage;

public class NovaMessageCircle extends BaseMessage {

    double x1, y1, z1, rx1, ry1, rz1;
    double x2, y2, z2, rx2, ry2, rz2;
    double j1_1, j2_1, j3_1, j4_1, j5_1, j6_1;
    double j1_2, j2_2, j3_2, j4_2, j5_2, j6_2;
    int user;
    int tool;
    int a;
    int v;
    int cp;
    int count;
    private boolean isPose = false;
    @Override
    public void constructSendData() {
        if (isPose)
            this.messageStrContent = ("Circle(pose={" + x1 + "," + y1 + "," + z1 + "," + rx1 + "," + ry1 + "," + rz1 + "},"+"pose={" + x2 + "," + y2 + "," + z2 + "," + rx2 + "," + ry2 + "," + rz2 + "}");
        else
            this.messageStrContent = ("Circle(joint={" + j1_1 + "," + j2_1 + "," + j3_1 + "," + j4_1 + "," + j5_1 + "," + j6_1 + "},"+"joint={" + j1_2 + "," + j2_2 + "," + j3_2 + "," + j4_2 + "," + j5_2 + "," + j6_2  + "}");
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
        if (count>=0)
            this.messageStrContent=this.messageStrContent+",count="+count;
        this.messageStrContent=this.messageStrContent+")";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            this.messageContent = this.messageStrContent.getBytes(StandardCharsets.US_ASCII);
        } else
            this.messageContent = this.messageStrContent.getBytes(Charset.forName("US-ASCII"));

    }

    @Override
    public void transformReceiveData2Object(OriginalData data) {

        this.messageContent=data.getBodyBytes();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            this.messageStrContent = new String(data.getBodyBytes(), StandardCharsets.US_ASCII);
        }else
            this.messageStrContent = new String(data.getBodyBytes(),Charset.forName("US-ASCII"));
    }

    public void setPose(double[] point1,double[]point2)
    {
        x1=point1[0];
        y1=point1[1];
        z1=point1[2];
        rx1=point1[3];
        ry1=point1[4];
        rz1=point1[5];
        x2=point2[0];
        y2=point2[1];
        z2=point2[2];
        rx2=point2[3];
        ry2=point2[4];
        rz2=point2[5];
        isPose=true;
        constructSendData();

    }


    public double[]getPosePoint1(){
        return new double[]{x1,y1,z1,rx1,ry1,rz1};
    }

    public double[]getPosePoint2(){
        return new double[]{x2,y2,z2,rx2,ry2,rz2};
    }

    public void setJoint(double[] joint1,double[]joint2)
    {
        j1_1=joint1[0];
        j2_1=joint1[1];
        j3_1=joint1[2];
        j4_1=joint1[3];
        j5_1=joint1[4];
        j6_1=joint1[5];
        j1_2=joint2[0];
        j2_2=joint2[1];
        j3_2=joint2[2];
        j4_2=joint2[3];
        j5_2=joint2[4];
        j6_2=joint2[5];
        isPose=false;
        constructSendData();

    }

    public double[]getJointPoint1(){
        return new double[]{j1_1,j2_1,j3_1,j4_1,j5_1,j6_1};
    }

    public double[]getJointPoint2(){
        return new double[]{j1_2,j2_2,j3_2,j4_2,j5_2,j6_2};
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
        constructSendData();
    }
}
