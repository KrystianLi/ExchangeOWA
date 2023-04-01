package entity.common;

import com.alibaba.fastjson2.annotation.JSONField;

public class ServerVersionInfo {
    @JSONField(name = "MajorVersion",ordinal = 0)
    private int majorVersion;
    @JSONField(name = "MinorVersion",ordinal = 1)
    private int minorVersion;
    @JSONField(name = "MajorBuildNumber",ordinal = 2)
    private int majorBuildNumber;
    @JSONField(name = "MinorBuildNumber",ordinal = 3)
    private int minorBuildNumber;
    @JSONField(name = "Version",ordinal = 0)
    private String version;

    public int getMajorVersion() {
        return majorVersion;
    }

    public void setMajorVersion(int majorVersion) {
        this.majorVersion = majorVersion;
    }

    public int getMinorVersion() {
        return minorVersion;
    }

    public void setMinorVersion(int minorVersion) {
        this.minorVersion = minorVersion;
    }

    public int getMajorBuildNumber() {
        return majorBuildNumber;
    }

    public void setMajorBuildNumber(int majorBuildNumber) {
        this.majorBuildNumber = majorBuildNumber;
    }

    public int getMinorBuildNumber() {
        return minorBuildNumber;
    }

    public void setMinorBuildNumber(int minorBuildNumber) {
        this.minorBuildNumber = minorBuildNumber;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
