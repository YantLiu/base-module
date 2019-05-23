package com.base.biz.biz;

import com.base.common.dto.response.BaseRespDTO;
import com.base.common.dto.response.TableRespDTO;
import com.base.common.enums.ErrorCodeEnum;
import com.base.common.util.Query;
import com.base.common.util.ValidateUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

/**
 * Created by Mr.AG
 * Date: 17/1/13
 * Time: 15:13
 * Version 1.0.0
 */
@Slf4j
public abstract class BaseBiz<M extends Mapper<T>, T extends Serializable> {
    @Autowired
    protected M mapper;

    public void setMapper(M mapper) {
        this.mapper = mapper;
    }

    public T selectOne(T entity) {
        return mapper.selectOne(entity);
    }


    public T selectById(Object id) {
        return mapper.selectByPrimaryKey(id);
    }


    public List<T> selectList(T entity) {
        return mapper.select(entity);
    }


    public List<T> selectListAll() {
        return mapper.selectAll();
    }


    public Long selectCount(T entity) {
        return Long.valueOf(mapper.selectCount(entity));
    }


    public BaseRespDTO insert(T entity) {
        //EntityUtils.setCreatAndUpdatInfo(entity);
        if (1 == mapper.insert(entity)) {
            return new BaseRespDTO<>(ErrorCodeEnum.ADD_SUCCESS);
        }
        return new BaseRespDTO<>(ErrorCodeEnum.ADD_FAILED);
    }


    public BaseRespDTO insertSelective(T entity) {
        //EntityUtils.setCreatAndUpdatInfo(entity);
        if (1 == mapper.insertSelective(entity)) {
            return new BaseRespDTO<>(ErrorCodeEnum.ADD_SUCCESS);
        }
        return new BaseRespDTO<>(ErrorCodeEnum.ADD_FAILED);
    }

    public BaseRespDTO deleteById(Object id) {
        if (1 == mapper.deleteByPrimaryKey(id)) {
            return new BaseRespDTO<>(ErrorCodeEnum.DELETE_SUCCESS);
        }
        return new BaseRespDTO<>(ErrorCodeEnum.DELETE_FAILED);
    }


    public BaseRespDTO updateById(T entity) {
        //EntityUtils.setUpdatedInfo(entity);
        if (1 == mapper.updateByPrimaryKey(entity)) {
            return new BaseRespDTO<>(ErrorCodeEnum.UPDATE_SUCCESS);
        }

        return new BaseRespDTO<>(ErrorCodeEnum.UPDATE_FAILED);
    }


    public BaseRespDTO updateSelectiveById(T entity) {
        //EntityUtils.setUpdatedInfo(entity);
        if (1 == mapper.updateByPrimaryKeySelective(entity)) {
            return new BaseRespDTO<>(ErrorCodeEnum.UPDATE_SUCCESS);
        }
        return new BaseRespDTO<>(ErrorCodeEnum.UPDATE_FAILED);
    }

    public List<T> selectByExample(Object example) {
        return mapper.selectByExample(example);
    }

    public int selectCountByExample(Object example) {
        return mapper.selectCountByExample(example);
    }

    public BaseRespDTO<TableRespDTO<T>> selectByQuery(Query query) {
        Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        Example example = new Example(clazz);
        if (!ValidateUtils.isCollectionEmpty(query.entrySet())) {
            Example.Criteria criteria = example.createCriteria();
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
}
