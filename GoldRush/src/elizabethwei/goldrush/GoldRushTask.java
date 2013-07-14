package elizabethwei.goldrush;

import java.util.Random;

import projectfun.mobile.PFPersistentTaskVariableDB;
import projectfun.mobile.ProjectFunMobileLogicTask;
import projectfun.mobile.media.Audio;
import projectfun.mobile.sensors.Accelerometer;
import projectfun.mobile.ui.AppLayout;
import projectfun.mobile.ui.CompoundButtons;
import projectfun.mobile.ui.Images;
import projectfun.mobile.ui.Popup;
import projectfun.mobile.ui.Text;
import projectfun.mobile.ui.Toasts;
import projectfun.mobile.utility.ProjectFunMobileValues;

public class GoldRushTask extends ProjectFunMobileLogicTask {

	public int energy = 100; 
	public int money = 1000; 
	public int level = 1; 
	
	public int nextmoney = 1000; 
	public int currmoney = 0; //money earned at current level

	public int shovelcost = 100; 
	public int shovelsupply = 0; 
	public int pancost = 50; 
	public int pansupply = 0;
	public int pickcost = 150;
	public int picksupply = 0;

	public int pancakecost = 5;
	public int pancakesupply = 0;
	public int pancakeenergy = 10;
	public int eggcost = 5;
	public int eggsupply = 0;
	public int eggenergy = 10; 
	public int coffeecost = 2;
	public int coffeesupply = 0;
	public int coffeeenergy = 4; 

	public int whichbuy = 0;  

	public int minecount = 0; 
	public int minemonster = 0; 
	public int monsterhealth = 100; 
	//piranha
	public int pancount = 0;
	public int rivermonster = 0;
	public int fishhealth = 100;
	
	public int newgame = 1; 
	
	public boolean playing = false; 
	public boolean wanttoplay = true; 
	public boolean wantsound = true; 
	public boolean bankruptdisplay = false; 

	public String[] tools = {"shovel", "pan", "pick"};
	public String[] food = {"pancakes", "eggs", "coffee"};

	@Override
	public void onSetPersistentData() {
		PFPersistentTaskVariableDB.RegisterInteger("energy");
		PFPersistentTaskVariableDB.RegisterInteger("money");
		PFPersistentTaskVariableDB.RegisterInteger("level");
		PFPersistentTaskVariableDB.RegisterInteger("shovelsupply");
		PFPersistentTaskVariableDB.RegisterInteger("pansupply");
		PFPersistentTaskVariableDB.RegisterInteger("picksupply");
		PFPersistentTaskVariableDB.RegisterInteger("pancakesupply");
		PFPersistentTaskVariableDB.RegisterInteger("eggsupply");
		PFPersistentTaskVariableDB.RegisterInteger("minecount");
		PFPersistentTaskVariableDB.RegisterInteger("pancount");
		PFPersistentTaskVariableDB.RegisterInteger("nextmoney");
		PFPersistentTaskVariableDB.RegisterInteger("currmoney");
		PFPersistentTaskVariableDB.RegisterInteger("newgame");
		PFPersistentTaskVariableDB.RegisterInteger("minemonster");
		PFPersistentTaskVariableDB.RegisterBoolean("bankruptdisplay");
		PFPersistentTaskVariableDB.RegisterBoolean("wanttoplay");
		PFPersistentTaskVariableDB.RegisterBoolean("wantsound");
	}
	
	public void startnewgame() {
		energy = 100;
		money = 1000;
		nextmoney = 2000;
		currmoney = nextmoney - money;
		level = 1;
		shovelsupply = 0;
		pansupply = 0;
		picksupply = 0;
		pancakesupply = 0;
		minecount = 0;
		pancount = 0;
		newgame = 1; 
		minemonster = 0;
		bankruptdisplay = false; 
		wanttoplay = true; 
		wantsound = true; 
	}
	
	public void checknewgame() {
		Popup.ShowYesNoDialog("Are you sure?", "All current info will be lost.", "startnewgame", ProjectFunMobileValues.NO_FUNCTION);
	}
	
	public void gotomain() {
		AppLayout.SetLayout(R.layout.main); 
	}

