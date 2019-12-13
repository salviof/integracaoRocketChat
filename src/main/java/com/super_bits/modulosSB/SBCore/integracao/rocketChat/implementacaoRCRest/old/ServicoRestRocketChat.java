/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.SBCore.integracao.rocketChat.implementacaoRCRest.old;

import com.google.api.client.util.store.FileDataStoreFactory;
import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ConfigModulo;
import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ItfConfigModulo;
import com.super_bits.modulosSB.SBCore.integracao.rocketChat.ItfServicoRestRocketChat;
import com.super_bits.modulosSB.SBCore.integracao.rocketChat.api.ItfServRCChannels;
import com.super_bits.modulosSB.SBCore.integracao.rocketChat.implementacaoRCRest.old.channel.RCServicosChannel;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sfurbino
 * @since 07/12/2019
 * @version 1.0
 */
public class ServicoRestRocketChat implements ItfServicoRestRocketChat {

    private final ConfigModulo cfg;

    public ServicoRestRocketChat(ItfConfigModulo pConfig) {
        cfg = (ConfigModulo) pConfig;
        //  SBCore.getConfigModulo(FabConfigRocketChat.class).getRepositorioDeArquivosExternos().putConteudoRecursoExterno(pIndice, pConteudo);
        FileDataStoreFactory data;
        try {
            data = new FileDataStoreFactory(new File("/caminhoarq"));

        } catch (IOException ex) {
            Logger.getLogger(ServicoRestRocketChat.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public ItfServRCChannels getServicosChannel() {
        return new RCServicosChannel(this);

    }

}
