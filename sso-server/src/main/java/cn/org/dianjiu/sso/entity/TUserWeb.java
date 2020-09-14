package cn.org.dianjiu.sso.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 站点操作(TUserWeb)实体类
 *
 * @author makejava
 * @since 2020-09-14 15:26:09
 */
@Data
public class TUserWeb {
    /**
     * 唯一ID
     */
    private Integer id;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 站点名字
     */
    private String webName;
    /**
     * 站点描述
     */
    private String webDesc;
    /**
     * 站点IP
     */
    private String webIp;
    /**
     * 站点域名
     */
    private String webUrl;
    /**
     * 站点类型
     */
    private String webType;
    /**
     * 站点头像
     */
    private String webImage;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date crateTime;
    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

}