package cn.org.dianjiu.sso.service;

import cn.org.dianjiu.sso.pojo.req.TUserWebReq;
import cn.org.dianjiu.sso.pojo.resp.TUserWebResp;

import java.util.List;

/**
 * 站点操作(TUserWeb)表服务接口
 *
 * @author makejava
 * @since 2020-09-14 15:26:10
 */
public interface TUserWebServiceI {

    TUserWebResp getById(Integer id);

    TUserWebResp getByEntity(TUserWebReq tUserWebReq);

    List<TUserWebResp> listByEntity(TUserWebReq tUserWebReq);

    List<TUserWebResp> listByIds(List<Integer> ids);

    int insert(TUserWebReq tUserWebReq);

    int insertBatch(List<TUserWebReq> list);

    int update(TUserWebReq tUserWebReq);

    int updateBatch(List<TUserWebReq> list);

    int deleteById(Integer id);

    int deleteByEntity(TUserWebReq tUserWebReq);

    int deleteByIds(List<Integer> list);

    int countAll();

    int countByEntity(TUserWebReq tUserWebReq);

    List<TUserWebResp> selectAll();
}