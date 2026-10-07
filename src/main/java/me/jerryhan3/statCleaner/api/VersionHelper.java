package me.jerryhan3.statCleaner.api;

public interface VersionHelper {
    boolean isVersionAtLeast(int major, int minor);
    boolean isVersionAtLeast(int major, int minor, int fix);
    int getMajorVersion();
    int getMinorVersion();
    int getFixVersion();
}
