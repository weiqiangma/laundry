package com.mawkun.laundry.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mawkun.laundry.base.data.query.KindQuery;
import com.mawkun.laundry.base.entity.Kind;
import com.mawkun.laundry.base.service.KindService;
import com.mawkun.laundry.dao.KindDaoExt;
import com.mawkun.laundry.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class KindServiceExt extends KindService {

    @Autowired
    private KindDaoExt kindDaoExt;

    /**
     * 列表分页
     * @param query
     * @return
     */
    public PageInfo pageByEntity(KindQuery query) {
        query.init();
        PageHelper.startPage(query.getPageNo(), query.getPageSize());
        List<Kind> list = kindDaoExt.listByEntity(query);
        return new<Kind> PageInfo(list);
    }


    /**
     * 添加商品分类
     * @param kind
     * @param file
     * @return
     */
    public int insertWithPic(Kind kind, MultipartFile file) {
        MultipartFile[] files = {file};
        String images = ImageUtils.uploadImages(files);
        kind.setIconName(images);
        return kindDaoExt.insert(kind);
    }

    /**
     * 商品分类编辑
     * @param kind
     * @param file
     * @return
     */
    public int updateWithPic(Kind kind, MultipartFile file) {
        if(file != null) {
            MultipartFile[] files = {file};
            String images = ImageUtils.uploadImages(files);
            kind.setIconName(images);
        }
        return kindDaoExt.update(kind);
    }

    public Kind getByName(String name) {
        return kindDaoExt.selectByName(name);
    }

}