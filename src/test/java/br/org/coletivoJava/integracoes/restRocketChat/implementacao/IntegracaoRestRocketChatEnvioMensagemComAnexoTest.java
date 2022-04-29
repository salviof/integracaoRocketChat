/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package br.org.coletivoJava.integracoes.restRocketChat.implementacao;

import br.org.coletivoJava.integracoes.restRocketChat.api.mensagens.FabApiRestRocketChatMensagens;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.ItfRespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.rocketChat.implementacaoRCRest.ConfigCoreRCTestesRegraNegocio;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author salvio
 */
public class IntegracaoRestRocketChatEnvioMensagemComAnexoTest {

    public IntegracaoRestRocketChatEnvioMensagemComAnexoTest() {
    }

    /**
     * Test of gerarCorpoRequisicao method, of class
     * IntegracaoRestRocketChatEnvioMensagemComAnexo.
     */
    @Test
    public void testGerarCorpoRequisicao() {
        SBCore.configurar(new ConfigCoreRCTestesRegraNegocio(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        ItfRespostaWebServiceSimples resposta = FabApiRestRocketChatMensagens.ENVIO_MENSAGEM_COM_ANEXO
                .getAcao("#NOVIDADES", "Salvio Furbino", "Mensagem teste", "Titulo Mensagem", "https://imagens.casanovadigital.com.br/2018/12/consultoria-de-marketing-casa-nova-digital.fw_.png").getResposta();
        System.out.println(resposta.getRespostaTexto());
        assertTrue("Falha enviando mensagem", resposta.isSucesso());
    }

}
