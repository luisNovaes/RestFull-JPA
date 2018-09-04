/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vipserviceauto.utilidades;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author luis.silva
 */
public class ConversaoStringData {

    public static void main(String[] args) throws Exception {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Date theDate = dateFormat.parse("01/01/2009");
        System.out.println(dateFormat.format(theDate));

    }

}
