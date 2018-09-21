setmqaut -m IBQQ1 -n '**' -t queue -g mquser +browse +dsp +put +get
setmqaut -m IBQQ1 -n SYSTEM.ADMIN.COMMAND.QUEUE -t queue -g mquser +dsp +inq +put
setmqaut -m IBQQ1 -n SYSTEM.MQEXPLORER.REPLY.MODEL -t queue -g mquser +dsp +inq +get
setmqaut -m IBQQ1 -n '**' -t topic -g mquser +dsp +pub
setmqaut -m IBQQ1 -n '**' -t channel -g mquser +dsp
setmqaut -m IBQQ1 -n '**' -t clntconn -g mquser +dsp
setmqaut -m IBQQ1 -n '**' -t authinfo -g mquser +dsp
setmqaut -m IBQQ1 -n '**' -t listener -g mquser +dsp
setmqaut -m IBQQ1 -n '**' -t namelist -g mquser +dsp
setmqaut -m IBQQ1 -n '**' -t process -g mquser +dsp
setmqaut -m IBQQ1 -n '**' -t service -g mquser +dsp
setmqaut -m IBQQ1 -t qmgr -g mquser +dsp +inq +connect

setmqaut -m IBQQ1 -n '**' -t queue -g mqadmin +browse +dsp +put +get +crt +chg +dlt
setmqaut -m IBQQ1 -n SYSTEM.ADMIN.COMMAND.QUEUE -t queue -g mqadmin +dsp +inq +put
setmqaut -m IBQQ1 -n SYSTEM.MQEXPLORER.REPLY.MODEL -t queue -g mqadmin +dsp +inq +get
setmqaut -m IBQQ1 -n '**' -t topic -g mqadmin +dsp +pub +crt +chg +dlt
setmqaut -m IBQQ1 -n '**' -t channel -g mqadmin +dsp
setmqaut -m IBQQ1 -n '**' -t clntconn -g mqadmin +dsp
setmqaut -m IBQQ1 -n '**' -t authinfo -g mqadmin +dsp
setmqaut -m IBQQ1 -n '**' -t listener -g mqadmin +dsp
setmqaut -m IBQQ1 -n '**' -t namelist -g mqadmin +dsp
setmqaut -m IBQQ1 -n '**' -t process -g mqadmin +dsp
setmqaut -m IBQQ1 -n '**' -t service -g mqadmin +dsp
setmqaut -m IBQQ1 -t qmgr -g mqadmin +dsp +inq +connect
