package Enums;

public enum GridValue {
			MinesweeperShot(-6),
			DestroyerShot(-5),
			BattleshipShot(-4),
			AircraftcarrierShot(-3),
			SubmarineShot(-1),
			
			EmptyCellValue(0),
	        MissedShot(1),
	        SuccessfulShot(9),
	        
	        MinesweeperIntact(2),
	        DestroyerIntact(3),
	        BattleshipIntact(4),
	        AircraftcarrierIntact(5),
	        SubmarineIntact(7);
			
	        private final int value;
			GridValue(int value) { this.value = value; }
	        public int getValue() { return value; }
}
