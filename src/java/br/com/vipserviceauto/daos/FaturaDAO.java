/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vipserviceauto.daos;

import br.com.vipserviceauto.modelos.Agendamento;
import br.com.vipserviceauto.modelos.Fatura;
import br.com.vipserviceauto.modelos.StatusDoc;
import br.com.vipserviceauto.utilidades.conexao;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author luis.silva
 */
public class FaturaDAO extends conexao {

    EntityManager managerMysql = conexao.getEntityManager();
    EntityTransaction conexaoMysql = managerMysql.getTransaction();

    public String buscarTodos() {

        try {
            CriteriaBuilder builder = managerMysql.getCriteriaBuilder();
            CriteriaQuery<Fatura> criteriaQuery = builder.createQuery(Fatura.class);

            Root<Fatura> fatura = criteriaQuery.from(Fatura.class);
            criteriaQuery.select(fatura);

            TypedQuery<Fatura> query = managerMysql.createQuery(criteriaQuery);
            List<Fatura> faturas = query.getResultList();

            for (Fatura fatu : faturas) {

                Date dataFat = fatu.getDatafatura();
                SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
                String datafatura = sdf1.format(dataFat);

                Date dataVenc = fatu.getDataVencimento();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String dataVencimeto = sdf.format(dataVenc);

                System.out.println(fatu.getValorTotDoc() + " - "
                        + fatu.getValorTotDoc() + " - "
                        + fatu.getStatusDoc() + " - "
                        + datafatura + " - "
                        + dataVencimeto + " - "
                        + fatu.getAgendamento()
                                .getVeiculo()
                                .getPropietario()
                                .getCpf_cnpj() + " - "
                        + fatu.getAgendamento()
                                .getVeiculo()
                                .getPropietario()
                                .getNome());

            }

        } catch (Exception e) {
            System.out.println("Erro (" + e.getMessage()
                    + ") ao consultar fatura no banco!");

        } finally {
            managerMysql.close();
            conexao.close();
        }

        return "Operação realizada com sucesso!";
    }

    public String inserirFatura(
            BigDecimal ValorTotDoc,
            StatusDoc StatusDoc,
            String datafatura,
            String dataVencimento,
            int agendamento) throws ParseException {

        Fatura fatura = new Fatura();

        long idAgenda = agendamento;

        DateFormat f = DateFormat.getDateInstance();
        Date dataFat = f.parse(datafatura);
        System.out.println(dataFat);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        System.out.println("Data formatada: " + sdf.format(dataFat));

        DateFormat f1 = DateFormat.getDateInstance();
        Date dataVenc = f1.parse(dataVencimento);
        System.out.println(dataVenc);

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd");
        System.out.println("Data formatada: " + sdf1.format(dataVenc));

        try {

            Agendamento agenda = managerMysql.find(Agendamento.class, idAgenda);
            conexaoMysql.begin();

            fatura.setValorTotDoc(ValorTotDoc);
            fatura.setStatusDoc(StatusDoc);
            fatura.setDatafatura(dataFat);
            fatura.setDataVencimento(dataVenc);
            fatura.setAgendamento(agenda);

            managerMysql.persist(fatura);

            conexaoMysql.commit();

        } catch (Exception e) {
            System.out.println(" Erro ao tentar inserir a fatuta no sistema! "
                    + "Já existe um usuario com este nome no sistema.");

        } finally {
            managerMysql.close();
            conexao.close();
        }
        return "Operação realizada com sucesso!";
    }

    public Fatura selectFatura(int codDocumento) {

        Fatura fatu = new Fatura();

        long id = codDocumento;

        try {

            fatu = (Fatura) managerMysql
                    .createQuery(
                            "SELECT u from Fatura u where u.codDocumento = "
                            + ":codDocumento")
                    .setParameter("codDocumento", id).getSingleResult();

            Date dataFat = fatu.getDatafatura();
            SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
            String datafatura = sdf1.format(dataFat);

            Date dataVenc = fatu.getDataVencimento();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String dataVencimeto = sdf.format(dataVenc);

            System.out.println(fatu.getValorTotDoc() + " - "
                    + fatu.getValorTotDoc() + " - "
                    + fatu.getStatusDoc() + " - "
                    + datafatura + " - "
                    + dataVencimeto + " - "
                    + fatu.getAgendamento()
                            .getVeiculo()
                            .getPropietario()
                            .getCpf_cnpj() + " - "
                    + fatu.getAgendamento()
                            .getVeiculo()
                            .getPropietario()
                            .getNome());
            return fatu;

        } catch (Exception e) {
            System.out.println(" Erro (" + e.getMessage()
                    + ") ao tentar buscar a Fatura no sistema"
                    + " ou não existe fatura com este código!");
            return null;

        } finally {
            managerMysql.close();
            conexao.close();
        }
    }

    public String excluirFatura(int codDocumento) {

        Fatura fatura = new Fatura();

        long id = codDocumento;

        try {
            conexaoMysql.begin();

            fatura = (Fatura) managerMysql.createQuery(
                    "SELECT u from Fatura u where u.codDocumento = :codDocumento")
                    .setParameter("codDocumento", id).getSingleResult();

            managerMysql.remove(fatura);
            conexaoMysql.commit();

            System.out.println(" Fatura excluido com sucesso!");

        } catch (Exception e) {

            System.out.println("Esta fatura não pode ser excluido, "
                    + "pode está vinculado a algum cadastro ou não existe no sistema!");

        } finally {
            managerMysql.close();
            conexao.close();
        }

        return "Operação realizada com sucesso!";
    }

    public String AtualizarDadosFatura(
            int codigo,
            BigDecimal ValorTotDoc,
            StatusDoc StatusDoc,
            String datafatura,
            String dataVencimento,
            int agendamento) throws ParseException {

        Fatura fatura = new Fatura();
        
        long idFatura = codigo;
        long idAgenda = agendamento;

        DateFormat f = DateFormat.getDateInstance();
        Date dataFat = f.parse(datafatura);
        System.out.println(dataFat);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        System.out.println("Data formatada: " + sdf.format(dataFat));

        DateFormat f1 = DateFormat.getDateInstance();
        Date dataVenc = f1.parse(dataVencimento);
        System.out.println(dataVenc);

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd");
        System.out.println("Data formatada: " + sdf1.format(dataVenc));

        try {
      
             fatura = managerMysql.find(Fatura.class, idFatura);
            Agendamento agenda = managerMysql.find(Agendamento.class, idAgenda);
            conexaoMysql.begin();

            fatura.setValorTotDoc(ValorTotDoc);
            fatura.setStatusDoc(StatusDoc);
            fatura.setDatafatura(dataFat);
            fatura.setDataVencimento(dataVenc);
            fatura.setAgendamento(agenda);

            conexaoMysql.commit();

        } catch (Exception e) {
            conexaoMysql.rollback();
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
