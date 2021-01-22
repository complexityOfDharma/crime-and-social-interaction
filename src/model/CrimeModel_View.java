package model;

import java.awt.Color;

import javax.swing.JFrame;

import portrayals.AgentPortrayal2D;
import sim.display.Console;
import sim.display.Controller;
import sim.display.Display2D;
import sim.display.GUIState;
import sim.engine.SimState;
import sim.portrayal.grid.ObjectGridPortrayal2D;

/**
 * Model visualization class for the simulation.
 * 
 * @author Brant Horio, George Mason University (2016)
 *
 */
public class CrimeModel_View extends GUIState
{
	private Display2D display;
	private JFrame displayFrame;
	
	// instantiate the MASON portrayal object for the visualization.
	private ObjectGridPortrayal2D agentPortrayal = new ObjectGridPortrayal2D();

	/**
	 * Constructor.
	 * @param state
	 */
	public CrimeModel_View(SimState state) 
	{
		super(state);
	}
	
	/**
	 * Constructor.
	 */
	public CrimeModel_View() 
	{
    	super(new CrimeModel_Controller(System.currentTimeMillis())); 
	}

	/** 
	 * @return name of the visualization window.
	 */
    public static String getName() 
    {
    	return "A Model of Crime and Social Interaction";
	}
    
    /**
     * Set up the portrayals.
     */
    public void setupPortrayals() 
	{
		agentPortrayal.setPortrayalForAll(new AgentPortrayal2D());
		agentPortrayal.setField(((CrimeModel_Controller)state).world);

		display.reset();
		display.repaint();
	}

	@Override
	public void init(Controller c) 
	{
		super.init(c);

		// instantiate the world display window.
		display = new Display2D(800, 800, this);
													
		displayFrame = display.createFrame();
		c.registerFrame(displayFrame); 										
		displayFrame.setVisible(true);

		display.attach(agentPortrayal, "Agents");

		display.setBackdrop(Color.black);

		// configure the size of the console window for the simulation controls.
		((Console) controller).setSize(550, 500);
	}

	@Override
	public void start() 
	{
		super.start();
		setupPortrayals();
	}
    
    @Override
	public void finish() 
    {
		super.finish();
	}

	public void quit() 
	{
		super.quit();

		if (displayFrame != null)
		{
			displayFrame.dispose();
		}
		
		displayFrame = null;
		display = null;
	}

    /**
     * Main.
     * @param args
     */
	public static void main(String[] args) 
    {
    	new CrimeModel_View().createController();
    }
}