package com.mawkun.laundry.controller;

import cn.pertech.common.abs.BaseController;
import com.mawkun.laundry.base.entity.Kind;
import com.mawkun.laundry.service.KindServiceExt;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * @author mawkun
 * @date 2020-08-19 21:43:16
 */
@RestController
@RequestMapping("/adm/kind")
public class KindController extends BaseController {
    
    @Autowired
    private KindServiceExt kindServiceExt;

    @RequestMapping("/get/{id}")
    public Kind getById(@PathVariable Long id) {
        Kind kind = kindServiceExt.getById(id);
        return kind!=null?kind:new Kind();
    }

    @RequestMapping("/get")
    public Kind getByEntity(Kind kind) {
        return kindServiceExt.getByEntity(kind);
    }

    @RequestMapping("/list")
    public List<Kind> list(Kind kind) {
        List<Kind> kindList = kindServiceExt.listByEntity(kind);
        return kindList;
    }

    @RequestMapping("/insert")
    public Kind insert(@RequestBody Kind kind){
        kindServiceExt.insert(kind);
        return kind;
    }

    @RequestMapping("/update")
    public int update(@RequestBody Kind kind){
        return kindServiceExt.update(kind);
    }

    @RequestMapping("/delete/{id}")
    public int deleteOne(@PathVariable Long id){
        return kindServiceExt.deleteById(id);
    }

    @RequestMapping("/delete")
    public int deleteBatch(@RequestBody List<Long> ids){
        int result = 0;
        if (ids!=null&&ids.size()>0) result = kindServiceExt.deleteByIds(ids);
        return result;
    }

}