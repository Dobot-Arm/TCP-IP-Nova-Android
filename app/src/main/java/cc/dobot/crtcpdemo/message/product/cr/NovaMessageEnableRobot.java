package cc.dobot.crtcpdemo.message.product.cr;

import android.os.Build;

import com.xuhao.didi.core.pojo.OriginalData;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import cc.dobot.crtcpdemo.message.base.BaseMessage;

public class NovaMessageEnableRobot extends BaseMessage {
    private static final String MESSAGE_CONTENT="EnableRobot()";
    private double load=-1;
    private double centerX=-999;
    private double centerY=-999;
    private double centerZ=-999;
    private int isCheck=0;

    @Override
    public void constructSendData() {
        int paramsCount=(load>=0?1:0)+(centerX>=-500?1:0)+(centerY>=-500?1:0)+(centerZ>=-500?1:0)+(isCheck ==1?1:0);

        this.messageStrContent=("EnableRobot("
                +(load>=0? load +(paramsCount>1?",":""):"")
                +(centerX>=-500? centerX +",":"")
                +(centerY>=-500? centerY +",":"")
                +(centerZ>=-500? centerZ +(paramsCount==5?",":""):"")
                +(isCheck==1? isCheck +"":"")
                +")");

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

    public double getLoad() {
        return load;
    }

    public void setLoad(double load) {
        if (load>=0)
        this.load = load;
    }

    public double getCenterX() {
        return centerX;
    }

    public void setCenterX(double centerX) {
        if (centerX>=-500&&centerX<=500)
            this.centerX = centerX;
    }

    public double getCenterY() {
        return centerY;
    }

    public void setCenterY(double centerY) {
        if (centerY>=-500&&centerY<=500)
            this.centerY = centerY;
    }

    public double getCenterZ() {
        return centerZ;
    }

    public void setCenterZ(double centerZ) {
        if (centerZ>=-500&&centerZ<=500)
            this.centerZ = centerZ;
    }

    public int getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(int isCheck) {
        if (isCheck==1||isCheck==0)
            this.isCheck = isCheck;
    }
}
