package entity;

public class BookEntity {
	private int id;
	private int userId;
	private String title;
	private String volume;
	private String publisher;
	private String author;

	public BookEntity() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public BookEntity(
			int userId,
			String title,
			String volume,
			String publisher,
			String author) {

		this.userId = userId;
		this.title = title;
		this.volume = volume;
		this.publisher = publisher;
		this.author = author;
	}

	public BookEntity(
			int id,
			int userId,
			String title,
			String volume,
			String publisher,
			String author) {

		this.id = id;
		this.userId = userId;
		this.title = title;
		this.volume = volume;
		this.publisher = publisher;
		this.author = author;
	}

	public int getId() {
		return id;
	}

	public int getUserId() {
		return userId;
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

	public void setBookId(int bookId) {
		this.id = bookId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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