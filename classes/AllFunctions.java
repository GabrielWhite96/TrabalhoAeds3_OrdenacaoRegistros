package classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class AllFunctions {


    //Função que gera numero aleatorio com minimo e maximo valor
	public static int geraNumeroAleatorio(int min, int max){
        return (int) Math.floor(Math.random()*(max - min +1) + min);
    }

	//Todos os geradores de Registro
    public static String gera_nome(){
        ThreadLocalRandom gerador = ThreadLocalRandom.current(); 

        int tamanhoNome = gerador.nextInt(3, 10);
        int tamanhoSobrenome = gerador.nextInt(3, 10);
        int tamanhoSegundoSobrenome = gerador.nextInt(3, 10);
        
        char primeiraLetraNome = (char) gerador.nextInt(65, 90);
        char primeiraLetraSobreNome = (char) gerador.nextInt(65, 90);
        char primeiraLetraSegundoSobreNome = (char) gerador.nextInt(65, 90);
        
        StringBuilder name = new StringBuilder().append(primeiraLetraNome);
        StringBuilder sobreNome = new StringBuilder().append(primeiraLetraSobreNome);
        StringBuilder segundoSobreNome = new StringBuilder().append(primeiraLetraSegundoSobreNome);
        
        
        for (int t = 1; t < tamanhoNome; t++) {
            char letra = (char) gerador.nextInt(97, 122);
            name.append(letra);
        }
        
        for (int j = 1; j < tamanhoSobrenome; j++) {
            char letra = (char) gerador.nextInt(97, 122);
            sobreNome.append(letra);
        }
        
        for (int q = 1; q < tamanhoSegundoSobrenome; q++) {
            char letra = (char) gerador.nextInt(97, 122);
            segundoSobreNome.append(letra);
        }
        
        String fullName = name + " " + sobreNome + " " + segundoSobreNome;
        return fullName;
    }
    
    public static int gera_idade(){
        int idade = geraNumeroAleatorio(10, 50);
        return idade;
    }
    
    public static char gera_sexo(){
        String letras = "MF";  
        Random gerador = new Random();  
        return letras.charAt(gerador.nextInt(letras.length()));
    }

    public static String gera_cpf(){
        String fullCpf = "";
        for(int i=1; i<12; i++){
            fullCpf += Integer.toString(geraNumeroAleatorio(0, 9));
            if(i % 3 == 0 && i != 9){
                fullCpf += ".";
            }else if(i == 9){
                fullCpf += "-";
            }
        }

        return fullCpf;
    }

    public static String gera_cep(){
        String fullCep = "";
        for(int i = 1; i <= 8; i++){
            fullCep += Integer.toString(geraNumeroAleatorio(0, 9));
            if(i == 5){
                fullCep += "-";
            }
        }
        return fullCep;
    }

    public static List<String> ler(String path) {
        List<String> linhas = new ArrayList<String>();
        String linha = "";

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            while(true){
                linha = br.readLine();
                if (linha == null) { break; };
                linhas.add(linha);
            }
        }
        catch(IOException e){
            System.out.println(e);
        }
        return linhas;
    }

    public static List<Registro> lerRegistros(String path) {
        List<String> linhas = ler(path);
        return Registro.manyFromCSV(linhas.toArray(new String[0]));
    }


    public static List<Registro> lerRegistrosNoIntervalo(String path, int primeiraLinha, int ultimaLinha) {
        int quantidadeLinhas = ultimaLinha - primeiraLinha;
        String[] linhas = new String[quantidadeLinhas];

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            for (int i = 0; i < primeiraLinha; i++) {
                br.readLine();
            }

            for (int i = 0; i < quantidadeLinhas; i++){
                linhas[i] = br.readLine();
            }
        }
        catch(IOException e){
            System.out.println(e);
        }
        return Registro.manyFromCSV(linhas);
    }


    public static String addEspaço(int quantidade){
        String aux = "";
        for(int i=0; i<=quantidade; i++){
            aux += " ";
        }
        return aux;
    }
}
