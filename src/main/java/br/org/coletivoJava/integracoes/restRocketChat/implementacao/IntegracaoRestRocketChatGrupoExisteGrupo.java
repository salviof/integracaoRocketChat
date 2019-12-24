package br.org.coletivoJava.integracoes.restRocketChat.implementacao;

import br.org.coletivoJava.integracoes.restRocketChat.api.InfoIntegracaoRestRocketChatChannel;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.ConsumoWSExecucao;
import br.org.coletivoJava.integracoes.restRocketChat.api.channel.FabApiRestRocketChatV1Channel;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.AcaoApiIntegracaoAbstrato;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteRest;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import java.util.Optional;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@InfoIntegracaoRestRocketChatChannel(tipo = FabApiRestRocketChatV1Channel.GRUPO_EXISTE_GRUPO)
public class IntegracaoRestRocketChatGrupoExisteGrupo
        extends
        AcaoApiIntegracaoAbstrato {

    public IntegracaoRestRocketChatGrupoExisteGrupo(
            final FabTipoAgenteClienteRest pTipoAgente,
            final ItfUsuario pUsuario, final java.lang.Object... pParametro) {
        super(FabApiRestRocketChatV1Channel.GRUPO_EXISTE_GRUPO, pTipoAgente,
                pUsuario, pParametro);
    }

    @Override
    public void gerarResposta(ConsumoWSExecucao pConsumoRest) {

        super.gerarResposta(pConsumoRest); //chamada super do metodo (implementação classe pai)
        if (parametros == null || parametros.length < 1) {
            resposta.addErro("Nenhum parametro foi enviado");
        }
        if (resposta.isSucesso()) {
            JSONObject resp = resposta.getJsonObj();
            JSONArray grupos = (JSONArray) resp.get("groups");
            Optional<String> grupoEncontrado = grupos.stream().filter(gp -> ((JSONObject) gp).get("fname").toString().equals(parametros[0])).findFirst();
            if (!grupoEncontrado.isPresent()) {
                resposta.addErro("O grupo não foi encontrado");
            }

        }
    }

}
