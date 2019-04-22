/**
 * @author helen
 * @date 2018年8月6日
 */
package common;

import redis.clients.jedis.Jedis;

/**
 * @Description:处理redis缓存数据
 */
public class MyRedis {
	MyConfig myConfig = new MyConfig();
	Jedis jedis;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MyRedis myRedis = new MyRedis();
		myRedis.redis_del("55266-CART");
	}
	
	/*从redis中删除数据
	 * @key	redis的key
	 * */
	public void redis_del(String key) {
		this.redis_connect();
		this.jedis.del(key);
		this.close_redis();
	}
	
	/**链接redis**/
	private void redis_connect() {
		try {
			//this.jedis = new Jedis("192.168.1.236",6380);
			this.jedis = new Jedis(myConfig.getKeys("redis_addr"),Integer.valueOf(myConfig.getKeys("redis_port")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*关闭redis链接*/
	private void close_redis() {
		this.jedis.close();
	}

}
