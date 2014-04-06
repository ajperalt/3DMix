package android3dmix.models.cellularautomata;

public class Cell {
	public static float liveCellsDistribution = .2f;
	public static final float COLOR_GREEN[] = { 0, 1, 1, 1.0f };
	public static final float COLOR_RED[] = { 1, 0, 0, 1.0f };

	public final Cell[] neighborhood;
	public final int[] neighborhoodIndexX;
	public final int[] neighborhoodIndexY;
	public boolean nextGenerationState;
	public boolean isLive;
	public boolean isBoundary;
	private float color[] = COLOR_GREEN;
	private float position[] = { 0, 0, 0 };

	public Cell() {
		neighborhood = new Cell[8];
		neighborhoodIndexX = new int[8];
		neighborhoodIndexY = new int[8];
		isLive = Math.random() <= liveCellsDistribution ? true : false;
	}

	public void Action() {
		isLive = true;
		nextGenerationState = true;

		for (int i = 0; i < neighborhood.length; i++) {
			if (!neighborhood[i].isBoundary) {
				neighborhood[i].nextGenerationState = true;
				neighborhood[i].isLive = true;
			}
		}
	}

	public float[] getColor() {
		return color;
	}

	public void setColor(float[] color) {
		this.color = color;
	}

	public float[] getPosition() {
		return position;
	}

	public void setPosition(float[] position) {
		this.position = position;
	}
}
