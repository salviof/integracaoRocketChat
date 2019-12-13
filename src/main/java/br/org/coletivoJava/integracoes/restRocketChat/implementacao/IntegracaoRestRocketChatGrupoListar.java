package br.org.coletivoJava.integracoes.restRocketChat.implementacao;

import br.org.coletivoJava.integracoes.restRocketChat.api.InfoIntegracaoRestRocketChat;
import com.super_bits.modulosSB.SBCore.integracao.rocketChat.api.channel.FabApiRestRocketChatV1Channel;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.AcaoApiIntegracaoAbstrato;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteRest;
import com.super_bits.modulosSB.SBCore.integracao.rocketChat.api.FabConfigRocketChat;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import java.util.Map;

@InfoIntegracaoRestRocketChat(tipo = FabApiRestRocketChatV1Channel.GRUPO_LISTAR)
public class IntegracaoRestRocketChatGrupoListar
        extends
        AcaoApiIntegracaoAbstrato {

    public IntegracaoRestRocketChatGrupoListar(
            final FabTipoAgenteClienteRest pTipoAgente,
            final ItfUsuario pUsuario, final java.lang.Object... pParametro) {
        super(FabApiRestRocketChatV1Channel.GRUPO_LISTAR, pTipoAgente,
                pUsuario, pParametro);
    }

    @Override
    public Map<String, String> gerarCabecalho() {
        Map<String, String> cabecalho = super.gerarCabecalho();

        return cabecalho;
    }

    @Override
    public String getUrlServidor() {

        return getConfiguracao().getPropriedade(FabConfigRocketChat.URL_SERVIDOR_ROCKET_CHAT);
    }

    @Override
    public String gerarUrlRequisicao() {
        return super.gerarUrlRequisicao(); //chamada super do metodo (implementação classe pai)
    }

}
