package com.base.biz.biz;

import com.base.common.constant.CommonConstants;
import com.base.common.dto.response.BaseRespDTO;
import com.base.common.dto.response.TableRespDTO;
import com.base.common.enums.ErrorCodeEnum;
import com.base.common.enums.ValidStatusEnum;
import com.base.common.exception.MyRuntimeException;
import com.base.common.util.Query;
import com.base.common.util.ValidateUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by Mr.AG
 * Date: 17/1/13
 * Time: 15:13
 * Version 1.0.0
 */
@Slf4j
public class SoftDeleteBaseBiz<M extends Mapper<T>, T extends Serializable> extends BaseBiz<M, T> {
    private Method setValidStatus;
    private Method setId;
    private Method getId;
    private Class<T> clz;

    public SoftDeleteBaseBiz() {
        try {
            clz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
            setValidStatus = clz.getMethod("setValidStatus", Integer.class);
            setId = clz.getMethod("setId", Long.class);
            getId = clz.getMethod("getId");
        } catch (NoSuchMethodException e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public void setMapper(M mapper) {
        this.mapper = mapper;
    }

    @Override
    public T selectOne(T entity) {
        try {
            setValidStatus.invoke(entity, ValidStatusEnum.YES.getCode());
            return mapper.selectOne(entity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public T selectById(Object id) {
        try {
            T entity = clz.newInstance();
            setId.invoke(entity, id);
            setValidStatus.invoke(entity, ValidStatusEnum.YES.getCode());
            return mapper.selectOne(entity);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public List<T> selectList(T entity) {
        try {
            setValidStatus.invoke(entity, ValidStatusEnum.YES.getCode());
            return mapper.select(entity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            log.error(e.getMessage(), e);
        }
        return Collections.emptyList();
    }

    @Override
    public List<T> selectListAll() {
        try {
            T entity = clz.newInstance();
            setValidStatus.invoke(entity, ValidStatusEnum.YES.getCode());
            return mapper.select(entity);
        } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
            log.error(e.getMessage(), e);
        }
        return Collections.emptyList();
    }

    @Override
    public Long selectCount(T entity) {
        try {
            setValidStatus.invoke(entity, ValidStatusEnum.YES.getCode());
            return Long.valueOf(mapper.selectCount(entity));
        } catch (IllegalAccessException | InvocationTargetException e) {
            log.error(e.getMessage(), e);
        }
        return 0L;
    }

    @Override
    public BaseRespDTO insert(T entity) {
        try {
            setValidStatus.invoke(entity, ValidStatusEnum.YES.getCode());
            //EntityUtils.setCreatAndUpdatInfo(entity);
            if (1 == mapper.insert(entity)) {
                return new BaseRespDTO(ErrorCodeEnum.ADD_SUCCESS);
            }
            return new BaseRespDTO(ErrorCodeEnum.ADD_FAILED);
        } catch (IllegalAccessException | InvocationTargetException e) {
            log.error(e.getMessage(), e);
            throw new MyRuntimeException(ErrorCodeEnum.ADD_FAILED.getMsg());
        }
    }

    @Override
    public BaseRespDTO insertSelective(T entity) {
        try {
            setValidStatus.invoke(entity, ValidStatusEnum.YES.getCode());
            //EntityUtils.setCreatAndUpdatInfo(entity);
            if (1 == mapper.insertSelective(entity)) {
                return new BaseRespDTO(ErrorCodeEnum.ADD_SUCCESS);
            }
            return new BaseRespDTO(ErrorCodeEnum.ADD_FAILED);
        } catch (IllegalAccessException | InvocationTargetException e) {
            log.error(e.getMessage(), e);
            throw new MyRuntimeException(ErrorCodeEnum.ADD_FAILED.getMsg());
        }
    }

    /**
     * @param id
     * @return com.alt.base.common.dto.response.BaseRespDTO
     * @description 彻底删除记录
     * @author liuyanting
     * @date 2019/3/21
     */
    public BaseRespDTO realDeleteById(Object id) {
        return super.deleteById(id);
    }

    /**
     * @param id
     * @return com.alt.base.common.dto.response.BaseRespDTO
     * @description 软删除
     * @author liuyanting
     * @date 2019/3/21
     */
    @Override
    public BaseRespDTO deleteById(Object id) {
        try {
            T entity = clz.newInstance();
            setId.invoke(entity, id);
            setValidStatus.invoke(entity, ValidStatusEnum.NO.getCode());
            BaseRespDTO ret = super.updateSelectiveById(entity);
            if (ret.isSuccess()) {
                return new BaseRespDTO(ErrorCodeEnum.DELETE_SUCCESS);
            }
            return new BaseRespDTO(ErrorCodeEnum.DELETE_FAILED);
        } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
            log.error(e.getMessage(), e);
            throw new MyRuntimeException(ErrorCodeEnum.DELETE_FAILED.getMsg());
        }
    }

    @Override
    public List<T> selectByExample(Object example) {
        Example.Criteria criteria = ((Example) example).createCriteria();
        criteria.andEqualTo(CommonConstants.VALID_STATUS, ValidStatusEnum.YES.getCode());
        return mapper.selectByExample(example);
    }

    @Override
    public int selectCountByExample(Object example) {
        Example.Criteria criteria = ((Example) example).createCriteria();
        criteria.andEqualTo(CommonConstants.VALID_STATUS, ValidStatusEnum.YES.getCode());
        return mapper.selectCountByExample(example);
    }

    @Override
    public BaseRespDTO<TableRespDTO<T>> selectByQuery(Query query) {
        Example example = new Example(clz);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo(CommonConstants.VALID_STATUS, ValidStatusEnum.YES.getCode());
        if (!ValidateUtils.isCollectionEmpty(query.entrySet())) {
            for (Map.Entry<String, Object> entry : query.entrySet()) {
                criteria.andLike(entry.getKey(), "%" + entry.getValue().toString() + "%");
            }
        }
        Page<Object> page = PageHelper.startPage(query.getPage(), query.getLimit());
        List<T> list = mapper.selectByExample(example);

        TableRespDTO<T> data = new TableRespDTO<>();
        data.count(page.getPageNum(), page.getPageSize(), page.getTotal());
        data.setRecords(list);

        return new BaseRespDTO(ErrorCodeEnum.SUCCESS, null, data);
    }

    /**
     * @param entity
     * @param isEdit 是否编辑
     * @return boolean
     * @description 判断是否唯一, !必须以ID为主键
     * @author liuyanting
     * @date 2019/1/9
     */
    public BaseRespDTO isUnique(T entity, boolean isEdit) {
        try {
            Object id = getId.invoke(entity);
            setId.invoke(entity, new Object[]{null});

            List<T> list = this.selectList(entity);
            BaseRespDTO ret = new BaseRespDTO();
            if (ValidateUtils.isCollectionEmpty(list)) {//没有记录
                ret.setCode(ErrorCodeEnum.SUCCESS);
            } else if (isEdit && list.size() == 1 && getId.invoke(list.get(0)).equals(id)) {//编辑时, 只有一条id相同的记录
                ret.setCode(ErrorCodeEnum.SUCCESS);
                //将旧的记录赋给查询实体
                BeanUtils.copyProperties(list.get(0), entity);
            } else {
                ret.setCode(ErrorCodeEnum.DATA_REPEAT);
            }
            return ret;
        } catch (IllegalAccessException | InvocationTargetException e) {
            log.error(ErrorCodeEnum.UNIQUE_FAILED.getMsg(), e);
            throw new MyRuntimeException(ErrorCodeEnum.UNIQUE_FAILED.getMsg());
        }
    }
}
