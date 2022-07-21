/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.coletivoJava.integracoes.restRocketChat.api.users;

import br.org.coletivoJava.integracoes.restRocketChat.api.FabConfigRocketChat;
import br.org.coletivoJava.integracoes.restRocketChat.implementacao.GestaoTokenRestRocketChat;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.ItfFabricaIntegracaoRest;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.FabTipoConexaoRest;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.InfoConsumoRestService;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.servicoRegistrado.FabTipoAutenticacaoRest;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.servicoRegistrado.InfoConfigRestClientIntegracao;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.token.ItfTokenGestao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;

/**
 *
 * @author sfurbino
 */
@InfoConfigRestClientIntegracao(enderecosDocumentacao = "https://rocket.chat/docs/developer-guides/rest-api/",
        tipoAutenticacao = FabTipoAutenticacaoRest.CHAVE_ACESSO_METODOLOGIA_PROPRIA,
        nomeIntegracao = FabConfigRocketChat.NOME_APLICACAO,
        configuracao = FabConfigRocketChat.class
)
public enum FabApiRestRokcetChatV1Users implements ItfFabricaIntegracaoRest {

    @InfoConsumoRestService(getPachServico = "/api/v1/users.list",
            tipoConexao = FabTipoConexaoRest.GET,
            urlDocumentacao = "https://rocket.chat/docs/developer-guides/rest-api/users/list/")
    USUARIOS_LISTAR,
    @InfoConsumoRestService(getPachServico = "/api/v1/users.list",
            tipoConexao = FabTipoConexaoRest.GET,
            parametrosGet = {"email"},
            urlDocumentacao = "https://rocket.chat/docs/developer-guides/rest-api/users/list/")
    USUARIOS_ENCONTRAR_POR_EMAIL,
    @InfoConsumoRestService(getPachServico = "/api/v1/users.info?userId={0}",
            tipoConexao = FabTipoConexaoRest.GET,
            parametrosGet = {"email"},
            urlDocumentacao = "https://rocket.chat/docs/developer-guides/rest-api/users/list/")
    USUARIOS_ENCONTRAR_POR_CODIGO,
    @InfoConsumoRestService(getPachServico = "/api/v1/users.create",
            tipoConexao = FabTipoConexaoRest.POST,
            parametrosGet = {"name", "email", "password", "username"},
            urlDocumentacao = "https://rocket.chat/docs/developer-guides/rest-api/users/create/")
    //     -d '{"name": "name", "email": "email@user.tld", "password": "anypassyouwant", "username": "uniqueusername"}'

    USUARIOS_CRIAR,
    /**
     * Aquardando este
     * requisito:https://github.com/RocketChat/Rocket.Chat/pull/13634
     */
    @InfoConsumoRestService(getPachServico = "/api/v1/users.update",
            tipoConexao = FabTipoConexaoRest.POST,
            parametrosGet = {"codUsuario", "senha"},
            urlDocumentacao = "https://rocket.chat/docs/developer-guides/rest-api/users/create/")
    USUARIOS_ALTERAR_SENHA,
    @InfoConsumoRestService(getPachServico = "/api/v1/im.counters",
            tipoConexao = FabTipoConexaoRest.GET,
            parametrosGet = {"username"},
            urlDocumentacao = "https://rocket.chat/docs/developer-guides/rest-api/authentication/me/")
    DIRECT_MENSAGENS_CONTADORES,
    @InfoConsumoRestService(getPachServico = "/api/v1/subscriptions.get",
            tipoConexao = FabTipoConexaoRest.GET,
            parametrosGet = {"username"},
            urlDocumentacao = "https://rocket.chat/docs/developer-guides/rest-api/authentication/me/")
    ASSINATURAS_LISTAR,;

    @Override
    public GestaoTokenRestRocketChat getGestaoToken() {
        return (GestaoTokenRestRocketChat) ItfFabricaIntegracaoRest.super.getGestaoToken(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GestaoTokenRestRocketChat getGestaoToken(ItfUsuario pUsuario) {
        return (GestaoTokenRestRocketChat) ItfFabricaIntegracaoRest.super.getGestaoToken(pUsuario); //To change body of generated methods, choose Tools | Templates.

    }

}
