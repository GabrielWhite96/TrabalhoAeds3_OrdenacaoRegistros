package classes;
import java.io.BufferedWriter;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;


public class CsvRegistros {
    List<Registro> registros;
	String path;
    int numeroRegistros;
    int numComp [];
    int numTroca [];
    int numAcesso [];
    int numAcessoTemporario;

    //Construtor do Csv
	public CsvRegistros(String path, int numeroRegistros){
        this.numComp = new int[10];
        this.numTroca = new int[10];
        this.numAcesso = new int[10];
        this.numeroRegistros = numeroRegistros; //declara o numero de registros a ser criado
        this.deletarArquivo(path); //deleta os arquivos que ja existe
		this.registros = new ArrayList<Registro>(); //cria a lista de registros
		this.path = path; //declara o caminho inicial
        this.criaRegistroCsv(this.numeroRegistros); //cria o arquivo csv passando como paramentro o numero de registros a ser criado
        this.numAcessoTemporario++;
        this.escreverArquivoCsv(this.path, false); //preenche o arquivo com todos os registros
        this.numAcesso[0] = this.numAcessoTemporario;
        this.numAcessoTemporario= 0;
		this.registros = new ArrayList<Registro>(); //Limpa a Lista de registros
	}

    

    public void toStringConts(){
        System.out.println("===============================================================");
        System.out.println("|                  Acessos     Trocas     Comparações         |");
        System.out.println("| Arquivo Inicial: " + this.numAcesso[0] + AllFunctions.addEspaço(10) + this.numTroca[0] + AllFunctions.addEspaço(9) + this.numComp[0] + AllFunctions.addEspaço(18) +"|");
        for(int i = 1; i <=8; i++){
            System.out.println("| Caminho " + i + ": " +AllFunctions.addEspaço(5)+ this.numAcesso[i] + AllFunctions.addEspaço(10) + this.numTroca[i] +AllFunctions.addEspaço(6)+ this.numComp[i] +AllFunctions.addEspaço(15)+ "|");
        }
        System.out.println("| Arquivo Final: " +AllFunctions.addEspaço(1)+ this.numAcesso[9] + AllFunctions.addEspaço(7) + this.numTroca[9] + AllFunctions.addEspaço(9) + this.numComp[9] + AllFunctions.addEspaço(18) +"|");
        System.out.println("===============================================================\n");
    }

    //Funcao apenas para deletar os arquivos existentes
    public void deletarArquivo(String path){
        deletar(path);
        for(int i = 1; i <=8; i++){
            deletar("arquivos//caminho" + i + ".csv");
        }
    }
    private static void deletar(String path){
        File arquivo = new File(path);
        arquivo.delete();
    }

    //Funcao ler CSV usada para preencher a matriz
	public void lerArquivoCsv(String path, List<Registro> registros){
        String csvArquivo = path;
        this.numAcessoTemporario++;

		try(BufferedReader conteudoCSV = new BufferedReader(new FileReader(csvArquivo))){
            String linha = conteudoCSV.readLine();
            while(linha != null){
                registros.add(stringToRegistro(linha));
                linha = conteudoCSV.readLine();
            }
            
		}
		catch(IOException erro){
            System.out.println("Deu erro ai doidão: " + erro);
		}
	}
    
    //Funcao ler CSV usada para criar os caminhos
    public void lerArquivoCsv(int start, int end){
        String csvArquivo = path;
        this.numAcessoTemporario++;
        
		try(BufferedReader conteudoCSV = new BufferedReader(new FileReader(csvArquivo))){
            String linha = conteudoCSV.readLine();
            int contAux = 0;
            while(linha != null){
                contAux++;
                if(contAux >= start && contAux <= end){
                    this.registros.add(stringToRegistro(linha));
                }
                linha = conteudoCSV.readLine();
            }
		}
		catch(IOException erro){
            System.out.println("Tudo errado faz denovo: " + erro);
		}
    }
    
