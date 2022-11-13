
## 🐺🚀 RiverWolves Helper - Sezonul 2022 - 2023 🚀🐺
Acesta este un SDK customizat construit pe baza SDK-ului de la FTC, cu rolul de a usura munca la codul robotului.



## Cum fac un Drive / Exemplu Drive
Partea principala a codului este Drive-ul.
Un "Drive" reprezinta o parte functionala a robotului.

### Tutorial pas cu pas
**Cum fac un Drive??**\
Pentru a face un "Drive", trebuie urmati 3 pasi principali:

**Pasul 1 - Adauga un nou tip de drive in clasa Drive**\
*Locatia clasei: 'org.firstinspires.ftc.teamcode.mina.drives.Drive'*

Cautam secventa asta de cod in clasa mentionata mai sus
```java
//Adauga numele noului tau drive
enum DriveType{
    MECANUM,
    //AICI ADAUGI NUMELE
}
```
si adaugam un nume care va reprezenta drive-ul nostru\
In cazul meu, numele este **EXEMPLU**


*Rezultat:*
```java
//Adauga numele noului tau drive
enum DriveType{
    MECANUM,
    EXEMPLU,
    //AICI ADAUGI ALT NUME
}
```
Acum practic ce am facut a fost sa adaugam inca o posibilitate de drive\

**Pasul 2 - Creaza o clasa extinsa la Drive**\
In constructorul clasei este functia '*super()*' careia trebuie sa ii zici ce tip de "Drive" este clasa.
In cazul meu, tipul drive-ului este cel adaugat la pasul anterior, si anume '*DriveType.EXEMPLU*'

```java
package org.firstinspires.ftc.teamcode.mina.drives;

import org.firstinspires.ftc.teamcode.mina.events.RWEvent;

public class ExempluDrive extends Drive {

    public ExempluDrive() {
        //Aici argumentul de la super() trebuie sa fie numele
        //pe care l-ai adaugat in pasul anterior
        super(DriveType.EXEMPLU);
    }

    @Override
    public void onInit() {
        //Functia asta se cheama cand se apasa butonul de INIT
        //Aici initializezi variabilele
    }

    @Override
    public void onEvent(RWEvent event) {
        //Functia asta se cheama cand se observa
        //un event de catre Listeneri
    }
}
```
**Pasul 3 - Inregistreaza clasa in RWRobot**\
*Locatia clasei: 'org.firstinspires.ftc.teamcode.mina.RWRobot'*\
*Secventa de cod este in functia **init** din RWRobot*

Cautam secventa asta folosind instructiunile date mai sus
```java
//ADAUGATI AICI DRIVERELE PE CARE LE CREATI
drives.add(new ControlMecanumDrive());
```
si adaugam noua clasa folosind acelasi procedeu ca in secventa.\
In cazul meu, este ```drives.add(new ExempluDrive());```

*Rezultat:*
```java
//ADAUGATI AICI DRIVERELE PE CARE LE CREATI
drives.add(new ControlMecanumDrive());
drives.add(new ExempluDrive());
```
Ce a facut ultimul pas a fost sa adauge Drive-ul pe lista care este updatata de programul principal.\
Fara acest pas, clasa noastra nu este luata in considerare cand se intampla un event sau se initializeaza robotul.
### Exemplu
Un exemplu este drive-ul pentru roti, numit **ControlMecanumDrive**
```java
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
            RWRobot.mecanumDrive.setWeightedDrivePower(new Pose2d(-x, -y, -r));
        }
    }
}
```