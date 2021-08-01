package br.org.coletivoJava.integracoes.restRocketChat.implementacao;

import br.org.coletivoJava.integracoes.restRocketChat.api.InfoIntegracaoRestRocketChatChannel;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.ConsumoWSExecucao;
import br.org.coletivoJava.integracoes.restRocketChat.api.channel.FabApiRestRocketChatV1Channel;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringFiltros;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringsCammelCase;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.AcaoApiIntegracaoAbstrato;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteApi;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
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
            final FabTipoAgenteClienteApi pTipoAgente,
            final ItfUsuario pUsuario, final java.lang.Object... pParametro) {
        super(FabApiRestRocketChatV1Channel.GRUPO_EXISTE_GRUPO, pTipoAgente,
                pUsuario, pParametro);
    }

    @Override
    public String gerarUrlRequisicao() {
        String urlRequisicao = super.gerarUrlRequisicao() + "?count=5000"; //To change body of generated methods, choose Tools | Templates.
        System.out.println("Acessando" + urlRequisicao);
        return urlRequisicao;
    }

    @Override
    public void gerarResposta(ConsumoWSExecucao pConsumoRest) {

        super.gerarResposta(pConsumoRest); //chamada super do metodo ( implementação classe pai)
        if (parametros == null || parametros.length < 1) {
            resposta.addErro("Nenhum parametro foi enviado");
        }
        if (resposta.isSucesso()) {
            JSONObject resp = getResposta().getRespostaComoObjetoJson();

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

    public static String getLocalizarIdRCGrupoByNomeOuIdentiicadorUnicoImutavel(String nomeOuIdentificador, JSONObject respostaApiPesquisaGrupo) {
        return getLocalizarAtributoDeGrupoByNomeOuIdentiicadorUnicoImutavel(nomeOuIdentificador, respostaApiPesquisaGrupo, "_id");
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

    public static String gerarIdentificadorGrupo(String pDepartamento, ItfBeanSimples pBeanRelacionado) {
        return UtilSBCoreStringsCammelCase.getCamelByTextoPrimeiraLetraMaiuscula(UtilSBCoreStringFiltros.removeCaracteresEspeciais(pBeanRelacionado.getCampoInstanciadoByAnotacao(FabTipoAtributoObjeto.AAA_NOME).getValor().toString())) + "_" + UtilSBCoreStringsCammelCase.getCamelByTextoPrimeiraLetraMaiuscula(pDepartamento) + "_" + pBeanRelacionado.getId();
    }

    public static String extrairIdentificadoGrupo(String pString) {
        int indiceSeparadorID_DEPARTAMENTO = pString.lastIndexOf("_");
        int indiceInicioDepartamento = pString.substring(0, indiceSeparadorID_DEPARTAMENTO - 1).lastIndexOf("_");
        String cofigoDoCliente = pString.substring(pString.lastIndexOf("_") + 1, pString.length());
        String codigoTipoDeGrupo = pString.substring(indiceInicioDepartamento, indiceSeparadorID_DEPARTAMENTO + 1);
        return codigoTipoDeGrupo + cofigoDoCliente;
    }
}
