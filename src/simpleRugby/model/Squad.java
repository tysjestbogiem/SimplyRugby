package simpleRugby.model;

import java.util.List;

/**
 * This class represents a Squad (team) in the rugby system.
 * A squad has a name, age group, list of players, and coaches.
 */
public class Squad {
	
	private String squadName;
	private String ageGroup;  // maybe change to enum
	private List<Player> players; // all players in the squad
	private List<Coach> coach; // coaches assigned to this squad
	
	
	/**
	 * Static method to get the current user's squad name from the database.
	 * This uses the logged-in user's ID to fetch it.
	 * 
	 * @return Squad name as a String
	 */
	public static String getSquadName() {
		String squadName = SquadDAO.getSquadNameByCoach(SessionManager.getUserId());
		return squadName;
	}

	/**
	 * @return the ageGroup
	 */
	public String getAgeGroup() {
		return ageGroup;
	}

	/**
	 * @param ageGroup the ageGroup to set
	 */
	public void setAgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
	}

	/**
	 * @return the players
	 */
	public List<Player> getPlayers() {
		return players;
	}

	/**
	 * @param players the players to set
	 */
	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	/**
	 * @return the coach
	 */
	public List<Coach> getCoach() {
		return coach;
	}

	/**
	 * @param coach the coach to set
	 */
	public void setCoach(List<Coach> coach) {
		this.coach = coach;
	}
	
	/**
	 * This is used when we want to print a Squad object – 
	 * it will show the squad's name.
	 */
	@Override
	public String toString() {
	    return squadName;
	}

}
