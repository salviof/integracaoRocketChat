/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.SBCore.integracao.testes.geradorCodigo;

import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.ItfFabricaIntegracaoRest;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.servicoRegistrado.InfoConfigRestClientIntegracao;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.UtilSBApiRestClientReflexao;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.gestaoToken.GestaoTokenChaveUnica;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.gestaoToken.GestaoTokenOath2;
import org.jboss.forge.roaster.model.source.AnnotationSource;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.ParameterSource;
import testesFW.geradorDeCodigo.GeradorClasseGenerico;

/**
 *
 * @author sfurbino
 * @since 13/12/2019
 * @version 1.0
 */
public class GeradorGestaoTokenAcessoIntegracaoRest extends GeradorClasseGenerico {

    public GeradorGestaoTokenAcessoIntegracaoRest(ItfFabricaIntegracaoRest pIntegracao) {
        super(UtilSBApiRestClientReflexao.getPacoteImplementacao(pIntegracao), UtilSBApiRestClientReflexao.getNomeClasseImplementacao(pIntegracao));
        //    ERPCodigoPostalBR Class
        getCodigoJava().addImport(UtilSBApiRestClientReflexao.getClasseAnotacao(pIntegracao));
        AnnotationSource<JavaClassSource> anotacao = getCodigoJava().addAnnotation(UtilSBApiRestClientReflexao.getClasseAnotacao(pIntegracao));
        //anotacao.setStringValue("tipo", pIntegracao.toString());
        anotacao.setEnumValue("tipo", (Enum) pIntegracao);
        InfoConfigRestClientIntegracao dadosIndegracao = pIntegracao.getDadosIntegracao();
        switch (dadosIndegracao.tipoAutenticacao()) {
            case OAUTHV1:
            case OAUTHV2:
                getCodigoJava().extendSuperType(GestaoTokenOath2.class);
                break;
            case USUARIO_SENHA_SIMPLES:
            case CHAVE_ACESSO_METODOLOGIA_PROPRIA:
            case CHAVE_PUBLICA_PRIVADA:

            default:
                getCodigoJava().extendSuperType(GestaoTokenChaveUnica.class);

        }

        ParameterSource<JavaClassSource> p = getCodigoJava().addMethod().setPublic().setConstructor(true)
                .setBody("super(" + pIntegracao.getClass().getSimpleName() + "." + pIntegracao.toString() + "" + ", pParametro);")
                .addParameter(Object.class, "pParametro").setVarArgs(true).setFinal(true);
    }

}
