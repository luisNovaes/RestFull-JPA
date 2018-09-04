/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vipserviceauto.daos;

import br.com.vipserviceauto.modelos.Agendamento;
import br.com.vipserviceauto.modelos.Montadora;
import br.com.vipserviceauto.modelos.Pessoa;
import br.com.vipserviceauto.modelos.TipoVeiculo;
import br.com.vipserviceauto.modelos.Veiculo;
import br.com.vipserviceauto.utilidades.conexao;
import java.text.ParseException;
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
public class VeiculoDAO extends conexao {

    EntityManager managerMysql = conexao.getEntityManager();
    EntityTransaction conexaoMysql = managerMysql.getTransaction();

    public String buscarTodos() throws ParseException {

        try {
            CriteriaBuilder builder = managerMysql.getCriteriaBuilder();
            CriteriaQuery<Veiculo> criteriaQuery = builder.createQuery(Veiculo.class);

            Root<Veiculo> veiculo = criteriaQuery.from(Veiculo.class);
            criteriaQuery.select(veiculo);

            TypedQuery<Veiculo> query = managerMysql.createQuery(criteriaQuery);
            List<Veiculo> Veiculos = query.getResultList();

            for (Veiculo veic : Veiculos) {

                System.out.println(veic.getIdVeiculo() + " - "
                        + veic.getPlaca() + " - "
                        + veic.getTipoveiculo() + " - "
                        + veic.getMontadora() + " - "
                        + veic.getModelo() + " - "
                        + veic.getAnoFabricacao() + " - "
                        + veic.getObservacao() + " - "
                        + veic.getPropietario().getNome() + " - "
                        + veic.getPropietario().getCpf_cnpj() + " - "
                        + veic.getAgendamento() + " - "
                        + veic.getAgendamento());

            }

        } catch (Exception e) {
            System.out.println("Erro (" + e.getMessage()
                    + ") ao consultar o Veículo no banco!");

        } finally {
            managerMysql.close();
            conexao.close();
        }

        return "Operação realizada com sucesso!";
    }

    public String inserirVeiculo(
            String placa,
            TipoVeiculo tipoveiculo,
            Montadora montadora,
            String modelo,
            int anoFabricacao,
            String observacao,
            int codigoPessoa,
            int codigoAgenda) {

        long idPessoa = codigoPessoa;
        long idAgenda = codigoAgenda;

        Veiculo veiculo = new Veiculo();

        try {
            conexaoMysql.begin();

            Pessoa pessoa = managerMysql.find(Pessoa.class, idPessoa);
            Agendamento agenda = managerMysql.find(Agendamento.class, idAgenda);

            veiculo.setPlaca(placa);
            veiculo.setTipoveiculo(tipoveiculo);
            veiculo.setMontadora(montadora);
            veiculo.setModelo(modelo);
            veiculo.setAnoFabricacao(anoFabricacao);
            veiculo.setObservacao(observacao);
            veiculo.setPropietario(pessoa);
            veiculo.setAgendamento(agenda);

            managerMysql.persist(veiculo);

            conexaoMysql.commit();

        } catch (Exception e) {
            System.out.println(" Erro ao tentar inserir veículo no sistema! "
                    + "Já existe um usuario com este nome no sistema.");

        } finally {
            managerMysql.close();
            conexao.close();
        }
        return "Operação realizada com sucesso!";
    }

    public Veiculo selectVeiculo(String placa) {

        Veiculo veiculo = new Veiculo();

        try {

            veiculo = (Veiculo) managerMysql
                    .createQuery(
                            "SELECT u from Veiculo u where u.placa = "
                            + ":placa")
                    .setParameter("placa", placa).getSingleResult();

            System.out.println(veiculo.getIdVeiculo() + " - "
                    + veiculo.getPlaca() + " - "
                    + veiculo.getTipoveiculo() + " - "
                    + veiculo.getMontadora() + " - "
                    + veiculo.getModelo() + " - "
                    + veiculo.getAnoFabricacao() + " - "
                    + veiculo.getObservacao() + " - "
                    + veiculo.getPropietario().getNome() + " - "
                    + veiculo.getPropietario().getCpf_cnpj() + " - "
                    + veiculo.getAgendamento());

            return veiculo;

        } catch (Exception e) {
            System.out.println(" Erro (" + e.getMessage()
                    + ") ao tentar buscar veículo no sistema "
                    + "ou o não existe veiculo com esta placa no sistema!");
            return null;

        } finally {
            managerMysql.close();
            conexao.close();
        }
    }

    public String excluirVeiculo(String placa) {

        Veiculo veiculo = new Veiculo();

        try {
            conexaoMysql.begin();

            veiculo = (Veiculo) managerMysql.createQuery(
                    "SELECT u from Veiculo u where u.placa = :placa")
                    .setParameter("placa", placa).getSingleResult();

            managerMysql.remove(veiculo);
            conexaoMysql.commit();

            System.out.println(" veiculo com placa " + veiculo.getPlaca()
                    + " foi excluido com sucesso!");

        } catch (Exception e) {

            System.out.println("Este veículo não pode ser excluido, "
                    + "pode está vinculado a algum cadastro ou não existe no sistema!");

        } finally {
            managerMysql.close();
            conexao.close();
        }

        return "Operação realizada com sucesso!";
    }

    public String AtualizarDadosVeiculo(
            int codigo,
            String placa,
            TipoVeiculo tipoveiculo,
            Montadora montadora,
            String modelo,
            int anoFabricacao,
            String observacao,
            int codigoPessoa,
            int codigoAgenda) {

        Veiculo veiculo = new Veiculo();

        long id = codigo;
        long idPessoa = codigoPessoa;
        long idAgenda = codigoAgenda;

        try {
            conexaoMysql.begin();
            veiculo = managerMysql.find(Veiculo.class, id);
            Pessoa pessoa = managerMysql.find(Pessoa.class, idPessoa);
            Agendamento agenda = managerMysql.find(Agendamento.class, idAgenda);

            veiculo.setPlaca(placa);
            veiculo.setTipoveiculo(tipoveiculo);
            veiculo.setMontadora(montadora);
            veiculo.setModelo(modelo);
            veiculo.setAnoFabricacao(anoFabricacao);
            veiculo.setObservacao(observacao);
            veiculo.setPropietario(pessoa);
            veiculo.setAgendamento(agenda);

            conexaoMysql.commit();

        } catch (Exception e) {
            conexaoMysql.rollback();
            System.out.println("Erro (" + e.getMessage()
                    + ") ao tentar atualizar Veículo! "
                    + "Cofira os dados e tente novamente.");

        } finally {
            managerMysql.close();
            conexao.close();
        }
        return "Opearação realizada com sucesso!";
    }
}
