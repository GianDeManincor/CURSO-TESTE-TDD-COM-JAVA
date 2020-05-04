package br.com.caelum.leilao.dominio;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LeilaoTest {

	@Test
	public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {

		Usuario joao = new Usuario("João");
		
		Leilao leilao = new Leilao("Macbook pro");
		leilao.propoe(new Lance(joao, 500));
		leilao.propoe(new Lance(joao, 600));
		
		assertEquals(1, leilao.getLances().size());		
		assertEquals(500, leilao.getLances().get(0).getValor(), 0.00001);	
		}

	@Test
	public void naoDeveAceitarMaisDoQue5LancesDeUmMesmoUsuario() {
		
		Usuario joao = new Usuario("João");
		Usuario carlos = new Usuario("Carlos");
		
		Leilao leilao = new Leilao("Macbook pro");
		leilao.propoe(new Lance(joao, 500));
		leilao.propoe(new Lance(carlos, 600));
		
		leilao.propoe(new Lance(joao, 700));
		leilao.propoe(new Lance(carlos, 800));
		
		leilao.propoe(new Lance(joao, 900));
		leilao.propoe(new Lance(carlos, 1000));
		
		leilao.propoe(new Lance(joao, 1100));
		leilao.propoe(new Lance(carlos, 1200));
		
		leilao.propoe(new Lance(joao, 1300));
		leilao.propoe(new Lance(carlos, 1400));
		
		leilao.propoe(new Lance(joao, 1500));
		
		assertEquals(10, leilao.getLances().size());		
		assertEquals(1400, leilao.getLances().get(leilao.getLances().size() - 1).getValor(), 0.00001);

	}
	
	@Test
	public void deveEncontrarUltimoLanceEcriarUmNovoLanceComOdobroDoValor() {
		
		Leilao leilao = new Leilao("Notebook Dell");
		Usuario joao = new Usuario("João");
		Usuario igor = new Usuario("Igor");

		leilao.propoe(new Lance(joao, 400));
		leilao.propoe(new Lance(igor, 450));
		
		leilao.dobraLance(joao);
		
		assertEquals(800, leilao.getLances().get(leilao.getLances().size() - 1).getValor(), 0.00001);
		
	}
	
	@Test
	public void casoNaoTenhaNenhumLanceNaoCriaUmNovoLance() {
		
		Leilao leilao = new Leilao("Notebook Dell");
		Usuario joao = new Usuario("João");
		
		leilao.dobraLance(joao);
		
		assertEquals(0, leilao.getLances().size());
		
	}

}
