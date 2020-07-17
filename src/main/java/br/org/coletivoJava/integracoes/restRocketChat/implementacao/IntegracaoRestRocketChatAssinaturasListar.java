package br.org.coletivoJava.integracoes.restRocketChat.implementacao;

import br.org.coletivoJava.integracoes.restRocketChat.api.InfoIntegracaoRestRocketChatUsers;
import br.org.coletivoJava.integracoes.restRocketChat.api.users.FabApiRestRokcetChatV1Users;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.ConsumoWSExecucao;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.AcaoApiIntegracaoAbstrato;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteRest;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@InfoIntegracaoRestRocketChatUsers(tipo = FabApiRestRokcetChatV1Users.ASSINATURAS_LISTAR)
public class IntegracaoRestRocketChatAssinaturasListar
        extends
        AcaoApiIntegracaoAbstrato {

    public IntegracaoRestRocketChatAssinaturasListar(
            final FabTipoAgenteClienteRest pTipoAgente,
            final ItfUsuario pUsuario, final java.lang.Object... pParametro) {
        super(FabApiRestRokcetChatV1Users.ASSINATURAS_LISTAR, pTipoAgente,
                pUsuario, pParametro);
    }

    @Override
    public String gerarUrlRequisicao() {
        String url = super.gerarUrlRequisicao(); //To change body of generated methods, choose Tools | Templates.
        url = url + "?updatedSince=2017-11-25T15:08:17.248Z";
        return url;
    }

    private int naoLidas;

    @Override
    public void gerarResposta(ConsumoWSExecucao pConsumoRest) {

        super.gerarResposta(pConsumoRest);

        if (!resposta.isSucesso()) {
            resposta.addErro("Falha obetendo resposta");

        } else {
            JSONArray grupos = (JSONArray) resposta.getJsonObj().get("update");
            for (Iterator it = grupos.iterator(); it.hasNext();) {

                try {
                    JSONObject obj = (JSONObject) it.next();
                    Long valor = (long) obj.get("unread");
                    naoLidas = naoLidas + valor.intValue();
                } catch (Throwable t) {

                }
            }
        }

    }

    public int getNaoLidas() {
        return naoLidas;
    }
}
