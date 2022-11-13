package org.firstinspires.ftc.teamcode.mina.drives;

import org.firstinspires.ftc.teamcode.mina.events.RWEvent;

public abstract class Drive {

    //Adauga numele noului tau drive
    enum DriveType{
        MECANUM,
        EXEMPLU,
        //AICI ADAUGI NUMELE
    }

    public DriveType driveType;

    public Drive(DriveType driveType){
        this.driveType = driveType;
    }

    public abstract void onInit();

    public abstract void onEvent(RWEvent event);

}