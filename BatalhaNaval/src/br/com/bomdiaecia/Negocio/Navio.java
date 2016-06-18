package br.com.bomdiaecia.Negocio;

import java.util.ArrayList;

import br.com.bomdiaecia.Enums.Direcao;
import br.com.bomdiaecia.Interfaces.IEmbarcacao;
import br.com.bomdiaecia.Modelos.ParteDoNavio;

public abstract class Navio implements IEmbarcacao {

	private String nome;
	private int comprimento;
	private int posicaoInicialX;
	private int posicaoInicialY;
	private Direcao direcao;
	private ArrayList<ParteDoNavio> listaPartesDoNavio;
	
	public Navio(String nome, int comprimento, int posicaoInicialX, int posicaoInicialY, Direcao direcao) {
		this.nome = nome;
		this.comprimento = comprimento;
		this.posicaoInicialX = posicaoInicialX;
		this.posicaoInicialY = posicaoInicialY;
		this.direcao = direcao;
		
		listaPartesDoNavio = new ArrayList<ParteDoNavio>();
		for (int i = 0; i < comprimento; i++) {
			ParteDoNavio parteDoNavio = new ParteDoNavio();
			if(direcao == Direcao.HORIZONTAL) {
				parteDoNavio.setPosicaoX(posicaoInicialX + i);
				parteDoNavio.setPosicaoY(posicaoInicialY);
			} else {
				parteDoNavio.setPosicaoY(posicaoInicialY + i);
				parteDoNavio.setPosicaoY(posicaoInicialX);
			}
			listaPartesDoNavio.add(parteDoNavio);
		}
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getComprimento() {
		return comprimento;
	}
	public void setComprimento(int comprimento) {
		this.comprimento = comprimento;
	}
	public Direcao getDirecao() {
		return direcao;
	}
	public void setDirecao(Direcao direcao) {
		this.direcao = direcao;
	}
	public ArrayList<ParteDoNavio> getListaPartesDoNavio() {
		return listaPartesDoNavio;
	}

	public int getPosicaoInicialX() {
		return posicaoInicialX;
	}

	public void setPosicaoInicialX(int posicaoInicialX) {
		this.posicaoInicialX = posicaoInicialX;
	}

	public int getPosicaoInicialY() {
		return posicaoInicialY;
	}

	public void setPosicaoInicialY(int posicaoInicialY) {
		this.posicaoInicialY = posicaoInicialY;
	}
}
