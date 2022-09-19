package org.firstinspires.ftc.teamcode.mina.events;

import org.firstinspires.ftc.teamcode.mina.Robot;
import org.firstinspires.ftc.teamcode.mina.drives.Drive;

public abstract class ControllerEvent extends BaseEvent {

    public enum ControllerEventType{
        BUTON,
        STICK,
        TRIGGER
    }
    public enum Controller{
        CONTROLLER1,CONTROLLER2
    }

    public ControllerEventType controllereventtype;
    public Controller controller;

    public ControllerEvent(ControllerEventType controllereventtype, Controller controller){
        super(EventType.CONTROLLER);
        this.controllereventtype = controllereventtype;
        this.controller = controller;
    }

    abstract String getInfo();



}