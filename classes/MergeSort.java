package classes;

import java.util.ArrayList;
import java.util.List;

public class MergeSort {
    private List <Registro>registrosMerge;
    private int contTroca=0;
    private int contComparacao=0;

    //construtor do MergeSort
    public MergeSort(List <Registro> registros){
        this.registrosMerge = new ArrayList<Registro>(registros);
        mergeSort(registros, this.registrosMerge,0 , registros.size()-1);
        this.getTroca();
        this.getComp();
    }
    
    //metodos gets para os contadores
    public int getTroca(){
        return this.contTroca;
    }
    public int getComp(){
        return this.contComparacao;
    }
    
    //Funcao que realiza as trocas do MergeSort
    public void merge(List<Registro> registros, List<Registro> registrosMerge, int inicio, int meio, int fim) {
        for(int k = inicio; k <= fim; k++) {
            registrosMerge.set(k, registros.get(k));
        }
        int i = inicio;
        int j = meio + 1;
        
        for(int k = inicio; k <= fim; k++) {
            if(i > meio) {
                registros.set(k, registrosMerge.get(j++));
                this.contTroca++;
                this.contComparacao++;

            }else if (j > fim){
                registros.set(k, registrosMerge.get(i++));
                this.contTroca++;
                this.contComparacao += 2;

            }else if(comparaRegistros(registrosMerge.get(i), registrosMerge.get(j)) == -1) {
                registros.set(k, registrosMerge.get(i++));
                this.contTroca++;
                this.contComparacao += 3;
            }else {
                registros.set(k, registrosMerge.get(j++));
                this.contTroca++;
                this.contComparacao += 3;
            }
        }
    }

    //Funcao para realizar o metodo MergeSort
    public void mergeSort(List<Registro> registros, List<Registro> registrosMerge, int inicio, int fim) {
        if(inicio < fim) {
            this.contComparacao++;
            int meio = (inicio + fim) / 2;
            mergeSort(registros, registrosMerge, inicio, meio);
            mergeSort(registros, registrosMerge, meio + 1, fim);
            merge(registros, registrosMerge, inicio, meio, fim);
        }
    }

    //Funçoes que vai comparar os registros apartir do CEP
    public static int comparaRegistros(Registro a, Registro b){
        int cepA = Integer.parseInt(a.getCep().replace("-", ""));
        int cepB = Integer.parseInt(b.getCep().replace("-", ""));
        int resposta = 1;
        if(cepA < cepB){
            resposta = -1;
        }else if(cepA == cepB){
            if(a.getSexo() == 'F' && b.getSexo() == 'M'){
                resposta = -1;
            }else if(a.getSexo() == b.getSexo()){
                if(a.getIdade() < b.getIdade()){
                    resposta = -1;
                }else if(a.getIdade() == b.getIdade()){
                    int ordem = a.getNome().compareTo(b.getNome());
                    if(ordem == (-2)){
                        resposta = -1;
                    }else if(ordem == 0){
                        resposta = 0;
                    }else{
                        resposta = 1;
                    }
                }
            }
        }
        return resposta;
    }
}
