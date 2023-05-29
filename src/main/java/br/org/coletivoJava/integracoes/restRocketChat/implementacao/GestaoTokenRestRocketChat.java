package br.org.coletivoJava.integracoes.restRocketChat.implementacao;

import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.FabTipoConexaoRest;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.RespostaWebServiceSimples;
import br.org.coletivoJava.integracoes.restRocketChat.api.channel.FabApiRestRocketChatV1Channel;
import br.org.coletivoJava.integracoes.restRocketChat.api.FabConfigRocketChat;
import br.org.coletivoJava.integracoes.restRocketChat.api.sessao.FabApiRestRocketChatSessao;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreDataHora;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreJson;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.ItfRespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteApi;

import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.token.ItfTokenDeAcessoExterno;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.token.TokenDeAcessoExternoDinamico;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.UtilSBApiRestClient;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.gestaoToken.GestaoTokenDinamico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import jakarta.json.JsonObject;
import java.util.Date;
import java.util.HashMap;
import org.json.simple.JSONObject;

public class GestaoTokenRestRocketChat extends GestaoTokenDinamico {

    private String loginNomeUsuario;
    private String loginSenhaUsuario;
    private String codigoUsuarioRocketChat;

    public GestaoTokenRestRocketChat(
            final FabTipoAgenteClienteApi pTipoAgente,
            final ItfUsuario pUsuario) {
        super(FabApiRestRocketChatV1Channel.class, pTipoAgente, pUsuario);
    }

    @Override
    public synchronized ItfTokenDeAcessoExterno gerarNovoToken() {
        String url = getConfig().getPropriedade(FabConfigRocketChat.URL_SERVIDOR_ROCKET_CHAT) + "/api/v1/login";

        String usuarioLogin = null;
        String senhaLogin = null;

        switch (getTipoAgente()) {
            case USUARIO:
                usuarioLogin = loginNomeUsuario;
                senhaLogin = loginSenhaUsuario;
                break;
            case SISTEMA:
                usuarioLogin = getConfig().getPropriedade(FabConfigRocketChat.USUARIO_ASSISTENTE_DE_CANAIS);
                senhaLogin = getConfig().getPropriedade(FabConfigRocketChat.SENHA_ASSISTENTE_DE_CANAIS);
                break;
            default:
                throw new AssertionError(getTipoAgente().name());

        }
        if (usuarioLogin != null) {
            RespostaWebServiceSimples resposta = UtilSBApiRestClient.getRespostaRest(url, FabTipoConexaoRest.POST, true,
                    new HashMap<>(), "user=" + usuarioLogin + "&password=" + senhaLogin);
            if (resposta.isSucesso()) {
                JsonObject jsonArquivado = resposta.getRespostaComoObjetoJson();
                jsonArquivado = UtilSBCoreJson.getJsonObjectIncrementandoCampo(jsonArquivado, "dataHora", new Date().getTime());
                armazenarRespostaToken(UtilSBCoreJson.getTextoByJsonObjeect(jsonArquivado));
            } else {
                SBCore.enviarAvisoAoUsuario("Usuário ou senha inválida, verifique suas credenciais em " + getConfig().getPropriedade(FabConfigRocketChat.URL_SERVIDOR_ROCKET_CHAT));
            }
        }
        return loadTokenArmazenado();

    }

    @Override
    public ItfTokenDeAcessoExterno extrairToken(JsonObject pJson) {
        try {
            codigoUsuarioRocketChat = ((JSONObject) pJson.get("data")).get("userId").toString();
            Date dataHoraExipira = UtilSBCoreDataHora.decrementaMinutos(new Date(), 5);

            if (pJson.containsKey("dataHora")) {

                Date dataHoraGeracaoToken = new Date((long) pJson.getJsonNumber("dataHora").longValue());
                dataHoraExipira = UtilSBCoreDataHora.incrementaHoras(dataHoraGeracaoToken, 6);
            }

            String chaveToken = ((JSONObject) pJson.get("data")).get("authToken").toString();
            TokenDeAcessoExternoDinamico token = new TokenDeAcessoExternoDinamico(chaveToken, dataHoraExipira);

            return token;
        } catch (Throwable t) {
            return null;
        }
    }

    @Override
    public synchronized boolean validarToken() {
        if (getToken() == null || getTokenCompleto() == null) {
            return false;
        }
        if (!getTokenCompleto().isTokenValido()) {
            return false;
        }
        switch (tipoAgente) {
            case USUARIO:
                ItfRespostaWebServiceSimples resp = FabApiRestRocketChatV1Channel.QUEM_SOU_EU.getAcao(usuario).getResposta();
                return resp.isSucesso();
            case SISTEMA:
                ItfRespostaWebServiceSimples resp2 = FabApiRestRocketChatV1Channel.QUEM_SOU_EU.getAcao().getResposta();
                return resp2.isSucesso();

            default:
                throw new AssertionError(tipoAgente.name());

        }
    }

    public String getLoginNomeUsuario() {
        return loginNomeUsuario;
    }

    public void setLoginNomeUsuario(String loginNomeUsuario) {
        this.loginNomeUsuario = loginNomeUsuario;
    }

    public String getLoginSenhaUsuario() {
        return loginSenhaUsuario;
    }

    public void setLoginSenhaUsuario(String LoginSenhaUsuario) {
        this.loginSenhaUsuario = LoginSenhaUsuario;
    }

    public static GestaoTokenRestRocketChat getInstancia(ItfUsuario pUsuario) {
        return (GestaoTokenRestRocketChat) FabApiRestRocketChatV1Channel.GRUPO_ADICIONAR_USUARIO.getGestaoToken(pUsuario);
    }

    public static GestaoTokenRestRocketChat getInstancia() {
        return (GestaoTokenRestRocketChat) FabApiRestRocketChatV1Channel.GRUPO_ADICIONAR_USUARIO.getGestaoToken();
    }

    public String getCodigoUsuarioRocketChat() {
        return codigoUsuarioRocketChat;
    }

    @Override
    public boolean isTemTokemAtivo() {
        return super.isTemTokemAtivo(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public synchronized boolean excluirToken() {
        if (!super.excluirToken()) {
            return false;
        }
        ItfRespostaWebServiceSimples respPesquisaEmail = FabApiRestRocketChatSessao.EFETUAR_LOUGOUT.getAcao().getResposta();
        return respPesquisaEmail.isSucesso();
    }

}
