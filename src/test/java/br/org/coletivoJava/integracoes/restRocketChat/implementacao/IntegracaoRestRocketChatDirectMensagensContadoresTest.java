/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.coletivoJava.integracoes.restRocketChat.implementacao;

import br.org.coletivoJava.integracoes.restRocketChat.api.channel.FabApiRestRocketChatV1Channel;
import br.org.coletivoJava.integracoes.restRocketChat.api.users.FabApiRestRokcetChatV1Users;
import com.jayway.restassured.path.json.JsonPath;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.RespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.rocketChat.implementacaoRCRest.ConfigCoreRCTestesRegraNegocio;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author sfurbino
 */
public class IntegracaoRestRocketChatDirectMensagensContadoresTest {

    public IntegracaoRestRocketChatDirectMensagensContadoresTest() {
    }

    @Before
    public void setUp() {
        SBCore.configurar(new ConfigCoreRCTestesRegraNegocio(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
    }

    /**
     * Test of gerarResposta method, of class
     * IntegracaoRestRocketChatDirectMensagensContadores.
     */
    @Test
    public void testGerarResposta() {

        GestaoTokenRestRocketChat autenticacaouser = (GestaoTokenRestRocketChat) FabApiRestRokcetChatV1Users.DIRECT_MENSAGENS_CONTADORES.getGestaoToken(SBCore.getUsuarioLogado());
        //autenticacaouser.setLoginNomeUsuario("salviof@gmail.com");
        //autenticacaouser.setLoginSenhaUsuario("123321");
        //autenticacaouser.gerarNovoToken();

        RespostaWebServiceSimples resposta1 = FabApiRestRokcetChatV1Users.USUARIOS_LISTAR.getAcao(SBCore.getUsuarioLogado()).getResposta();
        System.out.println(resposta1.isSucesso());

        IntegracaoRestRocketChatAssinaturasListar resposta = (IntegracaoRestRocketChatAssinaturasListar) FabApiRestRokcetChatV1Users.ASSINATURAS_LISTAR.getAcao(SBCore.getUsuarioLogado(), "salvio");

        System.out.println(resposta.getNaoLidas());

    }

}
