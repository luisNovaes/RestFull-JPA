/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vipserviceauto.modelos;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author luis.silva
 */
@Entity
@Table(name = "veiculo")
public class Veiculo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idVeiculo;
    @Column(nullable = false, length = 10, unique = true)
    private String placa;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoVeiculo tipoveiculo;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Montadora montadora;
    @Column(nullable = false, length = 60)
    private String modelo;
    @Column(nullable = false, length = 4)
    private Integer anoFabricacao;
    @Column(nullable = false)
    private String observacao;
    @ManyToOne
    @JoinColumn(name = "cod_proprietario")
    private Pessoa propietario;
    @OneToMany(mappedBy = "veiculo")
    private List<Agendamento> agendamentos;
    @ManyToOne
    @JoinColumn(name = "cod_agendamento")
    private Agendamento agendamento;

    public Veiculo() {
    }

    public Veiculo(Long idVeiculo, String placa, TipoVeiculo tipoveiculo, Montadora montadora, String modelo, Integer anoFabricacao, String observacao, Pessoa propietario, List<Agendamento> agendamentos, Agendamento agendamento) {
        this.idVeiculo = idVeiculo;
        this.placa = placa;
        this.tipoveiculo = tipoveiculo;
        this.montadora = montadora;
        this.modelo = modelo;
        this.anoFabricacao = anoFabricacao;
        this.observacao = observacao;
        this.propietario = propietario;
        this.agendamentos = agendamentos;
        this.agendamento = agendamento;
    }

    public Long getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(Long idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public TipoVeiculo getTipoveiculo() {
        return tipoveiculo;
    }

    public void setTipoveiculo(TipoVeiculo tipoveiculo) {
        this.tipoveiculo = tipoveiculo;
    }

    public Montadora getMontadora() {
        return montadora;
    }

    public void setMontadora(Montadora montadora) {
        this.montadora = montadora;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(Integer anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Pessoa getPropietario() {
        return propietario;
    }

    public void setPropietario(Pessoa propietario) {
        this.propietario = propietario;
    }

    public List<Agendamento> getAgendamentos() {
        return agendamentos;
    }

    public void setAgendamentos(List<Agendamento> agendamentos) {
        this.agendamentos = agendamentos;
    }

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
    }

    @Override
    public String toString() {
        return "Veiculo{" + "idVeiculo=" + idVeiculo + ", placa=" + placa + ", tipoveiculo=" + tipoveiculo + ", montadora=" + montadora + ", modelo=" + modelo + ", anoFabricacao=" + anoFabricacao + ", observacao=" + observacao + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.idVeiculo);
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
        final Veiculo other = (Veiculo) obj;
        if (!Objects.equals(this.idVeiculo, other.idVeiculo)) {
            return false;
        }
        return true;
    }

}
