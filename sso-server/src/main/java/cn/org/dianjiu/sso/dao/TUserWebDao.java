package cn.org.dianjiu.sso.dao;

import cn.org.dianjiu.sso.entity.TUserWeb;
import cn.org.dianjiu.sso.pojo.resp.TUserWebResp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import javax.validation.constraints.*;
import java.util.List;

/**
 * 站点操作(TUserWeb)表数据库访问层
 *
 * @author makejava
 * @since 2020-09-14 15:26:09
 */
@Mapper
public interface TUserWebDao {

    /**
     * 通过Id查询单个对象
     *
     * @param id 主键
     * @return 实例对象
     */
    TUserWeb getById(@NotNull Integer id);

    /**
     * 通过实体不为空的属性作为筛选条件查询对象列表
     *
     * @param tUserWeb 实例对象
     * @return 对象列表
     */
    List<TUserWeb> listByEntity(TUserWeb tUserWeb);

    /**
     * 通过实体不为空的属性作为筛选条件查询单个对象
     *
     * @param tUserWeb
     * @return 实例对象
     */
    TUserWeb getByEntity(TUserWeb tUserWeb);

    /**
     * 通过Id列表作为筛选条件查询对象列表，列表长度不为0
     *
     * @param list 实例对象
     * @return 对象列表
     */
    List<TUserWeb> listByIds(@NotEmpty List<Integer> list);

    /**
     * 新增实体属性不为null的记录
     *
     * @param tUserWeb 实例对象
     * @return 影响行数
     */
    int insert(@NotNull TUserWeb tUserWeb);

    /**
     * 批量新增所有列，列表长度不能为0，且列表id统一为null或者统一不为null
     *
     * @param list 实例对象
     * @return 影响行数
     */
    int insertBatch(@NotEmpty List<TUserWeb> list);

    /**
     * 通过主键修改实体属性不为null的列
     *
     * @param tUserWeb 实例对象
     * @return 影响行数
     */
    int update(@NotNull TUserWeb tUserWeb);

    /**
     * 通过表字段修改实体属性不为null的列
     *
     * @param where 实例对象
     * @param set   实例对象
     * @return 影响行数
     */
    int updateByField(@NotNull @Param("where") TUserWeb where, @NotNull @Param("set") TUserWeb set);

    /**
     * 通过主键修改实体列表，列表长度不能为0，注意：当实体属性为null时，对应的列也会别更新为null
     *
     * @param list 实例对象
     * @return 影响行数
     */
    int updateBatch(@NotEmpty List<TUserWeb> list);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(@NotNull Integer id);

    /**
     * 通过实体非空属性删除
     *
     * @param tUserWeb 实例对象
     * @return 影响行数
     */
    int deleteByEntity(@NotNull TUserWeb tUserWeb);

    /**
     * 通过主键列表删除，列表长度不能为0
     *
     * @param list 实例对象
     * @return 影响行数
     */
    int deleteByIds(@NotEmpty List<Integer> list);

    int countAll();

    int countByEntity(TUserWeb tUserWeb);

    @Select(" select * from t_user_web ")
    List<TUserWebResp> selectAll();
}