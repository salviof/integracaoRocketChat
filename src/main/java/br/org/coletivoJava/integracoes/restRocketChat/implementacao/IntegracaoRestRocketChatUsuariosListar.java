package br.org.coletivoJava.integracoes.restRocketChat.implementacao;

import br.org.coletivoJava.integracoes.restRocketChat.api.InfoIntegracaoRestRocketChatUsers;
import br.org.coletivoJava.integracoes.restRocketChat.api.users.FabApiRestRokcetChatV1Users;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.AcaoApiIntegracaoAbstrato;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteRest;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;

@InfoIntegracaoRestRocketChatUsers(tipo = FabApiRestRokcetChatV1Users.USUARIOS_LISTAR)
public class IntegracaoRestRocketChatUsuariosListar
		extends
			AcaoApiIntegracaoAbstrato {

	public IntegracaoRestRocketChatUsuariosListar(
			final FabTipoAgenteClienteRest pTipoAgente,
			final ItfUsuario pUsuario, final java.lang.Object... pParametro) {
		super(FabApiRestRokcetChatV1Users.USUARIOS_LISTAR, pTipoAgente,
				pUsuario, pParametro);
	}
}