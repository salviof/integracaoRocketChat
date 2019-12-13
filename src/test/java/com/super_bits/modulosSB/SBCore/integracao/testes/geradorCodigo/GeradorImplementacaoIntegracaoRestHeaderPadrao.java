/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.SBCore.integracao.testes.geradorCodigo;

import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.ItfFabricaIntegracaoRest;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.transmissao_recepcao_rest_client.ItfAcaoApiRest;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.AcaoApiIntegracaoHeaderBuilder;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.UtilSBApiRestClientReflexao;
import java.util.HashMap;
import java.util.Map;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.MethodSource;
import testesFW.geradorDeCodigo.GeradorClasseGenerico;

/**
 *
 * @author sfurbino
 * @since 11/12/2019
 * @version 1.0
 */
public class GeradorImplementacaoIntegracaoRestHeaderPadrao extends GeradorClasseGenerico {

    public GeradorImplementacaoIntegracaoRestHeaderPadrao(ItfFabricaIntegracaoRest pIntegracao) {
        super(UtilSBApiRestClientReflexao.getPacoteImplementacao(pIntegracao), UtilSBApiRestClientReflexao.getNomeClasseImplementacaoGestaoHeaderPadrao(pIntegracao));
        //    ERPCodigoPostalBR Class
        getCodigoJava().setSuperType(AcaoApiIntegracaoHeaderBuilder.class);

        MethodSource<JavaClassSource> constructor = getCodigoJava().addMethod().setPublic().setConstructor(true);
        constructor.addParameter(ItfAcaoApiRest.class, "pAcao").setFinal(true);
        constructor.setBody("super(pAcao);");

    }

}
