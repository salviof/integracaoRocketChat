/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.org.coletivoJava.fw.erp.implementacao.chatService;

import java.util.List;

/**
 *
 * @author salvio
 */
public interface ItfUsuarioChat {

    public String getCodigo();

    public String getEmailPrincipal();

    public List<String> getEmailsSecundarios();

}
