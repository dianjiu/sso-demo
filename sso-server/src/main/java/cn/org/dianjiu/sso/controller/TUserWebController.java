package cn.org.dianjiu.sso.controller;

import cn.org.dianjiu.sso.pojo.RespVO;
import cn.org.dianjiu.sso.pojo.req.TUserWebReq;
import cn.org.dianjiu.sso.pojo.resp.TUserWebResp;
import cn.org.dianjiu.sso.service.TUserWebServiceI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 站点操作(TUserWeb)表控制层
 *
 * @author makejava
 * @since 2020-09-14 15:26:12
 */
@RestController
@Api(value = "TUserWeb", tags = {"站点操作"})
@RequestMapping("/tUserWeb")
public class TUserWebController {

    /**
     * 服务对象
     */
    @Autowired
    private TUserWebServiceI tUserWebService;

    /**
     * 通过Id查询单个对象
     *
     * @param id 主键
     * @return 实例对象
     */
    @ApiOperation("通过Id查询单个对象")
    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RespVO<TUserWebResp> getById(@PathVariable Integer id) {
        RespVO<TUserWebResp> result = new RespVO<>();
        TUserWebResp tUserWebResp = tUserWebService.getById(id);
        if (null == tUserWebResp) {
            result.setCode("400");
            result.setMsg("没有查到数据！");
            return result;
        }
        result.setCode("200");
        result.setMsg("查询成功！");
        result.setData(tUserWebResp);
        return result;
    }

    /**
     * 通过实体不为空的属性作为筛选条件查询单个对象
     *
     * @param tUserWebReq
     * @return 实例对象
     */
    @ApiOperation("通过属性查询单个对象")
    @PostMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public RespVO<TUserWebResp> getByEntity(TUserWebReq tUserWebReq) {
        RespVO<TUserWebResp> result = new RespVO<>();
        TUserWebResp tUserWebResp = tUserWebService.getByEntity(tUserWebReq);
        if (null == tUserWebResp) {
            result.setCode("400");
            result.setMsg("没有查到数据！");
            return result;
        }
        result.setCode("200");
        result.setMsg("查询成功！");
        result.setData(tUserWebResp);
        return result;
    }

    /**
     * 通过实体不为空的属性作为筛选条件查询对象列表
     *
     * @param tUserWebReq 实例对象
     * @return 对象列表
     */
    @ApiOperation("通过属性查询对象列表")
    @PostMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public RespVO<List> list(TUserWebReq tUserWebReq) {
        RespVO<List> result = new RespVO<>();
        List<TUserWebResp> tUserWebRespList = tUserWebService.listByEntity(tUserWebReq);
        if (null == tUserWebRespList || tUserWebRespList.isEmpty()) {
            result.setCode("400");
            result.setMsg("没有查到数据！");
            return result;
        }
        result.setCode("200");
        result.setMsg("请求成功！");
        result.setData(tUserWebRespList);
        return result;
    }

    /**
     * 新增实体属性不为null的记录
     *
     * @param tUserWebReq 实例对象
     * @return 实例对象
     */
    @ApiOperation("新增对象记录")
    @PostMapping(value = "/insert", produces = MediaType.APPLICATION_JSON_VALUE)
    public RespVO<TUserWebResp> insert(@RequestBody @Validated TUserWebReq tUserWebReq) {
        RespVO<TUserWebResp> result = new RespVO<>();
        int insert = tUserWebService.insert(tUserWebReq);
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
    public RespVO<TUserWebResp> insertBatch(@RequestBody List<TUserWebReq> list) {
        RespVO<TUserWebResp> result = new RespVO<>();
        int insert = tUserWebService.insertBatch(list);
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
     * @param tUserWebReq 实例对象
     * @return 实例对象
     */
    @ApiOperation("更新对象记录")
    @PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public RespVO<TUserWebResp> update(@RequestBody @Validated TUserWebReq tUserWebReq) {
        RespVO<TUserWebResp> result = new RespVO<>();
        int update = tUserWebService.update(tUserWebReq);
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
    public RespVO<TUserWebResp> deleteOne(@PathVariable Integer id) {
        RespVO<TUserWebResp> result = new RespVO<>();
        int delete = tUserWebService.deleteById(id);
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
    public RespVO<TUserWebResp> deleteBatch(@RequestBody List<Integer> ids) {
        RespVO<TUserWebResp> result = new RespVO<>();
        int dels = 0;
        if (ids != null && ids.size() > 0) {
            dels = tUserWebService.deleteByIds(ids);
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