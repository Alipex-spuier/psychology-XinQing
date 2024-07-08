package com.music.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.music.entity.Expert;
import com.music.entity.User;

public interface ExpertService extends IService<Expert> {
    public String registerService(Expert expert);
}
