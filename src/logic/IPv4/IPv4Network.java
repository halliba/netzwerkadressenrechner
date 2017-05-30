package logic.IPv4;

import logic.Type;

import java.util.ArrayList;

public class IPv4Network {
    private ArrayList<IPv4Subnet> ipv4Subnets = new ArrayList<>();
    private IPv4Address netIpAddress;
    private IPv4Address subnetMask;
    private int suffix;
    private int maxAmountHosts;


    public IPv4Network(){

    }

    public IPv4Network(int suffix, int netIpAddress){
        this.suffix = suffix;
        this.subnetMask = createSubnetmaskBy(suffix);
        this.setMaxAmountHosts(subnetMask);
    }


    public IPv4Subnet createIPv4Subnet(IPv4Address networkAddress, int amountHosts){
        int suffix = createSuffixBy(amountHosts);
        IPv4Subnet iPv4Subnet = new IPv4Subnet(suffix,networkAddress);
        iPv4Subnet.setMaxAmountHosts(iPv4Subnet.getSubnetmask());
        int maxAmountHostsOfSubnet = iPv4Subnet.getMaxAmountHosts();
        iPv4Subnet.setHostIpAddresses(iPv4Subnet.createIPv4HostAddresses(maxAmountHostsOfSubnet,networkAddress.getIpAddressBlocks()));

        return iPv4Subnet;
    }


    public int createSuffixBy(int amountOfHosts){
        int host = amountOfHosts + 2;
        int suffix = 32;

        Double logOfHost = Math.ceil(Math.log(host) / Math.log(2));
        long logOfHostLong = logOfHost.longValue();
        suffix -= logOfHostLong;

        return suffix;
    }

    public IPv4Address createSubnetmaskBy(int suffix) {
        String binarySubnetMaskString = "";
        for(int i = 0; i < suffix; i++){
            if (i % 8 == 0 && i != 0){
                binarySubnetMaskString += ".1";
            }else {
                binarySubnetMaskString += "1";
            }
        }


        for(int i = suffix; i < 32 ; i++){
            if (i % 8 == 0){
                binarySubnetMaskString += ".0";
            }else {
                binarySubnetMaskString += "0";
            }
        }


        String[] ipAddressBlocks = binarySubnetMaskString.split("\\.");

        return (IPv4Address) new IPv4Address(ipAddressBlocks, Type.BINARY).convertIPAddressTo(Type.DECIMAL);
    }


    public void setMaxAmountHosts(IPv4Address subnetMask){
        IPv4Address netmaskBinary = (IPv4Address) subnetMask.convertIPAddressTo(Type.BINARY);
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
