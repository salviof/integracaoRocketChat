/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.coletivoJava.fw.erp.implementacao.chatService;

import br.org.coletivoJava.integracoes.restRocketChat.api.FabConfigRocketChat;
import br.org.coletivoJava.integracoes.restRocketChat.api.channel.FabApiRestRocketChatV1Channel;
import br.org.coletivoJava.integracoes.restRocketChat.api.users.FabApiRestRokcetChatV1Users;
import br.org.coletivoJava.integracoes.restRocketChat.implementacao.GestaoTokenRestRocketChat;
import br.org.coletivoJava.integracoes.restRocketChat.implementacao.IntegracaoRestRocketChatGrupoExisteGrupo;
import br.org.coletivoJava.integracoes.restRocketChat.implementacao.IntegracaoRestRocketChatUsuariosEncontrarPorEmail;

import com.google.common.collect.Lists;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.ItfRespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.token.ItfTokenGestao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author salvio
 */
public class ServicoChatRocketChat implements ItfErpChatService {

    private final static List<ItfChatSalaBeanRC> ULTIMAS_SALAS = new ArrayList<>();
    private final static Map<String, Integer> MAPA_INDICE_SALA = new HashMap<>();
    private final static Map<String, ItfUsuarioChatRC> MAPA_USUARIOS = new HashMap<>();

    private static int slot = 0;

    private synchronized void persistirUsuario(ItfUsuarioChatRC pSala) {
        MAPA_USUARIOS.put(pSala.getEmailPrincipal(), pSala);
    }

    private synchronized void persistirSala(ItfChatSalaBeanRC pSala) {

        if (slot > 20) {
            slot = 0;
        } else {
            slot++;
        }
        if (slot >= ULTIMAS_SALAS.size()) {
            ULTIMAS_SALAS.add(pSala);
        } else {
            ULTIMAS_SALAS.add(slot, pSala);
        }
    }

    @Override
    public boolean autenticarSessao(String pEmail, String pSenha) throws ErroConexaoServicoChat {
        String usuarioAuxiliar = SBCore.getConfigModulo(FabConfigRocketChat.class).getPropriedade(FabConfigRocketChat.USUARIO_ASSISTENTE_DE_CANAIS);
        ItfRespostaWebServiceSimples respostaQuemSouEu;
        GestaoTokenRestRocketChat gestaoToken;
        ItfUsuario pUsuario;
        if (pEmail.equals(usuarioAuxiliar)) {
            respostaQuemSouEu = FabApiRestRocketChatV1Channel.QUEM_SOU_EU.getAcao().getResposta();
            gestaoToken = (GestaoTokenRestRocketChat) FabApiRestRocketChatV1Channel.QUEM_SOU_EU.getGestaoToken();
        } else {
            pUsuario = SBCore.getServicoPermissao().getUsuarioByEmail(pEmail);
            if (pUsuario == null) {
                throw new ErroConexaoServicoChat("Nenhun usuário do sistema encontrado com e-mail" + pEmail);
            }
            respostaQuemSouEu = FabApiRestRocketChatV1Channel.QUEM_SOU_EU.getAcao(pUsuario).getResposta();
            gestaoToken = (GestaoTokenRestRocketChat) FabApiRestRocketChatV1Channel.QUEM_SOU_EU.getGestaoToken(pUsuario);
        }

        if (gestaoToken.isTemTokemAtivo() && respostaQuemSouEu.isSucesso()) {
            return true;
        } else {
            gestaoToken.setLoginNomeUsuario(pEmail);
            gestaoToken.setLoginSenhaUsuario(pSenha);
            gestaoToken.validarToken();
            if (gestaoToken.isTemTokemAtivo()) {
                return true;
            } else {
                gestaoToken.excluirToken();
                gestaoToken.gerarNovoToken();
                if (gestaoToken.isTemTokemAtivo()) {
                    return true;
                }
            }
        }
        return false;

    }

