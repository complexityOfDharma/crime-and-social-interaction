package agents;

import model.CrimeModel_Controller;

/**
 * Agent type class for agents described in the original paper as Type 2, or "imitators", 
 * who are agents that are swayed to change their behavior based on their neighbors. In
 * this implementation, imitator agents randomly choose a neighbor to either side of them
 * and copy their status into their own. The lattice is also assumed to wrap around.
 * 
 * @author Brant Horio, George Mason University (2016).
 *
 */
public class T2_Imitator extends Agent 
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Calls Agent constructor. 
	 * @param model : reference to the main model
	 */
	public T2_Imitator(CrimeModel_Controller model, int agentID) 
	{
		super(model);
		
		// assign the agent's unique identifier.
		this.agentID = agentID;
		// assign the agent's type.
		this.agentType = AgentType.TYPE2_IMITATOR;
		// assign the agent's status.
		this.status = Status.LAW_ABIDER;
		// assign the agent's color value.
		this.doubleValueColor = doubleValue();
	}

	/**
	 * Agent's look in a random direction to their right or left, and update their status to that selected
	 * neighbor's status.
	 */
	@Override
	protected Status updateStatus() 
	{
		// draw a random number on the range U[0, 1] and store in a local variable 'pick'.
		double pick = model.random.nextDouble();
				
		// local variable to store the status of the selected neighbor.
		Status statusToCopy = null;
		
		// even probability to select the neighbor on one side vs. the other.
		if(pick < 0.5)
		{
			// looking to the right (ascending in ID value)
			if (this.agentID == (Society.agentMatrix[0].length - 1))
			{
				// agent is all the way at the highest location in the lattice, thus wrap to lowest location.
				statusToCopy = Society.agentMatrix[0][0].getStatus();
			}
			else
			{
				// look to one agent higher.
				statusToCopy = Society.agentMatrix[0][this.agentID + 1].getStatus();
			}
			
			return statusToCopy;
		} 
		else
		{
			// looking to the left (descending in ID value)
			if (this.agentID == 0)
			{
				// agent is all the way at the lowest location in the lattice, thus wrap to highest location.
				statusToCopy = Society.agentMatrix[0][Society.agentMatrix[0].length - 1].getStatus();
			}
			else
			{
				// look to one agent lower.
				statusToCopy = Society.agentMatrix[0][this.agentID - 1].getStatus();
			}
					
			return statusToCopy;
		}
	}

	@Override
	public double doubleValue() 
	{
		switch (status) 
		{
			default 				:	return 1;		// law abider.
			case LAWBREAKER			:	return 0;
		}
	}
}

