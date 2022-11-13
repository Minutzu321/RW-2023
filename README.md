
# 🐺🚀 RiverWolves Helper - Sezonul 2022 - 2023 🚀🐺
Acesta este un SDK customizat construit pe baza SDK-ului de la FTC, cu rolul de a usura munca la codul robotului.




## :notebook_with_decorative_cover: Cuprins
- [Basics](https://github.com/Minutzu321/RW-2023#beginner-basics)
  - [RWRobot - clase principale](https://github.com/Minutzu321/RW-2023#arrow_right-rwrobotjava)
  - [RWConfig - variabile](https://github.com/Minutzu321/RW-2023#arrow_right-rwconfigjava)
  - [Telemetrie](https://github.com/Minutzu321/RW-2023#arrow_right-telemetrie)
- [Drive](https://github.com/Minutzu321/RW-2023#cum-fac-un-drive--exemplu-drive)
  - [Cum fac un drive](https://github.com/Minutzu321/RW-2023#tutorial-pas-cu-pas)
  - [Exemplu](https://github.com/Minutzu321/RW-2023#exemplu-cod-drive)

## :beginner: Basics

### :arrow_right: RWRobot.java
[Locatie: org.firstinspires.ftc.teamcode.mina.RWRobot](https://github.com/Minutzu321/RW-2023/blob/master/TeamCode/src/main/java/org/firstinspires/ftc/teamcode/mina/RWRobot.java)\
Aici sunt toate clasele principale\
Poti accesa orice clasa principala de oriunde cu urmatoarele scrieri:
- ```RWRobot.opMode``` pentru a accesa opMode-ul
  - ```RWRobot.opMode.hardwareMap``` pentru a accesa HardwareMap-ul
  - ```RWRobot.opMode.gamepad1``` pentru a accesa Gamepad1(lucru care **nu** iti trebuie pentru ca ai sistem pe eventuri)
  - ```RWRobot.opMode.gamepad2``` pentru a accesa Gamepad2(lucru care **nu** iti trebuie pentru ca ai sistem pe eventuri)
- ```RWRobot.mecanumDrive``` pentru a accesa controlul rotilor
  - [Vezi clasa SampleMecanumDrive pentru toate functiile](https://github.com/Minutzu321/RW-2023/blob/master/TeamCode/src/main/java/org/firstinspires/ftc/teamcode/drive/SampleMecanumDrive.java)
- ```RWRobot.startType``` pentru a vedea ce tip de program a inceput
  - ```CONTROL```
  - ```AUTONOMIE_ALBASTRU_DREAPTA```
  - ```AUTONOMIE_ALBASTRU_STANGA```
  - ```AUTONOMIE_ROSU_DREAPTA```
  - ```AUTONOMIE_ROSU_STANGA```
  - [Vezi clasa StartEvent](https://github.com/Minutzu321/RW-2023/blob/master/TeamCode/src/main/java/org/firstinspires/ftc/teamcode/mina/events/StartEvent.java)

### :arrow_right: RWConfig.java
[Locatie: org.firstinspires.ftc.teamcode.mina.RWConfig](https://github.com/Minutzu321/RW-2023/blob/master/TeamCode/src/main/java/org/firstinspires/ftc/teamcode/mina/RWConfig.java)\
Clasa este facuta pentru a retine toate variabilele de care depinde codul.\
Aceasta are o functie de **init** care se executa cu intaietate fata de celelalte inituri din alte clase\
Poti adauga oricand alte variabile pe care le poti accesa cu ```RWConfig.[numeVariabila]```\
Exemplu:
- ```public static DcMotor motor``` se poate accesa de oriunde cu ```RWConfig.motor```

Dar trebuie initializata aceasta valoare asa ca folosim expresiile de mai jos:
([Cod luat din clasa RWConfig.java](https://github.com/Minutzu321/RW-2023/blob/master/TeamCode/src/main/java/org/firstinspires/ftc/teamcode/mina/RWConfig.java))
```java
//Motoarele pentru roti
public static DcMotorEx ss, sf, ds, df;
//Giroscopul
public static BNO055IMU imu;
//Camera USB
public static WebcamName webcamName;

public static void init(){
    HardwareMap hardwareMap = RWRobot.opMode.hardwareMap;

    webcamName = hardwareMap.get(WebcamName.class, "webcam");

    ss = (DcMotorEx) hardwareMap.get("ss");
    sf = (DcMotorEx) hardwareMap.get("sf");
    ds = (DcMotorEx) hardwareMap.get("ds");
    df = (DcMotorEx) hardwareMap.get("df");
    
    ds.setDirection(DcMotorSimple.Direction.REVERSE);
    df.setDirection(DcMotorSimple.Direction.REVERSE);

    imu = hardwareMap.get(BNO055IMU.class, "imu");
    BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
    parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
    imu.initialize(parameters);
}
```
Cu codul de mai sus, putem accesa variabilele ss, sf, ds, df, imu si webcamName din orice clasa folosind ```RWConfig.ss```, ```RWConfig.sf```, ```RWConfig.ds```, ```RWConfig.df```, ```RWConfig.imu``` si ```RWConfig.webcamName``` datorita valorii *publice* si *statice* pe care o au variabilele.

### :arrow_right: Telemetrie
Pentru telemetrie exista mai multe modalitati:
```java
//Telemetrie simpla cu un mesaj
Telemetrie.addTel("TITLU", "MESAJ");
//sau telemetrie simpla cu o lista de mesaje
List<String> lista_mesaje = new ArrayList<>();

lista_mesaje.add("MESAJ1");
lista_mesaje.add("MESAJ2");

Telemetrie.addTel("TITLU", lista_mesaje);

//sau telemetrie de tip WARNING(apare inaintea telemetriei simple) cu un mesaj
Telemetrie.addTel(Telemetrie.TelType.WARN, "TITLU", "MESAJ");
//sau telemetrie de tip WARNING(apare inaintea telemetriei simple) cu o lista de mesaje
Telemetrie.addTel(Telemetrie.TelType.WARN, "TITLU", lista_mesaje");

//sau telemetrie de tip ERROR(apare inaintea telemetriei de tip warning si a celei simple) cu un mesaj
Telemetrie.addTel(Telemetrie.TelType.ERR, "TITLU", "MESAJ");
//sau telemetrie de tip ERROR(apare inaintea telemetriei de tip warning si a celei simple) cu o lista de mesaje
Telemetrie.addTel(Telemetrie.TelType.ERR, "TITLU", lista_mesaje");
```
## :interrobang: Cum fac un Drive / Exemplu Drive
Partea principala a codului este Drive-ul.
Un "Drive" reprezinta o parte functionala a robotului.

### :star: Tutorial pas cu pas
:white_check_mark:**Pasul 1 - Adauga un nou tip de drive in clasa Drive**\
*Locatia clasei: 'org.firstinspires.ftc.teamcode.mina.drives.Drive'*\
[Click pentru locatie](https://github.com/Minutzu321/RW-2023/blob/e1d5873c4ee91f2fa9173ecd605cee3647ec1929/TeamCode/src/main/java/org/firstinspires/ftc/teamcode/mina/drives/Drive.java#L10)

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
Acum practic ce am facut a fost sa adaugam inca o posibilitate de drive

:white_check_mark:**Pasul 2 - Creaza o clasa extinsa la Drive**\
In constructorul clasei este functia '*super()*' careia trebuie sa ii zici ce tip de "Drive" este clasa.
In cazul meu, tipul drive-ului este cel adaugat la pasul anterior, si anume ```DriveType.EXEMPLU```

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
:white_check_mark:**Pasul 3 - Inregistreaza clasa in RWRobot**\
*Locatia clasei: 'org.firstinspires.ftc.teamcode.mina.RWRobot'*\
*Secventa de cod este in functia **init** din RWRobot*\
[Click pentru locatie](https://github.com/Minutzu321/RW-2023/blob/e1d5873c4ee91f2fa9173ecd605cee3647ec1929/TeamCode/src/main/java/org/firstinspires/ftc/teamcode/mina/RWRobot.java#L44)

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
### :star2: Exemplu cod Drive
Un exemplu este drive-ul pentru roti, numit **ControlMecanumDrive**

:white_check_mark:*Pasul 1*\
*Locatie: **org.firstinspires.ftc.teamcode.mina.drives.Drive***\
[Click pentru locatie](https://github.com/Minutzu321/RW-2023/blob/e1d5873c4ee91f2fa9173ecd605cee3647ec1929/TeamCode/src/main/java/org/firstinspires/ftc/teamcode/mina/drives/Drive.java#L9)
```java
enum DriveType{
  MECANUM,
  //AICI ADAUGI ALT NUME
}
```

:white_check_mark:*Pasul 2*\
*Locatie: **org.firstinspires.ftc.teamcode.mina.drives.ControlMecanumDrive***
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

:white_check_mark:*Pasul 3*\
*Locatie: **org.firstinspires.ftc.teamcode.mina.RWRobot***\
[Click pentru locatie](https://github.com/Minutzu321/RW-2023/blob/b530e5d63ed20db44ad23866aac2b11731ae83aa/TeamCode/src/main/java/org/firstinspires/ftc/teamcode/mina/RWRobot.java#L43)
```java
//ADAUGATI AICI DRIVERELE PE CARE LE CREATI
drives.add(new ControlMecanumDrive());
```