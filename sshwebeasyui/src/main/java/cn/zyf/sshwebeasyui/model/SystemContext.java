package cn.zyf.sshwebeasyui.model;

/**
 * ThreadLocal:实现数据共享，本地线程变量，每个线程中创建副本，在多条线程中，给这个变量赋值，每条线程使用这个变量不会冲突
 * 进行分页信息通讯
 * 排序信息通讯
 * @author ray_cn
 *
 */
public class SystemContext {
	//分页大小，每页显示的数据条数
	private static ThreadLocal<Integer> pageSize = new ThreadLocal<>();
	//每页的起始条数索引
	private static ThreadLocal<Integer> pageOffset = new ThreadLocal<>();
	//排序字段
	private static ThreadLocal<String> sort = new ThreadLocal<>();
	//排序方式
	private static ThreadLocal<String> order = new ThreadLocal<>();
	
	public static Integer getPageSize() {
		return pageSize.get();
	}
	
	public static void setPageSize(Integer _pageSize) {
		pageSize.set(_pageSize);
	}
	
	public static Integer getPageOffset() {
		return pageOffset.get();
	}
	
	public static void setPageOffset(Integer _pageOffset) {
		pageOffset.set(_pageOffset);
	}
	
	public static String getSort() {
		return sort.get();
	}
	
	public static void setSort(String _sort) {
		sort.set(_sort);
	}
	
	public static String getOrder() {
		return order.get();
	}
	
	public static void setOrder(String _order) {
		order.set(_order);
	}
	
	public static void removePageSize() {
		pageSize.remove();
	}
	
	public static void removePageOffset() {
		pageOffset.remove();
	}
	
	public static void removeSort() {
		sort.remove();
	}
	
	public static void removeOrder() {
		order.remove();
	}
}