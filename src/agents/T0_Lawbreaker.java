package agents;

import model.CrimeModel_Controller;

/**
 * Agent type class for agents described in the original paper as Type 0, or "diehard lawbreaker", 
 * who are agents that are not swayed to change their behavior.
 * 
 * @author Brant Horio, George Mason University (2016).
 *
 */
public class T0_Lawbreaker extends Agent 
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Calls Agent constructor. 
	 * @param model : reference to the main model
	 */
	public T0_Lawbreaker(CrimeModel_Controller model) 
	{
		super(model);
		
		// assign agent type.
		this.agentType = AgentType.TYPE0_LAWBREAKER;
		// assign agent status.
		this.status = Status.LAWBREAKER;
		// assign agent's visualization color.
		this.doubleValueColor = 2;
	}

	@Override
	protected Status updateStatus() 
	{
		// always update this agent's status to "LAW_ABIDER" since they are diehard agents that do not change.
		return Status.LAWBREAKER;
	}

	@Override
	public double doubleValue() 
	{
		return this.doubleValueColor;
	}
}
