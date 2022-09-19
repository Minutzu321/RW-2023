package org.firstinspires.ftc.teamcode.mina.events;

public class ButonEvent extends ControllerEvent {

    public enum ButonType{
        A, B, X, Y,
        D_STANGA, D_DREAPTA, D_JOS, D_SUS,
        STANGA_BUMPER, DREAPTA_BUMPER
    }

    public boolean apasat;
    public ButonType butonType;
    public ButonEvent(Controller controller, ButonType tip, boolean apasat){
        super(ControllerEventType.BUTON,controller);
        this.butonType = tip;
        this.apasat = apasat;
    }

    public String getInfo(){
        return butonType.toString()+" - "+apasat;
    }
}