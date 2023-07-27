package br.org.coletivoJava.integracoes.restRocketChat.implementacao;

import br.org.coletivoJava.integracoes.restRocketChat.api.InfoIntegracaoRestRocketChatDirect;
import br.org.coletivoJava.integracoes.restRocketChat.api.direct.FabApiRestRocketChatV1Direct;
import com.jayway.restassured.path.json.JsonPath;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.ConsumoWSExecucao;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.AcaoApiIntegracaoAbstrato;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteApi;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;

@InfoIntegracaoRestRocketChatDirect(tipo = FabApiRestRocketChatV1Direct.OBTER_CODIGO_SALA_DIRECT_DO_USUARIO)
public class IntegracaoRestRocketChatObterCodigoSalaDirectDoUsuario
        extends
        AcaoApiIntegracaoAbstrato {

    public IntegracaoRestRocketChatObterCodigoSalaDirectDoUsuario(
            final FabTipoAgenteClienteApi pTipoAgente,
            final ItfUsuario pUsuario, final java.lang.Object... pParametro) {
        super(FabApiRestRocketChatV1Direct.OBTER_CODIGO_SALA_DIRECT_DO_USUARIO,
                pTipoAgente, pUsuario, pParametro);
    }

    @Override
    public String gerarCorpoRequisicao() {
        return "{ \"username\": \"" + parametros.get(0) + "\" }";
    }

    @Override
    public void gerarResposta(ConsumoWSExecucao pConsumoRest) {
        super.gerarResposta(pConsumoRest);
        if (getResposta().isSucesso()) {
            getResposta().setRetorno(JsonPath.from(getResposta().getRespostaTexto()).get("room.rid"));
        }
    }

}
