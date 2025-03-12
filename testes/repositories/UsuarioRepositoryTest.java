package repositories;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import entities.Dica;
import entities.Usuario;

class UsuarioRepositoryTest {
	private UsuarioRepository rp;
	private Usuario user1;
	private Usuario user2;
	private Usuario user3;
	private Dica dica1;
	private Dica dica2;
	private Dica dica3;
	
	@BeforeEach
	void setup() {
		this.rp = new UsuarioRepository();
		this.user1 = new  Usuario("Fulano", "123.456.789-10", "1234", "123456");
		this.dica1 = new Dica(user1, "Monitoria");
		this.dica2 = new Dica(user1, "Estagio");
		this.user2 = new  Usuario("Cicrano", "123.456.789-17", "1234", "123450");
		this.dica3 = new Dica(user2, "PesquisaExtensao");
		this.user3 = new  Usuario("Beltrano", "123.456.789-10", "1234", "123456");
	}
	
	@Test
	void testAdicionaEstudante() {
		assertTrue(rp.adicionaEstudante(user1));
		assertTrue(rp.adicionaEstudante(user2));
		assertFalse(rp.adicionaEstudante(user1));
	}

	@Test
	void testListaEstudantes() {
		rp.adicionaEstudante(user1);
		String[] array = {"Nome: Fulano, matrícula: 123456"};
		assertTrue(Arrays.equals(array, rp.listaEstudantes()));
		
		rp.adicionaEstudante(user2);
		String[] array2 = {"Nome: Cicrano, matrícula: 123450", "Nome: Fulano, matrícula: 123456"};
		assertTrue(Arrays.equals(array2, rp.listaEstudantes()));
	}

	@Test
	void testListaEstudantesRankingDicas() {
		rp.adicionaEstudante(user1);
		String[] array = {"Nome: Fulano, matrícula: 123456"};
		assertTrue(Arrays.equals(array, rp.listaEstudantesRankingDicas()));
		
		rp.adicionaEstudante(user2);
		String[] array2 = {"Nome: Cicrano, matrícula: 123450", "Nome: Fulano, matrícula: 123456"};
		assertTrue(Arrays.equals(array2, rp.listaEstudantesRankingDicas()));
	}

	@Test
	void testBuscaEstudante() {
		rp.adicionaEstudante(user1);
		rp.adicionaEstudante(user2);
		
		assertEquals(user1, rp.buscaEstudante("123.456.789-10", "1234"));
		assertEquals(user2, rp.buscaEstudante("123.456.789-17", "1234"));
		
		try {
			rp.buscaEstudante("123.456.789-19", "1234");
			fail("Usuário ou senha inválidos");
		} catch (Exception e) {}
		
	}

}
