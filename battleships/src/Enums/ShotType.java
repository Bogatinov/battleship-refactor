package Enums;

public class ShotType {
	private GridValue value;
	private String shipType;
	public ShotType(GridValue value, String shipType) {
		super();
		this.value = value;
		this.shipType = shipType;
	}
	public GridValue getValue() {
		return value;
	}
	public String getShipType() {
		return shipType;
	}
}
