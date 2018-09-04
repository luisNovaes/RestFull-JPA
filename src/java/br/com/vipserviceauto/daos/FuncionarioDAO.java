/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vipserviceauto.daos;

import br.com.vipserviceauto.modelos.Escolaridade;
import br.com.vipserviceauto.modelos.Especialidade;
import br.com.vipserviceauto.modelos.Estado;
import br.com.vipserviceauto.modelos.Filial;
import br.com.vipserviceauto.modelos.Funcao;
import br.com.vipserviceauto.modelos.Funcionario;
import br.com.vipserviceauto.modelos.Pessoa;
import br.com.vipserviceauto.modelos.Setor;
import br.com.vipserviceauto.modelos.TipoPessoa;
import br.com.vipserviceauto.modelos.Turno;
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
public class FuncionarioDAO extends conexao {

    EntityManager managerMysql = conexao.getEntityManager();
    EntityTransaction conexaoMysql = managerMysql.getTransaction();

    public String buscarTodos() throws ParseException {

        try {
            CriteriaBuilder builder = managerMysql.getCriteriaBuilder();
            CriteriaQuery<Funcionario> criteriaQuery = builder.createQuery(Funcionario.class);

            Root<Funcionario> funcionario = criteriaQuery.from(Funcionario.class);
            criteriaQuery.select(funcionario);

            TypedQuery<Funcionario> query = managerMysql.createQuery(criteriaQuery);
            List<Funcionario> Funcionarios = query.getResultList();

            for (Funcionario func : Funcionarios) {

                Date dataAdmi = func.getDataAdminissao();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String dataAdmissao = sdf.format(dataAdmi);

                Date dataDemi = func.getDataDemissao();
                SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
                String dataDemissao = sdf1.format(dataDemi);

                System.out.println(func.getIdfucionario() + " - "
                        + func.getPessoa().getNome() + " - "
                        + func.getFuncao() + " - "
                        + func.getEscolaridade() + " - "
                        + dataAdmissao + " - "
                        + dataDemissao + " - "
                        + func.getEspecialidade() + " - "
                        + func.getFilial() + " - "
                        + func.getSetor());
            }

        } catch (Exception e) {
            System.out.println("Erro (" + e.getMessage()
                    + ") ao consultar funcioanro no banco!");

        } finally {
            managerMysql.close();
            conexao.close();
        }

        return "Operação realizada com sucesso!";
    }

    public String inserirfuncionario(
            int codigo,
            Funcao funcao,
            Escolaridade escolaridade,
            Especialidade especialidade,
            String dataAdminissao,
            String dataDemissao,
            Filial filial,
            Setor setor,
            Turno turno) throws ParseException {

        long id = codigo;

        DateFormat f = DateFormat.getDateInstance();
        Date dataAdmi = f.parse(dataAdminissao);
        System.out.println(dataAdmi);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        System.out.println("Data formatada: " + sdf.format(dataAdmi));

        DateFormat f1 = DateFormat.getDateInstance();
        Date dataDem = f1.parse(dataDemissao);
        System.out.println(dataDem);

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd");
        System.out.println("Data formatada: " + sdf1.format(dataDem));

        Funcionario funcionario = new Funcionario();

        try {
            conexaoMysql.begin();
            Pessoa pessoa = managerMysql.find(Pessoa.class, id);

            funcionario.setPessoa(pessoa);
            funcionario.setFuncao(funcao);
            funcionario.setEscolaridade(escolaridade);
            funcionario.setEspecialidade(especialidade);
            funcionario.setDataAdminissao(dataAdmi);
            funcionario.setDataDemissao(dataDem);
            funcionario.setFilial(filial);
            funcionario.setSetor(setor);
            funcionario.setTurno(turno);

            managerMysql.persist(funcionario);

            conexaoMysql.commit();

        } catch (Exception e) {
            System.out.println(" Erro ao tentar inserir  o funcionario no sistema! "
                    + " ou já existe um funcionario com este nome no sistema."
                    + e.getMessage());

        } finally {
            managerMysql.close();
            conexao.close();
        }
        return "Operação realizada com sucesso!";
    }

