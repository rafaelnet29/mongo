/*
/Classe modelo com os 
/métodos GET e SET 
*/
package com.br.mongo;


public class Model {
    
   private String id;
   private String idValor;
   private String nome;
   private String nomeValor;
   private String iddChave;
   private int iddValor;
   
   public Model(){
   }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdValor() {
        return idValor;
    }

    public void setIdValor(String idValor) {
        this.idValor = idValor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeValor() {
        return nomeValor;
    }

    public void setNomeValor(String nomeValor) {
        this.nomeValor = nomeValor;
    }

    public String getIddChave() {
        return iddChave;
    }

    public void setIddChave(String iddChave) {
        this.iddChave = iddChave;
    }

    public int getIddValor() {
        return iddValor;
    }

    public void setIddValor(int iddValor) {
        this.iddValor = iddValor;
    }
}
