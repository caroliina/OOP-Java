package msgserver;

public class Message {

	String to;
	String content;

	public Message(String to, String content) {
		this.to = to;
		this.content = content;
	}

	public Message(String content) {
		this.content = content;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
