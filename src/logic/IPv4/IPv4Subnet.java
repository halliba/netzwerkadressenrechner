package logic.IPv4;

import logic.IPv4.IPv4Address;
import logic.Type;

public class IPv4Subnet {
    private IPv4Address networkIpAddress;
    private IPv4Address broadcastId;
    private IPv4HostAddress[] hostIpAddresses;
    private IPv4Address subnetmask;
    private int amountHosts;
    private int suffix;


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

    public IPv4Address getNetworkIpAddress() {
        return networkIpAddress;
    }

    public void setNetworkIpAddress(IPv4Address networkIpAddress) {
        this.networkIpAddress = networkIpAddress;
    }
}
