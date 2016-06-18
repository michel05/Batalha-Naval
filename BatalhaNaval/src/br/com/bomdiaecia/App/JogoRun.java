package br.com.bomdiaecia.App;

import java.util.Scanner;

import br.com.bomdiaecia.Enums.Direcao;
import br.com.bomdiaecia.Interfaces.IJogador;
import br.com.bomdiaecia.Modelos.Jogador;
import br.com.bomdiaecia.Modelos.navios.BarcoDePatrulha;
import br.com.bomdiaecia.Modelos.navios.Destroyer;
import br.com.bomdiaecia.Modelos.navios.PortaAvioes;
import br.com.bomdiaecia.Negocio.Plataforma;

public class JogoRun {

	public static void main(String[] args) {

		final int TAMANHO_PLATAFORMA_X = 10;
		final int TAMANHO_PLATAFORMA_Y = 10;
		
		IJogador jogador1 = new Jogador("Michel", new Plataforma(TAMANHO_PLATAFORMA_X, TAMANHO_PLATAFORMA_Y));
		IJogador jogador2 = new Jogador("Joao", new Plataforma(TAMANHO_PLATAFORMA_X, TAMANHO_PLATAFORMA_Y));
		
		//Adicione os Navios aqui, caso houver inconsistencias como intercessão de posicoes,
		//será mostrado em mensagens
		jogador1.adicioneNavio(new PortaAvioes("PortaAvioes", 2, 1, Direcao.HORIZONTAL));
		jogador1.adicioneNavio(new Destroyer("Destroyer", 0, 1, Direcao.HORIZONTAL));
		jogador2.adicioneNavio(new PortaAvioes("PortaAvioes", 1, 1, Direcao.HORIZONTAL));
		jogador2.adicioneNavio(new BarcoDePatrulha("Baco da Patrulha", 1, 2, Direcao.HORIZONTAL));
		
		//Define qual jogador esta jogando com o outro, notem que jogador 1 não tem acesso a plataforma.
		jogador1.adicionePlataformaAdversario(jogador2.getPlataforma());
		jogador2.adicionePlataformaAdversario(jogador1.getPlataforma());
		
		//Logica para funcionar os turnos
		boolean semGanhador = true;
		Scanner scanner = new Scanner(System.in);
		String jogada = "";
		String[] posicoes = null;
		while(semGanhador) {
			System.out.println("\nVez do Jogador 1: ");
			jogada = scanner.nextLine();
			
			posicoes = jogada.split(" ");
			System.out.println(jogador1.realizaJogada(Integer.parseInt(posicoes[0]), Integer.parseInt(posicoes[1])));
			
			if (jogador1.verifiqueSeGanhou()) {
				semGanhador = false;
			}
			
			System.out.println("\nVez do Jogador 2: ");
			jogada = scanner.nextLine();
			
			posicoes = jogada.split(" ");
			System.out.println(jogador2.realizaJogada(Integer.parseInt(posicoes[0]), Integer.parseInt(posicoes[1])));
			
			if (jogador2.verifiqueSeGanhou()) {
				semGanhador = false;
			}
		}
		
		System.out.println("\nJogador 1:\n" + jogador1.exibaPosicoesDosNavios());
		System.out.println("\n\nJogador 2:\n" + jogador2.exibaPosicoesDosNavios());
	}

}
