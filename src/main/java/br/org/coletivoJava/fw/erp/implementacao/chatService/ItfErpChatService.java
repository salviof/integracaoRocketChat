/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.org.coletivoJava.fw.erp.implementacao.chatService;

import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import java.util.List;

/**
 *
 * @author salvio
 */
public interface ItfErpChatService {

    public ItfChatSalaBean getChat(String nomeSala);

    public ItfChatSalaBean getChatCriandoSeNaoExistir(String nomeSala) throws ErroConexaoServicoChat;

    public boolean excluirSala(ItfChatSalaBean nomeSala) throws ErroConexaoServicoChat;

    public boolean autenticarSessao(String pEmail, String pSenha) throws ErroConexaoServicoChat;

    public List<ItfUsuarioChat> atualizarListaDeUsuarios() throws ErroConexaoServicoChat;

    public ItfUsuarioChat getUsuario(String pEmail) throws ErroConexaoServicoChat;

    public ItfUsuarioChat getUsuarioByCodigo(String pCodigo) throws ErroConexaoServicoChat;

    public List<ItfUsuarioChat> getUsuarios();

    public ItfUsuarioChat criarUsuario(ItfUsuario pUsuario) throws ErroConexaoServicoChat;

    public ItfUsuarioChat criarUsuario(ItfUsuario pUsuario, String pSenha) throws ErroConexaoServicoChat;

    public boolean adicionarUsuario(ItfChatSalaBean pSala, String pEmailSenha) throws ErroConexaoServicoChat;

    public ItfUsuarioChat getUsuarioLogado();

    public ItfUsuarioChat efetuarLogin(ItfUsuario pUsuario);

    public ItfUsuarioChat efetuarLogin(ItfUsuario pUsuario, String pSenha);

    public ItfChatSalaBean getSalaAtualizada(ItfChatSalaBean pSala);

}
