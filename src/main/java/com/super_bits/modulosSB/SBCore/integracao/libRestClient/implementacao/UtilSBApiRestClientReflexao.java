/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringSlugs;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringsCammelCase;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.ItfFabricaIntegracaoRest;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.servicoRegistrado.InfoConfigRestClientIntegracao;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfValidacao;
import org.reflections.ReflectionUtils;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.ItfFabricaIntegracaoRest;

/**
 *
 * @author sfurbino
 * @since 11/12/2019
 * @version 1.0
 */
public class UtilSBApiRestClientReflexao {

    public static InfoConfigRestClientIntegracao getInfoConfigWebService(ItfFabricaIntegracaoRest p) {
        return p.getClass().getAnnotation(InfoConfigRestClientIntegracao.class);
    }

    public static String getNomeClasseAnotacao(ItfFabricaIntegracaoRest p) {
        InfoConfigRestClientIntegracao info = getInfoConfigWebService(p);
        return "InfoIntegracaoRest" + UtilSBCoreStringsCammelCase.getCamelByTextoPrimeiraLetraMaiusculaSemCaracterEspecial(info.nomeIntegracao()).replace("-", " ");
    }

    public static String getNomeClasseImplementacao(ItfFabricaIntegracaoRest p) {
        InfoConfigRestClientIntegracao info = getInfoConfigWebService(p);
        if (info == null) {
            throw new UnsupportedOperationException(InfoConfigRestClientIntegracao.class.getSimpleName() + "Não foi definido para" + p.getClass().getSimpleName());
        }
        String nome = info.nomeIntegracao();

        return "IntegracaoRest" + UtilSBCoreStringsCammelCase.getCamelByTextoPrimeiraLetraMaiuscula(nome) + UtilSBCoreStringsCammelCase.getCamelByTextoPrimeiraLetraMaiuscula(p.toString());
    }

    public static String getNomeClasseImplementacaoGestaoToken(ItfFabricaIntegracaoRest p) {
        InfoConfigRestClientIntegracao info = getInfoConfigWebService(p);
        if (info == null) {
            throw new UnsupportedOperationException(InfoConfigRestClientIntegracao.class.getSimpleName() + "Não foi definido para" + p.getClass().getSimpleName());
        }
        String nome = info.nomeIntegracao();

        return "GestaoTokenRest" + UtilSBCoreStringsCammelCase.getCamelByTextoPrimeiraLetraMaiuscula(nome) + UtilSBCoreStringsCammelCase.getCamelByTextoPrimeiraLetraMaiuscula(p.toString());
    }

    public static String getPacoteApi(ItfFabricaIntegracaoRest p) {
        InfoConfigRestClientIntegracao info = getInfoConfigWebService(p);
        if (info == null) {
            throw new UnsupportedOperationException(InfoConfigRestClientIntegracao.class.getSimpleName()
                    + "Não foi definido para" + p.getClass().getSimpleName());
        }
        String nome = info.nomeIntegracao();
        return "br.org.coletivoJava.integracoes.rest" + UtilSBCoreStringsCammelCase.getCamelByTextoPrimeiraLetraMaiuscula(nome) + ".api";
    }

    public static String getPacoteImplementacao(ItfFabricaIntegracaoRest p) {
        InfoConfigRestClientIntegracao info = getInfoConfigWebService(p);
        if (info == null) {
            throw new UnsupportedOperationException(InfoConfigRestClientIntegracao.class.getSimpleName() + "Não foi definido para" + p.getClass().getSimpleName());
        }
        String nome = info.nomeIntegracao();
        return "br.org.coletivoJava.integracoes.rest" + UtilSBCoreStringsCammelCase.getCamelByTextoPrimeiraLetraMaiuscula(nome) + ".implementacao";
    }

    public static Class getClasseAnotacao(ItfFabricaIntegracaoRest pApi) {
        String caminhoCompleto = getPacoteApi(pApi)
                + "." + getNomeClasseAnotacao(pApi);
        Class classeAnotacao = (Class<? extends ItfValidacao>) ReflectionUtils.forName(caminhoCompleto);
        return classeAnotacao;
    }

    public static Class getClasseImplementacao(ItfFabricaIntegracaoRest pApi) {
        String caminhoCompleto = getPacoteImplementacao(pApi)
                + "." + getNomeClasseImplementacao(pApi);
        Class classeValidacao = (Class<? extends ItfValidacao>) ReflectionUtils.forName(caminhoCompleto);
        return classeValidacao;
    }
}
