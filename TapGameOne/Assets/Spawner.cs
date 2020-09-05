using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Spawner : MonoBehaviour
{

    private float timeBtwSpawns;
    public float startTimeBtwSpawns;
    public int num = 4;


    public GameObject[] obstacleTemplate;

    private void Start()
    {
        timeBtwSpawns = startTimeBtwSpawns;
    }

    private void Update()
    {
        //Debug.Log("S run");
        List<int> l = new List<int>();
        for(int i = 0; i< obstacleTemplate.Length; i++)
        {
            l.Add(i);
        }
        if (timeBtwSpawns <= 0)
        {
            //int rand = Random.Range(0, obstacleTemplate.Length);
            for(int i = 0; i<num;i++)
            {
                int rand = Random.Range(0, l.Count);
                int numero = l[rand];
                l.RemoveAt(rand);
                Instantiate(obstacleTemplate[numero], obstacleTemplate[numero].transform.position, Quaternion.identity);
            }
            
            timeBtwSpawns = startTimeBtwSpawns;
        }
        else
        {
            timeBtwSpawns -= Time.deltaTime;
        }
    }

}
