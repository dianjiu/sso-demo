package cn.org.dianjiu.sso.controller;

import cn.org.dianjiu.sso.pojo.RespVO;
import cn.org.dianjiu.sso.pojo.req.TUserReq;
import cn.org.dianjiu.sso.pojo.resp.TUserResp;
import cn.org.dianjiu.sso.service.TUserServiceI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户操作(TUser)表控制层
 *
 * @author makejava
 * @since 2020-09-14 15:26:04
 */
@RestController
@Api(value = "TUser", tags = {"用户操作"})
@RequestMapping("/tUser")
public class TUserController {

    /**
     * 服务对象
     */
    @Autowired
    private TUserServiceI tUserService;

    /**
     * 通过Id查询单个对象
     *
     * @param id 主键
     * @return 实例对象
     */
    @ApiOperation("通过Id查询单个对象")
    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RespVO<TUserResp> getById(@PathVariable Integer id) {
        RespVO<TUserResp> result = new RespVO<>();
        TUserResp tUserResp = tUserService.getById(id);
        if (null == tUserResp) {
            result.setCode("400");
            result.setMsg("没有查到数据！");
            return result;
        }
        result.setCode("200");
        result.setMsg("查询成功！");
        result.setData(tUserResp);
        return result;
    }

    /**
     * 通过实体不为空的属性作为筛选条件查询单个对象
     *
     * @param tUserReq
     * @return 实例对象
     */
    @ApiOperation("通过属性查询单个对象")
    @PostMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public RespVO<TUserResp> getByEntity(TUserReq tUserReq) {
        RespVO<TUserResp> result = new RespVO<>();
        TUserResp tUserResp = tUserService.getByEntity(tUserReq);
        if (null == tUserResp) {
            result.setCode("400");
            result.setMsg("没有查到数据！");
            return result;
        }
        result.setCode("200");
        result.setMsg("查询成功！");
        result.setData(tUserResp);
        return result;
    }

    /**
     * 通过实体不为空的属性作为筛选条件查询对象列表
     *
     * @param tUserReq 实例对象
     * @return 对象列表
     */
    @ApiOperation("通过属性查询对象列表")
    @PostMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public RespVO<List> list(TUserReq tUserReq) {
        RespVO<List> result = new RespVO<>();
        List<TUserResp> tUserRespList = tUserService.listByEntity(tUserReq);
        if (null == tUserRespList || tUserRespList.isEmpty()) {
            result.setCode("400");
            result.setMsg("没有查到数据！");
            return result;
        }
        result.setCode("200");
        result.setMsg("请求成功！");
        result.setData(tUserRespList);
        return result;
    }

    /**
     * 新增实体属性不为null的记录
     *
     * @param tUserReq 实例对象
     * @return 实例对象
     */
    @ApiOperation("新增对象记录")
    @PostMapping(value = "/insert", produces = MediaType.APPLICATION_JSON_VALUE)
    public RespVO<TUserResp> insert(@RequestBody @Validated TUserReq tUserReq) {
        RespVO<TUserResp> result = new RespVO<>();
        int insert = tUserService.insert(tUserReq);
        if (insert != 1) {
            result.setCode("400");
            result.setMsg("新增数据失败！");
            return result;
        }
        result.setCode("200");
        result.setMsg("新增数据成功！");
        return result;
    }

    /**
     * 新增实体属性不为null的多条记录
     *
     * @param list 对象集合
     * @return 实例对象
     */
    @ApiOperation("批量新增对象记录")
    @PostMapping(value = "/insertBatch", produces = MediaType.APPLICATION_JSON_VALUE)
    public RespVO<TUserResp> insertBatch(@RequestBody List<TUserReq> list) {
        RespVO<TUserResp> result = new RespVO<>();
        int insert = tUserService.insertBatch(list);
        if (insert < 1) {
            result.setCode("400");
            result.setMsg("新增数据失败！");
            return result;
        }
        result.setCode("200");
        result.setMsg("新增数据成功！");
        return result;
    }

    /**
     * 通过表字段修改实体属性不为null的列
     *
     * @param tUserReq 实例对象
     * @return 实例对象
     */
    @ApiOperation("更新对象记录")
    @PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public RespVO<TUserResp> update(@RequestBody @Validated TUserReq tUserReq) {
        RespVO<TUserResp> result = new RespVO<>();
        int update = tUserService.update(tUserReq);
        if (update != 1) {
            result.setCode("400");
            result.setMsg("更新数据失败！");
            return result;
        }
        result.setCode("200");
        result.setMsg("更新数据成功！");
        return result;
    }

    /**
     * 通过ID主键删除数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @ApiOperation("删除一条对象记录")
    @GetMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RespVO<TUserResp> deleteOne(@PathVariable Integer id) {
        RespVO<TUserResp> result = new RespVO<>();
        int delete = tUserService.deleteById(id);
        if (delete != 1) {
            result.setCode("400");
            result.setMsg("删除数据失败！");
            return result;
        }
        result.setCode("200");
        result.setMsg("删除数据成功！");
        return result;
    }

    /**
     * 通过主键列表删除，列表长度不能为0
     *
     * @param ids 实例对象
     * @return 实例对象
     */
    @ApiOperation("批量删除对象记录")
    @PostMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public RespVO<TUserResp> deleteBatch(@RequestBody List<Integer> ids) {
        RespVO<TUserResp> result = new RespVO<>();
        int dels = 0;
        if (ids != null && ids.size() > 0) {
            dels = tUserService.deleteByIds(ids);
        }
        if (dels <= 0) {
            result.setCode("400");
            result.setMsg("批量删除数据失败！");
            return result;
        }
        result.setCode("200");
        result.setMsg("批量删除数据成功！");
        return result;
    }

}