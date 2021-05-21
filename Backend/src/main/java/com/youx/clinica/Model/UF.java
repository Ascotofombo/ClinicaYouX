package com.youx.clinica.Model;


import java.util.Objects;

public class UF {

    String label;
    Integer qtd;

    public UF(String label) {
        this.label = label;
        this.qtd = 1;
    }

    public UF(){

    }

    @Override
    public int hashCode() {
        return 0;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getQtd() {
        return qtd;
    }

    public void setQtd(Integer qtd) {
        this.qtd = qtd;
    }
}

