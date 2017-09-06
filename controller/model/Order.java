package controller.model;

public class Order {
	private Movie movie;
	private Session session;
	private User user;
	private String seat;
	
	public Order(Movie movie, Session session, User user, int row, int column) {
		super();
		this.movie = movie;
		this.session = session;
		this.user = user;
		this.seat = ""+row+" 排 "+column+" 号";
	}

	public Movie getMovie() {
		return movie;
	}

	public String getSeat() {
		return seat;
	}

	public Session getSession() {
		return session;
	}

	public User getUser() {
		return user;
	}
	
	
}
