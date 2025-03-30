#!/bin/bash

if [ $# -lt 1 ]; then
    echo "Usage: $0 [option]"
    echo "Available options:"
    echo "  -c compile"
    echo "  -i install"
    echo "  -d deploy"
    echo "  -t test"
    exit 1
fi

appName="combo"
option="$1"
start=$(date +%s)
opMsg="compile"

# 根据参数执行不同操作
case "$option" in
    -c)
        echo $opMsg
        mvnd -B clean compile -Dmaven.test.skip=true
        ;;
    -i)
        opMsg="install"
        echo $opMsg
        mvnd -B clean install -Dmaven.test.skip=true
        ;;
    -t)
        opMsg="test"
        echo $opMsg
        mvnd -B clean test -Psurefire-test
        mvnd surefire-report:report-only
        ;;
    -d)
        opMsg="deploy"
        echo $opMsg
        mvnd -B clean install deploy:deploy -Dmaven.test.skip=true -Pgit-commit-id-package
        ;;
    *)
        echo "无效的参数选项，请使用 -c, -i, -t, -d"
        exit 1
        ;;
esac

end=$(date +%s)

elapsed=$((end - start))

echo "Time taken to $opMsg $appName is $elapsed seconds"