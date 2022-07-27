package classes;

public class Cronometro {
    private long tempo;

	public void comecar(){
		tempo = System.nanoTime();
	}

	public long parar(){
		tempo = System.nanoTime() - this.tempo;
        return getTempo();
	}

	public long getTempo(){
		return tempo/(long)1000000;
	}
}

