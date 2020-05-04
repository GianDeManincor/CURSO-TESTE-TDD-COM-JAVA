package br.com.caelum.leilao.dominio;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.leilao.builder.CriadorDeLeilao;

public class TesteDoAvaliador {
	
	private Avaliador leiloeiro;
	private Usuario joao;
	private Usuario jose;
	private Usuario maria;

	@Before
	public void setUp() {
		this.leiloeiro = new Avaliador();
		this.joao = new Usuario("João");
		this.jose = new Usuario("José");
		this.maria = new Usuario("Maria");
	}
	
	public static Matcher<Leilao> temUmLance(Lance lance) {
	        return new LeilaoMatcher(lance);
	}
	
	@Test
	public void deveEntenderLancesCrescentes() {
		
		//parte 1: cria o cenario
		
		Leilao leilao = new CriadorDeLeilao().para("Macbook Pro")
				.lance(maria, 300.0)
				.lance(joao, 200.0)
				.lance(jose, 400.0)
				.constroi();
		
		//parte 2: acao
		leiloeiro.avalia(leilao);
		
		//parte 3: validacao	
		assertThat(leiloeiro.getMaiorLance(), equalTo(400.0));
		assertThat(leiloeiro.getMenorLance(), equalTo(200.0));
		assertThat(leiloeiro.getMediaLance(), equalTo(300.0));
	}
	
	@Test
	public void deveEntenderApenasUmLance() {
		
		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 NOVO")
				.lance(joao, 1000.0)
				.constroi();
		
		
		leiloeiro.avalia(leilao);
		
		assertThat(leiloeiro.getMaiorLance(), equalTo(1000.0));
		assertThat(leiloeiro.getMenorLance(), equalTo(1000.0));
		
		
	}
	
	@Test
	public void deveEntenderLancesEmOrdemAleatoria() {
		
		Leilao leilao = new CriadorDeLeilao().para("Geladeira")
				.lance(joao, 120)
				.lance(jose, 200)
				.lance(maria, 230)
				.lance(joao, 400)
				.lance(jose, 450)
				.lance(maria, 510)
				.lance(jose, 510)
				.lance(joao, 700)
				.constroi();
				
				
		leiloeiro.avalia(leilao);
		
		assertThat(leiloeiro.getMenorLance(), equalTo(120.0));
		assertThat(leiloeiro.getMaiorLance(), equalTo(700.0));
		
	}
	
	@Test
	public void deveEcontrarOsMaioresLances() {
		
		Leilao leilao = new CriadorDeLeilao().para("Geladeira")
				.lance(joao, 120)
				.lance(jose, 200)
				.lance(maria, 230)
				.lance(joao, 400)
				.lance(jose, 450)
				.constroi();
		
		leiloeiro.avalia(leilao);
		
		assertThat(leiloeiro.getTresMaiores(), hasItems(
					new Lance(jose, 450),
					new Lance(joao, 400),
					new Lance(maria, 230)
				));
		
	}
	
	@Test
	public void deveEcontrarDoisLancesEDevolver() {
		
		Leilao leilao = new CriadorDeLeilao().para("Geladeira")
				.lance(joao, 120)
				.lance(jose, 200)
				.constroi();
		
		leiloeiro.avalia(leilao);
		
		assertThat(leiloeiro.getTresMaiores().get(0).getValor(), equalTo(200.0));
		assertThat(leiloeiro.getTresMaiores().get(1).getValor(), equalTo(120.0));
		
	}
	
	@Test
	public void deveEncontrarOlanceDado() {
		
		Leilao leilao = new CriadorDeLeilao()
				.para("PlayStation 4 Pro")
				.constroi();
		
		assertEquals(0, leilao.getLances().size());
		
		Lance lance = new Lance(maria, 12000);
		
		leilao.propoe(lance);		
		
		assertThat(leilao, temUmLance(lance));
		
	}

}
