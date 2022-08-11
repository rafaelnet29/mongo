package com.br.mongo.start;

import com.br.mongo.mongoDataAccess;
import javax.swing.JOptionPane;

public class mongoOpen {

    public static void main(String[] args) {
        mongoDataAccess db = new mongoDataAccess();
        db.Connect();
        int opc = 0;
        char op;
        /**
         * Mostra o menu inicial chamando todos os métodos para iniciar a
         * aplicação
         */
        try {
            do {
                opc = Integer.parseInt(JOptionPane.showInputDialog(null, " --> Escolha uma opção:"
                        + " <-- \n"
                        + "1 - Inserir Único\n"
                        + "2 - Listar Vários\n"
                        + "3 - Buscar\n"
                        + "4 - Atualizar\n"
                        + "5 - Deletar\n"
                        + "6 - Inserir Vários\n"
                        + "0 - Sair", " Menu ", 1));

                if (opc == 1) {
                    db.InsertOne();
                } else if (opc == 2) {
                    db.FindAll();
                } else if (opc == 3) {
                    db.findOne();
                } else if (opc == 4) {
                    db.updateOne();
                } else if (opc == 5) {
                    db.deleteOne();
                }else if(opc == 6){
                    db.InsertMany();
                } else {
                    JOptionPane.showMessageDialog(null, " Até mais!");
                }
            } while (opc != 0);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Formato invalido de opção ", "Opções entre 0 e 5 ", 1);
        }
    }//main
}//class
