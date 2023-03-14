package cc.dobot.crtcpdemo.message.product.cr;

import android.os.Build;
import android.text.TextUtils;

import com.xuhao.didi.core.pojo.OriginalData;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import cc.dobot.crtcpdemo.message.base.BaseMessage;

public class NovaMessageSetPayLoad extends BaseMessage {
    private static final String MESSAGE_CONTENT="SetPayLoad()";
    private double load=-1;
    private double centerX=-999;
    private double centerY=-999;
    private double centerZ=-999;
    private String name;

    @Override
    public void constructSendData() {
        int paramsCount=(load>=0?1:0)+(centerX>=-500?1:0)+(centerY>=-500?1:0)+(centerZ>=-500?1:0);
        if (TextUtils.isEmpty(name))
        this.messageStrContent=("SetPayLoad("
                +(load>=0? load +(paramsCount>1?",":""):"")
                +(centerX>=-500? centerX +",":"")
                +(centerY>=-500? centerY +",":"")
                +(centerZ>=-500? centerZ:"")
                +")");
        else
            this.messageStrContent="SetPayLoad(\""+name+"\")";

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
