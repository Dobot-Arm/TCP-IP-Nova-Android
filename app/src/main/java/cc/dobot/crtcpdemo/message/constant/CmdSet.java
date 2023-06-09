package cc.dobot.crtcpdemo.message.constant;

public interface CmdSet {
    String ENABLE_ROBOT = "EnableRobot";
    String DISABLE_ROBOT = "DisableRobot";
    String RESET_ROBOT = "ResetRobot";
    String STOP_ROBOT = "StopRobot";
    String POWER_ON = "PowerOn";
    String CLEAR_ERROR = "ClearError";
    String EMERGENCY_STOP = "EmergencyStop";
    String SPEED_FACTOR = "SpeedFactor";
    String MOV_J = "MovJ";
    String MOV_L = "MovL";
    String MOVE_JOG = "MoveJog";
    String START_PATH = "StartPath";
    String DO = "DO";
    String DO_EXECUTE = "DOExecute";
    String TOOL = "Tool";
    String USER = "User";
    String ACC_J = "AccJ";
    String ACC_L = "AccL";
    String STOP_SCRIPT = "StopScript";
    String GET_POSE = "GetPose";
    String GET_ERROR_ID = "GetErrorID";
    String GET_PATH_START_POSE = "GetPathStartPose";
}
