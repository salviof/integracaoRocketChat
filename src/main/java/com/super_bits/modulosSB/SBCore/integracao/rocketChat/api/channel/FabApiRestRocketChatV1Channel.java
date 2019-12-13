/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.SBCore.integracao.rocketChat.api.channel;

import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.ItfFabricaIntegracaoRest;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.FabTipoConexaoRest;
import com.super_bits.modulosSB.SBCore.integracao.rocketChat.api.FabConfigRocketChat;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.InfoConsumoRestService;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.ItfApiServicoTokenCliente;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.servicoRegistrado.FabTipoAutenticacaoRest;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.servicoRegistrado.InfoConfigRestClientIntegracao;

/**
 *
 * @author sfurbino
 */
@InfoConfigRestClientIntegracao(enderecosDocumentacao = "https://rocket.chat/docs/developer-guides/rest-api/",
        tipoAutenticacao = FabTipoAutenticacaoRest.CHAVE_ACESSO_METODOLOGIA_PROPRIA,
        nomeIntegracao = FabConfigRocketChat.NOME_APLICACAO,
        configuracao = FabConfigRocketChat.class
)
public enum FabApiRestRocketChatV1Channel implements ItfFabricaIntegracaoRest {

    @InfoConsumoRestService(getPachServico = "/api/v1/groups.listAll",
            tipoConexao = FabTipoConexaoRest.GET,
            urlDocumentacao = "https://rocket.chat/docs/developer-guides/rest-api/groups/list/ ")
    GRUPO_LISTAR,
    @InfoConsumoRestService(getPachServico = "/api/v1/groups.create",
            tipoConexao = FabTipoConexaoRest.POST,
            urlDocumentacao = "https://rocket.chat/docs/developer-guides/rest-api/groups/create/"
    )
    GRUPO_NOVO,
    @InfoConsumoRestService(getPachServico = "/api/v1/groups.listAll",
            tipoConexao = FabTipoConexaoRest.GET,
            urlDocumentacao = "https://rocket.chat/docs/developer-guides/rest-api/groups/list/ ")
    GRUPO_EXISTE_GRUPO,
    /**
     * -dados: '{ "roomId": "ByehQjC44FwMeiLbX", "userId": "nSYqWzZ4GsKTX4dyK"
     * }'
     *
     */
    @InfoConsumoRestService(getPachServico = "/api/v1/groups.invite",
            tipoConexao = FabTipoConexaoRest.POST,
            urlDocumentacao = "https://rocket.chat/docs/developer-guides/rest-api/groups/invite/ ")
    GRUPO_ADICIONAR_USUARIO,
    @InfoConsumoRestService(getPachServico = "/api/v1/groups.delete",
            tipoConexao = FabTipoConexaoRest.POST,
            urlDocumentacao = "https://rocket.chat/docs/developer-guides/rest-api/groups/delete/")
    GRUPO_EXCLUIR_GRUPO;

    @Override
    public ItfApiServicoTokenCliente getApiTokenAcesso() {
        throw new UnsupportedOperationException("O METODO AINDA N\u00c3O FOI IMPLEMENTADO.");
    }

}
