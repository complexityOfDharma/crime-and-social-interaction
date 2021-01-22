package agents;

import model.CrimeModel_Controller;

/**
 * Agent type class for the Null Agent.
 * 
 * @author Brant Horio, George Mason University (2016).
 *
 */
public class Null_Agent extends Agent
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor.
	 * @param model
	 */
	public Null_Agent(CrimeModel_Controller model)
	{
		super(model);
		
		// specify agent type.
		this.agentType = AgentType.NULL_AGENT;
		// assign current status.
		this.status = Status.NULL_AGENT;
		// assign agent visualization color.
		this.doubleValueColor = 4;
	}

	@Override
	protected Status updateStatus() 
	{
		return Status.NULL_AGENT;
	}

	@Override
	public double doubleValue() 
	{
		return this.doubleValueColor;
	}
	
}
