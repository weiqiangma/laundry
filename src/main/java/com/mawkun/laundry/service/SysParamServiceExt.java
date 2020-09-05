package com.mawkun.laundry.service;

import com.mawkun.laundry.base.dao.SysParamDao;
import com.mawkun.laundry.base.entity.SysParam;
import com.mawkun.laundry.base.service.SysParamService;
import com.mawkun.laundry.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class SysParamServiceExt extends SysParamService {

    @Autowired
    private SysParamDao sysParamDao;

    public int updateWithPic(SysParam param, MultipartFile[] files) {
        if(files != null && files.length > 0) {
            String imagePath = ImageUtils.uploadImages(files);
            param.setSysValue(imagePath);
        }
        return sysParamDao.update(param);
    }
}
