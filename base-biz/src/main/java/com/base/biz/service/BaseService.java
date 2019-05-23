package com.base.biz.service;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author wanghaobin
 * @create 2017-06-08 16:26
 */
public interface BaseService<T> {
    /**
     * 查询
     *
     * @param entity
     * @return
     */
    T selectOne(T entity);

    /**
     * 通过Id查询
     *
     * @param id
     * @return
     */
    T selectById(Object id);

    /**
     * 查询列表
     *
     * @param entity
     * @return
     */
    List<T> selectList(T entity);


    /**
     * 获取所有对象
     *
     * @return
     */
    List<T> selectListAll();

    /**
     * 查询总记录数
     *
     * @param entity
     * @return
     */
    Long selectCount(T entity);

    /**
     * 添加
     *
     * @param entity
     */
    void insert(T entity);


    /**
     * 插入 不插入null字段
     *
     * @param entity
     */
    void insertSelective(T entity);

    /**
     * 删除
     *
     * @param entity
     */
    void delete(T entity);

    /**
     * 根据Id删除
     *
     * @param id
     */
    void deleteById(Object id);


    /**
     * 根据id更新
     *
     * @param entity
     */
    void updateById(T entity);


    /**
     * 不update null
     *
     * @param entity
     */
    void updateSelectiveById(T entity);
}
