package com.br.mongo;

import com.br.interfaces.ChavesImplements;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import javax.swing.JOptionPane;
import org.bson.Document;

public class DBcon {

    private MongoClient client = null;
    private MongoDatabase db;
    private MongoCollection<Document> coll;
    private final Model model;
    ChavesImplements ci = null;
    List<String> tec;

    //Método construtor
    public DBcon() {
        model = new Model();
        ci = new ChavesImplements();
        tec = new ArrayList<>();
    }

    //Método de conexão com banco
    public void Connect() {
        client = new MongoClient("localhost", 27017);

        if (client != null) {
            db = client.getDatabase("banco");
            coll = db.getCollection("nomes");
            JOptionPane.showMessageDialog(null, "Conectado com sucesso !");
        } else {
            JOptionPane.showMessageDialog(null, "Problemas com a conexão");
        }
    }

    //Método de inserção de dados
    public void InsertOneDoc() {
        model.setIdValor(JOptionPane.showInputDialog("Informe o valor do id"));
        model.setNomeValor(JOptionPane.showInputDialog("Informe o valor do nome: "));
        model.setIddValor(Integer.parseInt(JOptionPane.showInputDialog("Informe o valor da idade: ")));
        model.setDescricaoValor(JOptionPane.showInputDialog("Informe o valor da descrição: "));
        model.setTecnicasValor(JOptionPane.showInputDialog("Informe o valor da tecnicas: "));
        String[] texto = model.getTecnicasValor().split(",");
        for (String tex : texto) {
            tec.add(tex);
        }

        //Preparando Document
        Document doc = new Document();
        //inserindo dados no Document
        doc.append(ci.Id(), model.getIdValor()).append(ci.Nome(), model.getNomeValor())
                .append(ci.Idade(), model.getIddValor())
                .append(ci.Descricao(), model.getDescricaoValor())
                .append(ci.Tecnicas(), tec);
        coll.insertOne(doc);
        JOptionPane.showMessageDialog(null, " Dados insiridos com sucesso ");
    }

    //Método para listar os Documents
    public void FindAll() {
        coll.find().forEach(new Consumer<Document>() {
            @Override
            public void accept(Document docs) {
                System.out.println("\n" + docs.toJson() + "\n");
            }
        });
    }

    //Método para busca de um único document
    public Document findOne() {
        String chave = JOptionPane.showInputDialog("Chave : ");
        String valor = JOptionPane.showInputDialog("Valor : ");

        Document doc = new Document(chave, valor);
        coll.find(doc).forEach(new Consumer<Document>() {
            @Override
            public void accept(Document doc) {
                Document[] nomes = {doc};
                for (int j = 0; j < nomes.length; j++) {
                    System.out.println(nomes[j]);
                }
            }
        });
        return doc;
    }

    //Em desenvolvimento
    //Método para atualizar document
    public void update() {
        //sem funcionar
    }
}
