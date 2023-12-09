/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.coletivoJava.fw.erp.implementacao.chatService;

import br.org.coletivoJava.fw.api.erp.chat.model.ItfUsuarioChat;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemSimples;
import java.util.List;

/**
 *
 * @author salvio
 */
public class SalaChatBeanTransitorio extends ItemSimples implements ItfChatSalaBeanRC {

    private String codigo;
    private String nome;
    private String urlSala;
    private String urlSalaFull;
    private boolean existe;

    private List<ItfUsuarioChatRC> usuarios;

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
        return (List) usuarios;
    }

    public void setUsuarios(List<ItfUsuarioChatRC> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public String getCodigoChat() {
        return codigo;
    }

    @Override
    public String getApelido() {
        return nome;
    }

}
