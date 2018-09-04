/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vipserviceauto.modelos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author luis.silva
 */
@Entity
@Table(name = "pecas")
public class Pecas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPeca;
    @Column(nullable = false, length = 200)
    private String nome;
    @Column(nullable = false, length = 100)
    private String fabricante;
    @Column(nullable = false, length = 100)
    private String modelo;
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valorPeca;
    @Column(length = 300)
    private String observacao;

    public Pecas() {
    }

    public Pecas(Long idPeca, String nome, String fabricante, String modelo, BigDecimal valorPeca, String observacao, Agendamento agendamento) {
        this.idPeca = idPeca;
        this.nome = nome;
        this.fabricante = fabricante;
        this.modelo = modelo;
        this.valorPeca = valorPeca;
        this.observacao = observacao;
        this.agendamento = agendamento;
    }
    
    

    @OneToOne
    private Agendamento agendamento;

    public Long getIdPeca() {
        return idPeca;
    }

    public void setIdPeca(Long idPeca) {
        this.idPeca = idPeca;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public BigDecimal getValorPeca() {
        return valorPeca;
    }

    public void setValorPeca(BigDecimal valorPeca) {
        this.valorPeca = valorPeca;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
    }

    @Override
    public String toString() {
        return "Pecas{" + "idPeca=" + idPeca + ", nome=" + nome + ", fabricante=" + fabricante + ", modelo=" + modelo + ", valorPeca=" + valorPeca + ", observacao=" + observacao + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.idPeca);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pecas other = (Pecas) obj;
        if (!Objects.equals(this.idPeca, other.idPeca)) {
            return false;
        }
        return true;
    }

}
