/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package br.org.coletivoJava.integracoes.restRocketChat.implementacao;

import br.org.coletivoJava.fw.erp.implementacao.chatService.ServicoChatRocketChat;
import br.org.coletivoJava.integracoes.restRocketChat.api.channel.FabApiRestRocketChatV1Channel;
import br.org.coletivoJava.integracoes.restRocketChat.api.sessao.FabApiRestRocketChatSessao;
import br.org.coletivoJava.integracoes.restRocketChat.api.users.FabApiRestRokcetChatV1Users;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreJson;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.ItfRespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.rocketChat.implementacaoRCRest.ConfigCoreRCTestesRegraNegocio;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import br.org.coletivoJava.fw.erp.implementacao.chatService.ItfChatSalaBeanRC;

/**
 *
 * @author salvio
 */
public class IntegracaoRestRocketChatGrupoAdicionarUsuarioTest {

    public IntegracaoRestRocketChatGrupoAdicionarUsuarioTest() {
    }

    @Before
    public void setUp() {
        SBCore.configurar(new ConfigCoreRCTestesRegraNegocio(), SBCore.ESTADO_APP.DESENVOLVIMENTO);

    }

    @Test
    public void testSomeMethod() {
        ItfRespostaWebServiceSimples resposta = FabApiRestRokcetChatV1Users.USUARIOS_ENCONTRAR_POR_EMAIL.getAcao("salviof@gmail.com").getResposta();
        if (resposta.isSucesso()) {
            String userID = UtilSBCoreJson.getValorApartirDoCaminho("users[0]._id", resposta.getRespostaComoObjetoJson());
            ItfRespostaWebServiceSimples respostaGrupo = FabApiRestRocketChatV1Channel.GRUPO_EXISTE_GRUPO.getAcao("Govenanca").getResposta();
            System.out.println(respostaGrupo.getRespostaTexto());
            String grupoId = UtilSBCoreJson.getValorApartirDoCaminho("group._id", respostaGrupo.getRespostaComoObjetoJson());
            System.out.println(userID);
            ItfRespostaWebServiceSimples respostaAddUserGrupo = FabApiRestRocketChatV1Channel.GRUPO_ADICIONAR_USUARIO.getAcao(grupoId, "wmaPJFFs4xPSCvkRF").getResposta();
            System.out.println(respostaAddUserGrupo.getRespostaTexto());
        } else {
            Assert.fail("Falhou localizando usuaário por email");
        }
        //

    }

}
