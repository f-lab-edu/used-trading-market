package org.flab.hyunsb.bootstrap.rest.actortoken;

import static org.flab.hyunsb.bootstrap.config.ActorTokenConfig.ACTOR_ID_ATTRIBUTE;
import static org.flab.hyunsb.bootstrap.config.ActorTokenConfig.ACTOR_TOKEN_HEADER;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.flab.hyunsb.application.usecase.member.ActorTokenAuthUseCase;
import org.flab.hyunsb.bootstrap.rest.exception.ActorTokenException;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@RequiredArgsConstructor
public class ActorTokenAuthInterceptor implements HandlerInterceptor {

    private final ActorTokenAuthUseCase actorTokenAuthUseCase;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler) throws Exception {

        String actorToken = getToken(request);
        Long actorId = actorTokenAuthUseCase.authenticate(actorToken);
        request.setAttribute(ACTOR_ID_ATTRIBUTE, actorId);

        return true;
    }

    private String getToken(HttpServletRequest request) {
        String token = request.getHeader(ACTOR_TOKEN_HEADER);
        if (Strings.isBlank(token)) {
            throw new ActorTokenException("토큰이 존재하지 않음");
        }
        return token;
    }
}
