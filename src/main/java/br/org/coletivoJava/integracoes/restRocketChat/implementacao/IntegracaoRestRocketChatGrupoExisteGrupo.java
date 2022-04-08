package br.org.coletivoJava.integracoes.restRocketChat.implementacao;

import br.org.coletivoJava.integracoes.restRocketChat.api.FabConfigRocketChat;
import br.org.coletivoJava.integracoes.restRocketChat.api.InfoIntegracaoRestRocketChatChannel;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.ConsumoWSExecucao;
import br.org.coletivoJava.integracoes.restRocketChat.api.channel.FabApiRestRocketChatV1Channel;
import com.jayway.restassured.path.json.JsonPath;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringFiltros;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringsCammelCase;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.AcaoApiIntegracaoAbstrato;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteApi;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
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
        String urlRequisicao = super.gerarUrlRequisicao();
        System.out.println("Acessando" + urlRequisicao);
        return urlRequisicao;
    }

    private String codigoGrupo;

    @Override
    public void gerarResposta(ConsumoWSExecucao pConsumoRest) {

        super.gerarResposta(pConsumoRest); //chamada super do metodo ( implementação classe pai)
        if (parametros == null || parametros.length < 1) {
            resposta.addErro("Nenhum parametro foi enviado");
        }
        if (resposta.isSucesso()) {
            JSONObject resp = getResposta().getRespostaComoObjetoJson();
            boolean encontrou = (boolean) resp.get("success");
            if (!encontrou) {
                resposta.addErro("O grupo não foi encontrado");
            }
            codigoGrupo = JsonPath.from(resp.toJSONString()).get("group._id");
            nomeGrupo = JsonPath.from(resp.toJSONString()).get("group.fname");
            urlEmbEmbedded = SBCore.getConfigModulo(FabConfigRocketChat.class).getPropriedade(FabConfigRocketChat.URL_SERVIDOR_ROCKET_CHAT) + "/group/" + nomeGrupo + "?layout=embedded";
            //   String codigoGrupo = getLocalizarCodigoGrupoByNomeOuIdentiicadorUnicoImutavel(parametros[0].toString(), resp);
            //    if (codigoGrupo == null) {
            //    }
        }
    }
    private String urlEmbEmbedded;
    private String nomeGrupo;

    public String getCodigoGrupo() {
        return codigoGrupo;
    }

    public String getUrlEmbEmbedded() {
        return urlEmbEmbedded;
    }

    public String getNomeGrupo() {
        return nomeGrupo;
    }

    public static String gerarIdentificadorGrupo(String pDepartamento, ItfBeanSimples pBeanRelacionado) {
        return UtilSBCoreStringsCammelCase.getCamelByTextoPrimeiraLetraMaiuscula(UtilSBCoreStringFiltros.removeCaracteresEspeciais(pBeanRelacionado.getCampoInstanciadoByAnotacao(FabTipoAtributoObjeto.AAA_NOME).getValor().toString()))
                + "_" + UtilSBCoreStringsCammelCase.getCamelByTextoPrimeiraLetraMaiuscula(pDepartamento) + "_" + pBeanRelacionado.getId();
    }

    public static String extrairIdentificadoGrupo(String pString) {
        int indiceSeparadorID_DEPARTAMENTO = pString.lastIndexOf("_");
        int indiceInicioDepartamento = pString.substring(0, indiceSeparadorID_DEPARTAMENTO - 1).lastIndexOf("_");
        String cofigoDoCliente = pString.substring(pString.lastIndexOf("_") + 1, pString.length());
        String codigoTipoDeGrupo = pString.substring(indiceInicioDepartamento, indiceSeparadorID_DEPARTAMENTO + 1);
        return codigoTipoDeGrupo + cofigoDoCliente;
    }
}
