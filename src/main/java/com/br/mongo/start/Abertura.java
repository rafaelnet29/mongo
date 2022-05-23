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
     *Mostra o menu e inicial 
     * chamando todos os métodos para iniciar 
     * a aplicação
    */
    public void Start() {       

        do {
            opc = Integer.parseInt(JOptionPane.showInputDialog(null, " --> Escolha uma opção:"
                    + " <-- \n"
                    + " -> 1 - Inserir\n"
                    + " -> 2 - Listar\n"
                    + " -> 3 - Buscar\n"
                    + " -> 4 - Atualiza (Manutanção)\n"
                    + " -> 0 - Sair ", " Menu ", 1));

            if (opc == 1) {
                db.InsertOneDoc();
            }if (opc == 2) {
                db.FindAll();
            }if (opc == 3) {
                db.findOne();
            }if (opc == 4){
                //db.update();
                JOptionPane.showMessageDialog(null, "Esta opção esta em Desenvolvimento");
            }else{
                JOptionPane.showMessageDialog(null, " Até mais! --->");
            }
        } while (opc != 0);
    }
}