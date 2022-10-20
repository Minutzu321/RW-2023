package org.firstinspires.ftc.teamcode.mina.events;

import org.firstinspires.ftc.teamcode.mina.RWConfig;
import org.firstinspires.ftc.teamcode.mina.RWRobot;
import org.firstinspires.ftc.teamcode.mina.drives.Drive;
import org.firstinspires.ftc.teamcode.mina.events.controller.ControllerEvent;

public abstract class RWEvent {

    public enum EventType{
        START,
        STOP,
        CONTROLLER
    }

    public EventType type;

    public RWEvent(EventType type){
        this.type = type;
    }

    public void execute(){
        if(RWConfig.DEBUG && eController()){
            RWRobot.telemetry.addData("event", getControllerEvent().getInfo());
        }
        for(Drive d : RWRobot.drives){
            d.onEvent(this);
        }
    }

    public boolean eStart(){
        return type == EventType.START;
    }

    public boolean eStop(){
        return type == EventType.STOP;
    }

    public boolean eController(){
        return type == EventType.CONTROLLER;
    }

    public ControllerEvent getControllerEvent(){
        return (ControllerEvent) this;
    }

}
