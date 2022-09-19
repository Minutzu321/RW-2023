package org.firstinspires.ftc.teamcode.mina;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.mina.drives.Drive;
import org.firstinspires.ftc.teamcode.mina.listeners.ControllerListener;

import java.util.ArrayList;
import java.util.List;

public class Robot {

    //pentru ca orice clasa sa poata actiona functiile din Robot, trebuie facuta o instanta publica pentru toate

    public static OpMode opMode;
    public static List<Drive> drives = new ArrayList<Drive>();

    public static void init(OpMode mode){
        opMode = mode;

        Config.init();
    }

    public static void update(){
        ControllerListener.update();
    }


}
