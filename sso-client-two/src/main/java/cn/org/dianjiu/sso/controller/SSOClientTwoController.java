package cn.org.dianjiu.sso.controller;

import org.springframework.stereotype.Controller;

@Controller
public class SSOClientTwoController {
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
