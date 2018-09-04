/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vipserviceauto.utilidades;

import br.com.vipserviceauto.daos.FaturaDAO;
import br.com.vipserviceauto.modelos.StatusDoc;
import java.math.BigDecimal;
import java.text.ParseException;

/**
 *
 * @author luis.silva
 */
public interface CrudFatura {

    public static void main(String[] args) throws ParseException {

        FaturaDAO fatura = new FaturaDAO();

        //fatura.buscarTodos();
//        fatura.inserirFatura(
//                (new BigDecimal(500)), 
//                StatusDoc.PENDENTE, 
//                "13/09/2018", 
//                "13/10/2018", 
//                1);
        //fatura.selectFatura(1);
        //fatura.excluirFatura(3);
        fatura.AtualizarDadosFatura(1,
                (new BigDecimal(300)),
                StatusDoc.OUTROS,
                "20/09/2018",
                "120/10/2018",
                1);
    }

}
