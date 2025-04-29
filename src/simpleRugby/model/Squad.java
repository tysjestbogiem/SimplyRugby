package simpleRugby.model;

import java.util.List;

public class Squad {
	
	private String squadName;
	private String ageGroup;  // maybe change to enum
	private List<Player> players;
	private List<Coach> coach;
	
	public static String getSquadName() {
		String squadName = SquadDAO.getSquadNameByCoach(SessionManager.getUserId());
		return squadName;
	}
	public void setSquadName(String squadName) {
		this.squadName = squadName;
	}
	public String getAgeGroup() {
		return ageGroup;
	}
	public void setAgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
	}
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	public List<Coach> getCoach() {
		return coach;
	}
	public void setCoach(List<Coach> coach) {
		this.coach = coach;
	}
	
	@Override
	public String toString() {
	    return squadName;
	}


}
