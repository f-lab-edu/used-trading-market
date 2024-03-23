package org.flab.hyunsb.bootstrap.config;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.flab.hyunsb.application.usecase.member.ActorTokenAuthUseCase;
import org.flab.hyunsb.bootstrap.rest.actortoken.ActorTokenAuthInterceptor;
import org.flab.hyunsb.bootstrap.rest.actortoken.LoginActorArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Configuration
public class ActorTokenConfig implements WebMvcConfigurer {

    public static final String ACTOR_TOKEN_HEADER = "Authorization";
    public static final String ACTOR_ID_ATTRIBUTE = "actorId";

    private final ActorTokenAuthUseCase actorTokenAuthUseCase;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ActorTokenAuthInterceptor(actorTokenAuthUseCase))
            .order(1)
            .addPathPatterns("/**")
            .excludePathPatterns("/error", "/api/v1/members/login", "/api/v1/members");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginActorArgumentResolver());
    }
}
