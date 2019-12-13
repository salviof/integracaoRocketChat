package br.org.coletivoJava.integracoes.restRocketChat.implementacao;

import br.org.coletivoJava.integracoes.restRocketChat.api.InfoIntegracaoRestRocketChat;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.FabTipoConexaoRest;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.RespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.rocketChat.api.channel.FabApiRestRocketChatV1Channel;
import com.super_bits.modulosSB.SBCore.integracao.rocketChat.api.FabConfigRocketChat;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.gestaoToken.GestaoTokenChaveUnica;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteRest;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.UtilSBApiRestClient;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import java.util.HashMap;
import org.json.simple.JSONObject;

@InfoIntegracaoRestRocketChat(tipo = FabApiRestRocketChatV1Channel.GRUPO_LISTAR)
public class GestaoTokenRestRocketChat extends GestaoTokenChaveUnica {

    @Override
    public boolean isTemTokemAtivo() {
        return false;
    }

    @Override
    public String gerarNovoToken() {
        String url = getConfig().getPropriedade(FabConfigRocketChat.URL_SERVIDOR_ROCKET_CHAT) + "/api/v1/login";
        String usuarioSistema = null;
        String senhaSistema = null;
        switch (getTipoAgente()) {
            case USUARIO:
                break;
            case SISTEMA:
                usuarioSistema = getConfig().getPropriedade(FabConfigRocketChat.USUARIO_ASSISTENTE_DE_CANAIS);
                senhaSistema = getConfig().getPropriedade(FabConfigRocketChat.SENHA_ASSISTENTE_DE_CANAIS);
                break;
            default:
                throw new AssertionError(getTipoAgente().name());

        }
        RespostaWebServiceSimples resposta = UtilSBApiRestClient.getRespostaRest(url, FabTipoConexaoRest.POST, true,
                new HashMap<>(), "user=" + usuarioSistema + "&password=" + senhaSistema);
        String token = ((JSONObject) resposta.getRespostaComoObjetoJson().get("data")).get("authToken").toString();
        return token;

    }

    public GestaoTokenRestRocketChat(
            final FabTipoAgenteClienteRest pTipoAgente,
            final ItfUsuario pUsuario) {
        super(FabApiRestRocketChatV1Channel.class, pTipoAgente, pUsuario);
    }

    @Override
    public boolean validarToken() {
        return isTemTokemAtivo();
    }

    @Override
    public boolean armazenarToken() {
        throw new UnsupportedOperationException("O METODO AINDA N\u00c3O FOI IMPLEMENTADO.");
    }

    @Override
    public boolean loadTokenArmazenado() {
        throw new UnsupportedOperationException("O METODO AINDA N\u00c3O FOI IMPLEMENTADO.");
    }

}
