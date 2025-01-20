package entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import enums.TipoAtividadeComplementar;
import validators.ValidadorRelatorio;
import validators.ValidadorUsuario;

public class Relatorio {

	private Usuario estudante;
	private LocalDate data;
	private Map<TipoAtividadeComplementar, Integer> sumarizacaoDeCreditos;
	
	public Relatorio(Usuario estudante, Map<TipoAtividadeComplementar, Integer> sumarizacao) {
		ValidadorUsuario.validaUsuario(estudante);
		ValidadorRelatorio.validaSumarizacao(sumarizacao);
		
		this.estudante = estudante;
		this.data = LocalDate.now();
		this.sumarizacaoDeCreditos = sumarizacao;
	}
	
	public String data() {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return this.data.format(formato);
	}
	
	public String apresentacaoDoEstudante() {
		return this.estudante.apresentacaoEmRelatorio() + ", data " + data();
	}
	
	public int creditoPorTipoAtividade(TipoAtividadeComplementar tipo) {
		return this.sumarizacaoDeCreditos.get(tipo);
	}
	
	@Override
	public String toString() {
		String apresentacao = apresentacaoDoEstudante() + "\nCréditos por atividades:";
		for (TipoAtividadeComplementar key : this.sumarizacaoDeCreditos.keySet()) {
			apresentacao += "\n" + key.getTipo() + ": " + this.sumarizacaoDeCreditos.get(key);
		}
		apresentacao += "\nCréditos totais: " + creditosTotais();
		
		return apresentacao;
	}
	
	private int creditosTotais() {
		return this.sumarizacaoDeCreditos.values().stream().mapToInt(Integer::intValue).sum();
	}
}
