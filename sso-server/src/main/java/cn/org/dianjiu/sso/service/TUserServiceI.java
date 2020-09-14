package cn.org.dianjiu.sso.service;

import cn.org.dianjiu.sso.pojo.req.TUserReq;
import cn.org.dianjiu.sso.pojo.resp.TUserResp;

import java.util.List;

/**
 * 用户操作(TUser)表服务接口
 *
 * @author makejava
 * @since 2020-09-14 15:25:57
 */
public interface TUserServiceI {

    TUserResp getById(Integer id);

    TUserResp getByEntity(TUserReq tUserReq);

    List<TUserResp> listByEntity(TUserReq tUserReq);

    List<TUserResp> listByIds(List<Integer> ids);

    int insert(TUserReq tUserReq);

    int insertBatch(List<TUserReq> list);

    int update(TUserReq tUserReq);

    int updateBatch(List<TUserReq> list);

    int deleteById(Integer id);

    int deleteByEntity(TUserReq tUserReq);

    int deleteByIds(List<Integer> list);

    int countAll();

    int countByEntity(TUserReq tUserReq);
}