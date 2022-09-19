package org.firstinspires.ftc.teamcode.mina.events;

public class TriggerEvent extends ControllerEvent {

    public enum TriggerType{
        STANGA_TRIGGER, DREAPTA_TRIGGER
    }

    public TriggerType triggerType;
    public float v;
    public TriggerEvent(Controller controller, TriggerType tip, float v){
        super(ControllerEventType.TRIGGER,controller);
        this.triggerType = tip;
        this.v = v;
    }

    public String getInfo(){
        return triggerType.toString()+" - "+v;
    }

}