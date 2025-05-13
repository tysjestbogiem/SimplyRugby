package simpleRugby.model;

import java.util.List;
/**
 * Coach represents a user who is also a Member in this system.
 * 
 * This class keeps track of the squads the coach is managing.
 */
public class Coach extends Member {
	
	// List of squads this coach is responsible for
	private List<Squad> squadManaged;

	/**
	 * @return the squadManaged
	 */
	public List<Squad> getSquadManaged() {
		return squadManaged;
	}

	/**
	 * @param squadManaged the squadManaged to set
	 */
	public void setSquadManaged(List<Squad> squadManaged) {
		this.squadManaged = squadManaged;
	}

}
