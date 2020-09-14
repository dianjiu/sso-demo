package cn.org.dianjiu.sso.service.impl;

import cn.org.dianjiu.sso.dao.TUserDao;
import cn.org.dianjiu.sso.entity.TUser;
import cn.org.dianjiu.sso.exception.BusinessException;
import cn.org.dianjiu.sso.pojo.req.TUserReq;
import cn.org.dianjiu.sso.pojo.resp.TUserResp;
import cn.org.dianjiu.sso.service.TUserServiceI;
import cn.org.dianjiu.sso.util.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户操作(TUser)表服务实现类
 *
 * @author makejava
 * @since 2020-09-14 15:25:59
 */
@Slf4j
@Service
public class TUserServiceImpl implements TUserServiceI {

    @Autowired
    private TUserDao tUserDao;

    @Override
    public TUserResp getById(Integer id) {
        TUserResp tUserResp = new TUserResp();
        TUser tUser = tUserDao.getById(id);
        if (ObjectUtils.checkObjAllFieldsIsNull(tUser)) {
            log.error("根据id【" + id + "】没有查到相关记录！");
            throw new BusinessException("400", "根据id【" + id + "】没有查到相关记录！");
        }
        ObjectUtils.copyProperties(tUser, tUserResp);
        return tUserResp;
    }

    @Override
    public TUserResp getByEntity(TUserReq tUserReq) {
        TUserResp tUserResp = new TUserResp();
        TUser tUser = new TUser();
        if (ObjectUtils.checkObjAllFieldsIsNull(tUserReq)) {
            log.error("入参对象不能为空！");
            throw new BusinessException("400", "入参对象不能为空！");
        }
        ObjectUtils.copyProperties(tUserReq, tUser);
        TUser tUser1 = tUserDao.getByEntity(tUser);
        if (ObjectUtils.checkObjAllFieldsIsNull(tUser1)) {
            log.error("根据tUserReq【" + tUserReq + "】没有查到相关记录！");
            throw new BusinessException("400", "根据tUserReq【" + tUserReq + "】没有查到相关记录！");
        }
        ObjectUtils.copyProperties(tUser1, tUserResp);
        return tUserResp;
    }

    @Override
    public List<TUserResp> listByEntity(TUserReq tUserReq) {
        List<TUserResp> list = new ArrayList<>();
        TUser tUser = new TUser();
        if (ObjectUtils.checkObjAllFieldsIsNull(tUserReq)) {
            log.error("入参对象不能为空！");
            throw new BusinessException("400", "入参对象不能为空！");
        }
        ObjectUtils.copyProperties(tUserReq, tUser);
        List<TUser> tUsers = tUserDao.listByEntity(tUser);
        if (null == tUsers || tUsers.isEmpty()) {
            log.error("根据tUserReq【" + tUserReq + "】没有查到相关记录！");
            throw new BusinessException("400", "根据tUserReq【" + tUserReq + "】没有查到相关记录！");
        }
        for (TUser tUser1 : tUsers) {
            TUserResp tUserResp = new TUserResp();
            if (ObjectUtils.checkObjAllFieldsIsNull(tUser1)) {
                log.error("根据tUserReq【" + tUserReq + "】没有查到相关记录！");
                throw new BusinessException("400", "根据tUserReq【" + tUserReq + "】没有查到相关记录！");
            }
            ObjectUtils.copyProperties(tUser1, tUserResp);
            list.add(tUserResp);
        }
        return list;
    }

    @Override
    public List<TUserResp> listByIds(List<Integer> ids) {
        List<TUserResp> list = new ArrayList<>();
        if (null == ids || ids.isEmpty()) {
            log.error("id集合不能为空！");
            throw new BusinessException("400", "id集合不能为空！");
        }
        List<TUser> tUsers = tUserDao.listByIds(ids);
        if (null != tUsers && !tUsers.isEmpty()) {
            for (TUser tUser1 : tUsers) {
                TUserResp tUserResp = new TUserResp();
                if (ObjectUtils.checkObjAllFieldsIsNull(tUser1)) {
                    log.error("根据ids【" + ids.toString() + "】没有查到相关记录！");
                    throw new BusinessException("400", "根据ids【" + ids.toString() + "】没有查到相关记录！");
                }
                ObjectUtils.copyProperties(tUser1, tUserResp);
                list.add(tUserResp);
            }
        }
        return list;
    }

