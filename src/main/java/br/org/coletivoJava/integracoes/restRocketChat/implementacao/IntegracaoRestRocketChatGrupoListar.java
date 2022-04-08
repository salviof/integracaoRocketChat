package br.org.coletivoJava.integracoes.restRocketChat.implementacao;

import br.org.coletivoJava.integracoes.restRocketChat.api.InfoIntegracaoRestRocketChatChannel;
import br.org.coletivoJava.integracoes.restRocketChat.api.channel.FabApiRestRocketChatV1Channel;
import static br.org.coletivoJava.integracoes.restRocketChat.implementacao.IntegracaoRestRocketChatGrupoExisteGrupo.extrairIdentificadoGrupo;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.AcaoApiIntegracaoAbstrato;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteApi;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import java.util.Optional;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@InfoIntegracaoRestRocketChatChannel(tipo = FabApiRestRocketChatV1Channel.GRUPO_LISTAR)
public class IntegracaoRestRocketChatGrupoListar
        extends
        AcaoApiIntegracaoAbstrato {

    public IntegracaoRestRocketChatGrupoListar(
            final FabTipoAgenteClienteApi pTipoAgente,
            final ItfUsuario pUsuario, final java.lang.Object... pParametro) {
        super(FabApiRestRocketChatV1Channel.GRUPO_LISTAR, pTipoAgente,
                pUsuario, pParametro);
    }

    protected static String getLocalizarAtributoDeGrupoByNomeOuIdentiicadorUnicoImutavel(String nomeOuIdentificador, JSONObject respostaApiPesquisaGrupo, String pAtributo) {
        JSONArray grupos = (JSONArray) respostaApiPesquisaGrupo.get("groups");
        if (grupos != null) {
            System.out.println("Encontrados" + grupos.size() + "grupos");
        }
        Optional<JSONObject> grupoEncontrado = grupos.stream().filter(gp -> ((JSONObject) gp).get("fname").toString().equals(nomeOuIdentificador)).findFirst();
        if (grupoEncontrado.isPresent()) {
            return grupoEncontrado.get().get(pAtributo).toString();
        }
        if (!SBCore.isEmModoProducao()) {
            System.out.println("Não encontrou grupo com nome" + nomeOuIdentificador);
            System.out.println("NomesRecebidos");
            grupos.stream().forEach(gp -> System.out.println(((JSONObject) gp).get("fname")));
        }
        String identiicadorUnicoImutavel = extrairIdentificadoGrupo(nomeOuIdentificador);

        if (identiicadorUnicoImutavel != null) {
            Optional<JSONObject> grupoEncontradoFinalIdentificador = grupos.stream().filter(gp -> ((JSONObject) gp).get("fname").toString().endsWith(identiicadorUnicoImutavel)).findFirst();
            if (grupoEncontradoFinalIdentificador.isPresent()) {
                return grupoEncontradoFinalIdentificador.get().get(pAtributo).toString();
            }
        }
        System.out.println("Não encontrou grupo com identificador" + identiicadorUnicoImutavel);
        return null;
    }

    public static String getLocalizarCodigoGrupoByNomeOuIdentiicadorUnicoImutavel(String nomeOuIdentificador, JSONObject respostaApiPesquisaGrupo) {
        return getLocalizarAtributoDeGrupoByNomeOuIdentiicadorUnicoImutavel(nomeOuIdentificador, respostaApiPesquisaGrupo, "_id");
    }

    public static String getLocalizarNomeAmigavelGrupoByNomeOuIdentiicadorUnicoImutavel(String nomeOuIdentificador, JSONObject respostaApiPesquisaGrupo) {
        return getLocalizarAtributoDeGrupoByNomeOuIdentiicadorUnicoImutavel(nomeOuIdentificador, respostaApiPesquisaGrupo, "fname");
    }

    public static String getLocalizarIdRCGrupoByNomeOuIdentiicadorUnicoImutavel(String nomeOuIdentificador, JSONObject respostaApiPesquisaGrupo) {
        return getLocalizarAtributoDeGrupoByNomeOuIdentiicadorUnicoImutavel(nomeOuIdentificador, respostaApiPesquisaGrupo, "_id");
    }

}
