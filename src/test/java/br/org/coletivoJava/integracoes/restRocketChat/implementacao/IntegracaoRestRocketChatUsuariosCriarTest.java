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
public class IntegracaoRestRocketChatUsuariosCriarTest {

    public IntegracaoRestRocketChatUsuariosCriarTest() {
    }

    /**
     * Test of gerarCorpoRequisicao method, of class
     * IntegracaoRestRocketChatUsuariosCriar.
     */
    @Test
    public void testGerarTesteCriacaoDeUSuario() {
        SBCore.configurar(new ConfigCoreRCTestesRegraNegocio(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        GestaoTokenRestRocketChat autenticacaoSistema = (GestaoTokenRestRocketChat) FabApiRestRokcetChatV1Users.USUARIOS_CRIAR.getGestaoToken();
        autenticacaoSistema.gerarNovoToken();
        IntegracaoRestRocketChatUsuariosCriar pesquisaEmail = (IntegracaoRestRocketChatUsuariosCriar) FabApiRestRokcetChatV1Users.USUARIOS_CRIAR
                .getAcao("teste casanova", "teste@casanovadigital.com.br", "123", "teste_casanovadigital_com_br");
        ItfRespostaWebServiceSimples respPesquisaEmail = pesquisaEmail.getResposta();
        System.out.println(respPesquisaEmail.isSucesso());
        System.out.println(respPesquisaEmail.getRespostaTexto());
        if (respPesquisaEmail.isSucesso()) {
            System.out.println(respPesquisaEmail.getRespostaTexto());
        }

    }

}
