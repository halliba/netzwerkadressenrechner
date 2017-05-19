import logic.IPv4Subnet;
import org.junit.Test;
import static org.junit.Assert.*;

public class IPv4SubnetTest {

    @Test
    public void subnetmaskTest(){
        IPv4Subnet iPv4Subnet = new IPv4Subnet();
        iPv4Subnet.setSubnetmask(26);
        assertEquals("255.255.255.192", iPv4Subnet.getSubnetmask().ipAddressBlockToString());

        iPv4Subnet.setSubnetmask(18);
        assertEquals("255.255.192.0", iPv4Subnet.getSubnetmask().ipAddressBlockToString());

        iPv4Subnet.setSubnetmask(8);
        assertEquals("255.0.0.0", iPv4Subnet.getSubnetmask().ipAddressBlockToString());

        iPv4Subnet.setSubnetmask(24);
        assertEquals("255.255.255.0", iPv4Subnet.getSubnetmask().ipAddressBlockToString());
    }
}
