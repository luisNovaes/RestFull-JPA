/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vipserviceauto.daos;

import br.com.vipserviceauto.modelos.Agendamento;
import br.com.vipserviceauto.modelos.Funcionario;
import br.com.vipserviceauto.modelos.HoraAgenda;
import br.com.vipserviceauto.modelos.Pecas;
import br.com.vipserviceauto.modelos.TipoServico;
import br.com.vipserviceauto.modelos.Usuario;
import br.com.vipserviceauto.modelos.Veiculo;
import br.com.vipserviceauto.utilidades.conexao;
import java.math.BigDecimal;
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
public class AgendamentoDAO extends conexao {

    EntityManager managerMysql = conexao.getEntityManager();
    EntityTransaction conexaoMysql = managerMysql.getTransaction();

    public String buscarTodos() throws ParseException {

        try {
            CriteriaBuilder builder = managerMysql.getCriteriaBuilder();
            CriteriaQuery<Agendamento> criteriaQuery = builder.createQuery(Agendamento.class);

            Root<Agendamento> agendamento = criteriaQuery.from(Agendamento.class);
            criteriaQuery.select(agendamento);

            TypedQuery<Agendamento> query = managerMysql.createQuery(criteriaQuery);
            List<Agendamento> agendamentos = query.getResultList();

            for (Agendamento agenda : agendamentos) {

                Date data = agenda.getDataAgenda();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String dataNacimento = sdf.format(data);

                System.out.println(agenda.getIdOS() + " - "
                        + agenda.getTipoServico() + " - "
                        + dataNacimento + " - "
                        + agenda.getHoraAgenda() + " - "
                        + agenda.getValorTotalServico() + " - "
                        + agenda.getObservacao() + " - "
                        + agenda.getVeiculo().getPlaca() + " - "
                        + agenda.getFuncionarios().getPessoa().getNome() + " - "
                        + agenda.getPecas().getNome());
            }

        } catch (Exception e) {
            System.out.println("Erro (" + e.getMessage()
                    + ") ao consultar Agendamento no sistema!");

        } finally {
            managerMysql.close();
            conexao.close();
        }

        return "Operação realizada com sucesso!";
    }

    public String inserirAgendamento(
            TipoServico tipoServico,
            String dataAgenda,
            HoraAgenda horaAgenda,
            BigDecimal valorTotalServico,
            String observacao,
            int veiculo,
            int funcionarios,
            int pecas) throws ParseException {

        long idVeiculo = veiculo;
        long idfuncionario = funcionarios;
        long idPecas = pecas;

        DateFormat f = DateFormat.getDateInstance();
        Date data = f.parse(dataAgenda);
        System.out.println(data);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        System.out.println("Data formatada: " + sdf.format(data));

        Agendamento agenda = new Agendamento();

        try {

            Veiculo veic = managerMysql.find(Veiculo.class, idVeiculo);
            Funcionario Func = managerMysql.find(Funcionario.class, idfuncionario);
            Pecas pec = managerMysql.find(Pecas.class, idPecas);

            conexaoMysql.begin();

            agenda.setTipoServico(tipoServico);
            agenda.setDataAgenda(data);
            agenda.setHoraAgenda(horaAgenda);
            agenda.setValorTotalServico(valorTotalServico);
            agenda.setObservacao(observacao);
            agenda.setVeiculo(veic);
            agenda.setFuncionarios(Func);
            agenda.setPecas(pec);

            managerMysql.persist(agenda);

            conexaoMysql.commit();

        } catch (Exception e) {
            System.out.println(" Erro ao tentar inserir Agendamento no sistema! "
                    + "Já existe um agendamento com este nome no sistema.");

        } finally {
            managerMysql.close();
            conexao.close();
        }
        return "Operação realizada com sucesso!";
    }

    public Agendamento selectAgendamento(int codigo) {

        Agendamento agenda = new Agendamento();

        long id = codigo;

        try {

            agenda = (Agendamento) managerMysql.createQuery(
                    "SELECT u from Agendamento u where u.idOS = :idOS")
                    .setParameter("idOS", id).getSingleResult();

            Date data = agenda.getDataAgenda();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String dataNacimento = sdf.format(data);

            System.out.println(agenda.getIdOS() + " - "
                    + agenda.getTipoServico() + " - "
                    + dataNacimento + " - "
                    + agenda.getHoraAgenda() + " - "
                    + agenda.getValorTotalServico() + " - "
                    + agenda.getObservacao() + " - "
                    + agenda.getVeiculo().getPlaca() + " - "
                    + agenda.getFuncionarios().getPessoa().getNome() + " - "
                    + agenda.getPecas().getNome());

            return agenda;

        } catch (Exception e) {
            System.out.println(" Erro (" + e.toString()
                    + ") ao tentar buscar agendamento no sistema "
                    + "ou não existe com este ID!");
            return null;

        } finally {
            managerMysql.close();
            conexao.close();
        }
    }

    public String excluirAgendamento(int codigo) {

        Agendamento agenda = new Agendamento();

        long id = codigo;

        try {
            conexaoMysql.begin();

            agenda = (Agendamento) managerMysql.createQuery(
                    "SELECT u from Agendamento u where u.idOS = :idOS")
                    .setParameter("idOS", id).getSingleResult();

            managerMysql.remove(agenda);
            conexaoMysql.commit();

            System.out.println(" Agendamento excluido com sucesso!");

        } catch (Exception e) {

            System.out.println("Este agendamento não pode ser excluido, "
                    + "pode está vinculado a algum cadastro ou não existe no sistema!");

        } finally {
            managerMysql.close();
            conexao.close();
        }

        return "Operação realizada com sucesso!";
    }

    public String AtualizarDadosAgendamento(
            int codigo,
            TipoServico tipoServico,
            String dataAgenda,
            HoraAgenda horaAgenda,
            BigDecimal valorTotalServico,
            String observacao,
            int veiculo,
            int funcionarios,
            int pecas) throws ParseException {
        
        long id = codigo;

        long idVeiculo = veiculo;
        long idfuncionario = funcionarios;
        long idPecas = pecas;

        DateFormat f = DateFormat.getDateInstance();
        Date data = f.parse(dataAgenda);
        System.out.println(data);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        System.out.println("Data formatada: " + sdf.format(data));

        try {
            
            Agendamento agenda = managerMysql.find(Agendamento.class, id);
             
            Veiculo veic = managerMysql.find(Veiculo.class, idVeiculo);
            Funcionario Func = managerMysql.find(Funcionario.class, idfuncionario);
            Pecas pec = managerMysql.find(Pecas.class, idPecas);

            conexaoMysql.begin();

            agenda.setTipoServico(tipoServico);
            agenda.setDataAgenda(data);
            agenda.setHoraAgenda(horaAgenda);
            agenda.setValorTotalServico(valorTotalServico);
            agenda.setObservacao(observacao);
            agenda.setVeiculo(veic);
            agenda.setFuncionarios(Func);
            agenda.setPecas(pec);

            conexaoMysql.commit();

        } catch (Exception e) {
            conexaoMysql.rollback();
            System.out.println("Erro (" + e.getMessage()
                    + ") ao tentar atualizar agendamento! "
                    + "Cofira os dados e tente novamente.");

        } finally {
            managerMysql.close();
            conexao.close();
        }
        return "Opearação realizada com sucesso!";
    }

}
