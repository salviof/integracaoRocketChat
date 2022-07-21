package br.org.coletivoJava.fw.erp.implementacao.chatService;

import java.util.List;

public interface ItfChatSalaBean {

    public String getCodigo();

    public String getNome();

    public String getUrlSala();

    public String getUrlSalaFull();

    boolean isExiste();

    public List<ItfUsuarioChat> getUsuarios();

}
