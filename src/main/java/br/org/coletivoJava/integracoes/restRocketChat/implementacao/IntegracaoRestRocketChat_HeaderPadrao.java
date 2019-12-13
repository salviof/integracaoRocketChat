package br.org.coletivoJava.integracoes.restRocketChat.implementacao;

import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.AcaoApiIntegracaoHeaderBuilder;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.transmissao_recepcao_rest_client.ItfAcaoApiRest;

public class IntegracaoRestRocketChat_HeaderPadrao
        extends
        AcaoApiIntegracaoHeaderBuilder {

    public IntegracaoRestRocketChat_HeaderPadrao(final ItfAcaoApiRest pAcao) {
        super(pAcao);
    }

    @Override
    public void buildHeaderPadrao() {
        super.buildHeaderPadrao(); //chamada super do metodo (implementação classe pai)
        cabecalho.put("X-Auth-Token", acao.getTokenSistemaGestao().getToken());
        cabecalho.put("X-User-Id", "8Svp8giySfzSAkFeR");
    }

}
