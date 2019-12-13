package br.org.coletivoJava.integracoes.restRocketChat.api;

import javax.inject.Qualifier;
import com.super_bits.modulosSB.SBCore.integracao.rocketChat.api.channel.FabApiRestRocketChatV1Channel;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface InfoIntegracaoRestRocketChat {

	FabApiRestRocketChatV1Channel tipo();
}