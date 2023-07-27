package br.org.coletivoJava.integracoes.restRocketChat.implementacao;

import br.org.coletivoJava.integracoes.restRocketChat.api.InfoIntegracaoRestRocketChatMensagens;
import br.org.coletivoJava.integracoes.restRocketChat.api.mensagens.FabApiRestRocketChatMensagens;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreJson;
import com.super_bits.modulosSB.SBCore.UtilGeral.json.ErroProcessandoJson;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.AcaoApiIntegracaoAbstrato;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteApi;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

@InfoIntegracaoRestRocketChatMensagens(tipo = FabApiRestRocketChatMensagens.ENVIO_MENSAGEM_COM_ANEXO)
public class IntegracaoRestRocketChatEnvioMensagemComAnexo
        extends
        AcaoApiIntegracaoAbstrato {

    public IntegracaoRestRocketChatEnvioMensagemComAnexo(
            final FabTipoAgenteClienteApi pTipoAgente,
            final ItfUsuario pUsuario, final java.lang.Object... pParametro) {
        super(FabApiRestRocketChatMensagens.ENVIO_MENSAGEM_COM_ANEXO,
                pTipoAgente, pUsuario, pParametro);
    }

    @Override
    public String gerarCorpoRequisicao() {
        boolean temAnexo = parametros.size() == 5;
        String pCanal_sala = (String) parametros.get(0);
        String pApelido = (String) parametros.get(1);
        String pMensagem = (String) parametros.get(2);

        boolean envioNomeCanal;
        boolean enviouCodigoChannelChat;

        String propriedadetipoCanal;
        if (pCanal_sala.startsWith("#")) {
            envioNomeCanal = true;
            enviouCodigoChannelChat = false;
        } else {
            envioNomeCanal = false;
            enviouCodigoChannelChat = true;
        }

        if (envioNomeCanal) {
            propriedadetipoCanal = "channel";
        } else {
            propriedadetipoCanal = "roomId";
        }

        try {
            JsonObjectBuilder jsonCorpoMensagem = UtilSBCoreJson.getJsonBuilderBySequenciaChaveValor(propriedadetipoCanal, pCanal_sala, "alias", pApelido, "text", pMensagem);

            if (temAnexo) {
                String pTituloImage = (String) parametros.get(3);
                String pUrlImagem = (String) parametros.get(4);
                JsonArrayBuilder builderArrayAnexxos = Json.createArrayBuilder();
                JsonObject jsonAnexos = UtilSBCoreJson
                        .getJsonObjectBySequenciaChaveValor("title", pTituloImage, "image_url", pUrlImagem);
                builderArrayAnexxos.add(jsonAnexos);
                jsonCorpoMensagem.add("attachments", builderArrayAnexxos);
            }
            String texto = UtilSBCoreJson.getTextoByJsonObjeect(jsonCorpoMensagem.build());
            return texto;
        } catch (ErroProcessandoJson ex) {
            throw new UnsupportedOperationException("Falha definindo parametros de requisição" + ex.getMessage());
        }

    }
}
