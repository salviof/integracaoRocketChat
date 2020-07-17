/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.coletivoJava.integracoes.restRocketChat.api.users;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.RespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.rocketChat.implementacaoRCRest.ConfigCoreRCTestesRegraNegocio;
import org.junit.Test;
import testes.testesSupers.TestesApiRest;

/**
 *
 * @author sfurbino
 */
public class FabApiRestRokcetChatV1UsersTest extends TestesApiRest {

    public FabApiRestRokcetChatV1UsersTest() {
    }

    /**
     * Test of values method, of class FabApiRestRokcetChatV1Users.
     */
    @Test
    public void testValues() {
        SBCore.configurar(new ConfigCoreRCTestesRegraNegocio(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        gerarCodigos(FabApiRestRokcetChatV1Users.class);
        RespostaWebServiceSimples resposta = FabApiRestRokcetChatV1Users.DIRECT_MENSAGENS_CONTADORES.getAcao().getResposta();
        System.out.println(resposta);
    }

}
