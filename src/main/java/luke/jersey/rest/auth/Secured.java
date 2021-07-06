package luke.jersey.rest.auth;


import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import javax.ws.rs.NameBinding;

/* Java Annotation 
 * 
 * It's bound to AuthenticationFilter class,
 * which will authenticate the header of every 
 * request to api, checking jwt validity (json web token)
 * */

@NameBinding
@Retention(RUNTIME)
@Target({TYPE, METHOD})
public @interface Secured {

}
