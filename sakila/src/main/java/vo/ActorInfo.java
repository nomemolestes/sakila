package vo;

public class ActorInfo {
	private int actorId;
	private String firstName;
	private String lastName;
	private String filmInfo;
	
	@Override
	public String toString() { //this.가 생략됨
		return "ActorInfo [actorId=" + actorId + ", firstName=" + firstName + ", lastName=" + lastName + ", filmInfo="
				+ filmInfo + "]";
	}
	/* @Override //우클릭 > source하면 toString()가능
	public String toString() { //오버라이딩+toString는 이클래스로 만들어진 객체값들의 출력
		return this.actorId+this.firstName+this.lastName+this.filmInfo;
	} */ 
	public int getActorId() {
		return actorId;
	}
	public void setActorId(int actorId) {
		this.actorId = actorId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFilmInfo() {
		return filmInfo;
	}
	public void setFilmInfo(String filmInfo) {
		this.filmInfo = filmInfo;
	}
}
