package ui.overlay;

public enum Side {
	TOP, RIGHT, BOTTOM, LEFT, CENTER;

	/**
	 * A method used to rotate the visuals when displaying an OverlayButton
	 *
	 * @return
	 */
	double angle() {
		switch (this) {
			case TOP:
				return 0;
			case RIGHT:
				return 90;
			case BOTTOM:
				return 180;
			case LEFT:
				return 270;
			case CENTER:
			default:
				return 0;
		}
	}
}

