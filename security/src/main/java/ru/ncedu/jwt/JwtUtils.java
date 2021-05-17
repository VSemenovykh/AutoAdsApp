package ru.ncedu.jwt;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import ru.ncedu.auth.response.MessageResponse;
import ru.ncedu.model.ExpiredTokenKey;
import ru.ncedu.services.UserDetailsImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Slf4j
@Component
public class JwtUtils {

    @Value("${autoads.app.jwtSecret}")
    private String jwtSecret;
    @Value("${autoads.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    private Map<ExpiredTokenKey, String> mapNotActiveJWT = new HashMap<>();

    public String generateJwtToken(Authentication authentication) {

        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder().setSubject((userPrincipal.getUsername())).setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs)).signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public Date getExpTimeFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getExpiration();
    }

    public boolean validateJwtToken(String authToken) {

        try {
            log.info("validateJwtToken before signing: {}", authToken);
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            /*Check token user, where authentication*/
            log.info("validateJwtToken after signing: {}", authToken);
            return !mapNotActiveJWT.containsValue(authToken);

        } catch (SignatureException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }

    public ResponseEntity setNotActiveToken(String token) {
        String userName = getUserNameFromJwtToken(token);
        long expTime = getExpTimeFromJwtToken(token).toInstant().toEpochMilli();

        String formattedDate = new SimpleDateFormat("ms", Locale.US).format(expTime);
        log.info("Time not active token: " + formattedDate);

        ExpiredTokenKey expiredTokenKey = ExpiredTokenKey.builder()
                .userName(userName)
                .exp(expTime)
                .build();
        mapNotActiveJWT.put(expiredTokenKey, token);
        log.info("Saving token into map: {}", token);

        return new ResponseEntity<>(new MessageResponse("token kill"), HttpStatus.OK);
    }

    @Scheduled(cron = "*/30 * * * * *")
    public void cleaningFromMapNotActiveJWT() {
        Date currentTime = new Date();
        String formattedCurrentTime = new SimpleDateFormat("ms", Locale.US).format(currentTime);
        String formattedNotActiveTime;
        log.info("Time cleaning tokens from map not active JWT");

        for (ExpiredTokenKey expiredTokenKey : mapNotActiveJWT.keySet()) {
            if (expiredTokenKey.getExp() < new Date().toInstant().toEpochMilli()) {
                log.info("Removing the JWT with expiration time = {} since it's already expired", new Date(expiredTokenKey.getExp()));
                mapNotActiveJWT.remove(expiredTokenKey);
            }
        }
    }
}