    @Override
    public int insert(TUserReq tUserReq) {
        TUser tUser = new TUser();
        if (ObjectUtils.checkObjAllFieldsIsNull(tUserReq)) {
            log.error("入参对象不能为空！");
            throw new BusinessException("400", "入参对象不能为空！");
        }
        ObjectUtils.copyProperties(tUserReq, tUser);
        Date date = new Date();
        tUser.setCrateTime(date);
        tUser.setUpdateTime(date);
        return tUserDao.insert(tUser);
    }

    @Override
    public int insertBatch(List<TUserReq> list) {
        List<TUser> tUsers = new ArrayList<>();
        if (null == list || list.isEmpty()) {
            log.error("执行批量插入的集合为空！");
            throw new BusinessException("400", "执行批量插入的集合为空！");
        }
        for (TUserReq tUserReq : list) {
            TUser tUser = new TUser();
            if (ObjectUtils.checkObjAllFieldsIsNull(tUserReq)) {
                log.error("执行批量插入的集合为空！");
                throw new BusinessException("400", "执行批量插入的集合为空！");
            }
            ObjectUtils.copyProperties(tUserReq, tUser);
            tUsers.add(tUser);
        }
        return tUserDao.insertBatch(tUsers);
    }

    @Override
    public int update(TUserReq tUserReq) {
        TUser tUser = new TUser();
        if (ObjectUtils.checkObjAllFieldsIsNull(tUserReq)) {
            log.error("入参对象不能为空！");
            throw new BusinessException("400", "入参对象不能为空！");
        }
        ObjectUtils.copyProperties(tUserReq, tUser);
        tUser.setUpdateTime(new Date());
        return tUserDao.update(tUser);
    }

    @Override
    public int updateBatch(List<TUserReq> list) {
        List<TUser> tUsers = new ArrayList<>();
        if (null == list || list.isEmpty()) {
            log.error("执行批量更新的集合为空！");
            throw new BusinessException("400", "执行批量更新的集合为空！");
        }
        for (TUserReq tUserReq : list) {
            TUser tUser = new TUser();
            if (ObjectUtils.checkObjAllFieldsIsNull(tUserReq)) {
                log.error("执行批量更新的集合为空！");
                throw new BusinessException("400", "执行批量更新的集合为空！");
            }
            ObjectUtils.copyProperties(tUserReq, tUser);
            tUsers.add(tUser);
        }
        return tUserDao.updateBatch(tUsers);
    }

    @Override
    public int deleteById(Integer id) {
        return tUserDao.deleteById(id);
    }

    @Override
    public int deleteByEntity(TUserReq tUserReq) {
        TUser tUser = new TUser();
        if (ObjectUtils.checkObjAllFieldsIsNull(tUserReq)) {
            log.error("入参对象不能为空！");
            throw new BusinessException("400", "入参对象不能为空！");
        }
        ObjectUtils.copyProperties(tUserReq, tUser);
        return tUserDao.deleteByEntity(tUser);
    }

    @Override
    public int deleteByIds(List<Integer> ids) {
        if (null == ids || ids.isEmpty()) {
            log.error("id集合不能为空！");
            throw new BusinessException("400", "id集合不能为空！");
        }
        return tUserDao.deleteByIds(ids);
    }

    @Override
    public int countAll() {
        return tUserDao.countAll();
    }

    @Override
    public int countByEntity(TUserReq tUserReq) {
        TUser tUser = new TUser();
        if (ObjectUtils.checkObjAllFieldsIsNull(tUserReq)) {
            log.error("入参对象不能为空！");
            throw new BusinessException("400", "入参对象不能为空！");
        }
        ObjectUtils.copyProperties(tUserReq, tUser);
        return tUserDao.countByEntity(tUser);
    }

}