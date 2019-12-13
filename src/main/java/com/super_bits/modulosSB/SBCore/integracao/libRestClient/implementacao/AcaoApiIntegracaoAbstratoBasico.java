/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao;

import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.gestaoToken.MapaTokensGerenciados;
import com.google.api.client.util.ArrayMap;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ConfigModulo;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringValidador;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.RespostaWebServiceRestIntegracao;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.ConsumoWSExecucao;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.FabTipoConexaoRest;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.InfoConsumoRestService;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.ItfRespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.RespostaWebServiceSimples;

import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.transmissao_recepcao_rest_client.ItfAcaoApiRest;
import java.util.Map;
import javax.ws.rs.core.HttpHeaders;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.ItfFabricaIntegracaoRest;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.token.ItfTokenGestao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;

/**
 *
 * @author desenvolvedor
 */
public abstract class AcaoApiIntegracaoAbstratoBasico implements ItfAcaoApiRest {

    private ConsumoWSExecucao consumoWS;
    protected ItfFabricaIntegracaoRest fabricaIntegracao;
    protected InfoConsumoRestService infoRest;
    private String corpoRequisicaoGerado;
    private String urlRequisicaoGerada;
    private FabTipoConexaoRest tipoRequisicao;
    private Map<String, String> cabecalhoGerado;
    private boolean postarInformacoes;
    private RespostaWebServiceRestIntegracao resposta;
    private String token;
    protected Object[] parametros;

    public AcaoApiIntegracaoAbstratoBasico(ItfFabricaIntegracaoRest conexao, Object... pParametros) {
        this.fabricaIntegracao = conexao;
        infoRest = UtilSBApiRestClient.getInformacoesConsumoRest(conexao);
        parametros = pParametros;
        try {
            executarAcao();
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro criando ação de integração Rest em:" + conexao, t);
        }

    }

    public ConfigModulo getConfiguracao() {
        return fabricaIntegracao.getConfiguracao();
    }

    @Override
    public String gerarTokenAcesso() {
        return MapaTokensGerenciados.getAutenticadorSistemaAtual(fabricaIntegracao).getTokenDeAcesso().getTokenValido();

    }

    @Override
    public String gerarUrlRequisicao() {
        if (infoRest == null) {
            return getUrlServidor();
        } else {
            return (getUrlServidor() + infoRest.getPachServico()).replace("//", "/");
        }
    }

    @Override
    public String gerarCorpoRequisicao() {
        return "";
    }

    private boolean defineRequisicaoPostaInformacoes() {
        switch (tipoRequisicao) {
            case POST:
            case PUT:
            case DELET:
                return true;

            case GET:
                break;

            case PATCH:
                break;
            case INDETERMINADO:

                break;

            default:
                throw new AssertionError(infoRest.tipoConexao().name());

        }
        return false;

    }

    @Override
    public Map<String, String> gerarCabecalho() {
        Map<String, String> cabecalho = new ArrayMap<>();
        if (infoRest != null) {
            if (infoRest.tipoInformacaoEnviada().getMediaType() != null) {
                cabecalho.put(HttpHeaders.CONTENT_TYPE, infoRest.tipoInformacaoEnviada().getMediaType().toString());
            }
            if (infoRest.adicionarAutenticacaoBearer()) {
                cabecalho.put("Authorization", "Bearer " + token);
            }
        }
        if (!UtilSBCoreStringValidador.isNuloOuEmbranco(corpoRequisicaoGerado)) {
            cabecalho.put(HttpHeaders.CONTENT_LENGTH, String.valueOf(corpoRequisicaoGerado.length()));
        }
        return cabecalho;
    }

    @Override
    public FabTipoConexaoRest gerarTipoRequisicao() {

        if (fabricaIntegracao.getInformacoesConsumo().tipoConexao().equals(FabTipoConexaoRest.INDETERMINADO)) {
            if (this.toString().contains("CTR")) {
                return FabTipoConexaoRest.PUT;
            }
            if (this.toString().contains("SELECAO")) {
                return FabTipoConexaoRest.PATCH;
            }

            return FabTipoConexaoRest.GET;

        } else {
            return fabricaIntegracao.getInformacoesConsumo().tipoConexao();
        }
    }

    private void executarAcao() {
        urlRequisicaoGerada = gerarUrlRequisicao();
        token = gerarTokenAcesso();
        tipoRequisicao = gerarTipoRequisicao();
        postarInformacoes = defineRequisicaoPostaInformacoes();
        corpoRequisicaoGerado = gerarCorpoRequisicao();
        cabecalhoGerado = gerarCabecalho();

        consumoWS = new ConsumoWSExecucao() {

            @Override
            public RespostaWebServiceSimples efetuarConexao() {
                return UtilSBApiRestClient.getRespostaRest(
                        urlRequisicaoGerada, tipoRequisicao, postarInformacoes, cabecalhoGerado, corpoRequisicaoGerado);

            }

        };

        gerarResposta(consumoWS);
    }

    @Override
    public boolean isMetodoEnviaDados() {
        return ((tipoRequisicao.equals(FabTipoConexaoRest.PUT)) || (tipoRequisicao.equals(FabTipoConexaoRest.POST)));
    }

    @Override
    public void gerarResposta(ConsumoWSExecucao pConsumoRest) {
        consumoWS.start();
        resposta = pConsumoRest.getResposta();
    }

    @Override
    public String getUrlServidor() {
        return urlRequisicaoGerada;
    }

    @Override
    public ItfRespostaWebServiceSimples getResposta() {
        return resposta;
    }

    @Override
    public ItfTokenGestao getTokenSistemaGestao() {
        return MapaTokensGerenciados.getAutenticadorSistemaAtual(fabricaIntegracao);
    }

    @Override
    public ItfTokenGestao getTokenUsuarioGestao(ItfUsuario pUsuario) {
        return MapaTokensGerenciados.getAutenticadorUsuarioLogado(fabricaIntegracao, pUsuario);
    }

}
