package br.com.bomdiaecia.Negocio;

public class Jogada {

	private int posicaoX;
	private int posicaoY;
	private boolean acertou;
	
	public Jogada(int x, int y) {
		this.posicaoX = x;
		this.posicaoY = y;
	}
	public int getPosicaoX() {
		return posicaoX;
	}
	public void setPosicaoX(int posicaoX) {
		this.posicaoX = posicaoX;
	}
	public int getPosicaoY() {
		return posicaoY;
	}
	public void setPosicaoY(int posicaoY) {
		this.posicaoY = posicaoY;
	}
	public boolean isAcertou() {
		return acertou;
	}
	public void setAcertou(boolean acertou) {
		this.acertou = acertou;
	}
}
