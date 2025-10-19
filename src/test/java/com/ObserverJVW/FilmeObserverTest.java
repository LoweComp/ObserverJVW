package com.ObserverJVW;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FilmeObserverTest {

    private static final String FILME = "TRON: Ares";
    private static final String STATUS_ESTREIA = "ESTREIA";

    @Test
    void deveNotificarAgregadorCorretamenteAoEstarEmEstreia() {
        Filme filme = new Filme(FILME, "PRE_PRODUCAO");
        CentraldeReviews agregador = new CentraldeReviews();

        agregador.inscrever(filme);

        filme.setStatusLancamento(STATUS_ESTREIA);

        String acaoEsperada = "Central de Reviews iniciou monitoramento para '" + FILME + "'.";
        assertEquals(acaoEsperada, agregador.getAcaoRealizada(), "O Agregador deve iniciar o monitoramento ao receber o status ESTREIA.");
    }

    @Test
    void deveFicarEmEsperaQuandoStatusNaoForEstreia() {
        Filme filme = new Filme(FILME, "PRE_PRODUCAO");
        CentraldeReviews agregador = new CentraldeReviews();
        agregador.inscrever(filme);

        filme.setStatusLancamento("FESTIVAL");

        String acaoEsperada = "Central de Reviews recebeu a notificação, mas está aguardando ESTREIA.";
        assertEquals(acaoEsperada, agregador.getAcaoRealizada(), "O Agregador deve registrar que está esperando o status correto.");
    }
}