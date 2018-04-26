package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取properties文件的单例类
 */
public class ConfigManager {

	// 01.创建本类的静态变量
	private static ConfigManager manager = new ConfigManager();

	private static Properties properties;

	// 02.私有化构造
	private ConfigManager() {
		// 实例化Properties对象
		properties = new Properties();
		InputStream stream = ConfigManager.class.getClassLoader()
				.getResourceAsStream("jdbc.properties");
		// 加载properties文件
		try {
			properties.load(stream);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try { // 关闭流
				stream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 03.对外提供访问的接口
	public static synchronized ConfigManager getInstance() {
		return manager;
	}

	// 04.让用户传递一个文件中的key 我们返回文件中的value
	public static String getValue(String key) {
		return properties.getProperty(key);
	}

}
