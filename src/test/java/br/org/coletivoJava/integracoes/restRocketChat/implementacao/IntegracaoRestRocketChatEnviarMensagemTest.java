/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.coletivoJava.integracoes.restRocketChat.implementacao;

import br.org.coletivoJava.integracoes.restRocketChat.api.channel.FabApiRestRocketChatV1Channel;
import br.org.coletivoJava.integracoes.restRocketChat.api.direct.FabApiRestRocketChatV1Direct;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.ItfRespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.RespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.rocketChat.implementacaoRCRest.ConfigCoreRCTestesRegraNegocio;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sfurbino
 */
public class IntegracaoRestRocketChatEnviarMensagemTest {

    public IntegracaoRestRocketChatEnviarMensagemTest() {
    }

    @Before
    public void setUp() {
        SBCore.configurar(new ConfigCoreRCTestesRegraNegocio(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        GestaoTokenRestRocketChat autenticacaoSistema = (GestaoTokenRestRocketChat) FabApiRestRocketChatV1Channel.QUEM_SOU_EU.getGestaoToken();
        autenticacaoSistema.gerarNovoToken();
        ItfRespostaWebServiceSimples resposta = FabApiRestRocketChatV1Channel.QUEM_SOU_EU.getAcao().getResposta();
        assertTrue("Quem eu sou não funcionou", resposta.isSucesso());
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testSomeMethod() {

        ItfRespostaWebServiceSimples resposta = FabApiRestRocketChatV1Direct.OBTER_CODIGO_SALA_DIRECT_DO_USUARIO.getAcao("salvio").getResposta();
        assertTrue("Codigo da sala direct não funcionou", resposta.isSucesso());

        System.out.println(resposta.getRespostaTexto());

        String valor = resposta.getRetorno().toString();
        FabApiRestRocketChatV1Channel.ENVIAR_MENSAGEM.getAcao(valor, "teste");
    }

}
