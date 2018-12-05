package cn.zyf.sshwebeasyui.service;

public interface BaseService<T> {
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
