/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.coletivoJava.fw.erp.implementacao.chatService;

import java.util.List;

/**
 *
 * @author salvio
 */
public class SalaChatBeanTransitorio implements ItfChatSalaBean {

    private String codigo;
    private String nome;
    private String urlSala;
    private String urlSalaFull;
    private boolean existe;

    private List<ItfUsuarioChat> usuarios;

    @Override
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public String getUrlSala() {
        return urlSala;
    }

    public void setUrlSala(String urlSala) {
        this.urlSala = urlSala;
    }

    @Override
    public String getUrlSalaFull() {
        return urlSalaFull;
    }

    public void setUrlSalaFull(String urlSalaFull) {
        this.urlSalaFull = urlSalaFull;
    }

    @Override
    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    @Override
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public List<ItfUsuarioChat> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<ItfUsuarioChat> usuarios) {
        this.usuarios = usuarios;
    }

}
