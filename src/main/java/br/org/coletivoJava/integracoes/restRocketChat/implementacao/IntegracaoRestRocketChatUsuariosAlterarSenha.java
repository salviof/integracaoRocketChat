package br.org.coletivoJava.integracoes.restRocketChat.implementacao;

import br.org.coletivoJava.integracoes.restRocketChat.api.InfoIntegracaoRestRocketChatUsers;
import br.org.coletivoJava.integracoes.restRocketChat.api.users.FabApiRestRokcetChatV1Users;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.AcaoApiIntegracaoAbstrato;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteApi;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;

@InfoIntegracaoRestRocketChatUsers(tipo = FabApiRestRokcetChatV1Users.USUARIOS_ALTERAR_SENHA)
public class IntegracaoRestRocketChatUsuariosAlterarSenha
        extends
        AcaoApiIntegracaoAbstrato {

    public IntegracaoRestRocketChatUsuariosAlterarSenha(
            final FabTipoAgenteClienteApi pTipoAgente,
            final ItfUsuario pUsuario, final java.lang.Object... pParametro) {
        super(FabApiRestRokcetChatV1Users.USUARIOS_ALTERAR_SENHA, pTipoAgente,
                pUsuario, pParametro);
    }

    @Override
    public String gerarCorpoRequisicao() {
        if (getQuantidadeParametrosEnviados() != 2) {
            throw new UnsupportedOperationException("Esperado 2 parametros código do usuário e senha");
        }
        String corpo = "{\"userId\": \"" + parametros.get(0) + "\","
                + " \"data\": { \"password\": \"" + parametros.get(1) + "\"}}";
        return corpo;

    }
}
