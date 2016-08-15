package own.ryze.application.weixin.config;

import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import lombok.extern.slf4j.Slf4j;

/**
 * 修改redis默认json序列化工具
 * 
 * @author LCY
 *
 */
@Slf4j
@Configuration
public class CacheConfig
{
	@Bean
	public RedisSerializer<Object> fastJson2JsonRedisSerializer()
	{
		log.info("FastjsonRedisSerializer初始化");
		return new FastJson2JsonRedisSerializer<Object>(Object.class);
	}

	@Bean
	public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory factory,
			RedisSerializer<Object> fastJson2JsonRedisSerializer)
	{
		log.info("StringRedisTeamplate初始化");
		StringRedisTemplate template = new StringRedisTemplate(factory);

		template.setValueSerializer(fastJson2JsonRedisSerializer);

		//初始化
		template.afterPropertiesSet();
		return template;
	}

	@Bean
	public CacheManager cacheManager(StringRedisTemplate stringRedisTemplate)
	{
		log.info("缓存管理器初始化");
		RedisCacheManager redisCacheManager = new RedisCacheManager(stringRedisTemplate);
		long seconds = TimeUnit.MINUTES.toSeconds(30);
		//缓存30分钟
		redisCacheManager.setDefaultExpiration(seconds);
		redisCacheManager.afterPropertiesSet();
		
		return redisCacheManager;
	}
}

/**
 * fastjsonredis序列化
 * 
 * @author LCY
 *
 * @param <T>
 */
class FastJson2JsonRedisSerializer<T> implements RedisSerializer<T>
{
	public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

	private Class<T> clazz;

	public FastJson2JsonRedisSerializer(Class<T> clazz)
	{
		super();
		this.clazz = clazz;
	}

	@Override
	public byte[] serialize(T t) throws SerializationException
	{
		if (t == null) return new byte[0];
		return JSON.toJSONString(t, SerializerFeature.WriteClassName).getBytes(DEFAULT_CHARSET);
	}

	@Override
	public T deserialize(byte[] b) throws SerializationException
	{
		if (b == null || b.length == 0) return null;
		String s = new String(b, DEFAULT_CHARSET);

		return JSON.parseObject(s, clazz);
	}

}
