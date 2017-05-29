package logic.IPv4;

import com.sun.deploy.util.StringUtils;
import logic.Type;

import java.util.ArrayList;
import java.util.Arrays;

public class IPv4Network {
    private ArrayList<IPv4Subnet> ipv4Subnets = new ArrayList<>();
    private IPv4Address netmask;
    private int suffix;
    private int maxAmountHosts;


    public void setMaxAmountHosts(IPv4Address netmask){
        IPv4Address netmaskBinary = (IPv4Address) netmask.convertIPAddressTo(Type.BINARY);
        String netmaskBinaryString = netmaskBinary.convertIpv4ToString();
        int count = 0;

        for(char string : netmaskBinaryString.toCharArray()){
            count = (string == '0') ? count + 1 : count;
        }

        this.maxAmountHosts = (int) Math.pow(2,count);
    }


    public int getMaxAmountHosts() {
        return maxAmountHosts;
    }
}