	public void getRules() {
		Popup.ShowOneButtonDialog("how to play", 
				                  "your goal is to get gold while avoiding bankruptcy.\n" +
		                          "at Rocky Range, you can mine for gold.\n" +
				                  "at the Alto River, you can try panning.\n" +
		                          "you can buy food and tools like picks at the General Store.\n" +
				                  "(watch out...the tools are flimsy and easily broken!)\n" +
		                          "at your tent, you can sleep and eat your meals.\n\n" + 
				                  "good luck!\n", 
				                  "OK", ProjectFunMobileValues.NO_FUNCTION);
	}
	
	public void getStoreHelp() {
		Popup.ShowOneButtonDialog("help", 
				                  "TOOLS: \n" +
		                          "   pick, shovel - both needed for mining\n" + 
				                  "   pan - needed for panning\n" +
		                          "*you can only buy one of each tool at a time\n\n" +
				                  "FOOD: \n" + 
		                          "   pancakes   $5   +10 energy\n" + 
				                  "   eggs           $5   +10 energy\n" +
		                          "   coffee         $2   +4 energy\n" +
				                  "*you can buy as much food as you want", "OK", ProjectFunMobileValues.NO_FUNCTION);
	}
	
	public void getRiverHelp() {
		Popup.ShowOneButtonDialog("help", 
				                  "panning takes 5 energy\n" +
		                          "unfortunately, you'll not always find gold.\n" +
				                  "if you get lucky, you'll find gold worth $80" +
		                          "occasionally, you'll hit the jackpot! this is worth $150." +
				                  "for this, you need a pan.\n" +
		                          "watch out for the piranhas!",
		                          "OK", ProjectFunMobileValues.NO_FUNCTION);
	}
	
	public void getMineHelp() {
		Popup.ShowOneButtonDialog("help", 
				                  "mining takes 10 energy\n" +
		                          "depending on what you find, you might be able to find gold worth up to $400.\n" +
				                  "for this, you need a pick and a shovel." + 
		                          "watch out for the Cave Monster!", "OK", ProjectFunMobileValues.NO_FUNCTION);
	}
	
	public void getTentHelp() {
		Popup.ShowOneButtonDialog("help", 
				                  "eat food.\n" +
		                          "sleep to gain up to 20 energy.\n", "OK", ProjectFunMobileValues.NO_FUNCTION);
	}

	public void gotoriver() {
		AppLayout.SetLayout(R.layout.river);
	}

	public void gotostore() {
		AppLayout.SetLayout(R.layout.store); 
	}

	public void gotostart() {
		AppLayout.SetLayout(R.layout.start);
	}
	
	public void gototent() {
		AppLayout.SetLayout(R.layout.tent);
	}

	public void gotomountains() {
		AppLayout.SetLayout(R.layout.mountains);
	}

	public void showTool(int i) {
		if(i == 0) {
			whichbuy = 0;
			Images.SetImage(R.id.buy_pic, R.raw.shovel);
			if(shovelsupply == 0)
				Text.SetText(R.id.display_store, "COST: $" + shovelcost);
			else
				Text.SetText(R.id.display_store, "YOU ALREADY HAVE IT.");
		}
		if(i == 1) {
			whichbuy = 1;
			Images.SetImage(R.id.buy_pic, R.raw.pan);
			if(pansupply == 0)
				Text.SetText(R.id.display_store, "COST: $" + pancost);
			else
				Text.SetText(R.id.display_store, "YOU ALREADY HAVE IT"); 
		}
		if(i == 2){
			whichbuy = 2;
			Images.SetImage(R.id.buy_pic, R.raw.pick);
			if(picksupply == 0)
				Text.SetText(R.id.display_store, "COST: $" + pickcost);
			else
				Text.SetText(R.id.display_store, "YOU ALREADY HAVE IT");

		}
	}

	public void showFood(int i) {
		if(i == 0) {
			whichbuy = 3;
			Images.SetImage(R.id.buy_pic, R.raw.pancake);
			Text.SetText(R.id.display_store,  "COST: $" + pancakecost);
		}
		if(i == 1) {
			whichbuy = 4;
			Images.SetImage(R.id.buy_pic, R.raw.egg);
			Text.SetText(R.id.display_store, "COST: $" + eggcost);
		}
		if(i == 2){
			whichbuy = 5;
			Images.SetImage(R.id.buy_pic, R.raw.coffee);
			Text.SetText(R.id.display_store, "COST: $" + coffeecost);

		}
	}

	public void getTools() {
		Popup.ShowSingleSelectListDialog("TOOLS", "showTool", tools);
	}

