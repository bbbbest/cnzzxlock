#!/bin/bash

# 目标执行文件
readonly TARGET_FILE=smart-lock-server.jar
# 是否存在执行文件的标志
declare -i exist

# 检查进程ID
checkpid() {
	P=`jps -l | grep "$TARGET_FILE" | awk '{print $1}'`
	if [ -n "$P" ]; then
        echo "Server start successfully!"
	else
		echo "Server start failed!"
	fi
}

checkfile() {
    echo 'finding executable file...'
    # 判断本目录下是否存在该执行文件
    if [ -f "$TARGET_FILE" ]; then
        # 存在
        exist=1
        echo 'found executable file!'
    else
        # 不存在
        exist=0
        echo 'not found executable file!'
    fi
}

runifexist() {
    if [[ $exist = 1 ]]; then
	    nohup java -jar -Xmx512m -Xmx256m $TARGET_FILE >/dev/null 2>&1 &
	    checkpid
    fi
}

main() {
checkfile
runifexist
}

main
