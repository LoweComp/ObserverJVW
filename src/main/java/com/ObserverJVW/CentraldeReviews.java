package com.ObserverJVW;

import java.util.Observable;
import java.util.Observer;

public class CentraldeReviews implements Observer {

    private String acaoRealizada = "";
    private final String nomeServico = "Central de Reviews";

    public void inscrever(Filme filme) {
        filme.addObserver(this);
    }

    public String getAcaoRealizada() {
        return this.acaoRealizada;
    }

    @Override
    public void update(Observable filme, Object novoStatus) {
        String status = (String) novoStatus;
        String tituloFilme = ((Filme) filme).getTitulo();

        if (status.equals("ESTREIA")) {
            this.acaoRealizada = this.nomeServico + " iniciou monitoramento para '" + tituloFilme + "'.";
        } else {
            this.acaoRealizada = this.nomeServico + " recebeu a notificação, mas está aguardando ESTREIA.";
        }
    }
}