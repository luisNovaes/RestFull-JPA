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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.crypto.Data;

/**
 *
 * @author luis.silva
 */
@Entity
@Table(name = "fatura")
public class Fatura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codDocumento;
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal ValorTotDoc;
    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private StatusDoc StatusDoc;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date datafatura;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataVencimento;

    @OneToOne
    private Agendamento agendamento;

    public Fatura() {
    }

    public Fatura(Long codDocumento, BigDecimal ValorTotDoc, StatusDoc StatusDoc, Date datafatura, Date dataVencimento, Agendamento agendamento) {
        this.codDocumento = codDocumento;
        this.ValorTotDoc = ValorTotDoc;
        this.StatusDoc = StatusDoc;
        this.datafatura = datafatura;
        this.dataVencimento = dataVencimento;
        this.agendamento = agendamento;
    }

    public Long getCodDocumento() {
        return codDocumento;
    }

    public void setCodDocumento(Long codDocumento) {
        this.codDocumento = codDocumento;
    }

    public BigDecimal getValorTotDoc() {
        return ValorTotDoc;
    }

    public void setValorTotDoc(BigDecimal ValorTotDoc) {
        this.ValorTotDoc = ValorTotDoc;
    }

    public StatusDoc getStatusDoc() {
        return StatusDoc;
    }

    public void setStatusDoc(StatusDoc StatusDoc) {
        this.StatusDoc = StatusDoc;
    }

    public Date getDatafatura() {
        return datafatura;
    }

    public void setDatafatura(Date datafatura) {
        this.datafatura = datafatura;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
    }

    @Override
    public String toString() {
        return "Fatura{" + "codDocumento=" + codDocumento + ", ValorTotDoc=" + ValorTotDoc + ", StatusDoc=" + StatusDoc + ", datafatura=" + datafatura + ", dataVencimento=" + dataVencimento + ", agendamento=" + agendamento + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.codDocumento);
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
        final Fatura other = (Fatura) obj;
        if (!Objects.equals(this.codDocumento, other.codDocumento)) {
            return false;
        }
        return true;
    }

}
