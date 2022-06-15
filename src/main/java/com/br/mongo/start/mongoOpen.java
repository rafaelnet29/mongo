package com.br.mongo.start;

import com.br.mongo.mongoDataAccess;
import javax.swing.JOptionPane;

public class mongoOpen {

    private final mongoDataAccess db;
    
    private int opc = 0;

    public mongoOpen() {
        db = new mongoDataAccess();
        db.Connect();
    }

    /**
     * Mostra o menu inicial chamando todos os métodos para iniciar a aplicação
     */
    public void Start() {

        try {
            do {
                opc = Integer.parseInt(JOptionPane.showInputDialog(null, " --> Escolha uma opção:"
                        + " <-- \n"
                        + "1 - Inserir " + " | " + "2 - Listar\n"
                        + "3 - Buscar "  + " | " + " 4 - Atualiza\n"
                        + "5 - Deletar " + " | " + " 0 - Sair ", " Menu ", 1));

                if (opc == 1) {
                    db.InsertOneDoc();
                } else if (opc == 2) {
                    db.FindAll();
                } else if (opc == 3) {
                    db.findOne();
                } else if (opc == 4) {
                    db.updateOne();
                }else if(opc == 5){
                    db.deleteOne();
                }else {
                    JOptionPane.showMessageDialog(null, " Até mais!");
                }
            } while (opc != 0);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Formato invalido de opção ", "Opções entre 0 e 5 " , 1);
            Start();
        }
    }
}
