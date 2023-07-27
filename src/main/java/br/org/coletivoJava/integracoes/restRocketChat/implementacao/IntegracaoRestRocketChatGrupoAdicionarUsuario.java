package br.org.coletivoJava.integracoes.restRocketChat.implementacao;

import br.org.coletivoJava.integracoes.restRocketChat.api.InfoIntegracaoRestRocketChatChannel;
import br.org.coletivoJava.integracoes.restRocketChat.api.channel.FabApiRestRocketChatV1Channel;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.RespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.AcaoApiIntegracaoAbstrato;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteApi;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import java.util.Map;

@InfoIntegracaoRestRocketChatChannel(tipo = FabApiRestRocketChatV1Channel.GRUPO_ADICIONAR_USUARIO)
public class IntegracaoRestRocketChatGrupoAdicionarUsuario
        extends
        AcaoApiIntegracaoAbstrato {

    public IntegracaoRestRocketChatGrupoAdicionarUsuario(
            final FabTipoAgenteClienteApi pTipoAgente,
            final ItfUsuario pUsuario, final java.lang.Object... pParametro) {
        super(FabApiRestRocketChatV1Channel.GRUPO_ADICIONAR_USUARIO,
                pTipoAgente, pUsuario, pParametro);
    }

    @Override
    public String gerarCorpoRequisicao() {
        return "{ \"roomId\": \"" + parametros.get(0) + "\", \"userId\": \"" + parametros.get(1) + "\" }";
    }

    @Override
    public Map<String, String> gerarCabecalho() {
        Map<String, String> cabecalho = super.gerarCabecalho();
        cabecalho.put("Content-type", "application/json");
        return cabecalho;

    }

    @Override
    protected RespostaWebServiceSimples gerarRespostaTratamentoFino(RespostaWebServiceSimples pRespostaWSSemTratamento) {
        return UtilIntRocketChat.gerarRespostaTratamentoFino(pRespostaWSSemTratamento);
    }

}