    //Funcao para preencher o arquivo
    private void escreverArquivoCsv(String path, boolean add){
        this.numAcessoTemporario++;
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(path, add))){
            for(Registro linha : this.registros){
                bw.write(this.registroToString(linha));
                bw.newLine();
            }
        }
        catch(IOException erro){
            erro.printStackTrace();
        }
    }

    //Funcao que divide o arquivo em 8 partes
    public void criarCaminhos(){
        int start = 1;
        int end = this.numeroRegistros/8;
        for(int i=1; i <= 8; i++){
            this.lerArquivoCsv(start , end);
            MergeSort caminho = new MergeSort(this.registros);
            this.numComp[i] = caminho.getComp();
            this.numTroca[i] = caminho.getTroca();
            this.escreverArquivoCsv("arquivos//caminho"+i+".csv", false);
            this.numAcesso[i] = this.numAcessoTemporario;
            this.numAcessoTemporario = 0;
            this.registros = new ArrayList<Registro>();
            start += this.numeroRegistros/8;
            end += this.numeroRegistros/8;
        }
    }

    public void intercalarCaminhos(){
        List<List<Registro>> listaRegistros = new ArrayList<List<Registro>>();
		for(int i = 0; i < 8; i++){
			listaRegistros.add(new ArrayList<Registro>());
			this.lerArquivoCsv("arquivos//caminho" + (i + 1) + ".csv", listaRegistros.get(i));
		}
		intercala(listaRegistros);
        this.numAcesso[9] = this.numAcessoTemporario;
        this.numAcessoTemporario = 0;
    }
    
    private void intercala(List<List<Registro>> lista){
        Registro menor = lista.get(0).get(0);
		int indexMenor = 0;
		for(int i = 0; i < lista.size(); i++){
			if(i == 0 && lista.get(i).get(0) != null){
				menor = lista.get(i).get(0);
				indexMenor = i;
			}else if(MergeSort.comparaRegistros(menor, lista.get(i).get(0)) == 1 && lista.get(i).get(0) != null){
				indexMenor = i;
				menor = lista.get(i).get(0);
			}
		}
		this.registros.add(menor);
		lista.get(indexMenor).remove(0);
		this.escreverArquivoCsv("arquivos//AllRegistersOrdered.csv", true);
		this.registros.remove(0);
		this.removeListaVazia(lista);
		if(!lista.isEmpty()){
			intercala(lista);
		}
    }
    
    private void removeListaVazia(List<List<Registro>> registros){
        for(int i = 0; i < registros.size(); i++){
            if(this.ListaEVazia(registros.get(i))){
                registros.remove(i);
            }
        }
    }

    private boolean ListaEVazia(List<Registro> listaRegistros){
        boolean resposta = true;
        for(Registro registro : listaRegistros){
            if(registro != null){
                resposta = false;
            }
        }
        return resposta;
    }
    
	private void criaRegistroCsv(int quantidade){
        for(int i = 0; i < quantidade; i++){   
            Registro registro = gera_registro(AllFunctions.geraNumeroAleatorio(100000, 800000));
            this.registros.add(registro);
        }
	}
	
	//Funcao que passa o registra para uma String
    private String registroToString(Registro registro){
        return (String) "" + registro.getId() + ";" + registro.getCpf() + ";" + registro.getNome() + ";" + registro.getIdade() + ";" + registro.getSexo() + ";" + registro.getCep();
    }

    //Funcao usada para ler o arquivo splitando em ;
    public Registro stringToRegistro(String linha){
        String separadorCampos = ";";
        String[] separados = linha.split(separadorCampos);
        Registro registro = new Registro();
        registro.setId(Integer.parseInt(separados[0]));
        registro.setCpf((separados[1]));
        registro.setNome((separados[2]));
        registro.setIdade(Integer.parseInt(separados[3]));
        registro.setSexo((separados[4].charAt(0)));
        registro.setCep((separados[5]));
        return registro;
    }

    //Funcao que cria cada registro puxando funcoes de allFunctions
	private Registro gera_registro(int id){
		Registro registro = new Registro();
		registro.setId(id);
		registro.setCpf(AllFunctions.gera_cpf());
		registro.setNome(AllFunctions.gera_nome());
		registro.setIdade(AllFunctions.gera_idade());
		registro.setSexo(AllFunctions.gera_sexo());
		registro.setCep(AllFunctions.gera_cep());
		return registro;
	}









}
