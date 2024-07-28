package it.polito.oop.milliways;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Party {
	private Map<Race,Integer> racesParty=new HashMap<>();

    public void addCompanions(Race race, int num) {
    	racesParty.put(race, num);
	}

	public int getNum() {
		int sum=0;
		for(Integer i: racesParty.values())
			sum+=i;
        return sum;
	}

	public int getNum(Race race) {
	    return this.racesParty.get(race);
	}

	public List<String> getRequirements() {
        return this.racesParty.keySet().stream()
        		.flatMap(r->r.getRequirements().stream()) //li raggruppa
        		.distinct()
        		.sorted()
        		.collect(Collectors.toList());
	}

    public Map<String,Integer> getDescription(){
        return racesParty.entrySet().stream()
        		.collect(Collectors.toMap(e->e.getKey().getName(), e->e.getValue()));
    }
    
    public Map<Race,Integer> getComposition(){
    	return this.racesParty;
    }
}
