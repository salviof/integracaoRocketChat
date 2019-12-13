/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.gestaoToken;

import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteRest;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ConfigModulo;
import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ItfFabConfigModulo;

import java.util.Date;
import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.tipoModulos.integracaoOauth.FabPropriedadeModuloIntegracaoOauth;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.ItfFabricaIntegracaoRest;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.oauth.FabStatusToken;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.oauth.InfoTokenOauth2;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.token.ItfTokenGestaoOauth;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;

/**
 *
 * @author SalvioF
 */
public class GestaoTokenOath2 implements ItfTokenGestaoOauth {

    private final FabTipoAgenteClienteRest tipoCliente;
    private final ConfigModulo confiModulo;
    private final Class<? extends ItfFabricaIntegracaoRest> fabricaOauth;
    private String chavePublica;

    private String chavePrivada;
    private String siteCliente;
    private String siteServidor;
    private String codigoSolicitacao;
    private InfoTokenOauth2 tokenDeAcesso;

    public FabTipoAgenteClienteRest getTipoCliente() {
        return tipoCliente;
    }

    public final void atualizarDados() {
        confiModulo.reloadConfiguracoes();
        chavePublica = confiModulo.getPropriedadePorAnotacao(FabPropriedadeModuloIntegracaoOauth.CHAVE_PUBLICA);
        chavePrivada = confiModulo.getPropriedadePorAnotacao(FabPropriedadeModuloIntegracaoOauth.CHAVE_PRIVADA);
        siteCliente = confiModulo.getPropriedadePorAnotacao(FabPropriedadeModuloIntegracaoOauth.SITE_CLIENTE);
        siteServidor = confiModulo.getPropriedadePorAnotacao(FabPropriedadeModuloIntegracaoOauth.SITE_SERVIDOR);

    }

    public boolean isCodigoSolicitacaoRegistrado() {
        return codigoSolicitacao != null;
    }

    public GestaoTokenOath2(Class<? extends ItfFabConfigModulo> arquivoConfiguracao, FabTipoAgenteClienteRest pTipocliente, Class<? extends ItfFabricaIntegracaoRest> pFabricaOauth) {
        tipoCliente = pTipocliente;
        confiModulo = SBCore.getConfigModulo(arquivoConfiguracao);
        fabricaOauth = pFabricaOauth;
        atualizarDados();
    }

    public final boolean carregarTokenArmazenado(ItfFabricaIntegracaoRest api) {
        InfoTokenOauth2 tokenencontrado = null;
        switch (tipoCliente) {
            case USUARIO:
                tokenencontrado = api.getApiTokenAcesso().getTokenArmazenadoUsuario();

                break;
            case SISTEMA:
                tokenencontrado = api.getApiTokenAcesso().getTokenArmazenadoSistema();
                break;
            default:
                throw new AssertionError(tipoCliente.name());

        }
        if (tokenencontrado != null) {
            tokenDeAcesso = tokenencontrado;
            return true;
        } else {
            return false;
        }
    }

    public FabStatusToken getStatusToken() {

        if (getTokenDeAcesso() != null) {
            if (tokenDeAcesso.getDataHoraExpirarToken() == null) {
                return FabStatusToken.ATIVO;
            } else {
                if (tokenDeAcesso.getDataHoraExpirarToken().getTime() < new Date().getTime()) {
                    return FabStatusToken.EXPIRADO;
                } else {
                    return FabStatusToken.ATIVO;
                }
            }
        } else {
            if (codigoSolicitacao != null) {
                return FabStatusToken.SOLICITACAO_TOKEN_EM_ANDAMENTO;
            }
        }

        return FabStatusToken.SEM_TOKEN;
    }

    public boolean isPossuiTokenValido() {

        switch (getStatusToken()) {
            case ATIVO:
                return true;
            default:
                return false;
        }

    }

    public boolean atualizarTokenExpirado() {
        return false;
    }

