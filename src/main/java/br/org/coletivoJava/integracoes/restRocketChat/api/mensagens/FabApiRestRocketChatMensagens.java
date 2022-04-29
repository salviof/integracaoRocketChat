/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.coletivoJava.integracoes.restRocketChat.api.mensagens;

import br.org.coletivoJava.integracoes.restRocketChat.api.direct.*;
import br.org.coletivoJava.integracoes.restRocketChat.api.FabConfigRocketChat;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.ItfFabricaIntegracaoRest;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.FabTipoConexaoRest;
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
public enum FabApiRestRocketChatMensagens implements ItfFabricaIntegracaoRest {
    @InfoConsumoRestService(getPachServico = "/api/v1/chat.postMessage",
            tipoConexao = FabTipoConexaoRest.POST, tipoInformacaoEnviada = FabTipoArquivoImportacao.JSON,
            tipoInformacaoRecebida = FabTipoArquivoImportacao.JSON,
            parametrosPost = {"roomId", "text", "alias", "attachments"},
            urlDocumentacao = "https://developer.rocket.chat/reference/api/rest-api/endpoints/team-collaboration-endpoints/chat-endpoints/")
    ENVIO_MENSAGEM_COM_ANEXO;

}
