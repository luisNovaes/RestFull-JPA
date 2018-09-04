/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vipserviceauto.modelos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author luis.silva
 */
@Entity
@Table(name = "agendamento")
public class Agendamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idOS;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoServico tipoServico;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataAgenda;
    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private HoraAgenda horaAgenda;
    @Column(precision = 10, scale = 2)
    private BigDecimal valorTotalServico;
    @Column(nullable = false, length = 255)
    private String observacao;

    @ManyToOne
    @JoinColumn(name = "cod_veiculo", nullable = false)
    private Veiculo veiculo;
    @OneToOne
    private Funcionario funcionarios;
    @OneToOne
    private Pecas pecas;

    public Agendamento() {
    }

    public Agendamento(Long idOS,
            TipoServico tipoServico,
            Date dataAgenda,
            HoraAgenda horaAgenda,
            BigDecimal valorTotalServico,
            String observacao) {

        this.idOS = idOS;
        this.tipoServico = tipoServico;
        this.dataAgenda = dataAgenda;
        this.horaAgenda = horaAgenda;
        this.valorTotalServico = valorTotalServico;
        this.observacao = observacao;
    }

    public Long getIdOS() {
        return idOS;
    }

    public void setIdOS(Long idOS) {
        this.idOS = idOS;
    }

    public TipoServico getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(TipoServico tipoServico) {
        this.tipoServico = tipoServico;
    }

    public Date getDataAgenda() {
        return dataAgenda;
    }

    public void setDataAgenda(Date dataAgenda) {
        this.dataAgenda = dataAgenda;
    }

    public HoraAgenda getHoraAgenda() {
        return horaAgenda;
    }

    public void setHoraAgenda(HoraAgenda horaAgenda) {
        this.horaAgenda = horaAgenda;
    }

    public BigDecimal getValorTotalServico() {
        return valorTotalServico;
    }

    public void setValorTotalServico(BigDecimal valorTotalServico) {
        this.valorTotalServico = valorTotalServico;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Funcionario getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(Funcionario funcionarios) {
        this.funcionarios = funcionarios;
    }

    public Pecas getPecas() {
        return pecas;
    }

    public void setPecas(Pecas pecas) {
        this.pecas = pecas;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    @Override
    public String toString() {
        return "Agendamento{" + "idOS=" + idOS
                + ", tipoServico=" + tipoServico
                + ", dataAgenda=" + dataAgenda
                + ", horaAgenda=" + horaAgenda
                + ", valorTotalServico=" + valorTotalServico
                + ", observacao=" + observacao + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.idOS);
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
        final Agendamento other = (Agendamento) obj;
        if (!Objects.equals(this.idOS, other.idOS)) {
            return false;
        }
        return true;
    }

}
