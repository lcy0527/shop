package service;

import java.io.Serializable;
import java.util.List;

import net.spy.memcached.MemcachedClient;

public interface BaseService<T> {
	
	/**
	 * 新增
	 * @return 
	 */
	int add(T t);

	/**
	 * 删除
	 */
	int delete(Serializable id);

	/**
	 * 修改
	 */
	int update(T t);

	/**
	 * 查询所有
	 */
	List<T> findAll();

	/**
	 * 根据id查询指定的信息
	 */
	T findById(Serializable id);
	
	
}
