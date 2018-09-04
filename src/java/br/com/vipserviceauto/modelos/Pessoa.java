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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author luis.silva
 */
@Entity
@Table(name = "pessoa")
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPessoa;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoPessoa tipoPessoa;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date databascimento;
    @Column(nullable = false, unique = true)
    private String rg;
    @Column(nullable = false, unique = true)
    private String cpf_cnpj;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String senha;
    @Column(nullable = false)
    private String telefone;
    @Column(nullable = false)
    private String Endereco;
    @Column(nullable = false)
    private String Cidade;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Estado estado;
    @Column(nullable = false)
    private String observacao;

    public Pessoa() {
    }

    public Pessoa(Long idPessoa, TipoPessoa tipoPessoa,
            String nome, Date databascimento,
            String rg, String cpf_cnpj,
            String email, String senha,
            String telefone, String Endereco,
            String Cidade, Estado estado,
            String observacao) {

        this.idPessoa = idPessoa;
        this.tipoPessoa = tipoPessoa;
        this.nome = nome;
        this.databascimento = databascimento;
        this.rg = rg;
        this.cpf_cnpj = cpf_cnpj;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.Endereco = Endereco;
        this.Cidade = Cidade;
        this.estado = estado;
        this.observacao = observacao;
    }

    public Long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Long idPessoa) {
        this.idPessoa = idPessoa;
    }

    public TipoPessoa getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(TipoPessoa tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDatabascimento() {
        return databascimento;
    }

    public void setDatabascimento(Date databascimento) {
        this.databascimento = databascimento;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf_cnpj() {
        return cpf_cnpj;
    }

    public void setCpf_cnpj(String cpf_cnpj) {
        this.cpf_cnpj = cpf_cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return Endereco;
    }

    public void setEndereco(String Endereco) {
        this.Endereco = Endereco;
    }

    public String getCidade() {
        return Cidade;
    }

    public void setCidade(String Cidade) {
        this.Cidade = Cidade;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Override
    public String toString() {
        return "Pessoa{" + "idPessoa=" + idPessoa
                + ", tipoPessoa=" + tipoPessoa
                + ", nome=" + nome
                + ", databascimento=" + databascimento
                + ", rg=" + rg + ", cpf_cnpj=" + cpf_cnpj
                + ", email=" + email + ", senha=" + senha
                + ", telefone=" + telefone
                + ", Endereco=" + Endereco
                + ", Cidade=" + Cidade + ", estado=" + estado
                + ", observacao=" + observacao + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + Objects.hashCode(this.idPessoa);
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
        final Pessoa other = (Pessoa) obj;
        if (!Objects.equals(this.idPessoa, other.idPessoa)) {
            return false;
        }
        return true;
    }

}
