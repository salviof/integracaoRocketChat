package br.org.coletivoJava.integracoes.restRocketChat.implementacao;

import br.org.coletivoJava.integracoes.restRocketChat.api.InfoIntegracaoRestRocketChat;
import br.org.coletivoJava.integracoes.restRocketChat.api.channel.FabApiRestRocketChatV1Channel;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.AcaoApiIntegracaoAbstrato;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteRest;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;

@InfoIntegracaoRestRocketChat(tipo = FabApiRestRocketChatV1Channel.GRUPO_ADICIONAR_USUARIO)
public class IntegracaoRestRocketChatGrupoAdicionarUsuario
		extends
			AcaoApiIntegracaoAbstrato {

	public IntegracaoRestRocketChatGrupoAdicionarUsuario(
			final FabTipoAgenteClienteRest pTipoAgente,
			final ItfUsuario pUsuario, final java.lang.Object... pParametro) {
		super(FabApiRestRocketChatV1Channel.GRUPO_ADICIONAR_USUARIO,
				pTipoAgente, pUsuario, pParametro);
	}
}