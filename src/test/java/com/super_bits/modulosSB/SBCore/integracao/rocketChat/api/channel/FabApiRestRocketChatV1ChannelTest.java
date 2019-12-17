/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.SBCore.integracao.rocketChat.api.channel;

import testes.testesSupers.TestesApiRest;
import br.org.coletivoJava.integracoes.restRocketChat.api.channel.FabApiRestRocketChatV1Channel;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.RespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.token.ItfTokenGestao;
import com.super_bits.modulosSB.SBCore.integracao.rocketChat.implementacaoRCRest.ConfigCoreRCTestesRegraNegocio;
import testes.geradorCodigo.GeradorApiIntegracaoRest;
import testes.geradorCodigo.GeradorGestaoTokenAcessoIntegracaoRest;
import testes.geradorCodigo.GeradorImplementacaoIntegracaoRest;
import testes.geradorCodigo.GeradorImplementacaoIntegracaoRestHeaderPadrao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico.UsuarioAplicacaoEmExecucao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.MapaObjetosProjetoAtual;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author sfurbino
 */
public class FabApiRestRocketChatV1ChannelTest extends TestesApiRest {

    public FabApiRestRocketChatV1ChannelTest() {
    }

    /**
     * Test of values method, of class FabApiRestRocketChatV1Channel.
     */
    @Test
    public void testValues() {

        SBCore.configurar(new ConfigCoreRCTestesRegraNegocio(), SBCore.ESTADO_APP.DESENVOLVIMENTO);

        gerarCodigos(FabApiRestRocketChatV1Channel.class);

        RespostaWebServiceSimples respExiteGrupo = FabApiRestRocketChatV1Channel.GRUPO_EXISTE_GRUPO.getAcao("canaltesteapi2").getResposta();
        if (respExiteGrupo.isSucesso()) {
            System.out.println("Sucesso! o grupo foi encontrado");
        } else {
            RespostaWebServiceSimples respcriacaoDoGrupo = FabApiRestRocketChatV1Channel.GRUPO_NOVO.getAcao("grupoTeste").getResposta();
            Assert.assertTrue("impossível criar o grupo", respcriacaoDoGrupo.isSucesso());
        }

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
        MapaObjetosProjetoAtual.adcionarObjeto(UsuarioAplicacaoEmExecucao.class);
        System.out.println(resposta.getRetorno());
        UsuarioAplicacaoEmExecucao usuario = new UsuarioAplicacaoEmExecucao();
        UsuarioSB novoUsuairo = new UsuarioSB();
        novoUsuairo.setNome("Salvio");
        novoUsuairo.setSenha("123321");

        System.out.println(usuario.getEmail());

        RespostaWebServiceSimples resp = FabApiRestRocketChatV1Channel.GRUPO_LISTAR.getAcao(novoUsuairo).getResposta();

        Assert.assertTrue("A resposta não foi retornada com sucesso", resposta.isSucesso());

        System.out.println(resposta.getRespostaTexto());
    }

}
