package util;

import java.io.IOException;
import java.net.InetSocketAddress;

import net.spy.memcached.MemcachedClient;

public class MemcachedUtil {

	public  static MemcachedClient client = null;

	static {
		try {
			client = new MemcachedClient(new InetSocketAddress("127.0.0.1",
					11211));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// 1.创建静态变量
	private static MemcachedUtil mu;

	// 2.私有化构造
	private MemcachedUtil() {
	}

	// 3.提供对外访问的接口
	public static synchronized MemcachedUtil getInstance() {
		if (mu == null) {
			synchronized (MemcachedUtil.class) {
				if (mu == null) {
					mu = new MemcachedUtil();
				}
			}
		}
		return mu;
	}

}
