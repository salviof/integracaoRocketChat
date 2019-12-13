/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.token;

import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.oauth.FabStatusToken;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;

/**
 * @author sfurbino
 * @since 12/12/2019
 * @version 1.0
 */
public interface ItfTokenGestao {

    public String getTokenUsuario(ItfUsuario pUsuario);

    public String getTokenSistema();

    public String getTokenUsuarioLogado();

    public boolean isTemTokemAtivoSistema();

    public boolean isTemTokenAtivoUsuarioLogado();

    public boolean istemTokemAtivoUsuario(ItfUsuario pUsuario);

    public boolean gerarNovoToken(ItfUsuario pUsuario);

    public boolean gerarNovoTokenSistema();

    public boolean excluirToken();

    public boolean isPossuiAutenticacaoDeUsuario();

    public FabStatusToken getStatusToken();

}
