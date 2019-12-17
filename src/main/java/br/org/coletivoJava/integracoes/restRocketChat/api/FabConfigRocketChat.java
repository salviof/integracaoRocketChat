/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.coletivoJava.integracoes.restRocketChat.api;

import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ItfFabConfigModulo;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.tipoModulos.integracaoOauth.FabPropriedadeModuloIntegracaoOauth;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.tipoModulos.integracaoOauth.InfoPropriedadeConfigRestIntegracao;

/**
 *
 * @author sfurbino
 * @since 07/12/2019
 * @version 1.0
 */
public enum FabConfigRocketChat implements ItfFabConfigModulo {

    @InfoPropriedadeConfigRestIntegracao(tipoPropriedade = FabPropriedadeModuloIntegracaoOauth.URL_SERVIDOR_API)
    URL_SERVIDOR_ROCKET_CHAT,
    @InfoPropriedadeConfigRestIntegracao(tipoPropriedade = FabPropriedadeModuloIntegracaoOauth.USUARIO)
    USUARIO_ASSISTENTE_DE_CANAIS,
    @InfoPropriedadeConfigRestIntegracao(tipoPropriedade = FabPropriedadeModuloIntegracaoOauth.SENHA)
    SENHA_ASSISTENTE_DE_CANAIS;

    public static final String NOME_APLICACAO = "Rocket Chat";

    @Override
    public String getValorPadrao() {
        switch (this) {
            case URL_SERVIDOR_ROCKET_CHAT:
                return "https://chat.enderecoexemplo.com.br:666";

            case USUARIO_ASSISTENTE_DE_CANAIS:
                return "nomeUsuarioRC@ouEmail.com";
            case SENHA_ASSISTENTE_DE_CANAIS:
                return "minhasenhasecretaquenaocontoparaninguemcontendoaté@e123";

            default:
                throw new AssertionError(this.name());

        }

    }

}
