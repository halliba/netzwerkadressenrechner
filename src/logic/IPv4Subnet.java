package logic;


import java.util.stream.IntStream;
import java.util.stream.Stream;

public class IPv4Subnet {
    private IPv4Address networkId;
    private IPv4Address broadcastId;
    private IPv4Address[] hostIpAddresses;
    private IPv4Address subnetmask;
    private int amountHosts;
    private int suffix;


    public void setSubnetmask(int suffix) {
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

        this.subnetmask = (IPv4Address) new IPv4Address(ipAddressBlocks, Type.BINARY).convertIPAddressTo(Type.DECIMAL);
    }



    public IPv4Address getSubnetmask() {
        return subnetmask;
    }
}
