package com.ObserverJVW;

import java.util.Observable;
import java.util.Observer;

public class CentraldeReviews implements Observer {

    private boolean monitoramentoAtivo = false;

    public boolean isMonitoramentoAtivo() {
        return monitoramentoAtivo;
    }

    public void iniciarMonitoramento() {
        this.monitoramentoAtivo = true;
    }

    public void inscrever(Filme filme) {
        filme.addObserver(this);
    }

    @Override
    public void update(Observable filme, Object novoStatus) {
        String status = (String) novoStatus;

        if (status.equals("ESTREIA")) {
            this.iniciarMonitoramento();
        }
    }
}