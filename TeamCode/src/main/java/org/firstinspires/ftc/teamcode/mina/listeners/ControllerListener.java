package org.firstinspires.ftc.teamcode.mina.listeners;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.mina.Robot;
import org.firstinspires.ftc.teamcode.mina.events.ButonEvent;
import org.firstinspires.ftc.teamcode.mina.events.ControllerEvent;
import org.firstinspires.ftc.teamcode.mina.events.StickEvent;
import org.firstinspires.ftc.teamcode.mina.events.TriggerEvent;

public class ControllerListener {

    public static Gamepad gamepad1= Robot.opMode.gamepad1,gamepad2=Robot.opMode.gamepad2;

    private static boolean a1=false, a2=false,
                           b1=false, b2=false,
                           x1=false, x2=false,
                           y1=false, y2=false,
                           lb1=false, lb2=false,
                           rb1=false, rb2=false,
                           dsus1=false, dsus2=false,
                           djos1=false, djos2=false,
                           dstanga1=false, dstanga2=false,
                           ddreapta1=false, ddreapta2=false;

    private static float sx1=0, sx2=0,
                         sy1=0, sy2=0,
                         dx1=0, dx2=0,
                         dy1=0, dy2=0,
                         lt1=0, lt2=0,
                         rt1=0, rt2=0;


