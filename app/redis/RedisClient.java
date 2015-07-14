package redis;

import java.util.Map;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import play.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

/**
 * Created by jualmeyda on 5/6/15.
 */
@Setter
@Getter
public class RedisClient {

	private JedisSentinelPool jedisSentinelPool;

	public Map<String, String> hgetAll(String key) {
		Jedis jedis = getResource();
		try {
			Logger.info("Jedis hgetAll");
			return jedis.hgetAll(key);
		} finally {
			Logger.info("Returning resource");
			returnResource(jedis);
		}
	}

	public void hmSet(String key, Map<String, String> map) {
		Jedis jedis = getResource();
		try {
			jedis.hmset(key, map);
		} finally {
			returnResource(jedis);
		}
	}

	public String get(String key) {
		Jedis jedis = getResource();
		try {
			return jedis.get(key);
		} finally {
			returnResource(jedis);
		}
	}

	public void delete(String key) {
		Jedis jedis = getResource();
		try {
			jedis.del(key);
		} finally {
			returnResource(jedis);
		}
	}

	public void set(String key, String value) {
		Jedis jedis = getResource();
		try {
			jedis.set(key, value);
		} finally {
			returnResource(jedis);
		}
	}

	public void expire(String key, int seconds) {
		Jedis jedis = getResource();
		try {
			jedis.expire(key, seconds);
		} finally {
			returnResource(jedis);
		}
	}

	public Set<String> keys(String pattern) {
		Jedis jedis = getResource();
		try {
			return jedis.keys(pattern);
		} finally {
			returnResource(jedis);
		}
	}

	public void flushAll() {
		Jedis jedis = getResource();
		try {
			jedis.flushAll();
		} finally {
			returnResource(jedis);
		}
	}

	private void returnResource(Jedis jedis) {
		jedisSentinelPool.returnResource(jedis);
	}

	private Jedis getResource() {
		return jedisSentinelPool.getResource();
	}
}
