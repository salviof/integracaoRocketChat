/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package br.org.coletivoJava.integracoes.restRocketChat.api.sessao;

import br.org.coletivoJava.integracoes.restRocketChat.api.FabConfigRocketChat;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.ItfFabricaIntegracaoRest;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.FabTipoConexaoRest;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.InfoConsumoRestService;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.servicoRegistrado.FabTipoAutenticacaoRest;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.servicoRegistrado.InfoConfigRestClientIntegracao;

@InfoConfigRestClientIntegracao(enderecosDocumentacao = "https://rocket.chat/docs/developer-guides/rest-api/",
        tipoAutenticacao = FabTipoAutenticacaoRest.CHAVE_ACESSO_METODOLOGIA_PROPRIA,
        nomeIntegracao = FabConfigRocketChat.NOME_APLICACAO,
        configuracao = FabConfigRocketChat.class
)
public enum FabApiRestRocketChatSessao implements ItfFabricaIntegracaoRest {

    @InfoConsumoRestService(getPachServico = "/api/v1/me",
            tipoConexao = FabTipoConexaoRest.GET,
            urlDocumentacao = "https://rocket.chat/docs/developer-guides/rest-api/authentication/me/")
    QUEM_SOU_EU,
    @InfoConsumoRestService(getPachServico = "/api/v1/logout",
            tipoConexao = FabTipoConexaoRest.POST,
            urlDocumentacao = "https://developer.rocket.chat/reference/api/rest-api/endpoints/other-important-endpoints/authentication-endpoints/logout")
    EFETUAR_LOUGOUT,

}
