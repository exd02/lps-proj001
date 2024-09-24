package main;

import model.DBConnection;
import view.FrAluno;

public class Main {
    public static void main(String[] args) {
        DBConnection.setupTables();
        FrAluno frAluno = new FrAluno();
    }
}