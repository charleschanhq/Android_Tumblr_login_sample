import java.util.Scanner;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TumblrApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;


public class tumblrtest {
	private static String PROTECTED_RESOURCE_URL="http://api.tumblr.com/v2/user/info";

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		OAuthService service = new ServiceBuilder()
        .provider( TumblrApi.class )
        .apiKey( "XgQdzoT4DNHb9cKFiOzcYlvOEixEGJzzDfgoNIh3YgbNA7BkRc" )
        .apiSecret( "AggjrbMvTze5TsEuUKHcsFt0BI3uAp5rc5ZW3BHIGCYi0vhuez" )
        .callback( "http://www.tumblr.com/connect/login_success.html" ) // OOB forbidden. We need an url and the better is on the tumblr website !
        .build();
        Scanner in = new Scanner( System.in );

System.out.println( "=== Tumblr's OAuth Workflow ===" );
System.out.println();

// Obtain the Request Token
System.out.println( "Fetching the Request Token..." );
Token requestToken = service.getRequestToken();
System.out.println( "Got the Request Token!" );
System.out.println();

System.out.println( "Now go and authorize Scribe here:" );
System.out.println( service.getAuthorizationUrl( requestToken ) );
System.out.println( "And paste the verifier here" );
System.out.print( ">>" );
Verifier verifier = new Verifier( in.nextLine() );
System.out.println();

// Trade the Request Token and Verfier for the Access Token
System.out.println( "Trading the Request Token for an Access Token..." );
Token accessToken = service.getAccessToken( requestToken ,
                                            verifier );
System.out.println( "Got the Access Token!" );
System.out.println( "(if your curious it looks like this: " + accessToken + " )" );
System.out.println();

// Now let's go and ask for a protected resource!
System.out.println( "Now we're going to access a protected resource..." );
OAuthRequest request = new OAuthRequest( Verb.GET ,
                                         PROTECTED_RESOURCE_URL );
service.signRequest( accessToken ,
                     request );
Response response = request.send();
System.out.println( "Got it! Lets see what we found..." );
System.out.println();
System.out.println( response.getBody() );

System.out.println();
System.out.println( "Thats it man! Go and build something awesome with Scribe! :)" );
	}}

