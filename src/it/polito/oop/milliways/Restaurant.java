package it.polito.oop.milliways;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Restaurant {
	private Map<String,Race> races=new HashMap<>();
	private List<Party> parties=new ArrayList<>();
	private Map<Integer,Hall> halls=new HashMap<>();
	private Map<Party,Hall> partyForHall =new HashMap<>();

    public Restaurant() {
	}
	
	public Race defineRace(String name) throws MilliwaysException{
		if(races.containsKey(name))
			throw new MilliwaysException();
		Race r=new Race(name);
		races.put(name, r);
	    return r;
	}
	
	public Party createParty() {
		Party p=new Party();
		parties.add(p);
	    return p;
	}
	
	public Hall defineHall(int id) throws MilliwaysException{
		if(halls.containsKey(id))
			throw new MilliwaysException();
		Hall h=new Hall(id);
		halls.put(id, h);
	    return h;
	}

	public List<Hall> getHallList() {
		return this.halls.values().stream()
				.collect(Collectors.toList());
	}

	public Hall seat(Party party, Hall hall) throws MilliwaysException {
		if(hall.isSuitable(party)==false)
			throw new MilliwaysException();
		partyForHall.put(party, hall);
        return hall;
	}

	public Hall seat(Party party) throws MilliwaysException {
		Hall hall = null;
		int i=1;
		
		for(Hall h: halls.values()) {
			
			if(h.isSuitable(party)) {
				this.partyForHall.put(party, h);
				hall=h;
				i=0;
			}
		}
		if(i==1)
			throw new MilliwaysException();
        return hall;
	}

	public Map<Race, Integer> statComposition() {
		return this.partyForHall.keySet().stream()
				.flatMap(c->c.getComposition().entrySet().stream())
				.collect(Collectors.groupingBy(Map.Entry<Race,Integer>::getKey,
						Collectors.summingInt(Map.Entry::getValue)));
	}

	public List<String> statFacility() {
		return halls.values().stream()
				.flatMap(c->c.getFacilities().stream())
				.collect(Collectors.groupingBy(x->x, Collectors.counting()))
				.entrySet().stream().sorted(Comparator.comparing(Map.Entry<String,Long>::getValue)
						.thenComparing(Map.Entry::getKey)).map(Map.Entry::getKey).collect(Collectors.toList());
	}
	
	public Map<Integer,List<Integer>> statHalls() {
		return halls.values().stream()
				.collect(Collectors.groupingBy(c->c.getFacilities().size(), //qua prendi questa size
						Collectors.mapping(Hall::getId, Collectors.toList()))); //qua sei sotto la condizione di quella size e quindi prendi solo alcune hall
	}

}
