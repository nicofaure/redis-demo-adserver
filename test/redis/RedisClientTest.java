package redis;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

public class RedisClientTest {
	@Mock
	private JedisSentinelPool jedisSentinelPool;

	@InjectMocks
	private RedisClient redisClient;

	@Before
	public void setUp() {
		initMocks(this);
	}

	@Test
	public void whenGetKeyByValidIdThenTheValueShouldBeReturned() {
		Jedis jedis = buildJedis();
		when(jedisSentinelPool.getResource()).thenReturn(jedis);
		redisClient.hgetAll("key");
		verifyJedisSentinelPool(jedis);
		verify(jedis, times(1)).hgetAll("key");
	}

	@Test
	public void whenGetAKeyThenTheValueShouldBeReturned() {
		Jedis jedis = buildJedis();
		when(jedisSentinelPool.getResource()).thenReturn(jedis);
		redisClient.get("key");
		verifyJedisSentinelPool(jedis);
		verify(jedis, times(1)).get("key");
	}

	@Test
	public void whenDeleteAKeyThenTheValueShouldBeDeleted() {
		Jedis jedis = buildJedis();
		when(jedisSentinelPool.getResource()).thenReturn(jedis);
		redisClient.delete("key");
		verifyJedisSentinelPool(jedis);
		verify(jedis, times(1)).del("key");
	}

	@Test
	public void whenSetAValueThenValueShouldBeUpdated() {
		Jedis jedis = buildJedis();
		when(jedisSentinelPool.getResource()).thenReturn(jedis);
		redisClient.set("key", "");
		verifyJedisSentinelPool(jedis);
		verify(jedis, times(1)).set("key", "");
	}

	@Test
	public void whenGetKeysThenTheKeysShouldBeGetted() {
		Jedis jedis = buildJedis();
		when(jedisSentinelPool.getResource()).thenReturn(jedis);
		redisClient.keys("pattern");
		verifyJedisSentinelPool(jedis);
		verify(jedis, times(1)).keys("pattern");
	}

	private void verifyJedisSentinelPool(Jedis jedis) {
		verify(jedisSentinelPool, times(1)).getResource();
		verify(jedisSentinelPool, times(1)).returnResource(jedis);
	}

	private Jedis buildJedis() {
		return mock(Jedis.class);
	}


}
