package com.epam.utils.nodemanager;

import com.epam.constants.Commons;
import com.epam.constants.TestProps;
import com.epam.exceptions.DeviceNotFoundException;
import com.epam.exceptions.InvalidDeviceException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NodeManager {

    private NodeManager(){}

    private static NodeManager nodeManager;

    private static Node node;

    public static NodeManager get(){
        if(nodeManager == null){
            nodeManager = new NodeManager();
            loadNodeList();
        }
        return nodeManager;
    }

    private static void loadNodeList(){
        String nodeFileName = "\\Nodes.json";
        ObjectMapper mapper = new ObjectMapper();
        try {
            node = mapper.readValue(new File(Commons.NODES_DIR + nodeFileName), Node.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public DeviceInfo getNodeInfo(){
        List<DeviceInfo> deviceInfoList;
        switch (TestProps.getPlatform()){
            case ANDROID: deviceInfoList = node.getAndroid(); break;
            case IOS: deviceInfoList = node.getIos(); break;
            default: throw new InvalidDeviceException();
        }

        for(DeviceInfo deviceInfo : deviceInfoList){
            if(deviceInfo.getOsVersion().equals(TestProps.getOsVersion())){
                return deviceInfo;
            }
        }

        throw new DeviceNotFoundException(TestProps.getOsVersion());
    }

}
