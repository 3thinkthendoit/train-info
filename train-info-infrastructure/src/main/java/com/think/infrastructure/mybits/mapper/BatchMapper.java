package com.think.infrastructure.mybits.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Collection;

/**
 * @author hg
 * @date 2022-04-08日 16:22
 */
public interface BatchMapper<T> extends BaseMapper<T> {

    /**
     * 批量插入 仅适用于mysql
     * @param list 实体列表
     * @return 影响行数
     */
    Integer insertBatchSomeColumn(Collection<T> list);
}
