package br.org.coletivoJava.integracoes.restRocketChat.implementacao;

import br.org.coletivoJava.integracoes.restRocketChat.api.InfoIntegracaoRestRocketChat;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteRest;

import com.super_bits.modulosSB.SBCore.integracao.rocketChat.api.channel.FabApiRestRocketChatV1Channel;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.AcaoApiIntegracaoAbstrato;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.gestaoToken.MapaTokensGerenciados;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.gestaoToken.GestaoTokenOath2;
import com.super_bits.modulosSB.SBCore.integracao.rocketChat.api.FabConfigRocketChat;

@InfoIntegracaoRestRocketChat(tipo = FabApiRestRocketChatV1Channel.GRUPO_LISTAR)
public class IntegracaoRestRocketChatGrupoListar
        extends
        AcaoApiIntegracaoAbstrato {

    public IntegracaoRestRocketChatGrupoListar(
            final java.lang.Object... pParametro) {
        super(FabApiRestRocketChatV1Channel.GRUPO_LISTAR, pParametro);
    }

    public void configuraOauth() {

        if (MapaTokensGerenciados.getAutenticadorSistemaAtual(fabricaIntegracao) == null) {
            MapaTokensGerenciados.registrarAutenticador(new GestaoTokenOath2(FabConfigRocketChat.class, FabTipoAgenteClienteRest.SISTEMA, fabricaIntegracao.getClass()), fabricaIntegracao);
        }

    }

    @Override
    public String gerarTokenAcesso() {

        return getConfiguracao().getPropriedade(FabConfigRocketChat.SENHA_ASSISTENTE_DE_CANAIS);
    }

}
