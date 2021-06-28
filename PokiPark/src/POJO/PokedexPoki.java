package POJO;

public class PokedexPoki {

	private int id;
	
	private String name, typ, firstEvo, secondEvo, thirdEvo, pokedexEntry;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTyp() {
		return typ;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}

	public String getFirstEvo() {
		return firstEvo;
	}

	public void setFirstEvo(String firstEvo) {
		this.firstEvo = firstEvo;
	}

	public String getSecondEvo() {
		return secondEvo;
	}

	public void setSecondEvo(String secondEvo) {
		this.secondEvo = secondEvo;
	}

	public String getThirdEvo() {
		return thirdEvo;
	}

	public void setThirdEvo(String thirdEvo) {
		this.thirdEvo = thirdEvo;
	}

	public String getPokedexEntry() {
		return pokedexEntry;
	}

	public void setPokedexEntry(String pokedexInfo) {
		this.pokedexEntry = pokedexInfo;
	}
}