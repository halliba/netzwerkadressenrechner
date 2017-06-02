package gui;

import logic.Converter;
import logic.IPv4.IPv4Network;
import logic.IPv4.IPv4Subnet;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.swing.*;
import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SubnetPanel extends JPanel {

    private JTabbedPane tabbedPane;
    private IPv4Network iPv4Network;
    private String networkTitle;
    private Map<String, IPv4Subnet> iPv4SubnetMap = new HashMap<>();
    private DefaultListModel<String> model = new DefaultListModel<>();
    private static ArrayList<HostPanel> hostPanels = new ArrayList<>();
    private JSONArray data;


    public SubnetPanel(IPv4Network iPv4Network,String network, NetworkCalculator networkCalculator, JSONArray data) {
        this.data = data;
        this.iPv4Network = iPv4Network;
        // Get Data an write to listModel
        for (int i = 0; i < data.size(); i++) {
            JSONObject networkObject = (JSONObject) data.get(i);

            if (networkObject.get("id").toString().equals(network)) {
                JSONArray subnets = (JSONArray) networkObject.get("subnets");
                for (int j = 0; j < subnets.size(); j++) {
                    JSONObject subnetObject = (JSONObject) subnets.get(j);
                    String subnetString = subnetObject.get("subnet").toString();
                    JSONArray hosts = (JSONArray) subnetObject.get("hosts");
                    iPv4Network.fillSubnetsListWithSubnet(iPv4Network.getNextNetworkIPAddress(),hosts.size(),iPv4Network.getMaxAmountHosts());
                    model.addElement(subnetString);
                    iPv4SubnetMap.put(subnetString,iPv4Network.getIpv4Subnets().get(iPv4Network.getIpv4Subnets().size() - 1));
                }
            }
        }

        // Set networkTitle
        networkTitle = network;

        // set the Subnet-Panel Layout to BorderLayout
        this.setLayout(new BorderLayout());
        // Initialise TabbedPane
        tabbedPane = networkCalculator.getTabbedPane();



        // -------------------------------------------------------------------------------------------------------------
        // Create Panels and give them specific Layout
        // -------------------------------------------------------------------------------------------------------------

        // Create Interaction Panel for Open-, Delete-Button, IP-Numberfields and Create Button
        JPanel interactionPanel = new JPanel();
        interactionPanel.setLayout(new BoxLayout(interactionPanel, BoxLayout.PAGE_AXIS));

        // Open and Delete Button Panel
        JPanel openDeleteButtonPanel = new JPanel();
        openDeleteButtonPanel.setLayout(new FlowLayout());

        // Numberfields Panel
        JPanel numberFieldsPanel = new JPanel();
        numberFieldsPanel.setLayout(new FlowLayout());



        // -------------------------------------------------------------------------------------------------------------
        // Create JList with ListModel
        // -------------------------------------------------------------------------------------------------------------

        // JList for all Networks
        JScrollPane scrollPane = new JScrollPane();
        JList<String> subnetList =  new JList<>(model);
        scrollPane.setViewportView(subnetList);

        subnetList.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    openNewHostPanel(subnetList, networkCalculator);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });



        // -------------------------------------------------------------------------------------------------------------
        // Create Open Button and Delete Button and add Action Listeners to open the from the list selected Network
        // in a new Tab or to delete the selected Network from the list
        // -------------------------------------------------------------------------------------------------------------

        // Open Selected Network Button
        JButton openSubnetPanelButton = new JButton();
        openSubnetPanelButton.setText("Open");
        openSubnetPanelButton.addActionListener(e -> {
            openNewHostPanel(subnetList, networkCalculator);
        });

        // Delete Selected Network Button
        JButton deleteSubnetPanelButton = new JButton();
        deleteSubnetPanelButton.setText("Delete");
        deleteSubnetPanelButton.addActionListener(e -> model.removeElement(subnetList.getSelectedValue()));



        // -------------------------------------------------------------------------------------------------------------
        // Create InputFields and set Preferred Size
        // -------------------------------------------------------------------------------------------------------------

        JTextField amountOfHostsTextField = new JTextField();
        amountOfHostsTextField.setPreferredSize(new Dimension(40, 20));



        // -------------------------------------------------------------------------------------------------------------
        // Create createNewSubnetButton
        // -------------------------------------------------------------------------------------------------------------

        JButton createNewSubnetButton = new JButton();
        createNewSubnetButton.setText("Create");
        createNewSubnetButton.addActionListener(e -> {
            int amountHosts = Integer.parseInt(amountOfHostsTextField.getText());
            this.iPv4Network.fillSubnetsListWithSubnet(iPv4Network.getNextNetworkIPAddress(),amountHosts,this.iPv4Network.getMaxAmountHosts());
            IPv4Subnet iPv4Subnet = iPv4Network.getIpv4Subnets().get(iPv4Network.getIpv4Subnets().size() - 1);
            String subnetString = iPv4Subnet.toString();
            model.addElement(subnetString);
            iPv4SubnetMap.put(subnetString,iPv4Subnet);
            // TODO get User Input and Create new Subnet according to the data
        });



        // -------------------------------------------------------------------------------------------------------------
        // Adding Elements to the different Panels
        // -------------------------------------------------------------------------------------------------------------

        add(scrollPane, BorderLayout.CENTER);
        openDeleteButtonPanel.add(openSubnetPanelButton);
        openDeleteButtonPanel.add(deleteSubnetPanelButton);
        interactionPanel.add(openDeleteButtonPanel);
        numberFieldsPanel.add(createNewSubnetButton);
        numberFieldsPanel.add(new JLabel("a new Subnet with"));
        numberFieldsPanel.add(amountOfHostsTextField);
        numberFieldsPanel.add(new JLabel("hosts"));
        interactionPanel.add(numberFieldsPanel);
        add(interactionPanel, BorderLayout.PAGE_END);

    }

    public void openNewHostPanel(JList subnetList, NetworkCalculator networkCalculator) {

        String subnet = (String) subnetList.getSelectedValue();
        if (subnet != null && networkCalculator.getTabIndexFromTitle(tabbedPane, subnet) == 0) {
            HostPanel hostPanel = new HostPanel(iPv4SubnetMap.get(subnet), networkTitle, subnet, data);
            hostPanels.add(hostPanel);
            tabbedPane.add(subnet, hostPanel);
            tabbedPane.setSelectedIndex(networkCalculator.getTabIndexFromTitle(tabbedPane, subnet));
            tabbedPane.add("X", new JPanel());
        }
    }

    public String getNetworkTitle() {
        return networkTitle;
    }

    public DefaultListModel<String> getModel() {
        return model;
    }

    public static ArrayList<HostPanel> getHostPanels() {
        return hostPanels;
    }
}
