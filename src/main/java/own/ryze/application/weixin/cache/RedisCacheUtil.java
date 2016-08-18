package own.ryze.application.weixin.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * redis缓存工具
 * 
 * @author LCY
 *
 */
@Component
@Slf4j
public class RedisCacheUtil
{
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	public String get(final String key)
	{
		try
		{
			final String value = stringRedisTemplate.opsForValue().get(key);
			log.info("redis get : key{},value{}", key, value);
			return value;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public boolean put(final String key, final String value)
	{
		try
		{
			stringRedisTemplate.opsForValue().set(key, value);
			log.info("redis put : key{},value{}", key, value);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean delete(final String key)
	{
		try
		{
			stringRedisTemplate.delete(key);
			log.info("redis delete : key{}", key);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * key是否存在
	 * 
	 * @param key
	 * @return
	 */
	public boolean exists(final String key)
	{
		return stringRedisTemplate.execute((RedisConnection redisConnection) ->
		{
			final Boolean exists = redisConnection.exists(key.getBytes());
			log.info("redis exists : key{} is {}", key, exists);
			return exists;
		});
	}

	/**
	 * 刷新缓存
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean refresh(final String key, final String value)
	{
		if (exists(key)) delete(key);
		return put(key, value);
	}

	public long size()
	{
		return stringRedisTemplate.execute((RedisConnection redisConnection) ->
		{
			final long size = redisConnection.dbSize();
			log.info("redis size : {}", size);
			return size;
		});
	}

	public boolean flush()
	{
		final String result = stringRedisTemplate.execute((RedisConnection redisConection) ->
		{
			redisConection.flushDb();
			log.info("redis flush");
			return "ok";
		});
		return "ok".equals(result) ? true : false;
	}

	public String ping()
	{
		return stringRedisTemplate.execute((RedisConnection redisConnection) ->
		{
			final String ping = redisConnection.ping();
			log.info("redis ping : {}", ping);
			return ping;
		});
	}
}
