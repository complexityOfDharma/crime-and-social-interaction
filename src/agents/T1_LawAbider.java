package agents;

import model.CrimeModel_Controller;

/**
 * Agent type class for agents described in the original paper as Type 1, or "diehard law abider", 
 * who are agents that are not swayed to change their behavior.
 * 
 * @author Brant Horio, George Mason University (2016).
 */
public class T1_LawAbider extends Agent 
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Calls Agent constructor. 
	 * @param model : reference to the main model
	 */
	public T1_LawAbider(CrimeModel_Controller model) 
	{
		super(model);
		
		// assign agent type. 
		this.agentType = AgentType.TYPE1_LAW_ABIDER;
		// assign agent status.
		this.status = Status.LAW_ABIDER;
		// assign agent's visualization color value.
		this.doubleValueColor = 3;
	}

	@Override
	protected Status updateStatus() 
	{
		// always update this agent's status to "LAW_ABIDER" since they are diehard agents that do not change.
		return Status.LAW_ABIDER;
	}

	@Override
	public double doubleValue() 
	{
		return this.doubleValueColor;
	}
}
