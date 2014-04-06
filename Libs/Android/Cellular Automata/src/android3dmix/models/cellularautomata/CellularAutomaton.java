package android3dmix.models.cellularautomata;

public class CellularAutomaton {

	private int mWidth;
	private int mHeight;
	private Cell[] mCells;

	public CellularAutomaton(int width, int height) {
		mWidth = width;
		mHeight = height;
		Cell.liveCellsDistribution = .3f;	
		CreateLattice();
		SetNeighborhood();
	}

	public void Update() {
		int liveNeighbords = 0;

		for (int i = 0; i < mCells.length; i++) {
			Cell cell = mCells[i];
			if (!cell.isBoundary) {
				liveNeighbords = 0;
				liveNeighbords += (cell.neighborhood[0].isLive
						&& !cell.neighborhood[0].isBoundary ? 1 : 0)
						+ (cell.neighborhood[1].isLive
								&& !cell.neighborhood[1].isBoundary ? 1 : 0)
						+ (cell.neighborhood[2].isLive
								&& !cell.neighborhood[2].isBoundary ? 1 : 0)
						+ (cell.neighborhood[3].isLive
								&& !cell.neighborhood[3].isBoundary ? 1 : 0)
						+ (cell.neighborhood[4].isLive
								&& !cell.neighborhood[4].isBoundary ? 1 : 0)
						+ (cell.neighborhood[5].isLive
								&& !cell.neighborhood[5].isBoundary ? 1 : 0)
						+ (cell.neighborhood[6].isLive
								&& !cell.neighborhood[6].isBoundary ? 1 : 0)
						+ (cell.neighborhood[7].isLive
								&& !cell.neighborhood[7].isBoundary ? 1 : 0);

				if (!cell.isLive && liveNeighbords == 3) {
					cell.nextGenerationState = true;
				} else if (cell.isLive
						&& (liveNeighbords <= 1 || liveNeighbords > 3)) {
					cell.nextGenerationState = false;
				} else
					cell.nextGenerationState = cell.isLive;
			}

		}

		for (int i = 0; i < mCells.length; i++) {
			Cell cell = mCells[i];

			if (!cell.isBoundary) {
				cell.isLive = cell.nextGenerationState;
				cell.setColor(cell.isLive ? Cell.COLOR_RED : Cell.COLOR_GREEN);
			}
		}
	}

	private void CreateLattice() {
		mCells = new Cell[mWidth * mHeight];
		int iIdx = 0;
		int jIdx = 0;

		for (int i = 0; i < mCells.length; i++) {
			Cell cell = new Cell();
			mCells[i] = cell;
			cell.setPosition(new float[] { (jIdx - mHeight / 2), iIdx, 0 });

			if (iIdx <= 0 || iIdx + 1 >= mWidth || jIdx <= 0
					|| jIdx + 1 >= mHeight) {
				cell.isBoundary = true;
				cell.setColor(Cell.COLOR_GREEN);
			} else {
				cell.isBoundary = false;
				cell.neighborhoodIndexX[0] = iIdx - 1;
				cell.neighborhoodIndexX[1] = iIdx;
				cell.neighborhoodIndexX[2] = iIdx + 1;
				cell.neighborhoodIndexX[3] = iIdx - 1;
				cell.neighborhoodIndexX[4] = iIdx + 1;
				cell.neighborhoodIndexX[5] = iIdx - 1;
				cell.neighborhoodIndexX[6] = iIdx;
				cell.neighborhoodIndexX[7] = iIdx + 1;

				cell.neighborhoodIndexY[0] = jIdx + 1;
				cell.neighborhoodIndexY[1] = jIdx + 1;
				cell.neighborhoodIndexY[2] = jIdx + 1;
				cell.neighborhoodIndexY[3] = jIdx;
				cell.neighborhoodIndexY[4] = jIdx;
				cell.neighborhoodIndexY[5] = jIdx - 1;
				cell.neighborhoodIndexY[6] = jIdx - 1;
				cell.neighborhoodIndexY[7] = jIdx - 1;
			}

			if (++iIdx == mHeight) {
				iIdx = 0;
				jIdx++;
			}
		}
	}

	private void SetNeighborhood() {
		for (int i = 0; i < mCells.length; i++) {
			Cell cell = mCells[i];

			if (!cell.isBoundary) {
				for (int j = 0; j < cell.neighborhood.length; j++)
					cell.neighborhood[j] = mCells[cell.neighborhoodIndexY[j]
							* mHeight + cell.neighborhoodIndexX[j]];
			}
		}

	}

	public Cell[] get_cells() {
		return mCells;
	}

	public void set_cells(Cell[] cells) {
		mCells = cells;
	}
}
