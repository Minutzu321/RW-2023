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
        //Functia asta se cheama cand se inregistreaza
        //un event de catre Listeneri
    }
}