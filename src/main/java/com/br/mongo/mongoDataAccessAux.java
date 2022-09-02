package com.br.mongo;

import com.br.mongo.model.mongoModel;
import com.br.interfaces.ChavesImplements;
import com.mongodb.client.MongoCollection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
import org.bson.Document;

public class mongoDataAccessAux {

    private mongoModel model = null;
    private List<String> tec = null;
    private ChavesImplements ci = null;
    private List<Document> list = null;
    private Document doc = null;
    private MongoCollection<Document> coll;
    
    //Método Construtor
    public mongoDataAccessAux() {
        this.model = new mongoModel();
        this.tec = new ArrayList<>();
        this.ci = new ChavesImplements();
        list = new ArrayList();
        this.doc = new Document();
    }
    
    public Document insertOneAux() {

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
         JOptionPane.showMessageDialog(null, " Document insirido com sucesso ");
        return doc;
    }
    //Inserir vários Documents
    public Document insertManyAux(){
        int op = 0;
        do {
            doc = insertOneAux();
            Document []docx = {doc};
            for (int i = 0; i < docx.length; i++) {
                list.add(docx[i]);
            }
            op = Integer.parseInt(JOptionPane.showInputDialog("Continuar: 1-Sim, 0-Não"));
        }while(op != 0 );
        coll.insertMany(list);
         JOptionPane.showMessageDialog(null, " Documents insiridos com sucesso ");
        return doc;
    }
}