package cn.darkjrong.mix.common.security;

import cn.darkjrong.core.lang.constants.AuthConstant;
import cn.darkjrong.mix.common.config.AuthConfig;
import cn.darkjrong.redis.RedisUtils;
import cn.hutool.core.convert.Convert;
import cn.hutool.extra.spring.SpringUtil;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 *  重写Shiro的Cache保存读取
 * @author Rong.Jia
 * @date 2018/8/30 15:49
 */
@SuppressWarnings("unchecked")
public class CustomCache<K, V>  implements Cache<K,V> {

    private RedisUtils redisUtils = SpringUtil.getBean(RedisUtils.class);

    private AuthConfig authConfig = SpringUtil.getBean(AuthConfig.class);

    /**
     * 缓存的key名称获取为shiro:cache:account
     * @param key 缓存的key
     * @author Rong.Jia
     * @date 2018/9/4 18:33
     * @return java.lang.String
     */
    private String getKey(Object key) {

        //return AuthConstant.PREFIX_SHIRO_CACHE + jwtUtils.getClaim(key.toString(), AuthConstant.ACCOUNT);

        return AuthConstant.PREFIX_SHIRO_CACHE + key;

    }

    /**
     * 获取缓存
     */
    @Override
    public Object get(Object key) throws CacheException {
        if(!redisUtils.hasKey(this.getKey(key))){
            return null;
        }
        return redisUtils.get(this.getKey(key));
    }

    /**
     * 保存缓存
     */
    @Override
    public Object put(Object key, Object value) throws CacheException {

        // 读取配置文件，获取Redis的Shiro缓存过期时间
        Long shiroCacheExpireTime = authConfig.getShiroCacheExpireTime();

        // 设置Redis的Shiro缓存
        redisUtils.setEx(this.getKey(key), value, shiroCacheExpireTime, TimeUnit.SECONDS);
        return value;
    }

    /**
     * 移除缓存
     */
    @Override
    public Object remove(Object key) throws CacheException {
        if(!redisUtils.hasKey(this.getKey(key))){
            return null;
        }
        redisUtils.delete(this.getKey(key));
        return null;
    }

    /**
     * 清空所有缓存
     */
    @Override
    public void clear() throws CacheException {
        Set<String> keys = redisUtils.getRedisTemplate().keys("*");
        redisUtils.delete(keys);
    }

    /**
     * 缓存的个数
     */
    @Override
    public int size() {
        Set<String> keys = redisUtils.getRedisTemplate().keys("*");
        return Convert.toInt(redisUtils.getRedisTemplate().countExistingKeys(keys));
    }

    /**
     * 获取所有的key
     */
    @Override
    public Set keys() {
        return redisUtils.getRedisTemplate().keys("*");
    }

    /**
     * 获取所有的value
     */
    @Override
    public Collection values() {
        Set keys = this.keys();
        List<Object> values = new ArrayList<Object>();
        for (Object key : keys) {
            values.add(redisUtils.get(this.getKey(key)));
        }
        return values;
    }



}
