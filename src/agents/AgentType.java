package agents;

/**
 * Enum class that defines the types of agents. Type 0, 1, and 2 agents follow those described
 * in the original paper by E.L. Glasser, B.Sacerdote, and J.A. Scheinkman. Null agent is a new
 * artifact within this model implementation that is for the initial model time steps before
 * the specified visualization time window of an agent's history isn't fully populated yet.
 * 
 * @author Brant Horio, George Mason University (2016).
 *
 */
public enum AgentType 
{
	TYPE0_LAWBREAKER, TYPE1_LAW_ABIDER, TYPE2_IMITATOR, NULL_AGENT;
}
