using UnityEngine;
using UnityEngine.UI;

public class PlayerCollision : MonoBehaviour {

	public PlayerMovement movement;
	public Text text;

	// This function runs when we hit another object.
	// We get information about the collision and call it "collisionInfo".
	void OnCollisionEnter (Collision collisionInfo)
	{
		// We check if the object we collided with has a tag called "Obstacle".
		if (collisionInfo.collider.tag == "Obstacle")
		{
			movement.enabled = false;   // Disable the players movement.
			FindObjectOfType<GameManager>().EndGame();
			//FindObjectOfType<Text>().color = Color.red;
			//text.color = Color.red;
			FindObjectOfType<Score>().StopScore();
		}
	}

}
