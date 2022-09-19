package org.firstinspires.ftc.teamcode.mina.events;

import org.firstinspires.ftc.teamcode.mina.Robot;
import org.firstinspires.ftc.teamcode.mina.drives.Drive;

public abstract class BaseEvent {

    public enum EventType{
        START,
        STOP,
        CONTROLLER
    }

    public EventType type;

    public BaseEvent(EventType type){
        this.type = type;
    }

    public void execute(){
        for(Drive d : Robot.drives){
            d.onEvent(this);
        }
    }

}
