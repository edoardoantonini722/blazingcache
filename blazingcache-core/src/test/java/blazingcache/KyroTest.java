/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blazingcache;

import java.nio.charset.StandardCharsets;
import blazingcache.client.CacheClient;
import blazingcache.network.ServerHostData;
import blazingcache.network.jvm.JVMServerLocator;
import blazingcache.server.CacheServer;
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

    @Test
    public void basicTest() throws Exception {
        byte[] data = "testdata".getBytes(StandardCharsets.UTF_8);

        ServerHostData serverHostData = new ServerHostData("localhost", -1, "test", false, null);
        try (CacheServer cacheServer = new CacheServer("ciao", serverHostData)) {
            cacheServer.start();
            try (CacheClient client1 = new CacheClient("theClient1", "ciao", new JVMServerLocator(cacheServer, false));) {
                client1.start();
                assertTrue(client1.waitForConnection(10000));

                client1.put("pippo", data, 0);

                Assert.assertArrayEquals(data, client1.get("pippo").getSerializedData());

                client1.invalidate("pippo");
                assertNull(client1.get("pippo"));

                client1.put("key2", data, System.currentTimeMillis());
                Thread.sleep(2000);
                assertNull(client1.get("key2"));

                client1.put("key3", data, -1);
                assertNotNull(client1.get("key3"));
                client1.touchEntry("key3", System.currentTimeMillis());
                Thread.sleep(2000);
                assertNull(client1.get("key3"));

            }

        }

    }
}