	public void getFood() {
		Popup.ShowSingleSelectListDialog("FOOD", "showFood", food); 
	}

	public void buyIt() {
		int bought = 0;
	    if(whichbuy == 0 && money < shovelcost) {
	    	Text.SetText(R.id.display_store, "You don't have enough money to buy this.");
	    	return;
	    }
	    if(whichbuy == 1 && money < pancost) {
	    	Text.SetText(R.id.display_store, "You don't have enough money to buy this.");
	    	return;
	    }
	    if(whichbuy == 2 && money < pickcost) {
	    	Text.SetText(R.id.display_store, "You don't have enough money to buy this.");
	    	return;
	    }
	    if(whichbuy == 3 && money < pancakecost) {
	    	Text.SetText(R.id.display_store, "You don't have enough money to buy this.");
	    	return;
	    }
	    if(whichbuy == 4 && money < eggcost) {
	    	Text.SetText(R.id.display_store, "You don't have enough money to buy this.");
	    	return;
	    }
	    if(whichbuy == 5 && money < coffeecost) {
	    	Text.SetText(R.id.display_store, "You don't have enough money to buy this.");
	    	return;
	    }
		
		if(whichbuy == 0 && shovelsupply == 0){
			money -= shovelcost;
			currmoney = nextmoney - money;  
			shovelsupply = 1; 
			bought = 1;
		}
		if(whichbuy == 1 && pansupply == 0){
			money -= pancost;
			currmoney = nextmoney- money; 
			pansupply = 1; 
			bought = 1;
		}
		if(whichbuy == 2 && picksupply == 0){
			money -= pickcost;
			currmoney = nextmoney - money; 
			picksupply = 1;
			bought = 1;
		}
		if(whichbuy == 3) {
			money -= pancakecost;
			currmoney = nextmoney - money; 
			pancakesupply++;
			bought = 1; 
		}
		if(whichbuy == 4){
			money -= eggcost;
			currmoney = nextmoney - money; 
			eggsupply++;
			bought = 1; 
		}
		if(whichbuy == 5) {
			money -= coffeecost;
			currmoney = nextmoney - money; 
			coffeesupply++; 
			bought = 1; 
		}
		Text.SetText(R.id.money_store, "YOU HAVE $" + money + " LEFT");
		if(bought == 0)
			Text.SetText(R.id.display_store, "YOU ALREADY HAVE IT.");
	}

	public void eat() {
		Popup.ShowSingleSelectListDialog("EAT", "showtentfood", food);
	}

	public void showtentfood(int i) {
		boolean foundFood = false; 
		if(i == 0 && pancakesupply > 0) {
			Images.SetImage(R.id.tent_display, R.raw.pancake);
			Text.SetText(R.id.tent_status, "EATING PANCAKES");
			if(energy + pancakeenergy <= 100)
				energy += pancakeenergy;
			else
				energy = 100; 
			foundFood = true;
			pancakesupply--;
		}
		else if(i == 1 && eggsupply > 0) {
			Images.SetImage(R.id.tent_display, R.raw.egg);
			Text.SetText(R.id.tent_status, "EATING EGGS");
			if(energy + eggenergy <= 100)
				energy += eggenergy;
			else
				energy = 100;
			foundFood = true;
			eggsupply--; 
		}
		else if(i == 2 && coffeesupply > 0) {
			Images.SetImage(R.id.tent_display, R.raw.coffee);
			Text.SetText(R.id.tent_status, "DRINKING COFFEE");
			if(energy + coffeeenergy <= 100)
				energy += coffeeenergy;
			else
				energy = 100;
			foundFood = true; 
			coffeesupply--; 
		}
		Text.SetText(R.id.energy_tent, "ENERGY: " + energy + "/100");
		setPancake();
		setEgg();
		setCoffee(); 
		if(foundFood == false)
			Text.SetText(R.id.tent_status, "NONE LEFT! BUY MORE AT THE STORE."); 
	}

	public void sleep() {
		Images.SetImage(R.id.tent_display, R.raw.sleep);
		if(energy >= 20) {
			Images.SetImage(R.id.tent_display, R.raw.standing);
			Text.SetText(R.id.tent_status, "I HAVE TOO MUCH ENERGY TO SLEEP."); 
			return; 
		}
		else
			energy = 20;
		Text.SetText(R.id.energy_tent, "ENERGY: " + energy + "/100");
		Text.SetText(R.id.tent_status, "SLEEPING"); 
	}
	
