package mx.gob.sat.siat.base.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.gob.sat.siat.base.constante.CommonConstants;
import mx.gob.sat.siat.base.dto.UserProfileDTO;
import mx.gob.sat.siat.modelo.dto.AccesoUsr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContribuyenteSessionFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        HttpSession session = (HttpSession) request.getSession(true);

        UserProfileDTO userProfileDTO = (UserProfileDTO) session
                .getAttribute(CommonConstants.USER_PROFILE);

        logger.debug("Objeto de User inicial resquest. [{}]", userProfileDTO);

        // Comprueba que no existe un usuario en session activa de un
        // contribuyente o un empleado.
        if (null == userProfileDTO) {

            // Obtenemos la session del cluster Coherence
            logger.debug("Iniciando Session... obteniendo  de Portal acceso...");
            AccesoUsr accesoUsr = null;
            try {
                logger.debug("Obteniendo contribuyente");
                accesoUsr = (AccesoUsr) session.getAttribute("acceso");
            } catch (Exception e) {
                logger.error("Error al obtener el session.", e);
            }

            // Si no se recupera un usuario
            if (accesoUsr == null) {
                logger.debug("No se obtuvo Usuario del contribuyente");
                response.sendRedirect(request.getContextPath()
                        + CommonConstants.REDIRECT_ERROR_LOGIN);
            } else {
                // Se recupero un objeto de Session de Novell
                logger.debug("Objeto acceso recuperado [{}]",
                        accesoUsr.getUsuario());
                userProfileDTO = convert(accesoUsr);
                logger.debug("Objeto userProfileDTO transformado [{}]",
                        userProfileDTO);
                session.setAttribute(CommonConstants.USER_PROFILE,
                        userProfileDTO);
            }
        } else {
            // La session existe y se inyecta al recurso
            logger.debug("Ya existe session, datos:  ", userProfileDTO);
        }
        chain.doFilter(req, res);

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub

    }

    private UserProfileDTO convert(AccesoUsr accesoUsr) {
        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setUsuario(accesoUsr.getUsuario());
        userProfileDTO.setRfc(accesoUsr.getUsuario());
        userProfileDTO.setRfcCorto(accesoUsr.getRfcCorto());
        userProfileDTO.setNombreCompleto(accesoUsr.getNombreCompleto());
        // userProfileDTO.setNombres(accesoUsr.getNombres());
        // userProfileDTO.setPrimerApellido(accesoUsr.getPrimerApellido());
        // userProfileDTO.setSegundoApellido(accesoUsr.getSegundoApellido());
        userProfileDTO.setIp(accesoUsr.getIp());
        userProfileDTO.setEsContribuyente("1".equals(accesoUsr
                .getIdTipoPersona()));
        Collection<String> roles = new ArrayList<String>();
        if (null != accesoUsr.getRoles()) {
            for (String rol : accesoUsr.getRoles().split(",")) {
                roles.add(rol);
            }
        }
        userProfileDTO.setRolesNovell(roles);
        userProfileDTO.setRoles(roles);
        return userProfileDTO;
    }

}
