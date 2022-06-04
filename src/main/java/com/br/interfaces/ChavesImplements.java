package com.br.interfaces;

public class ChavesImplements implements InterfaceChaves{

    @Override
    public String Id() {
       return "_id";
    }

    @Override
    public String Nome() {
        return "nome";
    }

    @Override
    public String Idade() {
        return "Idade";
    }

    @Override
    public String Descricao() {
        return "Descrição";
    }

    @Override
    public String Tecnicas() {
        return "Tecnicas";
    }
}
