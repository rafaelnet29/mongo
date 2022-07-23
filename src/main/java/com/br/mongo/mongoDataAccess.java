package com.br.mongo;

import com.br.interfaces.ChavesImplements;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import org.bson.Document;
import org.codehaus.jackson.map.ObjectMapper;

public class mongoDataAccess {

    private MongoCollection<Document> coll;
    private MongoClient client = null;
    private MongoDatabase db;
    private final ObjectMapper pretty;
    private ChavesImplements ci;
    private List<Document> list;

    //Método construtor
    public mongoDataAccess() {
        //new mongoModel();
        ci = new ChavesImplements();
        list = new ArrayList<>();
        pretty = new ObjectMapper();

    }

    public void Connect() {
        client = new MongoClient("localhost", 27017);
        if (client != null) {
            db = client.getDatabase("personagens");
            coll = db.getCollection("nomes");
            JOptionPane.showMessageDialog(null, "Conectado com sucesso !");
        } else {
            JOptionPane.showMessageDialog(null, "Problemas com a conexão");
        }
    }

    //Método de inserção de dados
    public void InsertOneDoc() {
        coll.insertOne(new mongoDataAccessAux().insertAux());
        JOptionPane.showMessageDialog(null, " Document insirido com sucesso ");
    }

    //em desenvolvimento
    public void InsertMany() {
        var texto  = "";
        int j = 0;
        String[] json = texto.split("\\|");
        Document doc = new Document();
        doc.append(ci.Id(), json[j]).append(ci.Nome(), json[j]).append(ci.Idade(), Integer.parseInt(json[j]))
                .append(ci.Descricao(), json[j]).append(ci.Tecnicas(), json[j].split(","));
        
        
        for (int i = 0; i < json.length; i++) {
            list.add(doc);
        }
        
        coll.insertMany(list);
        //new mongoDataAccessAux().insertManyAux();
    }

    //Método para listar os Documents
    public void FindAll() {
        coll.find().forEach(new Consumer<Document>() {
            @Override
            public void accept(Document docs) {
                Document[] nomes = {docs};
                for (int i = 0; i < nomes.length; i++) {
                    try {
                        String json = pretty.writerWithDefaultPrettyPrinter().writeValueAsString(nomes[i]);
                        System.out.println(" Document: " + json);
                        System.out.println(" \n ");
                    } catch (IOException ex) {
                        Logger.getLogger(mongoDataAccess.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }

    //Método para busca de um único document
    public Document findOne() {

        String chave = JOptionPane.showInputDialog("Informe qual a Chave : ");
        String valor = JOptionPane.showInputDialog("Informe o Valor : ");

        Document doc = new Document(chave, valor);

        coll.find(doc).forEach(new Consumer<Document>() {
            @Override
            public void accept(Document doc) {
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
        return doc;
    }

    //Método para atualizar document com os operadores $set $push
    public void updateOne() {
        Document doc = findOne();

        //informa aqui o que será atualizado
        String chave = JOptionPane.showInputDialog("Informe a Chave: ");
        String valor = JOptionPane.showInputDialog("Informe o Valor: ");

        String operador = JOptionPane.showInputDialog("Qual operador :  $set ou $push");

        Document doc2 = new Document(chave, valor);
        //Faz alusão a db.<collection>.updateOne({filtro},{operador : {dado substituto}})
        coll.updateOne(new Document(doc), new Document(operador, new Document(doc2)));
        JOptionPane.showMessageDialog(null, "Document atualizado com sucesso!");

        FindAll();
    }

    //Método para deletar Documents
    public void deleteOne() {
        Document doc = findOne();
        int opc = JOptionPane.showConfirmDialog(null, "Tem certeza que quer deletar o Document: "
                + doc, " Atenção! ", JOptionPane.YES_NO_CANCEL_OPTION);

        switch (opc) {
            case 0:
                coll.deleteOne(doc);
                JOptionPane.showMessageDialog(null, " Document deletado com Sucesso! ");
                FindAll();
                break;
            case 1:
                JOptionPane.showMessageDialog(null, " Document não deletado! ");
                break;
            default:
                JOptionPane.showMessageDialog(null, " Operação cancelada! ");
                break;
        }
    }
}
