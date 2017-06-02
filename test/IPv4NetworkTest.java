import static org.junit.Assert.*;

import logic.IPv4.IPv4Address;
import logic.IPv4.IPv4Network;
import logic.IPv4.IPv4Subnet;
import logic.Type;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class IPv4NetworkTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void maxAmountHostsTest(){
        //given
        IPv4Network iPv4NetworkOne = new IPv4Network();
        String[] netmaskIpOne = {"11111111","11111111","00000000","00000000"};
        IPv4Address netmaskOne = new IPv4Address(netmaskIpOne, Type.BINARY);

        IPv4Network iPv4NetworkTwo = new IPv4Network();
        String[] netmaskIpTwo = {"11111111","11111111","11000000","00000000"};
        IPv4Address netmaskTwo = new IPv4Address(netmaskIpTwo, Type.BINARY);

        IPv4Network iPv4NetworkThree = new IPv4Network();
        String[] netmaskIpThree = {"11111111","11111111","11111111","00000000"};
        IPv4Address netmaskThree = new IPv4Address(netmaskIpThree, Type.BINARY);



        //when
        iPv4NetworkOne.setMaxAmountHosts(iPv4NetworkOne.createMaxAmountHosts(netmaskOne));
        iPv4NetworkTwo.setMaxAmountHosts(iPv4NetworkTwo.createMaxAmountHosts(netmaskTwo));
        iPv4NetworkThree.setMaxAmountHosts(iPv4NetworkThree.createMaxAmountHosts(netmaskThree));


        //then
        assertEquals(65534,iPv4NetworkOne.getMaxAmountHosts());
        assertEquals(16382,iPv4NetworkTwo.getMaxAmountHosts());
        assertEquals(254,iPv4NetworkThree.getMaxAmountHosts());



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
        int maxAmountHostsOne = iPv4Network.createMaxAmountHosts(iPv4Network.createSubnetmaskBy(iPv4Network.createSuffixBy(400)));
        iPv4Network.setMaxAmountHosts(maxAmountHostsOne);
        IPv4Network iPv4Network1 = new IPv4Network();
        int maxAmountHostsTwo = iPv4Network1.createMaxAmountHosts(iPv4Network1.createSubnetmaskBy(iPv4Network1.createSuffixBy(255)));
        iPv4Network1.setMaxAmountHosts(maxAmountHostsTwo);
        String[] netmaskIpOne = {"192","168","1","0"};
        String[] netmaskIpTwo = {"192","168","2","0"};
        String[] netmaskIpThree = {"11111111","11111111","11000000","00000000"};
        String[] netmaskIpFour = {"11111111","11111111","11000000","00000000"};

        //when
        IPv4Subnet iPv4SubnetOne = iPv4Network.createIPv4Subnet(new IPv4Address(netmaskIpOne,Type.DECIMAL),60);
        IPv4Subnet iPv4SubnetTwo = iPv4Network.createIPv4Subnet(new IPv4Address(netmaskIpOne,Type.DECIMAL),300);
        IPv4Subnet iPv4SubnetThree = iPv4Network.createIPv4Subnet(new IPv4Address(netmaskIpTwo,Type.DECIMAL),64);

        //then
        assertEquals(62,iPv4SubnetOne.getHostIpAddresses().length);
        assertEquals("63",iPv4SubnetOne.getBroadcastAddress().getIpAddressBlocks()[3]);
        assertEquals(510,iPv4SubnetTwo.getHostIpAddresses().length);
        assertEquals("127",iPv4SubnetThree.getHostIpAddresses()[iPv4SubnetThree.getHostIpAddresses().length - 1].getIpv4Address().getIpAddressBlocks()[3]);
    }

    @Test
    public void fillSubnetsListTest(){
        //given
        IPv4Network iPv4Network = new IPv4Network();
        String[] netmaskIpOne = {"192","168","1","0"};
        String[] netmaskIpTwo = {"11111111","11111111","11000000","00000000"};
        String[] netmaskIpThree = {"11111111","11111111","11000000","00000000"};
        String[] netmaskIpFour = {"11111111","11111111","11000000","00000000"};

        String[] subnetmaskIpOne = {"11111111","11111111","00000000","00000000"};
        IPv4Address subnetmaskOne = new IPv4Address(subnetmaskIpOne, Type.BINARY);

        iPv4Network.setMaxAmountHosts(iPv4Network.createMaxAmountHosts(subnetmaskOne));

        //when
        IPv4Subnet iPv4SubnetOne = iPv4Network.createIPv4Subnet(new IPv4Address(netmaskIpOne,Type.DECIMAL),60);
        IPv4Subnet iPv4SubnetTwo = iPv4Network.createIPv4Subnet(new IPv4Address(netmaskIpOne,Type.DECIMAL),300);

        //iPv4Network.fillSubnetsListWithSubnet(iPv4SubnetOne,iPv4Network.getMaxAmountHosts());
        //iPv4Network.fillSubnetsListWithSubnet(iPv4SubnetTwo,iPv4Network.getMaxAmountHosts());

        //then

    }

    @Test()
    public void fillSubnetsListExceptionSubnetBiggerThanNetworkTest(){

        //given
        IPv4Network iPv4Network = new IPv4Network();
        String[] netmaskIpOne = {"192","168","1","0"};

        String[] subnetmaskIpOne = {"11111111","11111111","11111111","00000000"};

        IPv4Address subnetmaskOne = new IPv4Address(subnetmaskIpOne, Type.BINARY);

        iPv4Network.setMaxAmountHosts(iPv4Network.createMaxAmountHosts(subnetmaskOne));

        //when
        thrown.expect(IndexOutOfBoundsException.class);
        thrown.expectMessage(iPv4Network.SUBNET_BIGGER_NETWORK_MSG);
        IPv4Subnet iPv4SubnetTwo = iPv4Network.createIPv4Subnet(new IPv4Address(netmaskIpOne,Type.DECIMAL),300);

    }


}
