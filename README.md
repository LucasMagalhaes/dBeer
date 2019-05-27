
## What?

**Truck riding simulator**
It simulate a beer truck ride, each 3 seconds the truck unload a beer (Random).
When the beer is unloaded 2 things can happen:
	The door of container could be left open. (Random)
	The temperature increase in 1 degree. (Always)
	
When one of those events happen, the driver receive an alert to fix it.

## How?
I have made use of Callable interface, so I could manage the thread and implement the Truck monitor, this gave me the power to manage all truck state on a separe thread regardless ride operation.

