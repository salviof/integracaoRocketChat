/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package br.org.coletivoJava.integracoes.restRocketChat.implementacao;

import br.org.coletivoJava.integracoes.restRocketChat.api.sessao.FabApiRestRocketChatSessao;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.ItfRespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.rocketChat.implementacaoRCRest.ConfigCoreRCTestesRegraNegocio;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author salvio
 */
public class IntegracaoRestRocketChatEfetuarLougoutTest {

    public IntegracaoRestRocketChatEfetuarLougoutTest() {
    }

    @Test
    public void testSomeMethod() {

        SBCore.configurar(new ConfigCoreRCTestesRegraNegocio(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        ItfRespostaWebServiceSimples respLogout = FabApiRestRocketChatSessao.EFETUAR_LOUGOUT.getAcao().getResposta();
        GestaoTokenRestRocketChat autenticacaoSistema = (GestaoTokenRestRocketChat) FabApiRestRocketChatSessao.QUEM_SOU_EU.getGestaoToken();
        Assert.assertFalse("Token não validado", autenticacaoSistema.validarToken());
        autenticacaoSistema.gerarNovoToken();

        Assert.assertTrue("Token não validado", autenticacaoSistema.validarToken());
        autenticacaoSistema.excluirToken();
        ItfRespostaWebServiceSimples respPesquisaEmail = FabApiRestRocketChatSessao.EFETUAR_LOUGOUT.getAcao().getResposta();
        Assert.assertFalse("Token não excluido apos logout", autenticacaoSistema.validarToken());

        System.out.println(respPesquisaEmail.isSucesso());
        System.out.println(respPesquisaEmail.getRespostaTexto());
        if (respPesquisaEmail.isSucesso()) {
            System.out.println(respPesquisaEmail.getRespostaTexto());
        }
    }

}
