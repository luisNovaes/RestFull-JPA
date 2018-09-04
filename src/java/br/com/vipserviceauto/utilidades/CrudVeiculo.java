/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vipserviceauto.utilidades;

import br.com.vipserviceauto.daos.VeiculoDAO;
import br.com.vipserviceauto.modelos.Montadora;
import br.com.vipserviceauto.modelos.TipoVeiculo;
import java.text.ParseException;

/**
 *
 * @author luis.silva
 */
public interface CrudVeiculo {

    public static void main(String[] args) throws ParseException {

        VeiculoDAO veiculo = new VeiculoDAO();

        //veiculo.buscarTodos();
        veiculo.inserirVeiculo(
                "GTR-5857", 
                TipoVeiculo.FROTISTA, 
                Montadora.VOLKSWAGEM, 
                "Gol", 
                2017, 
                "Primeira revisão", 
                1, 
                0);
       //veiculo.selectVeiculo("LHD-5555");
       //veiculo.excluirVeiculo("FTR-7897");
       
//       veiculo.AtualizarDadosVeiculo(
//               1, 
//               "DDD-8888", 
//               TipoVeiculo.FROTISTA, 
//               Montadora.GM, 
//               "Celta", 
//               2008, 
//               "Revisar sistema elétrico",
//               1, 
//               0);

    }

}
