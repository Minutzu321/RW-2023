package org.firstinspires.ftc.teamcode.mina.drives;

import org.firstinspires.ftc.teamcode.mina.events.BaseEvent;
import org.firstinspires.ftc.teamcode.mina.events.ControllerEvent;

public interface Drive {

    void onInit();

    void onEvent(BaseEvent event);

}