

setmqaut -m IBQD1 -n '**' -t queue -g mquser -remove
setmqaut -m IBQD1 -n SYSTEM.DEFAULT.MODEL.QUEUE -t queue -g mquser -remove
setmqaut -m IBQD1 -n SYSTEM.ADMIN.COMMAND.QUEUE -t queue -g mquser -remove
setmqaut -m IBQD1 -n SYSTEM.MQEXPLORER.REPLY.MODEL -t queue -g mquser -remove
setmqaut -m IBQD1 -n '**' -t topic -g mquser -remove
setmqaut -m IBQD1 -n '**' -t channel -g mquser -remove
setmqaut -m IBQD1 -n '**' -t clntconn -g mquser -remove
setmqaut -m IBQD1 -n '**' -t authinfo -g mquser -remove
setmqaut -m IBQD1 -n '**' -t listener -g mquser -remove
setmqaut -m IBQD1 -n '**' -t namelist -g mquser -remove
setmqaut -m IBQD1 -n '**' -t process -g mquser -remove
setmqaut -m IBQD1 -n '**' -t service -g mquser -remove
setmqaut -m IBQD1 -t qmgr -g mquser -remove
setmqaut -m IBQD1 -n '**' -t queue -g mquser +browse +dsp +inq +put +get
setmqaut -m IBQD1 -n SYSTEM.DEFAULT.MODEL.QUEUE -t queue -g mquser +inq +browse +get
setmqaut -m IBQD1 -n SYSTEM.ADMIN.COMMAND.QUEUE -t queue -g mquser +dsp +inq +put
setmqaut -m IBQD1 -n SYSTEM.MQEXPLORER.REPLY.MODEL -t queue -g mquser +dsp +inq +get
setmqaut -m IBQD1 -n '**' -t topic -g mquser +dsp +pub
setmqaut -m IBQD1 -n '**' -t channel -g mquser +dsp
setmqaut -m IBQD1 -n '**' -t clntconn -g mquser +dsp
setmqaut -m IBQD1 -n '**' -t authinfo -g mquser +dsp
setmqaut -m IBQD1 -n '**' -t listener -g mquser +dsp
setmqaut -m IBQD1 -n '**' -t namelist -g mquser +dsp
setmqaut -m IBQD1 -n '**' -t process -g mquser +dsp
setmqaut -m IBQD1 -n '**' -t service -g mquser +dsp
setmqaut -m IBQD1 -t qmgr -g mquser +dsp +inq +connect


setmqaut -m IBQD1 -n '**' -t queue -g mqadmin -remove
setmqaut -m IBQD1 -n SYSTEM.DEFAULT.MODEL.QUEUE -t queue -g mqadmin -remove
setmqaut -m IBQD1 -n SYSTEM.ADMIN.COMMAND.QUEUE -t queue -g mqadmin -remove
setmqaut -m IBQD1 -n SYSTEM.MQEXPLORER.REPLY.MODEL -t queue -g mqadmin -remove
setmqaut -m IBQD1 -n '**' -t topic -g mqadmin -remove
setmqaut -m IBQD1 -n '**' -t channel -g mqadmin -remove
setmqaut -m IBQD1 -n '**' -t clntconn -g mqadmin -remove
setmqaut -m IBQD1 -n '**' -t authinfo -g mqadmin -remove
setmqaut -m IBQD1 -n '**' -t listener -g mqadmin -remove
setmqaut -m IBQD1 -n '**' -t namelist -g mqadmin -remove
setmqaut -m IBQD1 -n '**' -t process -g mqadmin -remove
setmqaut -m IBQD1 -n '**' -t service -g mqadmin -remove
setmqaut -m IBQD1 -t qmgr -g mqadmin -remove
setmqaut -m IBQD1 -n '**' -t queue -g mqadmin +browse +dsp +inq +put +get +crt +chg +dlt
setmqaut -m IBQD1 -n SYSTEM.DEFAULT.MODEL.QUEUE -t queue -g mqadmin +inq +browse +get
setmqaut -m IBQD1 -n SYSTEM.ADMIN.COMMAND.QUEUE -t queue -g mqadmin +dsp +inq +put
setmqaut -m IBQD1 -n SYSTEM.MQEXPLORER.REPLY.MODEL -t queue -g mqadmin +dsp +inq +get
setmqaut -m IBQD1 -n '**' -t topic -g mqadmin +dsp +pub +crt +chg +dlt
setmqaut -m IBQD1 -n '**' -t channel -g mqadmin +dsp
setmqaut -m IBQD1 -n '**' -t clntconn -g mqadmin +dsp
setmqaut -m IBQD1 -n '**' -t authinfo -g mqadmin +dsp
setmqaut -m IBQD1 -n '**' -t listener -g mqadmin +dsp
setmqaut -m IBQD1 -n '**' -t namelist -g mqadmin +dsp
setmqaut -m IBQD1 -n '**' -t process -g mqadmin +dsp
setmqaut -m IBQD1 -n '**' -t service -g mqadmin +dsp
setmqaut -m IBQD1 -t qmgr -g mqadmin +dsp +inq +connect
