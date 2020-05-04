package br.org.coletivoJava.integracoes.restRocketChat.implementacao;

import br.org.coletivoJava.integracoes.restRocketChat.api.InfoIntegracaoRestRocketChatChannel;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.ConsumoWSExecucao;
import br.org.coletivoJava.integracoes.restRocketChat.api.channel.FabApiRestRocketChatV1Channel;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringFiltros;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringsCammelCase;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.AcaoApiIntegracaoAbstrato;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteRest;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import java.util.Optional;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@InfoIntegracaoRestRocketChatChannel(tipo = FabApiRestRocketChatV1Channel.GRUPO_EXISTE_GRUPO)
public class IntegracaoRestRocketChatGrupoExisteGrupo
        extends
        AcaoApiIntegracaoAbstrato {

    public IntegracaoRestRocketChatGrupoExisteGrupo(
            final FabTipoAgenteClienteRest pTipoAgente,
            final ItfUsuario pUsuario, final java.lang.Object... pParametro) {
        super(FabApiRestRocketChatV1Channel.GRUPO_EXISTE_GRUPO, pTipoAgente,
                pUsuario, pParametro);
    }

    @Override
    public void gerarResposta(ConsumoWSExecucao pConsumoRest) {

        super.gerarResposta(pConsumoRest); //chamada super do metodo ( implementação classe pai)
        if (parametros == null || parametros.length < 1) {
            resposta.addErro("Nenhum parametro foi enviado");
        }
        if (resposta.isSucesso()) {
            JSONObject resp = resposta.getJsonObj();

            String codigoGrupo = getLocalizarCodigoGrupoByNomeOuIdentiicadorUnicoImutavel(parametros[0].toString(), resp);
            if (codigoGrupo == null) {
                resposta.addErro("O grupo não foi encontrado");
            }

        }
    }

    public static String getLocalizarCodigoGrupoByNomeOuIdentiicadorUnicoImutavel(String nomeOuIdentificador, JSONObject respostaApiPesquisaGrupo) {
        return getLocalizarAtributoDeGrupoByNomeOuIdentiicadorUnicoImutavel(nomeOuIdentificador, respostaApiPesquisaGrupo, "_id");
    }

    public static String getLocalizarNomeAmigavelGrupoByNomeOuIdentiicadorUnicoImutavel(String nomeOuIdentificador, JSONObject respostaApiPesquisaGrupo) {
        return getLocalizarAtributoDeGrupoByNomeOuIdentiicadorUnicoImutavel(nomeOuIdentificador, respostaApiPesquisaGrupo, "fname");
    }

    protected static String getLocalizarAtributoDeGrupoByNomeOuIdentiicadorUnicoImutavel(String nomeOuIdentificador, JSONObject respostaApiPesquisaGrupo, String pAtributo) {
        JSONArray grupos = (JSONArray) respostaApiPesquisaGrupo.get("groups");
        Optional<JSONObject> grupoEncontrado = grupos.stream().filter(gp -> ((JSONObject) gp).get("fname").toString().equals(nomeOuIdentificador)).findFirst();
        if (grupoEncontrado.isPresent()) {
            return grupoEncontrado.get().get(pAtributo).toString();
        }

        String identiicadorUnicoImutavel = extrairIdentificadoGrupo(nomeOuIdentificador);
        if (identiicadorUnicoImutavel != null) {
            Optional<JSONObject> grupoEncontradoFinalIdentificador = grupos.stream().filter(gp -> ((JSONObject) gp).get("fname").toString().endsWith(identiicadorUnicoImutavel)).findFirst();
            if (grupoEncontradoFinalIdentificador.isPresent()) {
                return grupoEncontradoFinalIdentificador.get().get(pAtributo).toString();
            }
        }

        return null;
    }

    public static String gerarIdentificadorGrupo(String pDepartamento, ItfBeanSimples pBeanRelacionado) {
        return UtilSBCoreStringsCammelCase.getCamelByTextoPrimeiraLetraMaiuscula(UtilSBCoreStringFiltros.removeCaracteresEspeciais(pBeanRelacionado.getNome())) + "_" + UtilSBCoreStringsCammelCase.getCamelByTextoPrimeiraLetraMaiuscula(pDepartamento) + "_" + pBeanRelacionado.getId();
    }

    public static String extrairIdentificadoGrupo(String pString) {
        int indiceSeparadorID_DEPARTAMENTO = pString.lastIndexOf("_");
        int indiceInicioDepartamento = pString.substring(0, indiceSeparadorID_DEPARTAMENTO).lastIndexOf("_");
        return pString.substring(indiceInicioDepartamento, indiceSeparadorID_DEPARTAMENTO + 2);
    }
}
