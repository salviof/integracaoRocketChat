/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.coletivoJava.integracoes.restRocketChat.implementacao;

import br.org.coletivoJava.integracoes.restRocketChat.api.channel.FabApiRestRocketChatV1Channel;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.RespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.rocketChat.implementacaoRCRest.ConfigCoreRCTestesRegraNegocio;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico.UsuarioSistemaRoot;
import org.junit.Test;

/**
 *
 * @author sfurbino
 */
public class IntegracaoRestRocketChatGrupoExisteGrupoTest {

    /**
     * Test of gerarResposta method, of class
     * IntegracaoRestRocketChatGrupoExisteGrupo.
     */
    @Test
    public void testGerarResposta() {

        SBCore.configurar(new ConfigCoreRCTestesRegraNegocio(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        GestaoTokenRestRocketChat autenticacaoSistema = (GestaoTokenRestRocketChat) FabApiRestRocketChatV1Channel.QUEM_SOU_EU.getGestaoToken();
        String tokenSystem = autenticacaoSistema.gerarNovoToken();
        IntegracaoRestRocketChatGrupoExisteGrupo teste;
        RespostaWebServiceSimples respGrupo = FabApiRestRocketChatV1Channel.GRUPO_EXISTE_GRUPO.getAcao("EmpresaXQueVaiFecharContradoNomeDaEmpresa_Vendas_1").getResposta();
        System.out.println(respGrupo.isSucesso());
        System.out.println(respGrupo.getResposta());
        System.out.println(IntegracaoRestRocketChatGrupoExisteGrupo.extrairIdentificadoGrupo("EmpresaXQueVaiFecharContradoNomeDaEmpresa_Vendas_1"));
        System.out.println(IntegracaoRestRocketChatGrupoExisteGrupo.gerarIdentificadorGrupo("Vendas", new UsuarioSistemaRoot()));
    }

}
