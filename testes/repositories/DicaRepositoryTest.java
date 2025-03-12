package repositories;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import entities.Dica;
import entities.Usuario;

class DicaRepositoryTest {
	private DicaRepository repo;
	private Usuario user;
	private Dica dica1;
	private Dica dica2;
	private Dica dica3;
	private Dica dica4;
	
	@BeforeEach
	void setup() {
		this.repo = new DicaRepository();
		this.user = new Usuario("Fulano", "123.456.789-10", "1234", "123456");
		
		this.dica1 = new Dica(user, "Monitoria");
		this.dica2 = new Dica(user, "Estagio");
		this.dica3 = new Dica(user, "PesquisaExtensao");
		this.dica4 = new Dica(user, "RepresentacaoEstudantil");
		
	}
	
	@Test
	void testAdicionaDica() {
		assertEquals(1, this.repo.adicionaDica(dica1));
		assertEquals(2, this.repo.adicionaDica(dica2));
		assertEquals(3, this.repo.adicionaDica(dica3));
		assertEquals(4, this.repo.adicionaDica(dica4));

	}

	@Test
	void testListaDicas() {	
		this.repo.adicionaDica(dica1);
		assertEquals("Autor: Fulano, tema: Monitoria", repo.listaDicas()[0]);
			
		this.repo.adicionaDica(dica2);
		assertEquals("Autor: Fulano, tema: Estagio", repo.listaDicas()[1]);
		
		
	}

	@Test
	void testListaDicasDetalhes() {
		this.repo.adicionaDica(dica1);
		assertEquals("Autor: Fulano", repo.listaDicasDetalhes()[0]);
			
		this.repo.adicionaDica(dica2);
		assertEquals("Autor: Fulano", repo.listaDicasDetalhes()[1]);
	}

	@Test
	void testListaDica() {
		this.repo.adicionaDica(dica1);
		assertEquals("Autor: Fulano, tema: Monitoria", repo.listaDica(1));
			
		this.repo.adicionaDica(dica2);
		assertEquals("Autor: Fulano, tema: Estagio", repo.listaDica(2));
	}

	@Test
	void testListaDicaDetalhes() {
		this.repo.adicionaDica(dica1);
		try {
			assertEquals(dica1, repo.listaDicaDetalhes(0));
			fail("A posição da dica é inválida");
		} catch (Exception e) {}
		
		assertEquals("Autor: Fulano", repo.listaDicaDetalhes(1));
		
		this.repo.adicionaDica(dica2);
		assertEquals("Autor: Fulano", repo.listaDicaDetalhes(2));
	}
	
	

	@Test
	void testBuscaDica() {
		this.repo.adicionaDica(dica1);
		
		try {
			assertEquals(dica1, repo.buscaDica(0));
			fail("A posição da dica é inválida");
		} catch (Exception e) {}
		
		assertEquals(dica1, repo.buscaDica(1));
		this.repo.adicionaDica(dica2);
		this.repo.adicionaDica(dica3);
		assertEquals(dica2, repo.buscaDica(2));
		assertEquals(dica3, repo.buscaDica(3));
	}

}
