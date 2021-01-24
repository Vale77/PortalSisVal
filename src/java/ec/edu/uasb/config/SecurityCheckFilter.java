/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.config;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author victor.barba
 */
public class SecurityCheckFilter implements Filter {

    private final static String FILTER_APPLIED = "_security_filter_applied";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        nonRestrictedPages = new ArrayList();
//        // index.jsp is the landing page of the application
//        // and needs no logging in
//        nonRestrictedPages.add("index.xhtml");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest hreq = (HttpServletRequest) request;
        HttpServletResponse hres = (HttpServletResponse) response;
//        HttpSession session = hreq.getSession();
//        Usuario user = (Usuario) session.getAttribute("user");

        String url = hreq.getRequestURI().trim();
        if (url.contains("rep/") 
                || url.contains("general/") 
                || url.contains("resources/css")
                || url.contains("resources/js")                
                || url.contains("pages/")
                || url.contains("sessionExpired")) {
            String pagPrincipal = hreq.getRequestURL().substring(0, hreq.getRequestURL().indexOf(url)) + hreq.getContextPath() + "/principal.xhtml";
            hres.sendRedirect(pagPrincipal);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
//            HttpServletRequest hreq = (HttpServletRequest) request;
//        HttpServletResponse hres = (HttpServletResponse) response;
//        HttpSession session = hreq.getSession();
//        String requestedSession = hreq.getRequestedSessionId();
//        String currentWebSession = hreq.getSession().getId();
//        String currentpage = hreq.getPathTranslated();

//        Usuario user = (Usuario) session.getAttribute("user");

//            if (user != null) {
//            chain.doFilter(request, response);
//        } else {
//            hres.sendRedirect(hreq.getContextPath() + "/index.jsf");
//        }
    // if currentpage is null or is not a jsp page don't do any filtering
//        if (currentpage == null || !(currentpage.endsWith(".xhtml"))) {
//            if (chain != null) {
//                chain.doFilter(request, response);
//            }
//            return;
//        }
//
//        // if the page is restricted and this filter is not
//        // already applied do this block
//        if (isNonRestrictedPage(currentpage) == false && request.getAttribute(FILTER_APPLIED) == null) {
//
//            request.setAttribute(FILTER_APPLIED, Boolean.TRUE);
//            //If the session bean is not null get the session bean property username.
//            Usuario user = (Usuario) session.getAttribute("user");
//            // if the user is not set and the current page is not
//            // the login page then redirect back to login page
//            if (user == null && (!currentpage.endsWith("principal.xhtml"))) {
//                hres.sendRedirect("principal.xhtml");
//                return;
//            }
//        }
//        //deliver request to next filter
//        if (chain != null) {
//            chain.doFilter(request, response);
//        }
}
