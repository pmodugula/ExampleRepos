setmqaut -m IBQ1 -n '**' -t queue -g _GROUP +browse +dsp +put +get
setmqaut -m IBQ1 -n SYSTEM.ADMIN.COMMAND.QUEUE -t queue -g _GROUP +dsp +inq +put
setmqaut -m IBQ1 -n SYSTEM.MQEXPLORER.REPLY.MODEL -t queue -g _GROUP +dsp +inq +get
setmqaut -m IBQ1 -n '**' -t topic -g _GROUP +dsp
setmqaut -m IBQ1 -n '**' -t channel -g _GROUP +dsp
setmqaut -m IBQ1 -n '**' -t clntconn -g _GROUP +dsp
setmqaut -m IBQ1 -n '**' -t authinfo -g _GROUP +dsp
setmqaut -m IBQ1 -n '**' -t listener -g _GROUP +dsp
setmqaut -m IBQ1 -n '**' -t namelist -g _GROUP +dsp
setmqaut -m IBQ1 -n '**' -t process -g _GROUP +dsp
setmqaut -m IBQ1 -n '**' -t service -g _GROUP +dsp
setmqaut -m IBQ1 -t qmgr -g _GROUP +dsp +inq +connect
