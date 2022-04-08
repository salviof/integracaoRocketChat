package br.org.coletivoJava.integracoes.restRocketChat.implementacao;

import br.org.coletivoJava.integracoes.restRocketChat.api.InfoIntegracaoRestRocketChatChannel;
import br.org.coletivoJava.integracoes.restRocketChat.api.channel.FabApiRestRocketChatV1Channel;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.ConsumoWSExecucao;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.AcaoApiIntegracaoAbstrato;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteApi;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;

@InfoIntegracaoRestRocketChatChannel(tipo = FabApiRestRocketChatV1Channel.QUEM_SOU_EU)
public class IntegracaoRestRocketChatQuemSouEu
        extends
        AcaoApiIntegracaoAbstrato {

    public IntegracaoRestRocketChatQuemSouEu(
            final FabTipoAgenteClienteApi pTipoAgente,
            final ItfUsuario pUsuario, final java.lang.Object... pParametro) {
        super(FabApiRestRocketChatV1Channel.QUEM_SOU_EU, pTipoAgente, pUsuario,
                pParametro);
    }

    @Override
    protected void executarAcao() {
        super.executarAcao(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void gerarResposta(ConsumoWSExecucao pConsumoRest) {
        super.gerarResposta(pConsumoRest);
        if (!resposta.isSucesso()) {
            resposta.addErro("Falha obetendo resposta");

        } else {

            String idUser = getResposta().getRespostaComoObjetoJson().get("_id").toString();
            String nome = getResposta().getRespostaComoObjetoJson().get("name").toString();
            System.out.println(nome);
            if (idUser == null) {
                resposta.addErro("Falha validando token");

            }
        }
    }

}
