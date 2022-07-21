/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package br.org.coletivoJava.integracoes.restRocketChat.implementacao;

import br.org.coletivoJava.integracoes.restRocketChat.api.users.FabApiRestRokcetChatV1Users;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.ConsumoWSExecucao;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.ItfRespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.rocketChat.implementacaoRCRest.ConfigCoreRCTestesRegraNegocio;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author salvio
 */
public class IntegracaoRestRocketChatUsuariosEncontrarPorCodigoTest {

    public IntegracaoRestRocketChatUsuariosEncontrarPorCodigoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        SBCore.configurar(new ConfigCoreRCTestesRegraNegocio(), SBCore.ESTADO_APP.DESENVOLVIMENTO);

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of gerarUrlRequisicao method, of class
     * IntegracaoRestRocketChatUsuariosEncontrarPorCodigo.
     */
    @Test
    public void testGerarUrlRequisicao() {

        ItfRespostaWebServiceSimples resposta = FabApiRestRokcetChatV1Users.USUARIOS_ENCONTRAR_POR_EMAIL.getAcao("salvio@casanovadigital.com.br").getResposta();
        JSONObject usuarioEncontrado = resposta.getRespostaComoObjetoJson();

        JSONArray usuariosrJSon = (JSONArray) usuarioEncontrado.get("users");

        String id = (String) ((JSONObject) usuariosrJSon.get(0)).get("_id");
        ItfRespostaWebServiceSimples repostaCore = FabApiRestRokcetChatV1Users.USUARIOS_ENCONTRAR_POR_CODIGO.getAcao(id).getResposta();
        assertTrue("Falha obtendo usuário com código", repostaCore.isSucesso());
    }

}
