package classes;

import java.util.ArrayList;
import java.util.List;

public class Tabela {
    private List<String> linhas;
    
    // Construtor
    public Tabela(){
        linhas = new ArrayList<>();
    }

    // Adiciona linha na tabela
    public void adicionaLinha(String linha) {
        linhas.add(linha);
    }  

    // Adiciona em linha da tabela
    public void adicionaNaLinha(int index, String extra) {
        String linha = linhas.get(index) + extra;
        linhas.set(index, linha);
    }

    // Adiciona na última linha da tabela
    public void adicionaNaUltimaLinha(String extra) {
        int index = indexUltimaLinha();
        String linha = linhas.get(index) + extra;
        linhas.set(index, linha);
    }

    // Adiciona um certo número de espaços em uma linha
    public void adicionarEspacos(int index, int quantidade) {
        String extra = "";
        for(int i = 0; i < quantidade; i++) {
            extra += " ";
        } 
        adicionaNaLinha(index, extra);
    }

    // Acerta espaços de linha
    public void normalizaLinha(int index, int tamanhoEsperado){
        int tamanhoLinha = tamanhoLinha(index);
        int espacosFaltando = tamanhoEsperado - tamanhoLinha;
        adicionarEspacos(index, espacosFaltando);
    }

    // Retorna tamanho da linha
    public int tamanhoLinha(int index){
        return linhas.get(index).length();
    }

    // Acerta os espaços em todas as linhas
    public void normalizaTodasLinhas(int tamanhoEsperado){
        for(int i = 0; i < linhas.size(); i++) {
            normalizaLinha(i, tamanhoEsperado);
        }   
    }

    // Retorna o index da ultima linha
    public int indexUltimaLinha(){
        return linhas.size()-1;
    }

    // Verific espaços da linha
    public static String inteiroPelaDireita(int inteiro, int tamanhoString){
        String inteiroString = String.valueOf(inteiro);
        int espacosFaltando = tamanhoString - inteiroString.length();
        for(int i = 0; i < espacosFaltando; i++) {
            inteiroString = " " + inteiroString;
        } 
        return inteiroString;
    }

    // Imprime linhas da tabela
    public void imprimir(){
        for(int i = 0; i < this.linhas.size(); i++) {
            System.out.println(this.linhas.get(i));
        }
    }
}
