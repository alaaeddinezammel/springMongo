package com.backendIntern.model;

public class logLine {

	String idOrder;

	String time;

	String Source;

	public String getSource() {
		return Source;
	}

	public void setSource(String source) {
		Source = source;
	}

	String Tags;

	public String getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(String idOrder) {
		this.idOrder = idOrder;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTags() {
		return Tags;
	}

	public void setTags(String tags) {
		Tags = tags;
	}

	@Override
	public String toString() {
		return "logLine [idOrder=" + idOrder + ", time=" + time + ", Source=" + Source + ", Tags=" + Tags + "]";
	}

	public logLine() {
		super();
		// TODO Auto-generated constructor stub
	}

	public logLine(String idOrder, String time, String source, String tags) {
		super();
		this.idOrder = idOrder;
		this.time = time;
		Source = source;
		Tags = tags;
	}

}
