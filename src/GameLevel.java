import java.awt.image.BufferedImage;

public abstract class GameLevel {
	//protected BufferedImage bg;
	protected SpeechBubble radio_txt;
	private int numBatteries;
	private int numZombies;
	private int numHealth;
	private int numAmmo;
	private int numSandwiches;
	private int numSurvivors;
	protected int level_width;
	protected int level_height;
	protected int kills;
	//private Sound levelMusic;
	protected Area area; //current area
	protected int prevDoor; 
	protected int prevPosition;

	public Area getArea() {
		return area;
	}

	public int getPrevPosition() {
		return prevPosition;
	}

	public void setPrevPosition(int prevPosition) {
		this.prevPosition = prevPosition;
	}
	
	public void setArea(Area area) {
		this.area = area;
	}

	public GameLevel()
	{
		kills = 0;
	}
	
	public int inDoor(int i, int absPos){
		return 0;
	}

	public void updateKills()
	{
		kills++;
	}
	
	public int getKills(){
		return kills;
	}
	
	public void setRadioText()
	{

	}
	
	public void updateItems(Ming ming)
	{
	
	}
	
	public void updateHealth(int healthnum)
	{
		//health_meter.updateState(healthnum);
	}
	
	public void setBackground()
	{
	
	}
	
	public String getTaskText()
	{
		//return task_lbl.getText();
		return null;
	}
	public void setNumZombies(int numZombies) {
		this.numZombies = numZombies;
	}

	public int getNumZombies() {
		return numZombies;
	}

	public void setNumHealth(int numHealth) {
		this.numHealth = numHealth;
	}

	public int getNumHealth() {
		return numHealth;
	}

	public void setNumAmmo(int numAmmo) {
		this.numAmmo = numAmmo;
	}

	public int getNumAmmo() {
		return numAmmo;
	}

	public void setNumBatteries(int numBatteries) {
		this.numBatteries = numBatteries;
	}

	public int getNumBatteries() {
		return numBatteries;
	}

	public void setNumSandwiches(int numSandwiches) {
		this.numSandwiches = numSandwiches;
	}

	public int getNumSandwiches() {
		return numSandwiches;
	}

	public void setNumSurvivors(int numSurvivors) {
		this.numSurvivors = numSurvivors;
	}

	public int getNumSurvivors() {
		return numSurvivors;
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

	public void setLevelMusic(String levelMusic) {
//		this.levelMusic = new Sound(levelMusic);
	}

	public Sound getLevelMusic() {
//		return levelMusic;
		return null;
	}
	
	public void runningMusic(){
//		levelMusic.running();
	}
	
	public void startMusic(){
//		levelMusic.start();
	}
	
	public void stopMusic(){
//		levelMusic.stop();
	}
	
	public void musicNormal(){
	}
	
	public void musicTense(){
	}
	
	public void musicMoreTense(){
	}
	
}
