package com.ObserverJVW;

import java.util.Observable;
import java.util.Observer;

public class ServidorBackup implements Observer {
    private boolean copiaMestraRealizada = false;
    private int copiasDeSegurancaExecutadas = 0;

    public int getCopiasDeSegurancaExecutadas() {
        return copiasDeSegurancaExecutadas;
    }

    public void executarCopiaSeguranca() {
        if (!copiaMestraRealizada) {
            this.copiasDeSegurancaExecutadas++;
            this.copiaMestraRealizada = true;
        }
    }

    public void inscrever(Filme filme) {
        filme.addObserver(this);
    }

    @Override
    public void update(Observable filmeSubject, Object novoStatus) {
        String status = (String) novoStatus;

        if (status.equals("DIGITAL")) {
            this.executarCopiaSeguranca();
        }
    }
}