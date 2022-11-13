package org.firstinspires.ftc.teamcode.mina.drives;


import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.firstinspires.ftc.teamcode.mina.RWRobot;
import org.firstinspires.ftc.teamcode.mina.events.RWEvent;
import org.firstinspires.ftc.teamcode.mina.events.controller.ControllerEvent;
import org.firstinspires.ftc.teamcode.mina.events.controller.StickEvent;

public class ControlMecanumDrive extends Drive {

    //Variabilele pentru puteri
    public float x, y, r;

    //Constructorul clasei
    //Trebuie pasat mereu tipul Driveului
    //se gaseste in clasa Drive din packageul drives
    public ControlMecanumDrive() {
        super(DriveType.MECANUM);
    }

    //Aici te asiguri ca totul este pregatit cand se apasa pe butonul de init
    @Override
    public void onInit() {
        x = 0;
        y = 0;
        r = 0;
    }

    //Aici este functia centrala.
    //Prin ea se paseaza evenimentele detectate de listeneri
    @Override
    public void onEvent(RWEvent event) {
        StickEvent stickEvent = event.getStickEvent();
        // !!! ATENTIE
        // Daca evenimentul NU este StickEvent, variabila de mai sus este
        // NULL deci trebuie verificata conditia mereu.
        // Chestia asta se aplica la orice event!!!!
        // !!! ATENTIE
        if (stickEvent != null && stickEvent.eController1()) {
            if (stickEvent.eSTANGA()) {
                x = stickEvent.x;
                y = stickEvent.y;
            } else {
                r = stickEvent.x;
            }
            getMecanum().setWeightedDrivePower(new Pose2d(-x, -y, -r));
        }
    }
}