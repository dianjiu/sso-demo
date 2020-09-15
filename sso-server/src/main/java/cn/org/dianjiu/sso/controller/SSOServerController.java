package cn.org.dianjiu.sso.controller;

import cn.org.dianjiu.sso.pojo.RespVO;
import cn.org.dianjiu.sso.pojo.req.TUserReq;
import cn.org.dianjiu.sso.pojo.resp.TUserResp;
import cn.org.dianjiu.sso.pojo.resp.TUserWebResp;
import cn.org.dianjiu.sso.service.TUserServiceI;
import cn.org.dianjiu.sso.service.TUserWebServiceI;
import cn.org.dianjiu.sso.util.JwtUtils;
import cn.org.dianjiu.sso.util.ObjectUtils;
import cn.org.dianjiu.sso.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Slf4j
@Controller
@RequestMapping("/sso")
public class SSOServerController {

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    private TUserServiceI tUserService;
    @Autowired
    private TUserWebServiceI tUserWebService;

    /**
     * 验证cookie是否通过
     * @param cookieName cookie名称
     * @param cookieValue cookie内容
     * @return 是否认证成功
     */
    @PostMapping ("/checkCookie")
    public boolean checkAuthCookies (String cookieName, String cookieValue) {
        //通过jwt解密获取redis的key
        Map<String, Object> decrypt = JwtUtils.decrypt(cookieValue);
        String redisKey = String.valueOf(decrypt.get("userToken"));
        //redis取出用户信息
        Object obj = redisUtils.get(redisKey);
        //校验用户信息
        if(ObjectUtils.isNotEmpty(obj) && cookieName.equals(obj.toString())){
            log.info("cookie验证通过");
            return true;
        }
        return false;
    }

    /**
     * 统一处理login请求
     * @param username 用户名
     * @param password 密码
     */
    @PostMapping
    public RespVO<Map<String,Object>> checkLogin (String username, String password) {
        log.info("统一登录校验");
        TUserReq tUserReq = new TUserReq();
        tUserReq.setUsername(username);
        tUserReq.setPassword(password);
        TUserResp userResp = tUserService.getByEntity(tUserReq);
        if (userResp != null) {
            //封装参数
            Map<String, Object> param = new HashMap<>();
            /*TUserWebReq tUserWebReq = new TUserWebReq();
            tUserWebReq.setUsername(username);*/
            //获得所有子系统域名信息
            //List<TUserWebResp> userWebResps = tUserWebService.listByEntity(tUserWebReq);
            List<TUserWebResp> userWebResps =tUserWebService.selectAll();
            List<String> domainUrl = new ArrayList<>(userWebResps.size());
            userWebResps.forEach(domain->{
                domainUrl.add(domain.getWebUrl()+"/addCookie");
            });
            //生成用户UUID，为Redis的Key
            String key = UUID.randomUUID().toString().replace("-", "");
            log.info("用户【"+username+"】的UUID为"+key);
            redisUtils.set(key, "UserInfo");
            //JWT加密key为cookie的value
            //username
            String cookieName = "dianjiu";
            Map<String, Object> hashMap = new HashMap<>();
            hashMap.put("userToken",key);
            String cookieValue = JwtUtils.encrypt(hashMap);
            log.info("用户【"+username+"】的UUID经JWT加密后为"+cookieValue);
            //重定向地址
            param.put("cookieurl",domainUrl);
            param.put("cookieName", cookieName);
            param.put("cookieValue",cookieValue);
            RespVO<Map<String, Object>> result = new RespVO<>();
            result.setData(param);
            return result;
        }
        return new RespVO<>("222","账号或密码错误");
    }

    /**
     * 统一处理logout请求
     */
    @GetMapping("/logout")
    public String logout (String cookieName, String cookieValue) {
        //通过jwt解密获取redis的key
        Map<String, Object> decrypt = JwtUtils.decrypt(cookieValue);
        String redisKey = String.valueOf(decrypt.get("userToken"));
        //清除redis数据
        redisUtils.del(redisKey);
        //获得所有子系统域名信息
        //String callbackFuncation = request.getParameter("callback");
        List<TUserWebResp> userWebResps =tUserWebService.selectAll();
        List<String> domainUrl = new ArrayList<>(userWebResps.size());
        userWebResps.forEach(domain->{
            domainUrl.add(domain.getWebUrl() +"/clearCookie");
        });
        //遍历domainUrl，可采用异常线程使用http工具类调用客户端的clearCookie
        return "登出成功！";
    }

}
