@echo off
echo ========================================
echo 清理 Gradle 缓存脚本
echo ========================================
echo.

echo 正在关闭 Gradle 进程...
taskkill /F /IM java.exe 2>nul
taskkill /F /IM gradle.exe 2>nul
echo.

echo 正在清理用户 Gradle 缓存...
if exist "%USERPROFILE%\.gradle\wrapper\dists\gradle-8.9*" (
    rmdir /s /q "%USERPROFILE%\.gradle\wrapper\dists\gradle-8.9*"
    echo 已删除 gradle-8.9 相关文件夹
) else (
    echo 未找到 gradle-8.9 缓存文件夹
)
echo.

echo 正在清理项目 Gradle 缓存...
if exist ".gradle" (
    rmdir /s /q ".gradle"
    echo 已删除项目 .gradle 文件夹
) else (
    echo 未找到项目 .gradle 文件夹
)
echo.

echo 正在清理 IDE 缓存...
if exist ".idea" (
    rmdir /s /q ".idea"
    echo 已删除 .idea 文件夹
) else (
    echo 未找到 .idea 文件夹
)
echo.

echo ========================================
echo 清理完成！
echo ========================================
echo.
echo 请按照以下步骤操作：
echo 1. 重新打开 Android Studio
echo 2. 点击 "File" -^> "Invalidate Caches / Restart"
echo 3. 选择 "Invalidate and Restart"
echo 4. IDE 重启后，点击 "Sync Project with Gradle Files"
echo.
pause
