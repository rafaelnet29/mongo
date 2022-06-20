package com.br.mongo;

import com.br.interfaces.ChavesImplements;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import javax.swing.JOptionPane;
import org.bson.Document;

public class mongoDataAccess {

    private MongoCollection<Document> coll;
    private mongoModel model = null;
    private ChavesImplements ci = null;
    private List<String> tec = null;
    private MongoClient client = null;
    private MongoDatabase db;

    //Método construtor
    public mongoDataAccess() {
        model = new mongoModel();
        ci = new ChavesImplements();
        tec = new ArrayList<String>();
    }

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

        model.setIdValor(JOptionPane.showInputDialog("Informe o do id"));
        model.setNomeValor(JOptionPane.showInputDialog("Informe o nome: "));
        model.setIddValor(Integer.parseInt(JOptionPane.showInputDialog("Informe a idade: ")));
        model.setDescricaoValor(JOptionPane.showInputDialog("Informe a descrição: "));
        model.setTecnicasValor(JOptionPane.showInputDialog("Informe as habilidades: ").split(","));
        tec.addAll(Arrays.asList(model.getTecnicasValor()));

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
                Document[] nomes = {docs};
                for (int i = 0; i < nomes.length; i++) {
                    System.out.println("Nomes: " + nomes[i] + "\n");
                }
            }
        });
    }

    //Método para busca de um único document
    public Document findOne() {
        String chave = JOptionPane.showInputDialog("Informe a Chave : ");
        String valor = JOptionPane.showInputDialog("Informe o Valor : ");
        Document doc = new Document(chave, valor);
        coll.find(doc).forEach(new Consumer<Document>() {
            @Override
            public void accept(Document doc) {
                Document[] nomes = {doc};
                for (int j = 0; j < nomes.length; j++) {
                    JOptionPane.showMessageDialog(null, nomes[j]);
                }
            }
        });
        return doc;
    }

    /**
     * Método para atualizar document com os operadores $set $push
     */
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