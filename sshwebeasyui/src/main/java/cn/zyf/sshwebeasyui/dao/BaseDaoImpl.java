package cn.zyf.sshwebeasyui.dao;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import cn.zyf.sshwebeasyui.model.Pager;
import cn.zyf.sshwebeasyui.model.SystemContext;

/**
 * BaseDaoImpl 操作数据库的基本方法
 * @author ray_cn
 * @param <T>
 */
public class BaseDaoImpl<T> implements BaseDao<T> {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Class<T> clazz;
	
	@SuppressWarnings("unchecked")
	public Class<T> getClazz(){
		//this不是指BaseDaoImpl，而是调用getClazz的对象，即BaseDaoImpl的子类
		//getGenericSuperclass 拿到父类的class类型
//		Type type = this.getClass().getGenericSuperclass();
//		//转换成子类
//		ParameterizedType pt = (ParameterizedType) type;
//		//拿到泛型类型的数组
//		Type[] types = pt.getActualTypeArguments();
//		Class<T> clz = (Class<T>) types[0]; 
		if(clazz==null) {
			clazz = (Class<T>)((((ParameterizedType)
					(this.getClass().getGenericSuperclass()))
					.getActualTypeArguments())[0]);
		}
		return clazz;
	}
	
	/**
	 * 获取数据连接会话的方法
	 */
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public T add(T t) {
		getSession().save(t);
		return t;
	}

	@Override
	public void delete(int id) {
		getSession().delete(load(id));
	}

	@Override
	public void update(T t) {
		getSession().update(t);
	}

	@Override
	public T load(int id) {
		return getSession().load(getClazz(), id);
	}

	/**
	 * 用hql语句查询多条记录，没有分页，返回list
	 * @param hql
	 * @param objs 替换hql语句中?占位符的实参
	 * @param alias 替换hql语句中:name占位符的实参
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> list(String hql,Object[] objs,Map<String, Object>alias){
		hql = initSort(hql);//初始化排序规则
		Query<T> query = getSession().createQuery(hql);
		setParameter(query,objs);//将hql的?占位符替换
		setAliasParameter(query,alias);
		return query.list();
	}
	
	/**
	 * 将hql的:name占位符替换
	 * @param query
	 * @param alias
	 */
	@SuppressWarnings("rawtypes")
	private void setAliasParameter(Query<T> query, Map<String, Object> alias) {
		if(alias!=null) {
			Set<String> keys = alias.keySet();
			for(String key:keys) {
				Object val = alias.get(key);
				if(val instanceof Collection) {
					query.setParameterList(key, (Collection)val);
				}else {
					query.setParameter(key, val);
				}
			}
		}
	}

	/**
	 * 将hql的?占位符替换
	 * @param query
	 * @param objs
	 */
	private void setParameter(Query<T> query, Object[] objs) {
		if(objs!=null&&objs.length > 0) {
			int index = 0;
			for(Object obj:objs) {
				query.setParameter(index++, obj);
			}
		}
	}

	/**
	 * 给hal语句加排序规则
	 * @param hql
	 * @return
	 */
	private String initSort(String hql) {
		String sort = SystemContext.getSort();//数据我们可以在Controller层去放
		String order = SystemContext.getOrder();
		if(sort!=null&&!"".equals(sort.trim())) {
			hql += " order by " + sort;
			if(!"desc".equals(order)) {
				hql += " asc";
			}else {
				hql += " desc";
			}
		}
		return hql;
	}
	
	/**
	 * 查询多条数据，支持分页
	 * @param hql
	 * @param objs
	 * @param alias
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Pager<T> find(String hql,Object[] objs,Map<String, Object>alias){
		hql = initSort(hql);//初始化排序规则
		Query<T> query = getSession().createQuery(hql);
		setParameter(query,objs);//将hql的?占位符替换
		setAliasParameter(query,alias);
		Pager<T> pager = new Pager<>();
		setPager(query,pager);
		List<T> datas = query.list();
		pager.setRows(datas);
		
		//还需要一个查询的总条数 select count(*) from users where id>0 
		String countHQL = getCountHQL(hql);
		Query countQuery = getSession().createQuery(countHQL);
		setParameter(countQuery,objs);//将hql的?占位符替换
		setAliasParameter(countQuery,alias);
		long total = (long) countQuery.uniqueResult();
		pager.setTotal(total);
		
		return pager;
 	}

	private String getCountHQL(String hql) {
		String hhql = hql.substring(hql.indexOf("from"));//拿到HQL语句from开始的后半部分：from users where id>10
		String countHQL = "select count(*) "+hhql;
		//hql语句，关联查询可能会用到fetch
		countHQL = countHQL.replace("fetch", "");
		return countHQL;
	}

	private void setPager(Query<T> query, Pager<T> pager) {
		Integer pageSize = SystemContext.getPageSize();
		Integer pageOffset = SystemContext.getPageOffset();
		if(pageOffset==null||pageOffset < 0) {
			pageOffset = 0;//pageOffset默认值
		}
		if(pageSize==null||pageSize < 0) {
			pageSize = 10;//没有设置每页大小，默认值显示10条
		}
		pager.setSize(pageSize);
		pager.setOffset(pageOffset);
		//datas就是分页数据 select * from users where id>10;一张页面显示的数据
		query.setFirstResult(pageOffset).setMaxResults(pageSize);
	}
	
	/**
	 * 针对一些特殊的查询，查询的结果不受泛型的制约
	 * @param hql
	 * @param objs
	 * @param alias
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object queryByHql(String hql,Object[] objs,Map<String, Object>alias) {
		Query query = getSession().createQuery(hql);
		setParameter(query,objs);//将hql的?占位符替换
		setAliasParameter(query,alias);
		return query.uniqueResult();
	}
	
	/**
	 * 应对某些特殊情况下要用hal语句来更新
	 * @param hql
	 * @param objs
	 * @param alias
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void updateByHql(String hql,Object[] objs,Map<String, Object>alias) {
		Query query = getSession().createQuery(hql);
		setParameter(query,objs);//将hql的?占位符替换
		setAliasParameter(query,alias);
		query.executeUpdate();
	}
} 
