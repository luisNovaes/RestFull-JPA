/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vipserviceauto.daos;

import br.com.vipserviceauto.modelos.Pecas;
import br.com.vipserviceauto.modelos.Usuario;
import br.com.vipserviceauto.utilidades.conexao;
import java.math.BigDecimal;
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
public class PecasDAO extends conexao {

    EntityManager managerMysql = conexao.getEntityManager();
    EntityTransaction conexaoMysql = managerMysql.getTransaction();

    public String buscarTodos() throws ParseException {

        try {
            CriteriaBuilder builder = managerMysql.getCriteriaBuilder();
            CriteriaQuery<Pecas> criteriaQuery = builder.createQuery(Pecas.class);

            Root<Pecas> pecas = criteriaQuery.from(Pecas.class);
            criteriaQuery.select(pecas);

            TypedQuery<Pecas> query = managerMysql.createQuery(criteriaQuery);
            List<Pecas> Pecass = query.getResultList();

            for (Pecas pec : Pecass) {

                System.out.println(pec.getIdPeca() + " - "
                        + pec.getNome() + " - "
                        + pec.getFabricante() + " - "
                        + pec.getModelo() + " - "
                        + pec.getValorPeca() + " - "
                        + pec.getObservacao());
            }

        } catch (Exception e) {
            System.out.println("Erro (" + e.getMessage()
                    + ") ao consultar peças no banco!");

        } finally {
            managerMysql.close();
            conexao.close();
        }

        return "Operação realizada com sucesso!";
    }

    public String inserirUsuario(
            String nome,
            String fabricante,
            String modelo,
            BigDecimal valorPeca,
            String observacao) {

        Pecas pecas = new Pecas();

        try {
            conexaoMysql.begin();

            pecas.setNome(nome);
            pecas.setFabricante(fabricante);
            pecas.setModelo(modelo);
            pecas.setValorPeca(valorPeca);
            pecas.setObservacao(observacao);

            managerMysql.persist(pecas);

            conexaoMysql.commit();

        } catch (Exception e) {
            System.out.println(" Erro ao tentar inserir a peça no sistema! "
                    + " ou já existe uma peça com este nome no sistema.");

        } finally {
            managerMysql.close();
            conexao.close();
        }
        return "Operação realizada com sucesso!";
    }

    public Pecas selectPecas(String nome) {

        Pecas pecas = new Pecas();

        try {

            pecas = (Pecas) managerMysql
                    .createQuery(
                            "SELECT u from Pecas u where u.nome = "
                            + ":nome")
                    .setParameter("nome", nome).getSingleResult();

            System.out.println(pecas.getIdPeca() + " -- "
                    + pecas.getNome() + " -- "
                    + pecas.getFabricante() + " -- "
                    + pecas.getModelo() + " - "
                    + pecas.getValorPeca() + " - "
                    + pecas.getObservacao());
            
            return pecas;

        } catch (Exception e) {
            System.out.println(" Erro (" + e.getMessage()
                    + ") ao tentar buscar a peças no sistema ou não existe!");
            return null;

        } finally {
            managerMysql.close();
            conexao.close();
        }
    }

    public String excluirUsuario(String nome) {

        Pecas pecas = new Pecas();

        try {
            conexaoMysql.begin();

            pecas = (Pecas) managerMysql.createQuery(
                    "SELECT u from Pecas u where u.nome = :nome")
                    .setParameter("nome", nome).getSingleResult();

            managerMysql.remove(pecas);
            conexaoMysql.commit();

            System.out.println(" Peça excluida com sucesso!");

        } catch (Exception e) {

            System.out.println("Esta peça não pode ser excluida, "
                    + " ou está vinculado a algum cadastro ou não existe no sistema!");

        } finally {
            managerMysql.close();
            conexao.close();
        }

        return "Operação realizada com sucesso!";
    }

    public String AtualizarDadosPecas(
            int codigo, 
            String nome,
            String fabricante,
            String modelo,
            BigDecimal valorPeca,
            String observacao) {

        Pecas pecas = new Pecas();

        long id = codigo;

        try {
            conexaoMysql.begin();
            pecas = managerMysql.find(Pecas.class, id);

            pecas.setNome(nome);
            pecas.setFabricante(fabricante);
            pecas.setModelo(modelo);
            pecas.setValorPeca(valorPeca);
            pecas.setObservacao(observacao);
            
            conexaoMysql.commit();

        } catch (Exception e) {
            conexaoMysql.rollback();
            System.out.println("Erro (" + e.getMessage()
                    + ") ao tentar atualizar a peça! "
                    + "Cofira os dados e tente novamente.");

        } finally {
            managerMysql.close();
            conexao.close();
        }
        return "Opearação realizada com sucesso!";
    }
}
