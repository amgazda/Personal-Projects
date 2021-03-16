using UnityEngine;

public class MoveObstacle : MonoBehaviour
{

	// This is a reference to the Rigidbody component called "rb"
	public Rigidbody rb;

	public float forwardForce = 2000f;  // Variable that determines the forward force

	// We marked this as "Fixed"Update because we
	// are using it to mess with physics.
	void FixedUpdate()
	{
		// Add a forward force
		rb.AddForce(0, 0, forwardForce * Time.deltaTime);

		if (rb.position.y < -1f)
		{
			Destroy(rb.gameObject);
		}
	}

}
