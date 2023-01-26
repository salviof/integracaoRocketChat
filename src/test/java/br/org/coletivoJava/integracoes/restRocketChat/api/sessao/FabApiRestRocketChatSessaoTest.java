/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package br.org.coletivoJava.integracoes.restRocketChat.api.sessao;

import br.org.coletivoJava.integracoes.restRocketChat.api.users.FabApiRestRokcetChatV1Users;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.integracao.rocketChat.implementacaoRCRest.ConfigCoreRCTestesRegraNegocio;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import testes.testesSupers.TestesApiRest;

/**
 *
 * @author salvio
 */
public class FabApiRestRocketChatSessaoTest extends TestesApiRest {

    public FabApiRestRocketChatSessaoTest() {
    }

    /**
     * Test of valueOf method, of class FabApiRestRocketChatSessao.
     */
    @Test
    public void testValueOf() {
        SBCore.configurar(new ConfigCoreRCTestesRegraNegocio(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        gerarCodigosChamadasEndpoint(FabApiRestRocketChatSessao.class);
    }

}
