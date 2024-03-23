package org.flab.hyunsb.application.service;

import static org.flab.hyunsb.application.exception.message.ActorTokenErrorMessage.TOKEN_AUTHENTICATION_FAILED;
import static org.flab.hyunsb.application.exception.message.ActorTokenErrorMessage.TOKEN_EXPIRED;
import static org.flab.hyunsb.application.exception.message.ActorTokenErrorMessage.TOKEN_FORMAT_INVALID;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import javax.crypto.SecretKey;
import org.flab.hyunsb.application.exception.authentication.ActorTokenInvalidException;
import org.flab.hyunsb.application.usecase.member.ActorTokenAuthUseCase;
import org.flab.hyunsb.application.util.DateGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ActorTokenService implements ActorTokenAuthUseCase {

    private static final long EXP = 1000L * 60 * 60 * 48;
    private static final String TOKEN_PREFIX = "Bearer";
    private static final String TOKEN_DELIMITER = " ";

    private final DateGenerator dateGenerator;
    private final SecretKey secretKey;

    public ActorTokenService(DateGenerator dateGenerator, @Value("${jwt.secretKey}") String key) {
        this.dateGenerator = dateGenerator;
        this.secretKey = Keys.hmacShaKeyFor(key.getBytes());
    }

    public String createActorToken(Long actorId) {
        String token = Jwts.builder()
            .subject(String.valueOf(actorId))
            .expiration(dateGenerator.getExpireDate(EXP))
            .signWith(secretKey)
            .compact();

        return String.join(TOKEN_DELIMITER, TOKEN_PREFIX, token);
    }

    @Override
    public Long authenticate(String token) {
        validateFormat(token);
        return readToken(token);
    }

    private void validateFormat(String token) {
        if (!token.startsWith(TOKEN_PREFIX)) {
            throw new ActorTokenInvalidException(TOKEN_FORMAT_INVALID);
        }
    }

    private Long readToken(String token) {
        Claims payload = parsePayload(token);
        validateExpirationDate(payload.getExpiration());

        return Long.parseLong(payload.getSubject());
    }

    private Claims parsePayload(String token) {
        try {
            String originToken = token.replace(TOKEN_PREFIX, "").trim();
            return Jwts.parser().verifyWith(secretKey).build()
                .parseSignedClaims(originToken).getPayload();
        } catch (JwtException exception) {
            throw new ActorTokenInvalidException(TOKEN_AUTHENTICATION_FAILED);
        }
    }

    private void validateExpirationDate(Date expirationDate) {
        Date currentDate = dateGenerator.getCurrentDate();
        if (currentDate.after(expirationDate)) {
            throw new ActorTokenInvalidException(TOKEN_EXPIRED);
        }
    }
}
