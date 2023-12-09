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

    public ItfChatSalaBeanRC getChat(String nomeSala);

    public ItfChatSalaBeanRC getChatCriandoSeNaoExistir(String nomeSala) throws ErroConexaoServicoChat;

    public boolean excluirSala(ItfChatSalaBeanRC nomeSala) throws ErroConexaoServicoChat;

    public boolean autenticarSessao(String pEmail, String pSenha) throws ErroConexaoServicoChat;

    public List<ItfUsuarioChatRC> atualizarListaDeUsuarios() throws ErroConexaoServicoChat;

    public ItfUsuarioChatRC getUsuario(String pEmail) throws ErroConexaoServicoChat;

    public ItfUsuarioChatRC getUsuarioByCodigo(String pCodigo) throws ErroConexaoServicoChat;

    public List<ItfUsuarioChatRC> getUsuarios();

    public ItfUsuarioChatRC criarUsuario(ItfUsuario pUsuario) throws ErroConexaoServicoChat;

    public ItfUsuarioChatRC criarUsuario(ItfUsuario pUsuario, String pSenha) throws ErroConexaoServicoChat;

    public boolean adicionarUsuario(ItfChatSalaBeanRC pSala, String pEmailSenha) throws ErroConexaoServicoChat;

    public ItfUsuarioChatRC getUsuarioLogado();

    public ItfUsuarioChatRC efetuarLogin(ItfUsuario pUsuario);

    public ItfUsuarioChatRC efetuarLogin(ItfUsuario pUsuario, String pSenha);

    public ItfChatSalaBeanRC getSalaAtualizada(ItfChatSalaBeanRC pSala);

}
