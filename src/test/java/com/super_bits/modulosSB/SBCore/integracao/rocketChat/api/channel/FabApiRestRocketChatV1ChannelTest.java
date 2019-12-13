/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.SBCore.integracao.rocketChat.api.channel;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.RespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.token.ItfTokenGestao;
import com.super_bits.modulosSB.SBCore.integracao.rocketChat.implementacaoRCRest.ConfigCoreRCTestesRegraNegocio;
import com.super_bits.modulosSB.SBCore.integracao.testes.geradorCodigo.GeradorApiIntegracaoRest;
import com.super_bits.modulosSB.SBCore.integracao.testes.geradorCodigo.GeradorGestaoTokenAcessoIntegracaoRest;
import com.super_bits.modulosSB.SBCore.integracao.testes.geradorCodigo.GeradorImplementacaoIntegracaoRest;
import com.super_bits.modulosSB.SBCore.integracao.testes.geradorCodigo.GeradorImplementacaoIntegracaoRestHeaderPadrao;
import org.junit.Assert;
import org.junit.Test;

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

        GeradorImplementacaoIntegracaoRestHeaderPadrao geradorheader = new GeradorImplementacaoIntegracaoRestHeaderPadrao(FabApiRestRocketChatV1Channel.GRUPO_LISTAR);
        geradorheader.salvarEmDiretorioPadraCASO_NAO_EXISTA();

        ItfTokenGestao token = FabApiRestRocketChatV1Channel.GRUPO_LISTAR.getGestaoToken();
        if (!token.isTemTokemAtivo()) {
            token.getToken();
        }
        System.out.println(token.getToken());
        if (!token.isTemTokemAtivo()) {
            token.getToken();
        }
        token = FabApiRestRocketChatV1Channel.GRUPO_LISTAR.getGestaoToken();
        if (!token.isTemTokemAtivo()) {
            token.getToken();
        }

        RespostaWebServiceSimples resposta = FabApiRestRocketChatV1Channel.GRUPO_LISTAR.getAcao().getResposta();

        Assert.assertTrue("A resposta não foi retornada com sucesso", resposta.isSucesso());
        System.out.println(resposta.getRespostaTexto());
    }

}
