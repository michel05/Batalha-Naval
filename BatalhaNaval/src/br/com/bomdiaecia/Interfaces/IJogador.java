package br.com.bomdiaecia.Interfaces;

import br.com.bomdiaecia.Negocio.Navio;
import br.com.bomdiaecia.Negocio.Plataforma;

public interface IJogador {

	Plataforma getPlataforma();
	void adicioneNavio(Navio navio);
	void adicionePlataformaAdversario(Plataforma plataforma);
	String realizaJogada(int posicaoX, int posicaoY);
	String exibaPosicoesDosNavios();
	boolean verifiqueSeGanhou();
}
