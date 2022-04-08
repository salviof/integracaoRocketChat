/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.coletivoJava.integracoes.restRocketChat.implementacao;

import br.org.coletivoJava.integracoes.restRocketChat.api.FabConfigRocketChat;
import br.org.coletivoJava.integracoes.restRocketChat.api.users.FabApiRestRokcetChatV1Users;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.token.ItfTokenDeAcessoExterno;
import com.super_bits.modulosSB.SBCore.integracao.rocketChat.implementacaoRCRest.ConfigCoreRCTestesRegraNegocio;
import java.util.Scanner;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author sfurbino
 */
public class GestaoTokenRestRocketChatTest {

    public GestaoTokenRestRocketChatTest() {
    }

    /**
     * Test of gerarNovoToken method, of class GestaoTokenRestRocketChat.
     */
    @Test
    public void testGerarNovoTokenSistema() {
        SBCore.configurar(new ConfigCoreRCTestesRegraNegocio(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        SBCore.getConfigModulo(FabConfigRocketChat.class);
        System.out.println("O usuário e senha do sistema são:");
        System.out.println(SBCore.getConfigModulo(FabConfigRocketChat.class).getPropriedade(FabConfigRocketChat.USUARIO_ASSISTENTE_DE_CANAIS));
        System.out.println(SBCore.getConfigModulo(FabConfigRocketChat.class).getPropriedade(FabConfigRocketChat.SENHA_ASSISTENTE_DE_CANAIS));
        GestaoTokenRestRocketChat token = FabApiRestRokcetChatV1Users.USUARIOS_LISTAR.getGestaoToken();
        token.excluirToken();
        token.gerarNovoToken();
        System.out.println(token.getCodigoUsuarioRocketChat());
    }

    public void testeGerarNovoTokenUsuario() {
        UsuarioSB novoUsuari = new UsuarioSB();

        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Entre com um usuário válido do rocket chat");
        //String login = myObj.nextLine();  // Read user input
        System.out.println("Entre com a senha do usuário");
        //  String senha = myObj.nextLine();  // Read user input
        novoUsuari.setSenha("");
        novoUsuari.setEmail("");
        SBCore.configurar(new ConfigCoreRCTestesRegraNegocio(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        SBCore.getConfigModulo(FabConfigRocketChat.class);
        String usuario = SBCore.getConfigModulo(FabConfigRocketChat.class).getPropriedade(FabConfigRocketChat.USUARIO_ASSISTENTE_DE_CANAIS);
        String senha = SBCore.getConfigModulo(FabConfigRocketChat.class).getPropriedade(FabConfigRocketChat.SENHA_ASSISTENTE_DE_CANAIS);
        System.out.println("Usuario");
        System.out.println("Senha");
        Assert.assertNotNull("O usuario não foi definido no arquivo de configuracao", usuario);
        Assert.assertNotNull("O token não foi gerado", senha);
        GestaoTokenRestRocketChat token = FabApiRestRokcetChatV1Users.USUARIOS_LISTAR.getGestaoToken(novoUsuari);
        token.excluirToken();
        ItfTokenDeAcessoExterno tokengerado = token.gerarNovoToken();
        Assert.assertNotNull("O token não foi gerado", tokengerado);
        System.out.println(token.getCodigoUsuarioRocketChat());
    }

}
