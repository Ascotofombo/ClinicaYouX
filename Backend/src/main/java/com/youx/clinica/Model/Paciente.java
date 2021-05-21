package com.youx.clinica.Model;
import org.apache.commons.codec.binary.Base64;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name="pacientes")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;

    @Column(unique = true)
    private String cpf;

    private LocalDate nasc;
    private Double altura;
    private String uf;
    private Double peso;

    public Paciente(Integer id, String nome, String cpf, LocalDate nasc, Double altura, String uf, Double peso) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.nasc = nasc;
        this.altura = altura;
        this.uf = uf;
        this.peso = peso;
    }

    public Paciente() {

    }



    public String encodeCpf(){
        byte[] encoded = Base64.encodeBase64(this.cpf.getBytes());

        return new String(encoded);
    }

    public String decodeCpf(){
        byte[] decoded = Base64.decodeBase64(this.cpf);
        return new String(decoded);
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getNasc() {
        return nasc;
    }

    public void setNasc(LocalDate nasc) {
        this.nasc = nasc;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}
