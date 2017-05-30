import static org.junit.Assert.*;

import logic.IPv4.IPv4Address;
import logic.IPv4.IPv4Network;
import logic.IPv4.IPv4Subnet;
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

    @Test
    public void createSuffixByTest(){
        //given
        IPv4Network iPv4Network = new IPv4Network();
        int amountOfHostsOne = 40;
        int amountOfHostsTwo = 300;
        int amountOfHostsThree = 1337;
        int amountOfHostsFour = 10;
        int amountOfHostsFive = 64;


        //when
        int prefixOne = iPv4Network.createSuffixBy(amountOfHostsOne);
        int prefixTwo = iPv4Network.createSuffixBy(amountOfHostsTwo);
        int prefixThree = iPv4Network.createSuffixBy(amountOfHostsThree);
        int prefixFour = iPv4Network.createSuffixBy(amountOfHostsFour);
        int prefixFive = iPv4Network.createSuffixBy(amountOfHostsFive);

        //then
        assertEquals(26, prefixOne);
        assertEquals(23,prefixTwo);
        assertEquals(21,prefixThree);
        assertEquals(28,prefixFour);
        assertEquals(25,prefixFive);
    }

    @Test
    public void createIPv4SubnetTest(){
        //given
        IPv4Network iPv4Network = new IPv4Network();
        String[] netmaskIpOne = {"192","168","1","0"};
        String[] netmaskIpTwo = {"11111111","11111111","11000000","00000000"};
        String[] netmaskIpThree = {"11111111","11111111","11000000","00000000"};
        String[] netmaskIpFour = {"11111111","11111111","11000000","00000000"};

        //when
        IPv4Subnet iPv4SubnetOne = iPv4Network.createIPv4Subnet(new IPv4Address(netmaskIpOne,Type.DECIMAL),60);

        //then
        assertEquals(62,iPv4SubnetOne.getHostIpAddresses().length);
    }

}
