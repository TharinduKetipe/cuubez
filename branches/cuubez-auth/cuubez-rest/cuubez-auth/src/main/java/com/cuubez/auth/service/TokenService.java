/**
 * 
 */
package com.cuubez.auth.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cuubez.auth.exception.AuthenticationException;
import com.cuubez.auth.model.UserProfile;

/**
 * @author ruwan
 */
public class TokenService {

    private static Log log = LogFactory.getLog(TokenService.class);

    private final Map<String, UserProfile> tokenMap;

    private final long tokenTimeout;

    /**
     * 
     */
    public TokenService() {
        tokenMap = new HashMap<String, UserProfile>();
        tokenTimeout = 5 * 60 * 1000; // 5min //TODO need to make this configurable
    }

    public UserProfile retrieveUserProfileForToken(String authenticationToken) throws AuthenticationException {

        if (authenticationToken == null) {
            String errMsg = "Authentication token invalid." + authenticationToken;
            log.error(errMsg);
            throw new AuthenticationException(errMsg);
        }

        if (tokenMap.containsKey(authenticationToken)) {

            UserProfile userProfile = tokenMap.get(authenticationToken);

            if (System.currentTimeMillis() - userProfile.getAuthenticationToken().getCreatedTimeInMilliseconds() > this.tokenTimeout) {
                String errMsg = "Authentication token expired. AuthenticationToken: " + authenticationToken;
                log.error(errMsg);
                throw new AuthenticationException(errMsg);
            } else {
                return userProfile;// TODO re-authorize?
            }

        } else {
            String errMsg = "Authentication token invalid." + authenticationToken;
            log.error(errMsg);
            throw new AuthenticationException(errMsg);
        }
    }

    public void addUserProfileWithToken(UserProfile userProfile) throws AuthenticationException {
        if (userProfile == null) {
            String errMsg = "Authentication token invalid. UserProfile: " + userProfile;
            log.error(errMsg);
            throw new AuthenticationException(errMsg);
        }

        if (!tokenMap.containsKey(userProfile.getAuthenticationToken().getAuthToken())) {
            tokenMap.put(userProfile.getAuthenticationToken().getAuthToken(), userProfile);
        } else {
            String errMsg = "Authentication token already exists. token: "
                    + userProfile.getAuthenticationToken().getAuthToken();
            log.error(errMsg);
            throw new AuthenticationException(errMsg);
        }

    }
}
