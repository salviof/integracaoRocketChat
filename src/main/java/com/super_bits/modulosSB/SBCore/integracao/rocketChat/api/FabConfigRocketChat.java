/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.SBCore.integracao.rocketChat.api;

import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ItfFabConfigModulo;

/**
 *
 * @author sfurbino
 * @since 07/12/2019
 * @version 1.0
 */
public enum FabConfigRocketChat implements ItfFabConfigModulo {
    URL_SERVIDOR_ROCKET_CHAT,
    USUARIO_ASSISTENTE_DE_CANAIS,
    SENHA_ASSISTENTE_DE_CANAIS;

    public static final String NOME_APLICACAO = "Rocket Chat";

    @Override
    public String getValorPadrao() {
        return "https://chat.enderecoexemplo.com.br:666";
    }

}
