/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vipserviceauto.utilidades;

import br.com.vipserviceauto.daos.AgendamentoDAO;
import br.com.vipserviceauto.modelos.HoraAgenda;
import br.com.vipserviceauto.modelos.TipoServico;
import java.math.BigDecimal;
import java.text.ParseException;

/**
 *
 * @author luis.silva
 */
public interface CrudAgendamento {

    public static void main(String[] args) throws ParseException {

        AgendamentoDAO agenda = new AgendamentoDAO();
        //agenda.buscarTodos();
        agenda.inserirAgendamento(
                TipoServico.ÉLETRICO,
                "04/09/2018",
                HoraAgenda.NOVE_HORAS,
                (new BigDecimal(250)),
                "Veículo em trânsito",
                2,
                1,
                1);
               
       //agenda.selectAgendamento(1);
      //agenda.excluirAgendamento(2);
//      agenda.AtualizarDadosAgendamento( 2,
//                TipoServico.ÉLETRICO, 
//                "30/12/2018", 
//                HoraAgenda.SEIS_HORAS, 
//                (new BigDecimal(1000)), 
//                "Veículo em garantia", 
//                1, 
//                8, 
//                1);

    }

}
