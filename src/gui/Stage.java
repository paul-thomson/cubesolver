package gui;

public enum Stage {
	ONE (1),
	TWO (2),
	THREE (3),
	FOUR (4),
	FIVE (5),
	SIX (6),
	SEVEN (7);
	
	private int number;
	
	private Stage(int num) {
		this.number = num;
	}
	
	public int getNumber() {
		return number;
	}

}
