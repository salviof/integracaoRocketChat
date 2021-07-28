package br.org.coletivoJava.integracoes.restRocketChat.implementacao;

import br.org.coletivoJava.integracoes.restRocketChat.api.InfoIntegracaoRestRocketChatUsers;
import br.org.coletivoJava.integracoes.restRocketChat.api.users.FabApiRestRokcetChatV1Users;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.AcaoApiIntegracaoAbstrato;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteApi;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;

@InfoIntegracaoRestRocketChatUsers(tipo = FabApiRestRokcetChatV1Users.USUARIOS_CRIAR)
public class IntegracaoRestRocketChatUsuariosCriar
        extends
        AcaoApiIntegracaoAbstrato {

    public IntegracaoRestRocketChatUsuariosCriar(
            final FabTipoAgenteClienteApi pTipoAgente,
            final ItfUsuario pUsuario, final java.lang.Object... pParametro) {
        super(FabApiRestRokcetChatV1Users.USUARIOS_CRIAR, pTipoAgente,
                pUsuario, pParametro);
    }

    @Override
    public String gerarCorpoRequisicao() {
        if (getQuantidadeParametrosEnviados() != 4) {
            throw new UnsupportedOperationException("Esperado 4 parametros para criação do usuário");
        }
        String corpo = "{\"name\": \"" + getParametros()[0] + "\", \"email\": \"" + getParametros()[1]
                + "\", \"password\": \"" + getParametros()[2] + "\", \"username\": \"" + getParametros()[3] + "\"}";
        return corpo;

    }

}
