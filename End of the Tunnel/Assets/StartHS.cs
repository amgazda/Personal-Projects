using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class StartHS : MonoBehaviour
{
    public Text aths;
    // Start is called before the first frame update
    void Start()
    {
        aths.text = "High Score: " + PlayerPrefs.GetString("HS");
    }

    
}
