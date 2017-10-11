#!/bin/bash
#
# 进程ID
declare PID

# 执行文件的名称
readonly TARGET_FILE=smart-lock-server.jar

# 检查进程ID
checkpid() {
	echo "Finding running application: smart lock server..."
	P=`jps -l | grep "$TARGET_FILE" | awk '{print $1}'`
	if [ -n "$P" ]; then
		# 存在
		echo "Found!"
		PID=$P
	else
		# 不存在
		echo "Not found!"
		PID=""
	fi
}

# 关闭进程
sd() {	
	if [ -n "$PID" ]; then
		echo "Stopping..."
		kill "$PID"
		echo "Stopped!"
	fi
}

# 主函数
main() {
	# 检查是否运行
	checkpid
	# 关闭
	sd
}

# 执行主函数
main
