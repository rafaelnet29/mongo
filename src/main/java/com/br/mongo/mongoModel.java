/*
/Classe modelo com os 
/métodos GET e SET 
 */
package com.br.mongo;

public class mongoModel {

    private String idValor;
    private String nomeValor;
    private int idadeValor;
    private String descricaoValor;
    private String[] tecnicasValor;

    public mongoModel() {
    }

    public String getIdValor() {
        return idValor;
    }

    public void setIdValor(String idValor) {
        this.idValor = idValor;
    }

    public String getNomeValor() {
        return nomeValor;
    }

    public void setNomeValor(String nomeValor) {
        this.nomeValor = nomeValor;
    }

    public int getIddValor() {
        return idadeValor;
    }

    public void setIddValor(int idadeValor) {
        this.idadeValor = idadeValor;
    }

    public String getDescricaoValor() {
        return descricaoValor;
    }

    public void setDescricaoValor(String descricaoValor) {
        this.descricaoValor = descricaoValor;
    }

    public String[] getTecnicasValor() {
        return tecnicasValor;
    }

    public void setTecnicasValor(String[] tecnicasValor) {
        this.tecnicasValor = tecnicasValor;
    }

    
}
