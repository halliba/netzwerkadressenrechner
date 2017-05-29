import static org.junit.Assert.*;

import logic.IPv4.IPv4Address;
import logic.IPv4.IPv4Network;
import logic.Type;
import org.junit.Test;

public class IPv4NetworkTest {
    @Test
    public void maxAmountHostsTest(){
        //given
        IPv4Network iPv4NetworkOne = new IPv4Network();
        String[] netmaskIpOne = {"11111111","11111111","00000000","00000000"};
        IPv4Address netmaskOne = new IPv4Address(netmaskIpOne, Type.BINARY);

        IPv4Network iPv4NetworkTwo = new IPv4Network();
        String[] netmaskIpTwo = {"11111111","11111111","11000000","00000000"};
        IPv4Address netmaskTwo = new IPv4Address(netmaskIpTwo, Type.BINARY);


        //when

        iPv4NetworkOne.setMaxAmountHosts(netmaskOne);
        iPv4NetworkTwo.setMaxAmountHosts(netmaskTwo);


        //then
        assertEquals(65536,iPv4NetworkOne.getMaxAmountHosts());
        assertEquals(16384,iPv4NetworkTwo.getMaxAmountHosts());



    }

}
