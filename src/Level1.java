public class Level1 extends GameLevel {
	//private Integer level_width = 5600;
	//private Integer level_height = 480;
	
	public Level1() {
		super();
		prevDoor = 1;
		prevPosition = 0;
		area = new L0A0();
		setNumZombies(area.ZOMBIES);
		setNumHealth(area.HEALTH);
		setNumAmmo(area.AMMO);
		setNumBatteries(area.BATTERIES);
		setNumSandwiches(area.SANDWICHES);
		setNumSurvivors(area.SURVIVORS);
		this.setRadioText();
		this.setLevelMusic("sounds/RoB-main.wav");
		//this.bg = area.build();
	}
	 
	public int inDoor(int i, int absPos){
		int tmpPosition = prevPosition;
		//prevPosition = absPos;
		//prevDoor = i;
		switch (i){
		case 0:
			area.clean();
			area = null;
			area = new L1A0();
			setNumZombies(area.ZOMBIES);
			setNumHealth(area.HEALTH);
			setNumAmmo(area.AMMO);
			setNumBatteries(area.BATTERIES);
			setNumSandwiches(area.SANDWICHES);
			setNumSurvivors(area.SURVIVORS);
			return prevDoor;
		case 1:
			prevDoor = i;
			prevPosition = absPos;
			//System.out.println("move into the safehouse");
			area.clean();
			area = null;
			area = new L0A0();
			setNumZombies(area.ZOMBIES);
			setNumHealth(area.HEALTH);
			setNumAmmo(area.AMMO);
			setNumBatteries(area.BATTERIES);
			setNumSandwiches(area.SANDWICHES);
			setNumSurvivors(area.SURVIVORS);
			return 0;
		case 2:
			prevDoor = i;
			prevPosition = absPos;
			//System.out.println("move into the building");
			area.clean();
			area = null;
			area = new L1A1();
			setNumZombies(area.ZOMBIES);
			setNumHealth(area.HEALTH);
			setNumAmmo(area.AMMO);
			setNumBatteries(area.BATTERIES);
			setNumSandwiches(area.SANDWICHES);
			setNumSurvivors(area.SURVIVORS);
			return 0;
		case 3:
			prevDoor = i;
			prevPosition = absPos;
			area.clean();
			area = null;
			area = new L1A1();
			setNumZombies(area.ZOMBIES);
			setNumHealth(area.HEALTH);
			setNumAmmo(area.AMMO);
			setNumBatteries(area.BATTERIES);
			setNumSandwiches(area.SANDWICHES);
			setNumSurvivors(area.SURVIVORS);
			return 0;
		case 4:
			prevDoor = i;
			prevPosition = absPos;
			area.clean();
			area = null;
			area = new L1A1();
			setNumZombies(area.ZOMBIES);
			setNumHealth(area.HEALTH);
			setNumAmmo(area.AMMO);
			setNumBatteries(area.BATTERIES);
			setNumSandwiches(area.SANDWICHES);
			setNumSurvivors(area.SURVIVORS);
			return 0;
		case 9:
			prevDoor = i;
			prevPosition = absPos;
			area.clean();
			area = null;
			area = new L1A2();
			setNumZombies(area.ZOMBIES);
			setNumHealth(area.HEALTH);
			setNumAmmo(area.AMMO);
			setNumBatteries(area.BATTERIES);
			setNumSandwiches(area.SANDWICHES);
			setNumSurvivors(area.SURVIVORS);
			return 0;
		case 10:
			prevDoor = i;
			prevPosition = absPos;
			area.clean();
			area = null;
			area = new L1A2();
			setNumZombies(area.ZOMBIES);
			setNumHealth(area.HEALTH);
			setNumAmmo(area.AMMO);
			setNumBatteries(area.BATTERIES);
			setNumSandwiches(area.SANDWICHES);
			setNumSurvivors(area.SURVIVORS);
			return 0;
		case 11:
			prevDoor = i;
			prevPosition = absPos;
			area.clean();
			area = null;
			area = new L1A3();
			setNumZombies(area.ZOMBIES);
			setNumHealth(area.HEALTH);
			setNumAmmo(area.AMMO);
			setNumBatteries(area.BATTERIES);
			setNumSandwiches(area.SANDWICHES);
			setNumSurvivors(area.SURVIVORS);
			return 0;
		case 12:
			prevDoor = i;
			prevPosition = absPos;
			area.clean();
			area = null;
			area = new L1A2();
			setNumZombies(area.ZOMBIES);
			setNumHealth(area.HEALTH);
			setNumAmmo(area.AMMO);
			setNumBatteries(area.BATTERIES);
			setNumSandwiches(area.SANDWICHES);
			setNumSurvivors(area.SURVIVORS);
			return 0;
			
		default:
			prevDoor = i;
			prevPosition = absPos;
			area.clean();
			area = null;
			area = new L1A1();
			setNumZombies(area.ZOMBIES);
			setNumHealth(area.HEALTH);
			setNumAmmo(area.AMMO);
			setNumBatteries(area.BATTERIES);
			setNumSandwiches(area.SANDWICHES);
			setNumSurvivors(area.SURVIVORS);
			return 0;
		}
	}
	
	public void musicNormal(){
		if (!this.getLevelMusic().getFileName().contains("sounds/RoB-main.wav")){
			System.out.println("musicNormal is changing the sound");
			this.getLevelMusic().stop();
			this.setLevelMusic("sounds/RoB-main.wav");
		}
	}
	
	public void musicTense(){
		if (!this.getLevelMusic().getFileName().contains("sounds/RoB-THE_PRESSURE_IS_ON.wav")){
			this.getLevelMusic().stop();
			this.setLevelMusic("sounds/RoB-THE_PRESSURE_IS_ON.wav");
		}
	}
	
	public void musicMoreTense(){
		if (!this.getLevelMusic().getFileName().contains("sounds/RoB-MORE PRESSURE_IS_ON.wav")){
			this.getLevelMusic().stop();
			this.setLevelMusic("sounds/RoB-MORE PRESSURE_IS_ON.wav");
		}
	}
		
	public boolean levelEnd(){
		/*
		String label_str = item_lbl.getText();
		int itemCount = Integer.parseInt(label_str);
		if (itemCount == 30)
		{
			// End level
			return true;
		}
		else
		{
			return false;
		}
		*/
		return false;
	}
	
	public void setRadioText()
	{
		//radio_txt.setText("Collect bullets so you can kill the zombies!");
	}

	public void setLevel_width(Integer level_width) {
		this.level_width = level_width;
	}

	public Integer getLevel_width() {
		return level_width;
	}

	public void setLevel_height(Integer level_height) {
		this.level_height = level_height;
	}

	public Integer getLevel_height() {
		return level_height;
	}
	
	/*
	public String getTextFile()
	{
		return text_file;
	}
	 */
}
