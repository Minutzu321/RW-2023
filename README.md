
## 🐺🚀 RiverWolves Helper - Sezonul 2022 - 2023 🚀🐺
Acesta este un SDK customizat construit pe baza SDK-ului de la FTC, cu rolul de a usura munca la codul robotului.




## Exemple / Cum se foloseste
Partea principala a codului este Drive-ul.\
Un "Drive" reprezinta o parte functionala a robotului.\
Un exemplu este drive-ul pentru roi, numit **ControlMecanumDrive**
```java
package org.firstinspires.ftc.teamcode.mina.drives;


import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.firstinspires.ftc.teamcode.mina.RWRobot;
import org.firstinspires.ftc.teamcode.mina.events.RWEvent;
import org.firstinspires.ftc.teamcode.mina.events.controller.ControllerEvent;
import org.firstinspires.ftc.teamcode.mina.events.controller.StickEvent;

public class ControlMecanumDrive extends Drive {

    public float x, y, r;

    public ControlMecanumDrive() {
        super(DriveType.MECANUM);
    }


    @Override
    public void onInit() {
        x = 0;
        y = 0;
        r = 0;
    }

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
            RWRobot.mecanumDrive.setWeightedDrivePower(new Pose2d(-x, -y, -r));
        }
    }
}
```