/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ConfigModulo;
import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ItfFabConfigModulo;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.InfoConsumoRestService;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.ItfApiServicoTokenCliente;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.servicoRegistrado.InfoConfigRestClientIntegracao;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.token.ItfTokenGestaoBasico;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.transmissao_recepcao_rest_client.ItfAcaoApiRest;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.UtilSBApiRestClient;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;

/**
 *
 * Fabrica pronta para ser utilizada com o consumo de webservice forncido pelo
 * sistema de integração
 *
 *
 * @author desenvolvedor
 * @param <T>
 */
public interface ItfFabricaIntegracaoRest<T extends ItfFabConfigModulo> {

    public default InfoConsumoRestService getInformacoesConsumo() {
        return UtilSBApiRestClient.getInformacoesConsumoRest(this);
    }

    public ItfApiServicoTokenCliente getApiTokenAcesso();

    public default ItfAcaoApiRest getAcao(Object... parametros) {
        return UtilSBApiRestClient.getAcaoDoContexto(this, parametros);
    }

    public default ItfAcaoApiRest getAcao(ItfUsuario pUsuario, Object... parametros) {
        return UtilSBApiRestClient.getAcaoDoContexto(this, parametros);
    }

    public default ItfAcaoApiRest getAcaoUsuarioLogado(ItfUsuario pUsuario, Object... parametros) {
        return null;
    }

    public default ItfTokenGestaoBasico getGestaoToken() {
        return null;
    }

    public Class<? extends T> getFabricaConfiguracao();

    public default ConfigModulo getConfiguracao() {
        return SBCore.getConfigModulo(getFabricaConfiguracao());
    }

    public default InfoConfigRestClientIntegracao getDadosIntegracao() {
        return UtilSBApiRestClient.getInfoConfigRest(this);
    }

}
