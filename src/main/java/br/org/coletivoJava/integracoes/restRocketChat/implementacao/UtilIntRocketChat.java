/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.coletivoJava.integracoes.restRocketChat.implementacao;

import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.RespostaWebServiceSimples;
import jakarta.json.JsonObject;
import jakarta.json.JsonValue;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author salvio
 */
public class UtilIntRocketChat {

    public static RespostaWebServiceSimples gerarRespostaTratamentoFino(RespostaWebServiceSimples pRespostaWSSemTratamento) {
        try {

            JsonObject respJson = pRespostaWSSemTratamento.getRespostaComoObjetoJson();
            if (respJson.containsKey("success")) {
                if (!respJson.getBoolean("success")) {
                    String mensagemErro = respJson.getString("error");
                    String tipoErro = respJson.getString("errorType");
                    pRespostaWSSemTratamento.addErro("Erro tipo " + tipoErro + ": " + mensagemErro);
                    System.out.println("Erro tipo " + tipoErro + ": " + mensagemErro);
                } else {
                    if (respJson.containsKey("count")) {
                        if (respJson.getInt("count") == 0) {
                            String tipoPesquisa = "";
                            Optional<Map.Entry<String, JsonValue>> pesquisaRegistro = respJson.entrySet().stream()
                                    .filter(entry -> entry.getValue().getValueType().equals(JsonValue.ValueType.ARRAY))
                                    .findFirst();
                            if (pesquisaRegistro.isPresent()) {
                                tipoPesquisa = pesquisaRegistro.get().getKey();
                            }

                            pRespostaWSSemTratamento.addErro("Nenhum registro [" + tipoPesquisa + "] encontrado");
                            System.out.println("Nenhum registro [" + tipoPesquisa + "] encontrado");
                        }
                    }

                }
            }

            return pRespostaWSSemTratamento;
        } catch (Throwable t) {
            return pRespostaWSSemTratamento;
        }

    }
}
