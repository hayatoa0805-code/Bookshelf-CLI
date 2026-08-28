package entity;

public class BookEntity {
	private static int nextId = 1;
	private int id;
	private String title;
	private String volume;
	private String publisher;
	private String author;
	
	public BookEntity(String title,String volume, String publisher, String author) {
		this.id = nextId++;
		this.title = title;
		this.volume = volume;
		this.publisher = publisher;
		this.author = author;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getVolume() {
		return volume;
	}

	public String getPublisher() {
		return publisher;
	}

	public String getAuthor() {
		return author;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
}