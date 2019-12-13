/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.SBCore.integracao.rocketChat;

import com.super_bits.modulosSB.SBCore.integracao.rocketChat.api.FabConfigRocketChat;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.integracao.rocketChat.api.ItfServRCChannels;
import com.super_bits.modulosSB.SBCore.integracao.rocketChat.implementacaoRCRest.old.ServicoRestRocketChat;

/**
 *
 * @author sfurbino
 * @since 07/12/2019
 * @version 1.0
 */
public interface ItfServicoRestRocketChat {

    public static ItfServicoRestRocketChat getIplContexto() {
        return new ServicoRestRocketChat(SBCore.getConfigModulo(FabConfigRocketChat.class));
    }

    public ItfServRCChannels getServicosChannel();
}
