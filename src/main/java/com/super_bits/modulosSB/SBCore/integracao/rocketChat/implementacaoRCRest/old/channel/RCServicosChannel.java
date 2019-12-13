/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.SBCore.integracao.rocketChat.implementacaoRCRest.old.channel;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.integracao.rocketChat.ItfServicoRestRocketChat;
import com.super_bits.modulosSB.SBCore.integracao.rocketChat.api.ItfServRCChannels;
import com.super_bits.modulosSB.SBCore.integracao.rocketChat.api.channel.ItfRCChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author sfurbino
 * @since 07/12/2019
 * @version 1.0
 */
public class RCServicosChannel implements ItfServRCChannels {

    private final ItfServicoRestRocketChat rcPrincipal;
    private String oauthToken;

    public RCServicosChannel(ItfServicoRestRocketChat rc) {
        this.rcPrincipal = rc;
    }

    /**
     *
     * https://rocket.chat/docs/developer-guides/rest-api/offset-and-count-and-sort-info/
     * TODO: ENCONTRAR UM TIPO DE BUSCA PELO CANAL MAIS EFICIENTE
     *
     * @param pnomeCanal
     * @return
     */
    @Override
    public boolean isChannelExiste(String pnomeCanal) {
        Map<String, String> cabecalho = new HashMap<>();
        cabecalho.put("X-Auth-Token", oauthToken);
        cabecalho.put("X-User-Id", "");
        JsonObject resposta = null;
        // UtilSBCoreClienteRest.getRespostaRest(pnomeCanal, FabTipoConexaoRest.OPTIONS, true, pCabecalho, pnomeCanal);
        if (resposta == null) {
            return false;
        }
        JsonArray canais = resposta.get("channels").getAsJsonArray();
        Optional<JsonElement> canalEncontrado = Lists.newArrayList(canais).parallelStream()
                .filter(canal -> canal.getAsJsonObject().get("name").equals(pnomeCanal))
                .findFirst();
        return canalEncontrado.isPresent();

    }

    @Override
    public ItfRCChannel getChannelByNome(String pNomeCanal) {
        try {
            Map<String, String> cabecalho = new HashMap<>();
            cabecalho.put("X-Auth-Token", oauthToken);
            cabecalho.put("X-User-Id", "");
            JsonObject resposta = null;
            // UtilSBCoreClienteRest.getRespostaRest(pnomeCanal, FabTipoConexaoRest.OPTIONS, true, pCabecalho, pnomeCanal);
            if (resposta == null) {
                return null;
            }
            JsonArray canais = resposta.get("channels").getAsJsonArray();
            Optional<JsonElement> canalEncontrado = Lists.newArrayList(canais).parallelStream()
                    .filter(canal -> canal.getAsJsonObject().get("name").equals(pNomeCanal))
                    .findFirst();
            if (canalEncontrado.isPresent()) {
                return new RCChannel(canalEncontrado.get().getAsJsonObject());
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo canal " + pNomeCanal, t);
        }
        return null;
    }

}
