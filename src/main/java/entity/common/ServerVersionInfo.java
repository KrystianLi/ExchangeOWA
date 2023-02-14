package entity.common;

public class ServerVersionInfo {
    private int MajorVersion;
    private int MinorVersion;
    private int MajorBuildNumber;
    private int MinorBuildNumber;
    private String Version;

    public int getMajorVersion() {
        return MajorVersion;
    }

    public void setMajorVersion(int majorVersion) {
        MajorVersion = majorVersion;
    }

    public int getMinorVersion() {
        return MinorVersion;
    }

    public void setMinorVersion(int minorVersion) {
        MinorVersion = minorVersion;
    }

    public int getMajorBuildNumber() {
        return MajorBuildNumber;
    }

    public void setMajorBuildNumber(int majorBuildNumber) {
        MajorBuildNumber = majorBuildNumber;
    }

    public int getMinorBuildNumber() {
        return MinorBuildNumber;
    }

    public void setMinorBuildNumber(int minorBuildNumber) {
        MinorBuildNumber = minorBuildNumber;
    }

    public String getVersion() {
        return Version;
    }

    public void setVersion(String version) {
        Version = version;
    }
}
