package view;

import controller.AlunoController;
import model.Aluno;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class FrAluno extends JFrame {

    private JPanel panel1;
    private JPanel mainPanel;
    private JLabel labelTitle;
    private JButton cadastrarButton;
    private JButton alterarButton;
    private JButton excluirButton;
    private JButton cancelarOuNovoButton;
    private JButton salvarButton;
    private JPanel paneForms;
    private JPanel paneContent;
    private JPanel leftForm;
    private JPanel rightForm;
    private JTextField textFieldNome;
    private JTextField textFieldMatricula;
    private JTextField textFieldIdade;
    private JTable tableAlunos;
    private JComboBox comboBoxSexo;
    private JScrollPane scrollPaneTable;

    private boolean isFormActive;
    private final AlunoController alunoController;
    private boolean isEditing = false;

    public FrAluno() {
        setContentPane(mainPanel);
        setTitle("CRUD Aluno");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setVisible(true);

        isFormActive = true;

        alunoController = new AlunoController();
        alunoController.refreshTable(tableAlunos);

        swapForm();

        cancelarOuNovoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textFieldMatricula.setEditable(true);
                swapForm();
                cleanForms();
                isEditing = false;
            }
        });
        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isEditing) {
                    alunoController.updateAluno(textFieldNome.getText(), textFieldIdade.getText(), (String) comboBoxSexo.getSelectedItem(), textFieldMatricula.getText());
                } else {
                    alunoController.inserirAluno(textFieldNome.getText(), textFieldIdade.getText(), (String) comboBoxSexo.getSelectedItem(), textFieldMatricula.getText());
                }

                alunoController.refreshTable(tableAlunos);
                cleanForms();
                swapForm();
            }
        });
        excluirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Aluno> aList = getSelectedsObjectsInTable();

                if (aList.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Selecione um registro para exclusão", "Erro!", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                alunoController.deleteAlunos(aList);
                alunoController.refreshTable(tableAlunos);
            }
        });
        alterarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Aluno> aList = getSelectedsObjectsInTable();

                if (aList.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Selecione um registro para edição", "Erro!", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                swapForm();
                isEditing = true;

                Aluno aSelected = aList.getFirst();
                loadForm(aSelected);
                textFieldMatricula.setEditable(false);
            }
        });
    }

    private void cleanForms() {
        textFieldNome.setText("");
        textFieldIdade.setText("");
        comboBoxSexo.setSelectedIndex(0);
        textFieldMatricula.setText("");
    }

    private void loadForm(Aluno aluno) {
        textFieldNome.setText(aluno.getNome());
        textFieldIdade.setText(String.valueOf(aluno.getIdade()));
        comboBoxSexo.setSelectedIndex(aluno.getSexo() == 'M' ? 0 : 1);
        textFieldMatricula.setText(aluno.getMatricula());
    }

    private List<Aluno> getSelectedsObjectsInTable() {
        int[] selectedRows = tableAlunos.getSelectedRows();
        List<Aluno> aList = new ArrayList<>();

        for (int i : selectedRows) {
            String nome = (String) tableAlunos.getValueAt(i, 0);
            int idade = Integer.parseInt(((String) tableAlunos.getValueAt(i, 1)));
            char sexo = ((String) tableAlunos.getValueAt(i, 2)).charAt(0);
            String matricula = (String) tableAlunos.getValueAt(i, 3);

            aList.add(new Aluno(nome, idade, sexo, matricula));
        }

        return aList;
    }


    public void swapForm() {
        isFormActive = !isFormActive;

        salvarButton.setVisible(isFormActive);
        alterarButton.setVisible(!isFormActive);
        paneForms.setVisible(isFormActive);
        excluirButton.setVisible(!isFormActive);

        cancelarOuNovoButton.setText(isFormActive ? "Cancelar" : "Novo");
    }

}
