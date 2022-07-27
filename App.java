import classes.AllFunctions;
import classes.Cronometro;
import classes.CsvRegistros;
import classes.TabelaRegisters;

public class App {

    private static String toStringInfos(){
        String aux = "===============================================================\n" +
        "| Aluno/Matricula: Gabriel Fernandes/2021008766               |\n" +
        "|                  Lucas Silva/15830037629                    |\n" +
        "| Curso: Ciencia da Computacao                                |\n" +
        "| 2º Trabalho Pratico -- Ordenacao Interna                    |\n" +
        "| DCC288 -- 2022 -- IFSEMG, 3o.                               |\n" +
        "| Prof. Flavio Augusto de Freitas                             |\n" +
        "| Compilador: Visual Studio Code                              |\n" +
        "| Sistema Operacional: Windows 11                             |\n" +
        "===============================================================\n" ;
        return aux;
    }
    public static void main(String[] args) {

        System.out.println(toStringInfos());

        Cronometro totalTime = new Cronometro();

        totalTime.comecar();
        CsvRegistros arquivo = new CsvRegistros("arquivos//AllRegisters.csv", 1600);
        
        Cronometro intercalaTime = new Cronometro();
        intercalaTime.comecar();
        arquivo.criarCaminhos();
        arquivo.intercalarCaminhos();
        intercalaTime.parar();

        System.out.println("10 PRIMEIROS DADOS INICIAIS:");
        (new TabelaRegisters("arquivos//AllRegisters.csv", 10)).imprimir();
        
        System.out.println("10 PRIMEIROS DADOS ORDENADOS:");
        (new TabelaRegisters("arquivos//AllRegistersOrdered.csv", 10)).imprimir();
        
        totalTime.parar();
        System.out.println("===============================================================");
        System.out.println("| RESULTADOS TEMPOS:"+AllFunctions.addEspaço(41)+"|");
        System.out.println("| Tempo Total : " + totalTime.getTempo() + "ms"+AllFunctions.addEspaço(40)+"|");
        System.out.println("| Tempos Para Intercalar Arquivos: " + intercalaTime.getTempo() + "ms"+AllFunctions.addEspaço(21)+"|");
        System.out.println("===============================================================\n");

        System.out.println("RESULTADO CONTADORES:");
        arquivo.toStringConts();
    }
}