	public void setPancake() {
		if(pancakesupply == 1)
			Text.SetText(R.id.pancake_display, "1 PANCAKE STACK (+10 ENERGY)");
		else
			Text.SetText(R.id.pancake_display, pancakesupply + " STACKS OF PANCAKES (+10 ENERGY)");
	}
	
	public void setEgg() {
		if(eggsupply == 1)
			Text.SetText(R.id.egg_display, "1 EGG (+10 ENERGY)");
		else
			Text.SetText(R.id.egg_display, eggsupply + " EGGS (+10 ENERGY)");
	}
	
	public void setCoffee() {
		if(coffeesupply == 1)
			Text.SetText(R.id.coffee_display, "1 CUP OF COFFEE (+4 ENERGY)");
		else
			Text.SetText(R.id.coffee_display, coffeesupply + " CUPS OF COFFEE (+4 ENERGY)");
	}
	
	public void gotosettings() {
		AppLayout.SetLayout(R.layout.settings);
	}

	public void onLayoutChange(int layoutID) {
		if(layoutID == R.layout.start) {
			if(playing == false && wanttoplay == true){
			    Audio.StopCurrentSound();
			    Audio.PlaySound(R.raw.music, true);
			    playing = true; 
			}
			if(newgame == 1) {
				Images.SetImage(R.id.begin, R.raw.begin_button);
			}
			else
				Images.SetImage(R.id.begin, R.raw.continuegame);
			Images.SetOnTouchBehavior(R.id.begin, "gotomain");
			Images.SetOnTouchBehavior(R.id.settings, "gotosettings");
		}
		else if(layoutID == R.layout.main) {
			if(playing == false && wanttoplay == true) {
				Audio.StopCurrentSound();
				Audio.PlaySound(R.raw.music, true);
				playing = true; 
			}
			if(money <= 50 && bankruptdisplay == false) {
				Popup.ShowOneButtonDialog("WARNING", "Only $" + money + " left, you are almost bankrupt.", "OK", ProjectFunMobileValues.NO_FUNCTION);
			    bankruptdisplay = true; 
			}
			else if(money <= 3) {
				Popup.ShowOneButtonDialog("BANKRUPT", "Back to square one. Restart game at level 1.", "OK", "startnewgame");
				Text.SetText(R.id.energy, "ENERGY: " + energy + "/100"); 
				Text.SetText(R.id.money, "MONEY: $" + money); 
				Text.SetText(R.id.level, "LEVEL: " + level);
				Text.SetText(R.id.nextlevel, "$" + currmoney + " until next level");  
			}
			if(currmoney <= 0){
			   level++; 
			   nextmoney = (2*(level-1)+1)*1000 + 1000;
			   currmoney = nextmoney - money; 
			   Popup.ShowOneButtonDialog("Next Level!", "You reached level " + level + "...Claim your bonus 200 dollars.", "OK", "nextLevel");
			}
			if(newgame == 1) {
			   startnewgame();
			   newgame = 0;
			}
			Images.SetOnTouchBehavior(R.id.settings_main, "gotosettings");
			Text.SetText(R.id.energy, "ENERGY: " + energy + "/100"); 
			Text.SetText(R.id.money, "MONEY: $" + money); 
			Text.SetText(R.id.level, "LEVEL: " + level);
			Text.SetText(R.id.nextlevel, "$" + currmoney + " until next level");
			Images.SetOnTouchBehavior(R.id.river, "gotoriver");
			Images.SetOnTouchBehavior(R.id.store, "gotostore"); 
			Images.SetOnTouchBehavior(R.id.tent, "gototent");
			Images.SetOnTouchBehavior(R.id.mountains, "gotomountains");
			Images.SetOnTouchBehavior(R.id.main_help, "getRules");
		}
		else if(layoutID == R.layout.river) {
			Audio.PreloadSoundEffects(R.raw.fishsound);
			if(wantsound) {
				Audio.StopCurrentSound();
				Audio.PlaySound(R.raw.riversound, true);
				playing = false; 
			}
			Images.SetImage(R.id.pan_display, R.raw.panning_start);
			Text.SetText(R.id.energylevel, "ENERGY: " + energy + "/100");
			Text.SetText(R.id.moneypan, "MONEY: $" + money);
			Images.SetOnTouchBehavior(R.id.pan_button, "panGold");
			Images.SetOnTouchBehavior(R.id.river_done, "gotomain");
			Images.SetOnTouchBehavior(R.id.river_help, "getRiverHelp");
		}
		else if(layoutID == R.layout.store) {
			if(wantsound){
				Audio.StopCurrentSound();
				Audio.PlaySound(R.raw.storesound, true);
				playing = false; 
			}
			Images.SetImage(R.id.buy_pic, R.raw.shovel);
			Text.SetText(R.id.display_store, "COST: $" + shovelcost);
			Text.SetText(R.id.money_store, "YOU HAVE $" + money + " LEFT");
			whichbuy = 0;
			Images.SetOnTouchBehavior(R.id.done_store, "gotomain");
			Images.SetOnTouchBehavior(R.id.tools_button, "getTools");
			Images.SetOnTouchBehavior(R.id.food_button, "getFood");
			Images.SetOnTouchBehavior(R.id.buy_button, "buyIt");
			Images.SetOnTouchBehavior(R.id.help_store, "getStoreHelp");
		}
		else if(layoutID == R.layout.tent) {
			if(wantsound) {
				Audio.StopCurrentSound();
				Audio.PlaySound(R.raw.tentsound, true);
				playing = false; 
			}
			Text.SetText(R.id.tent_status, "STANDING AROUND");
			Text.SetText(R.id.energy_tent, "ENERGY: " + energy + "/100");
			setPancake();
			setEgg();
			setCoffee(); 
			Images.SetOnTouchBehavior(R.id.eat_button, "eat");
			Images.SetOnTouchBehavior(R.id.sleep_button, "sleep");
			Images.SetOnTouchBehavior(R.id.done_tent, "gotomain");
			Images.SetOnTouchBehavior(R.id.help_tent, "getTentHelp");
		}
		else if(layoutID == R.layout.mountains) {
			Audio.PreloadSoundEffects(R.raw.mineroar);
			if(wantsound) {
				Audio.StopCurrentSound();
				Audio.PlaySound(R.raw.minesound, true);
				playing = false; 
			}
			Text.SetText(R.id.mine_money, "MONEY: $" + money);
			Text.SetText(R.id.mine_energy, "ENERGY LEFT: " + energy + "/100");
			Images.SetImage(R.id.mine_display, R.raw.mine_blank);
			Images.SetOnTouchBehavior(R.id.mine_button, "mine");
			Images.SetOnTouchBehavior(R.id.done_mine, "gotomain"); 
			Images.SetOnTouchBehavior(R.id.mine_help, "getMineHelp");
		}
		else if(layoutID == R.layout.settings) {
			Images.SetOnTouchBehavior(R.id.newgame_button, "checknewgame");
			Images.SetOnTouchBehavior(R.id.main_settings, "gotostart");
			Images.SetOnTouchBehavior(R.id.returngame, "gotomain"); 
			CompoundButtons.SetChecked(R.id.musicswitch, wanttoplay);
			CompoundButtons.SetOnCheckedChangeBehavior(R.id.musicswitch, "playBM");
			CompoundButtons.SetChecked(R.id.soundswitch, wanttoplay);
			CompoundButtons.SetOnCheckedChangeBehavior(R.id.soundswitch, "playSound");
		}
	}
	
