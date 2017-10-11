#!/bin/bash
echo 'finding executable file...'
readonly TARGET_FILE=smart-lock-server.jar

# 是否存在执行文件的标志
declare -i exist

# 判断本目录下是否存在该执行文件 
if [ -x $TARGET_FILE ]; then
	echo 'found executable file!'
	# 存在
	exist=1
else
	echo 'not found executable file!'
	# 不存在
	exist=0
fi

if [[ $exist = 1 ]]; then
	running=`java -jar -Xmx512m -Xmx256m TARGET_FILE &`
	if [[ $running = 0 ]]; then
		echo 'server started!'
	else
		echo 'server start failed!'
	fi
fi
