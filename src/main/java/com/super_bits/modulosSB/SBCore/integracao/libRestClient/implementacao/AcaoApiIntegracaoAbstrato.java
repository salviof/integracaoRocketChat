/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao;

import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.gestaoToken.MapaTokensGerenciados;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.gestaoToken.GestaoTokenOath2;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.ItfFabricaIntegracaoRest;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteRest;

/**
 *
 * @author SalvioF
 */
public abstract class AcaoApiIntegracaoAbstrato extends AcaoApiIntegracaoAbstratoBasico {

    public AcaoApiIntegracaoAbstrato(ItfFabricaIntegracaoRest pIntegracaoEndpoint, Object... parametros) {
        this(pIntegracaoEndpoint, false, parametros);

    }

    /**
     *
     * @param pEndpointIntegracaoRest
     * @param pModificarPropriedades Permitie modificar prorpiedades antes da
     * conexao (não executa conexção imediata..)
     * @param pParametros
     */
    public AcaoApiIntegracaoAbstrato(ItfFabricaIntegracaoRest pEndpointIntegracaoRest, boolean pModificarPropriedades, Object... pParametros) {
        super(pEndpointIntegracaoRest, pParametros);

    }

    @Override
    public String gerarTokenAcesso() {

        MapaTokensGerenciados.registrarAutenticador(new GestaoTokenOath2(fabricaIntegracao.getFabricaConfiguracao(), FabTipoAgenteClienteRest.SISTEMA, fabricaIntegracao.getClass()), fabricaIntegracao);
        return MapaTokensGerenciados.getAutenticadorSistemaAtual(fabricaIntegracao).getTokenDeAcesso().getTokenValido();

    }

}
