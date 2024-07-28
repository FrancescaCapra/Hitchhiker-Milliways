package it.polito.oop.milliways;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Hall {
	private int Id;
	private List<String> facilities=new ArrayList<>();
	

	public Hall(int id) {
		Id = id;
	}

	public int getId() {
		return this.Id;
	}

	public void addFacility(String facility) throws MilliwaysException {
		for(String s:this.facilities)
			if(s.equals(facility)==true)
				throw new MilliwaysException();
		this.facilities.add(facility);
	}

	public List<String> getFacilities() {
        return this.facilities.stream().sorted().collect(Collectors.toList());
	}
	
	int getNumFacilities(){
        return this.facilities.size();
	}

	public boolean isSuitable(Party party) {
		return party.getRequirements().equals(this.getFacilities());
	}

}
