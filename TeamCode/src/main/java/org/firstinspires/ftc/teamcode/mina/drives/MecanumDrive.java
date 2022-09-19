package org.firstinspires.ftc.teamcode.mina.drives;


import org.firstinspires.ftc.teamcode.mina.events.BaseEvent;
import org.firstinspires.ftc.teamcode.mina.events.ControllerEvent;

public class MecanumDrive implements Drive{

    public MecanumDrive(){

    }


    @Override
    public void onInit() {

    }

    @Override
    public void onEvent(BaseEvent event) {
        if(event.type == BaseEvent.EventType.CONTROLLER){
            ControllerEvent controllerEvent = (ControllerEvent) event;
        }
    }
}