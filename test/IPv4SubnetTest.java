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
        subnetMaskByPrefix = iPv4Subnet.createSubnetmaskBy(26).ipAddressBlockToString();
        //then
        assertEquals("255.255.255.192", subnetMaskByPrefix);

        //when
        subnetMaskByPrefix = iPv4Subnet.createSubnetmaskBy(18).ipAddressBlockToString();
        //then
        assertEquals("255.255.192.0", subnetMaskByPrefix);

        //when
        subnetMaskByPrefix = iPv4Subnet.createSubnetmaskBy(8).ipAddressBlockToString();
        //then
        assertEquals("255.0.0.0", subnetMaskByPrefix);

        //when
        subnetMaskByPrefix = iPv4Subnet.createSubnetmaskBy(24).ipAddressBlockToString();
        //then
        assertEquals("255.255.255.0", subnetMaskByPrefix);
    }

    @Test
    public void createHostIPv4AddressByTest(){
        //given
        IPv4Subnet iPv4Subnet = new IPv4Subnet();
        IPv4Address networkIPv4Address;
        String[] ipAddressBlock = new String[4];
        ipAddressBlock[0] = "192";
        ipAddressBlock[1] = "168";
        ipAddressBlock[2] = "172";
        ipAddressBlock[3] = "0";


        //when
        networkIPv4Address = new IPv4Address(ipAddressBlock, Type.DECIMAL);
        ipAddressBlock[3] = "43";
        IPv4HostAddress iPv4HostAddress = new IPv4HostAddress(new IPv4Address(ipAddressBlock, Type.DECIMAL));

        //then
        //assertEquals(iPv4HostAddress, iPv4Subnet.createHostIPv4AddressBy(networkIPv4Address,43));
    }
}
