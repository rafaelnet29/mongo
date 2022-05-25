package com.br.mongo;


import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import java.util.function.Consumer;
import javax.swing.JOptionPane;
import org.bson.Document;


public class DBcon {

    private MongoClient client = null;
    private MongoDatabase db;
    private MongoCollection<Document> coll;
    private final Model model;
    
    //Método construtor
    public DBcon() {
        model = new Model();
    }
    
    //Método de conexão com banco
    public void Connect() {
        client = new MongoClient("localhost", 27017);
        if (client != null) {

            db = client.getDatabase("banco");
            coll = db.getCollection("nomes");
            System.out.print("Conectado com sucesso !");
        } else {
            System.out.print("Problemas com a conexão");
        }
    }
    
    //Método de inserção de dados
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
                for (int j=0; j < nomes.length; j++  ) {
                    System.out.println(nomes[j]);
                }
            }
        });
        return doc;
    }
    
    //Em desenvolvimento
    //Método para atualizar document
    public void update(){
        /*Estava tudo errado
        /de volta a fase de 
        /planejamento e densevolvimento
        */
    }
}