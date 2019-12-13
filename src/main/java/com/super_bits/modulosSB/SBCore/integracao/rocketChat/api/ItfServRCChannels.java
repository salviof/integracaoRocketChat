/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.SBCore.integracao.rocketChat.api;

import com.super_bits.modulosSB.SBCore.integracao.rocketChat.api.channel.ItfRCChannel;

/**
 * @author sfurbino
 * @since 07/12/2019
 * @version 1.0
 */
public interface ItfServRCChannels {

    public boolean isChannelExiste(String pCodigoCanal);

    /**
     *
     * @param pNomeCanal
     * @return O Canal sincronizado com o servidor, ou nulo caso não exista
     */
    public ItfRCChannel getChannelByNome(String pNomeCanal);

}
