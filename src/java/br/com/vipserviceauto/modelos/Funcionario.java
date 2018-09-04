/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vipserviceauto.modelos;

import java.io.Serializable;
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
@Table(name = "funcionario")
public class Funcionario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idfucionario;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Funcao funcao;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Escolaridade Escolaridade;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataAdminissao;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Temporal(TemporalType.DATE)
    private Date dataDemissao;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Filial filial;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Setor setor;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Turno turno;

    @ManyToOne
    @JoinColumn(name = "cod_agendamento")
    private Agendamento agendamento;

    @OneToOne
    @JoinColumn(name = "cod_pessoa")
    private Pessoa pessoa;

    public Long getIdfucionario() {
        return idfucionario;
    }

    public void setIdfucionario(Long idfucionario) {
        this.idfucionario = idfucionario;
    }

    public Funcao getFuncao() {
        return funcao;
    }

    public void setFuncao(Funcao funcao) {
        this.funcao = funcao;
    }

    public Escolaridade getEscolaridade() {
        return Escolaridade;
    }

    public void setEscolaridade(Escolaridade Escolaridade) {
        this.Escolaridade = Escolaridade;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    public Date getDataAdminissao() {
        return dataAdminissao;
    }

    public void setDataAdminissao(Date dataAdminissao) {
        this.dataAdminissao = dataAdminissao;
    }

    public Date getDataDemissao() {
        return dataDemissao;
    }

    public void setDataDemissao(Date dataDemissao) {
        this.dataDemissao = dataDemissao;
    }

    public Filial getFilial() {
        return filial;
    }

    public void setFilial(Filial filial) {
        this.filial = filial;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
    }

    @Override
    public String toString() {
        return "Funcionario{" + "idfucionario=" + idfucionario + ", funcao=" + funcao + ", Escolaridade=" + Escolaridade + ", especialidade=" + especialidade + ", dataAdminissao=" + dataAdminissao + ", dataDemissao=" + dataDemissao + ", filial=" + filial + ", setor=" + setor + ", turno=" + turno + ", agendamento=" + agendamento + ", pessoa=" + pessoa + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.idfucionario);
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
        final Funcionario other = (Funcionario) obj;
        if (!Objects.equals(this.idfucionario, other.idfucionario)) {
            return false;
        }
        return true;
    }

}
