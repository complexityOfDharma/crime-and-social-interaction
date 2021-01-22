package model;

import java.util.ArrayList;

import agents.Agent;
import agents.Society;
import sim.engine.Schedule;
import sim.engine.SimState;
import sim.engine.Steppable;
import sim.engine.Stoppable;
import sim.field.grid.ObjectGrid2D;

/**
 * A Model of Crime and Social Interaction: originally by E.L. Glaeser, B.Sacerdote, and J.A. Scheinkman (1996).
 *   
 * This model simulates a population of agents on a lattice and explores how social interactions might 
 * explain high cross-city variance of crime rates.
 * 
 * @author Brant Horio, George Mason University (2016)
 */
public class CrimeModel_Controller extends SimState
{
	private static final long serialVersionUID = 1;
	
	/** Random seed value to allow reproducibility of results.*/
	public static long RANDOM_SEED = 12345;
	
	/** Number of agents to instantiate into the world.*/
	public static int NUM_AGENTS = 80;
	/** Rolling time period to see an agent's crime history.*/
	public static int CRIME_HISTORY_LENGTH = 80;
	
	/** The world that the agents will interact within.*/
	public ObjectGrid2D world;
	/** This is a matrix that will track all agents.*/
	public Society society;
	
	/**
	 * Constructor.
	 */
	public CrimeModel_Controller(long seed)
	{
		super(seed);
	}
	
	/**
	 * Initialize the model world as a 2D grid.
	 */
	public void init()
	{
		world = new ObjectGrid2D(CRIME_HISTORY_LENGTH, NUM_AGENTS);
	}
	
	/**
	 * MASON method run at the beginning of start of the simulation.
	 */
	public void start()
	{
		super.start();
		init();							// initialize the world.
		
		society = new Society(this);	// instantiate the matrix of agents, one agent per grid cell.
		society.initAgents();			// initialize the agents.
		
		// retrieve all instantiated agents and store in a local collection for adding to the simulation schedule.
		ArrayList<Agent> initialAgents = society.getAllAgents();
		
		// add the agents on the schedule with the highest priority.
		for(Agent agent : initialAgents)
		{
			schedule.scheduleRepeating(Schedule.EPOCH, 0, agent, 1);
		}
		
		// next, add the Society to the schedule with the next level of priority.
		schedule.scheduleRepeating(Schedule.EPOCH, 1, society, 1);
	}
	
	/**
	 * Main.
	 * @param args
	 */
	public static void main(String[] args)
	{
		doLoop(CrimeModel_Controller.class, args);
		System.exit(0);
	}
}
