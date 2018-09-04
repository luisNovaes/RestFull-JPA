/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vipserviceauto.utilidades;

import br.com.vipserviceauto.daos.UsuarioDAO;
import java.text.ParseException;

/**
 *
 * @author Luis Novaes
 */
public class CrudUsuario {

    public static void main(String[] args) throws ParseException {

        UsuarioDAO usuario = new UsuarioDAO();

        // usuario.buscarTodos();
//    usuario.inserirUsuario("kawan Novaes", 
//            "kawan@gmail.com", 
//            "123456");
        //usuario.selectUsuario("kawan@gmail.com");
        //usuario.excluirUsuario("kawan@gmail.com");
        usuario.AtualizarDadosUsuario(2, 
                "Zilda Silva", 
                "zilda@gmail.com", 
                "123456");

    }

}
