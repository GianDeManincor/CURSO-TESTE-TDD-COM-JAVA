package br.com.caelum.leilao.dominio;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.leilao.builder.CriadorDeLeilao;

public class LanceTest {
	
	private Avaliador leiloeiro;
	private Usuario maria;
	private Usuario joao;
	
	@Before
	public void setUp() {
		this.leiloeiro = new Avaliador();
		this.joao = new Usuario("João");
		this.maria = new Usuario("Maria");
	}
	
	@Test(expected = RuntimeException.class)
	public void naoDeveAvaliarLeilaoSemNenhumLanceDado() {
		
		Leilao leilao = new CriadorDeLeilao()
				.para("Notebook i7")
				.constroi();
		leiloeiro.avalia(leilao);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void naoDeveAvaliarUmLanceComValorMenorQueZero() {
		
		Leilao leilao = new CriadorDeLeilao()
				.para("PlayStation 4")
				.lance(joao, -100)
				.constroi();
		leiloeiro.avalia(leilao);
	
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void naoDeveAvaliarUmLanceComValorIgualZero() {
		
		Leilao leilao = new CriadorDeLeilao()
				.para("PlayStation 4")
				.lance(joao, 0)
				.constroi();
		leiloeiro.avalia(leilao);
	
	}
}
