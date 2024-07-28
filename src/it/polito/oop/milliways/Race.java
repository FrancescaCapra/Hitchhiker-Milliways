package it.polito.oop.milliways;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Race {
	private String name;
	private List<String> requisiti=new ArrayList<>();
    
	public Race(String name) {
		this.name = name;
	}

	public void addRequirement(String requirement) throws MilliwaysException {
		for(String s:requisiti) {
			if(s.equals(requirement))
				throw new MilliwaysException();
		}
		requisiti.add(requirement);
	}
	
	
	public List<String> getRequirements() {
        return requisiti.stream().sorted().collect(Collectors.toList());
	}
	
	public String getName() {
        return this.name;
	}
}
