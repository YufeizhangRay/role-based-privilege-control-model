package cn.zyf.sshwebeasyui.model;

public class Permission {

	private int id;
	private String resources;
	private Integer state;// 0禁用，1正常

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getResources() {
		return resources;
	}

	public void setResources(String resources) {
		this.resources = resources;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Permission [id=" + id + ", resources=" + resources + ", state=" + state + "]";
	}

}
