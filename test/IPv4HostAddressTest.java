import logic.IPv4.IPv4Address;
import logic.IPv4.IPv4HostAddress;
import logic.Type;
import org.junit.Test;
import static org.junit.Assert.*;

public class IPv4HostAddressTest {

    @Test
    public void getLastValueOfIpBlockTest(){
        //given
        String[] ipAddressBlock = new String[4];
        ipAddressBlock[0] = "192";
        ipAddressBlock[1] = "168";
        ipAddressBlock[2] = "172";
        ipAddressBlock[3] = "100";
        IPv4HostAddress iPv4HostAddress = new IPv4HostAddress(new IPv4Address(ipAddressBlock, Type.DECIMAL));

        //when
        int lastIpBlock = iPv4HostAddress.getLastValueOfIpBlock(ipAddressBlock);

        //then
        assertEquals(100, lastIpBlock);
    }
}
