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
   private String idadeChave;
   private int idadeValor;
   private String descricaoChave;
   private String descricaoValor;
   private String tecnicasChave;
   private String tecnicasValor;    
   
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

    public String getIdadeChave() {
        return idadeChave;
    }

    public void setIdadeChave(String idadeChave) {
        this.idadeChave = idadeChave;
    }

    public int getIdadeValor() {
        return idadeValor;
    }

    public void setIddValor(int idadeValor) {
        this.idadeValor = idadeValor;
    }
    
    public String getDescricaoChave() {
        return descricaoChave;
    }

    public void setDescricaoChave(String descricaoChave) {
        this.descricaoChave = descricaoChave;
    }
    
     public String getTecnicasChave() {
        return tecnicasChave;
    }

    public void setTecnicasChave(String tecnicasChave) {
        this.tecnicasChave = tecnicasChave;
    }
    
    public String getDescricaoValor() {
        return descricaoValor;
    }

    public void setDescricaoValor(String descricaoValor) {
        this.descricaoValor = descricaoValor;
    }
    
     public String getTecnicasValor() {
        return tecnicasValor;
    }

    public void setTecnicasValor(String tecnicasValor) {
        this.tecnicasValor = tecnicasValor;
    }
}
