package hello.jwt;

import io.jsonwebtoken.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JJwtMain {

    public static void main(String[] args){

        String key = "asd";

        String jwt = Jwts.builder()
                .setSubject("wally subject")
                .setExpiration(new Date(System.currentTimeMillis() + 60000))
                .claim("name", "wally")
                .claim("scope", "www")
                .signWith(SignatureAlgorithm.HS256, key.getBytes())
                .compact();

        System.out.println(jwt);

        Claims claims = Jwts.parser()
                .setSigningKey(key.getBytes())
                .parseClaimsJws(jwt).getBody();

        String scope = (String) claims.get("scope");
        String name = (String) claims.get("name");
        Date exp = claims.getExpiration();
        System.out.println("scope : " + scope);
        System.out.println("name : " + name);
        System.out.println("exp : " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(exp));


    }

}
