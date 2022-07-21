/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package br.org.coletivoJava.fw.erp.implementacao.chatService;

import br.org.coletivoJava.integracoes.restRocketChat.api.FabConfigRocketChat;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.integracao.rocketChat.implementacaoRCRest.ConfigCoreRCTestesRegraNegocio;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
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
public class ServicoChatRocketChatTest {

    private static String USUARIO;
    private static String SENHA;

    private static String NOME_SALA;

    private static String NOME_SALA_NOVA;

    public ServicoChatRocketChatTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        SBCore.configurar(new ConfigCoreRCTestesRegraNegocio(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        USUARIO = SBCore.getConfigModulo(FabConfigRocketChat.class).getPropriedade(FabConfigRocketChat.USUARIO_ASSISTENTE_DE_CANAIS);
        SENHA = SBCore.getConfigModulo(FabConfigRocketChat.class).getPropriedade(FabConfigRocketChat.SENHA_ASSISTENTE_DE_CANAIS);
        NOME_SALA = "salaTestes";
        NOME_SALA_NOVA = "salaTesteTemporario";
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
    }

    @Test
    public void testAutenticarSessaoAtual() {

        try {
            ServicoChatRocketChat instance = new ServicoChatRocketChat();
            boolean autenticado = instance.autenticarSessao(USUARIO, SENHA);
            assertTrue("Falha autenticando usuário", autenticado);
        } catch (ErroConexaoServicoChat ex) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, SENHA, ex);
        }

    }

    @Test
    public void testGetChat() {

        ServicoChatRocketChat instance = new ServicoChatRocketChat();
        ItfChatSalaBean salaEncontrada = instance.getChat(NOME_SALA);
        assertNotNull("Sala não encontrada", salaEncontrada);
        assertTrue("A sala não foi encontrada", salaEncontrada.isExiste());

    }

    @Test
    public void testGetChatCriandoSeNaoExistir() throws Exception {
        ServicoChatRocketChat instance = new ServicoChatRocketChat();
        try {
            ItfChatSalaBean salaEncontrada;
            salaEncontrada = instance.getChatCriandoSeNaoExistir(NOME_SALA_NOVA);
            assertNotNull("Sala não encontrada", salaEncontrada);
            assertTrue("A sala não foi encontrada", salaEncontrada.isExiste());
            assertTrue("Falha removendo canal de grupo privado", instance.excluirSala(salaEncontrada));;

        } catch (ErroConexaoServicoChat ex) {

        }

    }

    @Test
    public void testAtualizarListaDeUsuarios() {
        System.out.println("atualizarListaDeUsuarios");
        ServicoChatRocketChat instance = new ServicoChatRocketChat();
        List<ItfUsuarioChat> expResult = null;
        List<ItfUsuarioChat> result;
        try {
            result = instance.atualizarListaDeUsuarios();
            assertEquals(expResult, result);
        } catch (ErroConexaoServicoChat ex) {
            Logger.getLogger(ServicoChatRocketChatTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        // TODO review the generated test code and remove the default call to fail.
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getUsuario method, of class ServicoChat.
     */
    @Test
    public void testGetUsuario() {

        try {
            ServicoChatRocketChat instance = new ServicoChatRocketChat();

            ItfUsuarioChat result = instance.getUsuario(USUARIO);
            assertNotNull("Usuario auxliadora era experada", result);
            // TODO review the generated test code and remove the default call to fail.
        } catch (ErroConexaoServicoChat ex) {
            throw new UnsupportedOperationException(ex);
        }
    }

    /**
     * Test of getUsuarios method, of class ServicoChat.
     */
    @Test
    public void testGetUsuarios() {
        System.out.println("getUsuarios");
        ServicoChatRocketChat instance = new ServicoChatRocketChat();

        List<ItfUsuarioChat> usuarios = instance.getUsuarios();
        assertNotNull("Resultado não encontrado", usuarios);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of criarUsuario method, of class ServicoChat.
     */
    @Test
    public void testCriarUsuario_ItfUsuario() {

    }

    /**
     * Test of criarUsuario method, of class ServicoChat.
     */
    @Test
    public void testCriarUsuario_ItfUsuario_String() {

    }

    /**
     * Test of getUsuarioLogado method, of class ServicoChat.
     */
    @Test
    public void testGetUsuarioLogado() {

        ServicoChatRocketChat instance = new ServicoChatRocketChat();

        ItfUsuarioChat usuario = instance.getUsuarioLogado();
        assertNotNull("Resultado não encontrado", usuario);
        fail("The test case is a prototype.");
    }

    /**
     * Test of efetuarLogin method, of class ServicoChat.
     */
    @Test
    public void testEfetuarLogin_ItfUsuario() {

        fail("The test case is a prototype.");
    }

    /**
     * Test of efetuarLogin method, of class ServicoChat.
     */
    @Test
    public void testEfetuarLogin_ItfUsuario_String() {

        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testadicionarUsuario() {

        ServicoChatRocketChat instance = new ServicoChatRocketChat();
        ItfChatSalaBean sala = instance.getChat("TECNOLGIA");
        instance.adicionarUsuario(sala, "salviof@gmail.com");
    }
}
