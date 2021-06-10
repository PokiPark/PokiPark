package POJO;

import java.sql.SQLException;

import application.Database;

public class Poki {

	private int id;
	private String name;
	private String typ;
	private String sex;
	private String img_path;
	private String prevEvo;
	private String nextEvo;
	private String pokedexInfo;
	
	private Poki evolve() throws SQLException {
		Poki poki = Database.getPoki(nextEvo, sex);
		Database.removePoki(id);
		return poki;
	}
	
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getImg_path() {
		return img_path;
	}
	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}
	public String getPrevEvo() {
		return prevEvo;
	}
	public void setPrevEvo(String prevEvo) {
		this.prevEvo = prevEvo;
	}
	public String getNextEvo() {
		return nextEvo;
	}
	public void setNextEvo(String nextEvo) {
		this.nextEvo = nextEvo;
	}
	public String getPokedexInfo() {
		return pokedexInfo;
	}
	public void setPokedexInfo(String pokedexInfo) {
		this.pokedexInfo = pokedexInfo;
	}
	
	
}
