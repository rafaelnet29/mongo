package com.br.mongo.start;

import com.br.mongo.DBcon;
import javax.swing.JOptionPane;

public class Abertura {

    private final DBcon db;
    private int opc = 0;

    public Abertura() {
        db = new DBcon();
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
                        + "3 - Buscar "  + " | " + " 4 - Atualiza (Manutanção)\n"
                        + "5 - Deletar " + " | " + " 0 - Sair ", " Menu ", 1));

                if (opc == 1) {
                    db.InsertOneDoc();
                } else if (opc == 2) {
                    db.FindAll();
                } else if (opc == 3) {
                    db.findOne();
                } else if (opc == 4) {
                    db.update();
                    JOptionPane.showMessageDialog(null, "Esta opção esta em Desenvolvimento");
                }else if(opc == 5){
                    db.deleteOne();
                }else {
                    JOptionPane.showMessageDialog(null, " Até mais!");
                }
            } while (opc != 0);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Formato invalido de opção ", "Opção entre 0 e 4 " , 1);
            Start();
        }
    }
}
