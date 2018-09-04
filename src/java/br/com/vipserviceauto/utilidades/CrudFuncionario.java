/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vipserviceauto.utilidades;

import br.com.vipserviceauto.daos.FuncionarioDAO;
import br.com.vipserviceauto.modelos.Escolaridade;
import br.com.vipserviceauto.modelos.Especialidade;
import br.com.vipserviceauto.modelos.Filial;
import br.com.vipserviceauto.modelos.Funcao;
import br.com.vipserviceauto.modelos.Setor;
import br.com.vipserviceauto.modelos.Turno;
import java.text.ParseException;

/**
 *
 * @author luis.silva
 */
public interface CrudFuncionario {

    public static void main(String[] args) throws ParseException {

        FuncionarioDAO funcionario = new FuncionarioDAO();

        //funcioanrio.buscarTodos();
        funcionario.inserirfuncionario(
                1,
                Funcao.ANALISTA,
                Escolaridade.NÍVEL_SUPERIOR,
                Especialidade.FINANÇAS,
                "26/10/2017",
                "28/08/2018",
                Filial.CUIABÁ,
                Setor.OUTROS,
                Turno.TARDE);
        
        //funcionario.selectFuncioanrio(1);
        //funcionario.excluirFuncionario(4);
//        funcionario.AtualizarDadosFuncionario(5,3,
//                Funcao.ELETRICISTA,
//                Escolaridade.NÍVEL_MÉDIO,
//                Especialidade.ELÉTRICA,
//                "03/09/2017",
//                "15/08/2018",
//                Filial.PRIMAVERA,
//                Setor.OFICINA,
//                Turno.ADM);
    }

}
