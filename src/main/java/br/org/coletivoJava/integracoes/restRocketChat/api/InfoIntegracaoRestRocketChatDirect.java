package br.org.coletivoJava.integracoes.restRocketChat.api;

import javax.inject.Qualifier;
import br.org.coletivoJava.integracoes.restRocketChat.api.direct.FabApiRestRocketChatV1Direct;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface InfoIntegracaoRestRocketChatDirect {

	FabApiRestRocketChatV1Direct tipo();
}