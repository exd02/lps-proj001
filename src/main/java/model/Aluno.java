package model;

import model.exception.AlunoException;

import javax.swing.*;

public class Aluno {
    private String nome;
    private int idade;
    private char sexo;
    private String matricula;

    public Aluno() {
        this.nome = "";
        this.idade = 0;
        this.sexo = 'M';
        this.matricula = "0";
    }

    public Aluno(String nome, int idade, char sexo, String matricula) {
       this.nome = nome;
       this.idade = idade;
       this.sexo = sexo;
       this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public char getSexo() {
        return sexo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public static Aluno validateFields(String nome, String idade, String sexo, String matricula) {
        nome = nome.strip();
        idade = idade.strip();
        sexo = sexo.strip();
        matricula = matricula.strip();

        if (nome.isEmpty()) {
            throw new AlunoException("[ERROR] - \"nome\" field invalido!");
        }

        if (idade.isEmpty() || !idade.matches("[0-9]+")) {
            throw new AlunoException("[ERROR] - \"idade\" field invalido!");
        }

        if (sexo.isEmpty()) {
            throw new AlunoException("[ERROR] - \"sexo\" field invalido!");
        }

        if (matricula.isEmpty()) {
            throw new AlunoException("[ERROR] - \"matricula\" field invalido!");
        }

        return new Aluno(nome, Integer.parseInt(idade), sexo.charAt(0), matricula);
    }

    public String[] toStrArray() {
        return new String[]{
            nome,
            String.valueOf(idade),
            String.valueOf(sexo),
            matricula
        };
    }
}

