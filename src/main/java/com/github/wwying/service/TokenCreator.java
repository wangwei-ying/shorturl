package com.github.wwying.service;

/**
 * description: 生成唯一ID
 * @author yingww
 * @date 2019-11-27
 */
public interface TokenCreator<T> {
    /**
     * description: 计算短链接唯一token
     * @author yingww
     * @date 2019-11-27
     * @param id
     * @return
     */
    String creatToken(T id);
}
