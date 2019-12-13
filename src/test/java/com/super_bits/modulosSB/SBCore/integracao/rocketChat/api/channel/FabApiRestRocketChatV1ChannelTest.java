/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.SBCore.integracao.rocketChat.api.channel;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.ItfRespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.rocketChat.implementacaoRCRest.ConfigCoreRCTestesRegraNegocio;
import com.super_bits.modulosSB.SBCore.integracao.testes.geradorCodigo.GeradorApiIntegracaoRest;
import com.super_bits.modulosSB.SBCore.integracao.testes.geradorCodigo.GeradorGestaoTokenAcessoIntegracaoRest;
import com.super_bits.modulosSB.SBCore.integracao.testes.geradorCodigo.GeradorImplementacaoIntegracaoRest;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sfurbino
 */
public class FabApiRestRocketChatV1ChannelTest {

    public FabApiRestRocketChatV1ChannelTest() {
    }

    /**
     * Test of values method, of class FabApiRestRocketChatV1Channel.
     */
    @Test
    public void testValues() {
        SBCore.configurar(new ConfigCoreRCTestesRegraNegocio(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        GeradorApiIntegracaoRest gerador = new GeradorApiIntegracaoRest(FabApiRestRocketChatV1Channel.GRUPO_LISTAR);
        gerador.salvarEmDiretorioPadraoSubstituindoAnterior();

        GeradorImplementacaoIntegracaoRest geradorImp = new GeradorImplementacaoIntegracaoRest(FabApiRestRocketChatV1Channel.GRUPO_LISTAR);
        geradorImp.salvarEmDiretorioPadraCASO_NAO_EXISTA();
        GeradorGestaoTokenAcessoIntegracaoRest geradorToken = new GeradorGestaoTokenAcessoIntegracaoRest(FabApiRestRocketChatV1Channel.GRUPO_LISTAR);
        geradorToken.salvarEmDiretorioPadraCASO_NAO_EXISTA();

        ItfRespostaWebServiceSimples resposta = FabApiRestRocketChatV1Channel.GRUPO_LISTAR.getAcao().getResposta();

        System.out.println(resposta.getRespostaTexto());
    }

}