	public void nextLevel() {
		money += 200;
		currmoney = nextmoney - money;
	}
	
	public void playSound(boolean checked) {
		if(checked)
			wantsound = true;
		else
			wantsound = false;
	}

	public void playBM(boolean checked) {
		wanttoplay = checked;
		playing = checked; 
		if(checked) {
			Audio.PlaySound(R.raw.music, true);
		}
		else 
			Audio.StopCurrentSound();
	}
	
	public void escapeMine() {
		Popup.ShowTwoButtonDialog("ESCAPE!", "Escaping the Cave Monster is hard! If you run, you'll use up all your energy!", "run!", "runMine", "stay and fight!", ProjectFunMobileValues.NO_FUNCTION);
	}
	
	public void runMine() {
		energy = 0;
		AppLayout.SetLayout(R.layout.main); 
	}
	
	public void fightMineMonster() {
		Popup.ShowTwoButtonDialog("attack!", "Use 5 energy and attack the monster! With luck, you'll defeat him with one hit...Your only other option is to run for your life and use up all your remaining energy.\n\nCave Monster Energy: " + monsterhealth + "/100\nYour energy: " + energy + "/100", "Attack!", "attackMonster", "Run!", "runMine");
	}
	
	public void attackMonster() {
		if(energy < 5) {
			Popup.ShowOneButtonDialog("Run!", "The Cave Monster defeated me! I don't have enough energy to fight. My only option is to run...", "Run!", "runMine");
		}
		else if(monsterhealth <= 3) {
			Images.SetImage(R.id.mine_display, R.raw.mine_store);
			Images.SetImage(R.id.mine_button, R.raw.mine_button);
			Images.SetImage(R.id.done_mine, R.raw.done_button);
			Images.SetOnTouchBehavior(R.id.done_mine, "gotomain");
			Images.SetOnTouchBehavior(R.id.mine_button, "mine");
			Text.SetText(R.id.mine_energy, "ENERGY LEFT: " + energy + "/100");
			Popup.ShowOneButtonDialog("Victory!...for now", "You defeated the Cave Monster! It ran back to its lair. You are free to continue mining or leave the cave.", "OK", ProjectFunMobileValues.NO_FUNCTION);
		}
		else {
			energy -= 5; 
			Random rand = new Random();
			int attack = rand.nextInt(monsterhealth*2/3); 
			monsterhealth -= attack; 
			fightMineMonster();
		}
	}
	
