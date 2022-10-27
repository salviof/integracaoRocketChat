/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.coletivoJava.integracoes.restRocketChat.implementacao;

import br.org.coletivoJava.integracoes.restRocketChat.api.users.FabApiRestRokcetChatV1Users;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.ItfRespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.RespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.rocketChat.implementacaoRCRest.ConfigCoreRCTestesRegraNegocio;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sfurbino
 */
public class IntegracaoRestRocketChatUsuariosListarTest {

    public IntegracaoRestRocketChatUsuariosListarTest() {
    }

    @Test
    public void testSomeMethod() {
        // TODO review the generated test code and remove the default call to fail.
        SBCore.configurar(new ConfigCoreRCTestesRegraNegocio(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
//RespostaWebServiceSimples resp = FabApiRestRokcetChatV1Users.USUARIOS_LISTAR.getAcao().getResposta();
        ItfRespostaWebServiceSimples resp = FabApiRestRokcetChatV1Users.USUARIOS_LISTAR.getAcao().getResposta();
        JsonObject dados = resp.getRespostaComoObjetoJson();
        JsonArray usuarios = (JsonArray) dados.getJsonArray("users");
        usuarios.stream().forEach(usr -> {

            JsonObject usuario = (JsonObject) usr;
            JsonArray emails = (JsonArray) usuario.get("emails");
            String id = usuario.get("_id").toString();
            String nome = usuario.get("username").toString();
            System.out.println(nome);
        });

    }

}
