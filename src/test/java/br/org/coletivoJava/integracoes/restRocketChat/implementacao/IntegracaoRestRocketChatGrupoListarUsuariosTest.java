/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package br.org.coletivoJava.integracoes.restRocketChat.implementacao;

import br.org.coletivoJava.fw.erp.implementacao.chatService.ItfChatSalaBean;
import br.org.coletivoJava.fw.erp.implementacao.chatService.ServicoChatRocketChat;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.integracao.rocketChat.implementacaoRCRest.ConfigCoreRCTestesRegraNegocio;
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
public class IntegracaoRestRocketChatGrupoListarUsuariosTest {

    public IntegracaoRestRocketChatGrupoListarUsuariosTest() {
    }

    @Before
    public void setUp() {
        SBCore.configurar(new ConfigCoreRCTestesRegraNegocio(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
    }

    @Test
    public void testSomeMethod() {
        // TODO review the generated test code and remove the default call to fail.
        ServicoChatRocketChat instance = new ServicoChatRocketChat();
        ItfChatSalaBean sala = instance.getChat("TECNOLGIA");
        instance.adicionarUsuario(sala, "salviof@gmail.com");
    }

}
