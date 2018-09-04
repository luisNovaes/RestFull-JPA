/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vipserviceauto.daos;

import br.com.vipserviceauto.modelos.Estado;
import br.com.vipserviceauto.modelos.Pessoa;
import br.com.vipserviceauto.modelos.TipoPessoa;
import br.com.vipserviceauto.modelos.Usuario;
import br.com.vipserviceauto.utilidades.conexao;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author luis.silva
 */
public class PessoaDAO extends conexao {

    EntityManager managerMysql = conexao.getEntityManager();
    EntityTransaction conexaoMysql = managerMysql.getTransaction();

    public String buscarTodos() throws ParseException {

        try {
            CriteriaBuilder builder = managerMysql.getCriteriaBuilder();
            CriteriaQuery<Pessoa> criteriaQuery = builder.createQuery(Pessoa.class);

            Root<Pessoa> pessoa = criteriaQuery.from(Pessoa.class);
            criteriaQuery.select(pessoa);

            TypedQuery<Pessoa> query = managerMysql.createQuery(criteriaQuery);
            List<Pessoa> Pessoas = query.getResultList();

            for (Pessoa pess : Pessoas) {

                Date data = pess.getDatabascimento();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String dataNacimento = sdf.format(data);

                System.out.println(pess.getIdPessoa() + " - "
                        + pess.getTipoPessoa() + " - "
                        + pess.getNome() + " - "
                        + dataNacimento + " - "
                        + pess.getRg() + " - "
                        + pess.getCpf_cnpj() + " - "
                        + pess.getEmail() + " - "
                        + pess.getSenha() + " - "
                        + pess.getTelefone() + " - "
                        + pess.getEndereco() + " - "
                        + pess.getCidade() + " - "
                        + pess.getEstado() + " - "
                        + pess.getObservacao() + " - ");
            }

        } catch (Exception e) {
            System.out.println("Erro (" + e.getMessage()
                    + ") ao consultar pessoa no banco!");

        } finally {
            managerMysql.close();
            conexao.close();
        }

        return "Operação realizada com sucesso!";
    }

    public String inserirPessoa(
            TipoPessoa tipoPessoa,
            String nome,
            String dataNascimento,
            String rg,
            String cpf_cnpj,
            String email,
            String senha,
            String telefone,
            String endereco,
            String cidade,
            Estado estado,
            String observacao) throws ParseException {

        DateFormat f = DateFormat.getDateInstance();
        Date data = f.parse(dataNascimento);
        System.out.println(data);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        System.out.println("Data formatada: " + sdf.format(data));

        Pessoa pessoa = new Pessoa();

        try {
            conexaoMysql.begin();
            pessoa.setTipoPessoa(tipoPessoa);
            pessoa.setNome(nome);
            pessoa.setDatabascimento(data);
            pessoa.setRg(rg);
            pessoa.setCpf_cnpj(cpf_cnpj);
            pessoa.setEmail(email);
            pessoa.setSenha(senha);
            pessoa.setTelefone(telefone);
            pessoa.setEndereco(endereco);
            pessoa.setCidade(cidade);
            pessoa.setEstado(estado);
            pessoa.setObservacao(observacao);

            managerMysql.persist(pessoa);

            conexaoMysql.commit();

        } catch (Exception e) {
            System.out.println(" Erro ao tentar inserir usuário no sistema! "
                    + "Já existe um usuario com este nome no sistema.");

        } finally {
            managerMysql.close();
            conexao.close();
        }
        return "Operação realizada com sucesso!";
    }

    public Pessoa selectPessoa(String cpf_cnpj) {

        Pessoa pessoa = new Pessoa();

        try {

            pessoa = (Pessoa) managerMysql
                    .createQuery(
                            "SELECT u from Pessoa u where u.cpf_cnpj = "
                            + ":cpf_cnpj")
                    .setParameter("cpf_cnpj", cpf_cnpj).getSingleResult();

            Date data = pessoa.getDatabascimento();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String dataNacimento = sdf.format(data);

            System.out.println(pessoa.getIdPessoa() + " - "
                    + pessoa.getTipoPessoa() + " - "
                    + pessoa.getNome() + " - "
                    + dataNacimento + " - "
                    + pessoa.getRg() + " - "
                    + pessoa.getCpf_cnpj() + " - "
                    + pessoa.getEmail() + " - "
                    + pessoa.getSenha() + " - "
                    + pessoa.getTelefone() + " - "
                    + pessoa.getEndereco() + " - "
                    + pessoa.getCidade() + " - "
                    + pessoa.getEstado() + " - "
                    + pessoa.getObservacao() + " - ");
            return pessoa;

        } catch (Exception e) {
            System.out.println(" Erro (" + e.getMessage()
                    + ") ao tentar buscar a pessoa no sistema ou não existe!");
            return null;

        } finally {
            managerMysql.close();
            conexao.close();
        }
    }

    public String excluirPessoa(String cpf_cnpj) {

        Pessoa pessoa = new Pessoa();

        try {
            conexaoMysql.begin();

            pessoa = (Pessoa) managerMysql.createQuery(
                    "SELECT u from Pessoa u where u.cpf_cnpj = :cpf_cnpj")
                    .setParameter("cpf_cnpj", cpf_cnpj).getSingleResult();

            managerMysql.remove(pessoa);
            conexaoMysql.commit();

            System.out.println(" Pessoa " + pessoa.getNome()
                    + " excluida com sucesso!");

        } catch (Exception e) {

            System.out.println("Esta pessoa não pode ser excluido, "
                    + "pode está vinculado a algum cadastro ou não existe no sistema!");

        } finally {
            managerMysql.close();
            conexao.close();
        }

        return "Operação realizada com sucesso!";
    }

    public String AtualizarDadosUsuario(int codigo,
            TipoPessoa tipoPessoa,
            String nome,
            String dataNascimento,
            String rg,
            String cpf_cnpj,
            String email,
            String senha,
            String telefone,
            String endereco,
            String cidade,
            Estado estado,
            String observacao) throws ParseException {

        Pessoa pessoa = new Pessoa();

        DateFormat f = DateFormat.getDateInstance();
        Date data = f.parse(dataNascimento);
        System.out.println(data);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        System.out.println("Data formatada: " + sdf.format(data));

        long id = codigo;

        try {
            conexaoMysql.begin();
            pessoa = managerMysql.find(Pessoa.class, id);

            pessoa.setTipoPessoa(tipoPessoa);
            pessoa.setNome(nome);
            pessoa.setDatabascimento(data);
            pessoa.setRg(rg);
            pessoa.setCpf_cnpj(cpf_cnpj);
            pessoa.setEmail(email);
            pessoa.setSenha(senha);
            pessoa.setTelefone(telefone);
            pessoa.setEndereco(endereco);
            pessoa.setCidade(cidade);
            pessoa.setEstado(estado);
            pessoa.setObservacao(observacao);

            conexaoMysql.commit();

        } catch (Exception e) {

            System.out.println("Erro (" + e.getMessage()
                    + ") ao tentar atualizar pessoa! "
                    + "Cofira os dados e tente novamente.");

        } finally {
            managerMysql.close();
            conexao.close();
        }
        return "Opearação realizada com sucesso!";
    }

}
