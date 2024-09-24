package model.dao;

import model.Aluno;
import model.DBConnection;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class AlunoDAO implements DAO<Aluno> {

    @Override
    public void insert(Aluno o) {
        String sql = "INSERT INTO Aluno(nome, idade, sexo, matricula) VALUES (?,?,?,?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, o.getNome());
            stmt.setInt(2, o.getIdade());
            stmt.setString(3, String.valueOf(o.getSexo()));
            stmt.setString(4, o.getMatricula());

            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Aluno a) {
        String sql = "UPDATE Aluno SET " +
                    "nome = ?, " +
                    "idade = ?, " +
                    "sexo = ? " +
                    "WHERE matricula = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, a.getNome());
            stmt.setInt(2, a.getIdade());
            stmt.setString(3, String.valueOf(a.getSexo()));
            stmt.setString(4, a.getMatricula());

            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Aluno o) {
        String sql = "DELETE FROM Aluno WHERE matricula = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, o.getMatricula());

            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Aluno getById(String id) {
        String sql = "SELECT * FROM Aluno WHERE matricula = ?";
        Aluno aluno = null;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                aluno = new Aluno (
                        rs.getString(1),
                        rs.getInt(2),
                        rs.getString(3).charAt(0),
                        rs.getString(4)
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return aluno;
    }

    @Override
    public List<Aluno> getAll() {
        String sql = "SELECT * FROM Aluno";
        List<Aluno> alunoList = new ArrayList<Aluno>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery() ) {

            while (rs.next()) {
                Aluno aluno = new Aluno(
                        rs.getString("nome"),
                        rs.getInt("idade"),
                        rs.getString("sexo").charAt(0),
                        rs.getString("matricula")
                );
                alunoList.add(aluno);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return alunoList;
    }
}
