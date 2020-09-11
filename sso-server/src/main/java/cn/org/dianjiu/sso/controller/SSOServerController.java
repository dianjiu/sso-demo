package cn.org.dianjiu.sso.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class SSOServerController {

    /**
     * 验证cookie是否通过
     * @param cookieName cookie名称
     * @param cookieValue cookie内容
     * @return 是否认证成功
     */
    @GetMapping("/authcookies")
    public boolean checkAuthCookies (String cookieName, String cookieValue) {
       /* boolean isUpdate = new JwtUtil(null,cookieValue).freeJwt();
        if ("jian".equals(cookieName) && "ok".equals(cookieValue)) {
            log.info("cookie验证通过");
            return true;
        }*/
        return false;
    }

    /**
     * 统一处理login请求
     * @param username 用户名
     * @param password 密码
     */
    /*@PostMapping
    public Result<Map<String,Object>> checkLogin (String username, String password) {
        log.info("统一登录校验");
        TbUser user = userService.login(username, password);
        if (user != null) {
            //封装参数
            Map<String, Object> param = new HashMap<>();
            //获得所有子系统域名信息
            List<TbDomain> domains = domainService.selectAll();
            List<String> domainUrl = new ArrayList<>(domains.size());
            domains.forEach(domain->{
                domainUrl.add(domain.getDomain()+"/addcookie");
            });
            //生成jwt，加密用户信息
            String cookieName = "jian";
            String cookieValue = new JwtUtil(user.toString(),null).creatJwt();
            param.put("cookieurl",domainUrl);
            param.put("cookieName", cookieName);
            param.put("cookieValue",cookieValue);
            Result<Map<String, Object>> result = new Result<>(ResultCodeEnum.AUTHSUCCESS);
            result.setData(param);
            return result;
        }
        return new Result<>(ResultCodeEnum.UNAUTHORIZEd,"账号或密码错误");
    }*/

    /**
     * 添加需要清除的cookie
     *//*
    @GetMapping("/loginout")
    public String loginOut (HttpServletRequest request) {
        String callbackFuncation = request.getParameter("callback");
        log.info("start clear");
        List<TbDomain> domains = domainService.selectAll();
        List<String> domainUrl = new ArrayList<>(domains.size());
        domains.forEach(domain->{
            domainUrl.add(domain.getDomain()+"/clear");
        });
        String resultMsg = JSON.toJSONString(domainUrl);
        return callbackFuncation+"("+resultMsg+")";
    }*/

    /**
     * 检查是否有全局会话.
     * @param redirectUrl 客户端被拦截的请求地址
     * @param session      统一认证中心的会话对象
     * @param model        数据模型
     * @return              视图地址
     */
    @RequestMapping("/checkLogin")
    public String checkLogin(String redirectUrl, HttpSession session, Model model){
        //1.判断是否有全局的会话
        //从会话中获取令牌信息,如果取不到说明没有全局会话,如果能取到说明有全局会话
        String token = (String) session.getAttribute("token");
        if(StringUtils.isEmpty(token)){
            //表示没有全局会话
            model.addAttribute("redirectUrl",redirectUrl);
            //跳转到统一认证中心的登陆页面.已经配置视图解析器,
            // 会找/WEB-INF/views/login.jsp视图
            return "login";
        }else{
            /**---------------------------阶段三添加的代码start--------------------**/
            //有全局会话
            //取出令牌信息,重定向到redirectUrl,把令牌带上
            // http://www.wms.com:8089/main?token=
            model.addAttribute("token",token);
            /**---------------------------阶段三添加的代码end-----------------------**/
            return "redirect:"+redirectUrl;
        }
    }

    @RequestMapping("/logOut")
    public String logOut(HttpSession session){
        //销毁全局会话
        session.invalidate();
        return "logOut";
    }
}