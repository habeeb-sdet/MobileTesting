package com.epam.utils.nodemanager;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class DeviceInfo {

    private String osVersion;
    private int systemPort;
    private int mjpegServerPort;
    private String mjpegScreenshotUrl;

}
