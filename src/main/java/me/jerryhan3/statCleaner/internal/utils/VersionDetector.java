/*
 * Copyright (c) 2025-2026. JerryHan3.
 *
 * This is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this software. If not, see <https://www.gnu.org/licenses/>
 * and navigate to version 3 of the GNU Affero General Public License.
 */

package me.jerryhan3.statCleaner.internal.utils;

import org.bukkit.Bukkit;

public class VersionDetector {
    /**
     * 判断服务器版本是否高于特定版本。
     * @param major Minecraft大版本号，即第一个.前面的数字。必须为整数1或者大于等于26的整数。
     * @param minor Minecraft小版本号，即第一个.后、第二个.前的数字。
     * @return 当前服务器版本是否高于指定的MC版本。
     * @throws IllegalArgumentException 当major参数不在指定范围内时抛出。
     */
    public static boolean isVersionAtLeast(int major, int minor) throws IllegalArgumentException {
        if (major != 1 && major < 26) throw new IllegalArgumentException("Invalid major version number! Must be 1 or 26 and above.");
        String version = Bukkit.getBukkitVersion().split("-")[0];
        String[] parts = version.split("\\.");
        int thisMajor = Integer.parseInt(parts[0]);
        int thisMinor = Integer.parseInt(parts[1]);
        if (major > thisMajor) return true;
        if (major < thisMajor) return false;
        return minor >= thisMinor;
    }

    /**
     * 判断服务器版本是否高于特定版本。
     * @param major Minecraft大版本号，即第一个.前面的数字。必须为整数1或者大于等于26的整数。
     * @param minor Minecraft小版本号，即第一个.后、第二个.前的数字。
     * @param fix Minecraft修订号，即第二个.后的数字。如不存在（如版本1.21），请指定为0。
     * @return 当前服务器版本是否高于指定的MC版本。
     * @throws IllegalArgumentException 当major参数不在指定范围内时抛出。
     */
    public static boolean isVersionAtLeast(int major, int minor, int fix) throws IllegalArgumentException {
        if (major != 1 && major < 26) throw new IllegalArgumentException("Invalid major version number! Must be 1 or 26 and above.");
        String version = Bukkit.getBukkitVersion().split("-")[0];
        String[] parts = version.split("\\.");
        if (parts[2] == null) parts[2] = "0";
        int thisMajor = Integer.parseInt(parts[0]);
        int thisMinor = Integer.parseInt(parts[1]);
        int thisFix = Integer.parseInt(parts[2]);
        if (major > thisMajor) return true;
        if (major < thisMajor) return false;
        if (minor > thisMinor) return true;
        if (minor < thisMinor) return false;
        return fix >= thisFix;
    }

    /**
     * 判断服务器版本是否高于特定大版本。
     * @deprecated 本方法仅支持校验1.x.x版本的MC之间的关系。<br>由于Mojang修改MC版本号命名规范，本方法已弃用，目前重映射到新的isVersionAtLeast(int, int)方法中。
     */
    @Deprecated
    public static boolean isVersionAtLeastLegacy(int major) {
        return isVersionAtLeast(1, major);
    }

    /**
     * 判断服务器版本是否高于特定大+小版本的组合。
     * @deprecated 本方法仅支持校验1.x.x版本的MC之间的关系。<br>由于Mojang修改MC版本号命名规范，本方法已弃用，目前重映射到新的isVersionAtLeast(int, int)方法中。
     */
    @Deprecated
    public static boolean isVersionAtLeastLegacy(int major, int minor) {
        return isVersionAtLeast(1, major, minor);
    }

    public static int getMajorVersion() {
        String version = Bukkit.getBukkitVersion().split("-")[0];
        String[] parts = version.split("\\.");
        return Integer.parseInt(parts[0]);
    }

    public static int getMinorVersion() {
        String version = Bukkit.getBukkitVersion().split("-")[0];
        String[] parts = version.split("\\.");
        return Integer.parseInt(parts[1]);
    }

    public static int getFixVersion() {
        String version = Bukkit.getBukkitVersion().split("-")[0];
        String[] parts = version.split("\\.");
        if (parts[2] == null) return 0;
        return Integer.parseInt(parts[2]);
    }
}
