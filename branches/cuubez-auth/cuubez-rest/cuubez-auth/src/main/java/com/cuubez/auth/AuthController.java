/**
 * 
 */
package com.cuubez.auth;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cuubez.auth.exception.AccountingException;
import com.cuubez.auth.exception.AuthenticationException;
import com.cuubez.auth.exception.AuthorizationException;
import com.cuubez.auth.exception.UnauthorizedException;
import com.cuubez.auth.model.AuthenticationCode;
import com.cuubez.auth.model.AuthenticationToken;
import com.cuubez.auth.model.UserProfile;
import com.cuubez.auth.model.enums.AccountAction;
import com.cuubez.auth.model.enums.AccountOutcome;
import com.cuubez.auth.service.AccountingService;
import com.cuubez.auth.service.AuthenticationService;
import com.cuubez.auth.service.AuthorizationService;
import com.cuubez.auth.service.TokenService;
import com.cuubez.core.context.ServiceContext;

/**
 * @author ruwan
 */
public class AuthController {

    private static Log log = LogFactory.getLog(AuthController.class);

    private static AuthController authController = null;

    private static final String PRINCIPAL_CUSTOM_HEADER = "principal";
    private static final String CREDENTIALS_CUSTOM_HEADER = "credentials";
    private static final String AUTHENTICATION_TOKEN = "AUTH_TOKEN";

    private final AuthenticationService authenticationService;
    private final AuthorizationService authorizationService;
    private final AccountingService accountingService;
    private final TokenService tokenService;

    private AuthController() {
        authenticationService = new AuthenticationService();
        authorizationService = new AuthorizationService();
        accountingService = new AccountingService();
        tokenService = new TokenService();
    }

    public static AuthController getInstance() {
        if (authController == null) {
            authController = new AuthController();
        }
        return authController;
    }

    public AuthenticationToken service(ServletRequest request, ServletResponse response, ServiceContext serviceContext)
            throws UnauthorizedException {
        UserProfile userProfile = null;
        String principal = null;
        String credentials = null;
        String serviceName = null;
        String token = null;

        token = ((HttpServletRequest) request).getHeader(AUTHENTICATION_TOKEN);

        if (token != null) {
            userProfile = tokenService.retrieveUserProfileForToken(token);

            if (log.isDebugEnabled()) {
                log.debug("User authenticated with authentication token : " + token);// TODO account
            }

        } else {
            try {
                principal = ((HttpServletRequest) request).getHeader(PRINCIPAL_CUSTOM_HEADER);
                credentials = ((HttpServletRequest) request).getHeader(CREDENTIALS_CUSTOM_HEADER);

                if (principal == null || credentials == null) {
                    String errMsg = "User authentication failed. - principal: " + principal + " credentials: "
                            + credentials;
                    log.error(errMsg);
                    throw new UnauthorizedException(errMsg);
                }

                serviceName = serviceContext.getServiceAnnotationMetaData().getServiceLocation() + "/"
                        + serviceContext.getServiceName();

                accountingService.account(principal, null, AccountAction.AUTHENTICATION, serviceName,
                        AccountOutcome.ATTEMPTED);

                userProfile = authenticationService.authenticate(new AuthenticationCode(principal, credentials));

                tokenService.addUserProfileWithToken(userProfile);

                ((HttpServletResponse) response).addHeader(AUTHENTICATION_TOKEN, userProfile.getAuthenticationToken()
                        .getAuthToken());

                accountingService.account(userProfile.getUserId(), userProfile.getRoleId(),
                        AccountAction.AUTHENTICATION, serviceName, AccountOutcome.SUCCESS);

            } catch (AuthenticationException e) {

                accountingService.account(principal,
                        (userProfile == null ? null : userProfile.getRoleId()), AccountAction.AUTHENTICATION,
                        serviceName, AccountOutcome.FAIL);

                String errMsg = "User authentication failed. - " + e.getMessage();
                log.error(errMsg, e);
                throw new UnauthorizedException(errMsg, e);
            } catch (AccountingException e) {
                String errMsg = "User authentication accounting failed. - " + e.getMessage();
                log.error(errMsg, e);
                throw new UnauthorizedException(errMsg, e);
            }
        }

        try {
            accountingService.account(userProfile.getUserId(), userProfile.getRoleId(), AccountAction.AUTHORIZATION,
                    serviceName, AccountOutcome.ATTEMPTED);

            authorizationService.authorize(userProfile, serviceContext);

            accountingService.account(userProfile.getUserId(), userProfile.getRoleId(), AccountAction.AUTHORIZATION,
                    serviceName, AccountOutcome.SUCCESS);
        } catch (AuthorizationException e) {

            accountingService.account(userProfile.getUserId(), userProfile.getRoleId(), AccountAction.AUTHORIZATION,
                    serviceName, AccountOutcome.FAIL);

            String errMsg = "User authorization failed. - " + e.getMessage();
            log.error(errMsg, e);
            throw new UnauthorizedException(errMsg, e);
        } catch (AccountingException e) {
            String errMsg = "User authorization accounting failed. - " + e.getMessage();
            log.error(errMsg, e);
            throw new UnauthorizedException(errMsg, e);
        }

        return userProfile.getAuthenticationToken();
    }
}
