package agents;

import java.util.ArrayList;

import model.CrimeModel_Controller;
import sim.engine.SimState;
import sim.engine.Steppable;
import sim.engine.Stoppable;
import sim.util.Valuable;

/**
 * Abstract agent class. Implements Steppable to allow agents to be added to MASON's scheduler, and implements Valuable
 * to allow the object to return a doubleValue, used in the portrayal for determining the visualization color of their
 * current status of law abiding or law breaking.
 * 
 * @author Brant Horio, George Mason University (2016).
 *
 */
public abstract class Agent implements Steppable, Valuable
{
	private static final long serialVersionUID = 1L;

	/** Reference to the containing model.*/
	protected CrimeModel_Controller model;
	/** Unique agent identifier.*/
	public int agentID;
	/** Agent type of lawbreaker, law abider, or imitator agent.*/
	@SuppressWarnings("rawtypes")
	public Enum agentType;
	
	/** Agent's current status.*/
	protected Status status;
	/** Agent's current visualization color.*/
	public double doubleValueColor;
	
	/**
	 * Constructor.
	 * @param model
	 */
	public Agent (CrimeModel_Controller model)
	{
		this.model = model;
		
		// default status.
		this.status = Status.NULL_AGENT;
	}
	
	/**
	 * Schedule step logic that updates an agent's new status and stores their current color value.
	 */
	@Override
	public void step(SimState state) 
	{
		// update agent's status.
		this.status = updateStatus();
		
		// assign their current color value.
		this.doubleValueColor = doubleValue();
	}

	/**
	 * This is the main method to override for Agent-type decision making.
	 *@return status : agent status
	 */
	abstract protected Status updateStatus();

	/**
	 * Retrieves the agent's unique ID.
	 * @return agentID : agent's unique identifier.
	 */
	public int getAgentID() { return agentID; }

	/**
	 * Assigns color value.
	 * @param value
	 */
	public void setDoubleValueColor(double value)
	{
		this.doubleValueColor = value;
	}
	
	/**
	 * Returns the agent's current status.
	 * @return status : agent's current status.
	 */
	public Status getStatus() { return status; }

	/**
	 * Assigns the agent's new status.
	 * @param status
	 */
	public void setStatus(Status status) { this.status = status; }
}
