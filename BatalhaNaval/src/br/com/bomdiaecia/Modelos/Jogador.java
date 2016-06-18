package br.com.bomdiaecia.Modelos;

import br.com.bomdiaecia.Interfaces.IJogador;
import br.com.bomdiaecia.Negocio.Navio;
import br.com.bomdiaecia.Negocio.Plataforma;

public class Jogador implements IJogador {

	private String nome;
	private Plataforma plataforma;
	private Plataforma plataformaAdversario;
	
	public Jogador(String nome, Plataforma plataforma) {
		this.nome = nome;
		this.plataforma = plataforma;
	}
	
	@Override
	public void adicioneNavio(Navio navio) {
		if(this.plataforma.verifiquePosicaoLivreParaAdicionarNavio(navio)
				&& this.plataforma.verifiquePlataformaSuportaNavio(navio)) {
			this.plataforma.getListaNavios().add(navio);
			// Adiciona ao total de posicoes que podem ser atingidas de acordo com 
			// tamanho do navio inserido
			this.plataforma.setTotalDePartesParaAtingir(this.plataforma.getTotalDePartesParaAtingir() + navio.getComprimento());
		} else {
			System.out.println("Não foi possível adicionar " + navio.getNome() + ". A posicao é invalida.");
		}
	}
	
	@Override
	public String realizaJogada(int posicaoX, int posicaoY) {
		return this.plataformaAdversario.realizeAtaque(posicaoX, posicaoY);
	}

	@Override
	public Plataforma getPlataforma() {
		return plataforma;
	}

	@Override
	public void adicionePlataformaAdversario(Plataforma plataforma) {
		this.plataformaAdversario = plataforma;
	}

	public String exibaPosicoesDosNavios() {
		StringBuilder navios = new StringBuilder();
		
		for (Navio navio : this.plataforma.getListaNavios()) {
			navios.append("Navio: " + navio.getNome());
			for (ParteDoNavio parte : navio.getListaPartesDoNavio()) {
				navios.append("\n" + parte.getPosicaoX() + " " + parte.getPosicaoY());
			}
			navios.append("\n");
		}
		return navios.toString();
	}
	
	public String getNome() {
		return nome;
	}
	
	@Override
	public boolean verifiqueSeGanhou() {
		return this.plataformaAdversario.getTotalDeAcertos() == this.plataformaAdversario.getTotalDePartesParaAtingir();
	}
}