	public void mine() {
		Random rand = new Random();
		int monster = rand.nextInt(100); 
		if(picksupply == 0 && shovelsupply == 0) {
			Images.SetImage(R.id.mine_display, R.raw.mine_store);
			Text.SetText(R.id.mine_status, "I NEED TO BUY A PICK AND A SHOVEL.");
		}
		else if(picksupply == 0){
			Images.SetImage(R.id.mine_display, R.raw.mine_store);
			Text.SetText(R.id.mine_status, "I NEED TO BUY A PICK."); 
		}
		else if(shovelsupply == 0) {
			Images.SetImage(R.id.mine_display, R.raw.mine_store);
			Text.SetText(R.id.mine_status, "I NEED TO BUY A SHOVEL"); 
		}
		else if(energy < 5 && monster <= 20) {
			if(wantsound)
				Audio.PlaySoundEffect(R.raw.mineroar);
			Popup.ShowOneButtonDialog("Run!", "I hear the Cave Monster coming, but I don't have enough energy to fight.", "Run!", "runMine");
		}
		else if(energy >= 5 && monster <= 20) {
			if(wantsound)
				Audio.PlaySoundEffect(R.raw.mineroar);
			monsterhealth = 100;
			Images.SetImage(R.id.mine_display, R.raw.dragon);
			Text.SetText(R.id.mine_status, "YOU AWOKE THE CAVE MONSTER!");
			Images.SetImage(R.id.done_mine, R.raw.escapemine_button);
			Images.SetImage(R.id.mine_button, R.raw.attackmine);
			Images.SetOnTouchBehavior(R.id.done_mine, "escapeMine"); 
			Images.SetOnTouchBehavior(R.id.mine_button, "fightMineMonster");
			minemonster = 1; 
		}
		else if(minecount % 7 == 0 && minecount >= 7) {
			picksupply = 0;	
			Images.SetImage(R.id.mine_display, R.raw.mine_pick);
			Text.SetText(R.id.mine_status, "MY PICK BROKE...I NEED TO GO TO THE STORE.");
			minecount++; 
		}
		else if(minecount % 10 == 0 && minecount >= 10) {
			minecount++; 
			shovelsupply = 0; 
			Images.SetImage(R.id.mine_display, R.raw.mine_shovel);
			Text.SetText(R.id.mine_status, "MY SHOVEL BROKE...I NEED TO GO TO THE STORE.");
		}
		else if(energy < 10) {
			Popup.ShowOneButtonDialog("Not enough energy", "I need to eat!", "OK", ProjectFunMobileValues.NO_FUNCTION);
			Images.SetImage(R.id.mine_display, R.raw.mine_zero);
			Text.SetText(R.id.mine_status, "HUNGRY...I NEED FOOD.");
		}
		else if(picksupply == 1 && shovelsupply == 1 && energy >= 10) {
			minecount++; 
			Images.SetImage(R.id.mine_display, R.raw.mine_working);
			energy -= 10;
			Random rand1 = new Random();
			int earned = rand1.nextInt(400);
			money += earned; 
			currmoney = nextmoney - money;
			Text.SetText(R.id.mine_status, "MINING");
			Text.SetText(R.id.mine_energy, "ENERGY: " + energy + "/100");
			Text.SetText(R.id.mine_money, "MONEY: $" + money);
		}
	}
	
