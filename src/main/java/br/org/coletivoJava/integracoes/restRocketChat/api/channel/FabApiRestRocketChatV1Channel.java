/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.coletivoJava.integracoes.restRocketChat.api.channel;

import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.ItfFabricaIntegracaoRest;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.FabTipoConexaoRest;
import br.org.coletivoJava.integracoes.restRocketChat.api.FabConfigRocketChat;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.InfoConsumoRestService;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.servicoRegistrado.FabTipoAutenticacaoRest;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.servicoRegistrado.InfoConfigRestClientIntegracao;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.importacao.FabTipoArquivoImportacao;

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
            urlDocumentacao = "https://rocket.chat/docs/developer-guides/rest-api/groups/list/")
    GRUPO_LISTAR,
    @InfoConsumoRestService(getPachServico = "/api/v1/groups.create",
            tipoConexao = FabTipoConexaoRest.POST,
            tipoInformacaoEnviada = FabTipoArquivoImportacao.JSON,
            urlDocumentacao = "https://rocket.chat/docs/developer-guides/rest-api/groups/create/"
    )
    GRUPO_NOVO,
    /**
     * Atualmente suporta apenas pesquisas em canais vinculados a um ID, exemplo
     * nomeCanal-123 -> 123 representa o id
     */
    @InfoConsumoRestService(getPachServico = "/api/v1/groups.info?roomName={0}",
            parametrosGet = {"nomeGrupo"},
            tipoConexao = FabTipoConexaoRest.GET,
            urlDocumentacao = "http://localhost:3000/api/v1/groups.info?roomName={nomeGrupo}")
    GRUPO_EXISTE_GRUPO,
    /**
     * -dados: '{ "roomId": "ByehQjC44FwMeiLbX", "userId": "nSYqWzZ4GsKTX4dyK"
     * }'
     *
     */
    @InfoConsumoRestService(getPachServico = "/api/v1/groups.invite",
            tipoConexao = FabTipoConexaoRest.POST, tipoInformacaoEnviada = FabTipoArquivoImportacao.JSON,
            tipoInformacaoRecebida = FabTipoArquivoImportacao.JSON,
            urlDocumentacao = "https://rocket.chat/docs/developer-guides/rest-api/groups/invite/ ")
    GRUPO_ADICIONAR_USUARIO,
    @InfoConsumoRestService(getPachServico = "/api/v1/groups.delete",
            tipoConexao = FabTipoConexaoRest.POST,
            urlDocumentacao = "https://rocket.chat/docs/developer-guides/rest-api/groups/delete/")
    GRUPO_EXCLUIR_GRUPO,
    @InfoConsumoRestService(getPachServico = "/api/v1/me",
            tipoConexao = FabTipoConexaoRest.GET,
            urlDocumentacao = "https://rocket.chat/docs/developer-guides/rest-api/authentication/me/")
    QUEM_SOU_EU,
    @InfoConsumoRestService(getPachServico = "/api/v1/groups.addOwner",
            tipoConexao = FabTipoConexaoRest.POST, tipoInformacaoEnviada = FabTipoArquivoImportacao.JSON,
            tipoInformacaoRecebida = FabTipoArquivoImportacao.JSON,
            parametrosPost = {"roomId", "userId"},
            urlDocumentacao = "https://rocket.chat/docs/developer-guides/rest-api/groups/invite/ ")
    GRUPO_ADICIONAR_DONO_GRUPO,
    @InfoConsumoRestService(getPachServico = "/api/v1/chat.postMessage",
            tipoConexao = FabTipoConexaoRest.POST, tipoInformacaoEnviada = FabTipoArquivoImportacao.JSON,
            tipoInformacaoRecebida = FabTipoArquivoImportacao.JSON,
            parametrosPost = {"channel", "text"},
            urlDocumentacao = "https://docs.rocket.chat/api/rest-api/methods/chat/sendmessage")
    ENVIAR_MENSAGEM;

}
