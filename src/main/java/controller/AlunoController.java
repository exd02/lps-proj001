package controller;

import model.Aluno;
import model.dao.AlunoDAO;
import model.exception.AlunoException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class AlunoController {
    private AlunoDAO alunoDAO;

    public AlunoController() {
        alunoDAO = new AlunoDAO();
    }

    public void inserirAluno(String nome, String idade, String sexo, String matricula) {
        Aluno novoAluno = Aluno.validateFields(nome, idade, sexo, matricula);

        if (alunoDAO.getById(novoAluno.getMatricula()) != null) {
            throw new AlunoException("[ERROR] - Já existe Aluno com a matrícula (PK) \"" + matricula + "\"!");
        }

        alunoDAO.insert(novoAluno);
    }

    public void deleteAlunos(List<Aluno> aList) {
        for (Aluno a :aList) {
            alunoDAO.delete(a);
        }
    }

    public void updateAluno(String nome, String idade, String sexo, String matricula) {
        Aluno novoAluno = Aluno.validateFields(nome, idade, sexo, matricula);

        if (alunoDAO.getById(novoAluno.getMatricula()) == null) {
            throw new AlunoException("[ERROR] - Não existe aluno com a matrícula \"" + matricula + "\"!");
        }

        alunoDAO.update(novoAluno);
    }

    public void refreshTable(JTable table) {
        String[] cols = {"Nome", "Idade", "Sexo", "Matricula"};
        DefaultTableModel model = new DefaultTableModel(cols, 0);

        List<Aluno> alunoList = alunoDAO.getAll();

        for (Aluno aluno : alunoList) {
            model.addRow(aluno.toStrArray());
        }

        table.setModel(model);
    }

}
