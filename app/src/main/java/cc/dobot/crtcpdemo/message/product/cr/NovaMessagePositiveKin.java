package cc.dobot.crtcpdemo.message.product.cr;

import android.os.Build;

import com.xuhao.didi.core.pojo.OriginalData;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import cc.dobot.crtcpdemo.message.base.BaseMessage;

public class NovaMessagePositiveKin extends BaseMessage {
    double j1,j2,j3,j4,j5,j6;
    int user=-1;
    int tool=-1;

    @Override
    public void constructSendData() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            this.messageContent=("PositiveKin("+j1+","+j2+","+j3+","+j4+","+j5+","+j6+(user>0&&tool>0?","+"user="+user+","+"tool="+tool:"")+")").getBytes(StandardCharsets.US_ASCII);
        }else
            this.messageContent=("PositiveKin("+j1+","+j2+","+j3+","+j4+","+j5+","+j6+(user>0&&tool>0?","+"user="+user+","+"tool="+tool:"")+")").getBytes( Charset.forName("US-ASCII"));
        this.messageStrContent=("PositiveKin("+j1+","+j2+","+j3+","+j4+","+j5+","+j6+(user>0&&tool>0?","+"user="+user+","+"tool="+tool:"")+")");
    }

    @Override
    public void transformReceiveData2Object(OriginalData data) {

        this.messageContent=data.getBodyBytes();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            this.messageStrContent = new String(data.getBodyBytes(), StandardCharsets.US_ASCII);
        }else
            this.messageStrContent = new String(data.getBodyBytes(),Charset.forName("US-ASCII"));
    }



    public void setPoint(double[] point)
    {
        j1=point[0];
        j2=point[1];
        j3=point[2];
        j4=point[3];
        j5=point[4];
        j6=point[5];
        constructSendData();
    }

    public double[]getPoint(){
        return new double[]{j1,j2,j3,j4,j5,j6};
    }

    public void setCoordinateIndex(int user,int tool){

        this.user=user;
        this.tool=tool;
        constructSendData();
    }
}
