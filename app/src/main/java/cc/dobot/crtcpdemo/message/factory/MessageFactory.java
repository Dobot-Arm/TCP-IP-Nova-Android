package cc.dobot.crtcpdemo.message.factory;

import com.xuhao.didi.core.iocore.interfaces.ISendable;

import cc.dobot.crtcpdemo.message.constant.CmdSet;
import cc.dobot.crtcpdemo.message.product.cr.NovaMessageDO;
import cc.dobot.crtcpdemo.message.product.cr.NovaMessageDOInstant;

import cc.dobot.crtcpdemo.message.product.cr.NovaMessageEmergencyStop;
import cc.dobot.crtcpdemo.message.product.cr.NovaMessageEnableRobot;
import cc.dobot.crtcpdemo.message.product.cr.NovaMessageGetErrorID;
import cc.dobot.crtcpdemo.message.product.cr.NovaMessageGetStartPose;
import cc.dobot.crtcpdemo.message.product.cr.NovaMessageGetPose;
import cc.dobot.crtcpdemo.message.product.cr.NovaMessageMovJ;
import cc.dobot.crtcpdemo.message.product.cr.NovaMessageMovL;
import cc.dobot.crtcpdemo.message.product.cr.NovaMessageMoveJog;
import cc.dobot.crtcpdemo.message.product.cr.NovaMessagePowerOn;
import cc.dobot.crtcpdemo.message.product.cr.NovaMessageSpeedFactor;
import cc.dobot.crtcpdemo.message.product.cr.NovaMessageStartPath;
import cc.dobot.crtcpdemo.message.product.cr.NovaMessageStopRobot;
import cc.dobot.crtcpdemo.message.product.cr.NovaMessageStopScript;
import cc.dobot.crtcpdemo.message.product.cr.NovaMessageTool;
import cc.dobot.crtcpdemo.message.product.cr.NovaMessageUser;
import cc.dobot.crtcpdemo.message.product.cr.NovaMessageAccJ;
import cc.dobot.crtcpdemo.message.product.cr.NovaMessageAccL;
import cc.dobot.crtcpdemo.message.product.cr.NovaMessageClearError;
import cc.dobot.crtcpdemo.message.product.cr.NovaMessageDisableRobot;


public class MessageFactory {

    private static MessageFactory instance;

    private MessageFactory() {

    }

    public static MessageFactory getInstance() {
        if (instance == null) {
            synchronized (MessageFactory.class) {
                if (instance == null) {
                    instance = new MessageFactory();
                }
            }
        }
        return instance;
    }

    public ISendable createMsg(String CMD_SET) {
        switch (CMD_SET) {
            case CmdSet.ENABLE_ROBOT:
                return new NovaMessageEnableRobot();
            case CmdSet.DISABLE_ROBOT:
                return new NovaMessageDisableRobot();
            case CmdSet.POWER_ON:
                return new NovaMessagePowerOn();
            case CmdSet.CLEAR_ERROR:
                return new NovaMessageClearError();
            case CmdSet.EMERGENCY_STOP:
                return new NovaMessageEmergencyStop();
            case CmdSet.SPEED_FACTOR:
                return new NovaMessageSpeedFactor();
            case CmdSet.DO:
                return new NovaMessageDO();
            case CmdSet.MOV_L:
                return new NovaMessageMovL();
            case CmdSet.MOV_J:
                return new NovaMessageMovJ();
            case CmdSet.MOVE_JOG:
                return new NovaMessageMoveJog();
            case CmdSet.DO_EXECUTE:
                return new NovaMessageDOInstant();
            case CmdSet.TOOL:
                return new NovaMessageTool();
            case CmdSet.USER:
                return new NovaMessageUser();
            case CmdSet.ACC_J:
                return new NovaMessageAccJ();
            case CmdSet.ACC_L:
                return new NovaMessageAccL();
            case CmdSet.START_PATH:
                return new NovaMessageStartPath();
            case CmdSet.STOP_SCRIPT:
                return new NovaMessageStopScript();
            case CmdSet.STOP_ROBOT:
                return new NovaMessageStopRobot();
            case CmdSet.GET_PATH_START_POSE:
                return new NovaMessageGetStartPose();
            case CmdSet.GET_POSE:
                return new NovaMessageGetPose();
            case CmdSet.GET_ERROR_ID:
                return new NovaMessageGetErrorID();
        }
        return null;
    }


}
