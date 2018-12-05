package cn.zyf.sshwebeasyui.dao;

public interface BaseDao<T> {
	//Dao:操作数据库，操作数据库最最基本的功能性代码，
	//最最基本：增删改查
	/**
	 * 添加
	 * @param t
	 * @return
	 */
	public T add(T t);
	
	/**
	 * 删除
	 * @param id
	 */
	public void delete(int id);
	
	/**
	 * 修改
	 * @param t
	 */
	public void update(T t);
	
	/**
	 * 查询一条记录
	 * @param id
	 * @return
	 */
	public T load(int id);
}
