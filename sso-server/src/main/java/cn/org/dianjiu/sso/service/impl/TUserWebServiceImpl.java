package cn.org.dianjiu.sso.service.impl;

import cn.org.dianjiu.sso.dao.TUserWebDao;
import cn.org.dianjiu.sso.entity.TUserWeb;
import cn.org.dianjiu.sso.exception.BusinessException;
import cn.org.dianjiu.sso.pojo.req.TUserWebReq;
import cn.org.dianjiu.sso.pojo.resp.TUserWebResp;
import cn.org.dianjiu.sso.service.TUserWebServiceI;
import cn.org.dianjiu.sso.util.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 站点操作(TUserWeb)表服务实现类
 *
 * @author makejava
 * @since 2020-09-14 15:26:11
 */
@Slf4j
@Service
public class TUserWebServiceImpl implements TUserWebServiceI {

    @Autowired
    private TUserWebDao tUserWebDao;

    @Override
    public TUserWebResp getById(Integer id) {
        TUserWebResp tUserWebResp = new TUserWebResp();
        TUserWeb tUserWeb = tUserWebDao.getById(id);
        if (ObjectUtils.checkObjAllFieldsIsNull(tUserWeb)) {
            log.error("根据id【" + id + "】没有查到相关记录！");
            throw new BusinessException("400", "根据id【" + id + "】没有查到相关记录！");
        }
        ObjectUtils.copyProperties(tUserWeb, tUserWebResp);
        return tUserWebResp;
    }

    @Override
    public TUserWebResp getByEntity(TUserWebReq tUserWebReq) {
        TUserWebResp tUserWebResp = new TUserWebResp();
        TUserWeb tUserWeb = new TUserWeb();
        if (ObjectUtils.checkObjAllFieldsIsNull(tUserWebReq)) {
            log.error("入参对象不能为空！");
            throw new BusinessException("400", "入参对象不能为空！");
        }
        ObjectUtils.copyProperties(tUserWebReq, tUserWeb);
        TUserWeb tUserWeb1 = tUserWebDao.getByEntity(tUserWeb);
        if (ObjectUtils.checkObjAllFieldsIsNull(tUserWeb1)) {
            log.error("根据tUserWebReq【" + tUserWebReq + "】没有查到相关记录！");
            throw new BusinessException("400", "根据tUserWebReq【" + tUserWebReq + "】没有查到相关记录！");
        }
        ObjectUtils.copyProperties(tUserWeb1, tUserWebResp);
        return tUserWebResp;
    }

    @Override
    public List<TUserWebResp> listByEntity(TUserWebReq tUserWebReq) {
        List<TUserWebResp> list = new ArrayList<>();
        TUserWeb tUserWeb = new TUserWeb();
        if (ObjectUtils.checkObjAllFieldsIsNull(tUserWebReq)) {
            log.error("入参对象不能为空！");
            throw new BusinessException("400", "入参对象不能为空！");
        }
        ObjectUtils.copyProperties(tUserWebReq, tUserWeb);
        List<TUserWeb> tUserWebs = tUserWebDao.listByEntity(tUserWeb);
        if (null == tUserWebs || tUserWebs.isEmpty()) {
            log.error("根据tUserWebReq【" + tUserWebReq + "】没有查到相关记录！");
            throw new BusinessException("400", "根据tUserWebReq【" + tUserWebReq + "】没有查到相关记录！");
        }
        for (TUserWeb tUserWeb1 : tUserWebs) {
            TUserWebResp tUserWebResp = new TUserWebResp();
            if (ObjectUtils.checkObjAllFieldsIsNull(tUserWeb1)) {
                log.error("根据tUserWebReq【" + tUserWebReq + "】没有查到相关记录！");
                throw new BusinessException("400", "根据tUserWebReq【" + tUserWebReq + "】没有查到相关记录！");
            }
            ObjectUtils.copyProperties(tUserWeb1, tUserWebResp);
            list.add(tUserWebResp);
        }
        return list;
    }

    @Override
    public List<TUserWebResp> listByIds(List<Integer> ids) {
        List<TUserWebResp> list = new ArrayList<>();
        if (null == ids || ids.isEmpty()) {
            log.error("id集合不能为空！");
            throw new BusinessException("400", "id集合不能为空！");
        }
        List<TUserWeb> tUserWebs = tUserWebDao.listByIds(ids);
        if (null != tUserWebs && !tUserWebs.isEmpty()) {
            for (TUserWeb tUserWeb1 : tUserWebs) {
                TUserWebResp tUserWebResp = new TUserWebResp();
                if (ObjectUtils.checkObjAllFieldsIsNull(tUserWeb1)) {
                    log.error("根据ids【" + ids.toString() + "】没有查到相关记录！");
                    throw new BusinessException("400", "根据ids【" + ids.toString() + "】没有查到相关记录！");
                }
                ObjectUtils.copyProperties(tUserWeb1, tUserWebResp);
                list.add(tUserWebResp);
            }
        }
        return list;
    }

