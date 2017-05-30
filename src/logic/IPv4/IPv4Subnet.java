package logic.IPv4;

import logic.IPv4.IPv4Address;
import logic.Type;

import java.util.Arrays;

public class IPv4Subnet {
    private IPv4Address networkIpAddress;
    private IPv4Address broadcastId;
    private IPv4HostAddress[] hostIpAddresses;
    private IPv4Address subnetmask;
    private int amountHosts;
    private int suffix;


    public IPv4Subnet() {

    //this.suffix = suffix;
    //this.networkIpAddress = networkIpAddress;
    //this.subnetmask = createSubnetmaskBy(suffix);

    }


    public IPv4HostAddress[] createIPv4HostAddresses(int amountHosts, String[] ipAddressBlocks) {

        IPv4HostAddress[] iPv4HostAddresses = new IPv4HostAddress[amountHosts];

        for (int i = 0; i < amountHosts; i++){
            int blockNumber = getBlockNumber(ipAddressBlocks, 3);
            if(blockNumber < 3){
                for (int j = 3; blockNumber < j; j--){
                    ipAddressBlocks[j] = "0";
                }
            }
            ipAddressBlocks[blockNumber] = String.valueOf(Integer.parseInt(ipAddressBlocks[blockNumber]) + 1);
            IPv4Address iPv4Address = new IPv4Address(ipAddressBlocks.clone(),Type.DECIMAL);
            iPv4HostAddresses[i] = new IPv4HostAddress(iPv4Address);

        }

        return iPv4HostAddresses;
    }


    public int getBlockNumber(String[] networkIPBlock, int number){
        if(Integer.parseInt(networkIPBlock[number]) < 255){
            return number;
        }
        int newNumber = number - 1;

        return getBlockNumber(networkIPBlock, newNumber);
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

    public IPv4Address getNetworkIpAddress() {
        return networkIpAddress;
    }

    public void setNetworkIpAddress(IPv4Address networkIpAddress) {
        this.networkIpAddress = networkIpAddress;
    }
}
