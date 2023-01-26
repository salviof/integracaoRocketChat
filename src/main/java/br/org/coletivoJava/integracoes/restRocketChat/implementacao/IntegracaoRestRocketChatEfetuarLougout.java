package br.org.coletivoJava.integracoes.restRocketChat.implementacao;

import br.org.coletivoJava.integracoes.restRocketChat.api.InfoIntegracaoRestRocketChatSessao;
import br.org.coletivoJava.integracoes.restRocketChat.api.sessao.FabApiRestRocketChatSessao;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.AcaoApiIntegracaoAbstrato;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteApi;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;

@InfoIntegracaoRestRocketChatSessao(tipo = FabApiRestRocketChatSessao.EFETUAR_LOUGOUT)
public class IntegracaoRestRocketChatEfetuarLougout
        extends
        AcaoApiIntegracaoAbstrato {

    public IntegracaoRestRocketChatEfetuarLougout(
            final FabTipoAgenteClienteApi pTipoAgente,
            final ItfUsuario pUsuario, final java.lang.Object... pParametro) {
        super(FabApiRestRocketChatSessao.EFETUAR_LOUGOUT, pTipoAgente,
                pUsuario, pParametro);
    }

    @Override
    public String gerarCorpoRequisicao() {
        return "";
    }

}