    private ItfChatSalaBeanRC getSalaConsultandoApi(String pNome) {
        SalaChatBeanTransitorio salaChat = new SalaChatBeanTransitorio();
        salaChat.setNome(pNome);
        IntegracaoRestRocketChatGrupoExisteGrupo grupoExisteApi = (IntegracaoRestRocketChatGrupoExisteGrupo) FabApiRestRocketChatV1Channel.GRUPO_EXISTE_GRUPO.getAcao(pNome);
        boolean salaExiste = !(grupoExisteApi.getResposta() == null || !grupoExisteApi.getResposta().isSucesso());
        if (!salaExiste) {
            return salaChat;
        } else {

            JsonObject grupo = (JsonObject) grupoExisteApi.getResposta().getRespostaComoObjetoJson().get("group");

            String apicodigoGrupo = grupoExisteApi.getCodigoGrupo();
            ItfRespostaWebServiceSimples respostaListarUsuarios = FabApiRestRocketChatV1Channel.GRUPO_LISTAR_USUARIOS.getAcao(apicodigoGrupo).getResposta();
            if (respostaListarUsuarios.isSucesso()) {
                JsonObject usuariosDoGrupoJson = respostaListarUsuarios.getRespostaComoObjetoJson();
                JsonArray membros = usuariosDoGrupoJson.getJsonArray("members");
                List<ItfUsuarioChatRC> listaUsiariosMembros = new ArrayList<>();
                for (Object membro : membros) {
                    JsonObject membroJsonOb = (JsonObject) membro;
                    String codigoMembro = (String) membroJsonOb.getString("_id");
                    try {
                        ItfUsuarioChatRC usuario = getUsuarioByCodigo(codigoMembro);
                        listaUsiariosMembros.add(usuario);

                    } catch (ErroConexaoServicoChat ex) {
                        Logger.getLogger(ServicoChatRocketChat.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                salaChat.setUsuarios(listaUsiariosMembros);

            }
            String apiurlCanel = grupoExisteApi.getUrlEmbEmbedded();
            salaChat.setCodigo(apicodigoGrupo);
            salaChat.setExiste(true);
            salaChat.setUrlSala(apiurlCanel);
            salaChat.setUrlSalaFull(apiurlCanel.replace("?layout=embedded", ""));
            return salaChat;
        }

    }

    @Override
    public ItfChatSalaBeanRC getChat(String pNome) {

        Integer indice = MAPA_INDICE_SALA.get(pNome);
        if (indice == null) {
            ItfChatSalaBeanRC salaChat = getSalaConsultandoApi(pNome);

            persistirSala(salaChat);

            return salaChat;
        } else {
            return ULTIMAS_SALAS.get(indice - 1);
        }

    }

    @Override
    public ItfChatSalaBeanRC getChatCriandoSeNaoExistir(String pNomeSala) throws ErroConexaoServicoChat {
        ItfChatSalaBeanRC sala = getChat(pNomeSala);
        if (!sala.isExiste()) {
            ItfRespostaWebServiceSimples resp = FabApiRestRocketChatV1Channel.GRUPO_NOVO.getAcao(pNomeSala).getResposta();
            if (!resp.isSucesso()) {
                try {
                    if (!FabApiRestRocketChatV1Channel.QUEM_SOU_EU.getAcao().getResposta().isSucesso()) {
                        ItfTokenGestao gestao = FabApiRestRocketChatV1Channel.QUEM_SOU_EU.getGestaoToken();
                        gestao.excluirToken();
                        gestao.gerarNovoToken();
                        if (gestao.isTemTokemAtivo()) {
                            ItfRespostaWebServiceSimples respCriacaoSenhaPosRenovacaoDeAcesso = FabApiRestRocketChatV1Channel.GRUPO_NOVO.getAcao(pNomeSala).getResposta();
                            if (respCriacaoSenhaPosRenovacaoDeAcesso.isSucesso()) {
                                MAPA_INDICE_SALA.remove(pNomeSala);
                                return getChat(pNomeSala);
                            }
                        }
                    }
                } catch (Throwable t) {
                    throw new ErroConexaoServicoChat("Falha criando chat");
                }
            } else {
                MAPA_INDICE_SALA.remove(pNomeSala);
                return getChat(pNomeSala);
            }
        }
        return sala;
    }

    @Override
    public List<ItfUsuarioChatRC> atualizarListaDeUsuarios() throws ErroConexaoServicoChat {
        if (!FabApiRestRokcetChatV1Users.USUARIOS_LISTAR.getAcao().getTokenGestao().isTemTokemAtivo()) {
            FabApiRestRokcetChatV1Users.USUARIOS_LISTAR.getAcao().getTokenGestao().gerarNovoToken();
        }
        JsonObject respJson = FabApiRestRokcetChatV1Users.USUARIOS_LISTAR.getAcao().getResposta().getRespostaComoObjetoJson();

        if (respJson == null) {
            if (!FabApiRestRokcetChatV1Users.USUARIOS_LISTAR.getGestaoToken().isTemTokemAtivo()) {
                FabApiRestRokcetChatV1Users.USUARIOS_LISTAR.getGestaoToken().gerarNovoToken();
            }
            FabApiRestRokcetChatV1Users.USUARIOS_LISTAR.getGestaoToken().excluirToken();
            respJson = FabApiRestRokcetChatV1Users.USUARIOS_LISTAR.getAcao().getResposta().getRespostaComoObjetoJson();
            if (respJson == null) {
                throw new ErroConexaoServicoChat("Falha atualizando lista de usuários");
            }
        }

        JsonArray usuarioJson = respJson.getJsonArray("users");

        usuarioJson.stream().forEach(usr -> {
            UsuarioChatBeanTransitorio novousuarop = new UsuarioChatBeanTransitorio();
            JsonObject usuario = (JsonObject) usr;
            List<String> emailSecundario = new ArrayList<>();
            JsonArray emails = usuario.getJsonArray("emails");
            emails.forEach(mail -> {
                JsonObject emailJson = (JsonObject) mail;

                emailSecundario.add(emailJson.get("address").toString());
            });
            novousuarop.setEmailsSecudarios(emailSecundario);
            if (!novousuarop.getEmailsSecundarios().isEmpty()) {
                if (novousuarop.getEmailsSecundarios().get(0) != null) {
                    novousuarop.setEmail(novousuarop.getEmailsSecundarios().get(0));
                }
            }
            String id = usuario.get("_id").toString();
            String nome = usuario.get("name").toString();
            novousuarop.setCodigo(id);
            novousuarop.setNome(nome);
            persistirUsuario(novousuarop);

        });
        return Lists.newArrayList(MAPA_USUARIOS.values());
    }

    @Override
    public ItfUsuarioChatRC getUsuario(String pEmail) throws ErroConexaoServicoChat {
        ItfUsuarioChatRC usuario = MAPA_USUARIOS.get(pEmail);
        if (usuario != null) {
            return usuario;
        }
        IntegracaoRestRocketChatUsuariosEncontrarPorEmail pesquisaEmail = (IntegracaoRestRocketChatUsuariosEncontrarPorEmail) FabApiRestRokcetChatV1Users.USUARIOS_ENCONTRAR_POR_EMAIL.getAcao(pEmail);
        ItfRespostaWebServiceSimples respPesquisaEmail = pesquisaEmail.getResposta();
        if (respPesquisaEmail.isSucesso()) {
            UsuarioChatBeanTransitorio usuarioDados = new UsuarioChatBeanTransitorio();
            JsonObject resposta = respPesquisaEmail.getRespostaComoObjetoJson();
            JsonArray arrayUsuarios = resposta.getJsonArray("users");

            JsonObject usuarioJson = (JsonObject) arrayUsuarios.get(0);

            if (arrayUsuarios.size() > 1) {
                throw new ErroConexaoServicoChat("O e-mail: " + pEmail + " está vinculado a mais de uma conta");
            }

            JsonArray emailsUsuario = usuarioJson.getJsonArray("emails");
            JsonObject endereco = (JsonObject) emailsUsuario.get(0);
            String email = endereco.get("address").toString();
            usuarioDados.setCodigo((String) usuarioJson.getString("_id"));
            usuarioDados.setEmail(pEmail);
            usuarioDados.setNome((String) usuarioJson.getString("name"));
            return usuarioDados;

        }
        return null;
    }

    @Override
    public List<ItfUsuarioChatRC> getUsuarios() {
        return Lists.newArrayList(MAPA_USUARIOS.values());
    }

    @Override
    public ItfUsuarioChatRC getUsuarioByCodigo(String pCodigo) throws ErroConexaoServicoChat {
        Optional<ItfUsuarioChatRC> usuarioPesquisa
                = MAPA_USUARIOS.values()
                        .stream().filter(usuario -> usuario.getCodigo().equals(pCodigo)).findFirst();

        if (usuarioPesquisa.isPresent()) {
            return usuarioPesquisa.get();
        }
        ItfRespostaWebServiceSimples resposta = FabApiRestRokcetChatV1Users.USUARIOS_ENCONTRAR_POR_CODIGO.getAcao(pCodigo).getResposta();
        JsonObject usuarioJson = (JsonObject) resposta.getRespostaComoObjetoJson().get("user");

        JsonArray emailsUsuario = usuarioJson.getJsonArray("emails");
        JsonObject endereco = (JsonObject) emailsUsuario.get(0);
        String email = endereco.get("address").toString();
        return getUsuario(email);

    }

    public String buildSenha(ItfUsuario pUsuario) {
        StringBuilder senhaBuilder = new StringBuilder();
        String codEmail = String.valueOf(pUsuario.getEmail().hashCode()).substring(0, 5);
        senhaBuilder.append(SBCore.getConfigModulo(FabConfigRocketChat.class).getPropriedade(FabConfigRocketChat.SENHA_BUILDER_PADRAO));
        senhaBuilder.append(codEmail);

        return senhaBuilder.toString();
    }

    @Override
    public ItfUsuarioChatRC criarUsuario(ItfUsuario pUsuario) throws ErroConexaoServicoChat {

        return criarUsuario(pUsuario, buildSenha(pUsuario));

    }

    @Override
    public ItfUsuarioChatRC criarUsuario(ItfUsuario pUsuario, String pSenha) throws ErroConexaoServicoChat {

        ItfUsuarioChatRC usuarioJaCadastrado = getUsuario(pUsuario.getEmail());

        if (usuarioJaCadastrado == null) {
            ItfRespostaWebServiceSimples resposta = FabApiRestRokcetChatV1Users.USUARIOS_CRIAR.getAcao(pUsuario.getNome(), pUsuario.getEmail(), pSenha, pUsuario.getApelido()).getResposta();
            JsonObject respostaJson = resposta.getRespostaComoObjetoJson();
            JsonObject usuariojson = (JsonObject) respostaJson.getJsonObject("user");
            UsuarioChatBeanTransitorio usuario = new UsuarioChatBeanTransitorio();
            String codigo = respostaJson.get("_id").toString();
            usuario.setCodigo(codigo);
            usuario.setEmail(pUsuario.getEmail());
            usuario.setNome(pUsuario.getNome());
            return usuario;
        } else {
            return usuarioJaCadastrado;
        }

    }

    @Override
    public ItfUsuarioChatRC getUsuarioLogado() {
        try {
            ItfUsuarioChatRC usuario = getUsuario(SBCore.getUsuarioLogado().getEmail());
            return usuario;
        } catch (ErroConexaoServicoChat ex) {
            Logger.getLogger(ServicoChatRocketChat.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ItfUsuarioChatRC efetuarLogin(ItfUsuario pUsuario) {
        return efetuarLogin(pUsuario, buildSenha(pUsuario));
    }

    @Override
    public ItfUsuarioChatRC efetuarLogin(ItfUsuario pUsuario, String pSenha) {
        try {
            if (autenticarSessao(pUsuario.getEmail(), pSenha)) {
                return getUsuario(pUsuario.getEmail());
            } else {
                return null;
            }
        } catch (ErroConexaoServicoChat ex) {
            return null;
        }

    }

    @Override
    public boolean excluirSala(ItfChatSalaBeanRC nomeSala) throws ErroConexaoServicoChat {
        return FabApiRestRocketChatV1Channel.GRUPO_EXCLUIR_GRUPO.getAcao(nomeSala.getCodigo()).getResposta().isSucesso();
    }

    @Override
    public boolean adicionarUsuario(ItfChatSalaBeanRC pSala, String pEmailSenha) {
        try {
            ItfUsuarioChatRC usuario = getUsuario(pEmailSenha);
            if (pSala.getUsuarios().stream().filter(us -> us.getEmailPrincipal().equals(pEmailSenha)).findAny().isPresent()) {
                return true;
            }

            ItfRespostaWebServiceSimples respostaAddUserResponsavel = FabApiRestRocketChatV1Channel.GRUPO_ADICIONAR_USUARIO.getAcao(pSala, usuario.getCodigo()).getResposta();
            return respostaAddUserResponsavel.isSucesso();
        } catch (ErroConexaoServicoChat ex) {

            return false;
        }

    }

    @Override
    public ItfChatSalaBeanRC getSalaAtualizada(ItfChatSalaBeanRC pSala) {
        MAPA_INDICE_SALA.remove(pSala.getNome());
        return getChat(pSala.getNome());
    }

}
