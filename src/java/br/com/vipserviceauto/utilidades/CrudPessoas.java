/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vipserviceauto.utilidades;

import br.com.vipserviceauto.daos.PessoaDAO;
import br.com.vipserviceauto.modelos.Estado;
import br.com.vipserviceauto.modelos.TipoPessoa;

/**
 *
 * @author luis.silva
 */
public interface CrudPessoas {

    public static void main(String[] args) throws Exception {
        PessoaDAO pessoa = new PessoaDAO();
      
        //pessoa.buscarTodos();
        pessoa.inserirPessoa(TipoPessoa.FISICA, 
                "Ruan Novaes Novaes",
                "21/09/2001", 
                "1234567-00", 
                "56323252-00", 
                "ruan@gmail.com", 
                "123456", 
                "(65 9 99275216)", 
                "Rua babaçu, nº 47", 
                "Salvador", 
                Estado.BA, 
                "Criente de frota");
                
        //pessoa.selectPessoa("56323252-45");
        //pessoa.excluirPessoa("56323252-05");
//        pessoa.AtualizarDadosUsuario(1, 
//                TipoPessoa.FISICA, 
//                "Luis magno Novaes",
//                "22/07/1974", 
//                "1234567-05", 
//                "56323252-05", 
//                "magno@gmail.com", 
//                "123456", 
//                "(65 9 99275216)", 
//                "Rua babaçu, nº 47", 
//                "Salvador", 
//                Estado.BA, 
//                "Criente de frota");
    }

}
