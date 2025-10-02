For the implementation of **GwentStone Lite**, I used a set of core classes, designed similarly to those from `fileio`, as follows:

* **Cards** → contains the same fields as `CardInput`.
* **Deck** → represents the card deck and its characteristics (number of decks and number of cards per deck).
* **Hero** → similar to `Cards`, but without the `attackDamage` field.
* **Player** → contains fields such as `mana` and the number of cards in hand, along with methods for managing mana, adding, and removing cards from the hand.
* **Action** → implements the behavior of the actions received as input for each game.
* **Board** → represented as a list of lists, stores all the cards placed on the board and manages modifications such as adding or removing cards. Lists were chosen for their advantages in dynamic insertion and deletion.
* **StartGame** → accesses the required fields for initializing a new game.
* **MatchPreparation** → initializes the starting data of the game; this class is instantiated each time a new game begins.
* **Display interface** → consists only of methods responsible for outputting error messages or expected results in JSON format.
* **MatchExecution** → the main class where all commands are tested and executed. It inherits from `StartGame` (to access initial game data) and implements the `Display` interface to ensure correct output.

In the **main** function:

* Input data is first retrieved and converted into objects of the defined classes using dedicated constructors.
* For each game, the starting situation is initialized, followed by iterating over each command and executing it accordingly.

