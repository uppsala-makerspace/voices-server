package se.uppsalamakerspace.iot.auth;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by fredl2 on 2016-11-01.
 */
public class StationHeaderAuthenticationFilter extends RequestHeaderAuthenticationFilter {
    private Log log = LogFactory.getLog(StationHeaderAuthenticationFilter.class);

    public StationHeaderAuthenticationFilter() {
        log.debug("Hello from custom filter");
    }

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        if(request.getHeader("X-Station-ID") == null) {
            return null;
        }
        return request.getHeader("X-Station-ID");
    }
}
