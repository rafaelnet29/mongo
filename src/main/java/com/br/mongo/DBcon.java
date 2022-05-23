package com.br.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import static java.lang.System.in;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import javax.swing.JOptionPane;
import org.bson.Document;
import org.bson.conversions.Bson;

public class DBcon {

    private MongoClient client;
    private MongoDatabase db;
    private Model model;

    public DBcon() {
        model = new Model();
    }

    public void Connect() {
        client = new MongoClient("localhost", 27017);
        if (client != null) {

            db = client.getDatabase("banco");
            System.out.print("Conectado com sucesso !");
        } else {
            System.out.print("Problemas com a conexão");
        }
    }

    public void InsertOneDoc() {

        model.setId(JOptionPane.showInputDialog("Informe o id do document: "));
        model.setIdValor(JOptionPane.showInputDialog("Informe o valor do id"));
        model.setNome(JOptionPane.showInputDialog("Informe o chave nome: "));
        model.setNomeValor(JOptionPane.showInputDialog("Informe o valor do nome: "));
        model.setIddChave(JOptionPane.showInputDialog("Informe a chave da idade: "));
        model.setIddValor(Integer.parseInt(JOptionPane.showInputDialog("Informe o valor da idade: ")));

        //Preparando Document
        Document doc = new Document();

        //inserindo dados no Document
        doc.append(model.getId(), model.getIdValor());
        doc.append(model.getNome(), model.getNomeValor());
        doc.append(model.getIddChave(), model.getIddValor());

        db.getCollection("nomes").insertOne(doc);
        JOptionPane.showMessageDialog(null, " Dados insiridos com sucesso ");
    }

    public void FindAll() {
        db.getCollection("nomes").find().forEach(new Consumer<Document>() {
            @Override
            public void accept(Document docs) {
                System.out.println("\n" + docs.toJson() + "\n");
            }
        });
    }

    public void findOne() {

        String chave = JOptionPane.showInputDialog("Chave : ");
        String valor = JOptionPane.showInputDialog("Valor : ");

        Document doc = new Document(chave, valor);
        db.getCollection("nomes").find(doc).forEach(new Consumer<Document>() {
            @Override
            public void accept(Document doc) {
                Document[] nomes = {doc};
                for (int j=0; j < nomes.length; j++  ) {
                    System.out.println(nomes[j]);
                }
            }
        });
    }
    //Em desenvolvimento
    public void update(){
        /*Estava tudo errado
        /de volta a fase de 
        /planejamento e densevolvimento
        */
    }
}