package br.org.coletivoJava.integracoes.restRocketChat.implementacao;

import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.FabTipoConexaoRest;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.RespostaWebServiceSimples;
import br.org.coletivoJava.integracoes.restRocketChat.api.channel.FabApiRestRocketChatV1Channel;
import br.org.coletivoJava.integracoes.restRocketChat.api.FabConfigRocketChat;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteApi;

import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.token.ItfTokenDeAcessoExterno;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.token.TokenDeAcessoExternoSimples;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.UtilSBApiRestClient;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.gestaoToken.GestaoTokenDinamico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
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
    public ItfTokenDeAcessoExterno gerarNovoToken() {
        String url = getConfig().getPropriedade(FabConfigRocketChat.URL_SERVIDOR_ROCKET_CHAT) + "/api/v1/login";

        if (validarToken()) {

            return loadTokenArmazenado();
        }
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

            armazenarRespostaToken(resposta.getResposta());
        }
        return loadTokenArmazenado();

    }

    @Override
    public ItfTokenDeAcessoExterno extrairToken(JSONObject pJson) {
        try {
            codigoUsuarioRocketChat = ((JSONObject) pJson.get("data")).get("userId").toString();
            String token = ((JSONObject) pJson.get("data")).get("authToken").toString();
            return new TokenDeAcessoExternoSimples(token);
        } catch (Throwable t) {
            return null;
        }
    }

    @Override
    public boolean validarToken() {
        switch (tipoAgente) {
            case USUARIO:
                RespostaWebServiceSimples resp = FabApiRestRocketChatV1Channel.QUEM_SOU_EU.getAcao(usuario).getResposta();
                return resp.isSucesso();
            case SISTEMA:
                RespostaWebServiceSimples resp2 = FabApiRestRocketChatV1Channel.QUEM_SOU_EU.getAcao().getResposta();
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

}
