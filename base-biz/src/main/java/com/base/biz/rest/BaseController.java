package com.base.biz.rest;

import com.base.biz.biz.BaseBiz;
import com.base.common.dto.response.BaseRespDTO;
import com.base.common.dto.response.TableRespDTO;
import com.base.common.enums.ErrorCodeEnum;
import com.base.common.util.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Map;

/**
 * ${DESCRIPTION}
 *
 * @author wanghaobin
 * @create 2017-06-15 8:48
 */
@RestController
public class BaseController<Biz extends BaseBiz, Entity extends Serializable> {
    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected Biz baseBiz;

    @PostMapping(value = "")
    public BaseRespDTO<Entity> add(@RequestBody Entity entity) {
        return baseBiz.insertSelective(entity);
    }

    @GetMapping(value = "/{id}")
    public BaseRespDTO<Entity> get(@PathVariable Long id) {
        if (id == null) {
            return new BaseRespDTO(ErrorCodeEnum.ID_NOT_NULL);
        }
        Entity o = (Entity) baseBiz.selectById(id);
        if (o == null) {
            return new BaseRespDTO(ErrorCodeEnum.RECORD_NOT_EXISTS);
        }
        return new BaseRespDTO<>(ErrorCodeEnum.SUCCESS, null, o);
    }

    @PutMapping(value = "/{id}")
    public BaseRespDTO<Entity> update(@RequestBody Entity entity) {
        return baseBiz.updateSelectiveById(entity);
    }

    @DeleteMapping(value = "/{id}")
    public BaseRespDTO<Entity> remove(@PathVariable Long id) {
        if (id == null) {
            return new BaseRespDTO(ErrorCodeEnum.ID_NOT_NULL);
        }
        return baseBiz.deleteById(id);
    }

    @GetMapping(value = "")
    public BaseRespDTO<TableRespDTO<Entity>> list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        return baseBiz.selectByQuery(query);
    }
}
