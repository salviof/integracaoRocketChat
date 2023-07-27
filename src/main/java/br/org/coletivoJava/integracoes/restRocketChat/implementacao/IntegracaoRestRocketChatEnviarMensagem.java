package br.org.coletivoJava.integracoes.restRocketChat.implementacao;

import br.org.coletivoJava.integracoes.restRocketChat.api.InfoIntegracaoRestRocketChatChannel;
import br.org.coletivoJava.integracoes.restRocketChat.api.channel.FabApiRestRocketChatV1Channel;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreJson;
import com.super_bits.modulosSB.SBCore.UtilGeral.json.ErroProcessandoJson;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.ItfRespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.RespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.AcaoApiIntegracaoAbstrato;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteApi;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.token.ItfTokenGestao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import jakarta.json.JsonObjectBuilder;

@InfoIntegracaoRestRocketChatChannel(tipo = FabApiRestRocketChatV1Channel.ENVIAR_MENSAGEM)
public class IntegracaoRestRocketChatEnviarMensagem
        extends
        AcaoApiIntegracaoAbstrato {

    public IntegracaoRestRocketChatEnviarMensagem(
            final FabTipoAgenteClienteApi pTipoAgente,
            final ItfUsuario pUsuario, final java.lang.Object... pParametro) {
        super(FabApiRestRocketChatV1Channel.ENVIAR_MENSAGEM, pTipoAgente,
                pUsuario, pParametro);
    }

    @Override
    public GestaoTokenRestRocketChat getTokenGestao() {
        return (GestaoTokenRestRocketChat) super.getTokenGestao(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public String gerarCorpoRequisicao() {

        String pCanal_sala = (String) parametros.get(0);

        String pMensagem = (String) parametros.get(1);

        boolean envioNomeCanal;

        String propriedadetipoCanal;
        if (pCanal_sala.startsWith("#")) {
            envioNomeCanal = true;

        } else {
            envioNomeCanal = false;

        }
        String pApelido = null;
        if (parametros.size() == 3) {
            pApelido = (String) parametros.get(2);
        } else {
            pApelido = getTokenGestao().getLoginNomeUsuario();
        }
        if (envioNomeCanal) {
            propriedadetipoCanal = "channel";
        } else {
            propriedadetipoCanal = "roomId";
        }

        try {
            JsonObjectBuilder jsonCorpoMensagem = UtilSBCoreJson.getJsonBuilderBySequenciaChaveValor(propriedadetipoCanal, pCanal_sala, "alias", pApelido, "text", pMensagem);

            String texto = UtilSBCoreJson.getTextoByJsonObjeect(jsonCorpoMensagem.build());
            return texto;
        } catch (ErroProcessandoJson ex) {
            throw new UnsupportedOperationException("Falha definindo parametros de requisição" + ex.getMessage());
        }

    }

    @Override
    public ItfRespostaWebServiceSimples getResposta() {
        return (ItfRespostaWebServiceSimples) super.getResposta(); //To change body of generated methods, choose Tools | Templates.
    }

}
