package com.ObserverJVW;

import java.util.Observable;

public class Filme extends Observable {

    private String titulo;
    private String statusLancamento;

    public Filme(String titulo, String statusInicial) {
        this.titulo = titulo;
        this.statusLancamento = statusInicial;
    }

    public void setStatusLancamento(String novoStatus) {
        setChanged();
        this.statusLancamento = novoStatus;
        notifyObservers(novoStatus);
    }

    public String getTitulo() {
        return titulo;
    }

    public String getStatusLancamento() {
        return statusLancamento;
    }

    @Override
    public String toString() {
        return "Filme{" + "titulo='" + titulo + '\'' + ", status='" + statusLancamento + '\'' + '}';
    }
}
