package br.org.coletivoJava.integracoes.restRocketChat.implementacao;

import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.FabTipoConexaoRest;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.RespostaWebServiceSimples;
import br.org.coletivoJava.integracoes.restRocketChat.api.channel.FabApiRestRocketChatV1Channel;
import br.org.coletivoJava.integracoes.restRocketChat.api.FabConfigRocketChat;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.gestaoToken.GestaoTokenChaveUnica;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteRest;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.UtilSBApiRestClient;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import java.util.HashMap;
import org.json.simple.JSONObject;

public class GestaoTokenRestRocketChat extends GestaoTokenChaveUnica {

    private String loginNomeUsuario;
    private String loginSenhaUsuario;
    private String codigoUsuarioRocketChat;

    @Override
    public String gerarNovoToken() {
        String url = getConfig().getPropriedade(FabConfigRocketChat.URL_SERVIDOR_ROCKET_CHAT) + "/api/v1/login";
        String usuarioSistema = null;
        String senhaSistema = null;

        JSONObject ultimoRetornoToken = loadTokenArmazenadoComoJsonObject();
        if (ultimoRetornoToken != null) {
            if (!validarToken()) {
                ultimoRetornoToken = null;
            }
        }

        if (ultimoRetornoToken == null) {
            switch (getTipoAgente()) {
                case USUARIO:
                    usuarioSistema = loginNomeUsuario;
                    senhaSistema = loginSenhaUsuario;
                    break;
                case SISTEMA:
                    usuarioSistema = getConfig().getPropriedade(FabConfigRocketChat.USUARIO_ASSISTENTE_DE_CANAIS);
                    senhaSistema = getConfig().getPropriedade(FabConfigRocketChat.SENHA_ASSISTENTE_DE_CANAIS);
                    break;
                default:
                    throw new AssertionError(getTipoAgente().name());

            }
            if (usuarioSistema != null) {
                RespostaWebServiceSimples resposta = UtilSBApiRestClient.getRespostaRest(url, FabTipoConexaoRest.POST, true,
                        new HashMap<>(), "user=" + usuarioSistema + "&password=" + senhaSistema);
                ultimoRetornoToken = resposta.getRespostaComoObjetoJson();

                armazenarRespostaToken(resposta.getResposta());
            }
        }

        return extrairToken(ultimoRetornoToken);

    }

    @Override
    public String extrairToken(JSONObject pJson) {
        try {
            codigoUsuarioRocketChat = ((JSONObject) pJson.get("data")).get("userId").toString();
            return ((JSONObject) pJson.get("data")).get("authToken").toString();
        } catch (Throwable t) {
            return null;
        }
    }

    public GestaoTokenRestRocketChat(
            final FabTipoAgenteClienteRest pTipoAgente,
            final ItfUsuario pUsuario) {
        super(FabApiRestRocketChatV1Channel.class, pTipoAgente, pUsuario);
    }

    @Override
    public boolean validarToken() {
        switch (tipoAgente) {
            case USUARIO:
                RespostaWebServiceSimples resp = FabApiRestRocketChatV1Channel.QUEM_SOU_EU.getAcao(usuario).getResposta();
                if (!resp.isSucesso()) {
                    return false;
                }
                break;
            case SISTEMA:
                RespostaWebServiceSimples resp2 = FabApiRestRocketChatV1Channel.QUEM_SOU_EU.getAcao().getResposta();
                if (!resp2.isSucesso()) {
                    return false;
                }
                break;
            default:
                throw new AssertionError(tipoAgente.name());

        }
        return isTemTokemAtivo();
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

}
