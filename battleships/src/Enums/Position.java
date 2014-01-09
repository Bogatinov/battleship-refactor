package Enums;

public class Position {
	private int x;
    private int y;
    private Orientation orientation;

    public Position(int x, int y, Orientation orientation)
    {
        this.x = x;
        this.y = y;
        this.orientation = orientation;
    }

    public Orientation Orientation() {
        return orientation;
    }

    public int Y() {
    	return y;
    }

    public int X() {
    	return x;
    }

    public String toString()
    {
        return String.format("X = %d, Y= %d, %s", x, y, orientation.toString());
    }
}
