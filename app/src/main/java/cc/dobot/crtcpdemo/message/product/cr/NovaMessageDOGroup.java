package cc.dobot.crtcpdemo.message.product.cr;

import android.os.Build;

import com.xuhao.didi.core.pojo.OriginalData;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import cc.dobot.crtcpdemo.message.base.BaseMessage;

public class NovaMessageDOGroup extends BaseMessage {

    private int []index;
    private int []value;
    @Override
    public void constructSendData() {
        StringBuilder paramsBuilder=new StringBuilder();
        if (index!=null&&index.length>0){
            for (int i=0;i<index.length;i++){
                paramsBuilder.append(index[i]);
                paramsBuilder.append(",");
                paramsBuilder.append(value[i]);
                paramsBuilder.append(",");
            }
            paramsBuilder.deleteCharAt(paramsBuilder.length()-1);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            this.messageContent=("DOGroup("+paramsBuilder.toString()+")").getBytes(StandardCharsets.US_ASCII);
        }else
            this.messageContent=("DOGroup("+paramsBuilder.toString()+")").getBytes( Charset.forName("US-ASCII"));
        this.messageStrContent=("DOGroup("+paramsBuilder.toString()+")");
    }

    @Override
    public void transformReceiveData2Object(OriginalData data) {

        this.messageContent=data.getBodyBytes();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            this.messageStrContent = new String(data.getBodyBytes(), StandardCharsets.US_ASCII);
        }else
            this.messageStrContent = new String(data.getBodyBytes(),Charset.forName("US-ASCII"));
    }

    public int[] getIndex() {
        return this.index;
    }

    public void setIndex(int ...index) {
        this.index = index;
        constructSendData();
    }

    public int[] getValue() {
        return this.value;
    }

    public void setValue(int ...value) {
        this.value = value;
        constructSendData();
    }
}

