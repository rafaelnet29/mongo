package com.br.mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import java.io.IOException;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.bson.Document;
import org.codehaus.jackson.map.ObjectMapper;


public class mongoDataAccess {

    private MongoCollection<Document> coll;
    private MongoClient client = null;
    private MongoDatabase db;
    private Document doc = null;
    private ObjectMapper pretty = null;

    //Método construtor
    public mongoDataAccess() {
        this.doc = doc;
        this.pretty = new ObjectMapper();
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

    //Método de inserção de um único Document
    public void InsertOne() {
        coll.insertOne(new mongoDataAccessAux().insertOneAux());
        JOptionPane.showMessageDialog(null, " Document insirido com sucesso ");
    }

    //Método de insersão de varios Documents
    public void InsertMany() {
       new mongoDataAccessAux().insertManyAux();
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
                        System.out.println(" Document: \r\n" + json + " \n ");
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

        doc = new Document(chave, valor);
        coll.find(doc).forEach(new Consumer<Document>() {
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
        return doc;
    }

    //Método para atualizar document com os operadores $set $push
    public void updateOne() {
        doc = findOne();

        /**
         * informa aqui o que a com qual chave
         * o Document será atualizado
         */
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
        doc = findOne();
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