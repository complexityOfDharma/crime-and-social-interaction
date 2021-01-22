package portrayals;

import java.awt.Color;
import java.awt.Graphics2D;

import agents.Agent;
import agents.AgentType;
import sim.portrayal.DrawInfo2D;
import sim.portrayal.simple.RectanglePortrayal2D;
import sim.util.gui.SimpleColorMap;

/**
 * Agent portrayal class.
 * 
 * @author Brant Horio, George Mason University (2016)
 */
@SuppressWarnings("serial")
public class AgentPortrayal2D extends RectanglePortrayal2D
{
	/** Agent colors to represent their current state of law abiding or law breaking.*/
	// taken from Joseph Harrison, George Mason University, replication of Axtell's & Epstein's retirement age model.
	private final SimpleColorMap imitatorAgentColors = 
		new SimpleColorMap(new Color[] 
		{ 
			new Color(100, 160, 214), 	// imitating lawbreaker (darker shade)
			new Color(255, 154, 109), 	// imitating law abider (lighter shade)
			new Color(10, 83, 148),		// die-hard lawbreaker
			new Color(228, 71, 0),		// die-hard law abider
			Color.WHITE 				// null agent
		} );

	/**
	 * Draw method for all agents in the visualization.
	 */
	@Override
	public void draw(Object object, Graphics2D graphics, DrawInfo2D info) 
	{
		Agent a = (Agent) object;

		// color the agent according to their doubleValue returned.
		// taken from Joseph Harrison, George Mason University, replication of Axtell's & Epstein's retirement age model.
		paint = imitatorAgentColors.getColor(a.doubleValue());
		
		super.draw(object, graphics, info);

        graphics.setColor(Color.black);									// taken from Joseph Harrison, George Mason University, replication of Axtell's & Epstein's retirement age model.
        final int x = (int) (info.draw.x - info.draw.width / 2.0);		// taken from Joseph Harrison, George Mason University, replication of Axtell's & Epstein's retirement age model.
        final int y = (int) (info.draw.y - info.draw.height / 2.0);		// taken from Joseph Harrison, George Mason University, replication of Axtell's & Epstein's retirement age model.
        final int x2 = x + (int) info.draw.width - 1;					// taken from Joseph Harrison, George Mason University, replication of Axtell's & Epstein's retirement age model.
        final int y2 = y + (int) info.draw.height - 1;					// taken from Joseph Harrison, George Mason University, replication of Axtell's & Epstein's retirement age model.

        // draw a line down the left and top. 
        graphics.drawLine(x, y, x2, y);	// taken from Joseph Harrison, George Mason University, replication of Axtell's & Epstein's retirement age model.
        graphics.drawLine(x, y2, x, y);	// taken from Joseph Harrison, George Mason University, replication of Axtell's & Epstein's retirement age model.
	}

}

