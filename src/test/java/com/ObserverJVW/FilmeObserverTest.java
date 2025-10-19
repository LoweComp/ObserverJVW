package com.ObserverJVW;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FilmeObserverTest {

    private static final String FILME = "TRON: Ares";
    private static final String STATUS_ESTREIA = "ESTREIA";
    private static final String STATUS_DIGITAL = "DIGITAL";
    private static final String STATUS_PRE = "PRE_PRODUCAO";

    @Test
    void deveAcionarMetodosCorretosAoMudarStatus() {
        Filme filme = new Filme(FILME, STATUS_PRE);
        CentraldeReviews agregador = new CentraldeReviews();
        ServidorBackup backup = new ServidorBackup();

        agregador.inscrever(filme);
        backup.inscrever(filme);

        filme.setStatusLancamento(STATUS_ESTREIA);
        assertTrue(agregador.isMonitoramentoAtivo(), "O monitoramento do Agregador deve ser ativado na ESTREIA.");
        assertEquals(0, backup.getCopiasDeSegurancaExecutadas(), "O Backup não deve ter executado cópias na ESTREIA.");

        filme.setStatusLancamento(STATUS_DIGITAL);
        assertEquals(1, backup.getCopiasDeSegurancaExecutadas(), "O Backup deve ter executado exatamente 1 cópia no status DIGITAL.");
        assertTrue(agregador.isMonitoramentoAtivo(), "O monitoramento deve permanecer ativo.");
    }

    @Test
    void deveIgnorarStatusInvalidoMantendoOEstadoDeSucesso() {
        Filme filme = new Filme(FILME, "PRE_PRODUCAO");
        CentraldeReviews agregador = new CentraldeReviews();
        agregador.inscrever(filme);

        filme.setStatusLancamento("ESTREIA");
        assertTrue(agregador.isMonitoramentoAtivo(), "O monitoramento deve estar ATIVO.");

        filme.setStatusLancamento("FESTIVAL");
        assertTrue(agregador.isMonitoramentoAtivo(), "O status inválido não deve reverter o sucesso anterior.");
    }
}