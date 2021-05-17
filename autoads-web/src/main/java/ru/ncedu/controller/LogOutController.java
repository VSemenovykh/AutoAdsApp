package ru.ncedu.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.ncedu.jwt.JwtUtils;

import java.text.ParseException;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class LogOutController {

    private final JwtUtils jwtUtils;

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    @PostMapping("/logout")
    public ResponseEntity logOut(@RequestParam("token") String token) throws ParseException {
        //log.info(RequestContextHolder.getRequestAttributes().toString());
        return jwtUtils.setNotActiveToken(token);
    }
}
