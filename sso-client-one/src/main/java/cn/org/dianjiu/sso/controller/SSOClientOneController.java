package cn.org.dianjiu.sso.controller;

import cn.org.dianjiu.sso.util.HttpClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
public class SSOClientOneController {

    /**
     * 1、客户端先验证cookie，
     * 能从浏览器取到cookie，
     * 就去sso系统验证cookie
     * @return 响应界面：login/index
     */
    @GetMapping("/checkCookie")
    public ModelAndView checkCookie (HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                //统一登录cookieName为用户名，如果存在就认证
                if ("dianjiu".equals(cookie.getName())) {
                    log.info("cookie 存在，开始验证");
                    Map<String, String> map = new HashMap<>();
                    map.put("cookieName", "dianjiu");
                    map.put("cookieValue", "1231231");
                    String result = HttpClientUtils.doPost("http://sso.com/sso/checkCookie", map);
                    boolean authBoo  = Boolean.valueOf(result);
                    if (authBoo) {
                        log.info("验证通过");
                        return new ModelAndView("public/index");
                    }
                    break;
                }
            }
        }
        return new ModelAndView("index");
    }

    /**
     *
     * @param cookieName cookie名称
     * @param cookieValue cookie值
     * @param response 响应
     */
    /*@GetMapping("/addcookie")
    public void addCookies (String cookieName, String cookieValue, HttpServletResponse response) {
        log.info("添加cookie");
        Cookie cookie = new Cookie(cookieName,cookieValue);
        cookie.setPath("/");
        cookie.setMaxAge(3600);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }*/

    /**
     *
     * @param cookieName cookie名称
     * @param cookieValue cookie值
     * @param response 响应
     */
    /*@GetMapping("/clearCookie")
    public void clearCookie (String cookieName, String cookieValue, HttpServletResponse response) {
        log.info("添加cookie");
        Cookie cookie = new Cookie(cookieName,cookieValue);
        cookie.setPath("/");
        cookie.setMaxAge(3600);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }*/
}