    @Override
    public int insert(TUserWebReq tUserWebReq) {
        TUserWeb tUserWeb = new TUserWeb();
        if (ObjectUtils.checkObjAllFieldsIsNull(tUserWebReq)) {
            log.error("入参对象不能为空！");
            throw new BusinessException("400", "入参对象不能为空！");
        }
        ObjectUtils.copyProperties(tUserWebReq, tUserWeb);
        Date date = new Date();
        tUserWeb.setCrateTime(date);
        tUserWeb.setUpdateTime(date);
        return tUserWebDao.insert(tUserWeb);
    }

    @Override
    public int insertBatch(List<TUserWebReq> list) {
        List<TUserWeb> tUserWebs = new ArrayList<>();
        if (null == list || list.isEmpty()) {
            log.error("执行批量插入的集合为空！");
            throw new BusinessException("400", "执行批量插入的集合为空！");
        }
        for (TUserWebReq tUserWebReq : list) {
            TUserWeb tUserWeb = new TUserWeb();
            if (ObjectUtils.checkObjAllFieldsIsNull(tUserWebReq)) {
                log.error("执行批量插入的集合为空！");
                throw new BusinessException("400", "执行批量插入的集合为空！");
            }
            ObjectUtils.copyProperties(tUserWebReq, tUserWeb);
            tUserWebs.add(tUserWeb);
        }
        return tUserWebDao.insertBatch(tUserWebs);
    }

    @Override
    public int update(TUserWebReq tUserWebReq) {
        TUserWeb tUserWeb = new TUserWeb();
        if (ObjectUtils.checkObjAllFieldsIsNull(tUserWebReq)) {
            log.error("入参对象不能为空！");
            throw new BusinessException("400", "入参对象不能为空！");
        }
        ObjectUtils.copyProperties(tUserWebReq, tUserWeb);
        tUserWeb.setUpdateTime(new Date());
        return tUserWebDao.update(tUserWeb);
    }

    @Override
    public int updateBatch(List<TUserWebReq> list) {
        List<TUserWeb> tUserWebs = new ArrayList<>();
        if (null == list || list.isEmpty()) {
            log.error("执行批量更新的集合为空！");
            throw new BusinessException("400", "执行批量更新的集合为空！");
        }
        for (TUserWebReq tUserWebReq : list) {
            TUserWeb tUserWeb = new TUserWeb();
            if (ObjectUtils.checkObjAllFieldsIsNull(tUserWebReq)) {
                log.error("执行批量更新的集合为空！");
                throw new BusinessException("400", "执行批量更新的集合为空！");
            }
            ObjectUtils.copyProperties(tUserWebReq, tUserWeb);
            tUserWebs.add(tUserWeb);
        }
        return tUserWebDao.updateBatch(tUserWebs);
    }

    @Override
    public int deleteById(Integer id) {
        return tUserWebDao.deleteById(id);
    }

    @Override
    public int deleteByEntity(TUserWebReq tUserWebReq) {
        TUserWeb tUserWeb = new TUserWeb();
        if (ObjectUtils.checkObjAllFieldsIsNull(tUserWebReq)) {
            log.error("入参对象不能为空！");
            throw new BusinessException("400", "入参对象不能为空！");
        }
        ObjectUtils.copyProperties(tUserWebReq, tUserWeb);
        return tUserWebDao.deleteByEntity(tUserWeb);
    }

    @Override
    public int deleteByIds(List<Integer> ids) {
        if (null == ids || ids.isEmpty()) {
            log.error("id集合不能为空！");
            throw new BusinessException("400", "id集合不能为空！");
        }
        return tUserWebDao.deleteByIds(ids);
    }

    @Override
    public int countAll() {
        return tUserWebDao.countAll();
    }

    @Override
    public int countByEntity(TUserWebReq tUserWebReq) {
        TUserWeb tUserWeb = new TUserWeb();
        if (ObjectUtils.checkObjAllFieldsIsNull(tUserWebReq)) {
            log.error("入参对象不能为空！");
            throw new BusinessException("400", "入参对象不能为空！");
        }
        ObjectUtils.copyProperties(tUserWebReq, tUserWeb);
        return tUserWebDao.countByEntity(tUserWeb);
    }

    @Override
    public List<TUserWebResp> selectAll() {
        return tUserWebDao.selectAll();
    }

}