    public Funcionario selectFuncioanrio(int codigo) {

        Funcionario func = new Funcionario();

        long id = codigo;

        try {

            func = (Funcionario) managerMysql
                    .createQuery(
                            "SELECT u from Funcionario u where u.idfucionario = "
                            + ":idfucionario")
                    .setParameter("idfucionario", id).getSingleResult();

            Date dataAdmi = func.getDataAdminissao();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String dataAdmissao = sdf.format(dataAdmi);

            Date dataDemi = func.getDataDemissao();
            SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
            String dataDemissao = sdf1.format(dataDemi);

            System.out.println(func.getIdfucionario() + " - "
                    + func.getPessoa().getNome() + " - "
                    + func.getPessoa().getCpf_cnpj() + " - "
                    + func.getPessoa().getEmail() + " - "
                    + func.getPessoa().getTelefone() + " - "
                    + func.getFuncao() + " - "
                    + func.getEscolaridade() + " - "
                    + dataAdmissao + " - "
                    + dataDemissao + " - "
                    + func.getEspecialidade() + " - "
                    + func.getFilial() + " - "
                    + func.getSetor());
            return func;

        } catch (Exception e) {
            System.out.println(" Erro (" + e.getMessage()
                    + ") ao tentar buscar a funcionário no sistema "
                    + "ou não existe funcionário com este ID!");
            return null;

        } finally {
            managerMysql.close();
            conexao.close();
        }
    }

    public String excluirFuncionario(int codigo) {

        Funcionario funcionario = new Funcionario();

        long id = codigo;

        try {
            conexaoMysql.begin();

            funcionario = (Funcionario) managerMysql.createQuery(
                    "SELECT u from Funcionario u where u.idfucionario = :idfucionario")
                    .setParameter("idfucionario", id).getSingleResult();

            managerMysql.remove(funcionario);
            conexaoMysql.commit();

            System.out.println(" Funcionário " + funcionario.getPessoa().getNome()
                    + " excluido com sucesso!");

        } catch (Exception e) {

            System.out.println("Este Funcionário não pode ser excluido, "
                    + "pode está vinculado a algum cadastro ou não existe no sistema!"
                    + e.getMessage());

        } finally {
            managerMysql.close();
            conexao.close();
        }

        return "Operação realizada com sucesso!";
    }

    public String AtualizarDadosFuncionario(int codigo,
            int codigoPessoa,
            Funcao funcao,
            Escolaridade escolaridade,
            Especialidade especialidade,
            String dataAdminissao,
            String dataDemissao,
            Filial filial,
            Setor setor,
            Turno turno) throws ParseException {

        long id = codigo;
        long idPesoa = codigoPessoa;

        DateFormat f = DateFormat.getDateInstance();
        Date dataAdmi = f.parse(dataAdminissao);
        System.out.println(dataAdmi);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        System.out.println("Data formatada: " + sdf.format(dataAdmi));

        DateFormat f1 = DateFormat.getDateInstance();
        Date dataDem = f1.parse(dataDemissao);
        System.out.println(dataDem);

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd");
        System.out.println("Data formatada: " + sdf1.format(dataDem));

        Funcionario funcionario = new Funcionario();

        try {
            conexaoMysql.begin();
            funcionario = managerMysql.find(Funcionario.class, id);
            Pessoa pessoa = managerMysql.find(Pessoa.class, idPesoa);

            funcionario.setPessoa(pessoa);
            funcionario.setFuncao(funcao);
            funcionario.setEscolaridade(escolaridade);
            funcionario.setEspecialidade(especialidade);
            funcionario.setDataAdminissao(dataAdmi);
            funcionario.setDataDemissao(dataDem);
            funcionario.setFilial(filial);
            funcionario.setSetor(setor);
            funcionario.setTurno(turno);

            conexaoMysql.commit();

        } catch (Exception e) {

            System.out.println("Erro (" + e.getMessage()
                    + ") ao tentar atualizar o funcionário! "
                    + "Cofira os dados e tente novamente."
                    + e.getMessage());

        } finally {
            managerMysql.close();
            conexao.close();
        }
        return "Opearação realizada com sucesso!";
    }

}
