package spring.configurations;

import static com.google.common.collect.Sets.newHashSet;

import org.springframework.context.annotation.Bean;

import play.Configuration;
import redis.RedisClient;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

@org.springframework.context.annotation.Configuration
public class RedisClientSpringConfiguration {

	private static final String SENTINEL_HOSTS_SEPARATOR = ",";

	@Bean(name = "redisClient")
	public RedisClient redisClient() {
		String[] redisSentinelsHosts = getStringProperty("redisSentinelHosts").split(SENTINEL_HOSTS_SEPARATOR);
		JedisSentinelPool jedisPool = new JedisSentinelPool(getStringProperty("redisSentinelMasterName"), newHashSet(redisSentinelsHosts),
				new JedisPoolConfig(), 20000);

		RedisClient client = new RedisClient();
		client.setJedisSentinelPool(jedisPool);
		return client;
	}

	private static String getStringProperty(String property) {
		return Configuration.root().getString(property);
	}
}