    public static void update(){
        ControllerEvent.Controller c1 = ControllerEvent.Controller.CONTROLLER1,
                                   c2 = ControllerEvent.Controller.CONTROLLER2;
        if(gamepad1.a != a1){
            new ButonEvent(c1, ButonEvent.ButonType.A, gamepad1.a).execute();
            a1 = gamepad1.a;
        }
        if(gamepad1.b != b1){
            new ButonEvent(c1, ButonEvent.ButonType.B, gamepad1.b).execute();
            b1 = gamepad1.b;
        }
        if(gamepad1.x != x1){
            new ButonEvent(c1, ButonEvent.ButonType.X, gamepad1.x).execute();
            x1 = gamepad1.x;
        }
        if(gamepad1.y != y1){
            new ButonEvent(c1, ButonEvent.ButonType.Y, gamepad1.y).execute();
            y1 = gamepad1.y;
        }
        if(gamepad1.left_bumper != lb1){
            new ButonEvent(c1, ButonEvent.ButonType.STANGA_BUMPER, gamepad1.left_bumper).execute();
            lb1 = gamepad1.left_bumper;
        }
        if(gamepad1.right_bumper != rb1){
            new ButonEvent(c1, ButonEvent.ButonType.DREAPTA_BUMPER, gamepad1.right_bumper).execute();
            rb1 = gamepad1.right_bumper;
        }
        if(gamepad1.dpad_down != djos1){
            new ButonEvent(c1, ButonEvent.ButonType.D_JOS, gamepad1.dpad_down).execute();
            djos1 = gamepad1.dpad_down;
        }
        if(gamepad1.dpad_up != dsus1){
            new ButonEvent(c1, ButonEvent.ButonType.D_SUS, gamepad1.dpad_up).execute();
            dsus1 = gamepad1.dpad_up;
        }
        if(gamepad1.dpad_left != dstanga1){
            new ButonEvent(c1, ButonEvent.ButonType.D_STANGA, gamepad1.dpad_left).execute();
            dstanga1 = gamepad1.dpad_left;
        }
        if(gamepad1.dpad_right != ddreapta1){
            new ButonEvent(c1, ButonEvent.ButonType.D_DREAPTA, gamepad1.dpad_right).execute();
            ddreapta1 = gamepad1.dpad_right;
        }
        if(gamepad1.left_stick_x != sx1 || gamepad1.left_stick_y != sy1){
            new StickEvent(c1, StickEvent.StickType.STANGA, gamepad1.left_stick_x, gamepad1.left_stick_y).execute();
            sx1 = gamepad1.left_stick_x;
            sy1 = gamepad1.left_stick_y;
        }
        if(gamepad1.right_stick_x != dx1 || gamepad1.right_stick_y != dy1){
            new StickEvent(c1, StickEvent.StickType.DREAPTA, gamepad1.right_stick_x, gamepad1.right_stick_y).execute();
            dx1 = gamepad1.right_stick_x;
            dy1 = gamepad1.right_stick_y;
        }
        if(gamepad1.left_trigger != lt1){
            new TriggerEvent(c1, TriggerEvent.TriggerType.STANGA_TRIGGER, gamepad1.left_trigger).execute();
            lt1 = gamepad1.left_trigger;
        }
        if(gamepad1.right_trigger != rt1){
            new TriggerEvent(c1, TriggerEvent.TriggerType.DREAPTA_TRIGGER, gamepad1.right_trigger).execute();
            rt1 = gamepad1.right_trigger;
        }


        //GAMEPAD 2
        if(gamepad2.a != a2){
            new ButonEvent(c2, ButonEvent.ButonType.A, gamepad2.a).execute();
            a2 = gamepad2.a;
        }
        if(gamepad2.b != b2){
            new ButonEvent(c2, ButonEvent.ButonType.B, gamepad2.b).execute();
            b2 = gamepad2.b;
        }
        if(gamepad2.x != x2){
            new ButonEvent(c2, ButonEvent.ButonType.X, gamepad2.x).execute();
            x2 = gamepad2.x;
        }
        if(gamepad2.y != y2){
            new ButonEvent(c2, ButonEvent.ButonType.Y, gamepad2.y).execute();
            y2 = gamepad2.y;
        }
        if(gamepad2.left_bumper != lb2){
            new ButonEvent(c2, ButonEvent.ButonType.STANGA_BUMPER, gamepad2.left_bumper).execute();
            lb2 = gamepad2.left_bumper;
        }
        if(gamepad2.right_bumper != rb2){
            new ButonEvent(c2, ButonEvent.ButonType.DREAPTA_BUMPER, gamepad2.right_bumper).execute();
            rb2 = gamepad2.right_bumper;
        }
        if(gamepad2.dpad_down != djos2){
            new ButonEvent(c2, ButonEvent.ButonType.D_JOS, gamepad2.dpad_down).execute();
            djos2 = gamepad2.dpad_down;
        }
        if(gamepad2.dpad_up != dsus2){
            new ButonEvent(c2, ButonEvent.ButonType.D_SUS, gamepad2.dpad_up).execute();
            dsus2 = gamepad2.dpad_up;
        }
        if(gamepad2.dpad_left != dstanga2){
            new ButonEvent(c2, ButonEvent.ButonType.D_STANGA, gamepad2.dpad_left).execute();
            dstanga2 = gamepad2.dpad_left;
        }
        if(gamepad2.dpad_right != ddreapta2){
            new ButonEvent(c2, ButonEvent.ButonType.D_DREAPTA, gamepad2.dpad_right).execute();
            ddreapta2 = gamepad2.dpad_right;
        }
        if(gamepad2.left_stick_x != sx2 || gamepad2.left_stick_y != sy2){
            new StickEvent(c2, StickEvent.StickType.STANGA, gamepad2.left_stick_x, gamepad2.left_stick_y).execute();
            sx2 = gamepad2.left_stick_x;
            sy2 = gamepad2.left_stick_y;
        }
        if(gamepad2.right_stick_x != dx2 || gamepad2.right_stick_y != dy2){
            new StickEvent(c2, StickEvent.StickType.DREAPTA, gamepad2.right_stick_x, gamepad2.right_stick_y).execute();
            dx2 = gamepad2.right_stick_x;
            dy2 = gamepad2.right_stick_y;
        }
        if(gamepad2.left_trigger != lt2){
            new TriggerEvent(c2, TriggerEvent.TriggerType.STANGA_TRIGGER, gamepad2.left_trigger).execute();
            lt2 = gamepad2.left_trigger;
        }
        if(gamepad2.right_trigger != rt2){
            new TriggerEvent(c2, TriggerEvent.TriggerType.DREAPTA_TRIGGER, gamepad2.right_trigger).execute();
            rt2 = gamepad2.right_trigger;
        }



    }

}