    public String getUrlAutenticacao(ItfFabricaIntegracaoRest api) {
        return api.getApiTokenAcesso().getUrlSolicitacaoAutenticacao();
    }

    private final ItfFabricaIntegracaoRest getFabricaAcesosMetodosPadrao() {
        return fabricaOauth.getEnumConstants()[0];
    }

    public final void gerarNovoToken(String pCodigoSolicitacao) {
        codigoSolicitacao = pCodigoSolicitacao;
        ItfFabricaIntegracaoRest api = fabricaOauth.getEnumConstants()[0];

        atualizarDados();
        if (isPossuiTokenValido()) {
            return;
        }

        if (codigoSolicitacao != null) {
            tokenDeAcesso = api.getApiTokenAcesso().gerarNovoToken(codigoSolicitacao);
        } else {
            SBCore.getCentralDeMensagens().enviarMsgAvisoAoUsuario("O token não foi encontrado, acesse \n"
                    + api.getApiTokenAcesso().getUrlSolicitacaoAutenticacao() + "\n para obter um token de acesso.");
        }
    }

    public ConfigModulo getConfiModulo() {
        return confiModulo;
    }

    public String getChavePublica() {
        return chavePublica;
    }

    public String getChavePrivada() {
        return chavePrivada;
    }

    public String getSiteCliente() {
        return siteCliente;
    }

    public String getSiteServidor() {
        return siteServidor;
    }

    public String getCodigoSolicitacao() {
        return codigoSolicitacao;
    }

    public InfoTokenOauth2 getTokenDeAcesso() {
        if (tokenDeAcesso == null) {
            switch (tipoCliente) {
                case USUARIO:
                    tokenDeAcesso = getFabricaAcesosMetodosPadrao().getApiTokenAcesso().getTokenArmazenadoUsuario();
                    break;
                case SISTEMA:
                    tokenDeAcesso = getFabricaAcesosMetodosPadrao().getApiTokenAcesso().getTokenArmazenadoSistema();
                    break;
                default:
                    throw new AssertionError(tipoCliente.name());

            }

        }
        return tokenDeAcesso;
    }

    public void setCodigoSolicitacao(String codigoSolicitacao) {
        this.codigoSolicitacao = codigoSolicitacao;
    }

    @Override
    public String getTokenUsuario(ItfUsuario pUsuario) {
        throw new UnsupportedOperationException("O METODO AINDA N\u00c3O FOI IMPLEMENTADO.");
    }

    @Override
    public String getTokenSistema() {
        throw new UnsupportedOperationException("O METODO AINDA N\u00c3O FOI IMPLEMENTADO.");
    }

    @Override
    public String getTokenUsuarioLogado() {
        throw new UnsupportedOperationException("O METODO AINDA N\u00c3O FOI IMPLEMENTADO.");
    }

    @Override
    public boolean isTemTokemAtivoSistema() {
        throw new UnsupportedOperationException("O METODO AINDA N\u00c3O FOI IMPLEMENTADO.");
    }

    @Override
    public boolean isTemTokenAtivoUsuarioLogado() {
        throw new UnsupportedOperationException("O METODO AINDA N\u00c3O FOI IMPLEMENTADO.");
    }

    @Override
    public boolean istemTokemAtivoUsuario(ItfUsuario pUsuario) {
        throw new UnsupportedOperationException("O METODO AINDA N\u00c3O FOI IMPLEMENTADO.");
    }

    @Override
    public boolean gerarNovoToken(ItfUsuario pUsuario) {
        throw new UnsupportedOperationException("O METODO AINDA N\u00c3O FOI IMPLEMENTADO.");
    }

    @Override
    public boolean gerarNovoTokenSistema() {
        throw new UnsupportedOperationException("O METODO AINDA N\u00c3O FOI IMPLEMENTADO.");
    }

    @Override
    public boolean excluirToken() {
        throw new UnsupportedOperationException("O METODO AINDA N\u00c3O FOI IMPLEMENTADO.");
    }

}