	public void escapeRiver() {
		Popup.ShowTwoButtonDialog("ESCAPE!", "Running from the river will use up all your energy...", "Escape!", "runMine", "Stay and fight!", ProjectFunMobileValues.NO_FUNCTION);
	}

	public void attackPiranha() {
		if(energy < 5) {
			Popup.ShowOneButtonDialog("Surrender", "I don't have enough energy to fight! I need to run before the piranha eats me!", "Run!", "runMine");
		}
		else if(fishhealth <= 3) {
			Images.SetImage(R.id.pan_display, R.raw.panning_start);
			Images.SetImage(R.id.pan_button, R.raw.pan_button);
			Images.SetImage(R.id.river_done, R.raw.done_button);
			Images.SetOnTouchBehavior(R.id.river_done, "gotomain");
			Images.SetOnTouchBehavior(R.id.pan_button, "panGold");
			Text.SetText(R.id.energylevel, "ENERGY LEFT: " + energy + "/100");
			Popup.ShowOneButtonDialog("Piranha DEFEATED!", "Once the piranha realized it could not eat you, it swam away...for now. You are free to continue panning or leave the river.", "OK", ProjectFunMobileValues.NO_FUNCTION);
		}
		else {
			fightFish();
			energy -= 5;
			Random rand = new Random();
			int attack = rand.nextInt(fishhealth*2/3);
			fishhealth -= attack;
		}
	}
	
	public void fightFish() {
		Popup.ShowTwoButtonDialog("attack!", "Use 5 energy and attack the piranha! With luck, you'll defeat it quickly...Your only other option is to run for your life and use up all your remaining energy.\n\nPiranha Energy: " + fishhealth + "/100\nYour energy: " + energy + "/100", "Attack!", "attackPiranha", "Run!", "runMine");
	}
	
	public void panGold() {
		Random rand = new Random();
		int monster = rand.nextInt(100);
		if(pansupply == 0) {
			Images.SetImage(R.id.pan_display, R.raw.panning_nopan);
		}
		else if(energy < 5 && monster <= 20) {
			if(wantsound)
				Audio.PlaySoundEffect(R.raw.fishsound);
			Popup.ShowOneButtonDialog("A piranha is coming!", "I don't have enough energy to fight so I need to escape.", "Run!", "runMine");
		}
		else if(energy >= 5 && monster <= 20) {
			if(wantsound)
				Audio.PlaySoundEffect(R.raw.fishsound);
			fishhealth = 100;
		    Images.SetImage(R.id.pan_display, R.raw.piranha);
		    Images.SetImage(R.id.pan_button, R.raw.attackriver);
		    Images.SetImage(R.id.river_done, R.raw.escapemine_button);
		    Images.SetOnTouchBehavior(R.id.river_done, "escapeRiver"); 
		    Images.SetOnTouchBehavior(R.id.pan_button, "attackPiranha"); 
		}
		else if(energy < 5) {
			Popup.ShowOneButtonDialog("Not enough energy", "I need to eat!", "OK", ProjectFunMobileValues.NO_FUNCTION);
		}
		else if(pancount % 6 == 0 && pancount > 0) {
			pancount++; 
			pansupply = 0; 
			Images.SetImage(R.id.pan_display, R.raw.pan_gone);
		}
		else {
			energy-=5; 
			Random rand1 = new Random();
			int foundGold = rand1.nextInt(100); 
			int earned = 0;

			if(foundGold <= 50){
				earned = 0;
				Images.SetImage(R.id.pan_display, R.raw.panning_zero);
			}
			else if(foundGold > 50 && foundGold <=95){
				earned = 80;
				Images.SetImage(R.id.pan_display,  R.raw.panning_little);
			}
			else {
				earned = 150;
				Images.SetImage(R.id.pan_display,  R.raw.panning_lot);
			}
			money += earned; 
			currmoney = nextmoney - money; 

			Text.SetText(R.id.energylevel, "ENERGY LEFT: " + energy + "/100");
			Text.SetText(R.id.moneypan, "MONEY: $" + money); 
		}
	}

	@Override
	public void onStart() {
		AppLayout.SetLayout(R.layout.start);

		AppLayout.LockScreenLandscape();
	}

}
