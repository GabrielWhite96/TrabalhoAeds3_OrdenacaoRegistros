package classes;

import java.util.List;

public class TabelaRegisters extends Tabela{
	private List<Registro> registros;
	private int quantidade;

	// Construtor
	public TabelaRegisters(String path, int quantidade) {
		this.registros = AllFunctions.lerRegistros(path); 
		this.quantidade = quantidade;
		criarEsqueleto();
		inserirDezPrimeiros();
	}

	// Cria esqueleto da tabela
	private void criarEsqueleto(){
		adicionaLinha("ID     CPF            SEXO IDADE NOME                                           CEP(ordenado)");
		adicionaLinha("------ -------------- ---- ----- ---------------------------------------------  -------------");
	}

	// insere numero de registros pedidos
	private void inserirDezPrimeiros(){
		for(int i=0; i<this.quantidade; i++){
			inserirRegistro(i);
		}
		adicionaLinha("");
	}

	// Insere registros na tabela
	private void inserirRegistro(int index){
		Registro registro = this.registros.get(index);
		int indexLinha = index+2;
		
		adicionaLinha(inteiroPelaDireita(registro.getId(), 6));
		adicionarEspacos(indexLinha, 1);

		adicionaNaUltimaLinha(registro.getCep());
		adicionarEspacos(indexLinha, 1);
		
		adicionaNaUltimaLinha(String.valueOf(registro.getSexo()));
		adicionarEspacos(indexLinha, 4);

		adicionaNaUltimaLinha(Integer.toString(registro.getIdade()));
		adicionarEspacos(indexLinha, 4);

		adicionaNaUltimaLinha(registro.getNome());
		normalizaLinha(indexLinha, 80);

		adicionaNaUltimaLinha(registro.getCpf());
	}
}
