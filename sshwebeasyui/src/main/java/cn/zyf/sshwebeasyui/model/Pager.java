package cn.zyf.sshwebeasyui.model;

import java.util.List;

public class Pager<T> { 

	/**
	 * 分页大小，每页显示的条数
	 */
	private int size;
	
	/**
	 * 每一页第一条的条数索引
	 */
	private int offset;
	
	/**
	 * 查询出来的总的数据的条数，total与EasyUI插件里变量名称一致
	 */
	private long total;
	
	/**
	 * 每张每页是具体数据，rows与EasyUI插件里变量名称一致
	 */
	private List<T> rows;

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		return "Pager [size=" + size + ", offset=" + offset + ", total=" + total + ", rows=" + rows + "]";
	}
	
	
}
