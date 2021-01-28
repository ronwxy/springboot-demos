package cn.jboost.springboot.security.username;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @Author ronwxy
 * @Date 2020/8/10 16:04
 * @Version 1.0
 */
public class UsernameAuthenticationProvider extends DaoAuthenticationProvider {

    private CompositeUserDetailsChecker preChecker;
    private CompositeUserDetailsChecker afterPasswordInvalidChecker;

    public void setPreChecker(CompositeUserDetailsChecker preChecker) {
        this.preChecker = preChecker;
    }

    public void setAfterPasswordInvalidChecker(CompositeUserDetailsChecker afterPasswordInvalidChecker) {
        this.afterPasswordInvalidChecker = afterPasswordInvalidChecker;
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
                                                  UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

        if (authentication.getCredentials() == null) {
            logger.debug("Authentication failed: no credentials provided");
            throw new BadCredentialsException("用户名或密码错误");
        }
        String rawPassword = authentication.getCredentials().toString();

        if (!getPasswordEncoder().matches(rawPassword, userDetails.getPassword())) {
            logger.debug("Authentication failed: password does not match stored value");
            if(afterPasswordInvalidChecker != null) {
                afterPasswordInvalidChecker.check(userDetails);
            }
            throw new BadCredentialsException("用户名或密码错误");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernameAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
