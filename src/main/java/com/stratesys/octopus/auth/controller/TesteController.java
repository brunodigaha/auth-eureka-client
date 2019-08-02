package com.stratesys.octopus.auth.controller;

import com.stratesys.octopus.auth.config.AuthenticationCurrentUserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
public class TesteController {

    @Autowired
    AuthenticationCurrentUserService authenticationCurrentUserService;


    @PreAuthorize("hasAuthority('bruno2')")
    @GetMapping("/teste")
    public String teste(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request) throws InterruptedException {
//        TimeUnit.MILLISECONDS.sleep( 1500);
        System.out.println("passou");

        System.out.println(authenticationCurrentUserService.getClientId());
        System.out.println(authenticationCurrentUserService.getAuthentication());
        Authentication authentication = (Authentication) SecurityContextHolder.getContext().getAuthentication();

        String token = httpHeaders.get("authorization").toString().replace("[Bearer ", "").replace("]", "");
        final JwtParser jwtParser = Jwts.parser().setSigningKey("stsdevs123");
        final Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);
        final Claims claims = claimsJws.getBody();

        return "Teste";
    }
}
