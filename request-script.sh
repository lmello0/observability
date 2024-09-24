TIMES=245
for i in $(eval echo "{1..$TIMES}")
do
    # siege -c 1 -r 10 http://localhost:8080/status
    # siege -c 3 -r 5 http://localhost:8080/ping
    # siege -c 2 -r 5 http://localhost:8080/cpu_task/10000
    # sleep 5
    curl http://localhost:8080/memory_task
done
