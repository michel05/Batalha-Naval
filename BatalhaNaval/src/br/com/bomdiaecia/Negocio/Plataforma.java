package br.com.bomdiaecia.Negocio;

import java.util.ArrayList;
import java.util.List;

import br.com.bomdiaecia.Enums.Direcao;
import br.com.bomdiaecia.Modelos.ParteDoNavio;

public class Plataforma {

	private boolean[][] plataforma;
	private List<Navio> listaNavios;
	private List<Jogada> listaJogadas;
	private int totalDePartesParaAtingir;
	private int totalDeAcertos;
	
	public Plataforma(int tamanhoX, int tamanhoY) {
		this.plataforma = new boolean[tamanhoX][tamanhoY];
		this.listaNavios = new ArrayList<Navio>();
		this.listaJogadas = new ArrayList<Jogada>();
	}
	
	/*
	 * Este método realiza o ataque verificando e validando todas
	 * condições possíveis 
	 * 
	 * @params x PosiçãoX, y PosicaoY
	 * @Retorno String Informações da Jogada
	 */
	public String realizeAtaque(int x, int y) {
		StringBuilder resultadoJogada = new StringBuilder();
		
		if (!verifiqueSeJogadaEhValida(x, y)) {
			resultadoJogada.append("Jogada não permitida, não existe essa posição.");
			return resultadoJogada.toString();
		}
		
		if (this.verifiqueSeJogadaJaFoiFeita(x, y)){
			resultadoJogada.append("Jogada já foi feita.");
			return resultadoJogada.toString();
		}
		
		this.listaJogadas.add(new Jogada(x,y));
		
		ParteDoNavio parteAtingida = this.tenteAtigirNavio(x, y);
		if (parteAtingida != null) {
			
			Navio navioAtingindo = busqueNavio(x, y);
			if(verifiqueSeNavioAfundou(navioAtingindo)) {
				resultadoJogada.append("Posição : " + x + " " + y + " - Navio " + navioAtingindo.getNome() + " foi afundado!!\n");
				
				if (verifiqueSeGanhou()) {
					resultadoJogada.append("Parabens você ganhou!!!!!\n");
				}
			} else {
				resultadoJogada.append("Posição : " + x + " " + y + " - Navio " + navioAtingindo.getNome() + " foi atingido!");
			}
		} else {
			resultadoJogada.append("Posição : " + x + " " + y + " - Tiro na água");
		}
		return resultadoJogada.toString();
	}

	/*
	 * Verifica se existe algum outro navio na posição do novo navio a 
	 * ser inserido
	 * 
	 * @Params Navio Navio a ser verificado
	 * @Retorno boolean true/false
	 */
	public boolean verifiquePosicaoLivreParaAdicionarNavio(Navio navio) {
		
		for (ParteDoNavio parte : navio.getListaPartesDoNavio()) {
			if(this.busqueNavio(parte.getPosicaoX(), parte.getPosicaoY()) != null)
				return false;
		}
		return true;
	}
	
	/*
	 * Verifica se a plataforma suporta o Navio, validando seus limites
	 * de inserção
	 * 
	 * @Params Navio navio a ser verificado
	 * @Retorno boolean true/false
	 */
	public boolean verifiquePlataformaSuportaNavio(Navio navio) {
		
		if(navio.getDirecao() == Direcao.HORIZONTAL) {
			return this.plataforma.length >= (navio.getComprimento() + navio.getPosicaoInicialX())
					&& this.plataforma[0].length >= navio.getPosicaoInicialY(); 
		} else {
			return this.plataforma[0].length >= (navio.getComprimento() + navio.getPosicaoInicialY())
					&& this.plataforma.length >= navio.getPosicaoInicialX();
		}
	}
	
	/*
	 * Verifica se a jogada não foi feita anteriormente.
	 * 
	 * @params x PosiçãoX, y PosicaoY
	 * @Retorno boolean true/false
	 */
	private boolean verifiqueSeJogadaJaFoiFeita(int x, int y) {
		for (Jogada jogada : listaJogadas) {
			if (jogada.getPosicaoX() == x && jogada.getPosicaoY() == y) {
				return true;
			}
		}
		return false;
	}
	
	/*
	 * Verifica se o Navio não afundou
	 * @Params Navio navio a ser verificado
	 * @Retorno boolean true/false
	 */
	private boolean verifiqueSeNavioAfundou(Navio navio) {
		if (navio != null) {
			for (ParteDoNavio parte : navio.getListaPartesDoNavio()) {
				if(!parte.isAtingindo())
					return false;
			}
		}
		return true;
	}
	
	/*
	 * Busca na lista de navios o navio que existe na posição passada, caso
	 * não exista é retornado vazio.
	 * 
	 * @params x PosiçãoX, y PosicaoY
	 * @Retorno Navio navio encontrado
	 */
	private Navio busqueNavio(int x, int y) {
		
		for (Navio navio : listaNavios) {
			for (ParteDoNavio parte : navio.getListaPartesDoNavio()) {
				if(parte.getPosicaoX() == x && parte.getPosicaoY() == y) {
					return navio;
				}
			}
		}
		return null;
	}
	
	/*
	 * Tenta atingir o navio de acordo com as posições, caso não econtre é 
	 * retornado vazio.
	 * 
	 * @params x PosiçãoX, y PosicaoY
	 * @Retorno ParteDoNavio Parte do Navio atingida
	 */
	private ParteDoNavio tenteAtigirNavio(int x, int y){

		Navio navio = busqueNavio(x,y);
		
		if(navio != null) {
			for (ParteDoNavio parte : navio.getListaPartesDoNavio()) {
				if(parte.getPosicaoX() == x && parte.getPosicaoY() == y) {
					parte.setAtingindo(true);
					this.totalDeAcertos++;
					return parte;
				}
			}
		}
		return null;
	}

	/*
	 * Verifica se quantidade de acertos é igual ao total de partes
	 * que precisam ser atingindas.
	 * 
	 * @Retorno boolean true/false
	 */
	private Boolean verifiqueSeGanhou() {
		return this.totalDeAcertos == this.totalDePartesParaAtingir;
	}
	
	/*
	 * Verifica se a jogada é válida, caso informe uma posição fora da 
	 * plataforma é validado aqui.
	 * 
	 * @Retorno boolean true/false
	 */
	private boolean verifiqueSeJogadaEhValida(int x, int y) {
		if((this.plataforma.length >= x) && (this.plataforma[0].length >= y)) {
			return true;
		}
		return false;
	}

	public boolean[][] getPlataforma() {
		return plataforma;
	}

	public List<Navio> getListaNavios() {
		return listaNavios;
	}

	public List<Jogada> getListaJogadas() {
		return listaJogadas;
	}

	public int getTotalDePartesParaAtingir() {
		return totalDePartesParaAtingir;
	}

	public int getTotalDeAcertos() {
		return totalDeAcertos;
	}

	public void setTotalDePartesParaAtingir(int totalDePartesParaAtingir) {
		this.totalDePartesParaAtingir = totalDePartesParaAtingir;
	}
	
	
}
