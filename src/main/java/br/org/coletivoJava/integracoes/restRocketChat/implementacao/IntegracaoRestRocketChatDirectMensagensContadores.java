package br.org.coletivoJava.integracoes.restRocketChat.implementacao;

import br.org.coletivoJava.integracoes.restRocketChat.api.InfoIntegracaoRestRocketChatUsers;
import br.org.coletivoJava.integracoes.restRocketChat.api.users.FabApiRestRokcetChatV1Users;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.ConsumoWSExecucao;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.AcaoApiIntegracaoAbstrato;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteApi;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@InfoIntegracaoRestRocketChatUsers(tipo = FabApiRestRokcetChatV1Users.DIRECT_MENSAGENS_CONTADORES)
public class IntegracaoRestRocketChatDirectMensagensContadores
        extends
        AcaoApiIntegracaoAbstrato {

    public IntegracaoRestRocketChatDirectMensagensContadores(
            final FabTipoAgenteClienteApi pTipoAgente,
            final ItfUsuario pUsuario, final java.lang.Object... pParametro) {
        super(FabApiRestRokcetChatV1Users.DIRECT_MENSAGENS_CONTADORES,
                pTipoAgente, pUsuario, pParametro);
    }

}
