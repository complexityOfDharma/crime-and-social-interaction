# A Model of Crime and Social Interaction
This is a replication of the original model (in MASON) from Glaeser, Sacerdote, and Scheinkman (1996) that worked on an explanatory account of crime and social interaction.

Glaeser, E.L., B. Sacerdote, J. Scheinkman. 1996. Crime and Social Interactions, The Quarterly Journal of Economics, 1996, vol. 111, issue 2, 507-548

Research abstract from Glaeser, Sacerdote, and Scheinkman: The high variance of crime rates across time and space is one of the oldest puzzles in the social sciences; this variance appears too high to be explained by changes in the exogenous costs and benefits of crime. We present a model where social interactions create enough covariance across individuals to explain the high cross-city variance of crime rates. This model provides an index of social interactions which suggests that the amount of social interactions is highest in petty crimes, moderate in more serious crimes, and almost negligible in murder and rape.

## Model

in progress... model description using ODD Protocol

### Agents
Agent types include:
- Die-hard lawbreakers (dark orange-red) are not swayed by their neighbors and will always break the law.
- Die-hard law abiders (dark blue) are not swayed by their neighbors and will always abide by the law.
- Imitator agents randomly choose an adjacent neighbor at each time step and copy their decision to break or abide by the law (light orange for imitating law breakers, light blue for imitating law abiders).

