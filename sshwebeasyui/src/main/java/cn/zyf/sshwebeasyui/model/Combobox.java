package cn.zyf.sshwebeasyui.model;

public class Combobox {

	private int id;
	private String text;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Combobox [id=" + id + ", text=" + text + "]";
	}

	public Combobox() {
		super();
	}

	public Combobox(int id, String text) {
		super();
		this.id = id;
		this.text = text;
	}

}
