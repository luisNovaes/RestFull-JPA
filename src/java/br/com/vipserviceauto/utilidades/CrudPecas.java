/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vipserviceauto.utilidades;

import br.com.vipserviceauto.daos.PecasDAO;
import java.math.BigDecimal;
import java.text.ParseException;

/**
 *
 * @author luis.silva
 */
public interface CrudPecas {

    public static void main(String[] args) throws ParseException {

        PecasDAO pecas = new PecasDAO();
        
       // pecas.buscarTodos();
        pecas.inserirUsuario("Lâmpada interna", 
                "Siemens",
                "enbut", 
                (new BigDecimal(250)), 
                "Lãmpada de painel.");
        //pecas.selectPecas("Farol dianteiro");
        //pecas.excluirUsuario("Farol dianteiro");
//        pecas.AtualizarDadosPecas(1, 
//                "controlador", 
//                "Alem Bradem", 
//                "CLP500", 
//                (new BigDecimal(5000)), 
//                "Controlador lógico, programável");
//
    }

}
