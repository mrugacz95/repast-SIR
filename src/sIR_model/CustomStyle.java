package sIR_model;

import java.awt.Color;

import repast.simphony.visualizationOGL2D.DefaultStyleOGL2D;

public class CustomStyle extends DefaultStyleOGL2D {
	@Override
	public Color getColor(final Object agent) {
		if (agent instanceof Susceptible) {
				return Color.BLACK;
		} else if (agent instanceof Infectious) {
			return Color.RED;
		}
		return Color.GREEN;
	}

}
