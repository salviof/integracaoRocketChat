/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.SBCore.integracao.rocketChat.api.channel;

import br.org.coletivoJava.integracoes.restRocketChat.api.channel.FabApiRestRocketChatV1Channel;
import br.org.coletivoJava.integracoes.restRocketChat.implementacao.GestaoTokenRestRocketChat;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.ItfRespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.token.ItfTokenGestao;
import com.super_bits.modulosSB.SBCore.integracao.rocketChat.implementacaoRCRest.ConfigCoreRCTestesRegraNegocio;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico.UsuarioAplicacaoEmExecucao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.MapaObjetosProjetoAtual;
import org.junit.Assert;
import org.junit.Test;
import testes.testesSupers.TestesApiRest;

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

        GestaoTokenRestRocketChat autenticacaoSistema = (GestaoTokenRestRocketChat) FabApiRestRocketChatV1Channel.QUEM_SOU_EU.getGestaoToken();
        autenticacaoSistema.gerarNovoToken();
        gerarCodigosChamadasEndpoint(FabApiRestRocketChatV1Channel.class);
        ItfRespostaWebServiceSimples resp2 = FabApiRestRocketChatV1Channel.QUEM_SOU_EU.getAcao().getResposta();
        GestaoTokenRestRocketChat autenticacaouser = (GestaoTokenRestRocketChat) FabApiRestRocketChatV1Channel.QUEM_SOU_EU.getGestaoToken(SBCore.getUsuarioLogado());
        autenticacaouser.setLoginNomeUsuario("salviof@gmail.com");
        autenticacaouser.setLoginSenhaUsuario("123321");
        autenticacaouser.gerarNovoToken();

        ItfRespostaWebServiceSimples resp3 = FabApiRestRocketChatV1Channel.QUEM_SOU_EU.getAcao(SBCore.getUsuarioLogado()).getResposta();

        ItfRespostaWebServiceSimples respExiteGrupo = FabApiRestRocketChatV1Channel.GRUPO_EXISTE_GRUPO.getAcao("canaltesteapi3").getResposta();
        if (respExiteGrupo.isSucesso()) {
            System.out.println("Sucesso! o grupo foi encontrado");
        } else {
            ItfRespostaWebServiceSimples respcriacaoDoGrupo = FabApiRestRocketChatV1Channel.GRUPO_NOVO.getAcao("canaltesteapi2").getResposta();
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

        ItfRespostaWebServiceSimples resposta = FabApiRestRocketChatV1Channel.GRUPO_LISTAR.getAcao().getResposta();
        MapaObjetosProjetoAtual.adcionarObjeto(UsuarioAplicacaoEmExecucao.class);
        System.out.println(resposta.getRetorno());
        UsuarioAplicacaoEmExecucao usuario = new UsuarioAplicacaoEmExecucao();
        UsuarioSB novoUsuairo = new UsuarioSB();
        novoUsuairo.setNome("Salvio");
        novoUsuairo.setSenha("123321");

        System.out.println(usuario.getEmail());

        ItfRespostaWebServiceSimples resp = FabApiRestRocketChatV1Channel.GRUPO_LISTAR.getAcao(novoUsuairo).getResposta();

        Assert.assertTrue("A resposta não foi retornada com sucesso", resposta.isSucesso());

        System.out.println(resposta.getRespostaTexto());
    }

}
