using UnityEngine;
using UnityEngine.UI;
using System;

public class Score : MonoBehaviour {

	public Transform player;
	public Text scoreText;
	private float score;
	private bool hasCol = false;
	

	void Start()
    {
		scoreText.text = "0";

	}
	// Update is called once per frame
	
	void Update () {
        if (hasCol == false)
        {
			score += 2*Time.deltaTime;
			scoreText.text = (score).ToString("0");
        }
        else
        {
			int newScore=0;
			int currHigh=0;
			scoreText.color = Color.red;
            if(PlayerPrefs.HasKey("HS"))
            {
				try
				{
					currHigh = Int32.Parse(PlayerPrefs.GetString("HS"));
					newScore = Int32.Parse(scoreText.text);
				}
				catch (FormatException) { }
				if(newScore>currHigh)
				{
					PlayerPrefs.SetString("HS", scoreText.text);
				}
            }
            else
            {
				PlayerPrefs.SetString("HS", scoreText.text);
			}
			
		}
		
	}
	public void StopScore()
    {
		hasCol = true;
    }

}
