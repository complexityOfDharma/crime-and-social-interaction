package agents;

import java.util.ArrayList;

import model.CrimeModel_Controller;
import sim.engine.SimState;
import sim.engine.Steppable;

/**
 * Society object class that instantiates a matrix to track all model agents. Based on the approach and code
 * from Ernesto Carrella in the Retirement Age Model.
 * 
 * @author Brant Horio, George Mason University (2016).
 *
 */
public class Society implements Steppable
{
	private static final long serialVersionUID = 1L;
	
	/** Matrix to track all model agents.*/
	protected static Agent[][] agentMatrix;
	
	/** Reference to the simualtion controller class.*/
	private CrimeModel_Controller model;
	
	/**
	 * Constructor.
	 * @param model
	 */
	public Society(CrimeModel_Controller model)
	{
		this.model = model;
		
		// instantiate the agent matrix.
		agentMatrix = new Agent[CrimeModel_Controller.CRIME_HISTORY_LENGTH][CrimeModel_Controller.NUM_AGENTS];
	}

	/**
	 * Instantiates the agents into the model, based on fixed ratio for Type 0, 1, and 2 agents.
	 * @param agentID
	 * @return new agent
	 */
	private Agent createNewAgent(int agentID)
	{
		Agent agent;
		
		// draw a random variable on the range U[0, 1].
		double pick = model.random.nextDouble();
		
		if (pick < 0.05)			// 5% probability for Type 0 agent to be instantiated.
		{
			agent = new T0_Lawbreaker(model);
		}
		else if (pick < 0.10)		// 5% probability for Type 1 agent to be instantiated.	
		{
			agent = new T1_LawAbider(model);
		}
		else						// 5% probability for Type 2 agent to be instantiated.
		{
			agent = new T2_Imitator(model, agentID);
		}
		
		return agent;
	}
	
	/**
	 * Instantiate a null agent.
	 * @return
	 */
	private Agent createNullAgent()
	{
		Agent agent;
		agent = new Null_Agent(model);
		
		return agent;
	}
	
	/**
	 * Initialize all instantiated agents.
	 */
	public void initAgents()
	{
		// instantiate new agents for time 0.
		for(int a = 0; a < agentMatrix[0].length; a++)
		{
			// create a cohort of new agents in the world, in the far left column in the visualization.
			agentMatrix[0][a] = createNewAgent(a);
		}
		
		// now for all the remaining rows at beginning of model run, populate with null agents.
		for(int t = 1; t < agentMatrix.length; t++)
		{	
			// for each agent at a given time unit
			for(int a = 0; a < agentMatrix[t].length; a++)
			{
				agentMatrix[t][a] = createNullAgent();
			}
		}
		
		// copy agent to the world grid.
		copyAgentsToGrid();
	}
	
	/**
	 * Copy the agents stored in the agent matrix to the simualtion world grid.
	 */
	public void copyAgentsToGrid()
	{
		// for each crime history unit of time
		for(int t = 0; t < agentMatrix.length; t++)
		{	
			// for each agent
			for(int a = 0; a < agentMatrix[t].length; a++)
			{
				// copy agent to the world grid.
				model.world.field[t][a] = agentMatrix[t][a]; 
			}
		}
	}
	
	/**
	 * Persist agent's prior crime state and create a new one.
	 */
	@Override
	public void step(SimState state) 
	{
		// in reverse order starting at (length - 1), transfer current state to the state of the (t + 1) column. 
		for (int t = agentMatrix.length - 1; t > 0; t--)
		{
			// for each agent.
			for (int a = 0; a < agentMatrix[t].length; a++)
			{
				// assign the color value to that of the time unit prior to it, and in doing so, we advance the history. 
				agentMatrix[t][a].setDoubleValueColor(agentMatrix[t-1][a].doubleValueColor);
			}
		}
	}
	
	/**
	 * Return a list containing all the agents in the matrix for adding to the model schedule.
	 * @return
	 */
	public ArrayList<Agent> getAllAgents()
	{
		// local collection.
		ArrayList<Agent> agentList = new ArrayList<Agent>();
		
		// for each agent in the agent matrix.
		for (Agent[] agents : agentMatrix)
		{
			for (Agent historicalAgent : agents)
			{
				// add the agent's to this local collection.
				agentList.add(historicalAgent);
			}
		}
		
		return agentList;
	}
}
