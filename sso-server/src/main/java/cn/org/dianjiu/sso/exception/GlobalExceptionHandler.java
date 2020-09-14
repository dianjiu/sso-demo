package cn.org.dianjiu.sso.exception;


import cn.org.dianjiu.sso.constant.CodeEnum;
import cn.org.dianjiu.sso.pojo.ResultBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ControllerAdvice 捕获 Controller 层抛出的异常，如果添加 @ResponseBody 返回信息则为JSON格式。
 * @RestControllerAdvice 相当于 @ControllerAdvice 与 @ResponseBody 的结合体。
 * @ExceptionHandler 统一处理一种类的异常，减少代码重复率，降低复杂度。
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BusinessException.class)//指定拦截的异常
    public ResultBean BusinessExceptionHandler(BusinessException e) {
        ResultBean res = ResultBean.builder().build();
        res.setCode(e.getCode());
        res.setMsg(e.getMessage());
        return res;
    }

    @ExceptionHandler(value = Exception.class)
    public ResultBean ExceptionHandler(Exception e) {
        ResultBean res = ResultBean.builder().build();
        res.setCode(CodeEnum.SYS_ERROR.getCode());
        res.setMsg(CodeEnum.SYS_ERROR.getMsg());
        return res;
    }
}

