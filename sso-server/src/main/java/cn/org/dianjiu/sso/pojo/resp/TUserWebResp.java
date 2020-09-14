package cn.org.dianjiu.sso.pojo.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 站点操作(TUserWebResp) Resp
 *
 * @author makejava
 * @since 2020-09-14 15:26:15
 */
@Data
public class TUserWebResp implements Serializable {
    private static final long serialVersionUID = 9155949248117098529L;
    @ApiModelProperty("唯一ID")
    private Integer id;
    @ApiModelProperty("用户ID")
    private String userId;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("站点名字")
    private String webName;
    @ApiModelProperty("站点描述")
    private String webDesc;
    @ApiModelProperty("站点IP")
    private String webIp;
    @ApiModelProperty("站点域名")
    private String webUrl;
    @ApiModelProperty("站点类型")
    private String webType;
    @ApiModelProperty("站点头像")
    private String webImage;
    @ApiModelProperty("创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date crateTime;
    @ApiModelProperty("更新时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

}