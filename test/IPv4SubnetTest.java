import logic.IPv4.IPv4Address;
import logic.IPv4.IPv4HostAddress;
import logic.IPv4.IPv4Subnet;
import logic.Type;
import org.junit.Test;
import static org.junit.Assert.*;

public class IPv4SubnetTest {

    @Test
    public void subnetmaskTest(){
        //given
        IPv4Subnet iPv4Subnet = new IPv4Subnet();
        String subnetMaskByPrefix;

        //when
        subnetMaskByPrefix = iPv4Subnet.createSubnetmaskBy(26).convertIpv4ToString();
        //then
        assertEquals("255.255.255.192", subnetMaskByPrefix);

        //when
        subnetMaskByPrefix = iPv4Subnet.createSubnetmaskBy(18).convertIpv4ToString();
        //then
        assertEquals("255.255.192.0", subnetMaskByPrefix);

        //when
        subnetMaskByPrefix = iPv4Subnet.createSubnetmaskBy(8).convertIpv4ToString();
        //then
        assertEquals("255.0.0.0", subnetMaskByPrefix);

        //when
        subnetMaskByPrefix = iPv4Subnet.createSubnetmaskBy(24).convertIpv4ToString();
        //then
        assertEquals("255.255.255.0", subnetMaskByPrefix);
    }

    @Test
    public void createHostIPv4AddressByTest(){
        //given
        IPv4Subnet iPv4SubnetOne = new IPv4Subnet();
        IPv4Subnet iPv4SubnetTwo = new IPv4Subnet();
        String[] ipAddressBlockOne = new String[4];
        ipAddressBlockOne[0] = "192";
        ipAddressBlockOne[1] = "168";
        ipAddressBlockOne[2] = "172";
        ipAddressBlockOne[3] = "0";

        String[] ipAddressBlockTwo = new String[4];
        ipAddressBlockTwo[0] = "192";
        ipAddressBlockTwo[1] = "168";
        ipAddressBlockTwo[2] = "172";
        ipAddressBlockTwo[3] = "252";


        //when
        IPv4HostAddress[] iPv4HostAddressesOne = iPv4SubnetOne.createIPv4HostAddresses(4,ipAddressBlockOne);
        //IPv4HostAddress[] iPv4HostAddressesTwo = iPv4SubnetTwo.createIPv4HostAddresses(300,ipAddressBlockTwo);

        //then
        assertEquals(4, iPv4HostAddressesOne.length);
        assertEquals("2", iPv4HostAddressesOne[1].getIpv4Address().getIpAddressBlocks()[3]);

        //assertEquals(300, iPv4HostAddressesTwo.length);
        //assertEquals("173", iPv4HostAddressesTwo[4].getIpv4Address().getIpAddressBlocks()[2]);
    }
}
