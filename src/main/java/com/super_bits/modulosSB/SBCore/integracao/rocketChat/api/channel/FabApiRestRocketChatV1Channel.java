/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.SBCore.integracao.rocketChat.api.channel;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ConfigModulo;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.ItfFabricaIntegracaoRest;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.FabTipoConexaoRest;
import com.super_bits.modulosSB.SBCore.integracao.rocketChat.api.FabConfigRocketChat;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.InfoConsumoRestService;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.ItfApiServicoTokenCliente;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.servicoRegistrado.FabTipoAutenticacaoRest;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.servicoRegistrado.InfoConfigRestClientIntegracao;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.ServicoTokenClienteRestIntegracao;

/**
 *
 * @author sfurbino
 */
@InfoConfigRestClientIntegracao(enderecosDocumentacao = "https://rocket.chat/docs/developer-guides/rest-api/",
        tipoAutenticacao = FabTipoAutenticacaoRest.CHAVE_ACESSO_METODOLOGIA_PROPRIA,
        nomeIntegracao = FabConfigRocketChat.NOME_APLICACAO)
public enum FabApiRestRocketChatV1Channel implements ItfFabricaIntegracaoRest<FabConfigRocketChat> {

    @InfoConsumoRestService(getPachServico = "/api/v1/groups.list",
            tipoConexao = FabTipoConexaoRest.GET,
            urlDocumentacao = "https://rocket.chat/docs/developer-guides/rest-api/groups/list/ ")
    GRUPO_LISTAR,
    @InfoConsumoRestService(getPachServico = "/api/v1/groups.create",
            tipoConexao = FabTipoConexaoRest.POST,
            urlDocumentacao = "https://rocket.chat/docs/developer-guides/rest-api/groups/create/"
    )
    GRUPO_NOVO,
    GRUPO_ADICIONAR_USUARIO,
    GRUPO_EXCLUIR_GRUPO;

    @Override
    public ItfApiServicoTokenCliente getApiTokenAcesso() {
        return new ServicoTokenClienteRestIntegracao(getConfiguracao());
    }

    @Override
    public Class<? extends FabConfigRocketChat> getFabricaConfiguracao() {
        return FabConfigRocketChat.class;

    }

    @Override
    public ConfigModulo getConfiguracao() {
        return SBCore.getConfigModulo(getFabricaConfiguracao());
    }

}
