package br.org.coletivoJava.integracoes.restRocketChat;

import br.org.coletivoJava.integracoes.restRocketChat.api.mensagens.FabApiRestRocketChatMensagens;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.integracao.rocketChat.implementacaoRCRest.ConfigCoreRCTestesRegraNegocio;
import org.junit.Test;
import testes.testesSupers.TestesApiRest;

/**
 *
 * @author sfurbino
 */
public class TesteConformidade extends TestesApiRest {

    @Test
    public void testes() {
        SBCore.configurar(new ConfigCoreRCTestesRegraNegocio(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        System.out.println(FabApiRestRocketChatMensagens.class.getSimpleName());
        gerarCodigosChamadasEndpoint(FabApiRestRocketChatMensagens.class);
//        gerarCodigosChamadasEndpoint(FabApiRestRokcetChatV1Users.class);
        //    gerarCodigosChamadasEndpoint(FabApiRestRocketChatV1Direct.class);
        //   gerarCodigosChamadasEndpoint(FabApiRestRocketChatV1Channel.class);

    }
}
