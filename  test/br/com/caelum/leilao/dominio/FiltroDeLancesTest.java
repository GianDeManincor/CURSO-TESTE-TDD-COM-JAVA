package br.com.caelum.leilao.dominio;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class FiltroDeLancesTest {
	
	@Test
    public void deveSelecionarLancesEntre1000E3000() {
        Usuario joao = new Usuario("Joao");

        FiltroDeLances filtro = new FiltroDeLances();
        List<Lance> resultado = filtro.filtra(Arrays.asList(
                new Lance(joao,2000), 
                new Lance(joao,1000), 
                new Lance(joao,3000), 
                new Lance(joao, 800)));

        assertEquals(1, resultado.size());
        assertEquals(2000, resultado.get(0).getValor(), 0.00001);
    }

    @Test
    public void deveSelecionarLancesEntre500E700() {
        Usuario joao = new Usuario("Joao");

        FiltroDeLances filtro = new FiltroDeLances();
        List<Lance> resultado = filtro.filtra(Arrays.asList(
                new Lance(joao,600), 
                new Lance(joao,500), 
                new Lance(joao,700), 
                new Lance(joao, 800)));

        assertEquals(1, resultado.size());
        assertEquals(600, resultado.get(0).getValor(), 0.00001);
    }
    
    @Test
    public void deveSelecionarLancesMaioresQue5000() {
    	Usuario joao = new Usuario("João");
    	
    	FiltroDeLances filtro = new FiltroDeLances();
    	List<Lance> resultado = filtro.filtra(Arrays.asList(
    			new Lance(joao, 200),
    			new Lance(joao, 3800),
    			new Lance(joao, 5100)));
    	
    	assertEquals(1, resultado.size());
    	assertEquals(5100, resultado.get(0).getValor(), 0.0001);
    	
    }
    
    @Test
    public void deveDevolverVazioSeForMenorQue500() {
    	Usuario joao = new Usuario("João");
    	
    	FiltroDeLances filtro = new FiltroDeLances();
    	List<Lance> resultado = filtro.filtra(Arrays.asList(
    			new Lance(joao, 200),
    			new Lance(joao, 300)));
    	
    	assertEquals(0, resultado.size());	
    }
    
    @Test
    public void deveDevolverVazioSeForMaiorQue700MenorQue1000() {
    	Usuario joao = new Usuario("João");
    	
    	FiltroDeLances filtro = new FiltroDeLances();
    	List<Lance> resultado = filtro.filtra(Arrays.asList(
    			new Lance(joao, 710),
    			new Lance(joao, 900)));
    	
    	assertEquals(0, resultado.size());	
    }
    
    @Test
    public void deveDevolverVazioSeForMaiorQue3000MenorQue5000() {
    	Usuario joao = new Usuario("João");
    	
    	FiltroDeLances filtro = new FiltroDeLances();
    	List<Lance> resultado = filtro.filtra(Arrays.asList(
    			new Lance(joao, 4000),
    			new Lance(joao, 4800)));
    	
    	assertEquals(0, resultado.size());	
    }

}
