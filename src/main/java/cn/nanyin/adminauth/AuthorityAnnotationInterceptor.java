package cn.nanyin.adminauth;

import cn.nanyin.model.AdminUser;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by MYNP on 2015/7/27 0027.
 */
public class AuthorityAnnotationInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handler2=(HandlerMethod) handler;
        AdminAuthority adminAuthority;
        if(handler2.getMethodAnnotation(AdminAuthority.class)==null){
            adminAuthority=handler2.getBeanType().getAnnotation(AdminAuthority.class);
        }
        else{
            adminAuthority = handler2.getMethodAnnotation(AdminAuthority.class);
        }

        if(null == adminAuthority){
            return true;
        }
        HttpSession session = request.getSession();
        AdminUser admin = (AdminUser)session.getAttribute("AdminSession");
        if(admin==null || admin.getId()==0){
            response.sendRedirect("/nyadmin/adminloginerror");
            return false;
        }
        for(AuthorityType authorityType: adminAuthority.authorityTypes()){
            if(AuthorityHelper.hasAuthority(authorityType.getIndex()-1, admin.getPermission())){
                return true;
            }
        }
        response.sendRedirect("/nyadmin/adminautherror");

        return false;
    }

}
