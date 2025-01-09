package model;

public class Board {
	private int board_id;
	private String studentId;
	private String name;
	private String title;
	private String content;
	private String created_at;
	private int view;
	
	
	public int getView() {
		return view;
	}


	public void setView(int view) {
		this.view = view;
	}


	public int getBoard_id() {
		return board_id;
	}


	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}


	public String getStudentId() {
		return studentId;
	}


	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getCreated_at() {
		return created_at;
	}


	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}


	@Override
	public String toString() {
		return "Board [board_id=" + board_id + ", studentId=" + studentId + ", name=" + name + ", title=" + title
				+ ", content=" + content + ", created_at=" + created_at + ", view=" + view + "]";
	}


	
	
}
