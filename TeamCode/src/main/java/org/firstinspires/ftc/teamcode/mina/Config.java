package org.firstinspires.ftc.teamcode.mina;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Config {

    public static DcMotor ss,sf,ds,df;


    public static void init(){
        HardwareMap hardwareMap = Robot.opMode.hardwareMap;

        ss = (DcMotor) hardwareMap.get("ss");
        sf = (DcMotor) hardwareMap.get("sf");
        ds = (DcMotor) hardwareMap.get("ds");
        df = (DcMotor) hardwareMap.get("df");
    }

}
