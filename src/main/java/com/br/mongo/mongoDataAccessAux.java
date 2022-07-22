package com.br.mongo;

import com.br.interfaces.ChavesImplements;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import javax.swing.JOptionPane;
import org.bson.Document;

public class mongoDataAccessAux {

    private final mongoModel model;
    private List<String> tec = null;
    private ChavesImplements ci = null;
    private List<Document> list = null;
    private Document doc = null;

    public mongoDataAccessAux() {
        model = new mongoModel();
        tec = new ArrayList<>();
        ci = new ChavesImplements();
        list = new ArrayList();
        doc = new Document();
    }

    public Document insertAux() {

        model.setIdValor(JOptionPane.showInputDialog("Informe o do id"));
        model.setNomeValor(JOptionPane.showInputDialog("Informe o nome: "));
        model.setIddValor(Integer.parseInt(JOptionPane.showInputDialog("Informe a idade: ")));
        model.setDescricaoValor(JOptionPane.showInputDialog("Informe a descrição: "));
        model.setTecnicasValor(JOptionPane.showInputDialog("Informe as habilidades: ").split(","));
        tec.addAll(Arrays.asList(model.getTecnicasValor()));

        //inserindo dados no Document
        doc.append(ci.Id(), model.getIdValor()).append(ci.Nome(), model.getNomeValor())
                .append(ci.Idade(), model.getIddValor()).append(ci.Descricao(), model.getDescricaoValor())
                .append(ci.Tecnicas(), tec);
        return doc;
    }
    
    //em desenvolvimento
    public List<Document> insertManyAux() {
        int opc = 0;
        do {
            
            model.setIdValor(JOptionPane.showInputDialog("Informe o do id"));
            model.setNomeValor(JOptionPane.showInputDialog("Informe o nome: "));
            model.setIddValor(Integer.parseInt(JOptionPane.showInputDialog("Informe a idade: ")));
            model.setDescricaoValor(JOptionPane.showInputDialog("Informe a descrição: "));
            model.setTecnicasValor(JOptionPane.showInputDialog("Informe as habilidades: ").split(","));
            tec.addAll(Arrays.asList(model.getTecnicasValor()));

            doc.append(ci.Id(), model.getIdValor()).append(ci.Nome(), model.getNomeValor())
                    .append(ci.Idade(), model.getIddValor()).append(ci.Descricao(), model.getDescricaoValor())
                    .append(ci.Tecnicas(), tec);
            
             opc = Integer.parseInt(JOptionPane.showInputDialog(null, "Continuar a adicionar: "));
             
        } while (opc != 0);
        Document[] docx = {doc};
        for (int i = 0; i < docx.length; i++) {
            list.addAll(Arrays.asList(docx[i]));
        }
       
        return list;
    }
}
