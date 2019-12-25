/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.coletivoJava.integracoes.restRocketChat.implementacao;

import br.org.coletivoJava.integracoes.restRocketChat.api.users.FabApiRestRokcetChatV1Users;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.RespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.rocketChat.implementacaoRCRest.ConfigCoreRCTestesRegraNegocio;
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
        RespostaWebServiceSimples resp = FabApiRestRokcetChatV1Users.USUARIOS_LISTAR.getAcao().getResposta();
        JSONObject dados = resp.getRespostaComoObjetoJson();
        JSONArray usuarios = (JSONArray) dados.get("users");
        usuarios.stream().forEach(usr -> {

            JSONObject usuario = (JSONObject) usr;
            JSONArray emails = (JSONArray) usuario.get("emails");
            String id = usuario.get("_id").toString();
            String nome = usuario.get("username").toString();
        });

    }

}
