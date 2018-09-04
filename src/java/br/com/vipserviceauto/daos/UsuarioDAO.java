/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vipserviceauto.daos;

import br.com.vipserviceauto.modelos.Usuario;
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
public class UsuarioDAO {

    EntityManager managerMysql = conexao.getEntityManager();
    EntityTransaction conexaoMysql = managerMysql.getTransaction();

    public String buscarTodos() throws ParseException {

        try {
            CriteriaBuilder builder = managerMysql.getCriteriaBuilder();
            CriteriaQuery<Usuario> criteriaQuery = builder.createQuery(Usuario.class);

            Root<Usuario> usuario = criteriaQuery.from(Usuario.class);
            criteriaQuery.select(usuario);

            TypedQuery<Usuario> query = managerMysql.createQuery(criteriaQuery);
            List<Usuario> Usuarios = query.getResultList();

            for (Usuario user : Usuarios) {

                System.out.println(user.getUsuarioId()+ " - "
                        + user.getNome() + " - "
                        + user.getEmail() + " - "
                        + user.getSenha());
            }

        } catch (Exception e) {
            System.out.println("Erro (" + e.getMessage()
                    + ") ao consultar usuário no banco!");

        } finally {
            managerMysql.close();
            conexao.close();
        }

        return "Operação realizada com sucesso!";
    }

    public String inserirUsuario(String nome, String email,
            String senha) {

        Usuario usuario = new Usuario();

        try {
            conexaoMysql.begin();

            usuario.setNome(nome);
            usuario.setEmail(email);
            usuario.setSenha(senha);

            managerMysql.persist(usuario);

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

    public Usuario selectUsuario(String email) {

        Usuario usuario = new Usuario();

        try {

            usuario = (Usuario) managerMysql
                    .createQuery(
                            "SELECT u from Usuario u where u.email = "
                            + ":email")
                    .setParameter("email", email).getSingleResult();

            System.out.println(usuario.getUsuarioId()+ " -- "
                    + usuario.getNome() + " -- "
                    + usuario.getEmail() + " -- "
                    + usuario.getSenha());
            return usuario;

        } catch (Exception e) {
            System.out.println(" Erro (" + e.getMessage()
                    + ") ao tentar buscar usuário no sistema ou não existe!");
            return null;

        } finally {
            managerMysql.close();
            conexao.close();
        }
    }

    public String excluirUsuario(String email) {

        Usuario usuario = new Usuario();

        try {
            conexaoMysql.begin();

            usuario = (Usuario) managerMysql.createQuery(
                    "SELECT u from Usuario u where u.email = :email")
                    .setParameter("email", email).getSingleResult();

            managerMysql.remove(usuario);
            conexaoMysql.commit();
            
            System.out.println(" usuario excluido com sucesso!");

        } catch (Exception e) {

            System.out.println("Este usuário não pode ser excluido, "
                    + "pode está vinculado a algum cadastro ou não existe no sistema!");

        } finally {
            managerMysql.close();
            conexao.close();
        }

        return "Operação realizada com sucesso!";
    }

    public String AtualizarDadosUsuario(int codigo, String nome, String email,
            String senha) {

        Usuario usuario = new Usuario();

        long id = codigo;

        try {
            conexaoMysql.begin();
            usuario = managerMysql.find(Usuario.class, id);

            usuario.setNome(nome);
            usuario.setEmail(email);
            usuario.setSenha(senha);

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
