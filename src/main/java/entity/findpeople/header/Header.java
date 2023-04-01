package entity.findpeople.header;

import entity.common.ServerVersionInfo;

public class Header {
    private ServerVersionInfo ServerVersionInfo;

    public ServerVersionInfo getServerVersionInfo() {
        return ServerVersionInfo;
    }

    public void setServerVersionInfo(ServerVersionInfo serverVersionInfo) {
        this.ServerVersionInfo = serverVersionInfo;
    }
}
