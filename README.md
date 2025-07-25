# IBM-MQ-Integration-With-Spring-Boot

# Verification steps after installation of IBM MQ
---------------------------------------------------------
1. Verify Installation
   cmd:  dspmqver
2. Start the Queue Manager   
   Create a Queue Manager (if not created yet):
   cmd:  crtmqm TEST.QMGR
   Start the Queue Manager:
   cmd:  strmqm TEST.QMGR
   Check its status:
3. Create Queue and Test Message
   Create a queue:
   cmd: runmqsc TEST.QMGR
   Then enter these commands:
   cmd: DEFINE QLOCAL(TEST.QUEUE)
   END
   Put a message into the queue:
   cmd: echo "Hello MQ" | amqsput TEST.QUEUE TEST.QMGR
   Read the message back:
   cmd: amqsget TEST.QUEUE TEST.QMGR




Task	                       Command
-------------------------------------------------------
Create user	                net user app P@ssw0rd123 /add
Grant MQ rights	            setmqaut -m TEST.QMGR -t qmgr -p app +connect +inq
Enable queue access	        setmqaut -m TEST.QMGR -t queue -n TEST.QUEUE -p app +put +get
Allow user on channel	    runmqsc TEST.QMGR
                            SET CHLAUTH('DEV.APP.SVRCONN') TYPE(USERMAP) CLNTUSER('app') USERSRC(CHKLOCL)
                            REFRESH SECURITY TYPE(CONNAUTH)
END
Refresh auth	            REFRESH SECURITY TYPE(CONNAUTH)  

IBM MQ is not listening on port 1414
----------------------------------------------------------------------
Step 1: Open MQ Command Console
runmqsc TEST.QMGR
Step 2: Create Listener
DEFINE LISTENER(LISTENER.TCP) TRPTYPE(TCP) PORT(1414) CONTROL(QMGR)
Step 3: Start Listener
START LISTENER(LISTENER.TCP)

Verify Listener Is Running

DISPLAY LISTENER(LISTENER.TCP)
Expected output:
scss

LISTENER(LISTENER.TCP) TRPTYPE(TCP) PORT(1414) STATUS(RUNNING)



Note : The channel you're using in application.properties does not exist.
---------------------------------------------------------------------------
Step 1: Check Channel Name
In application.properties, you likely have:
ibm.mq.channel=DEV.APP.SVRCONN

or create your own channel via MQ.
---------------------------------
Open MQ console:

bash : 
runmqsc TEST.QMGR

Then enter:
DEFINE CHANNEL(MY.CHANNEL) CHLTYPE(SVRCONN) TRPTYPE(TCP)

SET CHLAUTH(MY.CHANNEL) TYPE(BLOCKUSER) USERLIST('*MQADMIN') ACTION(REMOVE)
SET CHLAUTH(MY.CHANNEL) TYPE(ADDRESSMAP) ADDRESS(*) USERSRC(CHANNEL) ACTION(ADD)

Then use in application.properties:
-------------------------------------
ibm.mq.channel=MY.CHANNEL


URL hit from postman:
----------------------------
URL : http://localhost:8081/mq/send?message=HelloMQ


