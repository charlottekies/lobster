package com.charlotte.kies.security;
import com.google.api.client.util.Beta;

/**
 * Constants for Google's OAuth 2.0 implementation.
 *
 * @since 1.7
 * @author Yaniv Inbar
 */
public class GoogleOAuthConstants {

    /** Encoded URL of Google's end-user authorization server. */
    public static final String AUTHORIZATION_SERVER_URL = "https://accounts.google.com/o/oauth2/auth";

    /** Encoded URL of Google's token server. */
    public static final String TOKEN_SERVER_URL = "https://oauth2.googleapis.com/token";

    /**
     * {@link Beta} <br>
     * Encoded URL of Google's public certificates.
     *
     * @since 1.15
     */
    @Beta
    public static final String DEFAULT_PUBLIC_CERTS_ENCODED_URL =
            "https://www.googleapis.com/oauth2/v1/certs";

    /**
     * Redirect URI to use for an installed application as specified in <a
     * href="https://developers.google.com/identity/protocols/OAuth2InstalledApp">Using OAuth 2.0 for
     * Mobile & Desktop Apps</a>.
     */
    public static final String OOB_REDIRECT_URI = "urn:ietf:wg:oauth:2.0:oob";

    private GoogleOAuthConstants() {}
}