package br.org.coletivoJava.integracoes.restRocketChat.implementacao;

import br.org.coletivoJava.integracoes.restRocketChat.api.InfoIntegracaoRestRocketChatUsers;
import br.org.coletivoJava.integracoes.restRocketChat.api.users.FabApiRestRokcetChatV1Users;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.ConsumoWSExecucao;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.AcaoApiIntegracaoAbstrato;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteApi;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@InfoIntegracaoRestRocketChatUsers(tipo = FabApiRestRokcetChatV1Users.USUARIOS_ENCONTRAR_POR_EMAIL)
public class IntegracaoRestRocketChatUsuariosEncontrarPorEmail
        extends
        AcaoApiIntegracaoAbstrato {

    public IntegracaoRestRocketChatUsuariosEncontrarPorEmail(
            final FabTipoAgenteClienteApi pTipoAgente,
            final ItfUsuario pUsuario, final java.lang.Object... pParametro) {
        super(FabApiRestRokcetChatV1Users.USUARIOS_ENCONTRAR_POR_EMAIL,
                pTipoAgente, pUsuario, pParametro);
    }

    @Override
    public String gerarUrlRequisicao() {
        if (getQuantidadeParametrosEnviados() != 1) {
            throw new UnsupportedOperationException("Esperado um parametro para criação do grupo (Parametro nome)");
        }
        String urlPadrao = super.gerarUrlRequisicao();
        String urlChamadaComParametro = urlPadrao + "?query=" + encodeValue("{\"emails\":{\"$elemMatch\": {\"address\" : {\"$eq\":\"" + getParametros()[0] + "\"}}}}");

        String urlCodificado = encodeValue(urlChamadaComParametro);
        return urlChamadaComParametro;
    }

    @Override
    public void gerarResposta(ConsumoWSExecucao pConsumoRest) {
        super.gerarResposta(pConsumoRest); //To change body of generated methods, choose Tools | Templates.
        if (resposta.isSucesso()) {
            if (getResposta() != null) {

                if (!getResposta().getRespostaTexto().contains(getParametros()[0].toString())) {
                    getResposta().addErro("Usuário não encontrado");
                }
            }
        }

    }

    private String encodeValue(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex.getCause());
        }
    }

}
