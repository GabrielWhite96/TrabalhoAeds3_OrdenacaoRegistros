package classes;

import java.util.ArrayList;
import java.util.List;

public class Registro {
    
    private int Id;
    private String Cpf;
    private String Nome;
    private int Idade;
    private char Sexo;
    private String Cep;
    
    //Construtores
    public Registro(int Id,  String Cpf, String Nome, int Idade, char Sexo, String Cep) {
        this.Id = Id;
		this.Cpf = Cpf;
		this.Idade = Idade;
		this.Sexo = Sexo;
		this.Nome = Nome;
		this.Cep = Cep;
	}
    public Registro() {
        this.Id = 0;
        this.Cpf = "";
        this.Nome = "";
        this.Idade = 0;
        this.Sexo = ' ';
        this.Cep = "";
    }

    
    //Metodos getter and setter
    public int getId() {
        return Id;
    }
    public void setId(int id) {
        Id = id;
    }
    public String getNome() {
        return Nome;
    }
    public void setNome(String nome) {
        Nome = nome;
    }
    public int getIdade() {
        return Idade;
    }
    public void setIdade(int idade) {
        Idade = idade;
    }
    public char getSexo() {
        return Sexo;
    }
    public void setSexo(char sexo) {
        Sexo = sexo;
    }
    public String getCpf() {
        return Cpf;
    }
    public void setCpf(String cpf) {
        Cpf = cpf;
    }
    public String getCep() {
        return Cep;
    }
    public void setCep(String cep) {
        Cep = cep;
    }
    
    //toString do id
    @Override
    public String toString() {
        return "Registro [Id=" + Id + ",  + Cep=" + Cep + ", Nome=" + Nome + ", Idade=" + Idade + ", Sexo=" + Sexo + ", Cpf=" + Cpf + "]";
    }

    static Registro fromCSV(String csvString){
		String[] atributos = csvString.split(";");
		Registro registro = new Registro();
		registro.setId(Integer.parseInt(atributos[0]));
		registro.setCep(atributos[1]);
		registro.setNome(atributos[2]);
		registro.setIdade(Integer.parseInt(atributos[3]));
		registro.setSexo(atributos[4].charAt(0));
		registro.setCpf(atributos[5]);
		return registro;
	}

	static List<Registro> manyFromCSV(String[] linhas){
		List<Registro> registros = new ArrayList<Registro>();
		for (String linha : linhas){
			registros.add(fromCSV(linha));
		}
		return registros;
	}

	public String toCSV(){
		return String.join( ";", 
		String.valueOf(getId()), 
		getCep(), 
		getNome(), 
		String.valueOf(getIdade()), 
		String.valueOf(getSexo()), 
		getCpf());
	}



}
