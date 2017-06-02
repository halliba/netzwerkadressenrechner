package logic.IPv4;

import logic.Type;

import java.util.Arrays;

public class IPv4Net {
    private IPv4Address networkIpAddress;
    private IPv4Address subnetmask;
    private int suffix;
    private int maxAmountHosts;



    public int createMaxAmountHosts(IPv4Address subnetMask){
        IPv4Address netmaskBinary = (IPv4Address) subnetMask.convertIPAddressTo(Type.BINARY);
        String netmaskBinaryString = netmaskBinary.convertIpv4ToString();
        int count = 0;

        for(char string : netmaskBinaryString.toCharArray()){
            count = (string == '0') ? count + 1 : count;
        }

        return  (int) Math.pow(2,count) - 2;
    }

    public void setMaxAmountHosts(int maxAmountHosts){
        this.maxAmountHosts = maxAmountHosts;
    }

    public int getMaxAmountHosts(){
        return this.maxAmountHosts;
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

    public String toString(){
       return networkIpAddress.convertIpv4ToString() + "/" + getSuffix();
    }

    public IPv4Address getSubnetmask() {
        return subnetmask;
    }

    public int getSuffix() {
        return suffix;
    }

    public void setSuffix(int suffix) {
        this.suffix = suffix;
    }

    public void setSubnetmask(IPv4Address subnetmask) {
        this.subnetmask = subnetmask;
    }

    public IPv4Address getNetworkIpAddress() {
        return networkIpAddress;
    }

    public void setNetworkIpAddress(IPv4Address networkIpAddress) {
        this.networkIpAddress = networkIpAddress;
    }
}
