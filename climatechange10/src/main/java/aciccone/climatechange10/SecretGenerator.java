package aciccone.climatechange10;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.util.Base64;

public class SecretGenerator {
    public static void main (String[]args){
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String base64Key = Base64.getEncoder().encodeToString(key.getEncoded());

        System.out.println("=".repeat(80));
        System.out.println("JWT SECRET KEY GENERATO CON SUCCESSO");
        System.out.println("=".repeat(80));
        System.out.println("\nCopia questo valore e salvalo come variabile d'ambiente JWT_SECRET:\n");
        System.out.println(base64Key);
        System.out.println("\n" + "=".repeat(80));
        System.out.println("ATTENZIONE: Questa chiave deve essere mantenuta SEGRETA!");
        System.out.println("Non condividerla mai pubblicamente o su GitHub!");
        System.out.println("=".repeat(80));
    }
}
