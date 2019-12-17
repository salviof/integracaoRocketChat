package br.org.coletivoJava.integracoes.restRocketChat.implementacao;

import br.org.coletivoJava.integracoes.restRocketChat.api.InfoIntegracaoRestRocketChat;
import br.org.coletivoJava.integracoes.restRocketChat.api.channel.FabApiRestRocketChatV1Channel;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.AcaoApiIntegracaoAbstrato;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteRest;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;

@InfoIntegracaoRestRocketChat(tipo = FabApiRestRocketChatV1Channel.GRUPO_NOVO)
public class IntegracaoRestRocketChatGrupoNovo
        extends
        AcaoApiIntegracaoAbstrato {

    public IntegracaoRestRocketChatGrupoNovo(
            final FabTipoAgenteClienteRest pTipoAgente,
            final ItfUsuario pUsuario, final java.lang.Object... pParametro) {
        super(FabApiRestRocketChatV1Channel.GRUPO_NOVO, pTipoAgente, pUsuario,
                pParametro);
    }

    @Override
    public String gerarCorpoRequisicao() {

        if (getQuantidadeParametrosEnviados() != 1) {
            throw new UnsupportedOperationException("Esperado um parametro para criação do grupo (Parametro nome)");
        }
        return "{ \"name\": \"" + parametros[0] + "\" }";
    }

}
