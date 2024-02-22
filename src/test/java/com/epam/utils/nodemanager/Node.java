package com.epam.utils.nodemanager;

import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
public class Node {

    @Getter
    private List<DeviceInfo> android;
    @Getter
    private List<DeviceInfo> ios;
}
