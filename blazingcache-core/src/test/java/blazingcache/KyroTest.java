/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blazingcache;

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

import org.junit.Test;
import static org.junit.Assert.assertTrue;

/**
 *Test class for the serializers.
 * @author edoardoantonini722
 */
public class KyroTest {

	private static final int BIKE_NUMBER=100000;
	
	private long start,end;
	private KyroEntrySerializer kyroSerializer = new KyroEntrySerializer();
	private JDKEntrySerializer jdkSerializer = new JDKEntrySerializer();
	
    @Test
    public void basicTest() throws Exception {
    	
        ServerHostData serverHostData = new ServerHostData("localhost", -1, "test", false, null);
        try (CacheServer cacheServer = new CacheServer("ciao", serverHostData)) {
            cacheServer.start();
            try (CacheClient client1 = new CacheClient("theClient1", "ciao", new JVMServerLocator(cacheServer, false));
            		CacheClient client2 = new CacheClient("theClient1", "ciao", new JVMServerLocator(cacheServer, false));) {
            	
                client1.start();
                assertTrue(client1.waitForConnection(10000));
                client2.start();
                assertTrue(client2.waitForConnection(10000));
                
                start = System.currentTimeMillis();
                
                client1.setEntrySerializer(kyroSerializer);
                client2.setEntrySerializer(kyroSerializer);
                //put a big list with Kyro
                for(int i = 0;i<BIKE_NUMBER;i++)
                {
                	client1.putObject("bike"+i, null, -1);
                	client2.putObject("bike"+i, getCasualBike(), -1);
                }
                //read a big list with Kyro
                for(int i = 0;i<BIKE_NUMBER;i++)
                {
                	client1.fetchObject("bike"+i);
                }
                
            	end = System.currentTimeMillis();
            	
            	System.out.println("Kyro time :"+(end-start)+" milliseconds");

                
                start = System.currentTimeMillis();
                
                client1.setEntrySerializer(jdkSerializer);
                client2.setEntrySerializer(jdkSerializer);
                //put a big list without Kyro
                for(int i = 0;i<BIKE_NUMBER;i++)
                {
                	client1.putObject("bike"+i, null, -1);
                	client2.putObject("bike"+i, getCasualBike(), -1);
                }
                //read a big list without Kyro
                for(int i = 0;i<BIKE_NUMBER;i++)
                {
                	client1.fetchObject("bike"+i);
                }
                
                end = System.currentTimeMillis();
                
                System.out.println("JDK time :"+(end-start)+" milliseconds");
				

            }

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
    
    private Bike getCasualBike()
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
		
		return bike;
    }
    
}
