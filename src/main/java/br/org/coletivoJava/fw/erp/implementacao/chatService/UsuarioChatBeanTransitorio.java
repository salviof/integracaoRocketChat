/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.coletivoJava.fw.erp.implementacao.chatService;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author salvio
 */
public class UsuarioChatBeanTransitorio implements ItfUsuarioChat {

    private String nome;
    private String codigo;
    private String email;
    private List<String> emailsSecudarios;

    @Override
    public String getCodigo() {
        return codigo;
    }

    @Override
    public String getEmailPrincipal() {
        return email;
    }

    @Override
    public List<String> getEmailsSecundarios() {
        return emailsSecudarios;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEmailsSecudarios(List<String> emailsSecudarios) {
        this.emailsSecudarios = emailsSecudarios;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
