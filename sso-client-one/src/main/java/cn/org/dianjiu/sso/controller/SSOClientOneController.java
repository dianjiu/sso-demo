package cn.org.dianjiu.sso.controller;

import cn.org.dianjiu.sso.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    /*@GetMapping("/ssocheck")
    public ModelAndView checkCookies (HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if ("jian".equals(cookie.getName())) { //统一登录cookie为jian，如果存在就认证
                    log.info("cookie 存在，开始验证");
                    HttpUtil httpUtil = new HttpUtil("http://sso.com/sso/authcookies", Method.GET);
                    String result = httpUtil.send(cookie.getName(), cookie.getValue());
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
*/
    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return index/login
     *//*
    @PostMapping("/login")
    public ModelAndView doLogin (String username, String password) {
        if (username != null && !"".equals(username) &&
                password != null && !"".equals(password) ) {
            HttpUtil httpUtil = new HttpUtil("http://sso.com/sso/", "POST");
            Result result = httpUtil.sendLogin(username,password);
            //如果验证通过，就携带所有子系统域名返回首页
            int isLogin = result.getResultCode().getCode();
            if (isLogin == 1) {
                @SuppressWarnings("all")
                Map<String,String> param = (Map<String, String>) result.getData();
                return new ModelAndView("public/index","sendparam",param);
            }
        }
        return new ModelAndView("index");
    }*/

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
}
