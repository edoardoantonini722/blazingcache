/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blazingcache;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import blazingcache.client.CacheClient;
import blazingcache.client.impl.JDKEntrySerializer;
import blazingcache.client.impl.KyroEntrySerializer;
import blazingcache.network.ServerHostData;
import blazingcache.network.jvm.JVMServerLocator;
import blazingcache.server.CacheServer;
import testEdoardo.Bike;
import testEdoardo.Brand;
import testEdoardo.Seat;
import testEdoardo.Wheel;
import testEdoardo.Frame;
import testEdoardo.Profile;

import org.junit.Assert;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author edoardoantonini722
 */
public class KyroTest {

	
	List<Bike> bikeArray = new ArrayList<>();
	List<Bike> returnedBikeArray;
    @Test
    public void basicTest() throws Exception {
    	
    	long start = System.currentTimeMillis();
    	generateBikeArray();
    	long end = System.currentTimeMillis();
    	System.out.println("Generation completed in:"+(end-start)+" millisecondi");
    	
    	
        ServerHostData serverHostData = new ServerHostData("localhost", -1, "test", false, null);
        try (CacheServer cacheServer = new CacheServer("ciao", serverHostData)) {
            cacheServer.start();
            try (CacheClient client1 = new CacheClient("theClient1", "ciao", new JVMServerLocator(cacheServer, false));) {
                client1.start();
                assertTrue(client1.waitForConnection(10000));
                
                
                start = System.currentTimeMillis();
                
                client1.setEntrySerializer(new KyroEntrySerializer());
                //put a lot of bike objects with Kyro
                client1.putObject("bikeArrayKyro", this.bikeArray, 1000000);
                //read a lot of bike objects with Kyro
                returnedBikeArray=client1.fetchObject("bikeArrayKyro");
                
            	end = System.currentTimeMillis();
            	
            	System.out.println("Kyro time "+(end-start)+" milliseconds");
                System.out.println(returnedBikeArray.get(52).toString());

                
                start = System.currentTimeMillis();
                
                client1.setEntrySerializer(new JDKEntrySerializer());
                //put a lot of bike objects without Kyro
                client1.putObject("bikeArrayKyro", this.bikeArray, 1000000);
                //read a lot of bike objects without Kyro
                returnedBikeArray=client1.fetchObject("bikeArrayKyro");
                
                end = System.currentTimeMillis();
                
                System.out.println("JDK time "+(end-start)+" milliseconds");
                System.out.println(returnedBikeArray.get(52).toString());


            }

        }
        
    }
    

    private void generateBikeArray()
    {
    	for(int i=0;i<300000;i++)
    	{
    		Bike bike = new Bike();
    		Frame frame = new Frame();
    		Wheel frontWheel = new Wheel();
    		Wheel rearWheel = new Wheel();
    		Seat seat = new Seat();
    		
    		frame.setWeight(new Random().nextDouble()*100);
    		frame.setBrand(getCasualBrand());
    		
    		frontWheel.setBrand(getCasualBrand());
    		frontWheel.setDiameter(new Random().nextDouble()*100);
    		frontWheel.setLenticulararity(new Random().nextBoolean());
    		frontWheel.setProfile(getCasualProfile());
    		
    		rearWheel.setBrand(getCasualBrand());
    		rearWheel.setDiameter(new Random().nextDouble()*100);
    		rearWheel.setLenticulararity(new Random().nextBoolean());
    		rearWheel.setProfile(getCasualProfile());
    		
    		seat.setBrand(getCasualBrand());
    		seat.setLenght(new Random().nextDouble()*100);
    		
    		bike.setFrame(frame);
    		bike.setFrontWheel(frontWheel);
    		bike.setRealWheel(rearWheel);
    		bike.setSeat(seat);
    		
    		this.bikeArray.add(bike);
    	}
    }
    
    private Brand getCasualBrand()
    {
    	Random r = new Random();
    	int i = r.nextInt(3);
    	return Brand.values()[i];
    }
    
    private Profile getCasualProfile()
    {
    	Random r = new Random();
    	int i = r.nextInt(3);
    	return Profile.values()[i];
    }
}
