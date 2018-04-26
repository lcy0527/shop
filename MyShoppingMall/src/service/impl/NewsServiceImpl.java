package service.impl;

import java.io.Serializable;
import java.util.List;

import net.spy.memcached.MemcachedClient;

import dao.NewsDao;
import dao.impl.NewsDaoImpl;
import bean.News;
import service.NewsService;
import util.MemcachedUtil;

public class NewsServiceImpl implements NewsService {
	MemcachedClient client = MemcachedUtil.getInstance().client;
	NewsDao dao = new NewsDaoImpl();

	@Override
	public int delete(Serializable id) {
		return dao.delete(id);
	}

	@Override
	public int update(News t) {
		List<News> list =(List<News>) client.get("list");
		if(list!=null){
			int tid = t.getId();
			for (News news : list) {
				if(news.getId()==tid){
					news.setContent(t.getContent());
					news.setImg(t.getImg());
					news.setNewstype(t.getNewstype());
					news.setTitle(t.getTitle());
					break;
				}
			}
			client.replace("list", 60*60, list);
		}
		
		return dao.update(t);
	}

	@Override
	public List<News> findAll() {
		List<News> list = null;
		if (client.get("list") == null) {
			System.out.println("List<News>进入了数据库");
			list = dao.findAll();
			client.set("list",60*60, list);
		} else {
			System.out.println("List<News>进入了缓存");
			list = (List<News>) client.get("list");
		}

		return list;
	}

	@Override
	public News findById(Serializable id) {
		if (client.get("myNew") == null) {
			System.out.println("findById进入了数据库");
			News news = dao.findById(id);
			client.set("myNew", 60*60, news);// 单位 s
			return news;
		} else {
			System.out.println("findById进入了缓存");
			return (News) client.get("myNew");
		}
	}



	@Override
	public int add(News t) {
		int num=dao.add(t);
		//给memcached赋值
		List<News> list =dao.findAll();
		client.replace("list", 60*60, list);
		
		return num;
	}

}
