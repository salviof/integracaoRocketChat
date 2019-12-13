/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.SBCore.integracao.testes.geradorCodigo;

import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.ItfFabricaIntegracaoRest;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.UtilSBApiRestClient;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.UtilSBApiRestClientReflexao;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.inject.Qualifier;
import testesFW.geradorDeCodigo.GeradorAnotacaoGenerico;

/**
 *
 * @author sfurbino
 * @since 10/12/2019
 * @version 1.0
 */
public class GeradorApiIntegracaoRest extends GeradorAnotacaoGenerico {

    private final ItfFabricaIntegracaoRest fabricaIntegracao;

    public GeradorApiIntegracaoRest(ItfFabricaIntegracaoRest pFabricaintegracao) {

        super(UtilSBApiRestClientReflexao.getPacoteApi(pFabricaintegracao), UtilSBApiRestClientReflexao.getNomeClasseAnotacao(pFabricaintegracao));
        fabricaIntegracao = pFabricaintegracao;

        getCodigoJava().addAnnotation(Qualifier.class);

        getCodigoJava().addImport(fabricaIntegracao.getClass());

        getCodigoJava().addAnnotation(Documented.class);
        getCodigoJava().addAnnotation(Retention.class).setEnumValue(RetentionPolicy.RUNTIME);
        getCodigoJava().addAnnotation(Target.class).setEnumValue(ElementType.TYPE);
        getCodigoJava().addAnnotationElement().setName("tipo").setType(fabricaIntegracao.getClass());

    }

}
