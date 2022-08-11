package com.br.mongo;

import com.br.interfaces.ChavesImplements;
import com.mongodb.client.MongoCollection;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.bson.Document;
import org.codehaus.jackson.map.ObjectMapper;

public class mongoDataAccessAux {

    private mongoModel model = null;
    private List<String> tec = null;
    private ChavesImplements ci = null;
    private List<Document> list = null;
    private Document doc = null;
    private ObjectMapper pretty = null;
    private MongoCollection<Document> coll;
    
    //Método Construtor
    public mongoDataAccessAux() {
        this.model = new mongoModel();
        this.tec = new ArrayList<>();
        this.ci = new ChavesImplements();
        list = new ArrayList();
        this.doc = new Document();
        this.pretty = new ObjectMapper();
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
        return doc;
    }
    
    public void aux() {
        coll.find().forEach(new Consumer<Document>() {
            @Override
            public void accept(Document d) {
                Document[] nomes = {doc};
                for (int i = 0; i < nomes.length; i++) {
                    try {
                        String json = pretty.writerWithDefaultPrettyPrinter().writeValueAsString(nomes[i]);
                        System.out.println("Document: " + json + "\n");
                    } catch (IOException ex) {
                        Logger.getLogger(mongoDataAccess.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }
}