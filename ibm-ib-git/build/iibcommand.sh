#!/usr/bin/env bash
MQSIFUN=mqsifun
if [[ -f "$(dirname $0)/$MQSIFUN" ]]; then
  . $(dirname $0)/$MQSIFUN
else
  . $MQSIFUN
fi
DRYRUN=1

if [ -n "$(grep -E '^(start|stop|restart|status|deployed)' <<< $1)" ]; then
  COMMAND=$1
  shift
fi
if [[ -z "$COMMAND" ]]; then
  if [ -n "$(grep -E '.*(start|stop|restart|status|deployed)$' <<< ${0%\.[^/]*})" ]; then
    # if [[ "${0%\.[^/]*}" =~ .*(restart|start|stop|status|deployed)$ ]]; then
    COMMAND="$(sed 's/.*\(start|stop|restart|status|deployed\)$/\1/g;' <<< ${0%\.[^/]*})"
  else
    COMMAND="status"
  fi
fi
BROKER=$1

case $COMMAND in
  status)
    # expected running
    EC=0 _mqsi_check $BROKER
    _RC=$?
    ;;
  deployed)
    echo status
    for broker in $(_mqsi_list_broker); do
      echo "broker: $broker"
      for eg in $(_mqsi_list_executiongroup $broker); do
        echo " eg: $eg"
        for app in $(_mqsi_list_application $broker $eg); do
          echo "  app: $app"
        done
      done
    done
    ;;
  start)
    echo starting $BROKER
    _mqsi_start $BROKER
    ;;
  stop)
    echo stopping $BROKER
    _mqsi_stop $BROKER
    ;;
  restart)
    echo restarting $BROKER
    _mqsi_stop $BROKER
    RC=$?
    if [ $RC -eq 0 ]; then
      _mqsi_start $BROKER
    fi
    ;;
  *)
esac



