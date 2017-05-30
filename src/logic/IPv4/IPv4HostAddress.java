package logic.IPv4;

public class IPv4HostAddress {
    private IPv4Address ipv4Address;
    private int id;
    private String name;

    public IPv4HostAddress(IPv4Address ipv4Address){
        this.ipv4Address = ipv4Address;
        this.id = getLastValueOfIpBlock(ipv4Address.getIpAddressBlocks());

    }

    public IPv4HostAddress(IPv4Address ipv4Address, String name){
        this.ipv4Address = ipv4Address;
        this.id = getLastValueOfIpBlock(ipv4Address.getIpAddressBlocks());
        this.name = name;
    }

    public int getLastValueOfIpBlock(String[] ipAddressBlock){
        return Integer.parseInt(ipAddressBlock[3]);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IPv4Address getIpv4Address() {
        return ipv4Address;
    }
}
