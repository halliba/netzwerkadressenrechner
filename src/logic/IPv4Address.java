package logic;

public class IPv4Address extends IPAddress {

    public IPv4Address(String[] ipAddressBlocks, Type type) {
        super.setIpAddressBlocks(ipAddressBlocks);
        super.setType(type);
    }

    public String ipAddressBlockToString(){
        String ipAddressBlockAsString = "";
        String[] ipAddressBlocks = super.getIpAddressBlocks();
        for(int i = 0; i < ipAddressBlocks.length; i++){
            if(i == 0){
                ipAddressBlockAsString = ipAddressBlocks[i];
            }else {
                ipAddressBlockAsString += "." + ipAddressBlocks[i];
            }

        }

        return ipAddressBlockAsString;
    }

}
