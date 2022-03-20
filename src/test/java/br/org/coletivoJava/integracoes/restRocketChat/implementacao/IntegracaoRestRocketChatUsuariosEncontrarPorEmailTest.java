/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.coletivoJava.integracoes.restRocketChat.implementacao;

import br.org.coletivoJava.integracoes.restRocketChat.api.users.FabApiRestRokcetChatV1Users;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.ItfRespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.RespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.rocketChat.implementacaoRCRest.ConfigCoreRCTestesRegraNegocio;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico.UsuarioSistemaRoot;
import org.junit.Test;

/**
 *
 * @author sfurbino
 */
public class IntegracaoRestRocketChatUsuariosEncontrarPorEmailTest {

    public IntegracaoRestRocketChatUsuariosEncontrarPorEmailTest() {
    }

    /**
     * Test of gerarUrlRequisicao method, of class
     * IntegracaoRestRocketChatUsuariosEncontrarPorEmail.
     */
    @Test
    public void testGerarUrlRequisicao() {
        SBCore.configurar(new ConfigCoreRCTestesRegraNegocio(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        GestaoTokenRestRocketChat autenticacaoSistema = (GestaoTokenRestRocketChat) FabApiRestRokcetChatV1Users.USUARIOS_ENCONTRAR_POR_EMAIL.getGestaoToken();
        autenticacaoSistema.gerarNovoToken();
        IntegracaoRestRocketChatUsuariosEncontrarPorEmail pesquisaEmail = (IntegracaoRestRocketChatUsuariosEncontrarPorEmail) FabApiRestRokcetChatV1Users.USUARIOS_ENCONTRAR_POR_EMAIL.getAcao("camila@csanovadigital.com.br");
        ItfRespostaWebServiceSimples respPesquisaEmail = pesquisaEmail.getResposta();
        System.out.println(respPesquisaEmail.isSucesso());
        System.out.println(respPesquisaEmail.getRespostaTexto());
        if (respPesquisaEmail.isSucesso()) {
            System.out.println(respPesquisaEmail.getRespostaTexto());
        }

    }

}
